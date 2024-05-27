<%@ page import="dao.IProjetImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier le Projet</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        header {
            background-color: #008DDA;
            color: #fff;
            padding: 1rem 0;
            text-align: center;
        }

        nav ul {
            list-style: none;
            padding: 0;
        }

        nav ul li {
            display: inline;
            margin: 0 1rem;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
        }

        main {
            padding: 2rem;
        }

        section {
            margin: 2rem 0;
        }

        form {
            background-color: #fff;
            padding: 1rem;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: auto;
        }

        form label {
            display: block;
            margin-bottom: 0.5rem;
            margin-left: 19px;
            color: #0599fb;
        }

        form input, form textarea {
            width: calc(100% - 2rem);
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-left: 1rem;
            margin-right: 1rem;
        }

        form input[type="submit"] {
            background-color: #008DDA;
            color: #fff;
            border: none;
            cursor: pointer;
            padding: 0.75rem 1.5rem;
            border-radius: 5px;
            margin-left: 1rem;
            margin-right: 1rem;
        }

        form input[type="submit"]:hover {
            background-color: #006bb5;
        }

        h2 {
            text-align: center;
        }
        #box{
            display: flex;
        }

        .error {
            color: red;
            margin-left: 1rem;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>

<%
    String id = request.getParameter("id");

%>

<header>
    <h1>ConstructionXpert Services Solution</h1>
    <nav>
        <ul>
            <li><a href="index.html">Accueil</a></li>
            <li><a href="index.html#projects">Projets</a></li>
            <li><a href="index.html#about">À propos</a></li>
            <li><a href="index.html#contact">Contact</a></li>
        </ul>
    </nav>
</header>

<main>
    <section id="updateProject">
        <h2>Modifier le Projet</h2>
        <form id="projectForm" action="Modifier" method="post">
            <input type="hidden" id="id" name="id" value="<%= id %>" required>

            <label for="nom">Nom du Projet:</label>
            <input type="text" id="nom" name="nom" value="${project.nom}" >
            <div id="nomError" class="error"></div>

            <label for="description">Description:</label>
            <textarea id="description" name="description" >${project.description}</textarea>
            <div id="descriptionError" class="error"></div>

            <label for="dateDebut">Date de Début:</label>
            <input type="date" id="dateDebut" name="dateDebut" value="${project.dateDebut}" >
            <div id="dateDebutError" class="error"></div>

            <label for="dateFin">Date de Fin:</label>
            <input type="date" id="dateFin" name="dateFin" value="${project.dateFin}" >
            <div id="dateFinError" class="error"></div>

            <label for="budget">Budget:</label>
            <input type="number" id="budget" name="budget" step="0.01" value="${project.budget}" >
            <div id="budgetError" class="error"></div>

            <input type="submit" value="Mettre à jour">
        </form>
    </section>
</main>

<script>
    document.getElementById('projectForm').addEventListener('submit', function(event) {
        let isValid = true;

        // Clear previous error messages
        document.querySelectorAll('.error').forEach(function(el) {
            el.textContent = '';
        });

        // Validate Nom
        const nom = document.getElementById('nom').value;
        if (nom.trim() === '') {
            document.getElementById('nomError').textContent = 'Le nom du projet est requis.';
            isValid = false;
        }

        // Validate Description
        const description = document.getElementById('description').value;
        if (description.trim() === '') {
            document.getElementById('descriptionError').textContent = 'La description est requise.';
            isValid = false;
        }

        // Validate Dates
        const dateDebut = document.getElementById('dateDebut').value;
        const dateFin = document.getElementById('dateFin').value;
        if (new Date(dateDebut) > new Date(dateFin)) {
            document.getElementById('dateDebutError').textContent = 'La date de début doit être avant la date de fin.';
            document.getElementById('dateFinError').textContent = 'La date de fin doit être après la date de début.';
            isValid = false;
        }

        // Validate Budget
        const budget = document.getElementById('budget').value;
        if (budget <= 0) {
            document.getElementById('budgetError').textContent = 'Le budget doit être un nombre positif.';
            isValid = false;
        }

        if (!isValid) {
            event.preventDefault(); // Prevent form submission if validation fails
        }
    });
</script>
</body>
</html>
