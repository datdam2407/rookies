/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author MSI-PC
 */
public class LoginDTO implements Serializable {

    String email, name, address, phone,password,status,useremail;
    String code;
    private String role;
    Date createDate;

    public LoginDTO(String email, String name, String address, String phone, String password,String status, String role, Date createDate) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.createDate = createDate;
        this.status = status;
    }
    public LoginDTO(String email, String name, String code) {
        this.email = email;
        this.name = name;
        this.code = code;
         
    }

    public LoginDTO(String email, String name, String useremail, String status) {
        this.email = email;
        this.name = name;
        this.useremail = useremail;
        this.status = status;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public LoginDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }
    
    
//    public LoginDTO(String email, String password, String role) {
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
