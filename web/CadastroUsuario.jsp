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
        <title>Cadastro Usuario</title>
    </head>
    <body>
        <h1>Cadastro de Usuário</h1>
        <hr><br>
        
        <div class="erro">${msgErro}</div>
        <!-- Method = post os dados não vao ser enviados via URL
            e sim dentro do corpo da requisicao -->
        <form action="CadastrarUsuario" method="post">
            Login:<br> <input type="text" name="txtLogin" value="${usuario.login}"><br>
            Nome:<br> <input type="text" name="txtNome"><br>
            Senha:<br> <input type="password" name="txtSenha"><br><br>
            Perfil:<br>
            <select name="Perfil">
                
            <option value="func"> Funcionario </option>
            <option value="adm"> Administrador </option>
            
            </select><br><br>
            
            Status:<br>
            
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
