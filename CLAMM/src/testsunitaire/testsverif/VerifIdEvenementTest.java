package testsunitaire.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerifIdEvenementTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unId égal à 1")
  void testPositifUn() {
    assertEquals(null, uneVerif.verifIdEvenement(1));
  }

  @Test
  @DisplayName("Test unId égal à 3")
  void testPositifTrois() {
    assertEquals(null, uneVerif.verifIdEvenement(3));
  }

  @Test
  @DisplayName("Test unId égal à 0")
  void testZero() {
    assertEquals(CodeErreur.ID_NEGATIF, uneVerif.verifIdEvenement(0));
  }

  @Test
  @DisplayName("Test unId égal à -1")
  void testNegatifUn() {
    assertEquals(CodeErreur.ID_NEGATIF, uneVerif.verifIdEvenement(-1));
  }

  @Test
  @DisplayName("Test unId égal à -3")
  void testNegatifTrois() {
    assertEquals(CodeErreur.ID_NEGATIF, uneVerif.verifIdEvenement(-3));
  }
}
