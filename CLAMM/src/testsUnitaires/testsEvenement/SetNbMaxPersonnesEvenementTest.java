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
  @DisplayName("Test unEvenement égal à 1")   
  void testPositifUn() {
      assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, unEvenement.setNbMaxPersonnes(1), "Le retour devrait être un code erreur NB_MAX_PERSONNES_TROP_PETIT");  
      assertEquals(0, unEvenement.getNbMaxPersonnes(), "Le nombre de personnes maximum autorisés devrait être égal à 0 (inchangé)");  
  }
  
  @Test                                               
  @DisplayName("Test unEvenement égal à 30")   
  void testPositifTrente() {
      assertEquals(null, unEvenement.setNbMaxPersonnes(30), "Le retour devrait être null");  
      assertEquals(30, unEvenement.getNbMaxPersonnes(), "Le nombre de personnes maximum autorisés devrait être égal à 30");  
  }

  @Test                                    
  @DisplayName("Test unEvenement égal à 0")
  void testZero() {
    assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, unEvenement.setNbMaxPersonnes(0), "Le retour devrait être un code erreur NB_MAX_PERSONNES_TROP_PETIT");  
    assertEquals(0, unEvenement.getNbMaxPersonnes(), "Le nombre de personnes maximum autorisés devrait être égal à 0 (inchangé)");  
  }
  
  @Test                                    
  @DisplayName("Test unEvenement égal à -2")
  void testNegatifDeux() {
    assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, unEvenement.setNbMaxPersonnes(-2), "Le retour devrait être un code erreur NB_MAX_PERSONNES_TROP_PETIT");  
    assertEquals(0, unEvenement.getNbMaxPersonnes(), "Le nombre de personnes maximum autorisés devrait être égal à 0 (inchangé)");  
  }

}
