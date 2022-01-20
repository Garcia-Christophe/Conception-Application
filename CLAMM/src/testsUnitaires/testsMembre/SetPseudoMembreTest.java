package testsUnitaires.testsMembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.membres.Membre;

class SetPseudoMembreTest {

  Membre membre;

  String pseudo;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test unPseudo égal à \"Test\"")
  void testPseudoAvecLettresSeulement() {
    pseudo = "Test";
    assertEquals(membre.setPseudo(pseudo), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo doit être changé : \"" + pseudo + "\"");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"123\"")
  void testPseudoAvecChiffresSeulement() {
    pseudo = "123";
    assertEquals(membre.setPseudo(pseudo), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo doit être changé : \"" + pseudo + "\"");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"test-\"")
  void testPseudoAvecTraitUnion() {
    pseudo = "test-";
    assertEquals(membre.setPseudo(pseudo), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo doit être changé : \"" + pseudo + "\"");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"testé\"")
  void testPseudoAvecAccent() {
    pseudo = "testé";
    assertEquals(membre.setPseudo(pseudo), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo doit être changé : \"" + pseudo + "\"");
  }

  @Test
  @DisplayName("Test unPseudo égal à null")
  void testPseudoNull() {
    pseudo = null;
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_NULL,
        "Cas d'erreur (null), le retour doit être : CodeErreur.PSEUDO_NULL");
  }

  @Test
  @DisplayName("Test unPseudo vide")
  void testPseudoVide() {
    pseudo = "";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_VIDE,
        "Cas d'erreur (vide), le retour doit être : CodeErreur.PSEUDO_VIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo avec un espace au milieu")
  void testPseudoEspaceEntre() {
    pseudo = "espace entre";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (espace), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo trop long")
  void testPseudoTropLong() {
    pseudo = "1234567890123456789012345678901"; // 31 caractères
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_TROP_LONG,
        "Cas d'erreur (trop long), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"<\"")
  void testPseudoAvecSymboleInferieur() {
    pseudo = "<";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \">\"")
  void testPseudoAvecSymboleSuperieur() {
    pseudo = ">";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"|\"")
  void testPseudoAvecSymboleOu() {
    pseudo = "|";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \":\"")
  void testPseudoAvecSymboleDeuxPoints() {
    pseudo = ":";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"\"\"")
  void testPseudoAvecSymboleGuillemet() {
    pseudo = "\"";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"*\"")
  void testPseudoAvecSymboleAstérix() {
    pseudo = "*";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"?\"")
  void testPseudoAvecSymbolePointInterrogation() {
    pseudo = "?";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"/\"")
  void testPseudoAvecSymboleSlash() {
    pseudo = "/";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \"\\\"")
  void testPseudoAvecSymboleBackslash() {
    pseudo = "\\";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unPseudo égal à \" \"")
  void testPseudoAvecEspaceSimple() {
    pseudo = " ";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caractère invalide), le retour doit être : CodeErreur.PSEUDO_INVALIDE");
    assertNotEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit pas être changé (null)");
  }

}
