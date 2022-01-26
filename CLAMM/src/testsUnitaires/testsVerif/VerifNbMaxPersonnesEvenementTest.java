package testsUnitaires.testsVerif;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Verif;

public class VerifNbMaxPersonnesEvenementTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unEvenement égal à 30")
  void testPositifTrente() {
    assertEquals(null, uneVerif.verifNbMaxPersonnesEvenement(30));
  }
  
  @Test
  @DisplayName("Test unEvenement égal à 2")
  void testPositifDeux() {
    assertEquals(null, uneVerif.verifNbMaxPersonnesEvenement(2));
  }
  
  @Test
  @DisplayName("Test unEvenement égal à 1")
  void testPositifUn() {
    assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, uneVerif.verifNbMaxPersonnesEvenement(1));
  }

  @Test
  @DisplayName("Test unEvenement égal à 0")
  void testZero() {
    assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, uneVerif.verifNbMaxPersonnesEvenement(0));
  }

  @Test
  @DisplayName("Test unEvenement égal à -2")
  void testNegatifDeux() {
    assertEquals(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT, uneVerif.verifNbMaxPersonnesEvenement(-2));
  }

}
