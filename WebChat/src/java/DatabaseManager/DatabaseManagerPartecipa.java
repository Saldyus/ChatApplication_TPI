/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import database.Partecipa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

/**
 *
 * @author Dinaro Salvatore
 * @version 1.0
 */
public class DatabaseManagerPartecipa {

    private static Connection connection;

    public static DatabaseManagerPartecipa getInstance() {
        return instance;
    }
    private Properties properties;
    private static DatabaseManagerPartecipa instance;
    private List<Partecipa> partecipa;

    public DatabaseManagerPartecipa() {
        try {
            // carica le preferenze dell'app dal file app.properties
            properties = new Properties();
            properties.load(new FileInputStream("app.properties"));

            // Se presente carica il driver JDBC
            if (properties.getProperty("JDBC_DRIVER_CLASS") != null) {
                try {
                    Class.forName(properties.getProperty("JDBC_DRIVER_CLASS"));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            createConnection();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        }
        partecipa = new ArrayList<>();
    }

    public static Connection getConnection() {
        try {
            if (instance == null) {
                instance = new DatabaseManagerPartecipa();
            }

            if (instance.connection.isValid(1) == false) {
                instance.createConnection();
            }

            return instance.connection;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(properties.getProperty("JDBC_DB_URL"));
    }

    public void add(String username, int ID_G) {
        this.partecipa.clear();
        List<Partecipa> partecipa = getPartecipa();

        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO partecipa (Username, ID_G) VALUES (?, ?)");
            s.setString(1, username);
            s.setInt(2, ID_G);
            s.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int size() {
        return partecipa.size();
    }

    public void clear() {
        partecipa.clear();
    }

    public List<Partecipa> getPartecipa() {

        try {
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM partecipa");
            while (r.next()) {
                String username = r.getString("Username");
                int ID = r.getInt("ID_G");
                Partecipa Partecipa = new Partecipa(username, ID);
                partecipa.add(Partecipa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return partecipa;
    }

    public List<Partecipa> getByGroupId(int ID_G) {
        this.partecipa.clear();
        PreparedStatement s;
        List<Partecipa> ret = new ArrayList<>();
        Partecipa gr;
        try {
            s = connection.prepareStatement("SELECT * FROM partecipa WHERE ID_G = ?");
            s.setInt(1, ID_G);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                String name = r.getString("Username");
                int id = r.getInt("ID_G");
                gr = new Partecipa(name, id);
                partecipa.add(gr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partecipa;
    }

    public List<Partecipa> getByUsername(String username) {
        this.partecipa.clear();
        PreparedStatement s;
        Partecipa gr;
        List<Partecipa> ret = new ArrayList<>();
        try {
            s = connection.prepareStatement("SELECT * FROM partecipa WHERE username = ?");
            s.setString(1, username);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                String name = r.getString("username");
                int id = r.getInt("ID_G");
                gr = new Partecipa(name, id);
                partecipa.add(gr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        }

        return partecipa;
    }

    public void remove(int ID_G) {
        try {
            //List<LogMessage> t = getByAppId(appId);
            //logMessage.remove(t);
            Statement st = connection.createStatement();
            StringBuilder delete = new StringBuilder();
            delete.append("DELETE FROM partecipa WHERE ID_G = ");
            delete.append(ID_G);
            st.executeUpdate(delete.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerPartecipa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
