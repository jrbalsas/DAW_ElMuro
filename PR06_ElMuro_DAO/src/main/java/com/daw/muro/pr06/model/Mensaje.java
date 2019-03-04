
package com.daw.muro.pr06.model;

import java.io.Serializable;
import javax.validation.constraints.Size;

public class Mensaje implements Serializable{
    private String identificador;
    @Size(min=2,max=25, message="La longitud del nombre debe estar entre {min} y {max} caracteres")
    private String mensaje;

    public Mensaje () {
        identificador="Desconocido";
        mensaje="";
    }
    public Mensaje (String id, String men) {
        identificador=id;
        mensaje=men;
    }

    
    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
