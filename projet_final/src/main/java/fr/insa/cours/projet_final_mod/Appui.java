/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;


import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */
public abstract class Appui extends Noeud {
    
    public static double RAYON_IN_DRAW = 5;
    private TriangleTerrain triangleTerrain; 
    private int p1;
    private double posSurSegment;

     public Appui(TriangleTerrain triangleTerrain, int p1, double posSurSegment) {
        super();
        this.Identificateur(num);
        this.triangleTerrain = triangleTerrain;
        this.p1 = p1;
        this.posSurSegment = posSurSegment;
        this.setForcePx(0);
        this.setForcePy(0);
        if(p1 == 1){
            this.triangleTerrain.getSegment1().getListAppui().add(this);
        } else if( p1==2) {
            this.triangleTerrain.getSegment2().getListAppui().add(this);
        } else if (p1==3){
            this.triangleTerrain.getSegment3().getListAppui().add(this);
        } 
    }
     
     public Appui(int id, TriangleTerrain triangleTerrain, int p1, double posSurSegment, Color col) {
        super(col);
        this.setId(id);
        this.triangleTerrain = triangleTerrain;
        this.p1 = p1;
        this.posSurSegment = posSurSegment;
        this.setForcePx(0);
        this.setForcePy(0);
        if(p1 == 1){
            this.triangleTerrain.getSegment1().getListAppui().add(this);
        } else if( p1==2) {
            this.triangleTerrain.getSegment2().getListAppui().add(this);
        } else if (p1==3){
            this.triangleTerrain.getSegment3().getListAppui().add(this);
        }
    }

    public TriangleTerrain getTriangleTerrain() {
        return triangleTerrain;
    }

    public int getP1() {
        return p1;
    }

    public double getPosSurSegment() {
        return posSurSegment;
    }
   
 

    @Override
    public void dessine(GraphicsContext context) {
        Point p = this.getcoordAppui(this.getP1(),this.getTriangleTerrain(),this.getPosSurSegment());
        context.setFill(this.getCouleur());
        context.fillOval(p.getPx()-RAYON_IN_DRAW, p.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);  
    }

    @Override
    public double distancePoint(Point p2) {
        Point p = this.getcoordAppui(this.getP1(),this.getTriangleTerrain(),this.getPosSurSegment());
        double dx = p.getPx() - p2.getPx();
        double dy = p.getPy() - p2.getPy();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        Point p = this.getcoordAppui(this.getP1(),this.getTriangleTerrain(),this.getPosSurSegment());
        context.setFill(Color.RED);
        context.fillOval(p.getPx()-RAYON_IN_DRAW, p.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);  
    }

 
    public Point getcoordAppui(int p1,TriangleTerrain t ,double alpha ) {
        SegmentTerrain seg = new SegmentTerrain();
        
        if(p1 == 1){
             seg = t.getSegment1();
        }
        if(p1 == 2){
             seg = t.getSegment2();
        }
        if(p1 == 3){
             seg = t.getSegment3();
        }
        double px = seg.getDebut().getPx()+ alpha*(seg.getFin().getPx()-seg.getDebut().getPx());
        double py = seg.getDebut().getPy()+ alpha*(seg.getFin().getPy()-seg.getDebut().getPy());
        Point res = new Point(px,py);
        
       return res;
    }

    @Override
    public Point getcoordAppui() {
       Point res =  this.getcoordAppui(p1, triangleTerrain, posSurSegment);
       return res;
    }

    @Override
    public void Identificateur(Numeroteur<Treillis> num) {
        this.setId(num.creeID(this));
    }
    
    public void suprAppui(){
        this.setId(-1);
        if(p1 == 1){
            this.triangleTerrain.getSegment1().getListAppui().remove(this);
        } else if( p1==2) {
            this.triangleTerrain.getSegment2().getListAppui().remove(this);
        } else if (p1==3){
            this.triangleTerrain.getSegment3().getListAppui().remove(this);
        } 
        num.suprObj(this);
        this.triangleTerrain = null;
        this.p1 = -1;
        this.posSurSegment = -1;
        
    }
    
    //Renvoie le segment du triangle terrrain sur lequel l'appui est positionné
    public SegmentTerrain giveSegmentTerrain(){
        SegmentTerrain res = null;
        if (this.p1 == 1){
            res = this.triangleTerrain.getSegment1();
        }
        if (this.p1 == 2){
            res = this.triangleTerrain.getSegment2();
        }
        if (this.p1 == 3){
            res =  this.triangleTerrain.getSegment3();
        }
        return res;
    }
    
    @Override
     public String afficheInfo() {
        String res = "AppuiDouble : "+this.getId()+"\nTriangle porteur : "+this.getTriangleTerrain().getId()+"\nPoint du plus proche : "+this.getP1()+"\nPosition sur le segment : "+this.getPosSurSegment();
        return res;
    }
    
}
