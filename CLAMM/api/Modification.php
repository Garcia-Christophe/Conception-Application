<?php 
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("BDD_Connexion.php");

    $status = 'ECHOUEE';
    
    if (isset($_POST["idEvenement"]) && isset($_POST["pseudo"]) && isset($_POST["nbPersonnes"]) && isset($_POST["informations"])) {
        $idEvenement = $_POST["idEvenement"];
        $pseudoMembre = $_POST["pseudo"];
        
        // Mise par defaut du status a REUSSIE
        $status = 'REUSSIE';

        // Connexion a la base de donnees
        $dbc = BDD_Connexion::getInstance()->getConnexion();

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
        $nbInscrits = intval($_POST["nbPersonnes"]);
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
    }

    echo json_encode(array("modification"=>$status));
?>