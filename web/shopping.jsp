<%-- 
    Document   : shopping
    Created on : Jun 14, 2022, 5:55:19 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <h1>Shopping now</h1>
        <c:set var="result" value="${sessionScope.SHOP_RESULT}" />
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Add To Card</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter" >       
                    <form action="addBooktoyourCartAction">
                        <tr>
                            <td>
                                ${counter.count}
                            .</td>
                            <td>
                                ${dto.name}
                                <input type="hidden" name="txtPname" 
                                       value="${dto.name}" />
                            </td>
                            <td>
                                ${dto.price}
                                <input type="hidden" name="txtPrice" 
                                       value="${dto.price}" />
                            </td>
                            <td>
                                ${dto.description}
                            </td>

                            <td>
                                <input type="submit" value="AddBooktoyourCart" name="btAction" />
                                <input type="hidden" name="cboBook" value="${dto.name}" />
                            </td>
                        </tr>
                    </form> <br>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <br/>
        <form action="viewYourCartPage">
            <input type="submit" value="View your Cart" name="btAction" />
        </form>
        <br/>
        <a href="loginPage">Login</a> <br>
    </body>
</html>
