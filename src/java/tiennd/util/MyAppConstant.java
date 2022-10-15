/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiennd.util;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class MyAppConstant implements Serializable {

    public class StartUpFeatures {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String SEARCH_PAGE = "searchPage";
        public static final String SEARCH_ACTION = "searchAction";
        public static final String INVALID_PAGE = "invalid";
    }

    public class LoginFeatures {
        
        public static final String INVALID_PAGE = "invalid";
        public static final String SEARCH_ACTION = "searchAction";
        public static final String LOGIN_PAGE = "loginPage";
    }

    public class SearchLastnameFeatures {

        public static final String SEARCH_ACTION = "searchAction";
        public static final String SEARCH_PAGE = "searchPage";
    }

    public class CreateAccountFeatures {

        public static final String CREATE_ACCOUNT_JPAGE = "createAccountJPage";
    }

    public class ShoppingFeatures {

        public static final String SHOPPING_PAGE = "shoppingPage";
        public static final String SHOPPING_ACTION = "shoppingAction";
    }

    public class RemoveItemsFeatures {

        public static final String VIEW_CART_PAGE = "viewYourCartPage";
    }

    public class ErrorFeatures {

        public static final String ERROR_PAGE = "errorPage";
    }

}
