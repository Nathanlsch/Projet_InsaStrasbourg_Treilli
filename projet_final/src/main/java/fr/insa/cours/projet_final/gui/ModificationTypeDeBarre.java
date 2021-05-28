/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import fr.insa.cours.projetv2mod.TypeDeBarre;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author lasch
 */
public class ModificationTypeDeBarre extends BorderPane{
   
     private int longueurMin = 200;
    private int longueurMax = 200;
    private int hauteur = 30;
    private TextField TFnom;
    private TextField TFcoutAuMetre;
    private TextField TFlongueurMin;
    private TextField TFlongueurMax;
    private TextField TFresistanceMaxTension;
    private TextField TFresistanceMaxCompression;
    private Button appliquer;
    
    private TextArea TAnom;
    private TextArea TAcoutAuMetre;
    private TextArea TAlongueurMin;
    private TextArea TAlongueurMax;
    private TextArea TAresistanceMaxTension;
    private TextArea TAresistanceMaxCompression;
    
    private mainPane main;
    
    
    public ModificationTypeDeBarre(mainPane main, TypeDeBarre type){
    
    this.main = main;
            
    TFnom = new TextField(type.getNom());
    TFnom.setMaxSize(longueurMax, hauteur);
    TFnom.setMinSize(longueurMin, hauteur);
    
    TFcoutAuMetre = new TextField(String.valueOf(type.getCoutAuMetre()));
    TFcoutAuMetre.setMaxSize(longueurMax, hauteur);
    TFcoutAuMetre.setMinSize(longueurMin, hauteur);
    
    TFlongueurMin = new TextField(String.valueOf(type.getLongueurMin()));
    TFlongueurMin.setMaxSize(longueurMax, hauteur);
    TFlongueurMin.setMinSize(longueurMin, hauteur);
    
    TFlongueurMax = new TextField(String.valueOf(type.getLongueurMax()));
    TFlongueurMax.setMaxSize(longueurMax, hauteur);
    TFlongueurMax.setMinSize(longueurMin, hauteur);
    
    TFresistanceMaxTension = new TextField(String.valueOf(type.getResistanceMaxTension()));
    TFresistanceMaxTension.setMaxSize(longueurMax, hauteur);
    TFresistanceMaxTension.setMinSize(longueurMin, hauteur);
    
    TFresistanceMaxCompression = new TextField(String.valueOf(type.getResistanceMaxCompression()));
    TFresistanceMaxCompression.setMaxSize(longueurMax, hauteur);
    TFresistanceMaxCompression.setMinSize(longueurMin, hauteur);
    
    VBox conteneur = new VBox(getTFnom(), getTFcoutAuMetre(), getTFlongueurMin(), getTFlongueurMax(), getTFresistanceMaxTension(), getTFresistanceMaxCompression());
    //this.setCenter(conteneur);
    conteneur.setSpacing(10);
    
    TAnom = new TextArea("Nom du nouveau type");
    TAnom.setMinSize(longueurMax, hauteur);
    TAnom.setMaxSize(longueurMax, hauteur);
    TAnom.setEditable(false);
    
    TAcoutAuMetre = new TextArea("Cout au metre");
    TAcoutAuMetre.setMinSize(longueurMax, hauteur);
    TAcoutAuMetre.setMaxSize(longueurMax, hauteur);
    TAcoutAuMetre.setEditable(false);
    
    TAlongueurMin = new TextArea("Longueur minimum");
    TAlongueurMin.setMinSize(longueurMax, hauteur);
    TAlongueurMin.setMaxSize(longueurMax, hauteur);
    TAlongueurMin.setEditable(false);
    
    TAlongueurMax = new TextArea("Longueur maximum");
    TAlongueurMax.setMinSize(longueurMax, hauteur);
    TAlongueurMax.setMaxSize(longueurMax, hauteur);
    TAlongueurMax.setEditable(false);
    
    TAresistanceMaxTension = new TextArea("Resistance maximum à la tension");
    TAresistanceMaxTension.setMinSize(longueurMax, hauteur);
    TAresistanceMaxTension.setMaxSize(longueurMax, hauteur);
    TAresistanceMaxTension.setEditable(false);
    
    TAresistanceMaxCompression = new TextArea("Resistance max à la compression");
    TAresistanceMaxCompression.setMinSize(longueurMax, hauteur);
    TAresistanceMaxCompression.setMaxSize(longueurMax, hauteur);
    TAresistanceMaxCompression.setEditable(false);
    
    
    VBox conteneur2 = new VBox(TAnom, TAcoutAuMetre, TAlongueurMin, TAlongueurMax, TAresistanceMaxTension, TAresistanceMaxCompression);
    //this.setLeft(conteneur2);
    conteneur2.setSpacing(10);
    
    appliquer = new Button("Appliquer");
    HBox conteneur4 = new HBox(appliquer);
    conteneur4.setAlignment(Pos.CENTER);
    HBox conteneur3 = new HBox(conteneur2, conteneur);
    VBox principal = new VBox(conteneur3, conteneur4);
    this.setCenter(principal);
    principal.setSpacing(10);  
    
    
    appliquer.setOnAction((t) -> {
        this.main.getControleur().appliquer(t,type); 
    });
    
    
    
    
    }

    /**
     * @return the TFnom
     */
    public TextField getTFnom() {
        return TFnom;
    }

    /**
     * @return the TFcoutAuMetre
     */
    public TextField getTFcoutAuMetre() {
        return TFcoutAuMetre;
    }

    /**
     * @return the TFlongueurMin
     */
    public TextField getTFlongueurMin() {
        return TFlongueurMin;
    }

    /**
     * @return the TFlongueurMax
     */
    public TextField getTFlongueurMax() {
        return TFlongueurMax;
    }

    /**
     * @return the TFresistanceMaxTension
     */
    public TextField getTFresistanceMaxTension() {
        return TFresistanceMaxTension;
    }

    /**
     * @return the TFresistanceMaxCompression
     */
    public TextField getTFresistanceMaxCompression() {
        return TFresistanceMaxCompression;
    }
}
