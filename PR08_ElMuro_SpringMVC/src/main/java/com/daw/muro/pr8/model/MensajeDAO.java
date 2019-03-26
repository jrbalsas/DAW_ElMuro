
package com.daw.muro.pr8.model;

import com.daw.muro.pr8.model.Mensaje;
import java.util.List;


/** Generic Interfaz for Mensaje DAOs implementations*/
public interface MensajeDAO {
    
    List<Mensaje> buscaTodos();
    boolean nuevoMensaje(Mensaje m);
 
}
