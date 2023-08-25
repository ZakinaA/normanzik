/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Zakina
 */
public class Groupe {
    
    private int id;
    private String nom;
    private String dateCreation;
    private Genre genre ;
    private ArrayList<Titre> lesTitres;
    

    public Groupe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public ArrayList<Titre> getLesTitres() {
        return lesTitres;
    }

    public void setLesTitres(ArrayList<Titre> lesTitres) {
        this.lesTitres = lesTitres;
    }

   
    
    
}
