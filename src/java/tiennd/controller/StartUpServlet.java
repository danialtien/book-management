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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tiennd.registration.RegistrationDAO;
import tiennd.registration.RegistrationDTO;
import tiennd.util.MyAppConstant;

/**
 *
 * @author DELL
 */
public class StartUpServlet extends HttpServlet {

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
        String url = (String) siteMap.get(MyAppConstant.StartUpFeatures.LOGIN_PAGE);

        try {
//            1. Get cookies from request
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
//                2. Traverse all cookies to check authentication
                for (Cookie cookie : cookies) {
//                    3. Get username and password from name value
                    String username = cookie.getName();
                    String password = cookie.getValue();

//                    4. Call DAO to check authentication
                    RegistrationDAO dao = new RegistrationDAO();
                    RegistrationDTO result = dao.checkLogin(username, password);

                    if (result != null) {
                        url = (String) siteMap.get(MyAppConstant.StartUpFeatures.SEARCH_PAGE);
                        HttpSession session = request.getSession();
                        session.setAttribute("ACCOUNT", result);
//                        break;
                    } // end authenticaion is successfully checked
                } // end for traverse cookies
            } // end cookies if existed

            

        } catch (NamingException ex) {
            log("StartUpServlet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
            log("StartUpServlet _ SQL _ " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
