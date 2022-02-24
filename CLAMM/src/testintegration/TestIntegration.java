package testintegration;

import gestion.CodeErreur;
import gestion.Gestion;
import gestion.evenements.TypeEvenement;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestIntegration {

  static Gestion uneGestion;

  @BeforeAll
  public static void init() throws Exception {
    uneGestion = new Gestion();
  }

  @SuppressWarnings("deprecation")
  @Test
  @DisplayName("Test d'intégration")
  void test() throws SQLException {

    // init
    ArrayList<CodeErreur> codes = uneGestion.getCodesErreurs();
    int id = uneGestion.getProchainIdEvenement();



    /*
     * ajout Membre Alpha
     */

    // requete et verif retour
    assertEquals(null, uneGestion.ajouterMembre("Alpha", "ANDRE", "Tom", "BREST",
        new Date(1999 - 1900, 1, 28, 0, 0), "BREST", "tomandre@gmail.com", "Tom29$$"));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getMembre("Alpha"));


    /*
     * ajout Membre Alpha (deja present)
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.PSEUDO_DEJA_EXISTANT);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.ajouterMembre("Alpha", "ANDRE", "Tom", "BREST",
        new Date(1999 - 1900, 1, 28, 0, 0), "BREST", "tomandre@gmail.com", "Tom29$$"));

    // codes erreurs
    assertEquals(CodeErreur.PSEUDO_DEJA_EXISTANT, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    /*
     * ajout Evenement
     */

    // requete et verif retour
    assertEquals(null, uneGestion.ajouterEvenement(id, "Assemblée Générale", "Election du bureau",
        "image.png", new Date(2022 - 1900, 8, 15, 10, 30), "BREST", 20, TypeEvenement.AG));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getEvenement(id));

    /*
     * suppression membre non existant
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.MEMBRE_INTROUVABLE);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.supprimerMembre("Bravo"));

    // codes erreurs
    assertEquals(CodeErreur.MEMBRE_INTROUVABLE, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));


    /*
     * ajouter membre Bravo
     */

    // requete et verif retour
    assertEquals(null, uneGestion.ajouterMembre("Bravo", "GILLES", "Jules", "RENNES",
        new Date(1994 - 1900, 8, 29, 0, 0), "RENNES", "julesgilles@gmail.com", "Jules56$$"));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getMembre("Bravo"));


    /*
     * modifier membre Bravo
     */

    // requete et verif retour
    assertEquals(null, uneGestion.modifierMembre("Bravo", "YVES", "Jules", "MARSEILLE",
        new Date(1994 - 1900, 8, 29, 0, 0), "BREST", "julesyves@gmail.com", "Jules56$$"));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif modif
    assertEquals("YVES", uneGestion.getMembre("Bravo").getNom());
    assertEquals("MARSEILLE", uneGestion.getMembre("Bravo").getLieuNaissance());
    assertEquals("julesyves@gmail.com", uneGestion.getMembre("Bravo").getMail());


    /*
     * modification membre non existant
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.MEMBRE_INTROUVABLE);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.modifierMembre("Michel", "Ange", "Tom", "BREST",
        new Date(2001 - 1900, 1, 28, 0, 0), "BREST", "tomgilles@gmail.com", "Tom29$$"));

    // codes erreurs
    assertEquals(CodeErreur.MEMBRE_INTROUVABLE, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));


    /*
     * supprimer membre Bravo
     */
    // requete et verif retour
    assertEquals(null, uneGestion.supprimerMembre("Bravo"));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif supp
    assertNull(uneGestion.getMembre("Bravo"));


    /*
     * ajouter participation
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.ajouterParticipation(uneGestion.getEvenement(id),
        uneGestion.getMembre("Alpha"), 1, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getListeMembresParticipation(id, "Alpha"));


    /*
     * ajouter membre Bravo
     */

    // requete et verif retour
    assertEquals(null, uneGestion.ajouterMembre("Bravo", "RENE", "Jean", "PARIS",
        new Date(1994 - 1900, 8, 29, 0, 0), "PARIS", "jeanrene@gmail.com", "Jean75$$"));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getMembre("Bravo"));


    /*
     * ajouter membre Bravo
     */

    // requete et verif retour
    assertEquals(null, uneGestion.ajouterMembre("Charlie", "DUPUY", "Jack", "LYON",
        new Date(1974 - 1900, 2, 18, 0, 0), "LYON", "jackdupuy@gmail.com", "Jack69$$"));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getMembre("Charlie"));

    /*
     * ajout Evenement
     */

    // requete et verif retour
    assertEquals(null,
        uneGestion.ajouterEvenement(id + 1, "Point Chantier", "modifications des plannings",
            "planning.png", new Date(2022 - 1900, 8, 17, 10, 30), "RENNES", 10,
            TypeEvenement.CHANTIER));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getEvenement(id + 1));


    /*
     * ajout Evenement
     */

    // requete et verif retour
    assertEquals(null, uneGestion.ajouterEvenement(id + 2, "Soirée", "Soirée jeux de société",
        "image.png", new Date(2023 - 1900, 5, 10, 20, 30), "BREST", 30, TypeEvenement.ANIMATION));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getEvenement(id + 2));


    /*
     * ajouter participation
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.ajouterParticipation(uneGestion.getEvenement(id),
        uneGestion.getMembre("Bravo"), 1, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getListeMembresParticipation(id, "Bravo"));

    /*
     * ajouter participation nb trop grand
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NBINSCRIT_TROP_GRAND);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.ajouterParticipation(uneGestion.getEvenement(id),
        uneGestion.getMembre("Charlie"), 25, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NBINSCRIT_TROP_GRAND, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNull(uneGestion.getListeMembresParticipation(id, "Charlie"));


    /*
     * ajouter participation
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.ajouterParticipation(uneGestion.getEvenement(id),
        uneGestion.getMembre("Charlie"), 4, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getListeMembresParticipation(id, "Charlie"));

    /*
     * supprimer participation non existante
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.PARTICIPATION_INEXISTANTE);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.supprimerParticipation(uneGestion.getMembre("Bravo"),
        uneGestion.getEvenement(id + 1)));

    // codes erreurs
    assertEquals(CodeErreur.PARTICIPATION_INEXISTANTE, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    /*
     * ajouter participation evenement null
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.EVENEMENT_NULL);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes,
        uneGestion.ajouterParticipation(null, uneGestion.getMembre("Bravo"), 1, ""));

    // codes erreurs
    assertEquals(CodeErreur.EVENEMENT_NULL, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));
    

    /*
     * ajouter participation
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.ajouterParticipation(uneGestion.getEvenement(id + 1),
        uneGestion.getMembre("Alpha"), 2, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getListeMembresParticipation(id + 1, "Alpha"));


    /*
     * modifier participation non existante
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.PARTICIPATION_INEXISTANTE);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.modifierParticipation(uneGestion.getEvenement(id + 2),
        uneGestion.getMembre("Alpha"), 2, ""));

    // codes erreurs
    assertEquals(CodeErreur.PARTICIPATION_INEXISTANTE, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    
    /*
     * ajouter participation
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes,
        uneGestion.ajouterParticipation(uneGestion.getEvenement(id + 1),
            uneGestion.getMembre("Bravo"), 1, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getListeMembresParticipation(id + 1, "Bravo"));
    
    
    /*
     * ajouter participation membre inexistant
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.MEMBRE_NULL);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes,
        uneGestion.ajouterParticipation(uneGestion.getEvenement(id + 1),
            uneGestion.getMembre("Michel"), 2, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.MEMBRE_NULL, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif non ajout
    assertNull(uneGestion.getListeMembresParticipation(id + 1, "Michel"));
    

    /*
     * ajouter participation
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes,
        uneGestion.ajouterParticipation(uneGestion.getEvenement(id + 2),
            uneGestion.getMembre("Charlie"), 3, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif ajout
    assertNotNull(uneGestion.getListeMembresParticipation(id + 2, "Charlie"));
    
    /*
     * supprimer participation
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.supprimerParticipation(uneGestion.getMembre("Charlie"),
        uneGestion.getEvenement(id)));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));
    
    // verif supp
    assertNull(uneGestion.getListeMembresParticipation(id, "Charlie"));
    
    /*
     * suppression evenement
     */

    // requete et verif retour
    assertEquals(null, uneGestion.supprimerEvenement(id+1));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif supp
    assertNull(uneGestion.getEvenement(id+1));
    assertNull(uneGestion.getListeMembresParticipation(id+1,"Alpha"));
    assertNull(uneGestion.getListeMembresParticipation(id+1,"Bravo"));
    
    /*
     * modifier participation evenement introuvable
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.EVENEMENT_NULL);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.modifierParticipation(uneGestion.getEvenement(id + 1),
        uneGestion.getMembre("Charlie"), 4, ""));

    // codes erreurs
    assertEquals(CodeErreur.EVENEMENT_NULL, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));
    
    
    /*
     * modifier participation evenement introuvable
     */

    // codes erreurs retour
    codes.set(0, CodeErreur.NO_ERROR);
    codes.set(1, CodeErreur.NO_ERROR);
    codes.set(2, CodeErreur.NO_ERROR);
    codes.set(3, CodeErreur.NO_ERROR);
    codes.set(4, CodeErreur.NO_ERROR);
    codes.set(5, CodeErreur.NO_ERROR);
    codes.set(6, CodeErreur.NO_ERROR);
    codes.set(7, CodeErreur.NO_ERROR);
    codes.set(8, CodeErreur.NO_ERROR);

    // requete et verif retour
    assertEquals(codes, uneGestion.modifierParticipation(uneGestion.getEvenement(id),
        uneGestion.getMembre("Alpha"), 4, ""));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    assertEquals(4,uneGestion.getListeMembresParticipation(id,"Alpha").getNbInscrit());
    
    
    /*
     * modifier Evenement
     */

    // requete et verif retour
    assertEquals(null,
        uneGestion.modifierEvenement(id+2, "Soirée jeux", "Soirée jeux de société",
            "planning.png", new Date(2023 - 1900, 5, 10, 10, 30), "RENNES", 30,
            TypeEvenement.ANIMATION));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif modif
    assertEquals("Soirée jeux", uneGestion.getEvenement(id + 2).getNom());
    assertEquals("RENNES", uneGestion.getEvenement(id + 2).getLieu());
    
    /*
     * suppression membre Bravo
     */


    // requete et verif retour
    assertEquals(null, uneGestion.supprimerMembre("Bravo"));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));
    
    //verif supp
    assertNull(uneGestion.getMembre("Bravo"));
    assertNull(uneGestion.getListeMembresParticipation(id,"Bravo"));
    
    

    /*
     * suppression evenement
     */

    // requete et verif retour
    assertEquals(null, uneGestion.supprimerEvenement(id+2));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif supp
    assertNull(uneGestion.getEvenement(id+2));
    

    /*
     * suppression evenement
     */

    // requete et verif retour
    assertEquals(null, uneGestion.supprimerEvenement(id));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));

    // verif supp
    assertNull(uneGestion.getEvenement(id));
    assertNull(uneGestion.getListeMembresParticipation(id,"Alpha"));
    
    /*
     * suppression membre Alpha
     */


    // requete et verif retour
    assertEquals(null, uneGestion.supprimerMembre("Alpha"));

    // codes erreurs
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(0));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(1));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(2));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(3));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(4));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(5));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(6));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(7));
    assertEquals(CodeErreur.NO_ERROR, uneGestion.getCodesErreurs().get(8));
    
    //verif supp
    assertNull(uneGestion.getMembre("Alpha"));
  }
}
