
package com.daw.muro.pr09.model;

import com.daw.muro.pr09.model.qualifiers.DAOList;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author admin
 */
@ApplicationScoped
@DAOList
public class MensajeDAOList implements MensajeDAO{
    private List<Mensaje> mensajes;

    public MensajeDAOList() {
        mensajes=new ArrayList<>();
        mensajes.add(new Mensaje(1, "pepe","Hola"));
        mensajes.add(new Mensaje(2, "ana","¿Qué tal?"));
        mensajes.add(new Mensaje(3, "pepe","Mola"));
    }
    public boolean nuevoMensaje(Mensaje m) {
        mensajes.add(m);
        return true;
    }
    public List<Mensaje> buscaTodos() {
        return mensajes;
    }
    
}
