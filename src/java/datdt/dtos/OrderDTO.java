/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.dtos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MSI-PC
 */
public class OrderDTO implements Serializable{
    String oderID, email ,customerName, address, phone;  
    Date date;
    float total;
    String status , discountID, carName,Image,Price;

    private Date  returnDate, createDate;
 
    List<CarDTO> ls;

    public OrderDTO(String oderID, String email, Date date, float total, String status, String discountID) {
        this.oderID = oderID;
        this.email = email;
        this.date = date;
        this.total = total;
        this.status = status;
        this.discountID = discountID;
    }

    public OrderDTO(String oderID, String email,Date date, float total,String status, String discountID, Date returnDate,String customerName, String address, String phone, 
              Date createDate) {
        this.oderID = oderID;
        this.email = email;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.total = total;
        this.status = status;
        this.discountID = discountID;
        this.returnDate = returnDate;
        this.createDate = createDate;
 

    }

    public OrderDTO(String oderID, String email, String customerName, String address, String phone, Date date, float total, String status, String discountID, String carName, String Image, String Price, Date returnDate, Date createDate) {
        this.oderID = oderID;
        this.email = email;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.total = total;
        this.status = status;
        this.discountID = discountID;
        this.carName = carName;
        this.Image = Image;
        this.Price = Price;
        this.returnDate = returnDate;
        this.createDate = createDate;
     
    }

    public OrderDTO(String oderID, String email, Date date, float total, String status, String discountID, String carName, String Image, String Price) {
        this.oderID = oderID;
        this.email = email;
        this.date = date;
        this.total = total;
        this.status = status;
        this.discountID = discountID;
        this.carName = carName;
        this.Image = Image;
        this.Price = Price;
    }



    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    
    public OrderDTO() {
    }

    public String getOderID() {
        return oderID;
    }

    public void setOderID(String oderID) {
        this.oderID = oderID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public List<CarDTO> getLs() {
        return ls;
    }

    public void setLs(List<CarDTO> ls) {
        this.ls = ls;
    }
}

    