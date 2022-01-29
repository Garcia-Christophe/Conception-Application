package testsunitaires.testsverif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import gestion.CodeErreur;
import gestion.Verif;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Initialisation
// Verif.TAILLE_MAX_LIEU_MEMBRE = 10

public class VerifLieuxMembreTest {

  Verif uneVerif = new Verif();

  // Lieu de naissance

  @Test
  @DisplayName("Test lieu �gal � 'Lieu' et num�ro � 0 (lieu de naissance)")
  void testLieuCorrect() {
    assertEquals(null, uneVerif.verifLieuxMembre("Lieu", 0));
  }

  @Test
  @DisplayName("Test lieu �gal � 'LieuNeufC' (9 caract�res) et num�ro � 0 (lieu de naissance)")
  void testLieuCorrect9car() {
    assertEquals(null, uneVerif.verifLieuxMembre("LieuNeufC", 0));
  }

  @Test
  @DisplayName("Test lieu �gal � 'LieuDixCar' (10 caract�res) et num�ro � 0 (lieu de naissance)")
  void testLieuCorrect10car() {
    assertEquals(null, uneVerif.verifLieuxMembre("LieuDixCar", 0));
  }

  @Test
  @DisplayName("Test lieu �gal � 'LieuOnzeCar' (11 car, trop long) et num�ro � 0 (lieu naissance)")
  void testLieuIncorrect11car() {
    assertEquals(CodeErreur.LIEU_TAILLE_INCORRECTE, uneVerif.verifLieuxMembre("LieuOnzeCar", 0));
  }

  @Test
  @DisplayName("Test lieu �gal � 'Acc�nt' et num�ro � 0 (lieu de naissance)")
  void testLieuAccent() {
    assertEquals(CodeErreur.LIEU_NOM_INCORRECT, uneVerif.verifLieuxMembre("Acc�nt", 0));
  }

  @Test
  @DisplayName("Test lieu null et num�ro � 0 (lieu de naissance)")
  void testLieuNull() {
    assertEquals(CodeErreur.LIEU_NULL, uneVerif.verifLieuxMembre(null, 0));
  }

  @Test
  @DisplayName("Test lieu �gal � 'un espace' et num�ro � 0 (lieu de naissance)")
  void testLieuEspace() {
    assertEquals(null, uneVerif.verifLieuxMembre("un espace", 0));
  }

  @Test
  @DisplayName("Test lieu �gal � 'chiffre1' et num�ro � 0 (lieu de naissance)")
  void testLieuChiffre() {
    assertEquals(CodeErreur.LIEU_NOM_INCORRECT, uneVerif.verifLieuxMembre("chiffre1", 0));
  }

  @Test
  @DisplayName("Test lieu �gal � '' et num�ro � 0 (lieu de naissance)")
  void testLieuVide() {
    assertEquals(CodeErreur.LIEU_TAILLE_INCORRECTE, uneVerif.verifLieuxMembre("", 0));
  }

  @Test
  @DisplayName("Test lieu �gal � 'un,lieu' et num�ro � 0 (lieu de naissance)")
  void testLieuCaractereSpecial() {
    assertEquals(CodeErreur.LIEU_NOM_INCORRECT, uneVerif.verifLieuxMembre("un,lieu", 0));
  }

  // Ville

  @Test
  @DisplayName("Test ville �gal � 'Ville' et num�ro � 1 (ville)")
  void testVilleCorrect() {
    assertEquals(null, uneVerif.verifLieuxMembre("Ville", 1));
  }

  @Test
  @DisplayName("Test ville �gal � 'VilleNeuf' (9 caract�res) et num�ro � 1 (ville)")
  void testVilleCorrect9car() {
    assertEquals(null, uneVerif.verifLieuxMembre("VilleNeuf", 1));
  }

  @Test
  @DisplayName("Test ville �gal � 'VilleDixCa' (10 caract�res) et num�ro � 1 (ville)")
  void testVilleCorrect10car() {
    assertEquals(null, uneVerif.verifLieuxMembre("VilleDixCa", 1));
  }

  @Test
  @DisplayName("Test ville �gal � 'VilleOnzeCa' (11 caract�res trop long) et num�ro � 1 (ville)")
  void testVilleIncorrect11car() {
    assertEquals(CodeErreur.VILLE_TAILLE_INCORRECTE, uneVerif.verifLieuxMembre("VilleOnzeCa", 1));
  }

  @Test
  @DisplayName("Test ville �gal � 'Acc�nt' et num�ro � 1 (ville)")
  void testVilleAccent() {
    assertEquals(CodeErreur.VILLE_NOM_INCORRECT, uneVerif.verifLieuxMembre("Acc�nt", 1));
  }

  @Test
  @DisplayName("Test ville null et num�ro � 1 (ville)")
  void testVilleNull() {
    assertEquals(CodeErreur.VILLE_NULL, uneVerif.verifLieuxMembre(null, 1));
  }

  @Test
  @DisplayName("Test ville �gal � 'un espace' et num�ro � 1 (ville)")
  void testVilleEspace() {
    assertEquals(null, uneVerif.verifLieuxMembre("un espace", 1));
  }

  @Test
  @DisplayName("Test ville �gal � 'chiffre1' et num�ro � 1 (ville)")
  void testVilleChiffre() {
    assertEquals(CodeErreur.VILLE_NOM_INCORRECT, uneVerif.verifLieuxMembre("chiffre1", 1));
  }

  @Test
  @DisplayName("Test ville �gal � '' et num�ro � 1 (ville)")
  void testVilleVide() {
    assertEquals(CodeErreur.VILLE_TAILLE_INCORRECTE, uneVerif.verifLieuxMembre("", 1));
  }

  @Test
  @DisplayName("Test ville �gal � 'une,ville' et num�ro � 1 (ville)")
  void testVilleCaractereSpecial() {
    assertEquals(CodeErreur.VILLE_NOM_INCORRECT, uneVerif.verifLieuxMembre("une,ville", 1));
  }
}
