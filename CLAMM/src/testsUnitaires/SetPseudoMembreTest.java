package testsUnitaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestionMembres.Membre;

class SetPseudoMembreTest {

  Membre membre;

  String pseudo;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test unPseudo �gal � \"Test\"")
  void testPseudoAvecLettresSeulement() {
    pseudo = "Test";
    assertEquals(membre.setPseudo(pseudo), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo doit �tre chang� : \"" + pseudo + "\"");
  }

  @Test
  @DisplayName("Test unPseudo �gal � \"123\"")
  void testPseudoAvecChiffresSeulement() {
    pseudo = "123";
    assertEquals(membre.setPseudo(pseudo), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo doit �tre chang� : \"" + pseudo + "\"");
  }

  @Test
  @DisplayName("Test unPseudo �gal � \"test-\"")
  void testPseudoAvecTraitUnion() {
    pseudo = "test-";
    assertEquals(membre.setPseudo(pseudo), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo doit �tre chang� : \"" + pseudo + "\"");
  }

  @Test
  @DisplayName("Test unPseudo �gal � \"test�\"")
  void testPseudoAvecAccent() {
    pseudo = "test�";
    assertEquals(membre.setPseudo(pseudo), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo doit �tre chang� : \"" + pseudo + "\"");
  }

  @Test
  @DisplayName("Test unPseudo �gal � null")
  void testPseudoNull() {
    pseudo = null;
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_NULL,
        "Cas d'erreur (null), le retour doit �tre : CodeErreur.PSEUDO_NULL");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unPseudo vide")
  void testPseudoVide() {
    pseudo = "";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_VIDE,
        "Cas d'erreur (vide), le retour doit �tre : CodeErreur.PSEUDO_VIDE");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unPseudo avec un espace au milieu")
  void testPseudoEspaceEntre() {
    pseudo = "espace entre";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (espace), le retour doit �tre : CodeErreur.PSEUDO_INVALIDE");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unPseudo trop long")
  void testPseudoTropLong() {
    pseudo = "1234567890123456789012345678901"; // 31 caract�res
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (trop long), le retour doit �tre : CodeErreur.PSEUDO_INVALIDE");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unPseudo �gal � \"<\"")
  void testPseudoAvecSymboleInferieur() {
    pseudo = "<";
    assertEquals(membre.setPseudo(pseudo), CodeErreur.PSEUDO_INVALIDE,
        "Cas d'erreur (caract�re invalide), le retour doit �tre : CodeErreur.PSEUDO_INVALIDE");
    assertEquals(membre.getPseudo(), pseudo, "Le pseudo ne doit �tre chang� (null)");
  }

}
