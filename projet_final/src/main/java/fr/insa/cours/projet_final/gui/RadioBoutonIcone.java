/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import java.io.InputStream;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author lasch
 */
public class RadioBoutonIcone extends RadioButton {
    
    
    public RadioBoutonIcone(String relPathOfImageFile, double sizeX, double sizeY) {
        // chargement des icones : la systaxe 
        // this.getClass().getResourceAsStream(path) permet de retrouver 
        // un fichier en indiquant son chemin relatif par rapport au répertoire
        // contenant la classe de this.
        // Cela à condition que les fichiers correspondants aient bien été
        // copiés !! Pour cela, voir le tag <ressources> dans le fichier .pom
        InputStream is = this.getClass().getResourceAsStream(relPathOfImageFile);
        if (is == null) {
            this.setText("?? " + relPathOfImageFile);
        } else {
            Image img = new Image(is, sizeX, sizeY, false, true);
            this.setGraphic(new ImageView(img));

        }

    }
}

    

