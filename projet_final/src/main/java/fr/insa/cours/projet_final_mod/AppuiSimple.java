/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import static fr.insa.cours.projetv2mod.NoeudSimple.RAYON_IN_DRAW;
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

public class AppuiSimple extends Appui {

    public AppuiSimple(TriangleTerrain triangleTerrain, int p1, double posSurSegment) {
        super(triangleTerrain, p1, posSurSegment);
        this.setBarreAssos(new ArrayList<Barre>());
    }
    
    public AppuiSimple(int id, TriangleTerrain triangleTerrain, int p1, double posSurSegment, Color col) {
        super(id, triangleTerrain, p1, posSurSegment, col);
        this.setBarreAssos(new ArrayList<Barre>());
    }
    
        @Override
    public String toString() {
        return "AppuiSimple;" + this.getId() + ";" + this.getTriangleTerrain().getId() + ";" + this.getP1() + ";" + this.getPosSurSegment() + ";" ;
    }
    
    @Override
    public void dessine(GraphicsContext context) {
        Point p = this.getcoordAppui(this.getP1(),this.getTriangleTerrain(),this.getPosSurSegment());
        context.setFill(Color.BLUEVIOLET);
        context.fillOval(p.getPx()-RAYON_IN_DRAW ,p.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW); 
    }
    
        @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
            Save.add(this);
            this.getTriangleTerrain().save(w);
        w.append("AppuiSimple;"+this.getId()+";"+this.getTriangleTerrain().getId()+";"+this.getP1()+";"+this.getPosSurSegment()+";"+ FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }
    
    @Override
    public boolean dansTerrain(Terrain terrain) {
        if(this.getTriangleTerrain().dansTerrain(terrain)){
           return true; 
        } else {
            return false;
        }
    }

   

    @Override
    public boolean suppr(GraphicsContext context) {
        if(this.getBarreAssos().isEmpty()){
            this.suprAppui();
            return true;
        } else {
            System.out.println("Appui associé a d'autre objet");
            return false;
        }
    }

  


}
