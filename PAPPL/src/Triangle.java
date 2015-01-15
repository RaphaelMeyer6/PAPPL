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

    public void parseGeometry() {
        String delims = "[ (),]+";
        String[] tokens = geometry.split(delims);
        A = new Point3D(Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]));
        B = new Point3D(Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]), Double.parseDouble(tokens[7]));
        C = new Point3D(Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]), Double.parseDouble(tokens[10]));
    }

    public double[] BoiteEnglobante() {
        double[] boiteEnglobante = new double[4];
        boiteEnglobante[0] = (Math.min(A.getAbscisse(), Math.min(B.getAbscisse(), C.getAbscisse())));
        boiteEnglobante[0] = (Math.max(A.getAbscisse(), Math.max(B.getAbscisse(), C.getAbscisse())));
        boiteEnglobante[0] = (Math.min(A.getOrdonnee(), Math.min(B.getOrdonnee(), C.getOrdonnee())));
        boiteEnglobante[0] = (Math.max(A.getOrdonnee(), Math.max(B.getOrdonnee(), C.getOrdonnee())));
        return boiteEnglobante;
    }

    private void Barycentre2D() {
        this.G = new Point3D((A.getAbscisse() + B.getAbscisse() + C.getAbscisse()) / 3, (A.getOrdonnee() + B.getOrdonnee() + C.getOrdonnee()) / 3, 0);
    }

    public void pointInclus(Point2D P) {
        double[] boiteEnglobante = BoiteEnglobante();
        Barycentre2D();
        if (P.getAbscisse() > boiteEnglobante[0] || P.getAbscisse() < boiteEnglobante[1] || P.getOrdonnee() > boiteEnglobante[2] || P.getOrdonnee() < boiteEnglobante[3]) {
            //Droite AB
            double a = (B.getOrdonnee() - A.getOrdonnee()) / (B.getAbscisse() - A.getAbscisse());
            double b = A.getOrdonnee() - a * A.getAbscisse();
            if (Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b)*Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b)==1 ||
                Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b)*Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b)==0) {
                //Droite BC
                a = (C.getOrdonnee() - B.getOrdonnee()) / (C.getAbscisse() - B.getAbscisse());
                b = C.getOrdonnee() - a * C.getAbscisse();
                if (Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b)*Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b)==1 ||
                Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b)*Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b)==0)  {
                    //Droite AC
                    a = (C.getOrdonnee() - A.getOrdonnee()) / (C.getAbscisse() - A.getAbscisse());
                    b = A.getOrdonnee() - a * A.getAbscisse();
                    if (Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b)*Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b)==1 ||
                Math.signum(P.getOrdonnee() - a * P.getAbscisse() - b)*Math.signum(G.getOrdonnee() - a * G.getAbscisse() - b)==0)  {
                    this.point_inclus.add(P);
                    }
                }
            }
        }
    }

    public double InterpolationAltitude(Point2D P) {
        double[] vecteur = A.pVectoriel(B, C);
        double altitude;
        altitude=(vecteur[0]*(A.getAbscisse()-P.getAbscisse())+vecteur[1]*(A.getOrdonnee()-P.getOrdonnee()))/vecteur[2]+A.getAltitude();
        return altitude;
    }


    public String toString() {
        String res = A.toString() + "\n" + B.toString() + "\n" + C.toString();
        return res;
    }

}
