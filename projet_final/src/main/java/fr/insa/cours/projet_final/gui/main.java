package fr.insa.cours.projetv2.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import fr.insa.cours.projetv2mod.Groupe;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author lasch
 */
public class main extends Application {
    
    public static void main(String[] args) {
        launch();
    }
    

    @Override
    public void start(Stage stage) {
        Scene sc = new Scene(new mainPane(stage),1000,600);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
        stage.show();
    }
    

    

}
