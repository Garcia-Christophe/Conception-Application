var mydata = '[{"id": "1","nom": "evenement 1","descriptif": "descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1descriptifTest1","date": "2025-02-02 00:00:00","lieu": "lieuTest1","nbMaxPersonnes": "27","type": "AG","inscrit": true,"nbInscrit": "18"}, {"id": "2","nom": "evenement 2","descriptif": "descriptifTest2","date": "2022-02-02 00:00:00","lieu": "lieuTest2","nbMaxPersonnes": "22","type": "AG","inscrit": false,"nbInscrit": "20"}]';
var data = JSON.parse(mydata);

var divEvenement;
var checkboxInscrit;

var events;

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

    var right = document.createElement("div");
    right.classList.add("right");

    var labelNbPers = document.createElement("label");
    labelNbPers.innerHTML = "Combien de personnes serez vous ?"

    var nbPers = document.createElement("input");
    nbPers.type = "number";

    var labelCommentaire = document.createElement("label");
    labelCommentaire.innerHTML = "Un commentaire ?"

    var commentaire = document.createElement("textarea");

    var divButtons = document.createElement("div");
    var btnAnnuler = document.createElement("button");
    btnAnnuler.setAttribute("onclick","fermeEvenement(event"+id+")");
    btnAnnuler.innerHTML = "Annuler";

    var btnConfirm = document.createElement("button");
    btnConfirm.setAttribute("onclick","fermeEvenement(event"+id+")");
    if(events[i]["inscrit"] == true){
        btnConfirm.innerHTML = "Modifier";
    }else{
        btnConfirm.innerHTML = "Inscription";
    }

    divButtons.appendChild(btnAnnuler);
    divButtons.appendChild(btnConfirm);

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

document.addEventListener('DOMContentLoaded',function(){
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
});