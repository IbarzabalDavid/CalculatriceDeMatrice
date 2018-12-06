package sample;
import javafx.scene.control.Tab;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MatriceTest {
    Matrice a,b,c,d,e,f,g;
    Matrice matriceRep = new Matrice();
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        ArrayList<Element> elem0 = new ArrayList<>();
        for (int i=0;i<9;i++){
            Element element0 = new Element();
            elem0.add(element0);
        }
        matriceRep.setElement(elem0);


        a = new Matrice();
        a.setTailleL(3);
        a.setTailleC(3);
        ArrayList<Element> elem= new ArrayList<>();
        int [] valA = {3,6,7,-4,-5,6,-8,9,2};
        for (int i=0;i<9;i++){
            Element element = new Element();
            element.setValeur(valA[i]);
            elem.add(element);
        }
        a.setElement(elem);

        b= new Matrice();
        b.setTailleL(1);
        b.setTailleC(3);
        ArrayList<Element> elemB = new ArrayList<>();
        int [] valB= {-2,4,3};
        for (int i=0;i<3;i++){
            Element elementb = new Element();
            elementb.setValeur(valB[i]);
            elemB.add(elementb);
        }
        b.setElement(elemB);

        c= new Matrice();
        c.setTailleL(1);
        c.setTailleC(1);
        ArrayList<Element> elemC= new ArrayList<>();
        Element elementc= new Element();
        elementc.setValeur(67);
        elemC.add(elementc);
        c.setElement(elemC);

        d= new Matrice();
        d.setTailleC(2);
        d.setTailleL(2);
        ArrayList<Element> elemD = new ArrayList<>();
        int [] valC={3,6,-2,4};
        for (int i=0;i<4;i++){
            Element elementd = new Element();
            elementd.setValeur(valC[i]);
            elemD.add(elementd);
        }
        d.setElement(elemD);

        e= new Matrice();
        e.setTailleL(1);
        e.setTailleC(3);
        ArrayList<Element> elemE = new ArrayList<>();
        int [] valE ={3,-5,1};
        for (int i=0;i<3;i++){
            Element elemente= new Element();
            elemente.setValeur(valE[i]);
            elemE.add(elemente);
        }
        e.setElement(elemE);

        f= new Matrice();
        f.setTailleL(2);
        f.setTailleC(2);
        ArrayList<Element> elemF= new ArrayList<>();
        int [] valF = {3,-2,-3,6};
        for (int i=0;i<4;i++){
            Element elementf= new Element();
            elementf.setValeur(valF[i]);
            elemF.add(elementf);
        }
        f.setElement(elemF);

        g= new Matrice();
        g.setTailleL(3);
        g.setTailleC(3);
        g.setDeterminant(-4);
        ArrayList<Element> elemG = new ArrayList<>();
        int [] valG = {1,2,1,4,0,-1,-1,2,2};
        for (int i=0;i<9;i++){
            Element elementg = new Element();
            elementg.setValeur(valG[i]);
            elemG.add(elementg);
        }
        g.setElement(elemG);

    }

    @org.junit.jupiter.api.Test
    void addition() {
        int [] tabRep ={6,12,14,-8,-10,12,-16,18,4};
        for (int i=0;i<9;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp = a.addition(a);
        for (int i=0;i<matriceRep.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }
    }

    @org.junit.jupiter.api.Test
    void soustraction() {
        int [] tabRep ={0,0,0,0,0,0,0,0,0};
        for (int i=0;i<9;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp = a.soustraction(a);
        for (int i=0;i<matriceRep.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }

    }

    @org.junit.jupiter.api.Test
    void multiplication() {
        int [] tabRep ={-71,51,71,-40,55,-46,-76,-75,2};
        for (int i=0;i<9;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp = a.multiplication(a);
        for (int i=0;i<matriceRep.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }
        int [] tabRep1={-46,-5,16};
        for (int i=0;i<3;i++){
            matriceRep.getElement().get(i).setValeur(tabRep1[i]);
        }
       Matrice temp1 = b.multiplication(a);
        for (int i=0;i<temp1.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp1.getElement().get(i).getValeur());
        }
    }

    @org.junit.jupiter.api.Test
    void multiScalaire() {
        int [] tabRep ={9,18,21,-12,-15,18,-24,27,6};
        for (int i=0;i<9;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp = a.multiScalaire(3);

        for (int i=0;i<matriceRep.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }
    }

    @org.junit.jupiter.api.Test
    void puissance() {
        int [] tabRep ={-985,-42,-49,28,-929,-42,56,-63,-978};
        for (int i=0;i<9;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp= a.puissance(3);

        for (int i=0 ; i<matriceRep.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }

        int [] tabRep1 = {3,6,7,-4,-5,6,-8,9,2};
        for (int i=0;i<9;i++){
            matriceRep.getElement().get(i).setValeur(tabRep1[i]);
        }
        Matrice temp1= a.puissance(0);

        for (int i=0;i<9;i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp1.getElement().get(i).getValeur());
        }
    }
    @org.junit.jupiter.api.Test
    void inversion(){
        double [] tabRep ={-0.5,0.5,0.5,1.75,-0.75,-1.25,-2,1,-2};
        for (int i=0;i<9;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp= new Matrice();
        temp.setTailleL(3);
        temp.setTailleC(3);
        temp = g.inversion();
        for (int i=0;i<matriceRep.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }
        Matrice temp1 = new Matrice();
        temp1.setDeterminant(0);
        assertNull(temp1.inversion());
    }

    @org.junit.jupiter.api.Test
    void transposition() {
        int [] tabRep ={3,-4,-8,6,-5,9,7,6,2};
        for (int i=0;i<9;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp= a.transposition();
        for (int i=0; i<matriceRep.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }
        int [] tabRep1 ={-2,4,3};
        for (int i=0;i<3;i++){
            matriceRep.getElement().get(i).setValeur(tabRep1[i]);
        }
        matriceRep.setTailleL(3);
        matriceRep.setTailleC(1);
        Matrice temp1 = b.transposition();
        for (int i=0;i<temp1.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp1.getElement().get(i).getValeur());

        }
        assertEquals(matriceRep.getTailleL(),temp1.getTailleL());
        assertEquals(matriceRep.getTailleC(),temp1.getTailleC());
    }

    @org.junit.jupiter.api.Test
    void determinant() {
        matriceRep.setDeterminant(67);
        Matrice temp=c.determinant();
        assertEquals(matriceRep.getDeterminant(),temp.getDeterminant());

        matriceRep.setDeterminant(24);
        Matrice temp1= d.determinant();
        assertEquals(matriceRep.getDeterminant(),temp1.getDeterminant());

        matriceRep.setDeterminant(-964);
        Matrice temp2= a.determinant();
        assertEquals(matriceRep.getDeterminant(),temp2.getDeterminant());
    }

    @org.junit.jupiter.api.Test
    void produitVect() {
        int [] tabRep ={19,11,-2};
        for (int i=0;i<3;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp= b.produitVect(e);
        for (int i=0;i<b.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }
    }

    @org.junit.jupiter.api.Test
    void produitHadamard() {
        int [] tabRep ={9,-12,6,24};
        for (int i=0;i<4;i++){
            matriceRep.getElement().get(i).setValeur(tabRep[i]);
        }
        Matrice temp= d.produitHadamard(f);
        for (int i=0;i<temp.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }

    }

    @org.junit.jupiter.api.Test
    void produitTensoriel() {
        int [] valeur ={9,-6,18,-12,-9,18,-18,36,-6,4,12,-8,6,-12,-12,24};
        ArrayList<Element> elements= new ArrayList<>();
        for (int i=0;i<16;i++){
            Element element= new Element();
            element.setValeur(valeur[i]);
            elements.add(element);
        }
        matriceRep.setElement(elements);
        matriceRep.setTailleL(4);
        matriceRep.setTailleC(4);

        Matrice temp= d.produitTensoriel(f);

        for (int i=0;i<temp.getElement().size();i++){
            assertEquals(matriceRep.getElement().get(i).getValeur(),temp.getElement().get(i).getValeur());
        }
        assertEquals(matriceRep.getTailleL(),temp.getTailleL());
        assertEquals(matriceRep.getTailleC(),temp.getTailleC());
    }

    @org.junit.jupiter.api.Test
    void verif() {
        Matrice temp= new Matrice();
        boolean ok=false;
        ok= temp.verif(d,f);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    void verif2() {
        Matrice temp= new Matrice();
        boolean ok=true;
        ok= temp.verif2(a,f);
        assertFalse(ok);
        boolean ok2 = false;
        ok2= temp.verif2(b,a);
        assertTrue(ok2);
    }

    @org.junit.jupiter.api.Test
    void verif3() {
        Matrice temp= new Matrice();
        boolean ok=false;
        ok= temp.verif3(a);
        assertTrue(ok);
    }

    @org.junit.jupiter.api.Test
    void verif4() {
        Matrice temp= new Matrice();
        boolean ok=true;
        ok= temp.verif4(b,e);
        assertTrue(ok);
    }
}