/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

//import fr.insa.cours.projet_treilli.*;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



/**
 *
 * @author francois
 */
public class SegmentTerrain extends FigureSimple {
    
    private Point debut;
    private Point fin;
    private int id;
    private List<Appui> listAppui;
    
    
    
    public SegmentTerrain(){
        super(Color.GREEN);
        this.Identificateur(num);
        this.debut = new Point();
        this.fin = new Point();
        this.listAppui = new ArrayList<Appui>();
    }
    
    public SegmentTerrain(Point debut,Point fin){
        super(Color.GREEN);
        this.Identificateur(num);
        this.debut = debut;
        this.fin = fin;
        this.listAppui = new ArrayList<Appui>();
    }
    
    public SegmentTerrain(int id,Point debut,Point fin, Color col){
        super(col);
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.listAppui = new ArrayList<Appui>();
    }
    
    public double longueur() {
        SegmentTerrain s2;
        return this.getDebut().distancePoint(this.getFin());
    }
    
    @Override
    public double distancePoint(Point p) {
        double x1 = this.getDebut().getPx();
        double y1 = this.getDebut().getPy();
        double x2 = this.getFin().getPx();
        double y2 = this.getFin().getPy();
        double x3 = p.getPx();
        double y3 = p.getPy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.getDebut().distancePoint(p);
        } else if (up > 1) {
            return this.getFin().distancePoint(p);
        } else {
            Point p4 = new Point(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4.distancePoint(p);
        }
    }
    
    public Point distancePointDonnePoint(Point p) {
        double x1 = this.getDebut().getPx();
        double y1 = this.getDebut().getPy();
        double x2 = this.getFin().getPx();
        double y2 = this.getFin().getPy();
        double x3 = p.getPx();
        double y3 = p.getPy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.getDebut();
        } else if (up > 1) {
            return this.getFin();
        } else {
            Point p4 = new Point(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4;
        }
    }
    
    public String toString() {
        return "[" + this.getDebut() +
                "," + this.getFin().toString() + "]";
    }

    public double minX() {
        return Math.min(this.getDebut().minX(), this.getFin().minX());
    }

    @Override
    public void dessine(GraphicsContext context) {
        context.setStroke(this.getCouleur());
        context.strokeLine(this.getDebut().getPx(),this.getDebut().getPy(),this.getFin().getPx(),this.getFin().getPy());
        
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        context.setStroke(Color.RED);
        context.strokeLine(this.getDebut().getPx(),this.getDebut().getPy(),this.getFin().getPx(),this.getFin().getPy());
        
    }

    /**
     * @return the debut
     */
    public Point getDebut() {
        return debut;
    }

    /**
     * @param debut the debut to set
     */
    public void setDebut(Point debut) {
        this.debut = debut;
    }

    /**
     * @return the fin
     */
    public Point getFin() {
        return fin;
    }
    
    @Override
    public void Identificateur(Numeroteur<Treillis> num) {
        this.id = num.creeID(this);
    }
    
    @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
        this.debut.save(w);
        this.fin.save(w);
        Save.add(this);
        w.append("SegmentTerrain;"+this.getId()+";"+this.debut.getId()+";"+this.fin.getId()+";"
                + FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean dansTerrain(Terrain terrain) {
        return true;
    }

    @Override
    public boolean suppr(GraphicsContext context) {
        if(this.getListAppui().isEmpty()){
            num.suprObj(this);
            this.id =-1;
            this.debut = null;
            this.fin = null;
            return true;
        } else {
            System.out.println("Appui associé a d'autre objet");
            return false;
        }
    }

    /**
     * @return the listAppui
     */
    public List<Appui> getListAppui() {
        return listAppui;
    }

    /**
     * @param listAppui the listAppui to set
     */
    public void setListAppui(List<Appui> listAppui) {
        this.listAppui = listAppui;
    }

    @Override
    public String afficheInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    
}
