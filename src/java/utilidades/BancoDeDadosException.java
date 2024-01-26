/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author sala304b
 */
public class BancoDeDadosException extends Exception{
    
    String msg = "";

    public BancoDeDadosException() {
        
        super();
    }
    
    
@Override
    public String getMessage(){
        
        if(super.getMessage().contains("Duplicate entry")){
            
            return "Já existe um usuario cadastrado com esse nome.";
          
        }else{
            return super.getMessage();
            
        }
        
    }
}
