package testsunitaires.testsgestionmembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Gestion;

public class AjouterMembreTest {

  Gestion uneGestion;

  @BeforeEach
  void setUp() {
    uneGestion = new Gestion();
  }

  @Test
  @DisplayName("Test correct : ajout d'un membre sans soucis")
  void testCorrect() {
    Date d = new Date(2001 - 1900, 10, 7);
    assertEquals(null, uneGestion.ajouterMembre("p1", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp"),
        "Le retour devrait �tre null");

    assertEquals("p1", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo devrait �tre p1");
    assertEquals("n", uneGestion.getListeMembres().get(0).getNom(), "Le nom devrait �tre n");
    assertEquals("pr", uneGestion.getListeMembres().get(0).getPrenom(),
        "Le pr�nom devrait �tre pr");
    assertEquals("l", uneGestion.getListeMembres().get(0).getLieuNaissance(),
        "Le lieu devrait �tre l");
    assertEquals(d, uneGestion.getListeMembres().get(0).getDateNaissance(),
        "La date devrait �tre 2001-11-07");
    assertEquals("v", uneGestion.getListeMembres().get(0).getVille(), "La ville devrait �tre v");
    assertEquals("m@m.m", uneGestion.getListeMembres().get(0).getMail(),
        "Le mail devrait �tre m@m.m");
    assertEquals("mdpppppp", uneGestion.getListeMembres().get(0).getMotDePasse(),
        "Le mot de passe devrait �tre mdpppppp");
  }

  @Test
  @DisplayName("Test correct : ajout d'un second membre")
  void testSecondAjout() {
    Date d = new Date(2001 - 1900, 10, 7);
    uneGestion.ajouterMembre("p1", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp");

    // Test ajout du second membre
    assertEquals(null,
        uneGestion.ajouterMembre("p2", "nn", "prr", "ll", d, "vv", "mm@m.m", "mmdpppppp"),
        "Le retour devrait �tre null");
    assertEquals("p2", uneGestion.getListeMembres().get(1).getPseudo(),
        "Le pseudo devrait �tre p2");
    assertEquals("nn", uneGestion.getListeMembres().get(1).getNom(), "Le nom devrait �tre n2");
    assertEquals("prr", uneGestion.getListeMembres().get(1).getPrenom(),
        "Le pr�nom devrait �tre prr");
    assertEquals("ll", uneGestion.getListeMembres().get(1).getLieuNaissance(),
        "Le lieu devrait �tre ll");
    assertEquals(d, uneGestion.getListeMembres().get(1).getDateNaissance(),
        "La date devrait �tre 2001-11-07");
    assertEquals("vv", uneGestion.getListeMembres().get(1).getVille(), "La ville devrait �tre vv");
    assertEquals("mm@m.m", uneGestion.getListeMembres().get(1).getMail(),
        "Le mail devrait �tre mm@m.m");
    assertEquals("mmdpppppp", uneGestion.getListeMembres().get(1).getMotDePasse(),
        "Le mot de passe devrait �tre mmdpppppp");
  }

  @Test
  @DisplayName("Test incorrect : param�tres null")
  void testNullite() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.PSEUDO_NULL);
    codes.add(CodeErreur.NOM_NULL);
    codes.add(CodeErreur.PRENOM_NULL);
    codes.add(CodeErreur.LIEU_NULL);
    codes.add(CodeErreur.DATE_NULL);
    codes.add(CodeErreur.VILLE_NULL);
    codes.add(CodeErreur.MAIL_NULL);
    codes.add(CodeErreur.MDP_NULL);
    assertEquals(codes, uneGestion.ajouterMembre(null, null, null, null, null, null, null, null),
        "Le retour devrait �tre une liste de codes erreurs : [PSEUDO_NULL, NOM_NULL, PRENOM_NULL, LIEU_NULL, DATE_NULL, VILLE_NULL, MAIL_NULL, MDP_NULL]");
    assertTrue(uneGestion.getListeMembres().isEmpty(), "La liste des membres devrait �tre vide");
  }

  @Test
  @DisplayName("Test incorrect : pseudo d�j� existant")
  void testPseudoDejaExistant() {
    Date d = new Date(2001 - 1900, 10, 7);
    uneGestion.ajouterMembre("p1", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp");
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.PSEUDO_DEJA_EXISTANT);
    assertEquals(codes,
        uneGestion.ajouterMembre("p1", "nn", "prr", "ll", d, "vv", "mm@m.m", "mmdpppppp"),
        "Le retour devrait �tre une liste de codes erreurs : [PSEUDO_DEJA_EXISTANT]");
    assertEquals(1, uneGestion.getListeMembres().size(),
        "La liste des membres devrait �tre de taille 1");
  }
}
