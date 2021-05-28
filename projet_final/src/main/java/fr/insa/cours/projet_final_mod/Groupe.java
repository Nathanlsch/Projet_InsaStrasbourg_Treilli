package fr.insa.cours.projetv2mod;

import fr.insa.cours.projetv2.recup.Lire;
import static fr.insa.cours.projetv2mod.Treillis.Save;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author lasch
 */


public class Groupe extends Treillis {
    
    private List<Treillis> contient;
    private int id;
    private Terrain terrain;
    
    public Groupe() {
        this.contient = new ArrayList<Treillis>();
        this.Identificateur(num);
    }
    
    public Groupe(int id) {
        this.contient = new ArrayList<Treillis>();
        this.id = id;
    }
    
    public boolean ContientNoeud(){
        boolean i = true;
        for(Treillis t : this.getContient()){
            if(t instanceof Noeud){
                i =false;
            }
        }
        return i;
    }
    

    public void add(Treillis f) {
        if (f.getGroupe() != this) {
            if (f.getGroupe() != null) {
                throw new Error("figure déja dans un autre groupe");
            }
            this.getContient().add(f);
            f.setGroupe(this);
        }
    }
    
    public void remove(Treillis f) {
        if (f.getGroupe() != this) {
            throw new Error("la figure n'est pas dans le groupe");
        }
        this.getContient().remove(f);
        f.setGroupe(null);
    }
    
    public void removeAll(List<Treillis> lf) {
        for(Treillis f : lf) {
            this.remove(f);
        }
    }
    
    public void clear() {
        List<Treillis> toRemove = new ArrayList<>(this.getContient());
        this.removeAll(toRemove);
    }

    public int size() {
        return this.getContient().size();
    }
    
 
    public Treillis plusProche(Point p, double distMax) {
        if (this.getContient().isEmpty()) {
            return null;
        } else {
            Treillis fmin = this.getContient().get(0);
            double min = fmin.distancePoint(p);
            if (fmin instanceof Noeud){
                min = min-0.5*min;
            }
            for (int i =1;i<this.getContient().size(); i++){
             Treillis fcur = this.getContient().get(i);
             double cur = fcur.distancePoint(p);
             if (fcur instanceof Noeud){
                cur = cur-0.5*cur;
            }
             if(cur<min){
                 min = cur;
                 fmin = fcur;
             }
            }
            if (min <= distMax) {
                return fmin;
            }else {
                return null;
            }
        }
    }
            
    @Override
  public double distancePoint(Point p) {
        if (this.getContient().isEmpty()) {
            return new Point(0,0).distancePoint(p);
        } else {
            double dist = this.getContient().get(0).distancePoint(p);
            for (int i = 1; i < this.getContient().size(); i++) {
                double cur = this.getContient().get(i).distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }
  }
        
    @Override
    public void dessine(GraphicsContext context) {
        for(Treillis f : this.getContient()){
            f.dessine(context);
        }
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
       for(Treillis f : this.getContient()){
            f.dessineSelection(context); 
    }
   }

    
    public Treillis NoeudplusProche(Point p, double distMax) {
        int i = 0;
        Treillis fmin = new Point();
        double min = 0;
        if (ContientNoeud()) {
            return null;
        } else {
            for(int z=0;z<this.getContient().size();z++){
            if( this.getContient().get(i) instanceof Noeud){
               fmin = this.getContient().get(i);
               min = fmin.distancePoint(p); 
               z=   this.getContient().size();
            } else {
               i=i+1;
            }
            }
            for (int y =i;y<this.getContient().size(); y++){
                if(this.getContient().get(y) instanceof Noeud){
                  Treillis fcur = this.getContient().get(y);
                  double cur = fcur.distancePoint(p);  
                  if(cur<min){
                  min = cur;
                  fmin = fcur;
                  }
                }
            }
            if (min <= distMax) {
                return fmin;
            }else {
                return null;
            }
    }
 }

    @Override
    public void Identificateur(Numeroteur<Treillis> num) {
        this.id = num.creeID(this);
    }
    
    @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
            Save.add(this);
        for(Treillis t : this.getContient()){
            t.save(w);
        }
        w.append("Groupe;"+this.id);
        for(Treillis t : this.getContient()){
            w.append(";"+num.getID(t));
        }
        w.append("\n");
        }
    }
    
    public static String indente(String toIndente, String prefix) {
        return prefix + toIndente.replaceAll("\n", "\n" + prefix);
    }
    
 @Override
    public String toString() {
        String res = "Groupe {\n";
        for (int i = 0; i < this.getContient().size(); i++) {
            res = res + indente(this.getContient().get(i).toString(), "  ") + "\n";
        }
        return res + "}";
    }

    /**
     * @return the terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * @param terrain the terrain to set
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    
    /**
     * @return the contient
     */
    public List<Treillis> getContient() {
        return contient;
    }

    @Override
    public boolean suppr(GraphicsContext context) {
        return false;
    }

    @Override
    public String afficheInfo() {
        return "";
    }
        
    }
    
    
    


