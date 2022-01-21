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
    assertEquals(0, uneGestion.getListeEvenements().size(), "La liste devrait �tre vide");

    //Test correct : ajout d'un �v�nement
    assertEquals(null, uneGestion.ajouterEvenement("Un �v�nement", "Description de l��v�nement", "url",new Date(2022-1900,06,27,15,30),"adresse",500, TypeEvenement.REPAS), "Le retour devrait �tre null");  
  
    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait �tre 1");
    assertEquals("Un �v�nement", uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait �tre 'Un �v�nement'");
    assertEquals("url", uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait �tre 'url'");
    assertEquals(new Date(2022-1900,06,27,15,30), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait �tre '27/06/22 15:30'");
    assertEquals("adresse", uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait �tre 'adresse'");
    assertEquals(500, uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait �tre 500");
    assertEquals(TypeEvenement.REPAS, uneGestion.getListeEvenements().get(0).getType(),"Le type devrait �tre REPAS");
    
    //Test incorrect : nom vide et date null
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.NOM_VIDE);
    codes.add(CodeErreur.DATE_NULL);
    assertEquals(codes, uneGestion.ajouterEvenement("", "Description de l��v�nement", "url",null,"adresse",500, TypeEvenement.REPAS), "Le retour devrait �tre une ligne de codes erreurs : [NOM_VIDE, DATE_NULL]");
    assertTrue(uneGestion.getEvenement(2)==null,"l'�v�nement id 2 ne devrait pas etre trouv�");
  
    //Test correct : ajout d'un second �v�nement
    assertEquals(null, uneGestion.ajouterEvenement("ag", "assembl�e g�n�rale", "url",new Date(2022-1900,06,8,12,45),"bureau",20, TypeEvenement.AG), "Le retour devrait �tre null");  
    
    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait �tre 1");
    assertEquals("Un �v�nement", uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait �tre 'Un �v�nement'");
    assertEquals("url", uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait �tre 'url'");
    assertEquals(new Date(2022-1900,06,27,15,30), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait �tre '27/06/22 15:30'");
    assertEquals("adresse", uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait �tre 'adresse'");
    assertEquals(500, uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait �tre 500");
    assertEquals(TypeEvenement.REPAS, uneGestion.getListeEvenements().get(0).getType(),"Le type devrait �tre REPAS");
    
    assertEquals(2, uneGestion.getListeEvenements().get(1).getId(),"L'identifiant devrait �tre 2");
    assertEquals("ag", uneGestion.getListeEvenements().get(1).getNom(),"Le nom devrait �tre 'ag'");
    assertEquals("url", uneGestion.getListeEvenements().get(1).getImage(),"L'image devrait �tre 'url'");
    assertEquals(new Date(2022-1900,06,8,12,45), uneGestion.getListeEvenements().get(1).getDate(),"La date devrait �tre '08/06/22 12:45'");
    assertEquals("bureau", uneGestion.getListeEvenements().get(1).getLieu(),"Le lieu devrait �tre 'bureau'");
    assertEquals(20, uneGestion.getListeEvenements().get(1).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait �tre 20");
    assertEquals(TypeEvenement.AG, uneGestion.getListeEvenements().get(1).getType(),"Le type devrait �tre AG");
  
   //Test correct : modifier �v�nement
    Evenement tmp = uneGestion.getEvenement(1);
    assertEquals(null, uneGestion.modifierEvenement(1,"nom", tmp.getDescriptif(), tmp.getImage(),tmp.getDate(),tmp.getLieu(),200, tmp.getType()), "Le retour devrait �tre null");  
    
    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait �tre 1");
    assertEquals("nom", uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait �tre 'nom'");
    assertEquals(tmp.getDescriptif(), uneGestion.getListeEvenements().get(0).getDescriptif(),"Le descriptif devrait �tre 'Description de l'�v�nement'");
    assertEquals(tmp.getImage(), uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait �tre 'url'");
    assertEquals(tmp.getDate(), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait �tre '27/06/22 15:30'");
    assertEquals(tmp.getLieu(), uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait �tre 'adresse'");
    assertEquals(200, uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait �tre 200");
    assertEquals(tmp.getType(), uneGestion.getListeEvenements().get(0).getType(),"Le type devrait �tre REPAS");
    
    //Test incorrect : modifier evenement avec un id positif inexistant
    codes = new ArrayList<CodeErreur>(); 
    codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
    assertEquals(codes, uneGestion.modifierEvenement(3,"nom", "Description de l��v�nement", "url",new Date(2022-1900,06,27,15,30),"adresse",200, TypeEvenement.REPAS), "Le retour devrait �tre CodeErreur.EVENEMENT_INTROUVABLE");  
    
    //Test incorrect : Modifier : Le nombre maximum de personnes autoris�s est n�gatif
    codes = new ArrayList<CodeErreur>(); 
    codes.add(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT);
    tmp = uneGestion.getEvenement(1);
    assertEquals(codes, uneGestion.modifierEvenement(1,tmp.getNom(), tmp.getDescriptif(), tmp.getImage(),tmp.getDate(),tmp.getLieu(),-500, tmp.getType()), "Le retour devrait �tre CodeErreur.NB_MAX_PERSONNES_TROP_PETIT");
    
    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait �tre 1");
    assertEquals(tmp.getNom(), uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait �tre 'Un �v�nement'");
    assertEquals(tmp.getDescriptif(), uneGestion.getListeEvenements().get(0).getDescriptif(),"Le descriptif devrait �tre 'Description de l'�v�nement'");
    assertEquals(tmp.getImage(), uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait �tre 'url'");
    assertEquals(tmp.getDate(), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait �tre '27/06/22 15:30'");
    assertEquals(tmp.getLieu(), uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait �tre 'adresse'");
    assertEquals(tmp.getNbMaxPersonnes(), uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait �tre 500");
    assertEquals(tmp.getType(), uneGestion.getListeEvenements().get(0).getType(),"Le type devrait �tre REPAS");
  
    //Test correct : supprimer �v�nement id 2 
    assertEquals(null, uneGestion.supprimerEvenement(2), "Le retour devrait �tre null");
    assertTrue(uneGestion.getEvenement(1)!=null,"l'�v�nement id 1 devrait etre trouv�");
    assertTrue(uneGestion.getEvenement(2)==null,"l'�v�nement id 2 ne devrait pas etre trouv�");
    
    //Test incorrect : Evenement avec un id nul inexistant
     codes = new ArrayList<CodeErreur>(); 
      codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
      assertEquals(codes, uneGestion.supprimerEvenement(0), "Le retour devrait �tre null");
      assertTrue(uneGestion.getEvenement(1)!=null,"l'�v�nement id 1 devrait etre trouv�");
      
     //Test correct get
     assertEquals(uneGestion.getListeEvenements().get(0), uneGestion.getEvenement(1), "Le retour devrait �tre �gale � l'�venement de listeEvenements d'id 1");  
    
     //Test id inexistant positif, �v�nement 2 supprim�
     assertEquals(null, uneGestion.getEvenement(2), "Le retour devrait �tre null");  
  }
}