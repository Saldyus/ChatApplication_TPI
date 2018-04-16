/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.validation.constraints.Size;

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
public class Users implements Serializable{

    @Size(max = 20)
    private String Username;

    @Size(max = 64)
    private String Password_c;

    @Size(max = 20)
    private String Nome_v;

    /**
     * 
     * @param Username The username of the person
     * @param Password_c The crypted password of the person
     * @param username_c The crypted username of the person
     * @param nome_v The visualazed name of the person
     */
    public Users(String Username, String Password_c, String nome_v) {
        this.Username = Username;
        this.Password_c = Password_c;
        this.Nome_v = nome_v;
    }

    /**
     * The blank constructor
     */
    public Users(){
        
    }
    
    /**
     *
     * @return the Username of the person
     */
    public String getUsername() {
        return Username;
    }

    /**
     *
     * @param Username The Username of the person
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     *
     * @return the Password crypted of the person
     */
    public String getPassword_c() {
        return Password_c;
    }

    /**
     *
     * @param Password The Password that the person insered
     * @throws java.security.NoSuchAlgorithmException
     */
    public void setPassword_c(String Password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(Password.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        this.Password_c = sb.toString();
    }

    /**
     *
     * @return the Name visulazed by other people
     */
    public String getNome_v() {
        return Nome_v;
    }

    /**
     *
     * @param Nome_v The Name that is visualized by other people
     */
    public void setNome_v(String Nome_v) {
        this.Nome_v = Nome_v;
    }
}
