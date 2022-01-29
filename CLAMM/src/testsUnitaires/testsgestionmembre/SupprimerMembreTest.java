package testsunitaires.testsgestionmembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Gestion;

public class SupprimerMembreTest {

  Gestion uneGestion;

  @BeforeEach
  void setUp() {
    uneGestion = new Gestion();
  }

  @Test
  @DisplayName("Test correct : suppression de 1 membre sur 2")
  void testUnMembreSurDeux() {
    Date d = new Date(2001 - 1900, 10, 7);
    uneGestion.ajouterMembre("Robert65", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp");
    uneGestion.ajouterMembre("GillesDu25", "nn", "prr", "ll", d, "vv", "mm@m.m", "mmdpppppp");

    assertEquals(null, uneGestion.supprimerMembre("Robert65"), "Le résultat doit être null");
    assertEquals(null, uneGestion.getMembre("Robert65"), "Le membre ne doit plus exister");
    assertEquals(1, uneGestion.getListeMembres().size(), "La liste doit être de taille 1");
  }

  @Test
  @DisplayName("Test correct : suppression de 1 membre sur 1")
  void testUnMembreSurUn() {
    Date d = new Date(2001 - 1900, 10, 7);
    uneGestion.ajouterMembre("Robert65", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp");

    assertEquals(null, uneGestion.supprimerMembre("Robert65"), "Le résultat doit être null");
    assertEquals(null, uneGestion.getMembre("Robert65"), "Le membre ne doit plus exister");
    assertEquals(0, uneGestion.getListeMembres().size(), "La liste doit être de taille 0");
  }

  @Test
  @DisplayName("Test incorrect : suppression sur une liste vide")
  void testNullite() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.MEMBRE_INTROUVABLE);
    assertEquals(codes, uneGestion.supprimerMembre("Robert65"),
        "Le retour devrait être une liste de codes erreurs : [MEMBRE_INTROUVABLE]");
  }

  @Test
  @DisplayName("Test incorrect : suppression d'un membre non existant")
  void testPseudoDejaExistant() {
    Date d = new Date(2001 - 1900, 10, 7);
    uneGestion.ajouterMembre("Robert65", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp");
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.MEMBRE_INTROUVABLE);
    assertEquals(codes, uneGestion.supprimerMembre("GillesDu25"),
        "Le retour devrait être une liste de codes erreurs : [MEMBRE_INTROUVABLE]");
    assertEquals(1, uneGestion.getListeMembres().size(),
        "La liste des membres devrait être de taille 1");
  }
}
