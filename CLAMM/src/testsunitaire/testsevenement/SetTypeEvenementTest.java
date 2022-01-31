package testsunitaire.testsevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;

public class SetTypeEvenementTest {

  Evenement unEvenement;

  @BeforeEach
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test
  @DisplayName("Test unEvenement �gal � typeEvenement.REPAS")
  void testCorrect() {
    assertEquals(null, unEvenement.setType(TypeEvenement.REPAS), "Le retour devrait �tre null");
    assertEquals(TypeEvenement.REPAS, unEvenement.getType(),
        "Le type de l'�v�nement devrait �tre �gal � REPAS");
  }

  @Test
  @DisplayName("Test unEvenement null")
  void testTypeNull() {
    assertEquals(CodeErreur.TYPE_NULL, unEvenement.setType(null),
        "Le retour devrait �tre un code erreur TYPE_NULL");
    assertEquals(null, unEvenement.getType(),
        "Le type de l'�v�nement devrait �tre null (inchang�)");
  }

}
