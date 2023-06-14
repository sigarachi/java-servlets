<%--
  Created by IntelliJ IDEA.
  User: lalal
  Date: 14.06.2023
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="row">
  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

  <div class="container">
    <h3 class="text-center">List of Users</h3>
    <hr>
    <div class="container text-left">

      <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
        New User</a>
    </div>
    <br>
    <table class="table table-bordered">
      <thead>
      <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Country</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>

      <c:forEach var="user" items="${listUser}">

        <tr>
          <td><c:out value="${user.id}" /></td>
          <td><c:out value="${user.name}" /></td>
          <td><c:out value="${user.email}" /></td>
          <td><c:out value="${user.country}" /></td>
        </tr>
      </c:forEach>

      </tbody>

    </table>
  </div>
</div>
</body>
</html>
