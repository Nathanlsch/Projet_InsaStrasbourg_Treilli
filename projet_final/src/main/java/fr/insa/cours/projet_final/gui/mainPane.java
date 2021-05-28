/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import fr.insa.cours.projetv2mod.Groupe;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author lasch
 */
public class mainPane extends BorderPane {
    
    private Groupe model;
    private Controleur controleur;
    private Stage inStage;
    private File curFile;
    
    private MainMenu menu;
    
    //Variable Dessin
    private DessinCanvas cvtest;
    
    //Variable Bouton
    
    private RadioBoutonIcone Select;
    private RadioBoutonIcone TriangleTerrain;
    private RadioBoutonIcone AppuiSimple;
    private RadioBoutonIcone AppuiDouble;
    private RadioBoutonIcone AppuiEncastre;
    private RadioBoutonIcone NoeudSimple;
    private RadioBoutonIcone Barre;
    private TextArea test;
    private Button assosType;
    private Button Supprimer;
    private Button assosForce;
    private Button information;
    
public mainPane(Stage inStage) {
        this(inStage, new Groupe());
    }

    public mainPane(Stage inStage, Groupe model) {
        this(inStage, null, model);
    }

   
    public mainPane(Stage inStage, File fromFile, Groupe model){ 
    
    this.setStyle("-fx-background-color:#444444;");
    this.inStage = inStage;
    this.curFile = fromFile;
    this.test = new TextArea();
    test.setPrefHeight(600);
    test.setPrefWidth(200);
    test.setEditable(false);
    test.setStyle("-fx-background-color:black;");
    
    test.appendText("Nouveau Treilli \nCliquez deux fois dans la zone \nde dessin pour définir la \nzone constructible\n");
 
    
    
    this.model = model;
    this.controleur = new Controleur(this);
    
    this.menu = new MainMenu(this);
    this.setTop(this.menu);
    
    this.assosType = new Button("Associer type de barre");
    this.assosType.setMinWidth(200);
    assosType.setOnAction((t) -> {
        this.controleur.assosTypeDeBarre();
    });
    
    this.assosForce = new Button("Associer Force");
    this.assosForce.setMinWidth(200);
    assosForce.setOnAction((t) -> {
        this.controleur.assosForce();
    });
    this.information = new Button("Information");
    this.information.setMinWidth(200);
    information.setOnAction((t) -> {
        this.controleur.information();
    });
    
    this.Supprimer = new Button("Supprimer");
    Supprimer.setOnAction((t) -> {
        this.getControleur().supprimer(t, cvtest.getRealCanvas().getGraphicsContext2D());
    });
    this.Supprimer.setMinWidth(200);
    VBox VBdroit = new VBox(Supprimer, assosType,assosForce,information, test);
    this.setRight(VBdroit);
    
   //Boite gauche
   
   
   
   Select = new RadioBoutonIcone("image/iconeSelect.png",60,24);

   Select.setOnAction((t) -> {
       this.controleur.boutonSelect(t);
   });
   TriangleTerrain = new RadioBoutonIcone("image/iconeTriangle.png",120,18);
   TriangleTerrain.setOnAction((t) -> {
       this.controleur.boutonTriangleTerrain(t);
   });
   AppuiSimple = new RadioBoutonIcone("image/iconeAS.png",120,18);
   AppuiSimple.setOnAction((t) -> {
       this.controleur.AppuiSimple(t);
   });
   AppuiDouble = new RadioBoutonIcone("image/iconeAD.png",120,18);
   AppuiDouble.setOnAction((t) -> {
       this.controleur.AppuiDouble(t);
   });
   AppuiEncastre = new RadioBoutonIcone("image/iconeAE.png",120,18);
   AppuiEncastre.setOnAction((t) -> {
       this.controleur.AppuiEncastre(t);
   });
   NoeudSimple = new RadioBoutonIcone("image/iconeNS.png",120,18);
   NoeudSimple.setOnAction((t) -> {
       this.controleur.NoeudSimple(t);
   });
   
   Barre = new RadioBoutonIcone("image/iconeBarre.png",90,18);
   Barre.setOnAction((t) -> {
       this.controleur.Barre(t);
   });
   
   
   
   ToggleGroup bgEtat = new ToggleGroup();
   Select.setToggleGroup(bgEtat);
   AppuiSimple.setToggleGroup(bgEtat);
   AppuiDouble.setToggleGroup(bgEtat);
   AppuiEncastre.setToggleGroup(bgEtat);
   NoeudSimple.setToggleGroup(bgEtat);
   TriangleTerrain.setToggleGroup(bgEtat);
   Barre.setToggleGroup(bgEtat);
   Select.setSelected(true);
   
   HBox boutons = new HBox(Select,TriangleTerrain, AppuiSimple,AppuiDouble , AppuiEncastre, NoeudSimple, Barre);
   boutons.setSpacing(10);
   VBox Conteneur = new VBox(menu, boutons);
   Conteneur.setSpacing(10);
   
   boutons.setMaxHeight(30);
   boutons.setMinHeight(30);
   this.setTop(Conteneur);
   Conteneur.setStyle("-fx-background-color:#444444;");
   
   
   //Debut dessin 
    this.cvtest = new DessinCanvas(this);
    this.cvtest.setStyle("-fx-background-color:white;");
    
    this.setCenter(this.cvtest);
    Border border = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT));
   this.cvtest.setBorder(border);
    
}  

    public void redrawAll(){
        this.getCvtest().redrawAll();
    }

    /**
     * @return the model
     */
    public Groupe getModel() {
        return model;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * @param controleur the controleur to set
     */
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
    
    public Stage getInStage() {
        return inStage;
    }
    
    public File getCurFile() {
        return curFile;
    }
    
     public void setCurFile(File curFile) {
        this.curFile = curFile;
    }

    /**
     * @return the test
     */
    public TextArea getTest() {
        return test;
    }

    /**
     * @return the cvtest
     */
    public DessinCanvas getCvtest() {
        return cvtest;
    }
    
}
