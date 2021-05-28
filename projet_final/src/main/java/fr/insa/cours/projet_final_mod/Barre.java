/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import static fr.insa.cours.projetv2mod.Treillis.Save;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



/**
 *
 * @author celiajoy
 */
public class Barre extends FigureSimple{
    
    private int id;
    private Noeud ndepart;
    private Noeud nfin;
    private TypeDeBarre typeDeBarre;
    private double angle;

    public Barre(Noeud ndepart, Noeud nfin) {
        super(Color.BLACK);
        this.Identificateur(num);
        this.ndepart = ndepart;
        this.ndepart.getBarreAssos().add(this);
        this.nfin = nfin; 
        this.nfin.getBarreAssos().add(this);
        this.angle = this.getAngleOriente();
    }
    
    public Barre(int id, Noeud ndepart, Noeud nfin, Color col) {
        super(col);
        this.id = id;
        this.ndepart = ndepart; 
        this.nfin = nfin; 
        this.ndepart.getBarreAssos().add(this);
        this.nfin.getBarreAssos().add(this);
        this.angle = this.getAngleOriente();
    }
    
    public Barre(Noeud ndepart, Noeud nfin, TypeDeBarre typeDeBarre) {
        super(Color.BLACK);
        this.Identificateur(num);
        this.ndepart = ndepart; 
        this.nfin = nfin;
        this.typeDeBarre = typeDeBarre;
        this.ndepart.getBarreAssos().add(this);
        this.nfin.getBarreAssos().add(this);
        this.angle = this.getAngleOriente();
        
    }

    public int getId() {
        return id;
    }

    public Noeud getNdepart() {
        return ndepart;
    }

    public Noeud getNfin() {
        return nfin;
    }

    public TypeDeBarre getTypeDeBarre() {
        return typeDeBarre;
    }
    
        @Override
    public String toString() {
        return "Barre;" + this.getId() + ";" + this.getNdepart() + ";" + this.getNdepart() + ";" + this.getTypeDeBarre() + ";" ;
    }

    
    @Override
    public void dessine(GraphicsContext context) {
        context.setStroke(this.getCouleur()); 
        context.strokeLine(this.getNdepart().getcoordAppui().getPx(),this.getNdepart().getcoordAppui().getPy(),this.getNfin().getcoordAppui().getPx(),this.getNfin().getcoordAppui().getPy());
        
    }


    @Override
    public void dessineSelection(GraphicsContext context) {
       context.setStroke(Color.RED);   
       context.strokeLine(this.getNdepart().getcoordAppui().getPx(),this.getNdepart().getcoordAppui().getPy(),this.getNfin().getcoordAppui().getPx(),this.getNfin().getcoordAppui().getPy());
        
        System.out.println("Barre select");
    }
    
   
    public double distancePoint(Point p) {
        double x1 = this.getNdepart().getcoordAppui().getPx();
        double y1 = this.getNdepart().getcoordAppui().getPy();
        double x2 = this.getNfin().getcoordAppui().getPx();
        double y2 = this.getNfin().getcoordAppui().getPy();
        double x3 = p.getPx();
        double y3 = p.getPy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.getNdepart().getcoordAppui().distancePoint(p);
        } else if (up > 1) {
            return this.getNfin().getcoordAppui().distancePoint(p);
        } else {
            Point p4 = new Point(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4.distancePoint(p);
        }
    }
    
    @Override
    public void Identificateur(Numeroteur<Treillis> num) {
        this.id = num.creeID(this);
    }
    
        @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
            this.ndepart.save(w);
            this.nfin.save(w);
             Save.add(this);
             w.append("Barre;"+this.getId()+";"+this.ndepart.getId()+";"+this.nfin.getId()+";"+ FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }
    
    public int getIdNdepart() {
        return this.ndepart.getId();
    }
    
    public int getIdNfin() {
        return this.nfin.getId();
    }

    

    @Override
    public boolean dansTerrain(Terrain terrain) {
        return true;
    }
    
    public double getAngleOriente() {
            Point P1 = this.ndepart.getcoordAppui();
            Point P2 = this.nfin.getcoordAppui();
            
            return Math.atan2(P2.getPy() - P1.getPy(), P2.getPx() - P1.getPx());
        }

    public double getAngle() {
        return angle;
    }

    @Override
    public boolean suppr(GraphicsContext context) {
        num.suprObj(this);
        this.id = -1;
        this.ndepart.getBarreAssos().remove(this);
        this.nfin.getBarreAssos().remove(this);
        this.ndepart = null;
        this.nfin = null;
        this.setTypeDeBarre(null);
        this.angle = 0;
        return true; 
    }

    /**
     * @param typeDeBarre the typeDeBarre to set
     */
    public void setTypeDeBarre(TypeDeBarre typeDeBarre) {
        this.typeDeBarre = typeDeBarre;
    }
    
    public double longueurBarre(){
        Point p1 = this.nfin.getcoordAppui();
        Point p2 = this.ndepart.getcoordAppui();
        return Math.sqrt((p1.getPx()-p2.getPx())*(p1.getPx()-p2.getPx())+(p1.getPy()-p2.getPy())*(p1.getPy()-p2.getPy()));
    }

    @Override
    public String afficheInfo() {
        String type = "";
        if(this.typeDeBarre == null){
            type = "Type de barre non défini";
        } else{
            type = this.typeDeBarre.afficheInfo();
        }
        String res = "Id barre: "+this.getId()+"\nLongueur barre : "+this.longueurBarre()+"\nId Noeud départ : "+this.ndepart.getId()+"\nId NoeudFin : "+this.nfin.getId()+"\n"+type;
                
        return res;
    }

    
}
