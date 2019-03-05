
package com.daw.muro.pr05.model;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author admin
 */
@ApplicationScoped
public class MensajesList {
    private List<Mensaje> mensajes;

    public MensajesList() {
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
