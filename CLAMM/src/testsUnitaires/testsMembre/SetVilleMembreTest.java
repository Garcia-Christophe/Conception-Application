package testsUnitaires.testsMembre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.membres.Membre;

class SetVilleMembreTest {

  Membre membre;

  String ville;

  @BeforeEach
  void setUp() {
    membre = new Membre();
  }

  @Test
  @DisplayName("Test uneVille égal à \"Brest\"")
  void testVilleAvecLettresSeulement() {
    ville = "Brest";
    assertEquals(membre.setVille(ville), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getVille(), ville, "Le ville doit être changé : \"" + ville + "\"");
  }

  @Test
  @DisplayName("Test uneVille avec un trait d'union")
  void testVilleAvecTraitUnion() {
    ville = "Saint-Vincent-Sur-Jar";
    assertEquals(membre.setVille(ville), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getVille(), ville, "Le ville doit être changé : \"" + ville + "\"");
  }

  @Test
  @DisplayName("Test uneVille avec la taille maximum")
  void testVilleTailleMaximum() {
    ville = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; // 50 caractères
    assertEquals(membre.setVille(ville), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getVille(), ville, "Le ville doit être changé : \"" + ville + "\"");
  }

  @Test
  @DisplayName("Test uneVille avec un accent")
  void testVilleAvecAccent() {
    ville = "Méric";
    assertEquals(membre.setVille(ville), null, "Pas d'erreur, le retour doit être null");
    assertEquals(membre.getVille(), ville, "Le ville doit être changé : \"" + ville + "\"");
  }

  @Test
  @DisplayName("Test uneVille égal à null")
  void testVilleNull() {
    ville = null;
    assertEquals(membre.setVille(ville), CodeErreur.VILLE_NULL,
        "Cas d'erreur (null), le retour doit être : CodeErreur.VILLE_NULL");
  }

  @Test
  @DisplayName("Test uneVille avec un espace")
  void testVilleAvecEspace() {
    ville = "Jean Pierre";
    assertEquals(membre.setVille(ville), CodeErreur.VILLE_NOM_INCORRECT,
        "Cas d'erreur (espace), le retour doit être : CodeErreur.VILLE_NOM_INCORRECT");
    assertNotEquals(membre.getVille(), ville, "Le ville ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test uneVille avec des chiffres")
  void testVilleAvecChiffres() {
    ville = "123";
    assertEquals(membre.setVille(ville), CodeErreur.VILLE_NOM_INCORRECT,
        "Cas d'erreur (chiffres), le retour doit être : CodeErreur.VILLE_NOM_INCORRECT");
    assertNotEquals(membre.getVille(), ville, "Le ville ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test uneVille trop long")
  void testVilleTropLong() {
    ville = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; // 51 caractères
    assertEquals(membre.setVille(ville), CodeErreur.VILLE_TAILLE_INCORRECTE,
        "Cas d'erreur (taille), le retour doit être : CodeErreur.VILLE_TAILLE_INCORRECTE");
    assertNotEquals(membre.getVille(), ville, "Le ville ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test uneVille vide")
  void testVilleVide() {
    ville = "";
    assertEquals(membre.setVille(ville), CodeErreur.VILLE_TAILLE_INCORRECTE,
        "Cas d'erreur (vide), le retour doit être : CodeErreur.VILLE_TAILLE_INCORRECTE");
    assertNotEquals(membre.getVille(), ville, "Le ville ne doit pas être changé (null)");
  }

  @Test
  @DisplayName("Test uneVille égal à \" \"")
  void testVilleSimpleEspace() {
    ville = " ";
    assertEquals(membre.setVille(ville), CodeErreur.VILLE_NOM_INCORRECT,
        "Cas d'erreur (espace), le retour doit être : CodeErreur.LIEU_NOM_INCORRECT");
    assertNotEquals(membre.getVille(), ville, "Le ville ne doit pas être changé (null)");
  }

}
