/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import database.Gruppo;
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
public class DatabaseManagerGruppo {

    static Connection connection;

    public static DatabaseManagerGruppo getInstance() {
        return instance;
    }
    private Properties properties;
    private static DatabaseManagerGruppo instance;
    private List<Gruppo> gruppo;

    public DatabaseManagerGruppo() {
        try {
            // carica le preferenze dell'app dal file app.properties
            properties = new Properties();
            properties.load(new FileInputStream("app.properties"));

            // Se presente carica il driver JDBC
            if (properties.getProperty("JDBC_DRIVER_CLASS") != null) {
                try {
                    Class.forName(properties.getProperty("JDBC_DRIVER_CLASS"));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            createConnection();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
        gruppo = new ArrayList<>();
    }

    public static Connection getConnection() {
        try {
            if (instance == null) {
                instance = new DatabaseManagerGruppo();
            }

            if (instance.connection.isValid(1) == false) {
                instance.createConnection();
            }

            return instance.connection;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(properties.getProperty("JDBC_DB_URL"));
    }

    public void add(String name) {
        List<Gruppo> gruppo = getGruppi();
        System.out.println(gruppo.size());
        int newId = 0;
        for (Gruppo gr : gruppo) {
            if (gr.getIdG() > newId) {
                newId = gr.getIdG();
            }
        }
        newId += 1;
        gruppo.clear();

        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO gruppo (ID_G, name_g) VALUES (?,?)");
            s.setInt(1, newId);
            s.setString(2, name);
            s.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int size() {
        return gruppo.size();
    }

    public void clear() {
        gruppo.clear();
    }

    public List<Gruppo> getGruppi() {

        try {
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM gruppo");
            while (r.next()) {
                int ID = r.getInt("ID_G");
                String name = r.getString("name_g");
                Gruppo Gruppo = new Gruppo(ID, name);
                gruppo.add(Gruppo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gruppo;
    }

    public List<Gruppo> getByGroupId(int ID_G) {
        gruppo.clear();
        PreparedStatement s;
        List<Gruppo> ret = new ArrayList<>();
        Gruppo gr;
        try {
            s = connection.prepareStatement("SELECT * FROM gruppo WHERE ID_G = ?");
            s.setInt(1, ID_G);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                int id = r.getInt("ID_G");
                String name = r.getString("name_g");
                gr = new Gruppo(id, name);
                gruppo.add(gr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gruppo;
    }

    public List<Gruppo> getByName(String name_g) {
        gruppo.clear();
        PreparedStatement s;
        Gruppo gr;
        List<Gruppo> ret = new ArrayList<>();
        try {
            s = connection.prepareStatement("SELECT * FROM gruppo WHERE name_g = ?");
            s.setString(1, name_g);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                int id = r.getInt("ID_G");
                String name = r.getString("name_g");
                gr = new Gruppo(id, name);
                gruppo.add(gr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gruppo;
    }

    public void remove(int ID_G) {
        try {
            Statement st = connection.createStatement();
            StringBuilder delete = new StringBuilder();
            delete.append("DELETE FROM gruppo WHERE ID_G = ");
            delete.append(ID_G);
            st.executeUpdate(delete.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}