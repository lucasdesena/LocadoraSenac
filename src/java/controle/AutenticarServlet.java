/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import persistencia.UsuarioDAO;
import utilidades.PersonalizarMsgErro;

/**
 *
 * @author sala304b
 */
@WebServlet(name = "AutenticarServlet", urlPatterns = {"/Autenticar"})
public class AutenticarServlet extends HttpServlet {

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
        
        String nome = request.getParameter("txtNome");
        String senha = request.getParameter("txtSenha");
        
        String msgErro = "";
        
        if(nome != null && senha !=null){
            String senhaCriptografada = DigestUtils.sha512Hex(senha);
            Usuario usuario = new Usuario();
            usuario.setLogin(nome);
            usuario.setSenha(senhaCriptografada);                        
                        
            try {
                //chamando o método buscar para verificar 
                //se o usuário existe no banco de dados
                Usuario autenticado = UsuarioDAO.buscar(usuario);

                
                
                
                if(autenticado != null ){
                    
                    if(autenticado.getStatus().equals("ativo")){
                    // Informo ao servidor qual usuario autenticado
                    HttpSession session = request.getSession(true);
                    session.setAttribute("usuarioAutenticado", autenticado);

                    // Redireciona para uma pagina logada
                    response.sendRedirect("PainelUsuario.jsp");

                    return;

                }else{
            
                  msgErro = "Usuario Inativo não pode logar";
                  
                    }
                    }else{
                            msgErro = "Login ou senha Incorretos!";
            
             }
                
                
            } catch (Exception ex) {
                throw new ServletException(ex);
            } 
        }
            
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AutenticarServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Autenticação</h1>");
            out.println("<hr>");
            out.println("<a href=\"javascript:history.back()\">Voltar</a><br>");
            out.println("<h3>" + msgErro + "</h3>");
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
