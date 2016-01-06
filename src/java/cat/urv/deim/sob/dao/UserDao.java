/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.dao;

import cat.urv.deim.sob.User;
import cat.urv.deim.sob.UserPk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class UserDao implements Dao{

    @Override
    public void add(IObject object) throws DaoEx{
        PreparedStatement ps = null; Connection connection = null;
        try {
            connection = createConnection(); 
            String sql = "INSERT INTO WHATSAPPDB.USERTABLE VALUES (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, ((User)object).getUsername());
            ps.setString(2, ((User)object).getUserlname());
            ps.setString(3, ((User)object).getPhone());
            ps.setString(4, ((User)object).getEmail());  
            ps.setString(5, ((User)object).getPwd().toString());
            ps.executeUpdate();
            
        }catch (Exception ex) {
            throw new DaoEx(ex.getMessage());
        }finally{
            try{if (ps!=null) ps.close();} catch(Exception ex) {}
            try{if (connection!=null) connection.close();} catch(Exception ex) {}
        }
    }
    
    @Override
    public void addContact(IObject object1, IObject object2) throws DaoEx{
        PreparedStatement ps = null; Connection connection = null;
        try {
            connection = createConnection(); 
            String sql = "INSERT INTO WHATSAPPDB.MESSAGETABLE VALUES (?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, ((User)object1).getUsername());
            ps.setString(2, ((User)object2).getUsername());
            ps.setString(3, null);
            ps.executeUpdate();
        }catch (Exception ex) {
            throw new DaoEx(ex.getMessage());
        }finally{
            try{if (ps!=null) ps.close();} catch(Exception ex) {}
            try{if (connection!=null) connection.close();} catch(Exception ex) {}
        }        
    }
    
    @Override
    public void removeContact(IObject object1, IObject object2) throws DaoEx{
        PreparedStatement ps = null; Connection connection = null;
        try {
            connection = createConnection(); 
            
            String sql = "DELETE FROM WHATSAPPDB.MESSAGETABLE WHERE CHATUSER = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, ((User)object1).getUsername());
            ps.setString(1, ((User)object2).getUsername());
            ps.executeUpdate();
        }catch (Exception ex) {
            throw new DaoEx(ex.getMessage());
        }finally{
            try{if (ps!=null) ps.close();} catch(Exception ex) {}
            try{if (connection!=null) connection.close();} catch(Exception ex) {}
        }        
    }
   
    
    @Override
    public IObject findRegistered(IKey key) throws DaoEx{
        PreparedStatement ps = null; Connection connection = null;
        try {
            connection = createConnection(); 
            String sql = "SELECT * FROM WHATSAPPDB.USERTABLE WHERE USERNAME = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, ((UserPk)key).getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(rs.getString("username"), rs.getString("userlname"),
                rs.getString("phone"),rs.getString("email"),rs.getString("pwd"));
            }else{
                return null;
            }
                        
        }catch (Exception ex) {
            throw new DaoEx(ex.getMessage());
        }finally{
            try{if (ps!=null) ps.close();} catch(Exception ex) {}
            try{if (connection!=null) connection.close();} catch(Exception ex) {}
        }
    }
    
    private Connection createConnection() throws Exception{
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection("jdbc:derby://localhost:1527/WhatsAppDB", "root", "toor");
        }catch(ClassNotFoundException | SQLException ex){
            throw ex;
        }
    }

    @Override
    public String getFriends(IKey key) throws DaoEx {
          PreparedStatement ps = null; Connection connection = null;
          String friends = null;
        try {
            connection = createConnection(); 
            String sql = "SELECT CHATUSER FROM WHATSAPPDB.MESSAGETABLE WHERE MAINUSER = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, ((UserPk)key).getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                friends=rs.getString("chatuser");
                friends+="/";
                while(rs.next()){
                    friends+=rs.getString("chatuser");
                    friends+="/";
                }
                return friends;
            }else{
                return null;
            }
                        
        }catch (Exception ex) {
            throw new DaoEx(ex.getMessage());
        }finally{
            try{if (ps!=null) ps.close();} catch(Exception ex) {}
            try{if (connection!=null) connection.close();} catch(Exception ex) {}
        }
    }

    @Override
    public String sendMessage(IObject object1, IObject object2, String message) throws DaoEx{
        PreparedStatement ps = null; 
        Connection connection = null;
        
        String messages = selectUser(object1, object2);
        
        try {
            connection = createConnection(); 
            
            messages+=message;
            messages+="/";
            String sql = "UPDATE WHATSAPPDB.MESSAGETABLE SET MESSAGES = ? WHERE MAINUSER = ? AND CHATUSER = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(2, ((User)object1).getUsername());
            ps.setString(3, ((User)object2).getUsername());
            ps.setString(1, messages);
            ps.executeUpdate();
            
            sql = "UPDATE WHATSAPPDB.MESSAGETABLE SET MESSAGES = ? WHERE MAINUSER = ? AND CHATUSER = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(3, ((User)object1).getUsername());
            ps.setString(2, ((User)object2).getUsername());
            ps.setString(1, messages);
            ps.executeUpdate();


            return messages;
         
        }catch (Exception ex) {
            throw new DaoEx(ex.getMessage());
        }finally{
            try{if (ps!=null) ps.close();} catch(Exception ex) {}
            try{if (connection!=null) connection.close();} catch(Exception ex) {}
        }      
    }
    
    @Override
    public String selectUser(IObject object1, IObject object2) throws DaoEx{
        PreparedStatement ps = null; 
        Connection connection = null;
        String messages = null;
        
        try {
            connection = createConnection(); 
            String sql = "SELECT MESSAGES FROM WHATSAPPDB.MESSAGETABLE WHERE MAINUSER = ? AND CHATUSER = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, ((User)object1).getUsername());
            ps.setString(2, ((User)object2).getUsername());
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                messages=rs.getString("messages");
            }
            
            sql = "SELECT MESSAGES FROM WHATSAPPDB.MESSAGETABLE WHERE MAINUSER = ? AND CHATUSER = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(2, ((User)object1).getUsername());
            ps.setString(1, ((User)object2).getUsername());
            
            rs = ps.executeQuery();
            if(rs.next()){
                messages=rs.getString("messages");
            }
            
            return messages;
         
        }catch (Exception ex) {
            throw new DaoEx(ex.getMessage());
        }finally{
            try{if (ps!=null) ps.close();} catch(Exception ex) {}
            try{if (connection!=null) connection.close();} catch(Exception ex) {}
        }
    }      
}
