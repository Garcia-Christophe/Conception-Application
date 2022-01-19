package testsUnitaires.testsMembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.membres.Membre;

class SetMotDePasseMembreTest {

  Membre membre;

  String motDePasse;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"MotDePasse\"")
  void testMotDePasseAvecLettresSeulement() {
    motDePasse = "MotDePasse";
    assertEquals(membre.setMotDePasse(motDePasse), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse doit etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"123456789\"")
  void testMotDePasseAvecChiffresSeulement() {
    motDePasse = "123456789";
    assertEquals(membre.setMotDePasse(motDePasse), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse doit etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"1234567\\\"")
  void testMotDePasseAvecChiffresEtCaractere() {
    motDePasse = "1234567\\";
    assertEquals(membre.setMotDePasse(motDePasse), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse doit etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"1234567\"\\\"")
  void testMotDePasseAvecChiffresEtCaractere2() {
    motDePasse = "1234567\"\\";
    assertEquals(membre.setMotDePasse(motDePasse), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse doit etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"12$45A\"")
  void testMotDePasseAvec6Caracteres() {
    motDePasse = "12$45A";
    assertEquals(membre.setMotDePasse(motDePasse), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse doit etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"$$$$$$$$$$AAAAAAAAAA2222222222\"")
  void testMotDePasseAvec30Caracteres() {
    motDePasse = "$$$$$$$$$$AAAAAAAAAA2222222222";
    assertEquals(membre.setMotDePasse(motDePasse), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse doit etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"$$^^-+\"")
  void testMotDePasseCaracteresSpeciaux() {
    motDePasse = "$$^^-+";
    assertEquals(membre.setMotDePasse(motDePasse), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse doit etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a null")
  void testMotDePasseNull() {
    motDePasse = null;
    assertEquals(membre.setMotDePasse(motDePasse), CodeErreur.MDP_NULL,
        "Erreur, le retour doit etre le code Erreur");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"\"")
  void testMotDePasseVide() {
    motDePasse = "";
    assertEquals(membre.setMotDePasse(motDePasse), CodeErreur.MDP_VIDE,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse ne doit pas etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"A$1AA\"")
  void testMotDePasseAvec5Caracteres() {
    motDePasse = "A$1AA";
    assertEquals(membre.setMotDePasse(motDePasse), CodeErreur.MDP_TAILLE_INCORRECTE,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse ne doit pas etre change : \"" + motDePasse + "\"");
  }

  @Test
  @DisplayName("Test unMotDePasse egal a \"$$$$$$$$$$AAAAAAAAAA2222222222+\"")
  void testMotDePasseAvec31Caracteres() {
    motDePasse = "$$$$$$$$$$AAAAAAAAAA2222222222+";
    assertEquals(membre.setMotDePasse(motDePasse), CodeErreur.MDP_TAILLE_INCORRECTE,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getMotDePasse(), motDePasse,
        "Le motDePasse ne doit pas etre change : \"" + motDePasse + "\"");
  }

}
