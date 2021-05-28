/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

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
public class AffichageForce extends BorderPane{
    
    private TextArea TA;
    private Button visualiser;
    private TextField TF;
    private Controleur controleur;
    
    public AffichageForce(Controleur vue){
        
        this.controleur = vue;
        
        //Zone de texte
        this.TA = new TextArea();
        TA.setPrefHeight(400);
        TA.setPrefWidth(200);
        TA.setEditable(false);
        
        //Zone de saisie
        this.TF = new TextField();
        
        //Bouton visualiser
        visualiser = new Button("Visualiser");
        visualiser.setOnAction((t) -> {
            this.controleur.visualiser(TF.getText());
        });
        
        //Conteneur du bas 
        HBox conteneur1 = new HBox(visualiser, TF);
        
        //Conteneur du princpal
        VBox conteneur = new VBox(TA, conteneur1);
        this.setCenter(conteneur);  
    }

    
    //Accesseur
    /**
     * @return the TA
     */
    public TextArea getTA() {
        return TA;
    }

    /**
     * @param TA the TA to set
     */
    public void setTA(TextArea TA) {
        this.TA = TA;
    }

    /**
     * @return the visualiser
     */
    public Button getVisualiser() {
        return visualiser;
    }

    /**
     * @param visualiser the visualiser to set
     */
    public void setVisualiser(Button visualiser) {
        this.visualiser = visualiser;
    }
    
}
