/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennd.registration;

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
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {

//        1. Make connection
            con = DBHelper.makeConnection();

            if (con != null) {
                //          2. Create SQL Stirng . co khoang trang sau username
                String sql = "Select username, lastname, isAdmin "
                        + "From Registration "
                        + "Where username like ? "
                        + "And password = ? ";
//          3. Create Statement Object
                statement = con.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);

//          4. Execute Query
                rs = statement.executeQuery();

//          5. Process result
                if (rs.next()) {
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    result= new RegistrationDTO(username, null, fullName, role) ;
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
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccount() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

//        1. Make connection
            con = DBHelper.makeConnection();

            if (con != null) {
                //          2. Create SQL Stirng . co khoang trang sau username
                String sql = "Select username, password, lastName, isAdmin "
                        + "From Registration "
                        + "Where lastname like ? ";
//          3. Create Statement Object
                statement = con.prepareStatement(sql);
                statement.setString(1, "%" + searchValue + "%");

//          4. Execute Query
                rs = statement.executeQuery();

//          5. Process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, isAdmin);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();

                    } // 
                    this.accounts.add(dto);
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

    public boolean deleteAcount(String username) throws SQLException,  NamingException {
        Connection con = null;
        PreparedStatement statement = null;
        try {

//        1. Make connection
            con = DBHelper.makeConnection();

            if (con != null) {
                //          2. Create SQL Statement String 
                String sql = "Delete From Registration "
                        + "Where username = ? ";
//          3. Create Statement Object
                statement = con.prepareStatement(sql);
                statement.setString(1, username);

//          4. Execute Query
                int affectRow = statement.executeUpdate();
                
//          5. Process result
                if (affectRow > 0) {
                    return true;
                }
            } // end if connection is not null

        } finally {

            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccount(String username, String password, String isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement statement = null;
        boolean role = false;
        if(isAdmin != null){
            role = true;
        }
        try {
//            1. make connection
            con = DBHelper.makeConnection();

//            2. Create sql string 
            if (con != null) {
                String sql = "Update Registration "
                        + "Set password = ? , isAdmin = ? "
                        + "Where username = ? ";
                statement = con.prepareStatement(sql);
                statement.setString(1, password);
                statement.setBoolean(2, role);
                statement.setString(3, username);

//          4. Execute Query
                int affectRow = statement.executeUpdate();

//          5. Process result
                if (affectRow > 0) {
                    return true;
                }
                
            }// end if connection is not null

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean createAccount(RegistrationDTO dto) 
            throws SQLException, NamingException{
        if(dto == null){
            return false;
        }
        Connection con = null;
        PreparedStatement statement = null;
        try {

//        1. Make connection
            con = DBHelper.makeConnection();

            if (con != null) {
                //          2. Create SQL Statement String 
                String sql = "Insert Into  Registration(username, password, lastname, isAdmin) "
                        + "Values(?,?,?,?) ";
//          3. Create Statement Object
                statement = con.prepareStatement(sql);
                statement.setString(1, dto.getUsername());
                statement.setString(2, dto.getPassword());
                statement.setString(3, dto.getFullName());
                statement.setBoolean(4, dto.isRole());

//          4. Execute Query
                int affectRow = statement.executeUpdate();
                
//          5. Process result
                if (affectRow > 0) {
                    return true;
                }
            } // end if connection is not null

        } finally {

            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
