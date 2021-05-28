/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 *
 * @author lasch
 */
public class Numeroteur<TO> {
    
    private TreeMap<Integer,TO> idversObjet;
    private Map<TO,Integer> objetversId;
    
    private int prochainID;
    
    public Numeroteur() {
        this(0);
    }
    
    public Numeroteur(int prochainId) {
        this.prochainID = prochainId;
        this.idversObjet = new TreeMap<>();
        this.objetversId = new HashMap<>();
    }
    
    public int creeID(TO obj) {
        if(this.getObjetversId().containsKey(obj)){
            throw new Error("Objet" + obj + " déja dans le numéroteur");
        }
        this.getIdversObjet().put(this.getProchainID(), obj);
        this.getObjetversId().put(obj, this.getProchainID());
        this.prochainID = this.getProchainID() + 1;
        return this.getProchainID()-1;
    }
   
    public boolean objExist(TO obj){
        return this.getObjetversId().containsKey(obj);
    }
    
    public int getID(TO obj) {
        if(this.objExist(obj)){
            return this.getObjetversId().get(obj);
        } else {
            throw new Error("Objet "+ obj+" inconnu dans numéroteur");
        }
    }
    
     public int getOucreeID(TO obj) {
        if(this.objExist(obj)){
            return this.getObjetversId().get(obj);
        } else {
            return this.creeID(obj);
        }
    }
     
    public TO getObj(int id){
       if(! this.idExist(id)){
            throw new Error("Identificateur non existant");
        } else { 
           return this.getIdversObjet().get(id);
       }
    }
    
    public boolean idExist(int id){
        return this.getIdversObjet().containsKey(id);
    }
            
    public void associe(int id,TO obj){
        if(this.idExist(id)){
            throw new Error("Identificateur existant");
        } else {
            this.getIdversObjet().put(id, obj);
            this.getObjetversId().put(obj, id);
        }
    }
    
    public void suprObj(TO obj) {
        int id = getID(obj);
        this.getIdversObjet().remove(id, obj);
        this.getObjetversId().remove(obj, id);
    }


    public TreeMap<Integer,TO> getIdversObjet() {
        return idversObjet;
    }

    public Map<TO,Integer> getObjetversId() {
        return objetversId;
    }

    public int getProchainID() {
        return prochainID;
    }
    
    public Set<Integer> parcours(){
        return this.idversObjet.keySet(); 
    }
    
    
}
