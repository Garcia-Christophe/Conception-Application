
//////////////////////////////////////////////////
//Variables

//liste événement
var data; //liste des événements
var events; //liste des événements affichés 
var divEvenement; //div contenant la liste des événements

var checkboxInscrit; //checkbox pour n'afficher que les éléments auxquels l'utilisateur est inscrit ou inversement

//formulaire inscription
var nbPers; //input nombre de personne
var commentaire; //input commentaire

var right; //formulaire inscription
var divButtons; //div des boutons pour gestion des inscriptions

var error = document.createElement("p");//message d'erreur



//////////////////////////////////////////////////
//A chaque premier chargement de la page

//variable stockant le pseudo de l'utilisateur
var user;


if(!(localStorage.getItem("user")) || user==""){ //l'utilisateur n'est pas connecté
    //retour à la page de connexion
    window.location.href="index.html"
}

//récupération du pseudo et vérification de la présence du membre dans la base
verifConnexion();

//Message de biencenue
divBienvenue = document.getElementById("bienvenue");
textBienvenue = document.createElement("h3");
textBienvenue.innerHTML="Bienvenue "+user;
divBienvenue.appendChild(textBienvenue);

//Bouton de deconnexion
var btn_deconnexion = document.getElementById("deconnexion");
btn_deconnexion.onclick=deconnexion;


///////////////////////////////////////////////////////
//A chaque rafraichissement de page

document.addEventListener('DOMContentLoaded',function(){
    init();
});


///////////////////////////////////////////////////////
//Fonctions

//Fonction de deconnexion de l'utilisateur
function deconnexion(){
    //localStorage.removeItem("user");  
    user=""; //vide la variable contenant le pseudo
    delete localStorage.user; //supprime le pseudo du LocalStorage
    window.location.href="index.html" //renvoi sur la page de connexion
}

//initialisation de la liste des événements
function init(){
    $(document).ready(function($) {
        $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/GetEvenements.php", { pseudoMembre: user })
        .done(function( result ) { //requete reussie
            data=result;
            divEvenement = document.getElementById("evenements");
            checkboxInscrit = document.getElementById("checkboxInscrit");
            

            fillEvents(false); //tous les événements (inscrit ou non)
            afficheEvent(); //affichage des événements 

            checkboxInscrit.addEventListener('change', function() { //handler de la checkbox
                if (this.checked) {
                    fillEvents(true);
                    afficheEvent();
                } else {
                    fillEvents(false);
                    afficheEvent();
                }
            });
        })
        .fail(function() { //requete échouée
            console.log("error"); //affichage du message d'erreur
        }); 

        });
}

//filtrer evenement inscrit
function fillEvents(inscrit){
    events = [];
    if(!inscrit){ //si on ne filtre pas
        events = data; //tous les événements
    }else{//sinon
        for(let i=0; i<data.length; i++){
            if(data[i]["inscrit"] == true){ //on filtre
                events.push(data[i]);
            }
        }
    }
}

//afficher la liste des evenements
function afficheEvent(){

    divEvenement.innerHTML = "";    //vide la liste

    for(let i=0; i<events.length; i++){ //pour tous les événements à afficher
        //création d'une ligne
        var parent = document.createElement("div"); 
        parent.classList.add("event");

        //si inscrit on indique
        if(events[i]["inscrit"] == true){
            parent.classList.add("inscrit");
        }

        //action quand on clique
        parent.setAttribute("onclick","rechercheEvenement("+events[i]["id"]+")");

        //nom
        var nom = document.createElement("h2");
        nom.innerHTML = events[i]["nom"];

        //date
        var date = document.createElement("h3");
        date.innerHTML = events[i]["date"].substring(0, 10);

        //nombre de personne
        var nbPers = document.createElement("h3");
        nbPers.innerHTML = events[i]["nbInscrit"]+"/"+events[i]["nbMaxPersonnes"];

        //on ajoute les éléments à la ligne
        parent.appendChild(nom);
        parent.appendChild(date);
        parent.appendChild(nbPers);

        //on ajoute la ligne à la liste
        divEvenement.appendChild(parent);
    }
}

