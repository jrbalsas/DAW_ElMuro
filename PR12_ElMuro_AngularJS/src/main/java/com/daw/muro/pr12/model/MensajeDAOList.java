package com.daw.muro.pr12.model;

import com.daw.muro.pr12.Mensaje;
import com.daw.muro.pr12.model.MensajeDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/** Sample DAO implementation for development*/
@ApplicationScoped
@MensajeDAOListBean
public class MensajeDAOList implements MensajeDAO, Serializable{
    
    private List<Mensaje> mensajes;
    private Integer lastId;
    
    public MensajeDAOList () {
        lastId=0;
        mensajes=new ArrayList<Mensaje>();
        nuevoMensaje(new Mensaje(0,"Carlos","Hola"));
        nuevoMensaje(new Mensaje(0,"Ana","Quedamos esta tarde?"));
        nuevoMensaje(new Mensaje(0,"Carlos","Sitio y hora"));
        
    }
    @Override
    public List<Mensaje> buscaTodos() {
        return mensajes;
    }

    @Override
    public boolean nuevoMensaje(Mensaje m) {
        m.setId(lastId++);
        mensajes.add(m);
        return true;
    }
    
}
