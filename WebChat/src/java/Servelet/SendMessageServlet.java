/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * @author salva
 */
@WebServlet(name = "SendMessageServlet", urlPatterns = {"/SendMessageServlet"})
public class SendMessageServlet extends HttpServlet {

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
        
        String mitt = request.getParameter("mitt");
        String dest = request.getParameter("dest");
        String message = request.getParameter("text");
        
        Calendar ca = Calendar.getInstance();
        
        int m = Integer.valueOf(ca.get(Calendar.MONTH)) + 1;
        String month = String.valueOf(m);
         
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO chat.messaggi (mitt, dest, type_m, text_m, data_m) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, mitt);
            ps.setString(2, dest);
            ps.setString(3, "message");
            ps.setString(4, message);
            ps.setString(5, ca.get(Calendar.YEAR)+"-"+ month +"-" + ca.get(Calendar.DAY_OF_MONTH));
            ps.executeUpdate();
            
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("insert eseguito");
            
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(SendMessageServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
