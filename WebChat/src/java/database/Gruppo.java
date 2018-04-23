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

public class Gruppo implements Serializable{
    
    private int idG;
    
    @Size(max = 50)
    private String nameG;
    
    /**
     * 
     * @param idG the Id of the group
     * @param nameG the name of the group
     */
    public Gruppo(int idG, String nameG){
        this.idG=idG;
        this.nameG=nameG;
    }
    
    /**
     * blank constructor
     */
    public Gruppo(){
        
    }
    
    /**
     * 
     * @return the ID of the group
     */
    public int getIdG(){
        return idG;
    }
    
    /**
     * 
     * @param idG The ID of the group
     */
    public void setIdG(int idG){
        this.idG=idG;
    }
    
    /**
     * 
     * @return the name of the group
     */
    public String getNameG(){
        return nameG;
    }
    
    /**
     * 
     * @param nameG The name of the group
     */
    public void setNameG(String nameG){
        this.nameG=nameG;
    }
    
}
