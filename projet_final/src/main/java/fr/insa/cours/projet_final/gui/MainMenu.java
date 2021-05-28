/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


/**
 *
 * @author lasch
 */
public class MainMenu extends MenuBar{
    
    private mainPane main;
    
    public MainMenu(mainPane main){
        this.main = main;
       
        Menu file = new Menu("Fichier");
        MenuItem nouveau = new MenuItem("Nouveau");
        nouveau.setOnAction((t) -> {
            this.main.getControleur().menuNouveau(t);
        });
        MenuItem save = new MenuItem("Enregistrer");
        save.setOnAction((t) -> {
            this.main.getControleur().menuSave(t);
        });
        MenuItem saveAs = new MenuItem("Enregistrer sous...");
        saveAs.setOnAction((t) -> {
            this.main.getControleur().menuSaveAs(t);
        });
        MenuItem ouvrir = new MenuItem("Ouvrir");
        ouvrir.setOnAction((t) -> {
            this.main.getControleur().menuOpen(t);
        });
        file.getItems().addAll(nouveau,save,saveAs,ouvrir);
        
        Menu ZoneDeTexte = new Menu("Zone de texte");
        MenuItem Activer = new MenuItem("Activer");
        Activer.setOnAction((t) -> {
            this.main.getControleur().activer(t);
        });
        MenuItem Desactiver = new MenuItem("Désactiver");
        Desactiver.setOnAction((t) -> {
            this.main.getControleur().desactiver(t);
        });
        ZoneDeTexte.getItems().addAll(Activer,Desactiver);
        
        Menu Aide = new Menu("Aide");
        MenuItem TriangleTerrain = new MenuItem("Créer un triangle terrain");
        TriangleTerrain.setOnAction((t) -> {
            this.main.getControleur().tt(t);
        });
        MenuItem Appui = new MenuItem("Créer un appui");
        Appui.setOnAction((t) -> {
            this.main.getControleur().appui(t);
        });
        MenuItem NoeudSimple = new MenuItem("Créer un noeud simple");
        NoeudSimple.setOnAction((t) -> {
            this.main.getControleur().noeudSimple(t);
        });
        MenuItem barre = new MenuItem("Créer une barre");
        barre.setOnAction((t) -> {
            this.main.getControleur().barre(t);
        });
        
        Aide.getItems().addAll(TriangleTerrain,Appui,NoeudSimple,barre);
        
        Menu TypeDeBarre = new Menu("Type de barre");
        MenuItem nouveauTypeDeBarre = new MenuItem("Nouveau type de barre");
        nouveauTypeDeBarre.setOnAction((t) -> {
            this.main.getControleur().TypeDeBarre(t);
        });
        
        MenuItem listeTypeDeBarre = new MenuItem("Liste type de barre");
        listeTypeDeBarre.setOnAction((t) -> {
            this.main.getControleur().listeTypeDeBarre(t);
        });
       
        
        TypeDeBarre.getItems().addAll(nouveauTypeDeBarre, listeTypeDeBarre);
        
        Menu Force = new Menu("Force");
        MenuItem calculforce = new MenuItem("Calcul des forces");
        calculforce.setOnAction((t) -> {
            this.main.getControleur().calculdeforce(t);
        });
        
        Force.getItems().addAll(calculforce);
        
        this.getMenus().addAll(file, ZoneDeTexte,TypeDeBarre,Force,Aide);
        
        
    }
    
}
