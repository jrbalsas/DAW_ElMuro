package com.daw.muro.webservice;

import com.daw.muro.Mensaje;
import com.daw.muro.model.MensajeDAO;
import com.daw.muro.model.MensajeDAOListBean;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/muro")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@RequestScoped
public class MuroRESTService {

    //DAO Injections using CDI
    @Inject @MensajeDAOListBean
    //@Inject @MensajeDAOJdbcBean
    private MensajeDAO mensajesDAO;
    
    @GET
    public List<Mensaje> recuperaMensajes() {
        List<Mensaje> l= new ArrayList<>();
        return mensajesDAO.buscaTodos();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public void nuevoMensaje(@Valid Mensaje m) {
        mensajesDAO.nuevoMensaje(m);        
    }

}
