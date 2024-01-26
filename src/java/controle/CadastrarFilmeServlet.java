/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Filme;
import modelo.Genero;
import modelo.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import persistencia.FilmeDAO;
import persistencia.UsuarioDAO;
import utilidades.BancoDeDadosException;
import utilidades.PersonalizarMsgErro2;

/**
 *
 * @author sala304b
 */
@WebServlet(name = "CadastrarFilmeServlet", urlPatterns = {"/CadastrarFilme"})
public class CadastrarFilmeServlet extends HttpServlet {

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
        
        String titulo = request.getParameter("txtTitulo");
        
        String codGeneroAux = request.getParameter("CodGenero");
       
        
        String sinopse = request.getParameter("txtSinopse");
        String diretor = request.getParameter("txtDiretor");
        
        String anoLancamentoAux = request.getParameter("txtAnoLancamento");
        
        
        String status = request.getParameter("Status");
        
        String msgErro = "";
        
        String auxiliar = diretor.replace(" ", "");
        
        if(codGeneroAux.equals("selecionar")){
            
            msgErro = "Não pode cadastrar um filme sem genero.";
        }else{
        
        if(titulo.trim().length() < 3){
           
           msgErro = "O titulo não pode ter menos que 3 caracteres.";
           
       }else{
        
        if(sinopse.trim().equals("")){
            
             msgErro = "O Campo sinopse não pode estar vazio.";
            
        }else{
            
           if(auxiliar.length() < 5){
            
            msgErro = "O Diretor não pode ter menos que 5 caracteres.";
            
        }else{
                
        
        if(anoLancamentoAux.trim().equals("")){
            
            msgErro = "O Campo Ano de Lançamento não pode estar vazio.";
            
        }else{
        
        int anoLancamento = Integer.parseInt(anoLancamentoAux);
        
        if(anoLancamento < 1895){
                
                msgErro = "Ano de lançamento somente a partir de 1895.";
                
            }else{
            
             int codGenero = Integer.parseInt(codGeneroAux);
            
        if(titulo != null){
            
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioAutenticado");
            
            Filme filme = new Filme();
            filme.setTitulo(titulo);
            filme.setGenero(new Genero(codGenero));
            filme.setSinopse(sinopse);
            filme.setDiretor(diretor);
            filme.setAno_lancamento(anoLancamento);
            filme.setStatus(status);
            filme.setUsuario_cadastro(usuario);
            filme.setDatahora_cadastro(new Date());
            
            
             
            try{
            FilmeDAO.inserir(filme);
            
            }catch (Exception ex){
            request.setAttribute("msgErro", "Ocorreu um erro ao salvar o filme: " + PersonalizarMsgErro2.getMensagem(ex.getMessage()));
            RequestDispatcher rd = request.getRequestDispatcher("CadastroFilme.jsp");
            rd.forward(request, response);
            
            request.setAttribute("usuario", filme);
            
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

