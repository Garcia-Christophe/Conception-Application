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
  @DisplayName("Test unDescriptif �gal � 'Descriptif'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifDescriptifEvenement("Descriptif"));
  }

  @Test
  @DisplayName("Test unDescriptif vide")
  void testVide() {
    assertEquals(null, uneVerif.verifDescriptifEvenement(""));
  }

  @Test
  @DisplayName("Test unDescriptif �gal � '  espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.DESCRIPTIF_ESPACE_EN_TROP,
        uneVerif.verifDescriptifEvenement("  espaces avant"));
  }

  @Test
  @DisplayName("Test unDescptif �gal � 'espaces apr�s  '")
  void testEspaceApres() {
    assertEquals(CodeErreur.DESCRIPTIF_ESPACE_EN_TROP,
        uneVerif.verifDescriptifEvenement("espaces apr�s  "));
  }

  @Test
  @DisplayName("Test unDescriptif �gal � 'Descriptif dix-neuf' (19 caract�res)")
  void testCorrect19car() {
    assertEquals(null, uneVerif.verifDescriptifEvenement("Descriptif dix-neuf"));
  }

  @Test
  @DisplayName("Test unDescriptif �gal � 'Descriptif vingt car' (20 caract�res)")
  void testCorrect20car() {
    assertEquals(null, uneVerif.verifDescriptifEvenement("Descriptif vingt car"));
  }

  @Test
  @DisplayName("Test unDescriptif �gal � 'Descriptif vingt et 1' (21 caract�res) trop long")
  void testTropLong() {
    assertEquals(CodeErreur.DESCRIPTIF_TROP_LONG,
        uneVerif.verifDescriptifEvenement("Descriptif vingt et 1"));
  }

  @Test
  @DisplayName("Test unDescriptif �gal � null")
  void testNull() {
    assertEquals(CodeErreur.DESCRIPTIF_NULL, uneVerif.verifDescriptifEvenement(null));
  }
}
