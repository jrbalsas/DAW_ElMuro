
package com.daw.muro.pr8.model;

import java.io.Serializable;
import javax.validation.constraints.Size;

public class Mensaje{
    private Integer id;
    private String identificador;

    @Size(min=2,max=100, message="La longitud del mensaje no es válida")
    private String texto;

    public Mensaje () {
        id=0;
        identificador="Desconocido";
        texto="";
    }
    public Mensaje (Integer nid, String identif, String men) {
        id=nid;
        identificador=identif;
        texto=men;
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
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the mensaje to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
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
