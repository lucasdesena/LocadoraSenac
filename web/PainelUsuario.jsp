<%-- 
    Document   : PainelUsuario
    Created on : 13/04/2017, 10:52:41
    Author     : sala304b
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Painel Locadora</title>
    </head>
    <body>
        <h1>Painel Locadora</h1>
        <hr>
        <ul>
            <c:if test="${usuarioAutenticado.perfil == 'adm'}">
                
            <li><a href="CadastroUsuario.jsp">Cadastrar Usuário</a></li>
            
            </c:if>
            
            <li><a href="CadastroFilme.jsp">Cadastrar Filme</a></li>
            <li><a href="CadastroGenero.jsp">Cadastrar Gênero</a></li>
            
        </ul>
        <a href="javascript:history.back()">Voltar</a><br>
        <a href="Logout">Logout</a><br>
    </body>
</html>
    