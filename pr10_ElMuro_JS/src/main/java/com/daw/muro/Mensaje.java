/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daw.muro;

import java.io.Serializable;

/**
 *
 * @author alumno
 */
public class Mensaje implements Serializable{
    private Integer id;
    private String identificador;
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
