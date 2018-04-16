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
public class Messaggi implements Serializable{
    
    private int ID_M;
    
    @Size(max = 20)
    private String mitt;
    
    @Size(max = 20)
    private String dest;
    
    @Size(max = 10)
    private String type_m;
    
    private String text_m;
    
    private Date data_m;
    
    /**
     * 
     * @param ID_M The ID of the Message
     * @param mitt The sender's username of the message
     * @param dest The recipient's username of the message
     * @param type_m The type of the message
     * @param text_m The text of the message
     */
    public Messaggi(int ID_M, String mitt, String dest, String type_m, String text_m, Date data_m){
        this.ID_M=ID_M;
        this.mitt=mitt;
        this.dest=dest;
        this.type_m=type_m;
        this.text_m=text_m;
        this.data_m=data_m;
    }
        
    /**
     * The blank constructor
     */
    public Messaggi(){
        
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
     * @param mitt The sender's username of the message
     */
    public void setMitt(String mitt){
        this.mitt=mitt;
    }
    
    /**
     * 
     * @return the recipient's username of the message
     */
    public String getDest(){
        return dest;
    }
    
    /**
     * 
     * @param dest the recipient's username of the message
     */
    public void setDest(String dest){
        this.dest=dest;
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
