var user=localStorage.getItem("user");  

var myData;
var events;
var divEvenement;
var checkboxInscrit;
var data;

var nbPers;
var commentaire;

var right;
var divButtons;

var error = document.createElement("p");

document.addEventListener('DOMContentLoaded',function(){
    init();
});

function init(){
    $(document).ready(function($) {
        $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/GetEvenements.php", { pseudoMembre: user })
        .done(function( result ) {
            data=result;
            divEvenement = document.getElementById("evenements");
        checkboxInscrit = document.getElementById("checkboxInscrit");

        fillEvents(false);
        afficheEvent();

        checkboxInscrit.addEventListener('change', function() {
            if (this.checked) {
                fillEvents(true);
                afficheEvent();
            } else {
                fillEvents(false);
                afficheEvent();
            }
        });
        })
        .fail(function() {
            console.log("error");
        }); 

        });
}

//filtrer evenement inscrit
function fillEvents(inscrit){
    events = [];
    if(!inscrit){
        events = data;
    }else{
        for(let i=0; i<data.length; i++){
            if(data[i]["inscrit"] == true){
                events.push(data[i]);
            }
        }
    }
}

//afficher la liste des evenements
function afficheEvent(){

    divEvenement.innerHTML = "";    

    for(let i=0; i<events.length; i++){
        var parent = document.createElement("div");
        parent.classList.add("event");

        if(events[i]["inscrit"] == true){
            parent.classList.add("inscrit");
        }

        parent.setAttribute("onclick","rechercheEvenement("+events[i]["id"]+")");

        var nom = document.createElement("h2");
        nom.innerHTML = events[i]["nom"];

        var date = document.createElement("h3");
        date.innerHTML = events[i]["date"].substring(0, 10);

        var nbPers = document.createElement("h3");
        nbPers.innerHTML = events[i]["nbInscrit"]+"/"+events[i]["nbMaxPersonnes"];

        parent.appendChild(nom);
        parent.appendChild(date);
        parent.appendChild(nbPers);

        divEvenement.appendChild(parent);
    }
}

function fermeEvenement(id){
    document.getElementById(id.id).remove();
}

function rechercheEvenement(id){
    let i=0;
    while(events[i]["id"] != id){
        i++;
    }
    
    var parent = document.createElement("div");
    parent.id = "event"+id;
    parent.classList.add("detailEvent");

    var header = document.createElement("div");
    header.classList.add("parentFerme");

    var ferme = document.createElement("div");
    ferme.classList.add("btnFerme");
    ferme.setAttribute("onclick","fermeEvenement(event"+id+")");
    ferme.innerHTML = '<img src="img/croix.png">';

    header.appendChild(ferme);

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

    left.appendChild(titre);
    left.appendChild(placesDispo);
    left.appendChild(date);
    left.appendChild(adresse);
    left.appendChild(description);

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

    if(events[i]["inscrit"] == true){
        var btnModification = document.createElement("button");
        btnModification.innerHTML = "Modifier";

        var btnDesinscription = document.createElement("button");
        btnDesinscription.innerHTML = "Desinscription";
        btnDesinscription.setAttribute("onclick","desinscription("+events[i]["id"]+")");
    }else{
        var btnInscription = document.createElement("button");
        btnInscription.innerHTML = "Inscription";
        btnInscription.setAttribute("onclick","inscription("+events[i]["id"]+","+(events[i]["nbMaxPersonnes"]-events[i]["nbInscrit"])+")");
    }

    if(events[i]["inscrit"] == true){
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

function inscription(id, nbMax){
    
    var nb= (nbPers.value=="") ? 0 : parseInt(nbPers.value);
    var comm= (commentaire.value==null) ? "" : commentaire.value;
    nbPers.value=nb;

    $(document).ready(function($) {
            $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/Inscription.php", { idEvenement : parseInt(id), pseudoMembre: user , nbInscrit : nb , informations : comm })
            .done(function( result ) {
                init();
            })
            .fail(function() {
                if(!(right.contains(error))){
                    error.style.color="red"
                    error.innerHTML="Erreur d'inscription : le nombre de personnes est trop petit (minimum 1) ou trop grand (maximum "+nbMax+") , et/ou le commentaire est trop long"
                    right.insertBefore(error,divButtons);
                }
            })
        });
}

function modification(id, nbMax){
    var nb= (nbPers.value=="") ? 0 : parseInt(nbPers.value);
    var comm= (commentaire.value==null) ? "" : commentaire.value;
    nbPers.value=nb;

    $(document).ready(function($) {
            $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/Modification.php", { idEvenement : parseInt(id), pseudoMembre: user , nbInscrit : nb , informations : comm })
            .done(function( result ) {
                console.log(result);
                init();
            })
            .fail(function() {
                if(!(right.contains(error))){
                    error.style.color="red"
                    error.innerHTML="Erreur de modification : le nombre de personnes est trop petit (minimum 1) ou trop grand (maximum "+nbMax+") , et/ou le commentaire est trop long"
                    right.insertBefore(error,divButtons);
                }
            })
        });
}

function desinscription(id){
    if (confirm('Etes-vous sûr de vouloir vous désinscrire ?')) {
        $(document).ready(function($) {
            $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/Suppression.php", { idEvenement : parseInt(id), pseudoMembre: user})
            .done(function( result ) {
                init();
            })
            .fail(function() {
                if(!(right.contains(error))){
                    error.style.color="red"
                    error.innerHTML="Erreur de désinscription"
                    right.insertBefore(error,divButtons);
                }
            })
        });
    }
    }