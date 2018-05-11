/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import DatabaseManager.DatabaseManagerUsers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Resource(name = "java:app/jdbc/chatWebR")
    private DataSource dataSource;

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
        request.getRequestDispatcher("login_prova.jsp").forward(request, response);
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
        
        String username_mitt = request.getParameter("username");
        String password = request.getParameter("password");
        String nome_v = request.getParameter("nome_v");
        
        try {
            Connection c = dataSource.getConnection();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT username FROM chat.users");
            while(r.next()){
                String username = r.getString("username");
                if(username.equals(username_mitt)){
                    request.getRequestDispatcher("login_prova.jsp").forward(request, response);
                }
            }
            
            PreparedStatement ps = c.prepareStatement("INSERT INTO chat.users (Username, Password_c, nome_v) VALUES (?, ?, ?)");
            ps.setString(1, username_mitt);
            ps.setString(2, hashCode(password));
            ps.setString(3, nome_v);
            ps.executeUpdate();

            request.getRequestDispatcher("login_prova.jsp").forward(request, response);
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * 
     * @param chiaro The message 
     * @return the hash of a message
     * @throws NoSuchAlgorithmException 
     */
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

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        InputStream is = new FileInputStream(new File(imageUrl));
        OutputStream os = new FileOutputStream(new File(destinationFile));

        byte[] b = new byte[1024];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
    
}
