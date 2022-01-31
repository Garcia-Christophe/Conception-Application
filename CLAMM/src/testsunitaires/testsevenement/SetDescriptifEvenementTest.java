package testsunitaires.testsevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.evenements.Evenement;

public class SetDescriptifEvenementTest {

  Evenement unEvenement;

  @BeforeEach
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test
  @DisplayName("Test unDescriptif égal à 'Organisation d’un repas de noël pour l’année 2022. Au programme un repas et différentes activités.")
  void testCorrect() {
    assertEquals(null, unEvenement.setDescriptif(
        "Organisation d’un repas de noël pour l’année 2022. Au programme un repas et différentes activités."),
        "Le retour devrait être null");
    assertEquals(
        "Organisation d’un repas de noël pour l’année 2022. Au programme un repas et différentes activités.",
        unEvenement.getDescriptif(),
        "Le descriptif devrait être égal à 'Organisation d’un repas de noël pour l’année 2022. Au programme un repas et différentes activités.'");
  }

  @Test
  @DisplayName("Test unDescriptif vide")
  void testVide() {
    assertEquals(null, unEvenement.setDescriptif(""), "Le retour devrait être null");
    assertEquals("", unEvenement.getDescriptif(), "Le descriptif devrait être vide");
  }

  @Test
  @DisplayName("Test unDescriptif égal à '   espaces avant'")
  void testEspaceAvant() {
    assertEquals(CodeErreur.DESCRIPTIF_ESPACE_EN_TROP,
        unEvenement.setDescriptif("   espaces avant"),
        "Le retour devrait être un code d'erreur DESCRIPTIF_ESPACE_EN_TROP");
    assertEquals(null, unEvenement.getDescriptif(), "Le descriptif devrait être égal à null");
  }

  @Test
  @DisplayName("Test unDescptif égal à 'espaces après    '")
  void testEspaceApres() {
    assertEquals(CodeErreur.DESCRIPTIF_ESPACE_EN_TROP,
        unEvenement.setDescriptif("espaces après    "),
        "Le retour devrait être un code d'erreur DESCRIPTIF_ESPACE_EN_TROP");
    assertEquals(null, unEvenement.getDescriptif(), "Le descriptif devrait être égal à null");
  }

  @Test
  @DisplayName("Test unDescriptif égal à un nom avec plus de 500 caractères (exemple 520)")
  void testCinquanteCara() {
    assertEquals(CodeErreur.DESCRIPTIF_TROP_LONG, unEvenement.setDescriptif(
        "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"),
        "Le retour devrait être un code d'erreur DESCRIPTIF_TROP_LONG");
    assertEquals(null, unEvenement.getDescriptif(), "Le descriptif devrait être égal à null");
  }

  @Test
  @DisplayName("Test unDescriptif égal à null")
  void testNull() {
    assertEquals(CodeErreur.DESCRIPTIF_NULL, unEvenement.setDescriptif(null),
        "Le retour devrait être un code d'erreur DESCRIPTIF_NULL");
    assertEquals(null, unEvenement.getDescriptif(), "Le nom devrait être égal à null (inchangé)");
  }
}