//ferme la page de detail d'un événement
function fermeEvenement(id){
    verifConnexion();
    document.getElementById(id.id).remove();
}

//ouvre la page de detail d'un événement
function rechercheEvenement(id){
    verifConnexion();

    //cherche l'événement à afficher
    let i=0;
    while(events[i]["id"] != id){
        i++;
    }

    ///////////////////////////////////////////////
    //page
    
    var parent = document.createElement("div");
    parent.id = "event"+id;
    parent.classList.add("detailEvent");

    var header = document.createElement("div");
    header.classList.add("parentFerme");

    //bouton pour fermer

    var ferme = document.createElement("div");
    ferme.classList.add("btnFerme");
    ferme.setAttribute("onclick","fermeEvenement(event"+id+")");
    ferme.innerHTML = '<img src="img/croix.png">';

    header.appendChild(ferme);


    ///////////////////////////////////////////////
    //infos sur l'événement
    var left = document.createElement("div");
    left.classList.add("left");

    var titre = document.createElement("h2");
    titre.innerHTML = events[i]["nom"];

    var placesDispo = document.createElement("p");
    placesDispo.innerHTML = (events[i]["nbMaxPersonnes"]-events[i]["nbInscrit"])+" places disponibles";

    var date = document.createElement("p");
    date.innerHTML = "Evènement prévu le "+events[i]["date"].substring(0, 10);

    var adresse = document.createElement("p");
    adresse.innerHTML = events[i]["lieu"]+' <a target="_blank" href="https://www.google.com/maps/place/'+events[i]["lieu"]+'">(voir sur maps)</a>';

    var description = document.createElement("textarea");
    description.readOnly = true;
    description.innerHTML = events[i]["descriptif"];

    //ajout des éléments à la page

    left.appendChild(titre);
    left.appendChild(placesDispo);
    left.appendChild(date);
    left.appendChild(adresse);
    left.appendChild(description);

    ///////////////////////////////////////////////
    //formulaire inscription
    right = document.createElement("div");
    right.classList.add("right");

    var labelNbPers = document.createElement("label");
    labelNbPers.innerHTML = "Combien de personnes serez vous ?"

    nbPers = document.createElement("input");
    nbPers.type = "number";

    var labelCommentaire = document.createElement("label");
    labelCommentaire.innerHTML = "Un commentaire ?"

    commentaire = document.createElement("textarea");

    divButtons = document.createElement("div");
    var btnAnnuler = document.createElement("button");
    btnAnnuler.setAttribute("onclick","fermeEvenement(event"+id+")");
    btnAnnuler.innerHTML = "Annuler";

   
    if(events[i]["inscrit"] == true){ //si inscrit
        //bouton modif
        var btnModification = document.createElement("button");
        btnModification.innerHTML = "Modifier";

        //bouton désinscription
        var btnDesinscription = document.createElement("button");
        btnDesinscription.innerHTML = "Desinscription";
        btnDesinscription.setAttribute("onclick","desinscription("+events[i]["id"]+")");
    }else{ //sinon
        //bouton inscription
        var btnInscription = document.createElement("button");
        btnInscription.innerHTML = "Inscription";
        btnInscription.setAttribute("onclick","inscription("+events[i]["id"]+","+(events[i]["nbMaxPersonnes"]-events[i]["nbInscrit"])+")");
    }


    if(events[i]["inscrit"] == true){//si inscrit
        //récupération des infos sur l'inscription
        $(document).ready(function($) {
            $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/GetParticipation.php", { pseudoMembre: user , idEvenement : events[i]["id"]})
            .done(function( result ) {
                nbPers.value=result["nbInscrit"]
                commentaire.value=result["informations"]
                btnModification.setAttribute("onclick","modification("+events[i]["id"]+","+(events[i]["nbMaxPersonnes"]-events[i]["nbInscrit"]+parseInt(nbPers.value))+")");
            })
            .fail(function() {
                console.log("error");
            })
        });
    }

    //ajout des éléments au formulaire

    divButtons.appendChild(btnAnnuler);
    if(events[i]["inscrit"] == true){
        divButtons.appendChild(btnModification);
        divButtons.appendChild(btnDesinscription);
    }else{
        divButtons.appendChild(btnInscription);
    }
    
    right.appendChild(labelNbPers);
    right.appendChild(nbPers);
    right.appendChild(labelCommentaire);
    right.appendChild(commentaire);
    right.appendChild(divButtons);


    parent.appendChild(header);
    parent.appendChild(left);
    parent.appendChild(right);

    divEvenement.appendChild(parent);
}


