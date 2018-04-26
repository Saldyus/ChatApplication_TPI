/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servelet;

import database.Messaggi;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author salva
 */
@WebServlet(name = "MessageServlet", urlPatterns = {"/MessageServlet"})
public class MessageServlet extends HttpServlet {

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
        
        List<Messaggi> message = new ArrayList<>();
        String mitt_w = request.getParameter("mitt");
        String dest_w = request.getParameter("dest");
        
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM chat.messaggi WHERE (mitt = ? and dest = ?) OR (mitt = ? and dest = ?)");
            ps.setString(1, mitt_w);
            ps.setString(2, dest_w);
            ps.setString(3, dest_w);
            ps.setString(4, mitt_w);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                int ID_M = r.getInt("ID_M");
                String mitt = r.getString("mitt");
                String dest = r.getString("dest");
                String type_m = r.getString("type_m");
                String text_m = r.getString("text_m");
                Date data = r.getDate("data");
                Messaggi m = new Messaggi(ID_M, mitt, dest, type_m, text_m, data);
                if(mitt.equals(mitt_w)){
                    m.setDS("DX");
                }else{
                    m.setDS("SX");
                }
                message.add(m);
            }
            request.setAttribute("messages", message);
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(MessageServlet.class.getName()).log(Level.SEVERE, null, ex);
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
