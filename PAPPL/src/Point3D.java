/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
public class Point3D {

    private double abscisse;
    private double ordonnee;
    private double altitude;

    /**
     * @param x l'abcisse du point.
     * @param y l'ordonnee du point.
     * @param z l'altitude du point.
     */
    public Point3D(double x, double y, double z) {
        this.abscisse = x;
        this.ordonnee = y;
        this.altitude = z;
    }

    public double getAbscisse() {
        return abscisse;
    }

    public double getOrdonnee() {
        return ordonnee;
    }

    public void setAbscisse(double x) {
        this.abscisse = x;
    }

    public void setOrdonnee(double y) {
        this.ordonnee = y;
    }

    public void setAltitude(double z) {
        this.altitude = z;
    }

    public double getAltitude() {
        return altitude;
    }

    public String toString() {
        String res = "(" + abscisse + "," + ordonnee + "," + altitude + ")";
        return res;
    }

    /**
     * Calcule de produit vectoriel de AB et AC.
     *
     * @param B point3D qui définit la droite AB.
     * @param C point3D qui définit la droite AC.
     * @return un tableau de 3 double correspondant au vecteur résultat du
     * produit vectoriel.
     */
    public double[] pVectoriel(Point3D B, Point3D C) {
        double[] vecteur = new double[3];
        double x1 = B.getAbscisse() - this.getAbscisse();
        double y1 = B.getOrdonnee() - this.getOrdonnee();
        double z1 = B.getAltitude() - this.getAltitude();
        double x2 = C.getAbscisse() - this.getAbscisse();
        double y2 = C.getOrdonnee() - this.getOrdonnee();
        double z2 = C.getAltitude() - this.getAltitude();
        vecteur[0] = y1 * z2 - y2 * z1;
        vecteur[1] = x2 * z1 - x1 * z2;
        vecteur[2] = x1 * y2 - x2 * y1;
        return vecteur;
    }
}
