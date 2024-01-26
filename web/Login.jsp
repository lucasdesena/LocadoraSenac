<%-- 
    Document   : Login
    Created on : 13/04/2017, 10:48:45
    Author     : sala304b
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Bem vindo</h1>
        <hr><br>
        
        <!-- Method = post os dados não vao ser enviados via URL
            e sim dentro do corpo da requisicao -->
        <form action="Autenticar" method="post">
            Nome: <input type="text" name="txtNome"><br><br>
            Senha: <input type="password" name="txtSenha"><br><br>
            <input type="submit" value="Logar"><br><br>   
            
            <a href="javascript:history.back()">Voltar</a><br>
        </form><br><br>
    </body>
</html>
