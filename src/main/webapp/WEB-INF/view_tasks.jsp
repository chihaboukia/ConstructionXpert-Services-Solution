<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Tasks</title>
</head>
<body>
<h1>Tasks for Project ID: ${projectId}</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td>${task.id_tach}</td>
            <td>${task.t_name}</td>
            <td>${task.t_Description}</td>
            <td>${task.t_datedebut}</td>
            <td>${task.tdatefin}</td>
            <td>${task.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

