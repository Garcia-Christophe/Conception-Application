package testsunitaires.testsevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.evenements.Evenement;

public class SetNomEvenementTest {

  Evenement unEvenement;

  @BeforeEach
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test
  @DisplayName("Test unNom �gal � 'Repas de No�l'")
  void testCorrect() {
    assertEquals(null, unEvenement.setNom("Repas de No�l"), "Le retour devrait �tre null");
    assertEquals("Repas de No�l", unEvenement.getNom(),
        "Le nom devrait �tre �gal � 'Repas de No�l'");
  }

  @Test
  @DisplayName("Test unNom �gal � '   espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.NOM_ESPACE_EN_TROP, unEvenement.setNom("   espaces avant"),
        "Le retour devrait �tre un code d'erreur NOM_ESPACE_EN_TROP");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait �tre �gal � null");
  }

  @Test
  @DisplayName("Test unNom �gal � 'espaces apr�s    '")
  void testEspaceApres() {
    assertEquals(CodeErreur.NOM_ESPACE_EN_TROP, unEvenement.setNom("espaces apr�s    "),
        "Le retour devrait �tre un code d'erreur NOM_ESPACE_EN_TROP");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait �tre �gal � null");
  }

  @Test
  @DisplayName("Test unNom �gal � un nom avec plus de 50 caract�res (exemple 52)")
  void testCinquanteCara() {
    assertEquals(CodeErreur.NOM_TROP_LONG,
        unEvenement.setNom("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"),
        "Le retour devrait �tre un code d'erreur NOM_TROP_LONG");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait �tre �gal � null");
  }

  @Test
  @DisplayName("Test unNom �gal � null")
  void testNull() {
    assertEquals(CodeErreur.NOM_NULL, unEvenement.setNom(null),
        "Le retour devrait �tre un code d'erreur NOM_NULL");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait �tre �gal � null (inchang�)");
  }

  @Test
  @DisplayName("Test unNom vide")
  void testVide() {
    assertEquals(CodeErreur.NOM_VIDE, unEvenement.setNom(""),
        "Le retour devrait �tre un code d'erreur NOM_VIDE");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait �tre �gal � null (inchang�)");
  }
}
