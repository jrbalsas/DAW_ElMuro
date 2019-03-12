
package com.daw.muro.pr07.model;

import java.util.List;


/** Generic Interfaz for Mensaje DAOs implementations*/
public interface MensajeDAO {
    
    List<Mensaje> buscaTodos();
    boolean nuevoMensaje(Mensaje m);
 
}
