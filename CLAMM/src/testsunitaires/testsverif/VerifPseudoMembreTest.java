package testsunitaires.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Initialisation Verif.TAILLE_MAX_PSEUDO_MEMBRE = 10

public class VerifPseudoMembreTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unPseudo �gal � 'Test'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifPseudoMembre("Test"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '123'")
  void testCorrectChiffre() {
    assertEquals(null, uneVerif.verifPseudoMembre("123"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � 'test-'")
  void testCorrectTiret() {
    assertEquals(null, uneVerif.verifPseudoMembre("test-"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � 'test�'")
  void testCorrectAccent() {
    assertEquals(null, uneVerif.verifPseudoMembre("test�"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � null")
  void testNull() {
    assertEquals(CodeErreur.PSEUDO_NULL, uneVerif.verifPseudoMembre(null));
  }

  @Test
  @DisplayName("Test unPseudo vide")
  void testVide() {
    assertEquals(CodeErreur.PSEUDO_VIDE, uneVerif.verifPseudoMembre(""));
  }

  @Test
  @DisplayName("Test unPseudo �gal � \"un espace\"")
  void testAvecEspace() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("un espace"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � 'pseudo-9c'")
  void testCorrect9car() {
    assertEquals(null, uneVerif.verifPseudoMembre("pseudo-9c"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � 'pseudo-10c'")
  void testCorrect10car() {
    assertEquals(null, uneVerif.verifPseudoMembre("pseudo-10c"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � 'pseudo-onze'")
  void testIncorrect11car() {
    assertEquals(CodeErreur.PSEUDO_TROP_LONG, uneVerif.verifPseudoMembre("pseudo-onze"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '<'")
  void testCaractereSpecial1() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("<"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '>'")
  void testCaractereSpecial2() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre(">"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '|'")
  void testCaractereSpecial3() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("|"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � ':'")
  void testCaractereSpecial4() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre(":"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '\"'")
  void testCaractereSpecial5() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("\""));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '*'")
  void testCaractereSpecial6() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("*"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '?'")
  void testCaractereSpecial7() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("?"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '/'")
  void testCaractereSpecial8() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("/"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � '\\'")
  void testCaractereSpecial9() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("\\"));
  }

  @Test
  @DisplayName("Test unPseudo �gal � ' ' espace")
  void testCaractereSpecial10() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre(" "));
  }

}
