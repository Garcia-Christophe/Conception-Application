package testsunitaires.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Initialisation Verif.TAILLE_MAX_MAIL_MEMBRE = 20

public class VerifMailMembreTest {
  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unMail �gal � 'tof.bo@example.com'")
  void testMailCorrect() {
    assertEquals(null, uneVerif.verifMailMembre("tof.bo@example.com"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof@example.me.org'")
  void testMailCorrect2() {
    assertEquals(null, uneVerif.verifMailMembre("tof@example.me.org"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof@example.com'")
  void testMailCorrect3() {
    assertEquals(null, uneVerif.verifMailMembre("tof@example.com"));
  }

  @Test
  @DisplayName("Test unMail null")
  void testMailNull() {
    assertEquals(CodeErreur.MAIL_NULL, uneVerif.verifMailMembre(null));
  }

  @Test
  @DisplayName("Test unMail �gal � ''")
  void testMailVide() {
    assertEquals(CodeErreur.MAIL_VIDE, uneVerif.verifMailMembre(""));
  }

  @Test
  @DisplayName("Test unMail �gal � 'to19@example.me.org' (19 caract�res)")
  void testMailCorrect19Car() {
    assertEquals(null, uneVerif.verifMailMembre("to19@example.me.org"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof20@example.me.org' (20 caract�res)")
  void testMailCorrect20Car() {
    assertEquals(null, uneVerif.verifMailMembre("tof20@example.me.org"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof.21@example.me.org' (21 caract�res) trop long")
  void testMailIncorrect21car() {
    assertEquals(CodeErreur.MAIL_TROP_LONG, uneVerif.verifMailMembre("tof.21@example.me.org"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof@.com'")
  void testMailIncorrect1() {
    assertEquals(CodeErreur.MAIL_INVALIDE, uneVerif.verifMailMembre("tof@.com"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof@exemple'")
  void testMailIncorrect2() {
    assertEquals(CodeErreur.MAIL_INVALIDE, uneVerif.verifMailMembre("tof@exemple"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof.example.com'")
  void testMailIncorrect3() {
    assertEquals(CodeErreur.MAIL_INVALIDE, uneVerif.verifMailMembre("tof.example.com"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof..bob@example.com'")
  void testMailIncorrect4() {
    assertEquals(CodeErreur.MAIL_INVALIDE, uneVerif.verifMailMembre("tof..bob@example.com"));
  }

  @Test
  @DisplayName("Test unMail �gal � '@example.me.org'")
  void testMailIncorrect5() {
    assertEquals(CodeErreur.MAIL_INVALIDE, uneVerif.verifMailMembre("@example.me.org"));
  }

  @Test
  @DisplayName("Test unMail �gal � 'tof#@exemple.me.org'")
  void testMailIncorrect6() {
    assertEquals(CodeErreur.MAIL_INVALIDE, uneVerif.verifMailMembre("of#@exemple.me.org"));
  }

}
