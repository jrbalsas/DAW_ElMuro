
package com.daw.muro.pr10.model;

import java.util.List;


/** Generic Interfaz for Mensaje DAOs implementations*/
public interface MensajeDAO {
    
    List<Mensaje> buscaTodos();
    boolean nuevoMensaje(Mensaje m);
 
}
