/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.muro.pr06.model;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author admin
 */
@Named(value="muro")
@ApplicationScoped
public class MensajesDAO {
    private List<Mensaje> mensajes;

    public MensajesDAO() {
        mensajes=new ArrayList<>();
        mensajes.add(new Mensaje("pepe","Hola"));
        mensajes.add(new Mensaje("ana","¿Qué tal?"));
        mensajes.add(new Mensaje("pepe","Mola"));
    }
    public void nuevoMensaje(Mensaje m) {
        mensajes.add(m);
    }
    public List<Mensaje> getMensajes() {
        return mensajes;
    }
    
}
