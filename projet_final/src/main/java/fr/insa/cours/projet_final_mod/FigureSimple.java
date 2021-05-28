/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.util.Map;
import javafx.scene.paint.Color;


/**
 *
 * @author francois
 */
public abstract class FigureSimple extends Treillis {

    private Color couleur;

    public FigureSimple(Color couleur) {
        this.couleur = couleur;
 
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    public static String saveColor(Color c){
        return c.getRed()+";"+c.getGreen()+";"+c.getBlue();
    }
    
    static Color parseColor(String sr, String sv, String sb) {
        double rouge = Double.parseDouble(sr);
        double vert = Double.parseDouble(sv);
        double bleu = Double.parseDouble(sb);
        return Color.color(rouge, vert, bleu);
    }
    
    public abstract boolean dansTerrain(Terrain terrain);
    
}
