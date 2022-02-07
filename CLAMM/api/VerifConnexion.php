<?php 
    /* 
    Auteur : Manon Goasguen
    
    Lien : https://obiwan2.univ-brest.fr/licence/lic8/api/VerifConnexion.php

    Method : POST

    Http response : 
      - 200 : success
      - 400 : erreur de parametre
      - 405 : methode non autorisee

    Param form_data : 
        pseudoMembre : pseudo du membre dont il faut vérifier la présence

    Retourne :
      - Le vrai si le membre existe, faux sinon

    */

    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("./BDD_Connexion.php");
    
    //récupération de la méthode de la requête (GET,POST,PUT...)
    $request_method = $_SERVER["REQUEST_METHOD"];
    
    if($request_method=='POST') {
        if (isset($_POST["pseudoMembre"])) {

            $bdd = new BDD_Connexion();
            $dbc = $bdd->getConnexion();
            $pseudo=$_POST["pseudoMembre"];
            $query = "SELECT * FROM MEMBRE WHERE pseudo='$pseudo'";
            $stmt = $dbc->query($query);
            $results = $stmt->fetchAll();
            if (count($results) > 0) {
                echo 1;
            } else {
                echo 0;
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