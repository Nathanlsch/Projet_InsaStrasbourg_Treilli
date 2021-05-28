/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import fr.insa.cours.projetv2mod.TypeDeBarre;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static fr.insa.cours.projetv2mod.CatalogueDeBarre.listTypeDeBarre;
import javafx.scene.control.Button;

/**
 *
 * @author lasch
 */
public class VueTypeDeBarre extends BorderPane{
    
    private TableView<TypeDeBarre> tableau;
    private mainPane main;

    private ObservableList<TypeDeBarre> getList(ArrayList<TypeDeBarre> lf) {
      
       ObservableList<TypeDeBarre> list = FXCollections.observableArrayList();
     for(TypeDeBarre h : lf) {
         h.getBtMod().setOnAction((t) -> {
             main.getControleur().boutonModifier(t,h);
         });
         h.getBtSup().setOnAction((t) -> {
             main.getControleur().boutonSup(t,h);
         });
         h.getBtSelect().setOnAction((t) -> {
             main.getControleur().boutonSelect(t,h);
         });
         list.add(h);
     }
     
      return list;
    }
    
    public VueTypeDeBarre(mainPane main){
    
    this.main = main;
    tableau = new TableView<TypeDeBarre>();
    tableau.setMinSize(1000, 300);
        
    TableColumn<TypeDeBarre, String> nomC = new TableColumn("Nom");
    TableColumn<TypeDeBarre, Double> coutC = new TableColumn ("Coût au metre");
    TableColumn<TypeDeBarre, Double> longMinC = new TableColumn("Longueur minimum");
    TableColumn<TypeDeBarre, Double> longMaxC = new TableColumn("Longueur maximum");
    TableColumn<TypeDeBarre, Double> maxTensionC = new TableColumn("Tension maximum");
    TableColumn<TypeDeBarre, Double> maxCompressionC = new TableColumn ("Compression maximum");
    TableColumn<TypeDeBarre, Button> boutonModC = new TableColumn ("Modifier");
    TableColumn<TypeDeBarre, Button> boutonSupC = new TableColumn ("Supprimer");
    TableColumn<TypeDeBarre, Button> boutonSelectC = new TableColumn ("Selectionner");
    
    nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
    coutC.setCellValueFactory(new PropertyValueFactory<>("coutAuMetre"));
    longMinC.setCellValueFactory(new PropertyValueFactory<>("longueurMin"));
    longMaxC.setCellValueFactory(new PropertyValueFactory<>("longueurMax"));
    maxTensionC.setCellValueFactory(new PropertyValueFactory<>("resistanceMaxTension"));
    maxCompressionC.setCellValueFactory(new PropertyValueFactory<>("resistanceMaxCompression"));
    boutonModC.setCellValueFactory(new PropertyValueFactory<>("btMod"));
    boutonSupC.setCellValueFactory(new PropertyValueFactory<>("btSup"));
    boutonSelectC.setCellValueFactory(new PropertyValueFactory<>("btSelect"));
   
    ObservableList<TypeDeBarre> list = getList(listTypeDeBarre);
    tableau.setItems(list);
 
   
    tableau.getColumns().addAll(nomC, coutC, longMinC,longMaxC,maxTensionC,maxCompressionC, boutonModC, boutonSupC, boutonSelectC);
    HBox conteneur = new HBox(tableau);
    this.setTop(conteneur);
        
    }
    
    
    
 
     
 
    
}
