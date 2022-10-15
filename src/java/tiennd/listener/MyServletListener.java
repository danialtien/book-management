/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennd.listener;

import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import tiennd.util.PropertiesFileHelper;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class MyServletListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        String siteMapLocation
                = context.getInitParameter("SITE_PROPERTIES_FILE_LOCATION");
        String authPropertyLocation
                = context.getInitParameter("AUTHENTICATION_PROPERTIES_FILE_LOCATION");
        Properties siteMapProperty = new Properties();
        siteMapProperty.putAll(PropertiesFileHelper.getProperties(context, 
                        siteMapLocation));
        Properties authenticationProperty = 
                PropertiesFileHelper.getProperties(context, 
                        authPropertyLocation);
        context.setAttribute("SITE_MAP", siteMapProperty);
        context.setAttribute("AUTHENTICATION_LIST", authenticationProperty);

//        String txtFile = realPath + "WEB-INF/roadmap.txt
//                read file and load to map
//        store attribute of context scope
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Undeploying successfully");           
    }
}
