package testsunitaire.testsgestionevenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Gestion;
import gestion.evenements.TypeEvenement;

public class SupprimerEvenementTest {

  Gestion uneGestion;

  @BeforeEach
  void setUp() {
    uneGestion = new Gestion();
    uneGestion.ajouterEvenement("Un événement", "Description de l’événement", "url",
        new Date(2022 - 1900, 06, 27, 15, 30), "adresse", 500, TypeEvenement.REPAS);
    uneGestion.ajouterEvenement("ag", "assemblée générale", "url",
        new Date(2022 - 1900, 06, 8, 12, 45), "bureau", 20, TypeEvenement.AG);
  }

  @Test
  @DisplayName("Test correct : supprimer événement id 2")
  void testCorrect() {
    assertEquals(null, uneGestion.supprimerEvenement(2), "Le retour devrait être null");
    assertTrue(uneGestion.getEvenement(1) != null, "l'événement id 1 devrait etre trouvé");
    assertTrue(uneGestion.getEvenement(2) == null, "l'événement id 2 ne devrait pas etre trouvé");
  }

  @Test
  @DisplayName("Test incorrect : Evenement avec un id nul inexistant")
  void testIdNullIntrouvable() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
    assertEquals(codes, uneGestion.supprimerEvenement(0), "Le retour devrait être null");
    assertTrue(uneGestion.getEvenement(1) != null, "l'événement id 1 devrait etre trouvé");
    assertTrue(uneGestion.getEvenement(2) != null, "l'événement id 2 devrait etre trouvé");
  }

  @Test
  @DisplayName("Test incorrect : Evenement avec un id négatif inexistant")
  void testIdNegatifIntrouvable() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
    assertEquals(codes, uneGestion.supprimerEvenement(-1), "Le retour devrait être null");
    assertTrue(uneGestion.getEvenement(1) != null, "l'événement id 1 devrait etre trouvé");
    assertTrue(uneGestion.getEvenement(2) != null, "l'événement id 1 devrait etre trouvé");
  }


  @Test
  @DisplayName("Test incorrect : Evenement avec un id positif inexistant")
  void testIdPositifIntrouvable() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
    assertEquals(codes, uneGestion.supprimerEvenement(3), "Le retour devrait être null");
    assertTrue(uneGestion.getEvenement(1) != null, "l'événement id 1 devrait etre trouvé");
    assertTrue(uneGestion.getEvenement(2) != null, "l'événement id 1 devrait etre trouvé");
  }

}
