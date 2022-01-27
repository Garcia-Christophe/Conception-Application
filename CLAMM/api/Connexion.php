<?php 
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("BDD_Connexion.php");

    $status = 'ECHOUEE';
    
    if (isset($_POST["email"]) && isset($_POST["mdp"])) {

        // On etablit un prefixe pour le salage
        // $prefixe = $_POST["email"];

        // On etablit un suffixe pour le salage (email a l'envers)
        // $suffixe = "";
        // for ($i = strlen($_POST["email"]) - 1; $i >= 0; $i--) {
        //     $suffixe = $suffixe . $_POST["email"][$i];
        // }

        // Hachage du mot de passe sale
        // $mdp = hash("sha256", $prefixe . $_POST["mdp"] . $suffixe);
        $mdp = $_POST["mdp"]; // sans securite (temporaire)

        // On recupere le mdp de la bdd grace a l'email
        // (le mot de passe n'existe pas si la saisie ne correspond a aucun email)
        $dbc = BDD_Connexion::getInstance()->getConnexion();
        $query = "SELECT motDePasse FROM MEMBRE WHERE mail = '" . $_POST["email"] . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchAll();
        if (count($results) > 0) {
            $mdpBDD = $results[0]['motDePasse'];
        } else {
            $mdpBDD = "";
        }

        // Verification de la validite de l'email et du mot de passe
        if ($mdp == $mdpBDD) {
            $status = 'REUSSIE';
        }
    }

    echo json_encode(array("connexion"=>$status));
?>