/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.DaoGroupe;
import java.sql.Connection;
import java.util.ArrayList;
import model.Genre;
import model.Groupe;

/**
 *
 * @author Zakina
 */
public class TestDaoGroupe {
    
    public static void main(String[] args) {
        Connection con = ConnexionBdd.ouvrirConnexion();
        
        Groupe leGroupe = DaoGroupe.getLeGroupe(con, 1);
        System.out.println(leGroupe.getNom());
        
        ArrayList<Groupe> lesGroupes = DaoGroupe.getLesGroupes(con);
        for (int i=0; i<lesGroupes.size(); i++){
            Groupe grp = lesGroupes.get(i);
            System.out.println("nom du groupe=" + grp.getNom() + " genre du groupe=" + grp.getGenre().getLibelle() );
        }
        
        Groupe gp =  new Groupe();
        gp.setNom("Les flash");
        gp.setDateCreation("2012-02-21");
        
        Genre genre = new Genre();
        genre.setId(1);
        gp.setGenre(genre);
        
        Groupe leGroupeInsere = DaoGroupe.ajouterGroupe(con, gp);
       
        ConnexionBdd.fermerConnexion(con);
    }
    
}
