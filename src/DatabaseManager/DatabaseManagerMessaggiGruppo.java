/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import database.Messaggigruppo;
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
public class DatabaseManagerMessaggiGruppo {
    
    private static Connection connection;

    public static DatabaseManagerMessaggiGruppo getInstance() {
        return instance;
    }
    private Properties properties;
    private static DatabaseManagerMessaggiGruppo instance;
    private List<Messaggigruppo> messaggi;

    public DatabaseManagerMessaggiGruppo() {
        try {
            // carica le preferenze dell'app dal file app.properties
            properties = new Properties();
            properties.load(new FileInputStream("app.properties"));

            // Se presente carica il driver JDBC
            if (properties.getProperty("JDBC_DRIVER_CLASS") != null) {
                try {
                    Class.forName(properties.getProperty("JDBC_DRIVER_CLASS"));

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            createConnection();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
        messaggi = new ArrayList<>();
    }

    public static Connection getConnection() {
        try {
            if (instance == null) {
                instance = new DatabaseManagerMessaggiGruppo();
            }

            if (instance.connection.isValid(1) == false) {
                instance.createConnection();
            }

            return instance.connection;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void createConnection() throws SQLException {
        this.connection = DriverManager.getConnection(properties.getProperty("JDBC_DB_URL"));
    }

    public void add(String mitt, String type_m, String text_m, int ID_G) {
        List<Messaggigruppo> MessaggiGruppo = getMessaggigruppo();
        System.out.println(MessaggiGruppo.size());
        int newId = 0;
        for (Messaggigruppo gr : MessaggiGruppo) {
            if (gr.getID_M()> newId) {
                newId = gr.getID_M();
            }
        }
        newId += 1;
        MessaggiGruppo.clear();

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        Date data = new Date(currentDate.getTime());
        
        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO MessaggiGruppo (ID_M, mitt, type_m, text_m, ID_G, data_m) VALUES (?, ?, ?, ?, ?, ?)");
            s.setInt(1, newId);
            s.setString(2, mitt);
            s.setString(3, type_m);
            s.setString(4, text_m);
            s.setInt(5, ID_G);
            s.setDate(6, data);
            s.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int size() {
        return messaggi.size();
    }

    public void clear() {
        messaggi.clear();
    }

    public List<Messaggigruppo> getMessaggigruppo() {

        try {
            Statement s = connection.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM MessaggiGruppo");
            while (r.next()) {
                int ID_M = r.getInt("ID_M");
                String mitt = r.getString("mitt");
                String type = r.getString("type_m");
                String text = r.getString("text_m");
                int ID_G = r.getInt("ID_G");
                Date data = r.getDate("data_m");
                Messaggigruppo MessaggiGruppo = new Messaggigruppo(ID_M, mitt, type, text, ID_G, data);
                messaggi.add(MessaggiGruppo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messaggi;
    }

    public List<Messaggigruppo> getByGroupId(int ID_Gr) {
        this.messaggi.clear();
        PreparedStatement s;
        List<Messaggigruppo> ret = new ArrayList<>();
        Messaggigruppo ms;
        try {
            s = connection.prepareStatement("SELECT * FROM MessaggiGruppo WHERE ID_G = ?");
            s.setInt(1, ID_Gr);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                int ID_M = r.getInt("ID_M");
                String mitt = r.getString("mitt");
                String type = r.getString("type_m");
                String text = r.getString("text_m");
                int ID_G = r.getInt("ID_G");
                Date data = r.getDate("data_m");
                ms = new Messaggigruppo(ID_M, mitt, type, text, ID_G, data);
                messaggi.add(ms);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messaggi;
    }

    public List<Messaggigruppo> getMessage(String mitt, int ID_G) {
        this.messaggi.clear();
        PreparedStatement s;
        Messaggigruppo ms;
        List<Messaggigruppo> ret = new ArrayList<>();
        try {
            s = connection.prepareStatement("SELECT * FROM MessaggiGruppo WHERE ID_G = ? and mitt = ?");
            s.setInt(1, ID_G);
            s.setString(2, mitt);
            ResultSet r = s.executeQuery();
            while (r.next()) {
                String type = r.getString("type_m");
                String text = r.getString("text_m");
                ms = new Messaggigruppo();
                ms.setID_G(ID_G);
                ms.setMitt(mitt);
                ms.setText(text);
                ms.setType(type);
                messaggi.add(ms);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messaggi;
    }

    public void remove(int ID_G) {
        try {
            //List<LogMessage> t = getByAppId(appId);
            //logMessage.remove(t);
            Statement st = connection.createStatement();
            StringBuilder delete = new StringBuilder();
            delete.append("DELETE FROM MessaggiGruppo WHERE ID_G = ");
            delete.append(ID_G);
            st.executeUpdate(delete.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManagerMessaggiGruppo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
