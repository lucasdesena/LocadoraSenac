<%-- 
    Document   : Login
    Created on : 13/04/2017, 10:48:45
    Author     : sala304b
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@page import="modelo.Genero"%>
<jsp:useBean class="persistencia.GeneroDAO"
             id="dao"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Cadastro de Filme</h1>
        <hr><br>
        
        <div class="erro">${msgErro}</div>
        <!-- Method = post os dados não vao ser enviados via URL
            e sim dentro do corpo da requisicao -->
        <form action="CadastrarFilme" method="post">
            
            <b>Titulo:</b><br> <input type="text" name="txtTitulo" maxlength="30"><br><br>
            
            <b>Generos:<br></b>
            
            <select name="CodGenero">
                
                <option value="selecionar">Selecione um genero</option>
                
                <c:forEach items="${dao.listar()}" var="genero">
                    <option value="${genero.codigo}">${genero.nome}</option>
            </c:forEach>
                
            </select><br><br>
            
            <b>Sinopse:</b><br> <textarea rows="10" cols="60" name="txtSinopse"></textarea><br><br>
            <b>Diretor:</b><br><input type="text" name="txtDiretor" maxlength="30"><br><br>
            <b>Ano de Lançamento:</b><br><input type="number" name="txtAnoLancamento" min="1985" max="2017"><br><br>
            
            <b>Status:</b><br>
            
            <select name="Status">
                
            <option value="ativo"> ATIVO </option>
            <option value="inativo"> INATIVO </option>
            
            </select><br><br>
            <input type="submit" value="Salvar"><br><br>

            <hr><br>
            <a href="javascript:history.back()">Voltar</a><br>            
            
        </form>
    </body>
</html>
