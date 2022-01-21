package testsIntegration.testsGestion;

import gestion.CodeErreur;
import gestion.Gestion;
import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class testIntegrationGestionEvenements {
  static Gestion uneGestion;

  @Test
  void tests() {
    uneGestion=new Gestion();
    
    // liste vide
    assertEquals(0, uneGestion.getListeEvenements().size(), "La liste devrait être vide");

    //Test correct : ajout d'un événement
    assertEquals(null, uneGestion.ajouterEvenement("Un événement", "Description de l’événement", "url",new Date(2022-1900,06,27,15,30),"adresse",500, TypeEvenement.REPAS), "Le retour devrait être null");  
  
    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait être 1");
    assertEquals("Un événement", uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait être 'Un événement'");
    assertEquals("url", uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait être 'url'");
    assertEquals(new Date(2022-1900,06,27,15,30), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait être '27/06/22 15:30'");
    assertEquals("adresse", uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait être 'adresse'");
    assertEquals(500, uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait être 500");
    assertEquals(TypeEvenement.REPAS, uneGestion.getListeEvenements().get(0).getType(),"Le type devrait être REPAS");
    
    //Test incorrect : nom vide et date null
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.NOM_VIDE);
    codes.add(CodeErreur.DATE_NULL);
    assertEquals(codes, uneGestion.ajouterEvenement("", "Description de l’événement", "url",null,"adresse",500, TypeEvenement.REPAS), "Le retour devrait être une ligne de codes erreurs : [NOM_VIDE, DATE_NULL]");
    assertTrue(uneGestion.getEvenement(2)==null,"l'événement id 2 ne devrait pas etre trouvé");
  
    //Test correct : ajout d'un second événement
    assertEquals(null, uneGestion.ajouterEvenement("ag", "assemblée générale", "url",new Date(2022-1900,06,8,12,45),"bureau",20, TypeEvenement.AG), "Le retour devrait être null");  
    
    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait être 1");
    assertEquals("Un événement", uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait être 'Un événement'");
    assertEquals("url", uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait être 'url'");
    assertEquals(new Date(2022-1900,06,27,15,30), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait être '27/06/22 15:30'");
    assertEquals("adresse", uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait être 'adresse'");
    assertEquals(500, uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait être 500");
    assertEquals(TypeEvenement.REPAS, uneGestion.getListeEvenements().get(0).getType(),"Le type devrait être REPAS");
    
    assertEquals(2, uneGestion.getListeEvenements().get(1).getId(),"L'identifiant devrait être 2");
    assertEquals("ag", uneGestion.getListeEvenements().get(1).getNom(),"Le nom devrait être 'ag'");
    assertEquals("url", uneGestion.getListeEvenements().get(1).getImage(),"L'image devrait être 'url'");
    assertEquals(new Date(2022-1900,06,8,12,45), uneGestion.getListeEvenements().get(1).getDate(),"La date devrait être '08/06/22 12:45'");
    assertEquals("bureau", uneGestion.getListeEvenements().get(1).getLieu(),"Le lieu devrait être 'bureau'");
    assertEquals(20, uneGestion.getListeEvenements().get(1).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait être 20");
    assertEquals(TypeEvenement.AG, uneGestion.getListeEvenements().get(1).getType(),"Le type devrait être AG");
  
   //Test correct : modifier événement
    Evenement tmp = uneGestion.getEvenement(1);
    assertEquals(null, uneGestion.modifierEvenement(1,"nom", tmp.getDescriptif(), tmp.getImage(),tmp.getDate(),tmp.getLieu(),200, tmp.getType()), "Le retour devrait être null");  
    
    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait être 1");
    assertEquals("nom", uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait être 'nom'");
    assertEquals(tmp.getDescriptif(), uneGestion.getListeEvenements().get(0).getDescriptif(),"Le descriptif devrait être 'Description de l'évènement'");
    assertEquals(tmp.getImage(), uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait être 'url'");
    assertEquals(tmp.getDate(), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait être '27/06/22 15:30'");
    assertEquals(tmp.getLieu(), uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait être 'adresse'");
    assertEquals(200, uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait être 200");
    assertEquals(tmp.getType(), uneGestion.getListeEvenements().get(0).getType(),"Le type devrait être REPAS");
    
    //Test incorrect : modifier evenement avec un id positif inexistant
    codes = new ArrayList<CodeErreur>(); 
    codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
    assertEquals(codes, uneGestion.modifierEvenement(3,"nom", "Description de l’événement", "url",new Date(2022-1900,06,27,15,30),"adresse",200, TypeEvenement.REPAS), "Le retour devrait être CodeErreur.EVENEMENT_INTROUVABLE");  
    
    //Test incorrect : Modifier : Le nombre maximum de personnes autorisés est négatif
    codes = new ArrayList<CodeErreur>(); 
    codes.add(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT);
    tmp = uneGestion.getEvenement(1);
    assertEquals(codes, uneGestion.modifierEvenement(1,tmp.getNom(), tmp.getDescriptif(), tmp.getImage(),tmp.getDate(),tmp.getLieu(),-500, tmp.getType()), "Le retour devrait être CodeErreur.NB_MAX_PERSONNES_TROP_PETIT");
    
    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait être 1");
    assertEquals(tmp.getNom(), uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait être 'Un évènement'");
    assertEquals(tmp.getDescriptif(), uneGestion.getListeEvenements().get(0).getDescriptif(),"Le descriptif devrait être 'Description de l'évènement'");
    assertEquals(tmp.getImage(), uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait être 'url'");
    assertEquals(tmp.getDate(), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait être '27/06/22 15:30'");
    assertEquals(tmp.getLieu(), uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait être 'adresse'");
    assertEquals(tmp.getNbMaxPersonnes(), uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait être 500");
    assertEquals(tmp.getType(), uneGestion.getListeEvenements().get(0).getType(),"Le type devrait être REPAS");
  
    //Test correct : supprimer événement id 2 
    assertEquals(null, uneGestion.supprimerEvenement(2), "Le retour devrait être null");
    assertTrue(uneGestion.getEvenement(1)!=null,"l'événement id 1 devrait etre trouvé");
    assertTrue(uneGestion.getEvenement(2)==null,"l'événement id 2 ne devrait pas etre trouvé");
    
    //Test incorrect : Evenement avec un id nul inexistant
     codes = new ArrayList<CodeErreur>(); 
      codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
      assertEquals(codes, uneGestion.supprimerEvenement(0), "Le retour devrait être null");
      assertTrue(uneGestion.getEvenement(1)!=null,"l'événement id 1 devrait etre trouvé");
      
     //Test correct get
     assertEquals(uneGestion.getListeEvenements().get(0), uneGestion.getEvenement(1), "Le retour devrait être égale à l'évenement de listeEvenements d'id 1");  
    
     //Test id inexistant positif, événement 2 supprimé
     assertEquals(null, uneGestion.getEvenement(2), "Le retour devrait être null");  
  }
}