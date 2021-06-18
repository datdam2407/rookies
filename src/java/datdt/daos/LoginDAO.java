/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.daos;

import datdt.dtos.LoginDTO;
import datdt.ult.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author MSI-PC
 */
public class LoginDAO implements Serializable{
       private Connection con;
    private PreparedStatement pr;
    private ResultSet rs;
    
   
    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pr != null) {
            pr.close();
        }
        if (con != null) {
            con.close();
        }

    }
 public List<LoginDTO> getAllUser() throws Exception
    {
        List<LoginDTO> result;
        String username, email;
        LoginDTO dto;
        String sql = "Select email, name From tbl_Account";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {
                username = rs.getString("email");
                email = rs.getString("name");
                dto = new LoginDTO(username, email);
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
     public boolean setStatus(String username) throws Exception
    {
        boolean check = false;
        String sql = "Update tbl_Account Set status = 'Active' Where email = ?";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, username);
            check = pr.executeUpdate() >0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
     
     
        public LoginDTO checkLogin(String username, String password) throws Exception {
        LoginDTO dto = null;
                                String user, fullname, status, email;

        String sql = "Select email, name , useremail,status from tbl_Account where email = ? and password = ? COLLATE SQL_Latin1_General_CP1_CS_AS";

        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, username);
            pr.setString(2, password);
            rs = pr.executeQuery();
            if(rs.next()){
                user = rs.getString("email");
                    fullname = rs.getString("name");
                    email = rs.getString("useremail");
                                        status = rs.getString("status");

            dto = new LoginDTO(user, fullname, email, status);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
        public String checkEmail(String username) throws Exception {
       
                                

        String sql = "Select email from tbl_Account where email = ?";

        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, username);
         
            rs = pr.executeQuery();
            if(rs.next()){
                username = rs.getString("email");
                   

          
            }
        } finally {
            closeConnection();
        }
           return username;
       
    }
     


 private List<LoginDTO> accountList;

    public List<LoginDTO> getAccountList() {
        return accountList;
    }

     
     public boolean  createAccount(String email, String name, String password, String role, String status, String address, String phone , Date createDate,String useremail) throws NamingException, SQLException, Exception{
        PreparedStatement ps = null;
        boolean check =false;
        try {
            //1. connect DB
            con = DBHelper.makeConnection();
            //2. creat SQL String
            if (con != null) {
                String sql = "Insert Into tbl_Account(email, name, password, role , status, address, phone, createDate,useremail)"
                        +"Values(?,?,?,?,?,?,?,?,?)";
                //3. Creat Statement
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setString(4, role);
                ps.setString(5, status);
                ps.setString(6, address);
                ps.setString(7,phone);
                ps.setTimestamp(8, new Timestamp(createDate.getTime()));
                                ps.setString(9,useremail);

                //4. Execute Query
                check = ps.executeUpdate() >0;
//                if(row > 0) {
//                    return true;
//                }
            }//end if connection is connected
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }
}


    

