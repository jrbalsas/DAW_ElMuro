
package com.daw.muro.pr05.model;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author admin
 */
@Named(value = "prefs") //Allows access from views
@SessionScoped
public class Preferencias implements Serializable{
    private String usuario="";

    public Preferencias() {
    }
    
    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
