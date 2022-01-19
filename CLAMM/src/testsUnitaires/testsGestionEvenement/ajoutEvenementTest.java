package testsUnitaires.testsGestionEvenement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import gestion.Gestion;

class ajoutEvenementTest {

    Gestion uneGestion;

    @BeforeEach                                         
    void setUp() {
      uneGestion = new Gestion();
    }

    @Test                                               
    @DisplayName("Test correct")   
    void testCorrect() {
        assertEquals(null, uneGestion.ajouterEvenement("", unDescriptif, uneImage, uneDate, unLieu, unNbMaxPersonnes, unType), "Le retour devrait être null");  
        assertEquals(1, unEvenement.getId(), "L'identifiant devrait être égal à 1");  
    }
}