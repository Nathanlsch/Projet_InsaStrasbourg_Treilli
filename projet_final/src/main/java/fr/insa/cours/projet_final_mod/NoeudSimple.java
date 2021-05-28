/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import static fr.insa.cours.projetv2mod.Treillis.Save;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */
public class NoeudSimple extends Noeud {
    
    
    public static double RAYON_IN_DRAW = 5;
    private double px;
    private double py;
     
    public NoeudSimple (double px, double py) {
    this.Identificateur(num);
    this.px = px;
    this.py = py; 
    this.setForcePx(0);
    this.setForcePy(0);
    this.setBarreAssos(new ArrayList<Barre>());
    
}
    
    public NoeudSimple (int id, double px, double py, Color col) {
        super(col);
        this.px = px;
        this.py = py;
        this.setForcePx(0);
        this.setForcePy(0);
        this.setId(id);
        this.setBarreAssos(new ArrayList<Barre>());
    }

    @Override
    public String toString() {
        return "NoeudSimple;" + this.getId() + ";(" + this.px + "," + this.py + ')';
    } 

    @Override
    public void dessine(GraphicsContext context) {
        context.setFill(this.getCouleur());
        context.fillOval(this.px-RAYON_IN_DRAW, this.py-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);   
    }
    
    public void dessinesupr(GraphicsContext context) {
          context.fillOval(this.px,this.py,0,0);
    }

 

    @Override
    public double distancePoint(Point p2) {
        double dx = this.px - p2.getPx();
        double dy = this.py - p2.getPy();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        context.setFill(Color.RED);
        context.fillOval(this.px-RAYON_IN_DRAW, this.py-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }

    @Override
    public Point getcoordAppui() {
        Point res = new Point(this.px,this.py);
        return res;
    }
    
    @Override
    public void Identificateur(Numeroteur<Treillis> num) {
        this.setId(num.creeID(this));
    }
    
    @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
            Save.add(this);
        w.append("NoeudSimple;"+this.getId()+";"+this.px+";"+this.py+";"+ FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }

    @Override
    public boolean dansTerrain(Terrain terrain) {
        if(((terrain.getMinX()<this.px)&&(this.px<terrain.getMaxX()))&&((terrain.getMinY()<this.py)&&(this.py<terrain.getMaxY()))){
           return true; 
        } else {
            return false;
        }
    }

    @Override
    public boolean suppr(GraphicsContext context) {
        if(this.getBarreAssos().isEmpty()){
            num.suprObj(this);
            this.setId(-1);
            this.px = -1;
            this.py = -1;
            return true;
        } else {
            System.out.println("Noeud associé a d'autre objet");
            return false;
        }
    }

    @Override
    public String afficheInfo() {
        String res = "Id NoeudSimple : "+this.getId()+"\nCoordonné x : "+this.px+"\nCoordonné y : "+this.py+"\nForce sur x : "+this.getForcePx()+"\nForce sur y : "+this.getForcePy();
        return res;
    }

  
}