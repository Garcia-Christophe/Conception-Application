package testsunitaires.testsmembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.membres.Membre;

public class SetNomMembreTest {

  Membre membre;

  String nom;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test unNom égal à \"Pierre\"")
  void testNomAvecLetetresSeulement() {
    nom = "Pierre";
    assertEquals(membre.setNom(nom), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getNom(), nom, "Le nom doit etre change : \"" + nom + "\"");
  }

  @Test
  @DisplayName("Test unNom égal à \"JeanPierre\"")
  void testNomAvecTiret() {
    nom = "JeanPierre";
    assertEquals(membre.setNom(nom), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getNom(), nom, "Le nom doit etre change : \"" + nom + "\"");
  }

  @Test
  @DisplayName("Test unNom égal à \"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"")
  void testNom30caracteres() {
    nom = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    assertEquals(membre.setNom(nom), null, "Pas d'erreur, le retour doit etre null");
    assertEquals(membre.getNom(), nom, "Le nom doit etre change : \"" + nom + "\"");
  }

  @Test
  @DisplayName("Test unNom égal à \"null\"")
  void testNomNull() {
    nom = null;
    assertEquals(membre.setNom(nom), CodeErreur.NOM_NULL,
        "Erreur, le retour doit etre le code Erreur");
  }

  @Test
  @DisplayName("Test unNom égal à \"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"")
  void testNom31caracteres() {
    nom = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    assertEquals(membre.setNom(nom), CodeErreur.NOM_TROP_GRAND,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getNom(), nom, "Le nom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unNom égal à \"\"")
  void testNomVide() {
    nom = "";
    assertEquals(membre.setNom(nom), CodeErreur.NOM_VIDE,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getNom(), nom, "Le nom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unNom égal à \"Jean Pierre\"")
  void testNomAvecUnEspace() {
    nom = "Jean Pierre";
    assertEquals(membre.setNom(nom), CodeErreur.NOM_HORS_ALPHABET,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getNom(), nom, "Le nom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unNom égal à \"Méric\"")
  void testNomAvecUnAccent() {
    nom = "Méric";
    assertEquals(membre.setNom(nom), CodeErreur.NOM_HORS_ALPHABET,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getNom(), nom, "Le nom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unNom égal à \"123\"")
  void testNomAvecDesChiffres() {
    nom = "123";
    assertEquals(membre.setNom(nom), CodeErreur.NOM_HORS_ALPHABET,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getNom(), nom, "Le nom ne doit pas etre change");
  }

  @Test
  @DisplayName("Test unNom égal à \"^^$$\"")
  void testNomAvecCaracteresSpeciaux() {
    nom = "^^$$";
    assertEquals(membre.setNom(nom), CodeErreur.NOM_HORS_ALPHABET,
        "Erreur, le retour doit etre le code Erreur");
    assertNotEquals(membre.getNom(), nom, "Le nom ne doit pas etre change");
  }
}
