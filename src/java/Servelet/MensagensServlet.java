/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.Mensagem;
import Repository.ListaMensagens;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Syane
 */
public class MensagensServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    String backgroundcolor;
    String h3color;
    String h1color;
    String h2color;
    
    @Override
    public void init() throws ServletException {
        this.backgroundcolor = getInitParameter("backgroundcolor");
        this.h1color = getInitParameter("h1color");
        this.h3color = getInitParameter("h3color");
        this.h2color = getInitParameter("h2color");
        if ((this.backgroundcolor == null) || (this.h1color == null) || this.h3color == null ){
             throw new UnavailableException ("Parametros errados!");
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ListaMensagens msgs = ListaMensagens.getMensagens();
        String pathArquivo = getServletContext().getInitParameter("arquivo");
        msgs.setArquivo(pathArquivo);
        HttpSession session = request.getSession();
        if(msgs.Mensagens.isEmpty()){
            msgs.lerArquivo();
        }
        this.init();
        Integer cont = (Integer) getServletContext().getAttribute("cont");
        if(cont == null) cont = new Integer(0);
        cont++;
        getServletContext().setAttribute("cont", cont);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Discussão</title>");            
            out.println("</head>");
            out.println("<body style = \"background-color:" + this.backgroundcolor + "\">");
            out.println("<h1 style = \"color:"+ this.h1color +"\">Discussão</h1>");
            out.println("<h1>Recuperado da sessão</h1>");
            out.println("<h3>Email: "+ session.getAttribute("email")+"</h3>");
            out.println("<h3>Mensagem: "+ session.getAttribute("mensagem") + "</h3>");
            for(Mensagem msg: msgs.Mensagens){
                out.println("<h3 style = \"color:"+this.h3color+"\"> Mensagem: " + msg.getMensagem() +" </h3>");
                out.println("<h3 style = \"color:"+this.h3color+"\"> Email: " + msg.getEmail() + "</h3>");
                out.println("</br>");
            }
            out.println("<h2 style = \"color:" +this.h2color + "\"> Numero de acessos = " + cont+ "</h2>");
            out.println("<form action=\"..\">"
                        + "<input type=\"submit\" value=\"Nova Mensagem\">"
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
