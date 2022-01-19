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
  @DisplayName("Test unLieu �gal � 'Salle des f�tes, rue de la Paix, 29200 Brest'")   
  void testCorrect() {
      assertEquals(null, unEvenement.setLieu("Salle des f�tes, rue de la Paix, 29200 Brest"), "Le retour devrait �tre null");  
      assertEquals("Salle des f�tes, rue de la Paix, 29200 Brest", unEvenement.getLieu(), "Le lieu devrait �tre �gal � 'Salle des f�tes, rue de la Paix, 29200 Brest'");  
  }

  @Test                                               
  @DisplayName("Test unLieu �gal � '   espaces avant'")   
  void testEspaceAvant() {
      assertEquals(CodeErreur.LIEU_ESPACE_EN_TROP, unEvenement.setLieu("   espaces avant"), "Le retour devrait �tre un code d'erreur LIEU_ESPACE_EN_TROP");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait �tre null");  
  }
  
  @Test                                               
  @DisplayName("Test unLieu �gal � 'espaces apr�s    '")   
  void testEspaceApres() {
      assertEquals(CodeErreur.LIEU_ESPACE_EN_TROP, unEvenement.setLieu("espaces apr�s    "), "Le retour devrait �tre un code d'erreur LIEU_ESPACE_EN_TROP");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait �tre null (inchang�)");  
  }
  
  @Test                                               
  @DisplayName("Test unLieu �gal � un lieu avec plus de 100 caract�res (exemple 104)")   
  void testCentCara() {
      assertEquals(CodeErreur.LIEU_TROP_LONG, unEvenement.setLieu("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"), "Le retour devrait �tre un code d'erreur LIEU_TROP_LONG");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait �tre null (inchang�)");  
  }
  
  @Test                                               
  @DisplayName("Test unLieu �gal � null")   
  void testNull() {
      assertEquals(CodeErreur.LIEU_NULL, unEvenement.setLieu(null), "Le retour devrait �tre un code d'erreur LIEU_NULL");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait �tre null (inchang�)");  
  }
  
  @Test                                               
  @DisplayName("Test unLieu vide")   
  void testVide() {
      assertEquals(CodeErreur.LIEU_VIDE, unEvenement.setLieu(""), "Le retour devrait �tre un code d'erreur LIEU_VIDE");  
      assertEquals(null, unEvenement.getLieu(), "Le lieu devrait �tre null (inchang�)");  
  }

}
