package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestionEvenements.Evenement;

class SetNbMaxPersonnesEvenementTest {

  Evenement unEvenement;

  @BeforeEach                                         
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test                                               
  @DisplayName("Test unEvenement �gal � 1")   
  void testPositifUn() {
      assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, unEvenement.setNbMaxPersonnes(1), "Le retour devrait �tre un code erreur NB_MAX_PERSONNES_TROP_PETIT");  
      assertEquals(0, unEvenement.getNbMaxPersonnes(), "Le nombre de personnes maximum autoris�s devrait �tre �gal � 0 (inchang�)");  
  }
  
  @Test                                               
  @DisplayName("Test unEvenement �gal � 30")   
  void testPositifTrente() {
      assertEquals(null, unEvenement.setNbMaxPersonnes(30), "Le retour devrait �tre null");  
      assertEquals(30, unEvenement.getNbMaxPersonnes(), "Le nombre de personnes maximum autoris�s devrait �tre �gal � 30");  
  }

  @Test                                    
  @DisplayName("Test unEvenement �gal � 0")
  void testZero() {
    assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, unEvenement.setNbMaxPersonnes(0), "Le retour devrait �tre un code erreur NB_MAX_PERSONNES_TROP_PETIT");  
    assertEquals(0, unEvenement.getNbMaxPersonnes(), "Le nombre de personnes maximum autoris�s devrait �tre �gal � 0 (inchang�)");  
  }
  
  @Test                                    
  @DisplayName("Test unEvenement �gal � -2")
  void testNegatifDeux() {
    assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, unEvenement.setNbMaxPersonnes(-2), "Le retour devrait �tre un code erreur NB_MAX_PERSONNES_TROP_PETIT");  
    assertEquals(0, unEvenement.getNbMaxPersonnes(), "Le nombre de personnes maximum autoris�s devrait �tre �gal � 0 (inchang�)");  
  }

}
