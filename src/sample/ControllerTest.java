package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Matrice a,b,c;
    @BeforeEach
    void setUp() {
        Matrice matriceRep = new Matrice();
        a= new Matrice();
        a.setNomMat('A');
    }

    @Test
    void nouvelleMatrice() {
    }

    @Test
    void supprimerMatrice() {
    }

    @Test
    void renameMat() {
    }

    @Test
    void afficherMat() {
    }

    @Test
    void loadCSV() {
    }
}