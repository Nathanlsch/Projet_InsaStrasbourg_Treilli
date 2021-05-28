
package fr.insa.cours.projetv2mod;

import java.text.DecimalFormat;
import fr.insa.cours.projetv2.recup.Lire;

public class Matrice {
    
    private int nbrLig;

    private int nbrCol;

  
    private double[][] coeffs;

   
    private static final double EPSILON_PIVOT = 1E-8;

    
    public Matrice(int nbrLig, int nbrCol) {
        this.nbrLig = nbrLig;
        this.nbrCol = nbrCol;
        this.coeffs = new double[nbrLig][nbrCol];
    }




    public static Matrice creeVecteur(double[] composantes) {
        Matrice res = new Matrice(composantes.length, 1);
        for (int i = 0; i < composantes.length; i++) {
            res.coeffs[i][0] = composantes[i];
        }
        return res;
    }

    //--------------- partie 1.4
    public int getNbrLig() {
        return this.nbrLig;
    }

    public int getNbrCol() {
        return this.nbrCol;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    public void set(int lig, int col, double val) {
        this.coeffs[lig][col] = val;
    }

    //--------------- non demandé : c'est simplement pour avoir éventuellement
    //  un affichage plus simple dans le sujet
    public static String formatDouble(double x) {
//        return formatDouble2Digits(x);
//        return formatDoubleMax2Decimales(x);
        return formatDoubleFixe(x);
    }

    public static String formatDoubleMax2Decimales(double x) {
        DecimalFormat f = new DecimalFormat("#.##");
        return f.format(x);
    }

    public static String formatDouble2Digits(double x) {
        return String.format("%+3.1f", x);
    }

    public static String formatDoubleFixe(double x) {
        return String.format("%+4.2E", x);
    }

    //--------------- partie 1.5
    @Override
    public String toString() {
        // oui, il serait plus efficace d'utiliser un {@link java.lang.StringBuilder}
        // mais ils n'ont pas été vu
        String res = "";
        for (int i = 0; i < nbrLig; i++) {
            res = res + "[";
            for (int j = 0; j < nbrCol; j++) {
                res = res + formatDouble(this.get(i, j));
                if (j < nbrCol - 1) {
                    res = res + " ";
                }
            }
            res = res + "]\n";
        }
        return res;
    }
    

    public Matrice concatLig(Matrice m2) {
        if (this.getNbrCol() != m2.getNbrCol()) {
            throw new Error("les matrices doivent avoir même nombre de colonnes");
        }
        Matrice res = new Matrice(this.getNbrLig() + m2.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                if (i < this.getNbrLig()) {
                    res.set(i, j, this.get(i, j));
                } else {
                    res.set(i, j, m2.get(i - this.getNbrLig(), j));
                }
            }
        }
        return res;
    }

    public Matrice concatCol(Matrice m2) {
        if (this.getNbrLig() != m2.getNbrLig()) {
            throw new Error("les matrices doivent avoir même nombre de lignes");
        }
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol() + m2.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                if (j < this.getNbrCol()) {
                    res.set(i, j, this.get(i, j));
                } else {
                    res.set(i, j, m2.get(i, j - this.getNbrCol()));
                }
            }
        }
        return res;
    }

    public Matrice subLignes(int ligMin, int ligMax) {
        if (ligMin < 0 || ligMax < ligMin || ligMax >= this.getNbrLig()) {
            throw new Error("indices lignes invalides");
        }
        Matrice res = new Matrice(ligMax - ligMin + 1, this.getNbrCol());
        for (int lig = 0; lig < res.getNbrLig(); lig++) {
            for (int col = 0; col < res.getNbrCol(); col++) {
                res.set(lig, col, this.get(ligMin + lig, col));
            }
        }
        return res;
    }

    public Matrice copie() {
        return this.subLignes(0, this.getNbrLig() - 1);
    }
    
    public int permuteLigne(int i1, int i2)
    {
        Matrice res = copie();
        for (int i=0; i<this.getNbrCol(); i++)
        {
          this.set(i1, i, res.get(i2, i));
          this.set(i2, i, res.get(i1, i));
        }
        
        if (i1 == i2)
        {
            return 1;
        }
        else
        {
            return -1;       
        }        
    }
    
    public void transvection(int i1, int i2)
    {
       if(this.get(i1, i1) == 0 || i1 > (this.getNbrCol()-1))
       {
           throw new Error("Division par zéro ou i1 < nc");
       }
       double p = (this.get(i2, i1)/this.get(i1, i1));
       for(int j=0; j< this.getNbrCol(); j++)
       {
           if(j==i1)
           {
               this.set(i2,i1, 0);
           }
           else
           {
             this.set(i2, j, this.get(i2, j)-(p*this.get(i1, j)));
           }         
       }
    }
    
    public int lignePlusGrandPivot(int e)
    {
        double pivot = Math.abs(this.get(e, e));
        int LPGP = e;
        for (int i = this.getNbrLig()-1; i>=e; i--)
        {
            if(Math.abs(this.get(i, e))>=pivot)
            {
                pivot = Math.abs(this.get(i, e));
                LPGP = i;
            }
        }
        if (pivot <= EPSILON_PIVOT)
        {
            return -1;
        }
        return LPGP;
    }

    
    public Matrice ResSysLin()
    {
        System.out.println("Matrice :");
        System.out.println(this);
        Matrice res = new Matrice(this.getNbrLig(),1);        
        for(int i=0; i<this.getNbrLig()-1; i++)
        {
            this.permuteLigne(i, this.lignePlusGrandPivot(i)); 
            for(int j=((this.getNbrLig())-1) ; j>i; j--)
            {
                this.transvection(i,j);
            }
        }
        Matrice save = this.copie();
        System.out.println("Matrice après descente :");
        System.out.println(this);
        for(int k=0; k<this.getNbrLig(); k++ )
        {
            for(int l=0; l<this.getNbrCol(); l++)
            {
                this.set(k, l, (save.get(k, l)/save.get(k, k)));
            }
        }
        System.out.println("Matrice après pivot unitaire :");
        System.out.println(this);
        for(int m=this.getNbrCol()-2; m>0; m--)
        {
            for(int n= m-1 ; n>-1; n--)
            {
              this.set(n, this.getNbrCol()-1, this.get(n, this.getNbrCol()-1)-this.get(m,this.getNbrCol()-1 )*this.get(n,m));
              this.set(n,m,0);
            }
        }
        System.out.println("Matrice après remontée :");
        System.out.println(this);
        for (int o =0; o<this.getNbrLig(); o++)
        {
            res.set(o,0, this.get(o, this.getNbrCol()-1));
        }
        System.out.println("Solutions du système linéaire :");
        return res;
    }
    
} 