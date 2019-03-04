package com.daw.muro.pr06;

import com.daw.muro.pr06.model.DAOList;
import com.daw.muro.pr06.model.MensajeDAOList;
import com.daw.muro.pr06.model.Mensaje;
import com.daw.muro.pr06.model.MensajeDAO;
import com.daw.muro.pr06.model.Preferencias;
import java.io.IOException;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@WebServlet(name = "MuroController", urlPatterns = {"/muro"})
public class MuroController extends HttpServlet {

    @Inject
    @DAOList
    private MensajeDAO mensajes;
    
    @Inject
    private Preferencias preferencias;

    @Inject
    private Validator validator;  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); //Accept UTF-8 parameters

        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        RequestDispatcher rd;

        if (preferencias.getUsuario() == "") {
            //Mostrar formulario de entrada de identificador
            rd = request.getRequestDispatcher("/WEB-INF/muro/identificador.jsp");
        } else {
            //El usuario ya tiene nombre asignado: mostrar mensajes
            request.setAttribute("mensajes", mensajes.buscaTodos());
            rd = request.getRequestDispatcher("/WEB-INF/muro/mensajes.jsp");
        }
        //Pasar el control a la vista
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String identificador = request.getParameter("identificador");

        if (identificador != null) {
            //TODO, validar identificador
            preferencias.setUsuario(identificador);
            response.sendRedirect("muro");   //Post-redirect-get
            return;
        }

        String nuevoMensaje = request.getParameter("mensaje");

        if (nuevoMensaje != null) {
            identificador = preferencias.getUsuario();
            Mensaje m = new Mensaje(0, identificador, nuevoMensaje);

            Set<ConstraintViolation<Mensaje>> errores = validator.validate(m);

            if (errores.size() == 0) {
                mensajes.nuevoMensaje(m);
                response.sendRedirect("muro"); //POST-Redirect-GET
                return;
            } else {
                //Mensaje incorrecto, generar vista de mensajes con mensaje original y mensaje de error
                request.setAttribute("mensaje", nuevoMensaje);
                request.setAttribute("mensajes", mensajes.buscaTodos());
                
                for (ConstraintViolation<Mensaje> error : errores) {
                    //Pass generated errors to view
                    request.setAttribute("err" + error.getPropertyPath(),
                            error.getMessage());
                }

                request.getRequestDispatcher("/WEB-INF/muro/mensajes.jsp")
                        .forward(request, response);
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Muro sample controller";
    }

}
