/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import database.Users;
import java.applet.Applet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
public class DatabaseManagerUsers extends JApplet{
    
    private static Connection connection;

    public static DatabaseManagerUsers getInstance() {
        return instance;
    }
    private Properties properties;
    private static DatabaseManagerUsers instance;
    private List<Users> users;

    public DatabaseManagerUsers() {
        try {
            // carica le preferenze dell'app dal file app.properties
            properties = new Properties();
            properties.load(new FileInputStream("app.properties"));

            // Se presente carica il driver JDBC
            if (properties.getProperty("JDBC_DRIVER_CLASS") != null) {
                try {
                    Class.forName(properties.getProperty("JDBC_DRIVER_CLASS"));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            createConnection();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        users = new ArrayList<>();
    }

    public static Connection getConnection() {
        try {
            if (instance == null) {
                instance = new DatabaseManagerUsers();
            }

            if (instance.connection.isValid(1) == false) {
                instance.createConnection();
            }

            return instance.connection;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(properties.getProperty("JDBC_DB_URL"));
    }

    public void add(String username, String password, String nome_v) {
        try {
            List<Users> user = getUsers();
            System.out.println(user.size());
            for (Users us : user) {
                if (us.getUsername().equals(username)) {
                    String exist = "Utente gia inserito";
                }
            }
            user.clear();
            
            try {
                PreparedStatement s;
                s = connection.prepareStatement("INSERT INTO users (Username, Password_c, nome_v) VALUES (?, ?, ?)");
                s.setString(1, username);
                s.setString(2, hashCode(password));
                s.setString(3, nome_v);
                s.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int sizeU() {
        return users.size();
    }

    public void clear() {
        users.clear();
    }

    public List<Users> getUsers() {
        try {
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM Users");
            while (r.next()) {
                String Username = r.getString("Username");
                String Password_c = r.getString("Password_c");
                String nome_v = r.getString("nome_v");
                Users Users = new Users(Username, Password_c, nome_v);
                users.add(Users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public List<Users> getByUsername(String username) throws NoSuchAlgorithmException {
        PreparedStatement s;
        List<Users> ret = new ArrayList<>();
        Users us;
        try {
            s = connection.prepareStatement("SELECT * FROM Users WHERE username = ? ");
            s.setString(1, username);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                String Username = r.getString("Username");
                String Password_c = r.getString("Password_c");
                String nome_v = r.getString("nome_v");
                us = new Users(Username,Password_c, nome_v);
                users.add(us);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public List<Users> getAllUsername(){
        try {
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("SELECT Username FROM Users");
            while (r.next()) {
                String Username = r.getString("Username");
                Users Users = new Users();
                Users.setUsername(Username);
                users.add(Users);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public boolean isUsers(String username, String password) throws NoSuchAlgorithmException{
        List<Users> user = getUsers();
        for(Users us : user){
            if(us.getUsername().equals(username) && us.getPassword_c().equals(hashCode(password))){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    public void remove(String username) {
        try {
            //List<LogMessage> t = getByAppId(appId);
            //logMessage.remove(t);
            Statement st = connection.createStatement();
            StringBuilder delete = new StringBuilder();
            delete.append("DELETE FROM users WHERE username = ");
            delete.append(username);
            st.executeUpdate(delete.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String hashCode(String chiaro) throws NoSuchAlgorithmException{
        String hash;        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(chiaro.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        hash=sb.toString();        
        return hash;
    }
    
}