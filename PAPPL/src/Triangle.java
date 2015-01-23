/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
import java.util.LinkedList;

public class Triangle {

    private Point3D A;
    private Point3D B;
    private Point3D C;
    private LinkedList<Point2D> point_inclus;
    private String geometry;
    private Point3D G;

    public Triangle() {
        A = null;
        B = null;
        C = null;
        point_inclus = new LinkedList<>();
        geometry = "";
        G = null;
    }

    /**
     *
     * @param A premier sommet du triangle.
     * @param B deuxième sommet du triangle.
     * @param C troisième sommet du triangle.
     */
    public Triangle(Point3D A, Point3D B, Point3D C) {
        this.A = A;
        this.B = B;
        this.C = C;
        point_inclus = new LinkedList<>();
        geometry = "";
    }

    public void setGeometry(String str) {
        this.geometry = str;
    }

    public void setA(Point3D A) {
        this.A = A;
    }

    public void setB(Point3D B) {
        this.B = B;
    }

    public void setC(Point3D C) {
        this.C = C;
    }

    public LinkedList<Point2D> getPointInclus() {
        return point_inclus;
    }

    /**
     * Lis l'attribut geometry, qui correspond au polygone de la base de
     * données, et crée les sommets A,B,C en fonction du contenu.
     */
    public void parseGeometry() {
        String delims = "[ (),]+";
        String[] tokens = geometry.split(delims);
        A = new Point3D(Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]));
        B = new Point3D(Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]), Double.parseDouble(tokens[7]));
        C = new Point3D(Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]), Double.parseDouble(tokens[10]));
    }

    /**
     * Calcule la boite englobante d'un triangle.
     *
     * @return un tableau de 4 double correspondant aux xmin,xmax,ymin,ymax.
     */
    public double[] BoiteEnglobante() {
        double[] boiteEnglobante = new double[4];
        boiteEnglobante[0] = (Math.min(A.getAbscisse(), Math.min(B.getAbscisse(), C.getAbscisse())));
        boiteEnglobante[1] = (Math.max(A.getAbscisse(), Math.max(B.getAbscisse(), C.getAbscisse())));
        boiteEnglobante[2] = (Math.min(A.getOrdonnee(), Math.min(B.getOrdonnee(), C.getOrdonnee())));
        boiteEnglobante[3] = (Math.max(A.getOrdonnee(), Math.max(B.getOrdonnee(), C.getOrdonnee())));
        return boiteEnglobante;
    }

    /**
     * Calcule le barycentre du triangle.
     */
    private void Barycentre2D() {
        this.G = new Point3D((A.getAbscisse() + B.getAbscisse() + C.getAbscisse()) / 3, (A.getOrdonnee() + B.getOrdonnee() + C.getOrdonnee()) / 3, 0);
    }

    /**
     * Détermine si un point du maillage est à l'intérieur (par projection sur
     * xOy) du triangle .
     *
     * @param P point dont on cherche à savoir s'il est à l'intérieur du
     * triangle .
     */
    public void pointInclus(Point2D P) {
        double[] boiteEnglobante = BoiteEnglobante();
        Barycentre2D();
        int compteur = 0;
        if (P.getAbscisse() > boiteEnglobante[0] || P.getAbscisse() < boiteEnglobante[1] || P.getOrdonnee() > boiteEnglobante[2] || P.getOrdonnee() < boiteEnglobante[3]) {
            //Droite AB
            if (A.getAbscisse() == B.getAbscisse()) {
                if (Math.signum(G.getAbscisse() - A.getAbscisse()) * Math.signum(P.getAbscisse() - A.getAbscisse()) == 1
                        || Math.signum(G.getAbscisse() - A.getAbscisse()) * Math.signum(P.getAbscisse() - A.getAbscisse()) == 0) {
                    compteur++;
                }
            } else {
                double a = (B.getOrdonnee() - A.getOrdonnee()) / (B.getAbscisse() - A.getAbscisse());
                double b = A.getOrdonnee() - a * A.getAbscisse();
                if (Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b) * Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b) == 1
                        || Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b) * Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b) == 0) {
                    compteur++;
                }
            }
            //Droite BC
            if (C.getAbscisse() == B.getAbscisse()) {
                if (Math.signum(G.getAbscisse() - B.getAbscisse()) * Math.signum(P.getAbscisse() - B.getAbscisse()) == 1
                        || Math.signum(G.getAbscisse() - B.getAbscisse()) * Math.signum(P.getAbscisse() - B.getAbscisse()) == 0) {
                    compteur++;
                }
            } else {
                double a = (C.getOrdonnee() - B.getOrdonnee()) / (C.getAbscisse() - B.getAbscisse());
                double b = C.getOrdonnee() - a * C.getAbscisse();
                if (Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b) * Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b) == 1
                        || Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b) * Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b) == 0) {
                    compteur++;
                }
            }
            //Droite AC
            if (C.getAbscisse() == A.getAbscisse()) {
                if (Math.signum(G.getAbscisse() - A.getAbscisse()) * Math.signum(P.getAbscisse() - A.getAbscisse()) == 1
                        || Math.signum(G.getAbscisse() - A.getAbscisse()) * Math.signum(P.getAbscisse() - A.getAbscisse()) == 0) {
                    compteur++;
                }
            } else {
                double a = (C.getOrdonnee() - A.getOrdonnee()) / (C.getAbscisse() - A.getAbscisse());
                double b = C.getOrdonnee() - a * C.getAbscisse();
                if (Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b) * Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b) == 1
                        || Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b) * Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b) == 0) {
                    compteur++;
                }
            }
            if (compteur == 3) {
                point_inclus.add(P);
            }
        }
    }

    /**
     * Calcule l'altitude d'un point du maillage2D par interpolation avec le
     * triangle.
     *
     * @param P point donc ont cherche l'altitude.
     * @return altitude interpolée du point.
     */
    public double InterpolationAltitude(Point2D P) {
        double[] vecteur = A.pVectoriel(B, C);
        double altitude;
        altitude = (vecteur[0] * (A.getAbscisse() - P.getAbscisse()) + vecteur[1] * (A.getOrdonnee() - P.getOrdonnee())) / vecteur[2] + A.getAltitude();
        return altitude;
    }

    public String toString() {
        String res = A.toString() + "\n" + B.toString() + "\n" + C.toString();
        return res;
    }

}
