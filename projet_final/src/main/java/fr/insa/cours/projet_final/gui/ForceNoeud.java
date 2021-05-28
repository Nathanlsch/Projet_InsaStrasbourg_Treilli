/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

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
public class ForceNoeud extends BorderPane{
    
    private int longueurMin = 200;
    private int longueurMax = 200;
    private int hauteur = 30;
    private TextField TFcomposanteX;
    private TextField TFcomposanteY;
    private TextArea TAcomposanteX;
    private TextArea TAcomposanteY;
    private Button ajouter;
    private mainPane main;
    
    public ForceNoeud(mainPane main){
    
    this.main = main;
    
    TFcomposanteX = new TextField();
    TFcomposanteX.setMaxSize(longueurMax, hauteur);
    TFcomposanteX.setMinSize(longueurMin, hauteur);
    
    TFcomposanteY = new TextField();
    TFcomposanteY.setMaxSize(longueurMax, hauteur);
    TFcomposanteY.setMinSize(longueurMin, hauteur);
    
    VBox conteneur = new VBox(getTFcomposanteX(), getTFcomposanteY());
    
    conteneur.setSpacing(10);
    
    TAcomposanteX = new TextArea("Composante sur X");
    TAcomposanteX.setMinSize(longueurMax, hauteur);
    TAcomposanteX.setMaxSize(longueurMax, hauteur);
    TAcomposanteX.setEditable(false);
    
    TAcomposanteY = new TextArea("Composante sur Y");
    TAcomposanteY.setMinSize(longueurMax, hauteur);
    TAcomposanteY.setMaxSize(longueurMax, hauteur);
    TAcomposanteY.setEditable(false);
    
    VBox conteneur2 = new VBox(TAcomposanteX, TAcomposanteY); 
    
    conteneur2.setSpacing(10);
    
    ajouter = new Button("Ajouter");
    HBox conteneur4 = new HBox(ajouter);
    conteneur4.setAlignment(Pos.CENTER);
    HBox conteneur3 = new HBox(conteneur2, conteneur);
    VBox principal = new VBox(conteneur3, conteneur4);
    this.setCenter(principal);
    principal.setSpacing(10);  
    
    ajouter.setOnAction((t) -> {
        this.main.getControleur().ajouterForce(t);
    });
    }

    /**
     * @return the TFcomposanteX
     */
    public TextField getTFcomposanteX() {
        return TFcomposanteX;
    }

    /**
     * @return the TFcomposanteY
     */
    public TextField getTFcomposanteY() {
        return TFcomposanteY;
    }
    
}
