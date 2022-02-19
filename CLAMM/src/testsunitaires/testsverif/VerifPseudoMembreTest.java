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
  @DisplayName("Test unPseudo égal à 'Test'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifPseudoMembre("Test"));
  }

  @Test
  @DisplayName("Test unPseudo égal à '123'")
  void testCorrectChiffre() {
    assertEquals(null, uneVerif.verifPseudoMembre("123"));
  }

  @Test
  @DisplayName("Test unPseudo égal à 'test-'")
  void testCorrectTiret() {
    assertEquals(null, uneVerif.verifPseudoMembre("test-"));
  }

  @Test
  @DisplayName("Test unPseudo égal à 'testé'")
  void testCorrectAccent() {
    assertEquals(null, uneVerif.verifPseudoMembre("testé"));
  }

  @Test
  @DisplayName("Test unPseudo égal à null")
  void testNull() {
    assertEquals(CodeErreur.PSEUDO_NULL, uneVerif.verifPseudoMembre(null));
  }

  @Test
  @DisplayName("Test unPseudo vide")
  void testVide() {
    assertEquals(CodeErreur.PSEUDO_VIDE, uneVerif.verifPseudoMembre(""));
  }

  @Test
  @DisplayName("Test unPseudo égal à \"un espace\"")
  void testAvecEspace() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("un espace"));
  }

  @Test
  @DisplayName("Test unPseudo égal à 'pseudo-9c'")
  void testCorrect9car() {
    assertEquals(null, uneVerif.verifPseudoMembre("pseudo-9c"));
  }

  @Test
  @DisplayName("Test unPseudo égal à 'pseudo-10c'")
  void testCorrect10car() {
    assertEquals(null, uneVerif.verifPseudoMembre("pseudo-10c"));
  }

  @Test
  @DisplayName("Test unPseudo égal à 'pseudo-onze'")
  void testIncorrect11car() {
    assertEquals(CodeErreur.PSEUDO_TROP_LONG, uneVerif.verifPseudoMembre("pseudo-onze"));
  }

  @Test
  @DisplayName("Test unPseudo égal à '<'")
  void testCaractereSpecial1() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("<"));
  }

  @Test
  @DisplayName("Test unPseudo égal à '>'")
  void testCaractereSpecial2() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre(">"));
  }

  @Test
  @DisplayName("Test unPseudo égal à '|'")
  void testCaractereSpecial3() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("|"));
  }

  @Test
  @DisplayName("Test unPseudo égal à ':'")
  void testCaractereSpecial4() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre(":"));
  }

  @Test
  @DisplayName("Test unPseudo égal à '\"'")
  void testCaractereSpecial5() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("\""));
  }

  @Test
  @DisplayName("Test unPseudo égal à '*'")
  void testCaractereSpecial6() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("*"));
  }

  @Test
  @DisplayName("Test unPseudo égal à '?'")
  void testCaractereSpecial7() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("?"));
  }

  @Test
  @DisplayName("Test unPseudo égal à '/'")
  void testCaractereSpecial8() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("/"));
  }

  @Test
  @DisplayName("Test unPseudo égal à '\\'")
  void testCaractereSpecial9() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre("\\"));
  }

  @Test
  @DisplayName("Test unPseudo égal à ' ' espace")
  void testCaractereSpecial10() {
    assertEquals(CodeErreur.PSEUDO_INVALIDE, uneVerif.verifPseudoMembre(" "));
  }

}
