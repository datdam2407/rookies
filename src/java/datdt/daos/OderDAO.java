/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.daos;

import datdt.dtos.CarDTO;
import datdt.dtos.OrderDTO;
import datdt.ult.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MSI-PC
 */
public class OderDAO {

    private Connection con;
    private PreparedStatement pr;
    private ResultSet rs;

    public OderDAO() {
    }

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

    public String getLastOderByUser(String username) throws Exception {
        String OderID = null;
        try {
            String sql = "SELECT oderID FROM tbl_Oder WHERE createDate = (SELECT MAX(createDate) FROM tbl_Oder WHERE email = ? )";
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, username);
            rs = pr.executeQuery();
            if (rs.next()) {
                OderID = rs.getString("oderID");
            }
        } finally {
            closeConnection();
        }
        return OderID;
    }
 
    public boolean DeleteOder(String OderID) throws Exception {
        boolean check = false;
        try {
            String sql = "update tbl_Oder set status = 'inactive' where oderID = ?";
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, OderID);
            check = pr.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }


    public boolean CreateOrder(String orderID, String username, Date rentalDate, float total, String status, String discountID, Date returnDate, String customerName, String customerPhone, String customerAddress, Date createDate)throws Exception {
        boolean check = true;
        try {
            String sql = "Insert Into tbl_Oder(oderID, email, date, total, status,discountID,returnDate, customerName, customerPhone, customerAddress, createDate, isDeleted) Values(?,?,?,?,?,?,?,?,?,?,?,?)";
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, orderID);
            pr.setString(2, username);
            pr.setTimestamp(3, new Timestamp(rentalDate.getTime()));
            pr.setFloat(4, (float) Math.round(total * 100) / 100);
            pr.setString(5, status);
            pr.setString(6, discountID);
            pr.setTimestamp(7, new Timestamp(returnDate.getTime()));
            pr.setString(8, customerName);
            pr.setString(9, customerPhone);
            pr.setString(10, customerAddress);
            pr.setTimestamp(11, new Timestamp(createDate.getTime()));
            pr.setString(12, "inactive");
            check = pr.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
   
  


    public List<OrderDTO> getOder(String carName, String email) throws Exception {
        String oderID;
        Date date;
        float total;
        String status, discountID;
        OrderDTO dt;
        List<OrderDTO> ls = new ArrayList<>();
        try {
            String sql = "Select O.OderID, O.email, O.Total, O.Date, O.status, O.discountID from tbl_Oder O inner join tbl_OderDetail OD on O.OderID = OD.OderID inner join tbl_Car C \n"
                    + "on C.carID = OD.carID where C.name like ? AND O.email = ?";

            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, "%" + carName + "%");
            pr.setString(2, email);
            rs = pr.executeQuery();
            while (rs.next()) {
                oderID = rs.getString("oderID");
                email = rs.getString("email");
                date = rs.getDate("Date");
                total = rs.getFloat("total");
                status = rs.getString("status");
                discountID = rs.getString("discountID");
                dt = new OrderDTO(oderID, email, date, total, status, discountID);
                ls.add(dt);
            }

        } finally {
            closeConnection();
        }
        return ls;
    }
