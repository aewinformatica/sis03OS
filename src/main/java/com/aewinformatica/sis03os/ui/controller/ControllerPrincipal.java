package com.aewinformatica.sis03os.ui.controller;

import com.aewinformatica.sis03os.ui.ViewPrincipal;
import com.aewinformatica.sis03os.ui.shared.controller.AbstractFrameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Jessica
 */
@Controller
public class ControllerPrincipal extends AbstractFrameController{

    @Autowired
    private ViewPrincipal viewPrincipal;

    @Autowired
    public ControllerPrincipal(ViewPrincipal viewPrincipal) {
        this.viewPrincipal = viewPrincipal;
    }

    @Autowired
    public void exibirView() {
        this.viewPrincipal.setVisible(true);
    }

}
