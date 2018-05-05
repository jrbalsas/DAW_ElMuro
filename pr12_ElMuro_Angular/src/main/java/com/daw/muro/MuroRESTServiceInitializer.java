package com.daw.muro;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author jrbalsas
 */
@ApplicationPath("webservice")  //  Service URL: /webservice/*
public class MuroRESTServiceInitializer extends ResourceConfig {

    public MuroRESTServiceInitializer() {
        super();
        //Configure JAX-RS implementation for sending BeanValidation messages
        property("jersey.config.beanValidation.enableOutputValidationErrorEntity.server", true);
    }
    
}
