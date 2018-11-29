package sample;

import java.util.ArrayList;
import java.util.TreeSet;

public class Matrice {
    private int tailleL;
    private int tailleC;
    private char nomMat;
    private int nbElement;
    private ArrayList<Element> element=new ArrayList<>();
    private double determinant;

    public int getTailleL() { return tailleL; }
    public void setTailleL(int tailleL) { this.tailleL = tailleL; }
    public int getTailleC() { return tailleC; }
    public void setTailleC(int tailleC) { this.tailleC = tailleC; }
    public ArrayList<Element> getElement() { return element; }
    public void setElement(ArrayList<Element> element) { this.element = element; }
    public char getNomMat() { return nomMat; }
    public void setNomMat(char nomMat) { this.nomMat = nomMat; }
    public int getNbElement() { return nbElement; }
    public void setNbElement(int nbElement) { this.nbElement = nbElement; }
    public double getDeterminant() { return determinant;}
    public void setDeterminant(double determinant) { this.determinant = determinant;}
}
