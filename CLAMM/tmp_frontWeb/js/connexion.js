var btn = document.getElementById("btn_conn");
btn.onclick=connexion;
var pseudo=document.getElementById("pseudo");
var mdp=document.getElementById("mdp");
var error=document.createElement("p");
error.innerHTML="Pseudo et/ou mot de passe incorrect";
error.style.color="red";
var div=document.getElementById("connexion");
var user="";

function connexion(){
    
        $(document).ready(function($) {
            if(pseudo.value!="" && mdp.value!=""){
                $.post( "https://obiwan2.univ-brest.fr/licence/lic8/api/Connexion.php", { pseudoMembre: pseudo.value, motDePasse: mdp.value })
                .done(function( data ) {
                    user=data;
                    localStorage.setItem("user", user); 
                    window.location.href="main.html"
                })
                .fail(function() {
                    div.appendChild(error);
                });
    }else{
        div.appendChild(error);
}
    });

}   