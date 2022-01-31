package testsunitaires.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import gestion.evenements.TypeEvenement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerifTypeEvenementTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unEvenement égal à typeEvenement.REPAS")
  void testCorrect() {
    assertEquals(null, uneVerif.verifTypeEvenement(TypeEvenement.REPAS));
  }

  @Test
  @DisplayName("Test unEvenement null")
  void testTypeNull() {
    assertEquals(CodeErreur.TYPE_NULL, uneVerif.verifTypeEvenement(null));
  }

}
