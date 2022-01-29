package testsunitaires.testsmembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.membres.Membre;

public class SetLieuNaissanceMembreTest {

  Membre membre;

  String lieu;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test unLieuNaissance �gal � \"Brest\"")
  void testLieuNaissanceAvecLettresSeulement() {
    lieu = "Brest";
    assertEquals(membre.setLieuNaissance(lieu), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getLieuNaissance(), lieu, "Le lieu doit �tre chang� : \"" + lieu + "\"");
  }

  @Test
  @DisplayName("Test unLieuNaissance avec un trait d'union")
  void testLieuNaissanceAvecTraitUnion() {
    lieu = "Saint-Vincent-Sur-Jar";
    assertEquals(membre.setLieuNaissance(lieu), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getLieuNaissance(), lieu, "Le lieu doit �tre chang� : \"" + lieu + "\"");
  }

  @Test
  @DisplayName("Test unLieuNaissance avec la taille maximum")
  void testLieuNaissanceTailleMaximum() {
    lieu = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; // 50 caract�res
    assertEquals(membre.setLieuNaissance(lieu), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getLieuNaissance(), lieu, "Le lieu doit �tre chang� : \"" + lieu + "\"");
  }

  @Test
  @DisplayName("Test unLieuNaissance avec un accent")
  void testLieuNaissanceAvecAccent() {
    lieu = "M�ric";
    assertEquals(membre.setLieuNaissance(lieu), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getLieuNaissance(), lieu, "Le lieu doit �tre chang� : \"" + lieu + "\"");
  }

  @Test
  @DisplayName("Test unLieuNaissance �gal � null")
  void testLieuNaissanceNull() {
    lieu = null;
    assertEquals(membre.setLieuNaissance(lieu), CodeErreur.LIEU_NULL,
        "Cas d'erreur (null), le retour doit �tre : CodeErreur.LIEU_NULL");
  }

  @Test
  @DisplayName("Test unLieuNaissance avec un espace")
  void testLieuNaissanceAvecEspace() {
    lieu = "Jean Pierre";
    assertEquals(membre.setLieuNaissance(lieu), CodeErreur.LIEU_NOM_INCORRECT,
        "Cas d'erreur (espace), le retour doit �tre : CodeErreur.LIEU_NOM_INCORRECT");
    assertNotEquals(membre.getLieuNaissance(), lieu, "Le lieu ne doit pas �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unLieuNaissance avec des chiffres")
  void testLieuNaissanceAvecChiffres() {
    lieu = "123";
    assertEquals(membre.setLieuNaissance(lieu), CodeErreur.LIEU_NOM_INCORRECT,
        "Cas d'erreur (chiffres), le retour doit �tre : CodeErreur.LIEU_NOM_INCORRECT");
    assertNotEquals(membre.getLieuNaissance(), lieu, "Le lieu ne doit pas �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unLieuNaissance trop long")
  void testLieuNaissanceTropLong() {
    lieu = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; // 51 caract�res
    assertEquals(membre.setLieuNaissance(lieu), CodeErreur.LIEU_TAILLE_INCORRECTE,
        "Cas d'erreur (taille), le retour doit �tre : CodeErreur.LIEU_TAILLE_INCORRECTE");
    assertNotEquals(membre.getLieuNaissance(), lieu, "Le lieu ne doit pas �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unLieuNaissance vide")
  void testLieuNaissanceVide() {
    lieu = "";
    assertEquals(membre.setLieuNaissance(lieu), CodeErreur.LIEU_TAILLE_INCORRECTE,
        "Cas d'erreur (vide), le retour doit �tre : CodeErreur.LIEU_TAILLE_INCORRECTE");
    assertNotEquals(membre.getLieuNaissance(), lieu, "Le lieu ne doit pas �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unLieuNaissance �gal � \" \"")
  void testLieuNaissanceSimpleEspace() {
    lieu = " ";
    assertEquals(membre.setLieuNaissance(lieu), CodeErreur.LIEU_NOM_INCORRECT,
        "Cas d'erreur (espace), le retour doit �tre : CodeErreur.LIEU_NOM_INCORRECT");
    assertNotEquals(membre.getLieuNaissance(), lieu, "Le lieu ne doit pas �tre chang� (null)");
  }

}
