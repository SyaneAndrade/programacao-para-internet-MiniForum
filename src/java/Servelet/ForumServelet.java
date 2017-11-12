/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import Repository.ListaMensagens;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.Mensagem;
import Repository.ListaMensagens;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Syane
 */
@WebServlet(name = "ForumServelet", urlPatterns = {"/forum/gravar"})
public class ForumServelet extends HttpServlet {

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
        HttpSession session = request.getSession();
        ListaMensagens lsMsg = ListaMensagens.getMensagens();
        Mensagem mensagem = new Mensagem();
        String pathArquivo = getServletContext().getInitParameter("arquivo");
        lsMsg.setArquivo(pathArquivo);
        mensagem.setMensagem(request.getParameter("mensagem"));
        mensagem.setEmail(request.getParameter("email"));
        session.setAttribute("email", mensagem.getEmail());
        session.setAttribute("mensagem", mensagem.getMensagem());
        if(lsMsg.Mensagens.isEmpty()){
            lsMsg.lerArquivo();
        }
        lsMsg.gravarMensagem(mensagem);
        Integer cont = (Integer) getServletContext().getAttribute("cont");
        if(cont == null) cont = new Integer(0);
        cont++;
        getServletContext().setAttribute("cont", cont);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Mensagem</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Mensagens</h1>");
            out.println("<h3>Mensagem: " + mensagem.getMensagem() + " </h3>");
            out.println("<h3> Email: " + mensagem.getEmail() + " </3>");
            out.println("<h2>Quantidade de acessos = " + cont + "</h2>");
            out.println("<form action=\"..\">"
                        + "<input type=\"submit\" value=\"Nova Mensagem\">"
                        + "</form>");
            out.println("<form action= \"listar\">"
                         + "<input type=\"submit\" value=\"Listar\">"
                         + "</form>");
            out.println("<form action=\"..\\login.jsp\">"+
                            "<input type=\"submit\" name=\"logout\" value=\"logout\">"+
                        "</form>");
            out.println("</body>");
            out.println("</html>");
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
