/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.Size;

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
public class Messaggigruppo implements Serializable{
    
    private int ID_M;
    
    @Size(max = 20)
    private String mitt;
    
    @Size(max = 10)
    private String type_m;
    
    private String text_m;
    
    private int ID_G;
    
    private Date data_m;
    
    /**
     * 
     * @param ID_M The ID of the message 
     * @param mitt The sender's username of the message
     * @param type_m The type of the message
     * @param text_m The text of the message
     * @param ID_G The Id of the group
     */
    public Messaggigruppo(int ID_M, String mitt, String type_m, String text_m, int ID_G, Date data_m){
        this.ID_M=ID_M;
        this.mitt=mitt;
        this.type_m=type_m;
        this.text_m=text_m;
        this.ID_G=ID_G;
        this.data_m=data_m;
    }
    
    /**
     * The blank constructor
     */
    public Messaggigruppo(){
        
    }
    
    /**
     * 
     * @return ID of the message
     */
    public int getID_M(){
        return ID_M;
    }
    
    /**
     * 
     * @param ID_M set the ID of the Message
     */
    public void setID_M(int ID_M){
        this.ID_M=ID_M;
    }
    
    /**
     * 
     * @return the sender's username of the message
     */
    public String getMitt(){
        return mitt;
    }
    
    /**
     * 
     * @param mitt The sender's usrname of the message
     */
    public void setMitt(String mitt){
        this.mitt=mitt;
    }
    
    /**
     * 
     * @return the type of the message(text or image)
     */
    public String getType(){
        return type_m;
    }
    
    /**
     * 
     * @param type The type of the message
     */
    public void setType(String type){
        type_m=type;
    }
    /**
     * 
     * @return the text of the message
     */
    public String getText(){
        return text_m;
    }
    
    /**
     * 
     * @param text The text of the message
     */
    public void setText(String text){
        text_m=text;
    }
    
    /**
     * 
     * @return the ID of the group
     */
    public int getID_G(){
        return ID_G;
    }
    
    /**
     * 
     * @param ID_G Set the ID of the group
     */
    public void setID_G(int ID_G){
        this.ID_G=ID_G;
    }
    
    /**
     * 
     * @return The day of the message
     */
    public Date getDayM(){
        return data_m;
    }
    
    /**
     * 
     * @param data The day of the message
     */
    public void setDayM(Date data){
        data_m=data;
    }
    
}
