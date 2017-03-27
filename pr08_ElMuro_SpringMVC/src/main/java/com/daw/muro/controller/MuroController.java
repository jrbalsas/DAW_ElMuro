package com.daw.muro.controller;

import com.daw.muro.Mensaje;
import com.daw.muro.model.MensajeDAOJDBC;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.daw.muro.model.MensajeDAOJDBC;
import com.daw.muro.model.MensajeDAOList;
import com.daw.muro.model.MensajeDAO;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;

@WebServlet(name = "MuroController", urlPatterns = {"/muro"})
//Authorization rule using annotation (only in Servlets!)
//@ServletSecurity(@HttpConstraint(rolesAllowed={"USUARIOS"}))
public class MuroController extends HttpServlet {

    private MensajeDAO mensajes;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init()
            throws ServletException {

        super.init(); 
        //Select DAO implementation
        mensajes=new MensajeDAOList();
        //mensajes=new MensajeDAOJDBC();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); //Aceptar caracteres acentuados y ñ
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String identificador=(String)request.getSession().getAttribute("identificador");
        RequestDispatcher rd;
                
        if (identificador==null) {
            //Mostrar formulario de entrada de identificador
            rd=request.getRequestDispatcher("/WEB-INF/muro/identificador.jsp");
        } else {
            //El usuario ya tiene nombre asignado: mostrar mensajes
            rd=request.getRequestDispatcher("/WEB-INF/muro/mensajes.jsp");
            request.setAttribute("mensajes",mensajes.buscaTodos());       
        }
        //Pasar el control a la vista
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String identificador=request.getParameter("identificador");
        //TODO, validar identificador
        if (identificador!=null) {
            request.getSession().setAttribute("identificador", identificador);
            response.sendRedirect("muro");         
        }
        
        String nuevoMensaje=request.getParameter("mensaje");
        if (nuevoMensaje!=null) {
            if (nuevoMensaje!="") {
                identificador=(String) request.getSession().getAttribute("identificador");
                Mensaje m=new Mensaje(0,identificador,nuevoMensaje);
                mensajes.nuevoMensaje(m);
                response.sendRedirect("muro");
                return;
            }else {
                //Mensaje incorrecto
                request.setAttribute("error", "El mensaje es incorrecto");
                request.setAttribute("mensajes", mensajes.buscaTodos());
                request.setAttribute("mensaje", nuevoMensaje);
                RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/muro/mensajes.jsp");
                rd.forward(request, response);
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
        return "El Muro";
    }

}
