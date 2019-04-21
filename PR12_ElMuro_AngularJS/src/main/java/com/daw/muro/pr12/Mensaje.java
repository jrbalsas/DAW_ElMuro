package com.daw.muro.pr12;

import java.io.Serializable;
import javax.validation.constraints.Size;


public class Mensaje implements Serializable{
    private Integer id;
    private String identificador;
    @Size(min=2,max=100, message="Solo mensajes entre 2 y 100 caracteres")
    private String mensaje;

    public Mensaje () {
        id=0;
        identificador="Desconocido";
        mensaje="";
    }
    public Mensaje (Integer nid, String identif, String men) {
        id=nid;
        identificador=identif;
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

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
