/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import fr.insa.cours.projetv2.gui.AffichageForce;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author francois
 */
public abstract class Treillis {

    /**
     * null si aucun Groupe
     */
    private Groupe groupe;
    
    
    public static Numeroteur<Treillis> num = new Numeroteur();
    
    public static Numeroteur<Treillis> num2 = new Numeroteur();
    
    public static ArrayList<Treillis> Save = new ArrayList<Treillis>();

    public Groupe getGroupe() {
        return groupe;
    }

    void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    public void clear() {
        List<Treillis> toRemove = new ArrayList<>(this.Save);
        this.removeAll(toRemove);
    }
    
    public void removeAll(List<Treillis> lf) {
        for(Treillis f : lf) {
            this.remove(f);
        }
    }
    
     public void remove(Treillis f) {
        if (f.getGroupe() != this) {
            throw new Error("la figure n'est pas dans le groupe");
        }
        this.Save.remove(f);
        f.setGroupe(null);
    }
   /* public abstract double maxX();
    public abstract double minX();
    public abstract double maxY();
    public abstract double minY();*/
    
    //public abstract double distancePoint(Point p);
    
   // public abstract void draw(GraphicsContext gc);
    
    public abstract void dessine(GraphicsContext context);
    
    public abstract double distancePoint(Point p2);

    public abstract void dessineSelection(GraphicsContext context); 
        
    public abstract void Identificateur(Numeroteur<Treillis> num);
    
    public abstract boolean suppr(GraphicsContext context);
   
    public abstract void save(Writer w) throws IOException;
    
    public void sauvegarde(File fout) throws IOException{
        try (BufferedWriter bout = new BufferedWriter(new FileWriter(fout))){
            this.save(bout);
        }
    }
    
