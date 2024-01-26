/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import persistencia.UsuarioDAO;
import utilidades.BancoDeDadosException;
import utilidades.PersonalizarMsgErro;

/**
 *
 * @author sala304b
 */
@WebServlet(name = "CadastrarUsuarioServlet", urlPatterns = {"/CadastrarUsuario"})
public class CadastrarUsuarioServlet extends HttpServlet {

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
        String login = request.getParameter("txtLogin");
        String nome = request.getParameter("txtNome");
        String senha = request.getParameter("txtSenha");
        String perfil = request.getParameter("Perfil");
        String status = request.getParameter("Status");
        String msgErro = "";
        
        
        if(login.trim().length() >= 5){
            
        if(senha.trim().length() != 6){
            
            msgErro = "A senha tem que ser igual a 6 caracters.";
            
        }else{
            
            if(senha.contains(" ")){
                
                msgErro = "A senha não pode ter espaço."; 
            }else{
            
            if(login.contains(" ")){
                
                msgErro = "O login não pode ter espaço.";
                
            }else{
            
            if(senha.equals(nome) || senha.equals(login)){
                
                 msgErro = "A senha não pode ser igual ao login nem igual ao nome.";
                 
            }else{
        
        if(login != null && senha !=null){
            String senhaCriptografada = DigestUtils.sha512Hex(senha);
            
            Usuario usuario = new Usuario();
            usuario.setLogin(login);
            usuario.setNome(nome);
            usuario.setSenha(senhaCriptografada);
            usuario.setPerfil(perfil);
            usuario.setStatus(status);
             
            try{
            UsuarioDAO.inserir(usuario);
            
            }catch (Exception ex){
            request.setAttribute("msgErro", "Ocorreu um erro ao salvar o usuário: " + PersonalizarMsgErro.getMensagem(ex.getMessage()));
            RequestDispatcher rd = request.getRequestDispatcher("CadastroUsuario.jsp");
            rd.forward(request, response);
            
            request.setAttribute("usuario", usuario);
            
            //throw new ServletException(ex);
            }
            
                // Redireciona para uma pagina logada
                response.sendRedirect("PainelUsuario.jsp");
                
                return;  
        }
        }
        }
        }
        }
        }else{
            
             msgErro = "O login não pode ter menos que 5 caracteres.";
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
            out.println("<h3>"+ msgErro +"</h3>");
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
