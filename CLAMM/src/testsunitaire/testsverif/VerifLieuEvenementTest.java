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
  @DisplayName("Test unLieu �gal � '5 rue Paix, Brest'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifLieuEvenement("5 rue Paix, Brest"));
  }

  @Test
  @DisplayName("Test unLieu �gal � '  espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.LIEU_ESPACE_EN_TROP, uneVerif.verifLieuEvenement("   espaces avant"));
  }

  @Test
  @DisplayName("Test unLieu �gal � 'espaces apr�s  '")
  void testEspaceApres() {
    assertEquals(CodeErreur.LIEU_ESPACE_EN_TROP, uneVerif.verifLieuEvenement("espaces apr�s  "));
  }

  @Test
  @DisplayName("Test unLieu �gal � 'nom du lieu 19 cara' 19 caract�res")
  void testCorrect19car() {
    assertEquals(null, uneVerif.verifLieuEvenement("nom du lieu 19 cara"));
  }

  @Test
  @DisplayName("Test unLieu �gal � 'un nom de lieu vingt' 20 caract�res")
  void testCorrect20car() {
    assertEquals(null, uneVerif.verifLieuEvenement("un nom de lieu vingt"));
  }

  @Test
  @DisplayName("Test unLieu �gal � 'nom du lieu vingtetun' 21 caract�res trop long")
  void testCentCara() {
    assertEquals(CodeErreur.LIEU_TROP_LONG, uneVerif.verifLieuEvenement("nom du lieu vingtetun"));
  }

  @Test
  @DisplayName("Test unLieu �gal � null")
  void testNull() {
    assertEquals(CodeErreur.LIEU_NULL, uneVerif.verifLieuEvenement(null));
  }

  @Test
  @DisplayName("Test unLieu vide")
  void testVide() {
    assertEquals(CodeErreur.LIEU_VIDE, uneVerif.verifLieuEvenement(""));
  }

}
