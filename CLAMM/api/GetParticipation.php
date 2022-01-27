<?php
    /* 
    Auteur : Manon Goasguen

    Lien : api/participations

    Method : POST

    Http response : 
      - 200 : success
      - 204 : pas de resultat
      - 400 : erreur de paramètre
      - 405 : methode non autorisée

    Param form_data : 
        idEvenment : id de l'événement
        pseudo : pseudo du membre

    Retourne :
      - Les informations d'une participation

    Structure d'un evenement : 
      - idEvenement
      - pseudoMembre
      - nbInscrit
      - informations

    */
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    //connection à la base
    include("BDD_Connexion.php");

    //structure des participations
    include('./Participation.php');

    //récupération de la méthode de la requête (GET,POST,PUT...)
    $request_method = $_SERVER["REQUEST_METHOD"];
    
    if($request_method=='POST'){
        if (isset($_POST["idEvenement"]) && isset($_POST["pseudo"])) {
            $idEvenement = $_POST["idEvenement"];
            $pseudoMembre = $_POST["pseudo"];
            
            // Connexion a la base de donnees
            $dbc = BDD_Connexion::getInstance()->getConnexion();

            // Verification de l'existence de la participation evenement/membre
            $query = "SELECT * FROM PARTICIPATION WHERE idEvenement = " . $idEvenement . " AND pseudoMembre = '" . $pseudoMembre . "'";
            $stmt = $dbc->query($query);
            $results = $stmt->fetchAll();

            if (count($results) > 0) {
                //variable avec la structure d'un événement
                $participation = new Participation();

                $participation=[
                    "idEvenement" => $results[0]['idEvenement'],
                    "pseudoMembre" => $results[0]['pseudoMembre'],
                    "nbInscrit" => $results[0]['nbInscrit'],
                    "informations" => $results[0]['informations']
                ];

                //affichage des résultats
                echo json_encode($participation);

                http_response_code(200); //code de réussite
            }else{
                http_response_code(204); //pas de participation
            }
            
        } else {
            http_response_code(400); //erreur pas de parametre
        }
    } else {
        // Requête invalide
      header("HTTP/1.0 405 Method Not Allowed");
    }
?>