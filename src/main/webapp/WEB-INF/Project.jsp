<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion de Projet</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" /><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #FFFFFF; /* Blanc */
        }

        header {
            background-color: #008DDA; /* Bleu Principal */
            color: #fff;
            padding: 1rem 0;
            text-align: center;
            border-radius: 10px;
            margin-bottom: 1rem;
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

        #projectsTable {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1rem;
            border-radius: 10px;
            overflow: hidden;
        }

        #projectsTable th, #projectsTable td {
            border: 1px solid #ACE2E1; /* Cyan Clair */
            padding: 8px;
            text-align: left;
        }

        #projectsTable th {
            background-color: #41C9E2; /* Bleu Secondaire */
            color: #fff;
        }

        button {
            padding: 0.5rem 1rem;
            margin: 0.5rem;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }

        .updateBtn {
            background-color: #008DDA; /* Bleu Principal */
            color: #fff;
        }

        .updateBtn:hover {
            background-color: #006bb5;
        }

        .deleteBtn {
            background-color: #dc3545;
            color: #fff;
        }

        .deleteBtn:hover {
            background-color: #c82333;
        }

        .viewBtn {
            background-color: #41C9E2; /* Bleu Secondaire */
            color: #fff;
        }

        .viewBtn:hover {
            background-color: #2e9ab2;
        }


        footer {
            background-color: #008DDA; /* Bleu Principal */
            color: #fff;
            text-align: center;
            padding: 1rem 0;
            width: 100%;
            bottom: 0;
            border-radius: 10px;
        }


        .abtn-primary {
            display: inline-block;
            padding: 5px 10px; /* taille réduite du bouton */
            color: #fff;
            background-color: #008915;
            border: none;
            border-radius: 3px;
            text-decoration: none; /* retirer le soulignement */
            text-align: center;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #0056b3; /* couleur du bouton au survol */
        }

    </style>
</head>
<body>
<header class="mb-4">
    <h1>Gestion de Projet</h1>
    <nav>
        <ul class="nav justify-content-center">
            <li class="nav-item"><a class="nav-link text-white" href="#home">Accueil</a></li>
            <li class="nav-item"><a class="nav-link text-white" href="#projects">Projets</a></li>
            <li class="nav-item"><a class="nav-link text-white" href="#about">À propos</a></li>
            <li class="nav-item"><a class="nav-link text-white" href="#contact">Contact</a></li>
        </ul>
    </nav>
</header>

<main class="container">
    <section id="home" class="mb-4">
        <h2>Accueil</h2>
        <p>Bienvenue dans le système de gestion de projet.</p>
    </section>

    <section id="projects" class="mb-4">
        <h2>Projets</h2>
        <input type="text" id="searchInput" class="form-control mb-3" placeholder="Rechercher des projets...">


        <a href="AjouterForm" class="abtn-primary">Ajouter projet</a>


        <div class="table-responsive">
            <table id="projectsTable" class="table table-striped rounded">
                <thead>
                <tr>
                    <th>Nom du projet</th>
                    <th>Description</th>
                    <th>Date de Début</th>
                    <th>Date de Fin</th>
                    <th>Budget</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="project" items="${model}">
                    <tr>
                        <td>${project.nom}</td>
                        <td>${project.description}</td>
                        <td>${project.dateDebut}</td>
                        <td>${project.dateFin}</td>
                        <td>${project.budget}</td>
                        <td>


                            <button class="viewBtn btn btn-primary btn-sm">  <a href="tacheHome?id=${project.idProject}"></a> <i class="fa-solid fa-eye"></i></button>

                            <form action="updateForm?id=${project.idProject}" method="post" style="display:inline;">
                                <input type="hidden" name="projectId" value=${project.idProject}>
                                <button class="updateBtn btn btn-success btn-sm" data-bs-toggle="modal" data-bs-target="#projectModal"> <i class="fas fa-edit"></i></button>

                            </form>
                            <form action="delete?id=${project.idProject}" method="post" style="display:inline;">
                                <input type="hidden" name="projectId" value="1">
                                <button type="submit" class="deleteBtn btn btn-danger btn-sm"><i class="fa-solid fa-trash-can"></i></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </section>

    <section id="about" class="mb-4">
        <h2>À propos</h2>
        <p>À propos du système de gestion de projet.</p>
    </section>

    <section id="contact" class="mb-4">
        <h2>Contact</h2>
        <p>Informations de contact.</p>
    </section>
</main>

<footer>
    <p>&copy; 2024 Votre Nom. Tous droits réservés.</p>
</footer>

<!-- JavaScript Bootstrap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const viewButtons = document.querySelectorAll('.viewBtn');

        viewButtons.forEach(button => {
            button.addEventListener('click', function () {
                const projectId = button.dataset.projectId;
                window.location.href = `view_tasks.jsp?projectId=${projectId}`;
            });
        });
    });
</script>
