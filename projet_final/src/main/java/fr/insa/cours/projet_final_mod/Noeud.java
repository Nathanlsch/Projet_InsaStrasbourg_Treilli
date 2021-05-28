/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */


public abstract class Noeud extends FigureSimple{
    
    private double ForcePx;
    private double ForcePy;  
    private int id;
    private List<Barre> BarreAssos;
    
    public Noeud() {
      super(Color.BLACK);       
    }
    
    public Noeud(Color col) {
      super(col);       
    }
    
    
    public List<Barre> getBarreAssos() {
        return BarreAssos;
    }

    public void setBarreAssos(List<Barre> BarreAssos) {
        this.BarreAssos = BarreAssos;
    }
    
    public int nombreBarre(){
       return this.getBarreAssos().size();
    }
    
    public abstract Point getcoordAppui();
    
    
    @Override
    public abstract void save(Writer w) throws IOException;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public double getAngleOriente(Noeud N2) {
            Point P1 = this.getcoordAppui();
            Point P2 = N2.getcoordAppui();
            
            return Math.atan2(P2.getPy() - P1.getPy(), P2.getPx() - P1.getPx());
        }

    public double getForcePx() {
        return ForcePx;
    }

    public void setForcePx(double ForcePx) {
        this.ForcePx = ForcePx;
    }

    public double getForcePy() {
        return ForcePy;
    }

    public void setForcePy(double ForcePy) {
        this.ForcePy = ForcePy;
    }

 

   
        
        

}
