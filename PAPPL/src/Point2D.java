/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
public class Point2D {

    private int abscisse;
    private int ordonnee;
    private int abs_grille;
    private int ord_grille;
    /*
     @param x l'abcisse du podouble
     @param y l'ordonnee du podouble
     */

    public Point2D(int x, int y) {
        this.abscisse = x;
        this.ordonnee = y;
    }

    public Point2D(Point2D A) {
        this.abscisse = A.getAbscisse();
        this.ordonnee = A.getOrdonnee();
    }
    public int getOrd_grille() {
        return ord_grille;
    }

    public void setOrd_grille(int y) {
        this.ord_grille = y;
    }

    public int getAbs_grille() {
        return abs_grille;
    }

    public void setAbs_grille(int x) {
        this.abs_grille = x;
    }

    public int getAbscisse() {
        return abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public void setAbscisse(int x) {
        this.abscisse = x;
    }

    public void setOrdonnee(int y) {
        this.ordonnee = y;
    }

    public String toString() {
        String res = "(" + abscisse + "," + ordonnee + ")";
        return res;
    }
}
