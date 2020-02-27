/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aewinformatica.sis03os.ui.repository.helper.cliente;

import com.aewinformatica.sis03os.model.Cliente;
import com.aewinformatica.sis03os.ui.repository.filter.ClienteFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jessica
 */
public interface ClientesQueries {

    @Autowired
    public List<Cliente> pesquisaCliente(ClienteFilter filtro);
}
