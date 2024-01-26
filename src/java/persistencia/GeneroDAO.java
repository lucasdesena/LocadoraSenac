/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

// Essa classe utiliza o padrao de projetos DAO (Data Access Object)

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Genero;




public class GeneroDAO {
    
    public static void inserir(Genero g) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement  preparedStatement = null;
        ResultSet rs = null;
        String SQL = "";
        
        // Obtem conexao com BD
        conn = ConexaoFactory.getConexao();
        
        // Comando SQL 
        SQL = "INSERT INTO generos (nome, descricao) " +
                                     "VALUES (?, ?)";

        
        preparedStatement = conn.prepareStatement(SQL);

        preparedStatement.setString(1, g.getNome());
        preparedStatement.setString(2, g.getDescricao());
        
        preparedStatement.executeUpdate(); 

        // Fechar conexao
        conn.close();
        
    }
    
 /*public static boolean buscar(Usuario u) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement  preparedStatement = null;
        ResultSet rs = null;
        String SQL = "";
        
        // Obtem conexao com BD
        conn = ConexaoFactory.getConexao();
        
        // Comando SQL 
        SQL = "SELECT * FROM usuarios " +
                " WHERE login = ? AND senha = ? ";

        preparedStatement = conn.prepareStatement(SQL);

        preparedStatement.setString(1, u.getLogin());
        preparedStatement.setString(2, u.getSenha());
                
        // Para buscar informações
        rs = preparedStatement.executeQuery();   

        // Verifica se possui dados
        if (rs.next()) {
            conn.close();
            return true;
         }else{
            conn.close();
            return false;
        } 
    }*/
 
    
    public static ArrayList listar() throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement  preparedStatement = null;
        ResultSet rs = null;
        String SQL = "";
        ArrayList<Genero> lista = new ArrayList();
        
        // Obtem conexao com BD
        conn = ConexaoFactory.getConexao();
        
        // Comando SQL 
        SQL = "SELECT * FROM generos " ;

        preparedStatement = conn.prepareStatement(SQL);

        // Para buscar informações
        rs = preparedStatement.executeQuery();   

        // Verifica se possui dados
        while (rs.next()) {
            
            Genero g = new Genero();
            
            g.setCodigo(rs.getInt("codigo"));
            g.setNome(rs.getString("nome"));
            g.setDescricao(rs.getString("descricao"));
            
            lista.add(g);
         } 
        
        // Fechar conexao
        conn.close();
        
        return lista;
    }
    
    
    /*
    public static void atualizar(Funcionario f) throws SQLException, ClassNotFoundException{
        
        Connection conn = null;
        PreparedStatement  preparedStatement = null;
        ResultSet rs = null;
        String SQL = "";
        
        // Obtem conexao com BD
        conn = ConexaoFactory.getConexao();
        
        // Comando SQL 
        SQL = "UPDATE tb_funcionario " +
              " SET nome = ?, " +
              " salario = ?, " +
              " dataAdmissao = ?, " +
              " dataDemissao = ? " +
              " WHERE idFuncionario = ? ";

        preparedStatement = conn.prepareStatement(SQL);

        preparedStatement.setString(1, f.getNome());
        preparedStatement.setFloat(2, f.getSalario());
        
        // Nao existe tipo de dado java.util.Date no 
        //JDBC entao temos que fazer uma conversao
        Timestamp dataAux = new Timestamp(
                f.getDataAdmissao().getTime());
        preparedStatement.setTimestamp(3, dataAux);  
        
        if(f.getDataDemissao() == null)
            preparedStatement.setTimestamp(4, null);  
        else{
            dataAux = new Timestamp(
                    f.getDataDemissao().getTime());
            preparedStatement.setTimestamp(4, dataAux);  
        }
        
        preparedStatement.setInt(5, f.getIdFuncionario());
        
        int qtdLinhas = preparedStatement.executeUpdate();  

        if (qtdLinhas == 0) {
            // O certo seria criar uma classe herdada de Exception
             throw new SQLException("Não existe linhas atualizadas");
         } 
        
        // Fechar conexao
        conn.close();
        
    }
*/
    
    /*
    
    public static void excluir(Funcionario f) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        PreparedStatement  preparedStatement = null;
        ResultSet rs = null;
        String SQL = "";
        
        // Obtem conexao com BD
        conn = ConexaoFactory.getConexao();
        
        // Comando SQL 
        SQL = "DELETE FROM tb_funcionario " +
              " WHERE idFuncionario = ? ";

        preparedStatement = conn.prepareStatement(SQL);

        preparedStatement.setInt(1, f.getIdFuncionario());
        
        int qtdLinhas = preparedStatement.executeUpdate();  

        if (qtdLinhas == 0) {
            // O certo seria criar uma classe herdada de Exception
             throw new SQLException("Não existe linha para ser excluído");
         } 
        
        // Fechar conexao
        conn.close();
    }
    */
}
