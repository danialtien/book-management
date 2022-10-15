/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennd.product;

import java.io.Serializable;

/**
 *
 * @author DELL
 */

public class ProductDTO implements Serializable{
 
    private String sku;
    private String name;
    private int quantity;
    private int price;
    private String description;

    public ProductDTO() {
    }
    
    public ProductDTO(String sku, String Pname, int quantity, int price, String description) {
        this.sku = sku;
        this.name = Pname;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String Pname) {
        this.name = Pname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
