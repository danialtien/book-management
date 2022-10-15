/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennd.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tiennd.util.DBHelper;

/**
 *
 * @author DELL
 */
public class ProductDAO implements Serializable {

    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void loadProduct() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

//        1. Make connection
            con = DBHelper.makeConnection();

            if (con != null) {
                //          2. Create SQL Stirng . co khoang trang sau username
                String sql = "Select SKU, Name, Quantity, Price, Description "
                        + "From Product ";
//          3. Create Statement Object
                statement = con.prepareStatement(sql);

//          4. Execute Query
                rs = statement.executeQuery();

//          5. Process result
                while (rs.next()) {
                    String sku = rs.getString("SKU");
                    String name = rs.getString("Name");
                    int quantity = rs.getInt("Quantity");
                    int price = rs.getInt("Price");
                    String des = rs.getString("Description");
                    ProductDTO dto = new ProductDTO(sku, name, quantity, price, des);

                    if (this.products == null) {
                        this.products = new ArrayList<>();

                    } // 
                    this.products.add(dto);
                } // end if rs.next() is null

            } // end if connection is not null

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public int getPrice(String title) {
        int price = 0;
        if (this.getProducts() != null) {
            List<ProductDTO> list = this.getProducts();
            for (ProductDTO product : list) {
                if (product.getName().equals(title)) {
                    price = product.getPrice();
                    return price;
                }
            }
        }
        return 99;
    }
}
