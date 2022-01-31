package testsunitaire.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Initialisation Verif.TAILLE_MAX_DESCRIPTIF_EVENEMENT=20;

public class VerifLieuEvenementTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unLieu égal à '5 rue Paix, Brest'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifLieuEvenement("5 rue Paix, Brest"));
  }

  @Test
  @DisplayName("Test unLieu égal à '  espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.LIEU_ESPACE_EN_TROP, uneVerif.verifLieuEvenement("   espaces avant"));
  }

  @Test
  @DisplayName("Test unLieu égal à 'espaces après  '")
  void testEspaceApres() {
    assertEquals(CodeErreur.LIEU_ESPACE_EN_TROP, uneVerif.verifLieuEvenement("espaces après  "));
  }

  @Test
  @DisplayName("Test unLieu égal à 'nom du lieu 19 cara' 19 caractères")
  void testCorrect19car() {
    assertEquals(null, uneVerif.verifLieuEvenement("nom du lieu 19 cara"));
  }

  @Test
  @DisplayName("Test unLieu égal à 'un nom de lieu vingt' 20 caractères")
  void testCorrect20car() {
    assertEquals(null, uneVerif.verifLieuEvenement("un nom de lieu vingt"));
  }

  @Test
  @DisplayName("Test unLieu égal à 'nom du lieu vingtetun' 21 caractères trop long")
  void testCentCara() {
    assertEquals(CodeErreur.LIEU_TROP_LONG, uneVerif.verifLieuEvenement("nom du lieu vingtetun"));
  }

  @Test
  @DisplayName("Test unLieu égal à null")
  void testNull() {
    assertEquals(CodeErreur.LIEU_NULL, uneVerif.verifLieuEvenement(null));
  }

  @Test
  @DisplayName("Test unLieu vide")
  void testVide() {
    assertEquals(CodeErreur.LIEU_VIDE, uneVerif.verifLieuEvenement(""));
  }

}
