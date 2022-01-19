package testsUnitaires.testsEvenement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestionEvenements.Evenement;

class SetLieuEvenementTest {

  Evenement unEvenement;

  @BeforeEach                                         
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test                                               
  @DisplayName("Test unLieu égal à 'Salle des fêtes, rue de la Paix, 29200 Brest'")   
  void testCorrect() {
      assertEquals(null, unEvenement.setLieu("Salle des fêtes, rue de la Paix, 29200 Brest"), "Le retour devrait être null");  
      assertEquals("Salle des fêtes, rue de la Paix, 29200 Brest", unEvenement.getLieu(), "Le lieu devrait être égal à 'Salle des fêtes, rue de la Paix, 29200 Brest'");  
  }

  @Test                                               
  @DisplayName("Test unLieu égal à '   espaces avant'")   
  void testEspaceAvant() {
      assertEquals(CodeErreur.LIEU_ESPACE_EN_TROP, unEvenement.setLieu("   espaces avant"), "Le retour devrait être un code d'erreur LIEU_ESPACE_EN_TROP");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait être null");  
  }
  
  @Test                                               
  @DisplayName("Test unLieu égal à 'espaces après    '")   
  void testEspaceApres() {
      assertEquals(CodeErreur.LIEU_ESPACE_EN_TROP, unEvenement.setLieu("espaces après    "), "Le retour devrait être un code d'erreur LIEU_ESPACE_EN_TROP");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait être null (inchangé)");  
  }
  
  @Test                                               
  @DisplayName("Test unLieu égal à un lieu avec plus de 100 caractères (exemple 104)")   
  void testCentCara() {
      assertEquals(CodeErreur.LIEU_TROP_LONG, unEvenement.setLieu("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"), "Le retour devrait être un code d'erreur LIEU_TROP_LONG");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait être null (inchangé)");  
  }
  
  @Test                                               
  @DisplayName("Test unLieu égal à null")   
  void testNull() {
      assertEquals(CodeErreur.LIEU_NULL, unEvenement.setLieu(null), "Le retour devrait être un code d'erreur LIEU_NULL");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait être null (inchangé)");  
  }
  
  @Test                                               
  @DisplayName("Test unLieu vide")   
  void testVide() {
      assertEquals(CodeErreur.LIEU_VIDE, unEvenement.setLieu(""), "Le retour devrait être un code d'erreur LIEU_VIDE");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait être null (inchangé)");  
  }

}
