package com.aewinformatica.sis03os.ui.service;

import com.aewinformatica.sis03os.model.Cliente;
import com.aewinformatica.sis03os.model.enun.TipoPessoa;
import com.aewinformatica.sis03os.ui.repository.Clientes;
import com.aewinformatica.sis03os.ui.repository.filter.ClienteFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jessica
 */
@Service
public class ClienteService {
    
    @Autowired
    private Clientes clientes;
    
    	public Cliente salvar(Cliente cliente) {

		String validacoes = this.getValidacoes(cliente);

		if (!validacoes.equals("")) {
			throw new NegocioException(validacoes);
		}


		return clientes.save(cliente);
	}
    
	private String getValidacoes(Cliente cliente) {
		
		if(cliente.getContribuinte() == true && cliente.getInscricaoEstadual().equals("")) {
			return ("Adicione a  Inscrição Estatual !");
		}

		if (this.isEstrangeiro(cliente)) {
			if (this.isDoc(cliente)) {
				if (this.isNotDocDisponivel(cliente)) {
					return "Documento já cadastrado";
				}
			}
		}

		if (this.isNotDocDisponivel(cliente)) {
			if (cliente.getTipoPessoa().equals(TipoPessoa.FISICA)) {
				return "CPF já cadastrado";
			} else {
				return "CNPJ já cadastrado";
			}
		}

		if (this.isNotEstrangeiro(cliente) && this.isNotExportacao(cliente)) {
			if (cliente.getEnderecos().isEmpty()) {
				return "Endereço: informe pelo menos um endereço";
			}
		}

		return "";
	}

	private boolean isEstrangeiro(Cliente cliente) {
		return cliente.getEstrangeiro().equals(true);
	}

	private boolean isNotEstrangeiro(Cliente cliente) {
		return !this.isEstrangeiro(cliente);
	}

	boolean isExportacao(Cliente cliente) {
		return cliente.getExterior().equals(true);
	}

	boolean isNotExportacao(Cliente cliente) {
		return !this.isExportacao(cliente);
	}

	private boolean isDocDisponivel(Cliente cliente) {

		if (this.isDoc(cliente)) {

			ClienteFilter filtro = new ClienteFilter();
			List<Cliente> clientesTodos = clientes.pesquisaCliente(filtro);

			for (Cliente item : clientesTodos) {
				if (this.isDoc(item)) {
					if (item.getDocReceitaFederal().equals(cliente.getDocReceitaFederal())) {
						if (!item.getId().equals(cliente.getId()))
							return false;
					}
				}
			}
		}

		return true;
	}

	private boolean isNotDocDisponivel(Cliente cliente) {
		return !this.isDocDisponivel(cliente);
	}

	private boolean isDoc(Cliente cliente) {
		return cliente.getDocReceitaFederal() != null && !cliente.getDocReceitaFederal().equals("");
	}
}
