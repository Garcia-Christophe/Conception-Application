<?php 
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    include_once("BDD_Connexion.php");

    $status = 'ECHOUEE';
    
    if (isset($_POST["email"]) && isset($_POST["mdp"])) {

        // On établit un prefixe pour le salage
        // $prefixe = $_POST["email"];

        // On établit un suffixe pour le salage (email à l'envers)
        // $suffixe = "";
        // for ($i = strlen($_POST["email"]) - 1; $i >= 0; $i--) {
        //     $suffixe = $suffixe . $_POST["email"][$i];
        // }

        // Hachage du mot de passe salé
        // $mdp = hash("sha256", $prefixe . $_POST["mdp"] . $suffixe);
        $mdp = $_POST["mdp"]; // sans sécurité (temporaire)

        // On récupère le mdp de la bdd dont l'email est le hash de l'email saisi
        // (le mot de passe n'existe pas si la saisie n'est pas le bon email)
        $dbc = BDD_Connexion::getInstance()->getConnexion();
        $query = "SELECT motDePasse FROM MEMBRE WHERE mail = '" . $_POST["email"] . "'";
        $stmt = $dbc->query($query);
        $results = $stmt->fetchAll();
        if (count($results) > 0) {
            $mdpBDD = $results[0]['motDePasse'];
        } else {
            $mdpBDD = "";
        }

        // Vérification de la validité de l'email et du mot de passe
        if ($mdp == $mdpBDD) {
            $status = 'REUSSIE';
        }
    }

    echo json_encode(array("connexion"=>$status));
?>