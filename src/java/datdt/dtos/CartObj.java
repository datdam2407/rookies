/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.dtos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author MSI-PC
 */
public class CartObj implements Serializable {

    private String customerName;
    private HashMap<String, CarDTO> cart;
    
    public CartObj(String customerName) {
        this.customerName = customerName;
        this.cart = new HashMap<>();
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, CarDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, CarDTO> cart) {
        this.cart = cart;
    }
    
    public void addToCart(CarDTO dto) throws Exception
    {
        if(!this.cart.containsKey(dto.getCarID()))
        {
            this.cart.put(dto.getCarID(), dto);
        }
    }
    
    public void remove(String id) throws Exception
    {
        if(this.cart.containsKey(id))
        {
            this.cart.remove(id);
        }
    }
}