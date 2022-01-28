<?php 
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("BDD_Connexion.php");

    $status = 'ECHOUEE';
    
    if (isset($_POST["idEvenement"]) && isset($_POST["pseudoMembre"]) && isset($_POST["nbInscrit"]) && isset($_POST["informations"])) {
        // Mise par defaut du status a REUSSIE
        $status = 'REUSSIE';

        //instance de connexion à la base
        $bdd = new BDD_Connexion();
        $dbc = $bdd->getConnexion();

        // Verification de l'existence de l'identifiant de l'evenement
        $idEvenement = $_POST["idEvenement"];
        $query = "SELECT * FROM EVENEMENT WHERE id = '" . $idEvenement . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchAll();

        if (count($results) == 0) {
            $status = 'ECHOUEE';
            echo "Evenement introuvable\n";
        }

        // Verification de l'existence du pseudo du membre
        $pseudoMembre = $_POST["pseudoMembre"];
        if ($status == 'REUSSIE') {
            $query = "SELECT * FROM MEMBRE WHERE pseudo = '" . $pseudoMembre . "'";
            $stmt = $dbc->query($query);
            $results = $stmt->fetchAll();

            if (count($results) == 0) {
                $status = 'ECHOUEE';
                echo "Membre introuvable\n";
            }
        }

        // Verification de la non existence de la participation evenement/membre
        if ($status == 'REUSSIE') {
            $query = "SELECT * FROM PARTICIPATION WHERE idEvenement = " . $idEvenement . " AND pseudoMembre = '" . $pseudoMembre . "'";
            $stmt = $dbc->query($query);
            $results = $stmt->fetchAll();

            if (count($results) != 0) {
                $status = 'ECHOUEE';
                echo "Participation Evenement/Membre deja existante\n";
            }
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
                $nbPersonnesParticipantes = intval($nbPersonnesParticipantes[0]["SUM(nbInscrit)"]);
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
        
        // Creation de l'inscription du membre a l'evenement
        if ($status == 'REUSSIE') {
            $query = "INSERT INTO PARTICIPATION (idEvenement, pseudoMembre, nbInscrit, informations) VALUES ("
            . $idEvenement . ", '" . $pseudoMembre . "', " . $nbInscrits . ", '" . $informations . "')";
            $stmt = $dbc->query($query);
            if ($stmt != false) {
                $results = $stmt->fetchAll();

                if (count($results) != 0) {
                    $status = 'ECHOUEE';
                    echo "Insertion de la participation echouee\n";
                }
            }
        }
    }

    echo json_encode(array("inscription"=>$status));
?>