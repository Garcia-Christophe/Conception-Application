<?php 
    /* 
    Auteur : Christophe Garcia
    
    Lien : api/modification

    Method : POST

    Http response : 
      - 200 : success
      - 400 : erreur de parametre
      - 405 : methode non autorisee
      - 406 : modification non acceptee

    Param form_data : 
        idEvenement : identifiant de l'evenement de la participation a modifier
        pseudoMembre : pseudo du membre de la participation a modifier
        nbInscrit : nombre de personnes a inscrire a l'evenement
        informations : les informations complementaires de la participation

    */

    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("./BDD_Connexion.php");

    //récupération de la méthode de la requête (GET,POST,PUT...)
    $request_method = $_SERVER["REQUEST_METHOD"];
    
    if($request_method=='POST') {
        if (isset($_POST["idEvenement"]) && isset($_POST["pseudoMembre"]) && isset($_POST["nbInscrit"]) && isset($_POST["informations"])) {
            $idEvenement = $_POST["idEvenement"];
            $pseudoMembre = $_POST["pseudoMembre"];
            
            // Mise par defaut du status a REUSSIE
            $status = 'REUSSIE';

            //instance de connexion à la base
            $bdd = new BDD_Connexion();
            $dbc = $bdd->getConnexion();

            // Verification de l'existence de la participation evenement/membre
            // Et recuperation du nombre de personnes inscrites enregistre
            $query = "SELECT nbInscrit FROM PARTICIPATION WHERE idEvenement = " . $idEvenement . " AND pseudoMembre = '" . $pseudoMembre . "'";
            $stmt = $dbc->query($query);
            $results = $stmt->fetchAll();
            $ancienNbInscrits = 0;

            if (count($results) == 0) {
                $status = 'ECHOUEE';
                echo "Participation Evenement/Membre inexistante\n";
            } else {
                $ancienNbInscrits = intval($results[0]["nbInscrit"]);
            }

            // Verification du nombre de personnes possible
            $nbInscrits = intval($_POST["nbInscrit"]);
            if ($status == 'REUSSIE') {
                // Nombre de personnes maximum autorise a participer a l'evenement
                $query = "SELECT nbMaxPersonnes FROM EVENEMENT WHERE id = " . $idEvenement;
                $stmt = $dbc->query($query);
                $nbPersonnesAutorisees = $stmt->fetchAll();

                if (count($nbPersonnesAutorisees) > 0) {
                    $nbPersonnesAutorisees = intval($nbPersonnesAutorisees[0]["nbMaxPersonnes"]);
                } else {
                    $nbPersonnesAutorisees = 0;
                }

                // Nombre de personnes participantes a l'evenement
                $query = "SELECT SUM(nbInscrit) FROM PARTICIPATION WHERE idEvenement = " . $idEvenement;
                $stmt = $dbc->query($query);
                $nbPersonnesParticipantes = $stmt->fetchAll();
                if (count($nbPersonnesParticipantes) > 0) {
                    $nbPersonnesParticipantes = intval($nbPersonnesParticipantes[0]["SUM(nbInscrit)"]) - $ancienNbInscrits;
                } else {
                    $nbPersonnesParticipantes = 0;
                }

                if ($nbInscrits < 1 || $nbInscrits > $nbPersonnesAutorisees - $nbPersonnesParticipantes) {
                    $status = 'ECHOUEE';
                    echo "Nb personnes inscrites trop petit ou trop grand\n";
                }
            }

            // Verification de la taille des informations
            $informations = $_POST["informations"];
            if ($status == 'REUSSIE') {
                if (strlen($informations) > 500) {
                    $status = 'ECHOUEE';
                    echo "Taille des informations trop grande\n";
                }
            }
            
            // Modification de la participation du membre a l'evenement
            if ($status == 'REUSSIE') {
                $query = "UPDATE PARTICIPATION SET nbInscrit = " . $nbInscrits . ", informations = '" . $informations
                . "' WHERE idEvenement = " . $idEvenement . " AND pseudoMembre = '" . $pseudoMembre . "'";
                $stmt = $dbc->query($query);
                if ($stmt != false) {
                    $results = $stmt->fetchAll();

                    if (count($results) != 0) {
                        $status = 'ECHOUEE';
                        echo "Modification de la participation echouee\n";
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