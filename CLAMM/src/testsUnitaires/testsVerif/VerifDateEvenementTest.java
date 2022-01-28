package testsUnitaires.testsVerif;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Verif;

public class VerifDateEvenementTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test uneDate correcte")
  void testCorrecte() {
    assertEquals(null, uneVerif.verifDateEvenement(new Date(2022 - 1900, 11, 25, 19, 30)));
  }

  @Test
  @DisplayName("Test uneDate passée")
  void testPassee() {
    assertEquals(CodeErreur.DATE_PASSEE, 
        uneVerif.verifDateEvenement(new Date(2021 - 1900, 11, 25, 19, 30)));
  }

  @Test
  @DisplayName("Test uneDate nulle")
  void testNulle() {
    assertEquals(CodeErreur.DATE_NULL, uneVerif.verifDateEvenement(null));
  }
}
