<?php 
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("BDD_Connexion.php");

    $status = 'ECHOUEE';
    
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
    }

    echo json_encode(array("suppression"=>$status));
?>