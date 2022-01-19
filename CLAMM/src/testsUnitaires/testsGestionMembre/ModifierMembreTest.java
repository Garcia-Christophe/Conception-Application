package testsUnitaires.testsGestionMembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Gestion;

class ModifierMembreTest {

  Gestion uneGestion;

  Date d;

  @BeforeEach
  void setUp() {
    uneGestion = new Gestion();
    d = new Date(2001 - 1900, 10, 7);
    uneGestion.ajouterMembre("Robert65", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp");
    uneGestion.ajouterMembre("GillesDu25", "nn", "prr", "ll", d, "vv", "mm@m.m", "mmdpppppp");
  }

  @Test
  @DisplayName("Test correct : nouveau pseudo non existant et nouvelle date")
  void testCorrectPseudo() {
    Date d2 = new Date(2022 - 1900, 0, 1);
    assertEquals(null,
        uneGestion.modifierMembre("Robert65", "Rob", "n", "pr", "l", d2, "v", "m@m.m", "mdpppppp"),
        "Le retour devrait être null");

    assertEquals("Rob", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo devrait être Rob");
    assertEquals(null, uneGestion.getMembre("Robert65"),
        "Il ne doit plus y avoir de membre avec le pseudo Robert65");
    assertEquals(d2, uneGestion.getListeMembres().get(0).getDateNaissance(),
        "new Date(2022 - 1900, 0, 1)");
  }

  @Test
  @DisplayName("Test correct : nouveau nom")
  void testCorrectNom() {
    assertEquals(null, uneGestion.modifierMembre("Robert65", "Robert65", "nn", "pr", "l", d, "v",
        "m@m.m", "mdpppppp"), "Le retour devrait être null");

    assertEquals("nn", uneGestion.getListeMembres().get(0).getNom(), "Le nom devrait être nn");
  }

  @Test
  @DisplayName("Test incorrect : paramètres nom et prénom null")
  void testNullite() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.NOM_NULL);
    codes.add(CodeErreur.PRENOM_NULL);

    assertEquals(codes,
        uneGestion.modifierMembre("Robert65", "Robert65", null, null, "l", d, "v", "m@m.m",
            "mdpppppp"),
        "Le retour devrait être une liste de codes erreurs : [NOM_NULL, PRENOM_NULL]");
    assertEquals("n", uneGestion.getListeMembres().get(0).getNom(), "Le nom devrait être n");
    assertEquals("pr", uneGestion.getListeMembres().get(0).getPrenom(),
        "Le prénom devrait être pr");
  }

  @Test
  @DisplayName("Test incorrect : modification d'un membre non existant")
  void testMembreNomExistant() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.MEMBRE_INTROUVABLE);

    assertEquals(codes,
        uneGestion.modifierMembre("Rob", "Robert", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp"),
        "Le retour devrait être une liste de codes erreurs : [MEMBRE_INTROUVABLE]");
    assertEquals("n", uneGestion.getListeMembres().get(0).getNom(), "Le nom devrait être n");
    assertEquals("pr", uneGestion.getListeMembres().get(0).getPrenom(),
        "Le prénom devrait être pr");
  }

  @Test
  @DisplayName("Test incorrect : pseudo déjà existant")
  void testPseudoDejaExistant() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.PSEUDO_DEJA_EXISTANT);

    assertEquals(codes,
        uneGestion.modifierMembre("Robert65", "GillesDu25", "n", "pr", "l", d, "v", "m@m.m",
            "mdpppppp"),
        "Le retour devrait être une liste de codes erreurs : [PSEUDO_DEJA_EXISTANT]");
    assertEquals("Robert65", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo devrait être Robert65");
  }
}
