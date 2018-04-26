/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import database.Gruppo;
import database.Partecipa;
import database.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Dinaro Salvatore
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/IndexServlet"})
public class IndexServlet extends HttpServlet {

    @Resource(name = "java:app/jdbc/chatWebR")
    private DataSource dataSource;
    
    private String username_mitt;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Users> users = getUsers("");
        request.setAttribute("users", users);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private List<Users> getUsers(String username_i) {
        List<Users> users = new ArrayList<>();
        try {
            Connection c = dataSource.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT username, nome_v from chat.users");
            while (rs.next()) {
                String username = rs.getString("username");
                String nome_v = rs.getString("nome_v");
                if (!username.equals(username_i)) {
                    Users n = new Users(username, nome_v);
                    users.add(n);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;

    }
    
    private List<Partecipa> getPartecipa(){
        List<Partecipa> partecipa = new ArrayList<>();
        try {
            Connection c = dataSource.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM chat.Partecipa");
            while(rs.next()){
                String username = rs.getString("Username");
                int ID_G = rs.getInt("ID_G");
                Partecipa p = new Partecipa(username, ID_G);
                partecipa.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return partecipa;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username_l = request.getParameter("username");
        String password_l = request.getParameter("password");
        username_mitt=username_l;
        
        try {
            Connection c = dataSource.getConnection();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT Username, Password_c FROM chat.users");
            while (r.next()) {
                String username = r.getString("Username");
                String password = r.getString("Password_c");
                if (username.equals(username_mitt) && password.equals(hashCode(password_l))) {
                    List<Users> users = getUsers(username_mitt);
                    List<Partecipa> partecipa = getPartecipa();
                    request.setAttribute("groups", partecipa);
                    request.setAttribute("users", users);
                    request.setAttribute("username_mitt", username_mitt);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }

            request.getRequestDispatcher("login_prova.jsp").forward(request, response);
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String hashCode(String chiaro) throws NoSuchAlgorithmException {
        String hash;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(chiaro.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        hash = sb.toString();
        return hash;
    }

}
