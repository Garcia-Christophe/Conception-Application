package testsUnitaires.testsGestionMembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.Gestion;

class GetMembreTest {

  Gestion uneGestion;

  @BeforeEach
  void setUp() {
    uneGestion = new Gestion();
    Date d = new Date(2001 - 1900, 10, 7);
    uneGestion.ajouterMembre("Robert65", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp");
  }

  @Test
  @DisplayName("Test correct : récupération d'un membre")
  void testCorrect() {
    assertEquals(uneGestion.getListeMembres().get(0), uneGestion.getMembre("Robert65"),
        "Le retour doit être le seul membre de la liste");
  }

  @Test
  @DisplayName("Test correct : pseudo non existant")
  void testPseudoNonExistant() {
    assertEquals(null, uneGestion.getMembre("GillesDu25"), "Le retour doit être null");
  }

  @Test
  @DisplayName("Test incorrect : pseudo null")
  void testNullite() {
    assertEquals(null, uneGestion.getMembre(null), "Le retour doit être null");
  }
}
