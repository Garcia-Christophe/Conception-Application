if(localStorage.getItem("user")){//si l'utilisateur est connecté
    window.location.href="main.html" //ammène à la liste des événements
}


////////////////////////////////////////////////////////////////
//Elements de la page

//bouton de connexion
var btn = document.getElementById("btn_conn");
btn.onclick=connexion;

//pseudo
var pseudo=document.getElementById("pseudo");

//mot de passe
var mdp=document.getElementById("mdp");

//message d'erreur
var error=document.createElement("p");
error.innerHTML="Pseudo et/ou mot de passe incorrect";
error.style.color="red";
//div du formulaire pour l'affichage de l'erreur
var div=document.getElementById("connexion");



/////////////////////////////////////////////////////////////////////////////
//déclanche la fonction de connexion 

document.querySelector('#pseudo').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
      connexion();
    }
});

document.querySelector('#mdp').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
      connexion();
    }
});
//////////////////////////////////////////////////////////////////////////////


//Fonction de vérification des identifiants
function connexion(){
    
        $(document).ready(function($) {
            if(pseudo.value!="" && mdp.value!=""){ //si les champs d'identifiants sont remplis

                $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/Connexion.php", { pseudoMembre: pseudo.value, motDePasse: mdp.value })
                .done(function( data ) { //requete réussie
                    const now = new Date() //date courante
                    const item = {
                        value: data, 
                        expiry: now.getTime() + 15*6000 //date d'expiration : date courante plus 15min
                    }
                    localStorage.setItem("user", JSON.stringify(item)) //stockage du pseudo dans le LocalStorage
                    window.location.href="main.html" //envoi sur la page de la liste des événements
                })
                .fail(function() {// erreur de connexion
                    div.appendChild(error); //affiche le message d'erreur
                });
    }else{//un des champs ou les champs sont vide.s
        div.appendChild(error); //affiche l'erreur
}
    });

}   