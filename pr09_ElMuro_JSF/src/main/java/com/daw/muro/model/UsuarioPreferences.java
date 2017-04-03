package com.daw.muro.model;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.validation.constraints.Size;

@SessionScoped
public class UsuarioPreferences implements Serializable{

    @Size(min=3, message="El identificador no es válido")
    private String identificador;

    public UsuarioPreferences() {
    }

    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }    
}
