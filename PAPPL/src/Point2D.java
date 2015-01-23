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
    /**
     * abs_grille correspond à la position en x de la maille dans la grille G
     * ord_grille correspond à la position en y de la maille dans la grille G
     */
    private int abs_grille;
    private int ord_grille;

    /**
     * @param x l'abcisse du point
     * @param y l'ordonnee du point
     */
    public Point2D(int x, int y) {
        this.abscisse = x;
        this.ordonnee = y;
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
