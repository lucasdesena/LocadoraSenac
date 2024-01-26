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
        <title>Cadastro de Gênero</title>
    </head>
    <body>
        <h1>Cadastro de Gênero</h1>
        <hr>
        
        <div class="erro">${msgErro}</div>
        <!-- Method = post os dados não vao ser enviados via URL
            e sim dentro do corpo da requisicao -->
        <form action="CadastrarGenero" method="post">
            <table>
                <tr>
                    <th>Nome:</th>
                    <th><input type="text" name="txtNome" maxlength="30"></th>
                </tr>
                <tr>
                    <th>Descrição:</th>                
                    <th><input type="text" name="txtDescricao"></th>
                </tr>
            </table>
            <br><input type="submit" value="Enviar"><br><br>
            <a href="javascript:history.back()">Voltar</a><br>
                      
        </form>
    </body>
</html>