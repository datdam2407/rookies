/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.daos;

import datdt.dtos.CarDTO;
import datdt.ult.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author MSI-PC
 */
public class CarDAO implements Serializable {

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

    public int count() throws Exception {
        try {
            String sql = "select count(*) from tbl_Car";
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(1); // kq no dem duoc bao nhieu bai no nam o cột đầu tiên
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    //search controller
    public int countSearchCar(String name, String category) throws Exception {
        String sql = null;
//        if (name.equals("") && category.equals("")) {
//            sql = "Select count(*) From tbl_OderDetail Where startDate = ? and endDate = ?";
//        }
        if (category.equals("")) {
            sql = "Select count(*) From tbl_Car Where name Like ?";
        }
//        With x As(Select ROW_NUMBER() Over (Order By Productname asc) as num,* From tblProduct Where Productname Like '%c%' And CategoryID = 'D' And Quantity > 0 And status = 'active') Select * From x Where num between 1 and 10
//With x As(Select ROW_NUMBER() Over (Order By Productname asc) as num,* From tblProduct Where Productname Like '%T%' And CategoryID = 'D' And Quantity > 0 And status = 'active') Select * From x Where num between 1 And 10

        if (name.equals("")) {
            sql = "Select count(*) From tbl_Car Where CategoryID = ?";
        }
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
//            if (name.equals("") && category.equals("")) {
//               pr.setTimestamp(1, new Timestamp(rentDate.getTime()));
//               pr.setTimestamp(2, new Timestamp(endDate.getTime()));
// 
//            }
            if (category.equals("")) {
                pr.setString(1, "%" + name + "%");
            }
            if (name.equals("")) {
                pr.setString(1, category);
            }
            rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public List<CarDTO> getAllProduct() throws NamingException, SQLException, Exception {
        List<CarDTO> ls = null;
        String carID, name, color, categoryID;
        float Price;
        String status, image;
        CarDTO dto;
        String sql = "Select carID, name, color, categoryID, price, status, image From tbl_Car";
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            ls = new ArrayList<>();
            while (rs.next()) {
                carID = rs.getString("carID");
                name = rs.getString("name");
                color = rs.getString("color");
                categoryID = rs.getString("categoryID");
                Price = rs.getFloat("price");
                status = rs.getString("status");
                image = rs.getString("image");

                dto = new CarDTO(carID, name, color, categoryID, Price, status, image);
                ls.add(dto);
            }
        } finally {
            closeConnection();
        }
        return ls;
    }

    public List<CarDTO> getAllProduct(int index, int size) throws NamingException, SQLException, Exception {
        List<CarDTO> ls = null;
        String carID, name, color, categoryID;
        float Price;
        String status, image;
        CarDTO dto;
        String sql = "With x As(Select ROW_NUMBER() Over (Order By name asc) as num,carID, name, color, categoryID, price, status,image From tbl_Car) "
                + "Select carID, name, color, categoryID, price, status,image From x Where num between ?*?-(?-1) And ?*?";
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setInt(1, index);
            pr.setInt(2, size);
            pr.setInt(3, size);
            pr.setInt(4, index);
            pr.setInt(5, size);
            rs = pr.executeQuery();
            ls = new ArrayList<>();
            while (rs.next()) {
                carID = rs.getString("carID");
                name = rs.getString("name");
                color = rs.getString("color");
                categoryID = rs.getString("categoryID");
                Price = rs.getFloat("price");
                status = rs.getString("status");
                image = rs.getString("image");

                dto = new CarDTO(carID, name, color, categoryID, Price, status, image);
                ls.add(dto);
            }
        } finally {
            closeConnection();
        }
        return ls;
    }

    public CarDTO getCarByKey(String CarID) throws NamingException, SQLException, Exception {
        CarDTO ls = null;
        String name, color, categoryID;
        float Price;
        String status, image;
        String sql = "Select name, color, categoryID, price, status,image From tbl_Car where carID = ?";
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, CarID);
            rs = pr.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                color = rs.getString("color");
                categoryID = rs.getString("categoryID");
                Price = rs.getFloat("price");
                status = rs.getString("status");
                image = rs.getString("image");

                ls = new CarDTO(CarID, name, color, categoryID, Price, status, image);
            }
        } finally {
            closeConnection();
        }
        return ls;
    }

    public List<CarDTO> getCarByName(String name) throws NamingException, SQLException, Exception {
        List<CarDTO> ls = null;
        String CarID, color, categoryID;
        float Price;
        String status, image;
        CarDTO dt;
        String sql = "Select carID, color, categoryID, price, status,image From tbl_Car where name like ?";
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, "%" + name + "%");
            rs = pr.executeQuery();
            ls = new ArrayList<>();

            while (rs.next()) {

                CarID = rs.getString("carID");
                color = rs.getString("color");
                categoryID = rs.getString("categoryID");
                Price = rs.getFloat("price");
                status = rs.getString("status");
                image = rs.getString("image");

                dt = new CarDTO(CarID, name, color, categoryID, Price, status, image);
                ls.add(dt);
            }
        } finally {
            closeConnection();
        }
        return ls;
    }

    public String getIDByCode(String discountCode) throws Exception {
        String sql = "Select discountID from tbl_Discound where discountCode =?";
        String CodeID = null;
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, discountCode);
            rs = pr.executeQuery();
            if (rs.next()) {
                CodeID = rs.getString("discountID");
            }
        } finally {
            closeConnection();
        }
        return CodeID;
    }

    public int getDicountCode(String discoundID) throws Exception {
        int discountPerCent = 0;

        String sql = "Select discountPercent from tbl_Discound where discountID = ? and GETDATE() < exDate";
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, discoundID);
            rs = pr.executeQuery();
            if (rs.next()) {
                discountPerCent = rs.getInt("discountPercent");
            }
        } finally {
            closeConnection();
        }
        return discountPerCent;
    }

    public int getRentingQuantity(String begin, String end, String id) throws Exception {
        String sql = "Select C.name, Sum(D.orderQuantity) As 'Count' From tbl_Car C Inner Join tbl_OderDetail D On C.carID = D.carID Inner Join tbl_Oder O \n"
                + "On D.oderID = O.oderID where ((O.date Between ? And ?) Or (O.returnDate Between ? And ?) Or (O.date < ? And O.returnDate > ?)) And D.carID = ? Group By C.name";
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, begin);
            pr.setString(2, end);
            pr.setString(3, begin);
            pr.setString(4, end);
            pr.setString(5, begin);
            pr.setString(6, end);
            pr.setString(7, id);
            rs = pr.executeQuery();
            while (rs.next()) {
                return rs.getInt(2);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public int getRentingSQuantity(String name, String category, String id, String begin, String end) throws Exception {
        String sql = null;
        if (name.equals("")) {
            sql = "Select C.name, Sum(D.orderQuantity) As 'Count' From tbl_Car C Inner Join tbl_OderDetail D On C.carID = D.carID Inner Join tbl_Oder O \n"
                    + "On D.oderID = O.oderID Where C.categoryID = ? And D.carID = ? and ((O.date Between ? And ?) "
                    + "Or (O.returnDate Between ? And ?) Or (O.date < ? And O.returnDate > ?)) "
                    + " Group By C.name";
        }
        if (category.equals("")) {
            sql = "Select count(C.name),Sum(D.orderQuantity) From tbl_Car C Inner Join tbl_OderDetail D On C.carID = D.carID Inner Join tbl_Oder O \n"
                    + "On D.oderID = O.oderID Where C.name like ? And D.carID = ? and ((O.date Between ? And ?) Or (O.returnDate Between ? And ?) "
                    + "Or (O.date < ? And O.returnDate > ?))";
        }
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            if (category.equals("")) {
                pr.setString(1, "%" + name + "%");
                pr.setString(2, id);
                pr.setString(3, begin);
                pr.setString(4, end);
                pr.setString(5, begin);
                pr.setString(6, end);
                pr.setString(7, begin);
                pr.setString(8, end);
            }
            else if (name.equals("")) {
                pr.setString(1, category);
                pr.setString(2, id);
                pr.setString(3, begin);
                pr.setString(4, end);
                pr.setString(5, begin);
                pr.setString(6, end);
                pr.setString(7, begin);
                pr.setString(8, end);
            }
            rs = pr.executeQuery();

            while (rs.next()) {
                return rs.getInt(2);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }
     public List<CarDTO> AddMoreCarToCart(String name) throws Exception
    {
        List<CarDTO> ls = new ArrayList<>();
        CarDTO dto = null;
    
        String CarID, color, categoryID;
        float Price;
        String status, image;
        String sql = "Select carID,name, color, categoryID, price, status,image From tbl_Car where name = ?";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, name);
            rs = pr.executeQuery();
            while (rs.next())
            {
                  CarID = rs.getString("carID");
                  name = rs.getString("name");
                color = rs.getString("color");
                categoryID = rs.getString("categoryID");
                Price = rs.getFloat("price");
                status = rs.getString("status");
                image = rs.getString("image");

                 dto = new CarDTO(CarID, name, color, categoryID, Price, status, image);
                ls.add(dto);
            }
        }
        finally
        {
            closeConnection();
        }
        return ls;
    }
     public boolean checkAvailableByDate(String id, String begin, String end) throws Exception
    {
        String sql = "Select D.oderID From tbl_Oder O Inner Join tbl_OderDetail D On O.oderID = D.oderID \n" +
"Where ((O.date Between ? And ?) Or (O.returnDate Between ? And ?) Or (O.date < ? And O.returnDate > ?)) And D.carID = ?";
        try
        {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, begin);
            pr.setString(2, end);
            pr.setString(3, begin);
            pr.setString(4, end);
            pr.setString(5, begin);
            pr.setString(6, end);
            pr.setString(7, id);
            rs = pr.executeQuery();
            while(rs.next())
            {
                return true;
            }
        }
        finally
        {
            closeConnection();
        }
        return false;
    }
}

