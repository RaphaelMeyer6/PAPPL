/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
import java.sql.*;
import java.util.LinkedList;

public class DBconnexion {

    private Connection con;
    private String dbURL;
    /**
     * Ouvre une connexion vers une base de données à partir d'un driver JDBC postgresql.
     * @param url URL de la bse de données, exemple: "localhost/PAPPL".
     * @param login login d'accès à la base de données.
     * @param password mot de passe d'accès à la base de données.
     */
    public DBconnexion(String url, String login, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            this.dbURL = "jdbc:postgresql://" + url;
            this.con = DriverManager.getConnection(dbURL, login, password);
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException:");
            System.err.println(e.getMessage());
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    /**
     * Ferme la connexion avec la base de donnée.
     */
    public void DBdisconnect() {
        try {
            con.close();
            Driver theDriver = DriverManager.getDriver(dbURL);
            DriverManager.deregisterDriver(theDriver);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    /**
     * Execute une requete.
     * @param requete la requete a effectuer.
     * @return LinkedList contenant la première colonne, ligne par ligne.
     */
    public LinkedList<String> lancerRequete(String requete) {
        LinkedList<String> resultat = new LinkedList<>();
        ResultSet res;
        try {
            Statement stmt = con.createStatement();
            res = stmt.executeQuery(requete);
            while (res.next()) {
                resultat.add(res.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return resultat;
    }
}
