<?php 
    /* 
    Auteur : Christophe Garcia
    
    Lien : api/suppression

    Method : POST

    Http response : 
      - 200 : success
      - 400 : erreur de parametre
      - 405 : methode non autorisee
      - 406 : suppression non acceptee

    Param form_data : 
        idEvenement : identifiant de l'evenement de la participation a supprimer
        pseudoMembre : pseudo du membre de la participation a supprimer

    */

    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("./BDD_Connexion.php");

    //récupération de la méthode de la requête (GET,POST,PUT...)
    $request_method = $_SERVER["REQUEST_METHOD"];
    
    if($request_method=='POST') {
        if (isset($_POST["idEvenement"]) && isset($_POST["pseudoMembre"])) {
            $idEvenement = $_POST["idEvenement"];
            $pseudoMembre = $_POST["pseudoMembre"];
            
            // Mise par defaut du status a REUSSIE
            $status = 'REUSSIE';

            //instance de connexion à la base
            $bdd = new BDD_Connexion();
            $dbc = $bdd->getConnexion();

            // Verification de l'existence de la participation evenement/membre
            $query = "SELECT * FROM PARTICIPATION WHERE idEvenement = " . $idEvenement . " AND pseudoMembre = '" . $pseudoMembre . "'";
            $stmt = $dbc->query($query);
            $results = $stmt->fetchAll();
            $ancienNbInscrits = 0;

            if (count($results) == 0) {
                $status = 'ECHOUEE';
                echo "Participation Evenement/Membre inexistante\n";
            }
            
            // Suppression de la participation du membre a l'evenement
            if ($status == 'REUSSIE') {
                $query = "DELETE FROM PARTICIPATION WHERE idEvenement = " . $idEvenement . " AND pseudoMembre = '" . $pseudoMembre . "'";
                $stmt = $dbc->query($query);
                if ($stmt != false) {
                    $results = $stmt->fetchAll();

                    if (count($results) != 0) {
                        $status = 'ECHOUEE';
                        echo "Suppression de la participation echouee\n";
                    }
                }
            }

            if ($status == 'REUSSIE') {
                http_response_code(200);
            } else {
                http_response_code(406);
            }
            echo json_encode($status);
        } else {
            // Erreur de parametre
            http_response_code(400);
        }
    } else {
        // Requête invalide
        header("HTTP/1.0 405 Method Not Allowed");
    }
?>