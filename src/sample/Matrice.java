package sample;

import java.util.ArrayList;

public class Matrice {
    int tailleL;
    int tailleC;
    double det;
    ArrayList<Element> element = new ArrayList<>();

    public int getTailleL() { return tailleL; }
    public void setTailleL(int tailleL) { this.tailleL = tailleL; }
    public int getTailleC() { return tailleC; }
    public void setTailleC(int tailleC) { this.tailleC = tailleC; }
    public double getDet() { return det; }
    public void setDet(double det) { this.det = det; }
    public ArrayList<Element> getElement() { return element; }
    public void setElement(ArrayList<Element> element) { this.element = element; }
}
