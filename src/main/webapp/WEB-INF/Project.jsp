<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Projet" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project Information</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1>Project Information</h1>
    <form action="addProject.jsp" method="get" class="mb-3">
        <button type="submit" class="btn btn-primary">Add Project</button>
    </form>

    <%
        List<Projet> listProjects = (List<Projet>) request.getAttribute("listProjects");
        if (listProjects == null || listProjects.isEmpty()) {
    %>
    <div class="alert alert-warning" role="alert">
        No projects found.
    </div>
    <%
    } else {
    %>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="thead-light">
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
    </div>
    <%
        }
    %>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
