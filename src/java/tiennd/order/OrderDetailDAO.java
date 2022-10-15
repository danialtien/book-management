/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennd.order;

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
public class OrderDetailDAO implements Serializable {

    private final int MAX = 1000;

    private List<OrderDetailDTO> orderList;

    /**
     * @return the orderList
     */
    public List<OrderDetailDTO> getOrderList() {
        return orderList;
    }

    public void loadOrderedDetail() throws SQLException, NamingException {
//        Delete list order data before adding
        if (orderList != null) {
            this.orderList.clear();
        }

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

//        1. Make connection
            con = DBHelper.makeConnection();

            if (con != null) {
                //          2. Create SQL Stirng . co khoang trang sau username
                String sql = "Select Id, Title, Quantity, Price, Total "
                        + "From OrderDetail ";
//          3. Create Statement Object
                statement = con.prepareStatement(sql);

//          4. Execute Query
                rs = statement.executeQuery();

//          5. Process result


                    while (rs.next()) {
                        int id = rs.getInt("Id");
                        String title = rs.getString("Title");
                        int quantity = rs.getInt("Quantity");
                        int price = rs.getInt("Price");
                        int total = rs.getInt("Total");

                        OrderDetailDTO dto = new OrderDetailDTO(id, title, quantity, price, total);

                        if (this.orderList == null) {
                            this.orderList = new ArrayList<>();
                        }
                        this.orderList.add(dto);
                    } // end if rs.next() is null
                }


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

    public void insertOrderIntoDB(String title, int quantity, int price) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int id = this.checkId();
        try {

//        1. Make connection
            con = DBHelper.makeConnection();

            if (con != null) {
//          2. Create SQL Stirng . co khoang trang sau username
                String sql = "INSERT INTO OrderDetail (Id, Title, Quantity, Price, Total) "
                        + "VALUES (?,?,?,?,?);";
//          3. Create Statement Object
                statement = con.prepareStatement(sql);
                statement.setInt(1, id);
                statement.setString(2, title);
                statement.setInt(3, quantity);
                statement.setInt(4, price);
                statement.setInt(5, price * quantity);

//          4. Execute Query
                int affectRow = statement.executeUpdate();

//          5. Process result
                if (affectRow > 0) {
                    return;
                } else{
                    System.out.println("Insert fail");
                }

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

//    check id exist or not if exist increment 1 unit
    public int checkId() {
        int id = 1;
        int arr[] = new int[MAX];
        if (this.orderList != null) {
            for (int i = 0; i < 100;) {
                for (OrderDetailDTO list : this.orderList) {
                    arr[i] = list.getId();
                    ++i;
                }
            }
            sortASC(arr);
            for (Integer num : arr) {
                if (num == id) {
                    ++id;
                }
            }
        } // end if orderList != null
        return id;
    }

//    sort array id in ascending order
    public static void sortASC(int[] arr) {
        int temp = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
}
