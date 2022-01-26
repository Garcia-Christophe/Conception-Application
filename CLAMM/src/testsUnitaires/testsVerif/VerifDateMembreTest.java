package testsUnitaires.testsVerif;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Verif;

public class VerifDateMembreTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test uneDateNaissance �gale � une date de naissance accept�e")
  void testDateNaissanceAcceptee() {
    assertEquals(null, uneVerif.verifDateMembre(new Date(2001 - 1900, 1, 1, 1, 1)));
  }

  @Test
  @DisplayName("Test uneDateNaissance plus grande que la date actuelle")
  void testDateNaissanceFuture() {
    assertEquals(CodeErreur.DATE_IMPOSSIBLE, 
        uneVerif.verifDateMembre(new Date(2023 - 1900, 1, 1, 1, 1)));
  }

  @Test
  @DisplayName("Test uneDateNaissance d'une personne sup�rieure � 130 ans")
  void testDateNaissanceTropVieille() {
    assertEquals(CodeErreur.DATE_AGE_IMPOSSIBLE, 
        uneVerif.verifDateMembre(new Date(1890 - 1900, 1, 1, 1, 1)));
  }

  @Test
  @DisplayName("Test unDateNaissance null")
  void testDateNaissanceNull() {
    assertEquals(CodeErreur.DATE_NULL, 
        uneVerif.verifDateMembre(null));
  }

}
