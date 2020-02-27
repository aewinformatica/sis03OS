/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aewinformatica.sis03os.ui.repository.helper.cliente;

import com.aewinformatica.sis03os.model.Cliente;
import com.aewinformatica.sis03os.model.StatusCliente;
import com.aewinformatica.sis03os.model.enun.TipoCliente;
import com.aewinformatica.sis03os.ui.repository.filter.ClienteFilter;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jessica
 */
public class ClientesImpl implements ClientesQueries {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Cliente> pesquisaCliente(ClienteFilter filtro) {

        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Cliente.class);

        criteria.add(Restrictions.eq("tipoCliente", TipoCliente.CLIENTE));
        criteria.add(Restrictions.eq("status", StatusCliente.CADASTRO));
        criteria.createAlias("empresas", "empresas");
//        criteria.add(Restrictions.eq("empresas.id", this.usuarioLogado.getUsuario().getEmpresa().getId()));

        if (StringUtils.isNotBlank(filtro.getNome())) {
            Criterion rest1 = Restrictions.or(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
            Criterion rest2 = Restrictions
                    .or(Restrictions.ilike("docReceitaFederal", filtro.getNome(), MatchMode.START));
            criteria.add(Restrictions.or(rest1, rest2));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }
}
