package testsunitaires.testsevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.evenements.Evenement;

public class SetDescriptifEvenementTest {

  Evenement unEvenement;

  @BeforeEach
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test
  @DisplayName("Test unDescriptif �gal � 'Organisation d�un repas de no�l pour l�ann�e 2022. Au programme un repas et diff�rentes activit�s.")
  void testCorrect() {
    assertEquals(null, unEvenement.setDescriptif(
        "Organisation d�un repas de no�l pour l�ann�e 2022. Au programme un repas et diff�rentes activit�s."),
        "Le retour devrait �tre null");
    assertEquals(
        "Organisation d�un repas de no�l pour l�ann�e 2022. Au programme un repas et diff�rentes activit�s.",
        unEvenement.getDescriptif(),
        "Le descriptif devrait �tre �gal � 'Organisation d�un repas de no�l pour l�ann�e 2022. Au programme un repas et diff�rentes activit�s.'");
  }

  @Test
  @DisplayName("Test unDescriptif vide")
  void testVide() {
    assertEquals(null, unEvenement.setDescriptif(""), "Le retour devrait �tre null");
    assertEquals("", unEvenement.getDescriptif(), "Le descriptif devrait �tre vide");
  }

  @Test
  @DisplayName("Test unDescriptif �gal � '   espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.DESCRIPTIF_ESPACE_EN_TROP,
        unEvenement.setDescriptif("   espaces avant"),
        "Le retour devrait �tre un code d'erreur DESCRIPTIF_ESPACE_EN_TROP");
    assertEquals(null, unEvenement.getDescriptif(), "Le descriptif devrait �tre �gal � null");
  }

  @Test
  @DisplayName("Test unDescptif �gal � 'espaces apr�s    '")
  void testEspaceApres() {
    assertEquals(CodeErreur.DESCRIPTIF_ESPACE_EN_TROP,
        unEvenement.setDescriptif("espaces apr�s    "),
        "Le retour devrait �tre un code d'erreur DESCRIPTIF_ESPACE_EN_TROP");
    assertEquals(null, unEvenement.getDescriptif(), "Le descriptif devrait �tre �gal � null");
  }

  @Test
  @DisplayName("Test unDescriptif �gal � un nom avec plus de 500 caract�res (exemple 520)")
  void testCinquanteCara() {
    assertEquals(CodeErreur.DESCRIPTIF_TROP_LONG, unEvenement.setDescriptif(
        "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"),
        "Le retour devrait �tre un code d'erreur DESCRIPTIF_TROP_LONG");
    assertEquals(null, unEvenement.getDescriptif(), "Le descriptif devrait �tre �gal � null");
  }

  @Test
  @DisplayName("Test unDescriptif �gal � null")
  void testNull() {
    assertEquals(CodeErreur.DESCRIPTIF_NULL, unEvenement.setDescriptif(null),
        "Le retour devrait �tre un code d'erreur DESCRIPTIF_NULL");
    assertEquals(null, unEvenement.getDescriptif(), "Le nom devrait �tre �gal � null (inchang�)");
  }
}
