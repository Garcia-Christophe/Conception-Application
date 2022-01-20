package testsUnitaires.testsEvenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.evenements.Evenement;

public class SetIdEvenementTest {

  Evenement unEvenement;

  @BeforeEach
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test
  @DisplayName("Test unId égal à 1")
  void testPositifUn() {
    assertEquals(null, unEvenement.setId(1), "Le retour devrait être null");
    assertEquals(1, unEvenement.getId(), "L'identifiant devrait être égal à 1");
  }

  @Test
  @DisplayName("Test unId égal à 3")
  void testPositifTrois() {
    assertEquals(null, unEvenement.setId(3), "Le retour devrait être null");
    assertEquals(3, unEvenement.getId(), "L'identifiant devrait être égal à 3");
  }

  @Test
  @DisplayName("Test unId égal à 0")
  void testZero() {
    assertEquals(CodeErreur.ID_NEGATIF, unEvenement.setId(0),
        "Le retour devrait être un code d'erreur ID_NEGATIF");
    assertEquals(0, unEvenement.getId(), "L'identifiant devrait être égal à 0 (inchangé)");
  }

  @Test
  @DisplayName("Test unId égal à -1")
  void testNegatifUn() {
    assertEquals(CodeErreur.ID_NEGATIF, unEvenement.setId(-1),
        "Le retour devrait être un code d'erreur ID_NEGATIF");
    assertEquals(0, unEvenement.getId(), "L'identifiant devrait être égal à 0 (inchangé)");
  }

  @Test
  @DisplayName("Test unId égal à -3")
  void testNegatifTrois() {
    assertEquals(CodeErreur.ID_NEGATIF, unEvenement.setId(-3),
        "Le retour devrait être un code d'erreur ID_NEGATIF");
    assertEquals(0, unEvenement.getId(), "L'identifiant devrait être égal à 0 (inchangé)");
  }
}
