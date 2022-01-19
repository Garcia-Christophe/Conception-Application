package testsUnitaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestionMembres.Membre;

class SetDateNaissanceMembreTest {

  Membre membre;

  Date date;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test unDateNaissance �gale � une date de naissance accept�e")
  void testDateNaissanceAcceptee() {
    date = new Date(2001 - 1900, 1, 1, 1, 1);
    assertEquals(membre.setDateNaissance(date), null, "Pas d'erreur, le retour doit �tre null");
    assertEquals(membre.getDateNaissance(), date, "Le lieu doit �tre chang� : \"" + date + "\"");
  }

  @Test
  @DisplayName("Test unDateNaissance plus grande que la date actuelle")
  void testDateNaissanceFuture() {
    date = new Date(2500 - 1900, 1, 1, 1, 1);
    assertEquals(membre.setDateNaissance(date), CodeErreur.DATE_IMPOSSIBLE,
        "Cas d'erreur (date future), le retour doit �tre : CodeErreur.DATE_IMPOSSIBLE");
    assertNotEquals(membre.getDateNaissance(), date, "Le lieu ne doit pas �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unDateNaissance d'une personne sup�rieure � 130 ans")
  void testDateNaissanceTropVieille() {
    date = new Date(1890 - 1900, 1, 1, 1, 1);
    assertEquals(membre.setDateNaissance(date), CodeErreur.DATE_AGE_IMPOSSIBLE,
        "Cas d'erreur (date future), le retour doit �tre : CodeErreur.DATE_AGE_IMPOSSIBLE");
    assertNotEquals(membre.getDateNaissance(), date, "Le lieu ne doit pas �tre chang� (null)");
  }

  @Test
  @DisplayName("Test unDateNaissance null")
  void testDateNaissanceNull() {
    date = null;
    assertEquals(membre.setDateNaissance(date), CodeErreur.DATE_NULL,
        "Cas d'erreur (null), le retour doit �tre : CodeErreur.DATE_NULL");
  }

}
