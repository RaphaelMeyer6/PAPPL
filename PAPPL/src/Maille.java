/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
import java.util.ArrayList;
public class Maille implements Comparable {

    private Point2D position;
    private int altitude;
    private Grille grille;
    private String direction;
    private boolean traitee;
    private int maillesDeversees;

    public Maille() {
        this.altitude = 0;
        traitee=false;
    }

    public Maille(Point2D P, int altitude, Grille G) {
        position = P;
        this.altitude = altitude;
        this.grille = G;
        G.ajouterMaille(this, position.getAbscisse(), position.getOrdonnee());
        traitee=false;
    }

    public int compareTo(Object O) {
        Maille M = (Maille) O;
        if (O==null){
            throw new NullPointerException();
        }
        if (altitude == M.getAltitude()) {
            return 0;
        } else if (altitude < M.getAltitude()) {
            return -1;
        } else {
            return 1;
        }
    }

    public String getDirection() {
        return direction;
    }
    public void setTraitee(boolean B){
        traitee=B;
    }
    public boolean getTraitee(){
        return traitee;
    }
    public void setMaillesDeversees(int i){
        maillesDeversees=i;
    }
    public int getMaillesDeversees(){
        return maillesDeversees;
    }
    public void setDirection(String S) {
        direction = S;
    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille G) {
        grille = G;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D P) {
        this.position = P;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public String toString() {
        String res = "Maille en position " + position + " et d'altitude " + altitude;
        return res;
    }

    public boolean horsGrille() {
        if (this.position.getAbscisse() < 0 || this.position.getOrdonnee() < 0
                || this.position.getAbscisse() >= grille.getLargeur() || this.position.getOrdonnee() >= grille.getHauteur()) {
            return true;
        } else {
            return false;
        }
    }
    

}
