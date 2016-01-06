/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.User;
import cat.urv.deim.sob.UserPk;
import cat.urv.deim.sob.dao.DaoEx;
import cat.urv.deim.sob.dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author carlos
 */
@WebServlet(name = "LogInCommand", urlPatterns = {"/login.do"})
public class LogInCommand extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws cat.urv.deim.sob.dao.DaoEx
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, DaoEx, NoSuchAlgorithmException {
        
        String user = request.getParameter("username");
        
        String passwordToHash = request.getParameter("pwd");
        String generatedPassword = null;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(passwordToHash.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
       
        if(user.equals("") || request.getParameter("pwd").equals("")){
            request.getRequestDispatcher("empty.jsp").forward(request, response);
        }else{       
            UserDao dao = new UserDao();
            UserPk userpk = new UserPk(request.getParameter("username"));
            User object = (User)dao.findRegistered(userpk);

            if(object==null){  
                request.getRequestDispatcher("erroruser.jsp").forward(request, response);
            }else{
                if(object.getPwd().equals(generatedPassword)){
                   HttpSession session = request.getSession(true);
                   session.setAttribute("user", user);
                   String friends = dao.getFriends(userpk);
                   session.setAttribute("friend", null);
                   if(friends!=null) session.setAttribute("friend", friends);
                
                   request.getRequestDispatcher("mainweb.jsp").forward(request, response);
                   //try(PrintWriter out = response.getWriter()) {
                       //out.println("ERROR " + friends);
                   // }
                }else{
                    request.getRequestDispatcher("errorpwd.jsp").forward(request, response);                  
                }          
            }  
        }    
  
    }

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
        try {
            processRequest(request, response);
        } catch (DaoEx | NoSuchAlgorithmException ex) {
            Logger.getLogger(LogInCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DaoEx | NoSuchAlgorithmException ex) {
            Logger.getLogger(LogInCommand.class.getName()).log(Level.SEVERE, null, ex);
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
