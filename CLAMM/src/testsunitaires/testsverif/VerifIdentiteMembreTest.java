package testsunitaires.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Initialisation
// Verif.TAILLE_MAX_NOM_MEMBRE = 10
// Verif.TAILLE_MAX_PRENOM_MEMBRE = 10

public class VerifIdentiteMembreTest {

  Verif uneVerif = new Verif();

  // NOM

  @Test
  @DisplayName("Test désignation égal à 'Pierre' et numéro à 0 (nom)")
  void testNomCorrect() {
    assertEquals(null, uneVerif.verifIdentiteMembre("Pierre", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'Nom' et numéro à 0 (nom)")
  void testNomCorrect2() {
    assertEquals(null, uneVerif.verifIdentiteMembre("Nom", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'NomNeufCa' (9 caractères) et numéro à 0 (nom)")
  void testNomCorrect9car() {
    assertEquals(null, uneVerif.verifIdentiteMembre("NomNeufCa", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'NomDixCara' (10 caractères) et numéro à 0 (nom)")
  void testNomCorrect10car() {
    assertEquals(null, uneVerif.verifIdentiteMembre("NomDixCara", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'NomOnzeCara' (11 car, trop long) et numéro à 0 (nom)")
  void testNomIncorrect11car() {
    assertEquals(CodeErreur.NOM_TROP_GRAND, uneVerif.verifIdentiteMembre("NomOnzeCara", 0));
  }

  @Test
  @DisplayName("Test désignation égal à null et numéro à 0 (nom)")
  void testNomNull() {
    assertEquals(CodeErreur.NOM_NULL, uneVerif.verifIdentiteMembre(null, 0));
  }

  @Test
  @DisplayName("Test désignation vide et numéro à 0 (nom)")
  void testNomVide() {
    assertEquals(CodeErreur.NOM_VIDE, uneVerif.verifIdentiteMembre("", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'un espace' et numéro à 0 (nom)")
  void testNomIncorrectEspace() {
    assertEquals(CodeErreur.NOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("un espace", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'Accént' et numéro à 0 (nom)")
  void testNomIncorrectAccent() {
    assertEquals(CodeErreur.NOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("Accént", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'Chiffre1' et numéro à 0 (nom)")
  void testNomIncorrectChiffre() {
    assertEquals(CodeErreur.NOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("Chiffre1", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'Pre-nom' et numéro à 0 (nom)")
  void testNomIncorrectTiret() {
    assertEquals(CodeErreur.NOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("Pre-nom", 0));
  }

  @Test
  @DisplayName("Test désignation égal à 'Pre*nom' et numéro à 0 (nom)")
  void testNomIncorrectCaraSpecial() {
    assertEquals(CodeErreur.NOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("Pre*nom", 0));
  }

  // PRENOM

  @Test
  @DisplayName("Test désignation égal à 'Pierre' et numéro à 1 (prenom)")
  void testPrenomCorrect() {
    assertEquals(null, uneVerif.verifIdentiteMembre("Pierre", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'PreNom' et numéro à 1 (prenom)")
  void testPrenomCorrect2() {
    assertEquals(null, uneVerif.verifIdentiteMembre("PreNom", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'PrenomNeu' (9 caractères) et numéro 1 (prenom)")
  void testPrenomCorrect9car() {
    assertEquals(null, uneVerif.verifIdentiteMembre("PrenomNeu", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'PrenomDixC' (10 caractères) et numéro à 1 (prenom)")
  void testPrenomCorrect10car() {
    assertEquals(null, uneVerif.verifIdentiteMembre("PrenomDixC", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'PrenomOnzeC' (11 car, trop long) et numéro à 1 (prenom)")
  void testPrenomIncorrect11car() {
    assertEquals(CodeErreur.PRENOM_TROP_GRAND, uneVerif.verifIdentiteMembre("PrenomOnzeC", 1));
  }

  @Test
  @DisplayName("Test désignation égal à null et numéro à 1 (prenom)")
  void testPrenomNull() {
    assertEquals(CodeErreur.PRENOM_NULL, uneVerif.verifIdentiteMembre(null, 1));
  }

  @Test
  @DisplayName("Test désignation vide et numéro à 1 (prenom)")
  void testPrenomVide() {
    assertEquals(CodeErreur.PRENOM_VIDE, uneVerif.verifIdentiteMembre("", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'un espace' et numéro à 1 (prenom)")
  void testPrenomIncorrectEspace() {
    assertEquals(CodeErreur.PRENOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("un espace", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'Accént' et numéro à 1 (prenom)")
  void testPrenomIncorrectAccent() {
    assertEquals(CodeErreur.PRENOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("Accént", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'Chiffre1' et numéro à 1 (prenom)")
  void testPrenomIncorrectChiffre() {
    assertEquals(CodeErreur.PRENOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("Chiffre1", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'Pre-nom' et numéro à 1 (prenom)")
  void testPrenomIncorrectTiret() {
    assertEquals(CodeErreur.PRENOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("Pre-nom", 1));
  }

  @Test
  @DisplayName("Test désignation égal à 'Pre*nom' et numéro à 1 (prenom)")
  void testPrenomIncorrectCaraSpecial() {
    assertEquals(CodeErreur.PRENOM_HORS_ALPHABET, uneVerif.verifIdentiteMembre("Pre*nom", 1));
  }

}
