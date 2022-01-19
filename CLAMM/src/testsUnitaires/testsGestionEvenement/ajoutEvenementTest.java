package testsUnitaires.testsGestionEvenement;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Gestion;
import gestionEvenements.TypeEvenement;

class ajoutEvenementTest {

    Gestion uneGestion;

    @BeforeEach                                         
    void setUp() {
      uneGestion = new Gestion();
    }

    @Test                                               
    @DisplayName("Test correct : ajout de deux événement")   
    void testCorrect() {
      //ajout premier événement
      assertEquals(null, uneGestion.ajouterEvenement("Un événement", "Description de l’événement", "url",new Date(2022-1900,06,27,15,30),"adresse",500, TypeEvenement.REPAS), "Le retour devrait être null");  
      
      assertEquals(1, uneGestion.getListeEvenements().get(0).getId(),"L'identifiant devrait être 1");
      assertEquals("Un événement", uneGestion.getListeEvenements().get(0).getNom(),"Le nom devrait être 'Un événement'");
      assertEquals("url", uneGestion.getListeEvenements().get(0).getImage(),"L'image devrait être 'url'");
      assertEquals(new Date(2022-1900,06,27,15,30), uneGestion.getListeEvenements().get(0).getDate(),"La date devrait être '27/06/22 15:30'");
      assertEquals("adresse", uneGestion.getListeEvenements().get(0).getLieu(),"Le lieu devrait être 'adresse'");
      assertEquals(500, uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait être 500");
      assertEquals(TypeEvenement.REPAS, uneGestion.getListeEvenements().get(0).getType(),"Le type devrait être REPAS");
      
      //ajout second événement
      assertEquals(null, uneGestion.ajouterEvenement("ag", "assemblée générale", "url",new Date(2022-1900,06,8,12,45),"bureau",20, TypeEvenement.AG), "Le retour devrait être null");  
      
      assertEquals(2, uneGestion.getListeEvenements().get(1).getId(),"L'identifiant devrait être 2");
      assertEquals("ag", uneGestion.getListeEvenements().get(1).getNom(),"Le nom devrait être 'ag'");
      assertEquals("url", uneGestion.getListeEvenements().get(1).getImage(),"L'image devrait être 'url'");
      assertEquals(new Date(2022-1900,06,8,12,45), uneGestion.getListeEvenements().get(1).getDate(),"La date devrait être '08/06/22 12:45'");
      assertEquals("bureau", uneGestion.getListeEvenements().get(1).getLieu(),"Le lieu devrait être 'bureau'");
      assertEquals(20, uneGestion.getListeEvenements().get(1).getNbMaxPersonnes(),"Le nombre de personnes maximun devrait être 20");
      assertEquals(TypeEvenement.AG, uneGestion.getListeEvenements().get(1).getType(),"Le type devrait être AG");
    }
   
    @Test                                               
    @DisplayName("Test incorrect : nombre de personnes maximun trop petit")   
    void testNbMaxPersonnesTropPetit() {
      ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
      codes.add(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT);
      assertEquals(codes, uneGestion.ajouterEvenement("Un événement", "Description de l’événement", "url",new Date(2022-1900,06,27,15,30),"adresse",-90, TypeEvenement.REPAS), "Le retour devrait être une ligne de codes erreurs : [NB_MAX_PERSONNES_TROP_PETIT]");  
      assertTrue(uneGestion.getListeEvenements().isEmpty(),"La liste des événements devrait être vide");
    }
    
    @Test                                               
    @DisplayName("Test incorrect : nom vide")   
    void testNomVide() {
      ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
      codes.add(CodeErreur.NOM_VIDE);
      assertEquals(codes, uneGestion.ajouterEvenement("", "Description de l’événement", "url",new Date(2022-1900,06,27,15,30),"adresse",500, TypeEvenement.REPAS), "Le retour devrait être une ligne de codes erreurs : [NOM_VIDE]");  
      assertTrue(uneGestion.getListeEvenements().isEmpty(),"La liste des événements devrait être vide");
    }
    
    @Test                                               
    @DisplayName("Test incorrect : nom vide et date null")   
    void testNomVideEtDateNull() {
      ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
      codes.add(CodeErreur.NOM_VIDE);
      codes.add(CodeErreur.DATE_NULL);
      assertEquals(codes, uneGestion.ajouterEvenement("", "Description de l’événement", "url",null,"adresse",500, TypeEvenement.REPAS), "Le retour devrait être une ligne de codes erreurs : [NOM_VIDE, DATE_NULL]");  
      assertTrue(uneGestion.getListeEvenements().isEmpty(),"La liste des événements devrait être vide");
    }
}