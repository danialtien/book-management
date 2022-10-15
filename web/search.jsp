<%-- 
    Document   : search
    Created on : Jun 6, 2022, 8:22:56 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="./assets/css/styleSearch.css"> 
    </head>
    <body>
        <div class="main">
            <div class="header">
                <!-- Begin nav -->
                <ul id="nav">
                    <li><a href="">Home</a></li>
                    <li><a href="">Band</a></li>
                    <li><a href="">Tour</a></li>
                    <li><a href="">Contact</a></li>
                    <li>
                        <a href="">
                            More
                            <!--<i class="arrow-down ti-angle-down"></i>-->
                        </a>
                        <ul class="subnav">
                            <li><a href="">Merchandise</a></li>
                            <li><a href="">Extras</a></li>
                            <li><a href="">Media</a></li>
                        </ul>
                    </li>
                </ul>
                <!-- End nav -->
            </div> <br/>

            <div class="welcome"> 
                <c:if test="${empty sessionScope.ACCOUNT.fullName}">
                    <c:redirect url="loginPage"/>
                </c:if>

                <font color="blue"> Welcome, ${sessionScope.ACCOUNT.fullName}</font>
            </div>
            
            <div class="content"> 
            <h1>Search page</h1>
            <form action="searchAction">
                Search value <input type="text" name="txtSearchValue"
                                    value="${param.txtSearchValue}"/> 
                <input type="submit" value="Search" name="btAction" />
                <br/><br/>
            </form>          

            <c:set var="searchValue" value="${param.txtSearchValue}"/> 
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="updateAccountAction">

                                <tr>
                                    <td>
                                        ${counter.count}
                                        .</td>
                                    <td>
                                        ${dto.username}
                                        <input type="hidden" name="txtUsername" 
                                               value="${dto.username}" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtPassword" 
                                               value="${dto.password}" />
                                    </td>
                                    <td>
                                        ${dto.fullName}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkAdmin" value="ON" 
                                               <c:if test="${dto.role}" >
                                                   checked="checked"
                                               </c:if>
                                               />
                                    </td>
                                    <td>
                                        <c:url var="deleteLink" value="deleteAccountAction">
                                            <c:param name="btAction" value="delete"/>
                                            <c:param name="username" value="${dto.username}"/>
                                            <c:param name="lastSearchValue" value="${searchValue}"/>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Update" name="updateAccountAction" />
                                        <input type="hidden" value="${searchValue}"
                                               name="lastSearchValue" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty result}">
                    <h2>
                        No record is matched!!!
                    </h2>
                </c:if>
            </c:if>
            </div>
            <br/>
            <form action="logoutAction">
                <input type="submit" name="Logout" value="Logout">
            </form>
        </div>
    </body>
</html>
