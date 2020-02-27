package com.aewinformatica.sis03os.ui.repository;

import com.aewinformatica.sis03os.model.Cliente;
import com.aewinformatica.sis03os.ui.repository.filter.ClienteFilter;
import com.aewinformatica.sis03os.ui.repository.helper.cliente.ClientesQueries;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jessica
 */
@Repository
public interface Clientes extends JpaRepository<Cliente, Long>,ClientesQueries {
    

}
