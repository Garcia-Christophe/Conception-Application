package testsIntegration.testsGestion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Gestion;

public class TestIntegrationGestionMembres {

  Gestion uneGestion;

  @BeforeEach
  void setUp() {
    uneGestion = new Gestion();
  }

  @Test
  @DisplayName("Test intégration : gestion de membres")
  void test() {
    Date d = new Date(2001 - 1900, 10, 7);
    Date d2 = new Date(2022 - 1900, 0, 1);
    ArrayList<CodeErreur> codes = new ArrayList<>();

    // liste vide
    assertEquals(0, uneGestion.getListeMembres().size(), "La liste devrait être vide");

    // ajouter un membre
    assertEquals(null, uneGestion.ajouterMembre("p1", "n", "pr", "l", d, "v", "m@m.m", "mdpppppp"),
        "Le retour devrait être null");

    // liste : 1 élément
    assertEquals(1, uneGestion.getListeMembres().size(), "La liste devrait être de taille 1");
    assertEquals("p1", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être p1");
    assertEquals("n", uneGestion.getListeMembres().get(0).getNom(),
        "Le pseudo du premier membre devrait être n");
    assertEquals("pr", uneGestion.getListeMembres().get(0).getPrenom(),
        "Le pseudo du premier membre devrait être pr");
    assertEquals("l", uneGestion.getListeMembres().get(0).getLieuNaissance(),
        "Le pseudo du premier membre devrait être l");
    assertEquals(d, uneGestion.getListeMembres().get(0).getDateNaissance(),
        "Le pseudo du premier membre devrait être 07/11/2001");
    assertEquals("v", uneGestion.getListeMembres().get(0).getVille(),
        "Le pseudo du premier membre devrait être v");
    assertEquals("m@m.m", uneGestion.getListeMembres().get(0).getMail(),
        "Le pseudo du premier membre devrait être m@m.m");
    assertEquals("mdpppppp", uneGestion.getListeMembres().get(0).getMotDePasse(),
        "Le pseudo du premier membre devrait être mdpppppp");

    // ajouter un membre (erreur : pseudo déjà existant)
    codes.add(CodeErreur.PSEUDO_DEJA_EXISTANT);
    assertEquals(codes,
        uneGestion.ajouterMembre("p1", "nn", "prr", "ll", d, "vv", "mm@m.m", "mmdpppppp"),
        "Le retour devrait être : PSEUDO_DEJA_EXISTANT");

    // liste : 1 élément
    assertEquals(1, uneGestion.getListeMembres().size(), "La liste devrait être de taille 1");
    assertEquals("p1", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être p1");

    // ajouter un second membre
    assertEquals(null,
        uneGestion.ajouterMembre("p2", "nn", "prr", "ll", d, "vv", "mm@m.m", "mmdpppppp"),
        "Le retour devrait être null");

    // liste : 2 éléments
    assertEquals(2, uneGestion.getListeMembres().size(), "La liste devrait être de taille 2");
    assertEquals("p1", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être p1");
    assertEquals("p2", uneGestion.getListeMembres().get(1).getPseudo(),
        "Le pseudo du premier membre devrait être p2");

    // modifier le pseudo et la date de naissance du premier membre
    assertEquals(null,
        uneGestion.modifierMembre("p1", "Rob", "n", "pr", "l", d2, "v", "m@m.m", "mdpppppp"),
        "Le retour devrait être null");

    // liste : 2 éléments
    assertEquals(2, uneGestion.getListeMembres().size(), "La liste devrait être de taille 2");
    assertEquals("Rob", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être Rob");
    assertEquals(d2, uneGestion.getListeMembres().get(0).getDateNaissance(),
        "Le pseudo du premier membre devrait être 01/01/2022");
    assertEquals("p2", uneGestion.getListeMembres().get(1).getPseudo(),
        "Le pseudo du premier membre devrait être p2");

    // modifier le nom d'un membre introuvable (erreur : membre introuvable)
    codes.clear();
    codes.add(CodeErreur.MEMBRE_INTROUVABLE);
    assertEquals(codes,
        uneGestion.modifierMembre("p1", "p1", "nn", "pr", "l", d2, "v", "m@m.m", "mdpppppp"),
        "Le retour devrait être : MEMBRE_INTROUVABLE");

    // liste : 2 éléments
    assertEquals(2, uneGestion.getListeMembres().size(), "La liste devrait être de taille 2");
    assertEquals("Rob", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être Rob");
    assertEquals("n", uneGestion.getListeMembres().get(0).getNom(),
        "Le pseudo du premier membre devrait être n");
    assertEquals("p2", uneGestion.getListeMembres().get(1).getPseudo(),
        "Le pseudo du premier membre devrait être p2");

    // modifier le nom et prénom d'un membre (erreur : nom et prénom null)
    codes.clear();
    codes.add(CodeErreur.NOM_NULL);
    codes.add(CodeErreur.PRENOM_NULL);
    assertEquals(codes,
        uneGestion.modifierMembre("Rob", "Rob", null, null, "l", d2, "v", "m@m.m", "mdpppppp"),
        "Le retour devrait être : NOM_NULL, PRENOM_NULL");

    // liste : 2 éléments
    assertEquals(2, uneGestion.getListeMembres().size(), "La liste devrait être de taille 2");
    assertEquals("Rob", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être Rob");
    assertEquals("n", uneGestion.getListeMembres().get(0).getNom(),
        "Le pseudo du premier membre devrait être n");
    assertEquals("pr", uneGestion.getListeMembres().get(0).getPrenom(),
        "Le pseudo du premier membre devrait être pr");
    assertEquals("p2", uneGestion.getListeMembres().get(1).getPseudo(),
        "Le pseudo du premier membre devrait être p2");

    // supprimer un membre
    assertEquals(null, uneGestion.supprimerMembre("p2"), "Le retour devrait être null");

    // liste : 1 élément
    assertEquals(1, uneGestion.getListeMembres().size(), "La liste devrait être de taille 1");
    assertEquals("Rob", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être Rob");

    // supprimer un membre introuvable (erreur : membre introuvable)
    codes.clear();
    codes.add(CodeErreur.MEMBRE_INTROUVABLE);
    assertEquals(codes, uneGestion.supprimerMembre("p2"),
        "Le retour devrait être : MEMBRE_INTROUVABLE");

    // liste : 1 élément
    assertEquals(1, uneGestion.getListeMembres().size(), "La liste devrait être de taille 1");
    assertEquals("Rob", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être Rob");

    // récupérer un membre
    assertNotEquals(null, uneGestion.getMembre("Rob"), "Le retour devrait être null");

    // liste : 1 élément
    assertEquals(1, uneGestion.getListeMembres().size(), "La liste devrait être de taille 1");
    assertEquals("Rob", uneGestion.getListeMembres().get(0).getPseudo(),
        "Le pseudo du premier membre devrait être Rob");

    // récupérer un membre introuvable (erreur : mmebre introuvable)
    assertEquals(null, uneGestion.getMembre("p2"), "Le retour devrait être null");
  }
}
