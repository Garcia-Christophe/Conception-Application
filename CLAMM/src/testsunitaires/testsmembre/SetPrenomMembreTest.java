package testsunitaires.testsmembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.membres.Membre;

public class SetPrenomMembreTest {

  Membre membre;

  String prenom;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test unPrenom egal a \"Pierre\"")
  void testPrenomAvecLettresSeulement() {
    prenom = "Pierre";
    assertEquals(membre.setPrenom(prenom), null, "Pas d'erreur, le retour doit etrenull");
    assertEquals(membre.getPrenom(), prenom, "Le nom doit etre change : \"" + prenom + "\"");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"JeanPierre\"")
  void testPrenomAvecTiret() {
    prenom = "JeanPierre";
    assertEquals(membre.setPrenom(prenom), null, "Pas d'erreur, le retour doit etrenull");
    assertEquals(membre.getPrenom(), prenom, "Le prenom doit etre change : \"" + prenom + "\"");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"")
  void testPrenom30caracteres() {
    prenom = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    assertEquals(membre.setPrenom(prenom), null, "Pas d'erreur, le retour doit etrenull");
    assertEquals(membre.getPrenom(), prenom, "Le prenom doit etre change : \"" + prenom + "\"");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"null\"")
  void testPrenomNull() {
    prenom = null;
    assertEquals(membre.setPrenom(prenom), CodeErreur.PRENOM_NULL,
        "Erreur, le retour doit etre le code Erreur");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"")
  void testPrenom31caracteres() {
    prenom = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    assertEquals(membre.setPrenom(prenom), CodeErreur.PRENOM_TROP_GRAND,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getPrenom(), prenom, "Le prenom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"\"")
  void testPrenomVide() {
    prenom = "";
    assertEquals(membre.setPrenom(prenom), CodeErreur.PRENOM_VIDE,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getPrenom(), prenom, "Le prenom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"Jean Pierre\"")
  void testPrenomAvecUnEspace() {
    prenom = "Jean Pierre";
    assertEquals(membre.setPrenom(prenom), CodeErreur.PRENOM_HORS_ALPHABET,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getPrenom(), prenom, "Le prenom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"Méric\"")
  void testPrenomAvecUnAccent() {
    prenom = "Méric";
    assertEquals(membre.setPrenom(prenom), CodeErreur.PRENOM_HORS_ALPHABET,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getPrenom(), prenom, "Le prenom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"123\"")
  void testNomAvecDesChiffres() {
    prenom = "123";
    assertEquals(membre.setPrenom(prenom), CodeErreur.PRENOM_HORS_ALPHABET,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getPrenom(), prenom, "Le prenom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unPrenom egal a \"^^$$\"")
  void testPrenomAvecCaracteresSpeciaux() {
    prenom = "^^$$";
    assertEquals(membre.setPrenom(prenom), CodeErreur.PRENOM_HORS_ALPHABET,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getPrenom(), prenom, "Le prenom ne doit pas etre change");
  }
}
