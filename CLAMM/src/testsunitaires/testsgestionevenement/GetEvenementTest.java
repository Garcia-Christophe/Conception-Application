package testsunitaires.testsgestionevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.Gestion;
import gestion.evenements.TypeEvenement;

public class GetEvenementTest {

  Gestion uneGestion;

  @BeforeEach
  void setUp() {
    uneGestion = new Gestion();
    uneGestion.ajouterEvenement("Un �v�nement", "Description de l'�v�nement", "url",
        new Date(2022 - 1900, 06, 27, 15, 30), "adresse", 500, TypeEvenement.REPAS);
    uneGestion.ajouterEvenement("ag", "assembl�e g�n�rale", "url",
        new Date(2022 - 1900, 06, 8, 15, 30), "bureau", 20, TypeEvenement.AG);
  }

  @Test
  @DisplayName("Test correct")
  void testCorrect() {
    assertEquals(uneGestion.getListeEvenements().get(0), uneGestion.getEvenement(1),
        "Le retour devrait �tre �gale � l'�venement de listeEvenements d'id 1");
  }

  @Test
  @DisplayName("Test id inexistant positif")
  void testPositif3() {
    assertEquals(null, uneGestion.getEvenement(3), "Le retour devrait �tre null");
  }

  @Test
  @DisplayName("Test id inexistant �gale � 0")
  void testZero() {
    assertEquals(null, uneGestion.getEvenement(0), "Le retour devrait �tre null");
  }

  @Test
  @DisplayName("Test id inexistant n�gatif")
  void testNegatif1() {
    assertEquals(null, uneGestion.getEvenement(-1), "Le retour devrait �tre null");
  }

}
