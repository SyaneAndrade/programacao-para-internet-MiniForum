/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Syane
 */
public class Mensagem {
    String mensagem;
    String email;
    
    public void setMensagem(String mens){
        
        this.mensagem = mens;
    }
    
    public String getMensagem(){
        
        return this.mensagem;
    }
    
    public void setEmail(String em){
        
        this.email = em;
    }
    
    public String getEmail(){
        
        return this.email;
    }
}
