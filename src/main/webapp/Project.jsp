<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23/05/2024
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Projet" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Information</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Project Information</h1>

<%
    List<Projet> listProjects = (List<Projet>) request.getAttribute("listProjects");
    if (listProjects == null || listProjects.isEmpty()) {
%>
<p>No projects found.</p>
<%
} else {
%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Budget</th>
        <th>Start Date</th>
        <th>End Date</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Projet project : listProjects) {
    %>
    <tr>
        <td><%= project.getId() %></td>
        <td><%= project.getNom() %></td>
        <td><%= project.getDescription() %></td>
        <td><%= project.getBudget() %></td>
        <td><%= project.getDateDebut() %></td>
        <td><%= project.getDateFin() %></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>

