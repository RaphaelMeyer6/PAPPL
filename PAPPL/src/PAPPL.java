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

public class PAPPL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Point3D A=new Point3D(0,0,25);
        Point3D B=new Point3D(10,0,2);
        Point3D C=new Point3D(5,5,20);
        Triangle T=new Triangle(A,B,C);
        Point2D P=new Point2D(9,1);
        T.pointInclus(P);
//long debut = System.currentTimeMillis();
        /*DBconnexion con = new DBconnexion("localhost/PAPPL", "postgres", "root");
        LinkedList<String> resultat;
        //Calcul de la taille du maillage à créer pour couvrir le maillage de Delaunay
        resultat = con.lancerRequete("SELECT min(ST_Xmin(thegeom)) FROM decoupage;");
        double min = Math.ceil(Math.abs((Double.parseDouble(resultat.getFirst()))));
        resultat = con.lancerRequete("SELECT max(ST_Xmax(thegeom)) FROM decoupage;");
        double max = Math.ceil(Math.abs((Double.parseDouble(resultat.getFirst()))));
        double largeur = Math.max(min, max);
        resultat = con.lancerRequete("SELECT min(ST_Ymin(thegeom)) FROM decoupage;");
        min = Math.ceil(Math.abs((Double.parseDouble(resultat.getFirst()))));
        resultat = con.lancerRequete("SELECT max(ST_Ymax(thegeom)) FROM decoupage;");
        max = Math.ceil(Math.abs((Double.parseDouble(resultat.getFirst()))));
        double hauteur = Math.max(min, max);

        Grille G = new Grille((int) largeur, (int) hauteur);
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Point2D P = new Point2D(x, y);
                Maille M = new Maille(P, Double.NaN, G);
            }
        }
        resultat = con.lancerRequete("SELECT ST_asText(thegeom) from decoupage;");
        Delaunay delaunay = new Delaunay();
        for (String str : resultat) {
            Triangle T = new Triangle();
            delaunay.ajouterTriangle(T);
            T.setGeometry(str);
            T.parseGeometry();
        }
        int t=1;
        for (Triangle T : delaunay.getListeTriangles()) {
            for (int y = 0; y < hauteur; y++) {
                for (int x = 0; x < largeur; x++) {
                    T.pointInclus(G.getMaillage()[x][y].getPosition());
                }

            }
            t++;
            System.out.println("Triangle "+t);
        }
        for (Triangle T:delaunay.getListeTriangles()){
            for (Point2D P:T.getPointInclus()){
                double altitude =T.InterpolationAltitude(P);
                G.getMaillage()[P.getAbscisse()][P.getOrdonnee()].setAltitude(altitude);
            }
        }
        /*Graph graphique=new Graph(largeur,hauteur,G,0);
         G.calculDirection();
         Graph graph2=new Graph(largeur,hauteur,G,1);
         G.appelCalculVersantes();
         Graph graph3=new Graph(largeur,hauteur,G,2);
         G.trouveBassins(10);
         Graph graph4=new Graph(largeur,hauteur,G,3);
         

            //long fin = System.currentTimeMillis();
        //long total = (fin - debut);
        //System.out.println("Exécuté en " + total + " ms");
        System.out.println("fini");
        con.DBdisconnect();
    */
    
    }
}
