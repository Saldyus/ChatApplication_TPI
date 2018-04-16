/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import database.Messaggi;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
public class DatabaseManagerMessaggi {

    private static Connection connection;

    public static DatabaseManagerMessaggi getInstance() {
        return instance;
    }
    private Properties properties;
    private static DatabaseManagerMessaggi instance;
    private List<Messaggi> messaggi;

    public DatabaseManagerMessaggi() {
        try {
            // carica le preferenze dell'app dal file app.properties
            properties = new Properties();
            properties.load(new FileInputStream("app.properties"));

            // Se presente carica il driver JDBC
            if (properties.getProperty("JDBC_DRIVER_CLASS") != null) {
                try {
                    Class.forName(properties.getProperty("JDBC_DRIVER_CLASS"));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            createConnection();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }
        messaggi = new ArrayList<>();
    }

    public static Connection getConnection() {
        try {
            if (instance == null) {
                instance = new DatabaseManagerMessaggi();
            }

            if (instance.connection.isValid(1) == false) {
                instance.createConnection();
            }

            return instance.connection;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(properties.getProperty("JDBC_DB_URL"));
    }

    public void add(String mitt, String dest, String type, String text) {
        List<Messaggi> messaggio = getMessaggi();
        System.out.println(messaggio.size());
        int newId = 0;
        for (Messaggi ms : messaggio) {
            if (ms.getID_M() > newId) {
                newId = ms.getID_M();
            }
        }
        newId += 1;
        messaggio.clear();

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        Date data = new Date(currentDate.getTime());
        
        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO messaggi (ID_M, mitt, dest, type_m, text_m, data_m) VALUES (?, ?, ?, ? , ?, ?)");
            s.setInt(1, newId);
            s.setString(2, mitt);
            s.setString(3, dest);
            s.setString(4, type);
            s.setString(5, text);
            s.setDate(6, data);
            s.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int size() {
        return messaggi.size();
    }

    public void clear() {
        messaggi.clear();
    }

    public List<Messaggi> getMessaggi() {
        messaggi.clear();
        try {
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM messaggi");
            while (r.next()) {
                int ID = r.getInt("ID_M");
                String mitt = r.getString("mitt");
                String dest = r.getString("dest");
                String type = r.getString("type_m");
                String text = r.getString("text_m");
                Date data = r.getDate("data_m");
                Messaggi Messaggi = new Messaggi(ID, mitt, dest, type, text, data);
                messaggi.add(Messaggi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messaggi;
    }

    public List<Messaggi> getMessageDM(String dest, String mitt) {
        this.messaggi.clear();
        PreparedStatement s;
        Messaggi ms;
        List<Messaggi> ret = new ArrayList<>();
        try {
            s = connection.prepareStatement("SELECT type_m, text_m FROM messaggi WHERE (dest = ? and mitt = ?) OR (dest = ? and mitt = ?)");
            s.setString(1, dest);
            s.setString(2, mitt);
            s.setString(3, mitt);
            s.setString(4, dest);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                String type = r.getString("type_m");
                String text = r.getString("text_m");
                ms = new Messaggi();
                ms.setMitt(mitt);
                ms.setDest(dest);
                ms.setText(text);
                ms.setType(type);
                messaggi.add(ms);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messaggi;
    }

    public void remove(int ID_G) {
        try {
            //List<LogMessage> t = getByAppId(appId);
            //logMessage.remove(t);
            Statement st = connection.createStatement();
            StringBuilder delete = new StringBuilder();
            delete.append("DELETE FROM messaggi WHERE ID_M = ");
            delete.append(ID_G);
            st.executeUpdate(delete.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
