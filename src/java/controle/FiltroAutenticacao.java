/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author sala304b
 */

@WebFilter(filterName = "FiltroAutenticacao", urlPatterns = {"/PainelUsuario.jsp","/CadastroFilme.jsp","/CadastroGenero.jsp","/CadastroUsuario.jsp"})
public class FiltroAutenticacao implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //converte o ServletRequest em HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) request;
        //converte o ServletResponse em HttpServletResponse
        HttpServletResponse res = (HttpServletResponse) response;
        
        //Pega o parametro usuarioAutenticado da sessão
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioAutenticado");
        
        //Se o usuário não está logado redireciona para a página de login
        if(usuario == null){
            res.sendRedirect("Login.jsp");
        }else{
            //passa pelo filtro e abre a url requisistada
            chain.doFilter(request, response);
        }
        
    }

    @Override
    public void destroy() {
        
    }
    
}
