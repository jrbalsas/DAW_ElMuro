package com.daw.muro.controller;

import com.daw.muro.Mensaje;
import com.daw.muro.model.MensajeDAO;
import com.daw.muro.model.MensajeDAOJdbcBean;
import com.daw.muro.model.MensajeDAOListBean;
import com.daw.muro.model.UsuarioPreferences;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class Muro implements Serializable {

    private static final long serialVersionUID = 1L;

    //View-Model
    private Mensaje mensaje;
    private List<Mensaje> mensajes;
    @Inject
    private UsuarioPreferences userPrefs;

    //DAO Injections using CDI
    @Inject @MensajeDAOListBean
    //@Inject @MensajeDAOJdbcBean
    private MensajeDAO mensajesDAO;

    public Muro() {
    }

    @PostConstruct
    private void init() {
        mensaje = new Mensaje();
        mensajes = null;
    }

    //Controller Bean properties
    public UsuarioPreferences getUserPrefs() {
        return userPrefs;
    }

    public void setUserPrefs(UsuarioPreferences userPrefs) {
        this.userPrefs = userPrefs;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public List<Mensaje> getMensajes() {
        if (mensajes == null) {
            mensajes = mensajesDAO.buscaTodos();
        }
        return mensajes;
    }

    //ACTIONS
    public String cambiaIdentificador() {
        //On this point, identificador is set correctly in UserPreferences Object
        //at Session Scope
        return "mensajes?faces-redirect=true";
    }

    public String enviaMensaje() {
        //Process new message
        //mesaje text is set from view... but identificador is missing yet
        mensaje.setIdentificador(userPrefs.getIdentificador());
        mensajesDAO.nuevoMensaje(mensaje);
        mensajes=null; //reset local message list to reloat it when the view call getMessages after next redirect
        return "mensajes?faces-redirect=true";
    }
}
