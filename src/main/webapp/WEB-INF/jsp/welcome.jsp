<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create/Edit Contact</title>
</head>
<body>
<h3><a href="aboutus">About Us</a></h3>
	 <div align="center">
            <h1>Contact List</h1>
            <h3><a href="newUser">New Contact</a></h3>
            <table border="1">
                <th>No</th>
                <th>Name</th>
                <th>Email</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Action</th>
                 
                <c:forEach var="users" items="${users}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${users.name}</td>
                    <td>${users.email}</td>
                    <td>${users.address}</td>
                    <td>${users.phone}</td>
                    <td>
                        <a href="editUser?id=${users.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="deleteUser?id=${users.id}">Delete</a>
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
        <br>
       <h3><a href="newFeature">About Us</a></h3> 
</body>
</html>