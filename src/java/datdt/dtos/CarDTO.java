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
public class CarDTO implements Serializable{
    
    String carID ,name, color, categoryID,error,odID;
    Date rentDate , payDate;
    float price;
    int quantity,rateStar; //9
    String status, image ,uxname,phone ,address,feedback;
    int avaliableCar , cartQuantity; //6 
    public CarDTO(String carID, String name, String color, String categoryID, float price, String status, String image) {
        this.carID = carID;
        this.name = name;
        this.color = color;
 
        this.categoryID = categoryID;
        this.price = price;
      
        this.status = status;
        this.image = image;

    }

    public CarDTO(String name, int quantity, String image, float price) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getRateStar() {
        return rateStar;
    }

    public void setRateStar(int rateStar) {
        this.rateStar = rateStar;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public CarDTO(String name,String image,int quantity , float price, Date rentDate, Date payDate, String uxname, String phone, String address) {
        this.name = name;
        this.rentDate = rentDate;
        this.payDate = payDate;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.uxname = uxname;
        this.phone = phone;
        this.address = address;
    }

    public CarDTO(String carID,String image, String name, float price,String feedback , int rateStar,String odID) {
        this.carID = carID;
        this.image = image;
        this.name = name;
        this.price = price;
        this.feedback = feedback;
        this.rateStar = rateStar;
        this.carID = odID;

    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    
    public int getAvaliableCar() {
        return avaliableCar;
    }

    public void setAvaliableCar(int avaliableCar) {
        this.avaliableCar = avaliableCar;
    }

    public String getUxname() {
        return uxname;
    }

    public void setUxname(String uxname) {
        this.uxname = uxname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

   
    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

  
}
