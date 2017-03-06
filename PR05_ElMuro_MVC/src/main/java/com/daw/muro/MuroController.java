package com.daw.muro;

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

@WebServlet(name = "MuroController", urlPatterns = {"/muro"})
public class MuroController extends HttpServlet {

    private List<Mensaje> mensajes;
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

        super.init(); //To change body of generated methods, choose Tools | Templates.
        mensajes=new ArrayList<Mensaje>();
        mensajes.add(new Mensaje("Carlos","Hola"));
        mensajes.add(new Mensaje("Ana","Quedamos esta tarde?"));
        mensajes.add(new Mensaje("Carlos","Sitio y hora"));
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
            request.setAttribute("mensajes",mensajes);       
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

        if (identificador!=null) {
            //TODO, validar identificador
            request.getSession().setAttribute("identificador", identificador);
            response.sendRedirect("muro");         
        }
        
        String nuevoMensaje=request.getParameter("mensaje");
        if (nuevoMensaje!=null) {
            if (nuevoMensaje.length()>3 && nuevoMensaje.length()<50) {
                //Mensaje correcto
                identificador=(String) request.getSession().getAttribute("identificador");
                Mensaje m=new Mensaje(identificador,nuevoMensaje);
                mensajes.add(m);
                response.sendRedirect("muro"); //POST-Redirect-GET
                return;
            }else {
                //Mensaje incorrecto, generar vista de mensajes con mensaje original y mensaje de error
                request.setAttribute("msgError", "La longitud del mensaje debe estar entre 3 y 50 caracteres");
                request.setAttribute("mensajes", mensajes);
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
        return "Muro sample controller";
    }

}
