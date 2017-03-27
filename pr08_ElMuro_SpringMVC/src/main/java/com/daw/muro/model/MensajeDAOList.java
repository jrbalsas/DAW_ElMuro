package com.daw.muro.model;

import com.daw.muro.Mensaje;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;


/** Sample DAO implementation for development*/
@Repository("MensajeDAOList")
public class MensajeDAOList implements MensajeDAO{
    
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
