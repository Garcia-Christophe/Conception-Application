package testsUnitaires.testsGestionEvenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.CodeErreur;
import gestion.Gestion;
import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;

public class ModifierEvenementTest {

  Gestion uneGestion;

  @BeforeEach
  void setUp() {
    uneGestion = new Gestion();
    uneGestion.ajouterEvenement("Un �v�nement", "Description de l��v�nement", "url",
        new Date(2022 - 1900, 06, 27, 15, 30), "adresse", 500, TypeEvenement.REPAS);
    uneGestion.ajouterEvenement("ag", "assembl�e g�n�rale", "url",
        new Date(2022 - 1900, 06, 8, 12, 45), "bureau", 20, TypeEvenement.AG);
  }

  @Test
  @DisplayName("Test correct : modifier �v�nement")
  void testCorrect() {
    Evenement tmp = uneGestion.getEvenement(1);
    assertEquals(null, uneGestion.modifierEvenement(1, "nom", tmp.getDescriptif(), tmp.getImage(),
        tmp.getDate(), tmp.getLieu(), 200, tmp.getType()), "Le retour devrait �tre null");

    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(), "L'identifiant devrait �tre 1");
    assertEquals("nom", uneGestion.getListeEvenements().get(0).getNom(),
        "Le nom devrait �tre 'nom'");
    assertEquals(tmp.getDescriptif(), uneGestion.getListeEvenements().get(0).getDescriptif(),
        "Le descriptif devrait �tre 'Description de l'�v�nement'");
    assertEquals(tmp.getImage(), uneGestion.getListeEvenements().get(0).getImage(),
        "L'image devrait �tre 'url'");
    assertEquals(tmp.getDate(), uneGestion.getListeEvenements().get(0).getDate(),
        "La date devrait �tre '27/06/22 15:30'");
    assertEquals(tmp.getLieu(), uneGestion.getListeEvenements().get(0).getLieu(),
        "Le lieu devrait �tre 'adresse'");
    assertEquals(200, uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),
        "Le nombre de personnes maximun devrait �tre 200");
    assertEquals(tmp.getType(), uneGestion.getListeEvenements().get(0).getType(),
        "Le type devrait �tre REPAS");
  }


  @Test
  @DisplayName("Test incorrect : Evenement avec un id positif inexistant")
  void testIdPositifIntrouvable() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
    assertEquals(codes,
        uneGestion.modifierEvenement(3, "nom", "Description de l��v�nement", "url",
            new Date(2022 - 1900, 06, 27, 15, 30), "adresse", 200, TypeEvenement.REPAS),
        "Le retour devrait �tre CodeErreur.EVENEMENT_INTROUVABLE");
  }

  @Test
  @DisplayName("Test incorrect : Evenement avec un id n�gatif inexistant")
  void testIdNegatifIntrouvable() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.EVENEMENT_INTROUVABLE);
    assertEquals(codes,
        uneGestion.modifierEvenement(-1, "nom", "Description de l��v�nement", "url",
            new Date(2022 - 1900, 06, 27, 15, 30), "adresse", 200, TypeEvenement.REPAS),
        "Le retour devrait �tre CodeErreur.EVENEMENT_INTROUVABLE");
  }

  @Test
  @DisplayName("Test incorrect : Le nombre maximum de personnes autoris�s est n�gatif")
  void testNbMaxPersonneNegatif() {
    ArrayList<CodeErreur> codes = new ArrayList<CodeErreur>();
    codes.add(CodeErreur.NB_MAX_PERSONNES_TROP_PETIT);
    Evenement tmp = uneGestion.getEvenement(1);
    assertEquals(codes,
        uneGestion.modifierEvenement(1, tmp.getNom(), tmp.getDescriptif(), tmp.getImage(),
            tmp.getDate(), tmp.getLieu(), -500, tmp.getType()),
        "Le retour devrait �tre CodeErreur.NB_MAX_PERSONNES_TROP_PETIT");

    assertEquals(1, uneGestion.getListeEvenements().get(0).getId(), "L'identifiant devrait �tre 1");
    assertEquals(tmp.getNom(), uneGestion.getListeEvenements().get(0).getNom(),
        "Le nom devrait �tre 'Un �v�nement'");
    assertEquals(tmp.getDescriptif(), uneGestion.getListeEvenements().get(0).getDescriptif(),
        "Le descriptif devrait �tre 'Description de l'�v�nement'");
    assertEquals(tmp.getImage(), uneGestion.getListeEvenements().get(0).getImage(),
        "L'image devrait �tre 'url'");
    assertEquals(tmp.getDate(), uneGestion.getListeEvenements().get(0).getDate(),
        "La date devrait �tre '27/06/22 15:30'");
    assertEquals(tmp.getLieu(), uneGestion.getListeEvenements().get(0).getLieu(),
        "Le lieu devrait �tre 'adresse'");
    assertEquals(tmp.getNbMaxPersonnes(),
        uneGestion.getListeEvenements().get(0).getNbMaxPersonnes(),
        "Le nombre de personnes maximun devrait �tre 500");
    assertEquals(tmp.getType(), uneGestion.getListeEvenements().get(0).getType(),
        "Le type devrait �tre REPAS");
  }



}
