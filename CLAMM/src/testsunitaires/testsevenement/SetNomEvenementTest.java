package testsunitaires.testsevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.evenements.Evenement;

public class SetNomEvenementTest {

  Evenement unEvenement;

  @BeforeEach
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test
  @DisplayName("Test unNom égal à 'Repas de Noël'")
  void testCorrect() {
    assertEquals(null, unEvenement.setNom("Repas de Noël"), "Le retour devrait être null");
    assertEquals("Repas de Noël", unEvenement.getNom(),
        "Le nom devrait être égal à 'Repas de Noël'");
  }

  @Test
  @DisplayName("Test unNom égal à '   espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.NOM_ESPACE_EN_TROP, unEvenement.setNom("   espaces avant"),
        "Le retour devrait être un code d'erreur NOM_ESPACE_EN_TROP");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait être égal à null");
  }

  @Test
  @DisplayName("Test unNom égal à 'espaces après    '")
  void testEspaceApres() {
    assertEquals(CodeErreur.NOM_ESPACE_EN_TROP, unEvenement.setNom("espaces après    "),
        "Le retour devrait être un code d'erreur NOM_ESPACE_EN_TROP");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait être égal à null");
  }

  @Test
  @DisplayName("Test unNom égal à un nom avec plus de 50 caractères (exemple 52)")
  void testCinquanteCara() {
    assertEquals(CodeErreur.NOM_TROP_LONG,
        unEvenement.setNom("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"),
        "Le retour devrait être un code d'erreur NOM_TROP_LONG");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait être égal à null");
  }

  @Test
  @DisplayName("Test unNom égal à null")
  void testNull() {
    assertEquals(CodeErreur.NOM_NULL, unEvenement.setNom(null),
        "Le retour devrait être un code d'erreur NOM_NULL");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait être égal à null (inchangé)");
  }

  @Test
  @DisplayName("Test unNom vide")
  void testVide() {
    assertEquals(CodeErreur.NOM_VIDE, unEvenement.setNom(""),
        "Le retour devrait être un code d'erreur NOM_VIDE");
    assertEquals(null, unEvenement.getNom(), "Le nom devrait être égal à null (inchangé)");
  }
}
