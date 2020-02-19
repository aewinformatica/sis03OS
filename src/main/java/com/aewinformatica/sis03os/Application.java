package com.aewinformatica.sis03os;

import com.aewinformatica.sis03os.ui.controller.ControllerPrincipal;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author Jessica
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
                                 builder.headless(false);
        
        ConfigurableApplicationContext context;
                                       context = builder.run(args);
        
        ControllerPrincipal controllerPrincipal = context.getBean(ControllerPrincipal.class);
                            controllerPrincipal.exibirView();
    }
}
