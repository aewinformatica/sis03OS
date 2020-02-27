package com.aewinformatica.sis03os.ui.controller;

import com.aewinformatica.sis03os.model.Cliente;
import org.springframework.stereotype.Controller;
import com.aewinformatica.sis03os.ui.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jessica
 */
@Controller
public class ControllerCliente {
    
    
    @Autowired
    private ClienteService clienteService;
    
    public void salvar(Cliente cliente){
    
        clienteService.salvar(cliente);
    }
}
