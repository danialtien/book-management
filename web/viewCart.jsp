<%-- 
    Document   : viewCart
    Created on : Jun 14, 2022, 6:42:00 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book store</title>
    </head>
    <body>
        <h1>Your Cart includes:</h1>

        <c:if test="${not empty sessionScope}">
            <c:set var="result" value="${sessionScope.CART.getItems()}" />
            
            <c:if test="${not empty result}">
                <form action="removeSelectedBook">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${result.entrySet()}" varStatus="counter" >
                                <tr>
                                    <td>
                                        ${counter.count}
                                        .</td>
                                    <td>
                                        ${entry.getKey()}
                                        <input type="hidden" name="txtPname" 
                                               value="${entry.getKey()}" />
                                    </td>
                                    <td>
                                        ${entry.getValue()}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${entry.getKey()}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3"> 
                                    <a href="shoppingAction">Add more book to Cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Book" name="btAction"/>
                                </td>

                            </tr>
                        </tbody>
                    </table>
                    
                </form> <br>
                <form action="checkoutAction">
                    <input type="submit" value="Checkout" name="btAction" />
                </form> <br> 
            </c:if>
            <c:if test="${empty result}">
                <h2>No cart is existed!!!</h2>
                <a href="shoppingAction">Shopping Now</a>
            </c:if>
        </c:if>

        <c:if test="${empty sessionScope}">
            <h2>No cart is existed!!!</h2>
            <a href="shoppingAction">Shopping Now</a>
        </c:if>

    </body>

</html>