    public static Treillis lecture(File fin) throws IOException {
        Treillis derniere = null;
        try(BufferedReader bin = new BufferedReader(new FileReader(fin))) {
            String line;
            while((line = bin.readLine())!= null && line.length() != 0){
                String[] bouts = line.split(";");
                if(bouts[0].equals("Point")){
                    int id = Integer.parseInt(bouts[1]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    Point np = new Point(id ,px, py, col);
                    System.out.println("check 1");
                    num2.associe(id, np);
                    System.out.println("check 2");
                    derniere = np;
                } else if (bouts[0].equals("SegmentTerrain")){
                    int id = Integer.parseInt(bouts[1]);
                    int idP1 = Integer.parseInt(bouts[2]);
                    int idP2 = Integer.parseInt(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    Point p1 = (Point) num2.getObj(idP1);
                    Point p2 = (Point) num2.getObj(idP2);
                    SegmentTerrain ns = new SegmentTerrain(id ,p1, p2, col);
                    num2.associe(id, ns);
                    derniere = ns;
                } else if (bouts[0].equals("TriangleTerrain")){
                    int id = Integer.parseInt(bouts[1]);
                    int idP1 = Integer.parseInt(bouts[2]);
                    int idP2 = Integer.parseInt(bouts[3]);
                    int idP3 = Integer.parseInt(bouts[4]);
                    int idS1 = Integer.parseInt(bouts[5]);
                    int idS2 = Integer.parseInt(bouts[6]);
                    int idS3 = Integer.parseInt(bouts[7]);
                    Color col = FigureSimple.parseColor(bouts[8],bouts[9],bouts[10]);
                    Point p1 = (Point) num2.getObj(idP1);
                    Point p2 = (Point) num2.getObj(idP2);
                    Point p3 = (Point) num2.getObj(idP3);
                    SegmentTerrain s1 = (SegmentTerrain) num2.getObj(idS1);
                    SegmentTerrain s2 = (SegmentTerrain) num2.getObj(idS2);
                    SegmentTerrain s3 = (SegmentTerrain) num2.getObj(idS3);
                    TriangleTerrain nt = new TriangleTerrain(id ,p1, p2, p3, s1, s2, s3, col);
                    num2.associe(id, nt);
                    derniere = nt;
                } else if (bouts[0].equals("NoeudSimple")){
                    int id = Integer.parseInt(bouts[1]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    NoeudSimple nns = new NoeudSimple(id ,px, py, col);
                    num2.associe(id, nns);
                    derniere = nns;
                } else if (bouts[0].equals("AppuiSimple")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    System.out.println("check Triangle2");
                    TriangleTerrain t1 = (TriangleTerrain) num2.getObj(idT1);
                    System.out.println("check Triangle3");
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiSimple nas = new AppuiSimple(id ,t1, p1, alpha, col);
                    
                    num2.associe(id, nas);
                    
                    derniere = nas;
                } else if (bouts[0].equals("AppuiDouble")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    TriangleTerrain t1 = (TriangleTerrain) num2.getObj(idT1);
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiDouble nad = new AppuiDouble(id ,t1, p1, alpha, col);
                    num2.associe(id, nad);
                    derniere = nad;
                } else if (bouts[0].equals("AppuiEncastre")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    TriangleTerrain t1 = (TriangleTerrain) num2.getObj(idT1);
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiEncastre nae = new AppuiEncastre(id ,t1, p1, alpha, col);
                    num2.associe(id, nae);
                    derniere = nae;
                } else if (bouts[0].equals("Barre")){
                    int id = Integer.parseInt(bouts[1]);
                    int idN1 = Integer.parseInt(bouts[2]);
                    int idN2 = Integer.parseInt(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    Noeud n1 = (Noeud) num2.getObj(idN1);
                    Noeud n2 = (Noeud) num2.getObj(idN2);
                    Barre nb = new Barre(id ,n1, n2, col);
                    num2.associe(id, nb);
                    derniere = nb;
                } else if (bouts[0].equals("Groupe")){
                    int id = Integer.parseInt(bouts[1]);
                    Groupe ng = new Groupe(id);
                    num2.associe(id, ng);
                    for ( int i =2; i< bouts.length; i++){
                        int idSous = Integer.parseInt(bouts[i]);
                        Treillis tre = num2.getObj(idSous);
                        ng.add(tre);
                        if(tre instanceof Terrain){
                            ng.setTerrain((Terrain) tre);
                        }
                    }
                    Save.clear();
                    derniere = ng;
                } else if(bouts[0].equals("Terrain")){
                    int id = Integer.parseInt(bouts[1]);
                    double pxmin = Double.parseDouble(bouts[2]);
                    double pxmax = Double.parseDouble(bouts[3]);
                    double pymin = Double.parseDouble(bouts[4]);
                    double pymax = Double.parseDouble(bouts[5]);
                    Color col = FigureSimple.parseColor(bouts[6],bouts[7],bouts[8]);
                    Terrain terrain = new Terrain(id,pxmin,pxmax,pymin,pymax,col);
                    num2.associe(id, terrain);
                    for ( int i =9; i< bouts.length; i++){
                        int idSous = Integer.parseInt(bouts[i]);
                        Treillis tri = num2.getObj(idSous);
                            terrain.getContientTriangle().add((TriangleTerrain) tri);
                    }
                    derniere = terrain;
                } else if (bouts[0].equals("TypeDeBarre")){
                    int id = Integer.parseInt(bouts[1]);
                    String nom = bouts[2];
                    double cout = Double.parseDouble(bouts[3]);
                    double longMin = Double.parseDouble(bouts[4]);
                    double longMax = Double.parseDouble(bouts[5]);
                    double maxTension = Double.parseDouble(bouts[6]);
                    double maxCompression = Double.parseDouble(bouts[7]);
                    TypeDeBarre ntdb = new TypeDeBarre(id,nom,cout,longMin,longMax,maxTension,maxCompression);
                    num2.associe(id, ntdb);
                }
        }
        num = num2;
        return derniere;
    }
    }

        
        
    

    /**
     * @return the num
     */
    public Numeroteur<Treillis> getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(Numeroteur<Treillis> num) {
        this.num = num;
    }
   
    public static int getNombreBarre() {
        int nbrBarre = 0;
        for (int i=0; i < num.getIdversObjet().size();i++) {
            if (num.getObj(i) instanceof Barre) {
            nbrBarre++;
        }
        }
        return nbrBarre;
    }
  
    public int testForce(){
      int nombreNS=0;
      int nombreNB=0;
      int nombreNAS=0;
      int nombreNAD=0;
      int nombreN=0;
      boolean testAE=false;
      Set<Integer> set = this.num.parcours();
      for(Integer key: set){
           if(num.getObj(key) instanceof NoeudSimple){
               nombreNS = nombreNS + 1;
               nombreN = nombreN+1;
               
           }
           if(num.getObj(key) instanceof Barre){
               nombreNB = nombreNB + 1;         
           }
           
           if(num.getObj(key) instanceof AppuiDouble){
               nombreNAD = nombreNAD + 1;
               nombreN = nombreN+1;
               
           }
           if(num.getObj(key) instanceof AppuiSimple){
               nombreNAS = nombreNAS + 1;
               nombreN = nombreN+1;
               
           }
           if(num.getObj(key) instanceof AppuiEncastre){
               testAE = true;  
               nombreN = nombreN+1;
           }
      }
      
      if((2*nombreN == nombreNB+nombreNAS+2*nombreNAD)&&(testAE == false)){
          return 2*nombreN;
      } else {
          
          return 0;
      }  
   } 
  
    public int BarreMax(){
        System.out.println("Etape3");
      int barre;
      int barreMax=0;
      Set<Integer> set = this.num.parcours();
      for(Integer key: set){ 
          if((num.getObj(key) instanceof NoeudSimple)||(num.getObj(key) instanceof AppuiSimple)||(num.getObj(key) instanceof AppuiDouble)){
              barre = ((Noeud) num.getObj(key)).nombreBarre();
              if(barreMax<barre){
                  barreMax = barre;
              }
          }
      }
      return barreMax;
    }
    
    public void ajout(int[][] tab, int id,int ligne){
        System.out.println("Etape2");
        System.out.println(ligne);
        if(ligne==11){
            int i=0;
            while(tab[1][i]!=0){
                i=i+2;
            }
            tab[1][i]=id;
            tab[1][i+1]=id;
            System.out.println(tab);
        } else if (ligne==10){
            int y=0;
            while((tab[0][y]!=0)&&(tab[0][y]!=id)){
                y=y+1;
                System.out.println(y);
            }
            
            System.out.println(y);
            tab[0][y]=id;
            System.out.println(tab);
        } else if (ligne ==20) {
            int i=0;
            while(tab[0][i]!=0){
                System.out.println(tab[0][i]);
                i=i+1;
                System.out.println(tab[0][i]);
            }
            if(num.getObj(id) instanceof AppuiSimple){
               tab[0][i]=id; 
            }
            if(num.getObj(id) instanceof AppuiDouble){
               tab[0][i]=id; 
               tab[0][i+1]=id; 
            }
        }
    }
    
    public int numCol(int[][] tab, int id){
        int i=0;
        while(tab[0][i]!=id){
            i=i+1;
        }
        return i;
    }

    public void Force(AffichageForce force){
        Matrice carre = new Matrice(this.testForce(), this.testForce());
        Matrice vecteur = new Matrice(this.testForce(),1);
        int[][] info = new int[2][this.testForce()];
        if(this.testForce() !=0){
            int ligne =0;
            int nbMax = BarreMax();
            double valeur;
            double valeur1;
            double valeur2;
            Set<Integer> set = this.num.parcours();
            System.out.println("Etape1");
            for(Integer key: set){
               if(num.getObj(key) instanceof Noeud){
                   ajout(info,key,11);
                   vecteur.set(ligne, 0, ((Noeud)num.getObj(key)).getForcePx());
                   vecteur.set(ligne+1, 0, ((Noeud)num.getObj(key)).getForcePy());
                   for(Barre barre : ((Noeud)num.getObj(key)).getBarreAssos()){
                       int id = barre.getId();
                       ajout(info,id,10);
                       int col = numCol(info,id);
                       carre.set(ligne, col, Math.cos(barre.getAngle()));
                       carre.set(ligne+1, col, Math.sin(barre.getAngle())); 
                   }
                ligne = ligne+2;   
               }
            }
            ligne=0;
            for(Integer key: set){
               if(num.getObj(key) instanceof Noeud){
                   
                   if(num.getObj(key) instanceof AppuiSimple){
                       ajout(info,key,20);
                      int col = numCol(info,key);
                      SegmentTerrain sgt = ((AppuiSimple) num.getObj(key)).giveSegmentTerrain();
                      double angle = sgt.getDebut().getAngleOrientePoint(sgt.getFin()) + Math.PI/2;
                      carre.set(ligne, col, Math.cos(angle));
                      carre.set(ligne+1, col, Math.sin(angle));
                   }
                   if(num.getObj(key) instanceof AppuiDouble){
                       ajout(info,key,20);
                       int col = numCol(info,key);
                       carre.set(ligne, col, 1);
                       carre.set(ligne+1, col+1, 1);
                   }
                ligne = ligne+2;   
               }
            }
        System.out.println(carre);   
        } 
        boolean test2 = true;
        for(int i=0;i<this.testForce();i++){
            if(vecteur.get(i, 0)!=0){
                test2 = false;
            }
        }
        Matrice concat;
        if(test2==true){
            concat = carre;
        } else {
            concat = carre.concatCol(vecteur);
        }
        Matrice resSys = concat.ResSysLin();
        System.out.println(concat.ResSysLin());
        for(int i = 0; i<this.testForce();i++){
            if(num.getObj(info[0][i]) instanceof Barre){
                force.getTA().appendText("La barre d'identificateur "+info[0][i]+" subit une force de "+resSys.get(i, 0)+" Newton(s)\n");
            }
            if(num.getObj(info[0][i]) instanceof AppuiSimple){
                force.getTA().appendText("L'appui simple d'identificateur "+info[0][i]+" a une réaction de "+resSys.get(i, 0)+" Newton(s)\n");
            }
            if(num.getObj(info[0][i]) instanceof AppuiDouble){
                force.getTA().appendText("L'appui double d'identificateur "+info[0][i]+" a une réaction sur x de "+resSys.get(i, 0)+" et sur y de "+resSys.get(i+1, 0)+" Newton(s)\n");
                i=i+1;
            }
            
            
        }
    }
    
    public abstract String afficheInfo();


  
      
  }  
        
    
    

