package sample;

public class Element implements Comparable<Element>{
    private double valeur;
    private int position;


    public double getValeur() { return valeur; }
    public void setValeur(double valeur) { this.valeur = valeur; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public int compareTo(Element o) {
        return position-o.getPosition();
    }
}
