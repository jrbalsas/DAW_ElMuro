package com.daw.muro.pr09;

import com.daw.muro.pr09.model.Mensaje;
import com.daw.muro.pr09.model.MensajeDAO;
import com.daw.muro.pr09.model.Preferencias;
import com.daw.muro.pr09.model.qualifiers.DAOJdbc;
import com.daw.muro.pr09.model.qualifiers.DAOList;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "muro")
@ViewScoped
public class MuroController implements Serializable {

    private static final long serialVersionUID = 1L;

    //DAO Injections using CDI
    @Inject @DAOList
    //@Inject @DAOJdbc
    private MensajeDAO mensajesDAO;
    
    //View-Model
    private Mensaje mensaje;
    @Inject
    private Preferencias preferencias;


    public MuroController() {
        //DAO NOT available
    }

    @PostConstruct
    private void init() {
        //DAO available (CDI completed)
        mensaje = new Mensaje();
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public List<Mensaje> getMensajes() {
            return mensajesDAO.buscaTodos();
    }

    //ACTIONS
    public String cambiaIdentificador() {
        //On this point, identificador is set correctly in Preferencias bean
        //at Session Scope
        return "mensajes?faces-redirect=true";
    }

    public String enviaMensaje() {
        //Process new message
        //mesaje text is set from view... but identificador is missing yet
        mensaje.setIdentificador(preferencias.getUsuario());
        mensajesDAO.nuevoMensaje(mensaje);
        return "mensajes?faces-redirect=true";
    }
}
