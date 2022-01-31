package testsunitaire.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Initialisation Verif.TAILLE_MAX_DESCRIPTIF_EVENEMENT=20;

public class VerifDescriptifEvenementTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unDescriptif égal à 'Descriptif'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifDescriptifEvenement("Descriptif"));
  }

  @Test
  @DisplayName("Test unDescriptif vide")
  void testVide() {
    assertEquals(null, uneVerif.verifDescriptifEvenement(""));
  }

  @Test
  @DisplayName("Test unDescriptif égal à '  espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.DESCRIPTIF_ESPACE_EN_TROP,
        uneVerif.verifDescriptifEvenement("  espaces avant"));
  }

  @Test
  @DisplayName("Test unDescptif égal à 'espaces après  '")
  void testEspaceApres() {
    assertEquals(CodeErreur.DESCRIPTIF_ESPACE_EN_TROP,
        uneVerif.verifDescriptifEvenement("espaces après  "));
  }

  @Test
  @DisplayName("Test unDescriptif égal à 'Descriptif dix-neuf' (19 caractères)")
  void testCorrect19car() {
    assertEquals(null, uneVerif.verifDescriptifEvenement("Descriptif dix-neuf"));
  }

  @Test
  @DisplayName("Test unDescriptif égal à 'Descriptif vingt car' (20 caractères)")
  void testCorrect20car() {
    assertEquals(null, uneVerif.verifDescriptifEvenement("Descriptif vingt car"));
  }

  @Test
  @DisplayName("Test unDescriptif égal à 'Descriptif vingt et 1' (21 caractères) trop long")
  void testTropLong() {
    assertEquals(CodeErreur.DESCRIPTIF_TROP_LONG,
        uneVerif.verifDescriptifEvenement("Descriptif vingt et 1"));
  }

  @Test
  @DisplayName("Test unDescriptif égal à null")
  void testNull() {
    assertEquals(CodeErreur.DESCRIPTIF_NULL, uneVerif.verifDescriptifEvenement(null));
  }
}
