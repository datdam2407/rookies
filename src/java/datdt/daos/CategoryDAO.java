/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.daos;

import datdt.dtos.CategoryDTO;
import datdt.ult.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI-PC
 */
public class CategoryDAO implements Serializable{
       private Connection con;
    private PreparedStatement pr;
    private ResultSet rs;

    public CategoryDAO() {
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

    public List<CategoryDTO> getAllCategory() throws Exception {
        List<CategoryDTO> rss;
        CategoryDTO dto;
        String id, name;
        String sql = "Select categoryID, categoryname From tbl_category";
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            rss = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("categoryID");
                name = rs.getString("categoryname");
                dto = new CategoryDTO(id, name);
                rss.add(dto);
            }
        } finally {
            closeConnection();
        }
        return rss;
    }

    public String getID(String name) throws Exception {
        String sql = "Select categoryID From tbl_category Where categoryname = ?";
        try {
            con = DBHelper.makeConnection();
            pr = con.prepareStatement(sql);
            pr.setString(1, name);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString("categoryID");
            }
        } finally {
            closeConnection();
        }
        return "";
    }
}


