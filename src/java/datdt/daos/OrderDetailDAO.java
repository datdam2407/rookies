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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class OrderDetailDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public OrderDetailDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean createOrderDetails(String orderDetailID, String carID, String orderID) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tbl_OderDetail(oderdetailID, oderID, carID) Values(?,?,?)";
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, orderDetailID);
            preStm.setString(2, orderID);
            preStm.setString(3, carID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<CarDTO> getAllOrderDetailByID(String id) throws Exception {
        List<CarDTO> result;
        CarDTO dto;
        String carName,image,feedbackContent,carID,oderdetailID;
        float price;
        int rateStar;
        String sql = "Select C.carID, C.image, C.name, C.price,O.feedbackContent,O.rateStar,O.oderdetailID\n"
                + " From tbl_Car C Inner Join tbl_OderDetail O On C.carID = O.carID Where O.oderID = ?";
        try {
            conn = DBHelper.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                image = rs.getString("image");
                carName = rs.getString("name");
                price = rs.getFloat("price");
                feedbackContent = rs.getString("feedbackContent");             
                rateStar = rs.getInt("rateStar");
                 carID = rs.getString("carID");
                 oderdetailID = rs.getString("oderdetailID");
                dto = new CarDTO(carID,image, carName, price,feedbackContent,rateStar,oderdetailID);
                dto.setFeedback(feedbackContent);
                dto.setRateStar(rateStar);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
