
package com.daw.muro.model;

import java.io.Serializable;

public class Mensaje implements Serializable{
    private String identificador;
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
