package testsunitaires.testsevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.evenements.Evenement;

public class SetImageEvenementTest {

  Evenement unEvenement;

  @BeforeEach
  void setUp() {
    unEvenement = new Evenement();
  }

  @Test
  @DisplayName("Test uneImage null correcte")
  void testNullCorrecte() {
    assertEquals(null, unEvenement.setImage(null), "Le retour devrait �tre null");
    assertEquals(null, unEvenement.getImage(), "L'image devrait �tre null ");
  }

  @Test
  @DisplayName("Test uneImage vide correcte")
  void testVideCorrecte() {
    assertEquals(null, unEvenement.setImage(""), "Le retour devrait �tre null");
    assertEquals("", unEvenement.getImage(), "L'image devrait �tre une chaine vide ");
  }

  @Test
  @DisplayName("Test uneImage correcte")
  void testCorrecte() {
    assertEquals(null, unEvenement.setImage("https://www.google.com/image/chat.gif"),
        "Le retour devrait �tre null");
    assertEquals("https://www.google.com/image/chat.gif", unEvenement.getImage(),
        "L'image devrait �tre : https://www.google.com/image/chat.gif");
  }

}
