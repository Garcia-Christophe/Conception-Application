package testsunitaire.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Initialisation Verif.TAILLE_MAX_NOM_EVENEMENT=20;

public class VerifNomEvenementTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unNom �gal � 'Nom de l'�v�nement'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifNomEvenement("Repas de No�l"));
  }

  @Test
  @DisplayName("Test unNom �gal � '  espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.NOM_ESPACE_EN_TROP, uneVerif.verifNomEvenement("  espaces avant"));
  }

  @Test
  @DisplayName("Test unNom �gal � 'espaces apr�s  '")
  void testEspaceApres() {
    assertEquals(CodeErreur.NOM_ESPACE_EN_TROP, uneVerif.verifNomEvenement("espaces apr�s  "));
  }

  @Test
  @DisplayName("Test unNom �gal � 'Nom d'�v�nement 19c' 19 caract�res")
  void testCorrect19car() {
    assertEquals(null, uneVerif.verifNomEvenement("Nom d'�v�nement 19c"));
  }

  @Test
  @DisplayName("Test unNom �gal � 'Nom d'�v�nement 20cc' 20 caract�res")
  void testCorrect20car() {
    assertEquals(null, uneVerif.verifNomEvenement("Nom d'�v�nement 20cc"));
  }

  @Test
  @DisplayName("Test unNom �gal � 'Nom d'�v�nement 21ccc' 21 caract�res trop long")
  void testTropLong() {
    assertEquals(CodeErreur.NOM_TROP_LONG, uneVerif.verifNomEvenement("Nom d'�v�nement 21ccc"));
  }

  @Test
  @DisplayName("Test unNom �gal � null")
  void testNull() {
    assertEquals(CodeErreur.NOM_NULL, uneVerif.verifNomEvenement(null));
  }

  @Test
  @DisplayName("Test unNom vide")
  void testVide() {
    assertEquals(CodeErreur.NOM_VIDE, uneVerif.verifNomEvenement(""));
  }
}
