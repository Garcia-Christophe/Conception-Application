<?php
//nb de place
    /* 
    Auteur : Manon Goasguen
    
    Lien : https://obiwan2.univ-brest.fr/licence/lic8/GetEvenements.php

    Method : POST

    Http response : 
      - 200 : success
      - 400 : erreur de paramètre
      - 405 : methode non autorisée

    Param form_data : 
        pseudo_membre : pseudo du membre connecté

    Retourne :
      - Les événements non passés où le membre n'est pas inscrit et où il reste de la place 
      - Les événements non passés où le membre est inscrit (qu'il reste de la place ou non)

    Structure d'un evenement : 
      - id
      - nom
      - descriptif
      - date
      - lieu
      - nbMaxPersonnes
      - type
      - inscrit : booleen précisant si le membre est inscrit

    */
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');

    //connection à la base
    include("./BDD_Connexion.php");
    
    //structure des évévenements
    include('./Evenement.php');

    //récupération de la méthode de la requête (GET,POST,PUT...)
    $request_method = $_SERVER["REQUEST_METHOD"];
    
    if($request_method=='POST'){
        if (isset($_POST["pseudoMembre"])){

            //variable avec la structure d'un événement
            $evenement = new Evenement();
            
            //tableau des événements
            $tableEvenements = [];

            //instance de connexion à la base
            $bdd = new BDD_Connexion();
            $dbc = $bdd->getConnexion();
            
            //requete de récupération des événements non passés
            $query = "SELECT * FROM EVENEMENT where date>=NOW()";
            $stmt = $dbc->query($query);
            $evenements = $stmt->fetchAll();
           
            for($i=0;$i<count($evenements);$i++) {
                
                //id de l'événement
                $id_evenement=$evenements[$i]['id'];
                
                //requete de récupération d'une participation correspondante à l'événement et au membre
                $pseudo_membre=$_POST['pseudoMembre'];
                $query2 = "SELECT * FROM PARTICIPATION where idEvenement=$id_evenement and pseudoMembre='$pseudo_membre'";
                $stmt2 = $dbc->query($query2);
                $participation = $stmt2->fetchAll();

                //requete de récupération du nombre de participant à un événement
                $query3 = "SELECT sum(nbInscrit) as nb FROM PARTICIPATION inner join EVENEMENT on PARTICIPATION.idEvenement=EVENEMENT.id where idEvenement=$id_evenement";
                $stmt3 = $dbc->query($query3);
                $results = $stmt3->fetchAll();
                if($results[0]['nb']==null){
                    $nbinscrits=0;
                }else{
                    $nbinscrits=$results[0]['nb'];
                }

                //s'il y a une participation (le membre est inscrit)
                if(count($participation)>0){
                    //récupération des infos
                    $evenement=[
                        "id" => $evenements[$i]['id'],
                        "nom" => $evenements[$i]['nom'],
                        "descriptif" => $evenements[$i]['descriptif'],
                        "date" => $evenements[$i]['date'],
                        "lieu" => $evenements[$i]['lieu'],
                        "nbMaxPersonnes" => $evenements[$i]['nbMaxPersonnes'],
                        "type" => $evenements[$i]['type'],
                        "inscrit" => true, //le membre est inscrit
                        "nbInscrit" => $nbinscrits
                    ];

                    //ajout dans la table
                    $tableEvenements[]=$evenement;

                } else {
                    $ajout=false; 

                    if(count($results)>0){//s'il y a des inscrits
                        if($nbinscrits<$evenements[$i]['nbMaxPersonnes']){
                            $ajout=true;
                        }
                    } else {//il n'y a pas d'inscrit
                        $ajout=true;
                    }

                    if($ajout){//il reste de la place
                        $evenement=[
                            "id" => $evenements[$i]['id'],
                            "nom" => $evenements[$i]['nom'],
                            "descriptif" => $evenements[$i]['descriptif'],
                            "date" => $evenements[$i]['date'],
                            "lieu" => $evenements[$i]['lieu'],
                            "nbMaxPersonnes" => $evenements[$i]['nbMaxPersonnes'],
                            "type" => $evenements[$i]['type'],
                            "inscrit" => false, //le membre n'est pas inscrit
                            "nbInscrit" => $nbinscrits
                        ];
                        
                        //ajout dans la table
                        $tableEvenements[]=$evenement;
                    }
                }
            }
            
            //affichage des résultats
            echo json_encode($tableEvenements);
    
            //code de réussite
            http_response_code(200);
        }else{
            http_response_code(400); //erreur pas de parametre
        }
    }else{
      // Requête invalide
      header("HTTP/1.0 405 Method Not Allowed");
    }
?>