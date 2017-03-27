package com.daw.muro.controller;

import com.daw.muro.Mensaje;
import com.daw.muro.model.MensajeDAO;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/muro")
@SessionAttributes("identificador")
public class MuroSpringController {

    @Autowired
    @Qualifier("MensajeDAOList")  //Needed when there are more than one implementation
    private MensajeDAO mensajes;
    
    @RequestMapping(value="/identificador",method=RequestMethod.GET)
    String formIndentificador() {
        return "muro/identificador";
    }
    
    @RequestMapping(value="/identificador",method=RequestMethod.POST)
    String envioIdentificador(
            @RequestParam(value="identificador",required=true) 
            String identificador, ModelMap model) {

       //save attribute on (session) context!
       model.addAttribute("identificador", identificador); 
       return "redirect:mensajes";
    }
    
    @RequestMapping(value="/mensajes",method=RequestMethod.GET)
    String formMensajes(ModelMap model,
            @ModelAttribute("mensaje") Mensaje m) {

        List<Mensaje> lmensajes = mensajes.buscaTodos();
        model.addAttribute("mensajes",lmensajes);
        return "muro/mensajes_spring";
    }

    @RequestMapping(value="/mensajes",method=RequestMethod.POST)
    String envioMensaje(
            @ModelAttribute("identificador") String identificador, 
            @ModelAttribute("mensaje") @Valid Mensaje m,            
            BindingResult result, //IMPORTAN: MUST appear after @Valid attribute
            ModelMap model
            ) {
        String view;
        
        if (!result.hasErrors()) {
            m.setIdentificador(identificador);
            mensajes.nuevoMensaje(m);
            view="redirect:mensajes";
        } else {
            //Show message form with original text and bean validation error message
            List<Mensaje> lmensajes = mensajes.buscaTodos();
            model.addAttribute("mensajes",lmensajes);
            view="muro/mensajes_spring";
        }
        return view;
    }

    
}
