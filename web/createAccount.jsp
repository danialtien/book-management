<%-- 
    Document   : createAccount
    Created on : Jun 17, 2022, 3:29:44 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="createAccountAction" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERR}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (e.g 6-20 characters) <br>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font> <br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExist}">
                <font color="red">
                ${errors.usernameIsExist}
                </font> <br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (e.g 6-20 characters) <br>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font> <br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> <br> 
            <c:if test="${not empty errors.confirmMatched}">
                <font color="red">
                ${errors.confirmMatched}
                </font> <br/>
            </c:if>
            Full Name* <input type="text" name="txtFullname" value="${param.txtFullname}" /> (e.g 2-20 characters) <br>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}
                </font> <br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
