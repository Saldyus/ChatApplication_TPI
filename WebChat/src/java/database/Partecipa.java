/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
public class Partecipa implements Serializable{
    
    @Size(max = 20)
    private String Username;
    
    private int ID_G;
    
    /**
     * 
     * @param Username The Username who partecipate the group
     * @param ID_G The Id of the group
     */
    public Partecipa(String Username, int ID_G){
        this.Username=Username;
        this.ID_G=ID_G;
    }
    
    /**
     * The blank constructor
     */
    public Partecipa(){
        
    }
    
    /**
     * 
     * @return Username of the person
     */
    public String getUsername(){
        return Username;
    }
    
    /**
     * 
     * @param Username The Username of the person
     */
    public void setUsername(String Username){
        this.Username=Username;
    }
    
    /**
     * 
     * @return ID of the group 
     */
    public int ID_G(){
        return ID_G;
    }
    
    /**
     * 
     * @param ID_G The ID of the group
     */
    public void setID_G(int ID_G){
        this.ID_G=ID_G;
    }
    
}
