<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Projet</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4; /* Gris clair */
        }

        header {
            background-color: #008DDA; /* Gris foncé */
            color: #fff; /* Blanc */
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
            color: #fff; /* Blanc */
            text-decoration: none;
        }

        main {
            padding: 2rem;
        }

        section {
            margin: 2rem 0;
        }

        form {
            background-color: #fff; /* Blanc */
            padding: 1rem;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: auto;
        }

        form label {
            display: block;
            margin-bottom: 0.5rem;
        }

        form input, form textarea {
            width: calc(100% - 2rem);
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc; /* Gris clair */
            border-radius: 5px;
            margin-left: 1rem;
            margin-right: 1rem;
        }

        form input[type="submit"] {
            background-color: #008DDA; /* Bleu Principal */
            color: #fff; /* Blanc */
            border: none;
            cursor: pointer;
            padding: 0.75rem 1.5rem;
            border-radius: 5px;
            margin-left: 1rem;
            margin-right: 1rem;
        }

        form input[type="submit"]:hover {
            background-color: #006bb5; /* Bleu plus foncé */
        }

        h2 {
            text-align: center; /* Center the heading */
        }
    </style>
</head>
<body>
<header>
    <h1>Détails du Projet</h1>
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
    <section id="addProject">
        <h2>Ajouter un Nouveau Projet</h2>
        <form action="Ajouter" method="post">
            <label for="nom">Nom du Projet:</label>
            <input type="text" id="nom" name="nom" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <label for="dateDebut">Date de Début:</label>
            <input type="date" id="dateDebut" name="dateDebut" required>

            <label for="dateFin">Date de Fin:</label>
            <input type="date" id="dateFin" name="dateFin" required>

            <label for="budget">Budget:</label>
            <input type="number" id="budget" name="budget" step="0.01" required>

            <input type="submit" value="Ajouter">
        </form>
    </section>
</main>
</body>
</html>