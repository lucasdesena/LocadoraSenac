/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import modelo.Filme;
import modelo.Usuario;

/**
 *
 * @author Administrador
 */
public class FilmeDAO {
    
    public static void inserir(Filme f) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement  preparedStatement = null;
        String SQL = "";
        
        // Obtem conexao com BD
        conn = ConexaoFactory.getConexao();
        
        // Comando SQL 
        SQL = "INSERT INTO filmes (titulo, cod_genero, sinopse, diretor, ano_lancamento, status, usuario_cadastro, datahora_cadastro) " +
                                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        preparedStatement = conn.prepareStatement(SQL);

        preparedStatement.setString(1, f.getTitulo());
        preparedStatement.setInt(2, f.getGenero().getCodigo());
        preparedStatement.setString(3, f.getSinopse());
        preparedStatement.setString(4, f.getDiretor());        
        preparedStatement.setInt(5, f.getAno_lancamento());
        preparedStatement.setString(6, f.getStatus());
        preparedStatement.setString(7, f.getUsuario_cadastro().getLogin());
        
        Timestamp dataAux = new Timestamp(
                f.getDatahora_cadastro().getTime());
        preparedStatement.setTimestamp(8, dataAux);
        
        
        // Executa no BD        
        preparedStatement.executeUpdate();  
        
        // Fechar conexao
        conn.close();
        
    }    
    
   /* public static boolean buscar(Usuario u) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement  preparedStatement = null;
        ResultSet rs = null;
        String SQL = "";
        boolean existe = false;
        
        // Obtem conexao com BD
        conn = ConexaoFactory.getConexao();
        
        // Comando SQL 
        SQL = "SELECT * FROM usuarios " +
                " WHERE login = ? AND senha = ?";

        preparedStatement = conn.prepareStatement(SQL);

        preparedStatement.setString(1, u.getLogin());
        preparedStatement.setString(2, u.getSenha());
        
                
        // Para buscar informações
        rs = preparedStatement.executeQuery();   

        // Verifica se possui dados
        if (rs.next()) {
            existe = true;            
         } 
        
        // Fechar conexao
        conn.close();
        
        return existe;
    }   */ 
}