public List<OrderDTO> getAllOrderByUsernameAndSearchName(String user, String name) throws Exception
    {
        List<OrderDTO> result;
        OrderDTO dto;
        String orderID, username, status, customerName, customerPhone, customerAddress,discount;
        float total;
        Date orderDate, returnDate, createDate;
        String sql = " Select O.oderID, O.email, O.total, O.date,O.discountID, O.returnDate, O.status, O.customerName, O.customerPhone, O.customerAddress, O.createDate \n" +
" From tbl_Oder O Inner Join tbl_OderDetail D On O.oderID = D.oderID Inner Join tbl_Car C On D.carID = C.carID Where O.email = ? And O.isDeleted = 'inactive' \n" +
" And C.name Like ? Group By O.oderID, O.email, O.total, O.date,O.discountID, O.returnDate, O.status, O.customerName, O.customerPhone, O.customerAddress, O.createDate\n" +
"  Order By createDate ASC";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, "%" + name +"%");
            rs = pr.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {                
                orderID = rs.getString("oderID");
                username = rs.getString("email");
                total = rs.getFloat("total");
                orderDate = rs.getDate("date");
                returnDate = rs.getDate("returnDate");
                status = rs.getString("status");
                discount = rs.getString("discountID");
                customerName = rs.getString("customerName");
                customerPhone = rs.getString("customerPhone");
                customerAddress = rs.getString("customerAddress");
                createDate = rs.getDate("createDate");
              
                dto = new OrderDTO(orderID, username, orderDate, total, status, discount, returnDate, customerName, customerPhone, customerAddress, createDate);

                
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
public List<OrderDTO> getAllOrderByUsernameAndDate(String user, Date date1, Date date2) throws Exception
    {
        List<OrderDTO> result;
        OrderDTO dto;
        String orderID, username, status, customerName, customerPhone, customerAddress,discount;
        float total;
      
        Date orderDate, returnDate, createDate;
        String sql = " Select O.oderID, O.email, O.total, O.date,O.discountID, O.returnDate, O.status, O.customerName, O.customerPhone, O.customerAddress, O.createDate\n" +
" From tbl_Oder O Inner Join tbl_OderDetail D On O.oderID = D.oderID Inner Join tbl_Car C On D.carID = C.carID Where O.email = ? And O.isDeleted = 'inactive' \n" +
"And (O.createDate between ? and ?) and (O.createDate between ? and ?)\n" +
"Group By O.oderID, O.email, O.total, O.date,O.discountID, O.returnDate, O.status, O.customerName, O.customerPhone, O.customerAddress, O.createDate\n" +
"   Order By createDate ASC";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, user);
            pr.setTimestamp(2, new Timestamp(date1.getTime()));
            pr.setTimestamp(3, new Timestamp(date2.getTime()));
            pr.setTimestamp(4, new Timestamp(date1.getTime()));
            pr.setTimestamp(5, new Timestamp(date2.getTime()));
            rs = pr.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {                
                orderID = rs.getString("oderID");
                username = rs.getString("email");
                total = rs.getFloat("total");
                orderDate = rs.getDate("date");
                returnDate = rs.getDate("returnDate");
                status = rs.getString("status");
                discount = rs.getString("discountID");
                customerName = rs.getString("customerName");
                customerPhone = rs.getString("customerPhone");
                customerAddress = rs.getString("customerAddress");
                createDate = rs.getDate("createDate");
             
                dto = new OrderDTO(orderID, username, orderDate, total, status, discount, returnDate, customerName, customerPhone, customerAddress, createDate);

                
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
//    public List<OrderDTO> getOderbyMOnth(Date date1, Date date2, String email) throws Exception {
//        String oderID;
//        Date date;
//        float total;
//        String status, discountID;
//        OrderDTO dt;
//        List<OrderDTO> ls = new ArrayList<>();
//        try {
//            String sql = "Select O.OderID, O.email, O.Total, O.Date, O.status, O.discountID from tbl_Oder O inner join tbl_OderDetail OD on O.OderID = OD.OderID inner join tbl_Car C \n"
//                    + "on C.carID = OD.carID where (O.date between ? and ?) and (O.date between ? and ?) AND O.email = ? And O.status = 'active'";
//
//            con = DBHelper.makeConnection();
//            pr = con.prepareStatement(sql);
//            pr.setTimestamp(1, new Timestamp(date1.getTime()));
//            pr.setTimestamp(2, new Timestamp(date2.getTime()));
//            pr.setTimestamp(3, new Timestamp(date1.getTime()));
//            pr.setTimestamp(4, new Timestamp(date2.getTime()));
//            pr.setString(5, email);
//            rs = pr.executeQuery();
//            while (rs.next()) {
//                oderID = rs.getString("oderID");
//                email = rs.getString("email");
//                date = rs.getDate("Date");
//                total = rs.getFloat("total");
//                status = rs.getString("status");
//                discountID = rs.getString("discountID");
//                dt = new OrderDTO(oderID, email, date, total, status, discountID);
//                ls.add(dt);
//            }
//
//        } finally {
//            closeConnection();
//        }
//        return ls;
//    }




   public boolean setStatus() throws Exception
    {
        boolean check = false;
        String sql = "Update tbl_Oder Set status = 'Paid' Where GETDATE() > returnDate";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            check = pr.executeUpdate() >0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
   public boolean FeebBack(String id,String feedbackContent,int rateStar) throws Exception
    {
        boolean check = false;
        String sql = "Update tbl_OderDetail set feedbackContent = ?,rateStar = ? Where oderdetailID = ?";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, feedbackContent);
            pr.setInt(2, rateStar);
            pr.setString(3, id);
            check = pr.executeUpdate() >0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
 public boolean UpdateIsDeleted(String id) throws Exception
    {
        boolean check = false;
        String sql = "Update tbl_Oder Set isDeleted = 'active' Where oderID = ?";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, id);
            check = pr.executeUpdate() >0;
        }
        finally
        {
            closeConnection();
        }
        return check;
    }
    public List<CarDTO> getOderdetail(String OderID) throws Exception {
        String CarName, Image;
        int Quan;
        float price;
       
        CarDTO dt;
        List<CarDTO> ls = new ArrayList<>();
        try {
            String sql = " \n"
                    + "Select P.name, P.Image, OD.Orderquantity, P.Price from tbl_Oder O inner join tbl_OderDetail OD on O.OderID = OD.OderID\n"
                    + " inner join tbl_Car P on P.carID = OD.carID\n"
                    + " where O.OderID = ?";

            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, OderID);
            rs = pr.executeQuery();
            while (rs.next()) {
                CarName = rs.getString("name");
                Image = rs.getString("Image");
                Quan = rs.getInt("Orderquantity");
                price = rs.getFloat("Price");
            
                dt = new CarDTO(CarName, Quan, Image, price);
                ls.add(dt);
            }
        } finally {
            closeConnection();
        }
        return ls;
    }

    public void updateQuantity(String OderID) throws Exception {
        try {

            String sql = "update tbl_Car set Quantity = (P.Quantity - OD.Quantity) from tbl_Car "
                    + " P inner join tbl_OderDetail OD on P.carID = OD.carID "
                    + " inner join tbl_Oder O on O.OderID = OD.OderID "
                    + " where O.OderID =?";
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, OderID);
            pr.executeUpdate();
        } finally {
            closeConnection();
        }
    }
public List<OrderDTO> getAllOrderByUsername(String user) throws Exception
    {
        List<OrderDTO> result;
        OrderDTO dto;
        String orderID, username, status, customerName, customerPhone, customerAddress,discount;
        float total;
  ;
        Date orderDate, returnDate, createDate;
        String sql = "Select oderID, email, date, total, status,discountID,returnDate, customerName, customerPhone, customerAddress, createDate "
                + "From tbl_Oder Where email = ? And isDeleted = 'inactive' Order By createDate DESC";
     
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, user);
            rs = pr.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) 
            {                
                orderID = rs.getString("oderID");
                username = rs.getString("email");
                total = rs.getFloat("total");
                orderDate = rs.getDate("date");
                returnDate = rs.getDate("returnDate");
                status = rs.getString("status");
                discount = rs.getString("discountID");
                customerName = rs.getString("customerName");
                customerPhone = rs.getString("customerPhone");
                customerAddress = rs.getString("customerAddress");
                createDate = rs.getDate("createDate");

                dto = new OrderDTO(orderID, username, orderDate, total, status, discount, returnDate, customerName, customerPhone, customerAddress, createDate);
         
                result.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return result;
    }
    


}
