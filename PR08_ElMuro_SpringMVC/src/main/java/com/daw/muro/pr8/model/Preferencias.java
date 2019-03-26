/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.muro.pr8.model;

import java.io.Serializable;
import org.springframework.stereotype.Component;
//import javax.inject.Named;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author admin
 */
@Component
@SessionScope
public class Preferencias implements Serializable{
    private String usuario="Desconocido";

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
