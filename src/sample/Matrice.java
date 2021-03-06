package sample;

import java.util.ArrayList;

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
    public Matrice addition(Matrice matrice2){
        if (verif(this,matrice2)){
            Matrice matriceRes = new Matrice();
            ArrayList<Element> elem = new ArrayList<>();
            for (int i=0;i<this.getElement().size();i++){
                Element element = new Element();
                element.setValeur(this.getElement().get(i).getValeur()+matrice2.getElement().get(i).getValeur());
                elem.add(element);
            }
            matriceRes.setElement(elem);
            matriceRes.setTailleL(this.getTailleL());
            matriceRes.setTailleC(this.getTailleC());
            return matriceRes;
        }
        else {
            return null;
        }
    }
    public Matrice soustraction( Matrice matrice2){
        if (verif(this,matrice2)){
            Matrice matriceRes = new Matrice();
            ArrayList<Element> elem = new ArrayList<>();
            for (int i=0;i<this.getElement().size();i++){
                Element element = new Element();
                element.setValeur(this.getElement().get(i).getValeur()- matrice2.getElement().get(i).getValeur());
                elem.add(element);
            }
            matriceRes.setElement(elem);
            matriceRes.setTailleL(this.getTailleL());
            matriceRes.setTailleC(this.getTailleC());
            return matriceRes;
        }
        else {
            return null;
        }
    }

    public Matrice multiplication(Matrice matrice2){

        if (verif2(this,matrice2)){
            int var=0;

            Matrice matriceRes = new Matrice();
            ArrayList<Element> elem = new ArrayList<>();
            double [][] mat1 = new double[this.getTailleL()][this.getTailleC()];
            double [][] mat2 = new double[matrice2.getTailleL()][matrice2.getTailleC()];
            double [][] matRes = new double[this.getTailleL()][matrice2.getTailleC()];

            for (int i=0;i<this.getTailleL();i++){
                for (int j=0;j<this.getTailleC();j++){
                    mat1[i][j]= this.getElement().get(var).getValeur();
                    var++;
                }
            }
            var=0;
            for (int i=0;i<matrice2.getTailleL();i++){
                for (int j=0;j<matrice2.getTailleC();j++){
                    mat2[i][j]= matrice2.getElement().get(var).getValeur();
                    var++;
                }
            }

            for(int i = 0; i < this.getTailleL(); i++) {
                for (int j = 0; j < matrice2.getTailleC(); j++) {
                    for (int k = 0; k < this.getTailleC(); k++) {
                        matRes[i][j]+= mat1[i][k]*mat2[k][j];
                    }
                }
            }
            for (int i=0;i<this.getTailleL();i++){
                for (int j=0;j<matrice2.getTailleC();j++){
                    Element element = new Element();
                    element.setValeur(matRes[i][j]);
                    elem.add(element);
                }
            }
            matriceRes.setElement(elem);
            matriceRes.setTailleL(this.getTailleL());
            matriceRes.setTailleC(matrice2.getTailleC());
            return matriceRes;
        }
        else {
            return null;
        }
    }
    public Matrice multiScalaire(int nombre){
        Matrice matriceRes= new Matrice();
        ArrayList<Element> elements = new ArrayList<>();
                for (int i=0;i<this.getElement().size();i++){
                    Element elem = new Element();
                    elem.setValeur(this.getElement().get(i).getValeur()*nombre);
                    elements.add(elem);
                }
                matriceRes.setElement(elements);
                matriceRes.setTailleL(this.getTailleL());
                matriceRes.setTailleC(this.getTailleC());
        return  matriceRes;
    }

    public Matrice puissance(int nombre){
        if (verif3(this)){
            Matrice matriceRes = new Matrice();
            ArrayList<Element> elements= new ArrayList<>();
            for (int i=0;i<this.getTailleL()*this.getTailleC();i++){
                Element elem= new Element();
                elem.setValeur(this.getElement().get(i).getValeur());
                elements.add(elem);
            }
            matriceRes.setElement(elements);
            double [][] mat1= new double [this.getTailleC()][this.getTailleL()];
            double [][]matRes = new double[this.getTailleC()][this.getTailleL()];
            double [][] matTemp = new double[this.getTailleC()][this.getTailleL()];
            ArrayList<Element> elem = new ArrayList<>();
            int var=0;
            int tour=1;
            for (int i=0;i<this.getTailleL();i++){
                for (int j=0;j<this.getTailleC();j++){
                    mat1[i][j]= this.getElement().get(var).getValeur();
                    matRes[i][j]= this.getElement().get(var).getValeur();
                    var++;
                }
            }
            if (nombre==0){
                int chiffre=0;
                for (int i=0;i<this.getTailleL();i++){
                    for (int j=0;j<this.getTailleC();j++){
                        if (i==j){
                            elements.get(j+chiffre).setValeur(1);
                        }else {
                            elements.get(j+chiffre).setValeur(0);
                        }
                    }
                    chiffre=chiffre+this.getTailleC();
                }
                matriceRes.setElement(elements);
                matriceRes.setTailleL(this.getTailleL());
                matriceRes.setTailleC(this.getTailleC());
                return matriceRes;
            }else {
                while (nombre!=tour){
                    for(int i = 0; i < this.getTailleL(); i++) {
                        for (int j = 0; j < this.getTailleC(); j++) {
                            for (int k = 0; k < this.getTailleC(); k++) {
                                matTemp[i][j]+= mat1[k][j]*matRes[i][k];
                            }
                        }
                    }
                    for (int i=0;i<this.getTailleL();i++){
                        for (int j=0;j<this.getTailleC();j++){
                            matRes[i][j]= matTemp[i][j];
                            matTemp[i][j]=0;
                        }
                    }
                    tour++;
                }
                for (int i=0;i<this.getTailleL();i++){
                    for (int j=0;j<this.getTailleC();j++){
                        Element element = new Element();
                        element.setValeur(matRes[i][j]);
                        elem.add(element);
                    }
                }
                matriceRes.setElement(elem);
                matriceRes.setTailleL(this.getTailleL());
                matriceRes.setTailleC(this.getTailleC());
                return matriceRes;
            }


        }
        else {
            return null;
        }
    }
    public Matrice inversion(){
        this.determinant();
       if (verif5(this)) {
           Matrice matriceRes = new Matrice();
           if (this.getTailleL()==1){

               Element element = new Element();
               element.setValeur(1/this.getElement().get(0).getValeur());
               ArrayList<Element> oneElement = new ArrayList<>();
               oneElement.add(element);
               matriceRes.setElement(oneElement);
               matriceRes.setTailleL(1);
               matriceRes.setTailleC(1);
               return matriceRes;
           }
           else {
               int var = 0;
               int valeur = 0;
               ArrayList<Element> autre = new ArrayList<>();
               for (int i = 0; i < this.getTailleL(); i++) {
                   for (int j = 0; j < this.getTailleC(); j++) {
                       autre.add(this.getElement().get(j + var));
                   }
                   for (int j = 0; j < this.getTailleC(); j++) {
                       if (j == valeur) {
                           Element element = new Element();
                           element.setValeur(1);
                           autre.add(element);
                       } else {
                           Element element = new Element();
                           element.setValeur(0);
                           autre.add(element);
                       }
                   }
                   valeur++;
                   var = var + this.getTailleC();
               }
               int tourRestant=1;
               int var1=0;
               int var2=0;
               for (int k=0;k<this.getTailleC();k++){
                   for (int j=0;j<this.getTailleL()-tourRestant;j++){
                       for (int i=0;i<(this.getTailleC()*2)-(tourRestant-1);i++){
                           if (autre.get(var1).getValeur()!=0){
                               autre.get(i+var1).setValeur(autre.get(i+var1).getValeur()/autre.get(var1).getValeur());
                           }
                       }
                       var2=var2+(this.getTailleC()*2);
                       for (int i=0;i<(this.getTailleC()*2)-(tourRestant-1);i++){
                           autre.get(i+var1).setValeur(autre.get(i+var1).getValeur()*autre.get(i+var2).getValeur());
                       }
                       for (int i=0;i<(this.getTailleC()*2)-(tourRestant-1);i++){
                           autre.get(i+var2).setValeur(autre.get(i+var2).getValeur()-autre.get(i+var1).getValeur());
                       }
                   }
                   tourRestant++;
                   var1=var1+(this.getTailleC()*2)+1;
                   var2=var1;
               }
               tourRestant=1;
               var1=(this.getTailleC()*2)+1;
               var2=var1;
               for (int k=0;k<this.getTailleC()-1;k++){
                   for (int j=0;j<this.getTailleL()-(this.getTailleL()-tourRestant);j++){
                       for (int i=0;i<(this.getTailleC()*2)-tourRestant;i++){
                           if (autre.get(var1).getValeur()!=0){
                               autre.get(i+var1).setValeur(autre.get(i+var1).getValeur()/autre.get(var1).getValeur());
                           }
                       }
                       var2=var2-(this.getTailleC()*2);
                       for (int i=0;i<(this.getTailleC()*2)-tourRestant;i++){
                           autre.get(i+var1).setValeur(autre.get(i+var1).getValeur()*autre.get(var2).getValeur());
                       }
                       for (int i=0;i<(this.getTailleC()*2)-tourRestant;i++){
                           autre.get(i+var2).setValeur(autre.get(i+var2).getValeur()-autre.get(i+var1).getValeur());
                       }
                   }
                   tourRestant++;
                   var1=var1+(this.getTailleC()*2)+1;
                   var2=var1;
               }
               ArrayList<Element> bonne = new ArrayList<>();
               int chiffre=this.getTailleC();
               for (int j=0;j<this.getTailleL();j++){
                   for (int i=0;i<this.getTailleC();i++){
                    bonne.add(autre.get(chiffre+i));
                   }
                   chiffre=chiffre+this.getTailleC();
               }
               matriceRes.setElement(bonne);
               matriceRes.setTailleC(this.getTailleC());
               matriceRes.setTailleL(this.getTailleL());
               return matriceRes;
           }


       }else {
           return null;
       }


    }
    public Matrice transposition(){
        Matrice matriceRes = new Matrice();
            ArrayList<Element> elem = new ArrayList<>();
            int var=0;
            for (int j=0;j<this.getTailleC();j++){
                for (int i=0;i<this.getTailleL();i++){
                    elem.add(this.getElement().get(var));
                    var=var+this.getTailleC();
                }
                var=var-(this.getTailleC()*(this.getTailleL()-1)-1+this.getTailleC());
            }
            matriceRes.setElement(elem);
            matriceRes.setTailleL(this.getTailleC());
            matriceRes.setTailleC(this.getTailleL());
            return matriceRes;
    }
    //Manque This
    public Matrice determinant (){
        if (verif3(this)){
            double rep=1;
            double positif=0;
            double negatif=0;
            switch (this.getTailleL()){
                case 1:rep=(this.getElement().get(0).getValeur());
                    this.setDeterminant(rep);
                    break;
                case 2: rep=((this.getElement().get(0).getValeur()*this.getElement().get(3).getValeur())-(this.getElement().get(1).getValeur()*this.getElement().get(2).getValeur()));
                    this.setDeterminant(rep);
                    break;
                case 3: positif=((this.getElement().get(0).getValeur()*this.getElement().get(4).getValeur()*this.getElement().get(8).getValeur())+(this.getElement().get(1).getValeur()*this.getElement().get(5).getValeur()*this.getElement().get(6).getValeur())+(this.getElement().get(2).getValeur()*this.getElement().get(3).getValeur()*this.getElement().get(7).getValeur()));
                    negatif=((this.getElement().get(6).getValeur()*this.getElement().get(4).getValeur()*this.getElement().get(2).getValeur())+(this.getElement().get(7).getValeur()*this.getElement().get(5).getValeur()*this.getElement().get(0).getValeur())+(this.getElement().get(8).getValeur()*this.getElement().get(3).getValeur()*this.getElement().get(1).getValeur()));
                    rep=(positif-negatif);
                    this.setDeterminant(rep);
                    break;
                default:
                    int var= 0;
                    int var1=0;
                    int position=1;
                    int ligne=1;
                    int tour=0;
                    double constante1=0;
                    int ok=0;
                    ArrayList<Element> elements = new ArrayList<>();
                    for (int i=0;i<this.getElement().size();i++){
                        Element elem = new Element();
                        elem.setValeur(this.getElement().get(i).getValeur());
                        elements.add(elem);
                    }
                    ArrayList<Number> tabcons = new ArrayList<>();
                    for (int k=0;k<this.getTailleL()-1;k++){
                        position=1;
                        tabcons.clear();
                        for (int i=0;i<this.getTailleL()-(tour+1);i++){
                            constante1=(elements.get(ok+(this.getTailleC()*position)).getValeur()/elements.get(tour+(this.getTailleC()*tour)).getValeur());
                            tabcons.add(constante1);
                            position++;
                        }
                        for (int i=0;i<this.getTailleL()-(tour+1);i++){
                            for (int j=0;j<this.getTailleC()-tour;j++){
                                elements.get((this.getTailleC()*ligne)+ok+j).setValeur(elements.get((this.getTailleC()*ligne)+ok+j).getValeur()-(tabcons.get(ligne-1).doubleValue()*elements.get(var1+j).getValeur()));
                            }
                            ligne++;
                        }
                        ok=ok+this.getTailleC()+1;
                        tour++;
                        var1=var1+this.getTailleC()+1;
                        ligne=1;

                    }
                    for (int i=0;i<this.getTailleL();i++){
                        rep*=elements.get(var).getValeur();
                        var=var+1+this.getTailleL();
                    }
                    double temp=0;
                    temp=(double)Math.round(rep*100)/100;
                    this.setDeterminant(temp);
                    break;
            }
            return this;
        }else {
            return null;
        }
    }
    public Matrice produitVect( Matrice matrice2){
        if (verif4(this,matrice2)){
            double compI=0;
            double compJ=0;
            double compK=0;
            Matrice matriceRes= new Matrice();
            ArrayList<Element> elem = new ArrayList<>();
            Element elementI = new Element();
            Element elementJ = new Element();
            Element elementK = new Element();
            compI=((this.getElement().get(1).getValeur()*matrice2.getElement().get(2).getValeur())-(this.getElement().get(2).getValeur()*matrice2.getElement().get(1).getValeur()));
            compJ=((this.getElement().get(0).getValeur()*matrice2.getElement().get(2).getValeur())-(this.getElement().get(2).getValeur()*matrice2.getElement().get(0).getValeur()));
            compK=((this.getElement().get(0).getValeur()*matrice2.getElement().get(1).getValeur())-(this.getElement().get(1).getValeur()*matrice2.getElement().get(0).getValeur()));
            elementI.setValeur(compI);
            elementJ.setValeur(0-compJ);
            elementK.setValeur(compK);
            elem.add(elementI);
            elem.add(elementJ);
            elem.add(elementK);
            matriceRes.setElement(elem);
            matriceRes.setTailleL(this.getTailleL());
            matriceRes.setTailleC(this.getTailleC());
            return matriceRes;
        }
        else {
            return null;
        }

    }
    public Matrice produitHadamard(Matrice matrice2){
        if (verif(this,matrice2)){
            Matrice matriceRes= new Matrice();
            ArrayList<Element> elem = new ArrayList<>();
            for (int i=0;i<this.getElement().size();i++){
                Element element = new Element();
                element.setValeur(this.getElement().get(i).getValeur()*matrice2.getElement().get(i).getValeur());
                elem.add(element);
            }
            matriceRes.setElement(elem);
            matriceRes.setTailleL(this.getTailleL());
            matriceRes.setTailleC(this.getTailleC());
            return matriceRes;
        }
        else{
            return null;
        }
    }
    public Matrice produitTensoriel(Matrice matrice2){
        Matrice matriceRes= new Matrice();
        ArrayList<Element> elem = new ArrayList<>();
        int var=0;
        int var1=0;
        for (int n=0;n<this.getTailleL();n++){
            for (int k=0;k<matrice2.getTailleL();k++){
                for (int i=0;i<this.getTailleC();i++){
                    for (int j=0;j<matrice2.getTailleC();j++){
                        Element element = new Element();
                        element.setValeur(this.getElement().get(var).getValeur()*matrice2.getElement().get(j+var1).getValeur());
                        elem.add(element);
                    }
                    var++;
                }
                var=var-this.getTailleC();
                var1=var1+matrice2.getTailleC();
            }
            var1=0;
            var=var+this.getTailleC();
        }
        matriceRes.setElement(elem);
        matriceRes.setTailleL(this.getTailleL()*matrice2.getTailleL());
        matriceRes.setTailleC(this.getTailleC()*matrice2.getTailleC());
        return matriceRes;
    }

    public Boolean verif(Matrice matrice1, Matrice matrice2){
        if (matrice1.getTailleC()== matrice2.getTailleC() && matrice1.getTailleL()== matrice2.getTailleL()){
            return true;
        }
        else {
            return false;
        }
    }
    public Boolean verif2(Matrice matrice1, Matrice matrice2){
        if (matrice1.getTailleC()== matrice2.getTailleL()){
            return true;
        }
        else {
            return false;
        }
    }
    public Boolean verif3(Matrice matrice1){
        if (matrice1.getTailleL()==matrice1.getTailleC()){
            return true;
        }
        else {
            return false;
        }
    }
    public Boolean verif4(Matrice matrice1,Matrice matrice2){
        if (matrice1.getTailleL()==1 && matrice2.getTailleL()==1 && matrice1.getTailleC()==3 && matrice1.getTailleC()==3){
            return true;
        }
        else {
            return false;
        }
    }
    public Boolean verif5(Matrice matrice1){
        if (verif3(matrice1) && matrice1.getDeterminant()!=0){
            return true;
        }
        else {
            return false;
        }
    }
}
