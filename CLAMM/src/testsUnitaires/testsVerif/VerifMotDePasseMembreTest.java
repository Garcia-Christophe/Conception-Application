package testsUnitaires.testsVerif;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Verif;
import gestion.membres.Membre;

//Initialisation Verif.TAILLE_MAX_MDP_MEMBRE = 10

public class VerifMotDePasseMembreTest {

  Verif uneVerif = new Verif();

  @Test
  @DisplayName("Test unMotDePasse �gal � 'Mdp1!a'")
  void testCorrect() {
    assertEquals(null, uneVerif.verifMdpMembre("Mdp1!a"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'mdp1!a' pas de majuscule")
  void testPasMaj() {
    assertEquals(CodeErreur.MDP_INCORRECT, uneVerif.verifMdpMembre("mdp1!a"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'Mdp!aa' pas de chiffre")
  void testPasChiffre() {
    assertEquals(CodeErreur.MDP_INCORRECT, uneVerif.verifMdpMembre("Mdp!aa"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'MDP1!A' pas de minuscule")
  void testPasMinuscule() {
    assertEquals(CodeErreur.MDP_INCORRECT, uneVerif.verifMdpMembre("MDP1!A"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'Mdp1aa' pas de caract�re sp�cial")
  void testPasCaraSpecial() {
    assertEquals(CodeErreur.MDP_INCORRECT, uneVerif.verifMdpMembre("Mdp1aa"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'mdpaaa' que des minuscules")
  void testQueMin() {
    assertEquals(CodeErreur.MDP_INCORRECT, uneVerif.verifMdpMembre("mdpaaa"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse null")
  void testNull() {
    assertEquals(CodeErreur.MDP_NULL, uneVerif.verifMdpMembre(null));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � ''")
  void testVide() {
    assertEquals(CodeErreur.MDP_TAILLE_INCORRECTE, uneVerif.verifMdpMembre(""));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'Mdp5!'")
  void testTropPetit() {
    assertEquals(CodeErreur.MDP_TAILLE_INCORRECTE, uneVerif.verifMdpMembre("Mdp5!"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'Mdp6c!'")
  void testCorrect6car() {
    assertEquals(null, uneVerif.verifMdpMembre("Mdp6c!"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'Mdp9cara!'")
  void testCorrect9car() {
    assertEquals(null, uneVerif.verifMdpMembre("Mdp9cara!"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'Mdp10cara!'")
  void testCorrect10car() {
    assertEquals(null, uneVerif.verifMdpMembre("Mdp10cara!"));
  }
  
  @Test
  @DisplayName("Test unMotDePasse �gal � 'Mdp11cara!!'")
  void testTropLong() {
    assertEquals(CodeErreur.MDP_TAILLE_INCORRECTE, uneVerif.verifMdpMembre("Mdp11cara!!"));
  }
}