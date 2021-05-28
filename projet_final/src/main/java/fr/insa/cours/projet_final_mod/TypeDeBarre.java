/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import com.sun.tools.javac.Main;
import fr.insa.cours.projetv2.gui.Controleur;
import fr.insa.cours.projetv2.gui.mainPane;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import static fr.insa.cours.projetv2mod.Treillis.Save;
import static fr.insa.cours.projetv2mod.CatalogueDeBarre.listTypeDeBarre;
import static fr.insa.cours.projetv2mod.Treillis.num;
import java.util.Set;
import javafx.scene.control.Button;

/**
 *
 * @author celiajoy
 */
public class TypeDeBarre extends Treillis {
    
    private int id;
    private String nom;
    private double coutAuMetre;
    private double longueurMin;
    private double longueurMax;
    private double resistanceMaxTension;
    private double resistanceMaxCompression;
    private  Button btMod = new Button("Modifier");
    private  Button btSup = new Button("Supprimer");
    private  Button btSelect = new Button("Selectionner");
    
    


    public TypeDeBarre(int id,String nom, double coutAuMetre, double longueurMin, double longueurMax, double resistanceMaxTension, double resistanceMaxCompression) {
        this.id = id;
        this.nom = nom;
        this.coutAuMetre = coutAuMetre;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.resistanceMaxTension = resistanceMaxTension;
        this.resistanceMaxCompression = resistanceMaxCompression;
        listTypeDeBarre.add(this);
       }
    
    public TypeDeBarre(String nom, double coutAuMetre, double longueurMin, double longueurMax, double resistanceMaxTension, double resistanceMaxCompression) {
        this.Identificateur(num);
        this.nom = nom;
        this.coutAuMetre = coutAuMetre;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.resistanceMaxTension = resistanceMaxTension;
        this.resistanceMaxCompression = resistanceMaxCompression;
        listTypeDeBarre.add(this);
    }

    public int getId() {
        return id;
    }
    
    
    public double getCoutAuMetre()
    {
        return this.coutAuMetre;
    }
    
    public double getLongueurMin()
    {
        return this.longueurMin;
    }
    
    public double getLongueurMax()
    {
        return this.longueurMax;
    }

    public double getResistanceMaxTension() {
        return resistanceMaxTension;
    }
    
    
    public double getResistanceMaxCompression()
    {
        return this.resistanceMaxCompression;
    }
    
  
    public void ResistanceMaxCompression(double resistanceMaxCompression)
    {
        this.setResistanceMaxCompression(resistanceMaxCompression);
    } 

    @Override
    public String toString() {
        return "TypeDeBarre;" + this.getId() + ";" + this.getLongueurMin() +";"+ this.getResistanceMaxTension()+";"+ this.getResistanceMaxCompression();
    }
    
    public void Identificateur(Numeroteur<Treillis> num) {
        this.setId(num.creeID(this));
    }

    @Override
    public void dessine(GraphicsContext context) {
        
    }

    @Override
    public double distancePoint(Point p2) {
        return 1000000;
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        
    }

    @Override
    public void save(Writer w) throws IOException {
        
        if(! Save.contains(this)){
             Save.add(this);
             w.append("TypeDeBarre;"+this.getId()+";"+this.getNom()+";"+this.getCoutAuMetre()+";"+this.getLongueurMin()+
                    ";"+this.getLongueurMax()+";"+this.getResistanceMaxTension()+";"+this.getResistanceMaxCompression()+"\n");
        }
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param coutAuMetre the coutAuMetre to set
     */
    public void setCoutAuMetre(double coutAuMetre) {
        this.coutAuMetre = coutAuMetre;
    }

    /**
     * @param longueurMin the longueurMin to set
     */
    public void setLongueurMin(double longueurMin) {
        this.longueurMin = longueurMin;
    }

    /**
     * @param longueurMax the longueurMax to set
     */
    public void setLongueurMax(double longueurMax) {
        this.longueurMax = longueurMax;
    }

    /**
     * @param resistanceMaxTension the resistanceMaxTension to set
     */
    public void setResistanceMaxTension(double resistanceMaxTension) {
        this.resistanceMaxTension = resistanceMaxTension;
    }

    /**
     * @param resistanceMaxCompression the resistanceMaxCompression to set
     */
    public void setResistanceMaxCompression(double resistanceMaxCompression) {
        this.resistanceMaxCompression = resistanceMaxCompression;
    }

    /**
     * @return the btMod
     */
    public Button getBtMod() {
        return btMod;
    }

    /**
     * @param btMod the btMod to set
     */
    public void setBtMod(Button btMod) {
        this.btMod = btMod;
    }

    /**
     * @return the btSup
     */
    public Button getBtSup() {
        return btSup;
    }

    /**
     * @param btSup the btSup to set
     */
    public void setBtSup(Button btSup) {
        this.btSup = btSup;
    }
    
    public void supr(){
        Set<Integer> set = this.num.parcours();
        for(Integer key: set){
           if(num.getObj(key) instanceof Barre){
               if(((Barre)num.getObj(key)).getTypeDeBarre()==this){
                   ((Barre)num.getObj(key)).setTypeDeBarre(null);
               }
           }
        }
        num.suprObj(this);
        this.nom = null;
        this.coutAuMetre =0;
        this.longueurMin =0;
        this.longueurMax = 0;
        this.resistanceMaxTension=0;
        this.resistanceMaxCompression=0;
        listTypeDeBarre.remove(this);
}

    @Override
    public boolean suppr(GraphicsContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the btSelect
     */
    public Button getBtSelect() {
        return btSelect;
    }

    /**
     * @param btSelect the btSelect to set
     */
    public void setBtSelect(Button btSelect) {
        this.btSelect = btSelect;
    }

    @Override
    public String afficheInfo() {
        String res = "TypeDeBarre : "+this.getNom()+"\nId : "+this.getId()+"\nLongueurMin : "+this.getLongueurMin()+"\nLongueurMax : "+this.getLongueurMax()+
                    "\nCout au mètre : "+this.getCoutAuMetre()+"\nResistanceMaxTension : "+this.getResistanceMaxTension()+"\nResistanceMaxCompression : "+this.getResistanceMaxCompression()+"\n";
        return res;
    }
}
