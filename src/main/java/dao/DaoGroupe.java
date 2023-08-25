/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Genre;
import model.Groupe;
import model.Titre;

/**
 *
 * @author Zakina
 */
public class DaoGroupe {
    Connection connection=null;
    static PreparedStatement requete=null;
    static ResultSet rs=null;

    public static ArrayList<Groupe> getLesGroupes(Connection connection){
        ArrayList<Groupe> lesGroupes = new  ArrayList<Groupe>();
        try
        {
            //preparation de la requete
            requete=connection.prepareStatement("select * from groupe, genre where gpe_idGenre = gre_id");
            System.out.println("Requete" + requete);

            //executer la requete
            rs=requete.executeQuery();

            //On hydrate l'objet métier Groupe et sa relation Genre avec les résultats de la requête
            while ( rs.next() ) {


                Groupe leGroupe = new Groupe();
                leGroupe.setId(rs.getInt("gpe_id"));
                leGroupe.setNom(rs.getString("gpe_nom"));
                leGroupe.setDateCreation(rs.getString("gpe_dateCreation"));

                Genre leGenre = new Genre();
                leGenre.setId(rs.getInt("gre_id"));
                leGenre.setLibelle(rs.getString("gre_libelle"));

                leGroupe.setGenre(leGenre);
                lesGroupes.add(leGroupe);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return lesGroupes ;
    }


    public static Groupe getLeGroupe(Connection connection, int idGroupe){

        Groupe leGroupe = new Groupe();
        try
        {
            //preparation de la requete
            requete=connection.prepareStatement("select * from groupe, genre where gpe_idGenre = gre_id and gpe_id=?");
            requete.setInt(1, idGroupe);
            System.out.println("Requete" + requete);

            //executer la requete
            rs=requete.executeQuery();

            //On hydrate l'objet métier Groupe et sa relation Genre avec les résultats de la requête
            while ( rs.next() ) {

                leGroupe.setId(rs.getInt("gpe_id"));
                leGroupe.setNom(rs.getString("gpe_nom"));
                leGroupe.setDateCreation(rs.getString("gpe_dateCreation"));

                Genre leGenre = new Genre();
                leGenre.setId(rs.getInt("gre_id"));
                leGenre.setLibelle(rs.getString("gre_libelle"));

                leGroupe.setGenre(leGenre);
                
                ArrayList<Titre> lesTitres = getLesTitresDuGroupe(connection, idGroupe);
                leGroupe.setLesTitres(lesTitres);

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
        }
        return leGroupe ;
    }

     public static ArrayList<Titre> getLesTitresDuGroupe(Connection connection, int idGroupe){
         ArrayList<Titre> lesTitres = new ArrayList<Titre>();
         return lesTitres;
     }
    
    
    public static Groupe ajouterGroupe(Connection connection, Groupe unGroupe){
        int idGenere = -1;
        try
        {
            //preparation de la requete
            // gpe_id (clé primaire de la table groupe) est en auto_increment,donc on ne renseigne pas cette valeur
            // le paramètre RETURN_GENERATED_KEYS est ajouté à la requête afin de pouvoir récupérer l'id généré par la bdd (voir ci-dessous)
            // supprimer ce paramètre en cas de requête sans auto_increment.
            requete=connection.prepareStatement("INSERT INTO GROUPE ( gpe_nom, gpe_dateCreation, gpe_idGenre)\n" +
                    "VALUES (?,?,?)", requete.RETURN_GENERATED_KEYS );
            requete.setString(1, unGroupe.getNom());
            requete.setString(2, unGroupe.getDateCreation());
            requete.setInt(3, unGroupe.getGenre().getId());

            System.out.println("requeteInsertion=" + requete);
            /* Exécution de la requête */
            int resultatRequete = requete.executeUpdate();
            System.out.println("resultatrequete=" + resultatRequete);

            // Récupération de id auto-généré par la bdd dans la table groupe
            rs = requete.getGeneratedKeys();
            while ( rs.next() ) {
                idGenere = rs.getInt( 1 );
                unGroupe.setId(idGenere);
            }

            // si le résultat de la requete est différent de 1, c'est que la requête a échoué.
            // Dans ce cas, on remet l'objet groupe à null
            if (resultatRequete != 1){
                unGroupe= null;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //out.println("Erreur lors de l’établissement de la connexion");
            unGroupe= null;
        }
        return unGroupe ;
    }
    
}
