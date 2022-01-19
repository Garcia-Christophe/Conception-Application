package testsUnitaires.testsMembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestionMembres.Membre;

class SetMailMembreTest {

  Membre membre;

  String mail;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test unMail avec un point dans l'identifiant")
  void testMailAvecPointIdentifiant() {
    mail = "tof.bob@example.com";
    assertEquals(membre.setMail(mail), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getMail(), mail, "Le mail doit être changé : \"" + mail + "\"");
  }

  @Test
  @DisplayName("Test unMail avec plusieurs points dans le nom de domaine")
  void testMailPlusieursPointsDomaine() {
    mail = "tof@example.me.org";
    assertEquals(membre.setMail(mail), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getMail(), mail, "Le mail doit être changé : \"" + mail + "\"");
  }

  @Test
  @DisplayName("Test unMail normal")
  void testMailNormal() {
    mail = "tof@example.com";
    assertEquals(membre.setMail(mail), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getMail(), mail, "Le mail doit être changé : \"" + mail + "\"");
  }

  @Test
  @DisplayName("Test unMail null")
  void testMailNull() {
    mail = null;
    assertEquals(membre.setMail(mail), CodeErreur.MAIL_NULL,
        "Cas d'erreur (null), le retour doit être : CodeErreur.MAIL_NULL");
  }

  @Test
  @DisplayName("Test unMail vide")
  void testMailVide() {
    mail = "";
    assertEquals(membre.setMail(mail), CodeErreur.MAIL_VIDE,
        "Cas d'erreur (vide), le retour doit être : CodeErreur.MAIL_VIDE");
    assertNotEquals(membre.getMail(), mail, "Le mail ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unMail avec le point collé à l'arobase")
  void testMailArobasePointColles() {
    mail = "tof@.com";
    assertEquals(membre.setMail(mail), CodeErreur.MAIL_INVALIDE,
        "Cas d'erreur (invalide), le retour doit être : CodeErreur.MAIL_INVALIDE");
    assertNotEquals(membre.getMail(), mail, "Le mail ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unMail sans la fin (ex: .com)")
  void testMailSansFin() {
    mail = "tof@example";
    assertEquals(membre.setMail(mail), CodeErreur.MAIL_INVALIDE,
        "Cas d'erreur (invalide), le retour doit être : CodeErreur.MAIL_INVALIDE");
    assertNotEquals(membre.getMail(), mail, "Le mail ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unMail sans arobase")
  void testMailSansArobase() {
    mail = "tof.example.com";
    assertEquals(membre.setMail(mail), CodeErreur.MAIL_INVALIDE,
        "Cas d'erreur (invalide), le retour doit être : CodeErreur.MAIL_INVALIDE");
    assertNotEquals(membre.getMail(), mail, "Le mail ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unMail avec plusieurs points collés dans l'identifant")
  void testMailPlusieursPointsCollesIdentifiant() {
    mail = "tof..bob@example.com";
    assertEquals(membre.setMail(mail), CodeErreur.MAIL_INVALIDE,
        "Cas d'erreur (invalide), le retour doit être : CodeErreur.MAIL_INVALIDE");
    assertNotEquals(membre.getMail(), mail, "Le mail ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unMail sans identifiant")
  void testMailSansIdentifiant() {
    mail = "@example.me.org";
    assertEquals(membre.setMail(mail), CodeErreur.MAIL_INVALIDE,
        "Cas d'erreur (invalide), le retour doit être : CodeErreur.MAIL_INVALIDE");
    assertNotEquals(membre.getMail(), mail, "Le mail ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test unMail avec un caractère invalide dans l'identifiant")
  void testMailAvecCaractereInvalideIdentifiant() {
    mail = "tof#@exemple.me.org";
    assertEquals(membre.setMail(mail), CodeErreur.MAIL_INVALIDE,
        "Cas d'erreur (invalide), le retour doit être : CodeErreur.MAIL_INVALIDE");
    assertNotEquals(membre.getMail(), mail, "Le mail ne doit pas être changé (null)");
  }

}
