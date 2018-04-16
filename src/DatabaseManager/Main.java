/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Dinaro Salvatore
 */
public class Main {
    
    public static void main(String[] args) throws NoSuchAlgorithmException{
        DatabaseManagerUsers user = new DatabaseManagerUsers();
        user.add("Saldyus", "password", "Salvatore");
        System.out.println(user.getByUsername("Saldyus").get(0).getUsername());
    }
    
}
