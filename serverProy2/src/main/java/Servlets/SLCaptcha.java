/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controladores.ControlCargarCaptchas;
import generadores.GenStrHTMLJS;
import objetos.Captcha;

/**
 *
 * @author user-ubunto
 */
@WebServlet(name = "SLCaptcha", urlPatterns = {"/SLCaptcha"})
public class SLCaptcha extends HttpServlet {
    
    private String paramIdForm;
    private Captcha captcha;
    private String strHtml;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print(strHtml);
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet SLCaptcha</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SLCaptcha at " + request.getContextPath() + "</h1>");
//            out.println("<h1>El captcha es: " + captcha.getIdCaptcha()+"Con nombre: "+captcha.getNombreCaptcha() + "</h1>");            
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        this.paramIdForm = request.getParameter("idForm");
        ControlCargarCaptchas control =new ControlCargarCaptchas();
        captcha = control.getCaptchaPorId(paramIdForm);
        GenStrHTMLJS genStr = new GenStrHTMLJS(captcha);
        this.strHtml = genStr.getgStr();
        control.insertarUtilizacion(paramIdForm);
        processRequest(request, response);
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
