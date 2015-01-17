/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */

public class Maille implements Comparable {

    private Point2D position;
    private double altitude;
    private double maximum_drop;
    private Grille grille;
    private String direction;
    private boolean traitee;
    private int maillesDeversees;
    private boolean estMaxima;
    private boolean estBassin;

    public Maille() {
        this.altitude = Double.NaN;
        traitee=false;
    }

    public Maille(Point2D P, double altitude, Grille G) {
        position = P;
        this.altitude = altitude;
        this.grille = G;
        //G.ajouterMaille(this, (int)position.getAbscisse()+G.getLargeur()*200, (int)position.getOrdonnee()+G.getHauteur()/2);
        traitee=false;
        maillesDeversees=0;
        estMaxima=false;
        estBassin=false; 
        maximum_drop=0;
    }

    public int compareTo(Object O) {
        Maille M = (Maille) O;
        if (O==null){
            throw new NullPointerException();
        }
        if (maximum_drop == M.getMaximumDrop()) {
            return 0;
        } else if (maximum_drop < M.getMaximumDrop()) {
            return 1;
        } else {
            return -1;
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
    public void setEstMaxima(boolean B){
        estMaxima=B;
    }
    public boolean getEstMaxima(){
        return estMaxima;
    }
    public void setEstBassin(boolean B){
        estBassin=B;
    }
    public boolean getEstBassin(){
        return estBassin;
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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    public double getMaximumDrop() {
        return maximum_drop;
    }

    public void calculMaximumDrop(double alt, double facteur)
    {
        maximum_drop=(alt-altitude)/facteur;
    }

    public String toString() {
        String res = "Maille en position " + position + " et d'altitude " + altitude;
        return res;
    }   

}
