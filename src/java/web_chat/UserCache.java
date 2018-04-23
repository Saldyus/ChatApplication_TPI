/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_chat;

import javax.validation.constraints.Size;

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
public class UserCache {
    
    @Size(max = 20)
    private String username;
    
    private String password;
    
    private String nome_v;
    
    public UserCache(String username, String password, String nome_v){
        this.username=username;
        this.password=password;
        this.nome_v=nome_v;
    }
    
    /**
     * 
     * @return The User's username
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * 
     * @return The User's password
     */
    public String getPassword(){
        return password;
    }
    
    /**
     * 
     * @return The name the can be visualized by other people
     */
    public String getNomeV(){
        return nome_v;
    }
    
    /**
     * Clear all the variables
     */
    void clear() {
        this.nome_v="";
        this.password="";
        this.username="";
    }
}
