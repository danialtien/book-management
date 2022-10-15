/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tiennd.registration.RegistrationDAO;
import tiennd.util.MyAppConstant;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateAccountController", urlPatterns = {"/UpdateAccountController"})
public class UpdateAccountController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");
        String urlRewriting = (String) siteMap.get(MyAppConstant.ErrorFeatures.ERROR_PAGE);

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String isAdmin = request.getParameter("chkAdmin");
        String searchValue = request.getParameter("lastSearchValue");
        
        try {
//            1. Call Dao
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.updateAccount(username, password, isAdmin);

//             2. Process result
//                call function again to refresh
            if (result) {
                urlRewriting = siteMap.get(MyAppConstant.SearchLastnameFeatures.SEARCH_ACTION)
                        + "?btAction=Search"
                        + "&txtSearchValue=" + searchValue;
            }
        } catch (SQLException ex) {
            log("UpdateAccountServlet _ SQL _ " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateAccountServlet _ Naming _ " + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