//inscription d'un membre à un événement
function inscription(id, nbMax){
    verifConnexion();

    //récup des champs du formulaire
    var nb= (nbPers.value=="") ? 0 : parseInt(nbPers.value);
    var comm= (commentaire.value==null) ? "" : commentaire.value;
    nbPers.value=nb;

    $(document).ready(function($) {
            $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/Inscription.php", { idEvenement : parseInt(id), pseudoMembre: user , nbInscrit : nb , informations : comm })
            .done(function( result ) { //requete réussie 
                init(); //retour à la liste
            })
            .fail(function() {//echouée
                //affichage du message d'erreur
                if(!(right.contains(error))){
                    error.innerHTML="Erreur d'inscription : le nombre de personnes est trop petit (minimum 1) ou trop grand (maximum "+nbMax+") , et/ou le commentaire est trop long"
                    error.classList.add("erreurForm");
                    right.insertBefore(error,divButtons);
                }
            })
        });
}

//modification d'une participation d'un membre à un événement
function modification(id, nbMax){
    verifConnexion();

    //récup des champs du formulaire
    var nb= (nbPers.value=="") ? 0 : parseInt(nbPers.value);
    var comm= (commentaire.value==null) ? "" : commentaire.value;
    nbPers.value=nb;

    $(document).ready(function($) {
            $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/Modification.php", { idEvenement : parseInt(id), pseudoMembre: user , nbInscrit : nb , informations : comm })
            .done(function( result ) {//requete réussie 
                init(); //retour à la liste
            })
            .fail(function() {//echouée
                //affichage du message d'erreur
                if(!(right.contains(error))){
                    error.innerHTML="Erreur de modification : le nombre de personnes est trop petit (minimum 1) ou trop grand (maximum "+nbMax+") , et/ou le commentaire est trop long"
                    error.classList.add("erreurForm");
                    right.insertBefore(error,divButtons);
                }
            })
        });
}

//désinscription d'un membre d'un événément
function desinscription(id){
    verifConnexion();
    if (confirm('Etes-vous sûr de vouloir vous désinscrire ?')) {
        $(document).ready(function($) {
            $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/Suppression.php", { idEvenement : parseInt(id), pseudoMembre: user})
            .done(function( result ) {//requete réussie 
                init(); //retour à la liste
            })
            .fail(function() {//echouée
                //affichage du message d'erreur
                if(!(right.contains(error))){
                    error.style.color="red"
                    error.innerHTML="Erreur de désinscription"
                    right.insertBefore(error,divButtons);
                }
            })
        });
    }


    }


    ////récupération du pseudo et vérification de la présence du membre dans la base
function verifConnexion(){
    //recup de l'element
    const itemStr = localStorage.getItem("user")

    //le pseudo
    const item = JSON.parse(itemStr)

    //date courante
    const now = new Date()
            
    //si la données est expirée
    if (now.getTime() > item.expiry) {
        deconnexion();
    }

    //sinon

    //recup du pseudo
    user=item.value;

    //verification de la présence du membre dans la base
    $(document).ready(function($) {
        $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/VerifConnexion.php", {pseudoMembre: user})
        .done(function( result ) {//réussie
            if(result==0){ //sinon non présent (présent = 1)
                deconnexion();
            }
        })
        .fail(function() { //erreur
            deconnexion();
        })
    });
}