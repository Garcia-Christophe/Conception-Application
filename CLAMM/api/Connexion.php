<?php 
    /* 
    Auteur : Christophe Garcia
    
    Lien : https://obiwan2.univ-brest.fr/licence/lic8/Connexion.php

    Method : POST

    Http response : 
      - 200 : success
      - 400 : erreur de parametre
      - 405 : methode non autorisee
      - 406 : connexion non acceptee

    Param form_data : 
        pseudoMembre : pseudo du membre a connecter
        motDePasse : mot de passe du membre a connecter

    Retourne :
      - Le pseudo du membre si la connexion est une reussite

    */

    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("./BDD_Connexion.php");
    
    //récupération de la méthode de la requête (GET,POST,PUT...)
    $request_method = $_SERVER["REQUEST_METHOD"];
    
    if($request_method=='POST') {
        if (isset($_POST["pseudoMembre"]) && isset($_POST["motDePasse"])) {

            // Definition d'un prefixe pour le salage
            $prefixe = $_POST["pseudoMembre"];

            // Definition d'un suffixe pour le salage (pseudoMembre a l'envers)
            $suffixe = "";
            for ($i = strlen($_POST["pseudoMembre"]) - 1; $i >= 0; $i--) {
                $suffixe = $suffixe . $_POST["pseudoMembre"][$i];
            }

            // Hachage du mot de passe sale
            $mdp = hash("sha256", $prefixe . $_POST["motDePasse"] . $suffixe);

            // Recuperation du mot de passe de la bdd grace au pseudoMembre
            // (le mot de passe n'existe pas si la saisie ne correspond a aucun pseudoMembre)
            // instance de connexion à la base
            $bdd = new BDD_Connexion();
            $dbc = $bdd->getConnexion();
            $query = "SELECT motDePasse FROM MEMBRE WHERE pseudo = '" . $_POST["pseudoMembre"] . "'";
            $stmt = $dbc->query($query);
            $results = $stmt->fetchAll();
            if (count($results) > 0) {
                $mdpBDD = $results[0]['motDePasse'];
            } else {
                $mdpBDD = "";
            }

            // Verification de la validite du pseudoMembre et du mot de passe
            if ($mdp == $mdpBDD) {
                http_response_code(200);
                echo json_encode($_POST["pseudoMembre"]);
            } else {
                http_response_code(406);
            }
        } else {
            // Erreur de parametre
            http_response_code(400);
        }
    } else {
        // Requête invalide
        header("HTTP/1.0 405 Method Not Allowed");
    }
?>