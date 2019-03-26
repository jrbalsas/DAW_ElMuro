package com.daw.muro.pr8.controller;

import com.daw.muro.pr8.model.Mensaje;
import com.daw.muro.pr8.model.MensajeDAO;
import com.daw.muro.pr8.model.Preferencias;
import java.util.List;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/muro")
public class MuroController {

    @Autowired
    @Qualifier("MensajeDAOList")  //Needed when there are more than one implementation
    //@Qualifier("MensajeDAOJDBC")
    private MensajeDAO mensajes;

    @Autowired
    private Preferencias prefs;

    public MuroController() {
    }

    @ModelAttribute("prefs")
    public Preferencias getPreferences() {
        return prefs; //pass preferences bean to views
    }

    @GetMapping("/identificador")
    public String formIndentificador() {
        return "identificador";
    }

    @PostMapping("/identificador")
    public String envioIdentificador(
            @RequestParam(value = "identificador", required = true) String identificador, ModelMap model) {

        prefs.setUsuario(identificador);
        model.clear();
        return "redirect:mensajes";
    }

    @GetMapping("/mensajes")
    public String formMensajes(ModelMap model,
            @ModelAttribute("mensaje") Mensaje m) {

        model.addAttribute("mensajes", mensajes.buscaTodos());
        return "mensajes";
    }

    @PostMapping("/mensajes")
    public String envioMensaje(
            @ModelAttribute("mensaje") @Valid Mensaje m,
            BindingResult result, //IMPORTAN: MUST appear after @Valid attribute
            ModelMap model
    ) {
        String view="mensajes";

        if (!result.hasErrors()) {
            m.setIdentificador(prefs.getUsuario());
            mensajes.nuevoMensaje(m);
            view = "redirect:mensajes";
        } else {
            //Show message form with original text and bean validation error message
            model.addAttribute("mensajes", mensajes.buscaTodos());
        }
        return view;
    }
    @GetMapping("/logout")
    public String logout (HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }
}
