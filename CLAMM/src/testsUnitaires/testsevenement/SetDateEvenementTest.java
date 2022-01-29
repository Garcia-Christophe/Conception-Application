package testsunitaires.testsevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.evenements.Evenement;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SetDateEvenementTest {

  Evenement unEvenement;

  @BeforeEach
  void setUp() {
    unEvenement = new Evenement();
  }

  @SuppressWarnings("deprecation")
  @Test
  @DisplayName("Test uneDate correcte")
  void testCorrecte() {
    assertEquals(null, unEvenement.setDate(new Date(2022 - 1900, 11, 25, 19, 30)),
        "Le retour devrait �tre null");
    assertEquals(new Date(2022 - 1900, 11, 25, 19, 30), unEvenement.getDate(),
        "La date devrait �tre �gale au 25/12/22 19:30 ");
  }

  @Test
  @DisplayName("Test uneDate pass�e")
  void testPassee() {
    assertEquals(CodeErreur.DATE_PASSEE, unEvenement.setDate(new Date(2021 - 1900, 11, 25, 19, 30)),
        "Le retour devrait �tre un code erreur DATE_PASSEE");
    assertEquals(null, unEvenement.getDate(), "La date devrait �tre �gale � null (inchang�e) ");
  }

  @Test
  @DisplayName("Test uneDate nulle")
  void testNulle() {
    assertEquals(CodeErreur.DATE_NULL, unEvenement.setDate(null),
        "Le retour devrait �tre un code erreur DATE_NULL");
    assertEquals(null, unEvenement.getDate(), "La date devrait �tre �gale � null (inchang�e) ");
  }
}
