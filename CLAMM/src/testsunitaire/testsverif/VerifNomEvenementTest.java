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
  @DisplayName("Test unNom égal à 'Nom de l'événement'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifNomEvenement("Repas de Noël"));
  }

  @Test
  @DisplayName("Test unNom égal à '  espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.NOM_ESPACE_EN_TROP, uneVerif.verifNomEvenement("  espaces avant"));
  }

  @Test
  @DisplayName("Test unNom égal à 'espaces après  '")
  void testEspaceApres() {
    assertEquals(CodeErreur.NOM_ESPACE_EN_TROP, uneVerif.verifNomEvenement("espaces après  "));
  }

  @Test
  @DisplayName("Test unNom égal à 'Nom d'événement 19c' 19 caractères")
  void testCorrect19car() {
    assertEquals(null, uneVerif.verifNomEvenement("Nom d'événement 19c"));
  }

  @Test
  @DisplayName("Test unNom égal à 'Nom d'événement 20cc' 20 caractères")
  void testCorrect20car() {
    assertEquals(null, uneVerif.verifNomEvenement("Nom d'événement 20cc"));
  }

  @Test
  @DisplayName("Test unNom égal à 'Nom d'événement 21ccc' 21 caractères trop long")
  void testTropLong() {
    assertEquals(CodeErreur.NOM_TROP_LONG, uneVerif.verifNomEvenement("Nom d'événement 21ccc"));
  }

  @Test
  @DisplayName("Test unNom égal à null")
  void testNull() {
    assertEquals(CodeErreur.NOM_NULL, uneVerif.verifNomEvenement(null));
  }

  @Test
  @DisplayName("Test unNom vide")
  void testVide() {
    assertEquals(CodeErreur.NOM_VIDE, uneVerif.verifNomEvenement(""));
  }
}
