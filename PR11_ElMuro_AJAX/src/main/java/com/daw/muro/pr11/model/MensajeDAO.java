
package com.daw.muro.pr11.model;

import com.daw.muro.pr11.Mensaje;
import java.util.List;


/** Generic Interfaz for Mensaje DAOs implementations*/
public interface MensajeDAO {
    
    List<Mensaje> buscaTodos();
    boolean nuevoMensaje(Mensaje m);
 
}
