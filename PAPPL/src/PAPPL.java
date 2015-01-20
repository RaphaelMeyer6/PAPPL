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
        /*Point3D P=new Point3D(0,-4000,50);
        Point3D P1=new Point3D(4000,-4000,60);
        Point3D P2=new Point3D(0,0,40);
        Triangle T=new Triangle(P,P1,P2);
        Point2D p2D =new Point2D(100,-4000);
        Maille M = new Maille();
        M.setPosition(p2D);
        T.pointInclus(p2D);
        if (T.getPointInclus().size()!=0){
        M.setAltitude(T.InterpolationAltitude(p2D));
        System.out.println(T.getPointInclus().getFirst());
        System.out.println(M.getAltitude());
        }*/
        long debut = System.currentTimeMillis();  
        DBconnexion con = new DBconnexion("localhost/PAPPL", "postgres", "root");
        LinkedList<String> resultat;
        int pas=500;
        //Calcul de la taille du maillage à créer pour couvrir le maillage de Delaunay
        resultat = con.lancerRequete("SELECT min(ST_Xmin(thegeom)) FROM decoupage;");
        double min = Math.ceil(Math.abs((Double.parseDouble(resultat.getFirst()))));
        resultat = con.lancerRequete("SELECT max(ST_Xmax(thegeom)) FROM decoupage;");
        double max = Math.ceil(Math.abs((Double.parseDouble(resultat.getFirst()))));
        double largeur = Math.max(min, max);
        int largeur_grille = 2*(int)largeur/pas+1;
        resultat = con.lancerRequete("SELECT min(ST_Ymin(thegeom)) FROM decoupage;");
        min = Math.ceil(Math.abs((Double.parseDouble(resultat.getFirst()))));
        resultat = con.lancerRequete("SELECT max(ST_Ymax(thegeom)) FROM decoupage;");
        max = Math.ceil(Math.abs((Double.parseDouble(resultat.getFirst()))));
        double hauteur = Math.max(min, max);
        int hauteur_grille = 2*(int)hauteur/pas+1;
        Grille G = new Grille(largeur_grille,hauteur_grille,pas);
        resultat = con.lancerRequete("SELECT ST_asText(thegeom) from decoupage;");
        Delaunay delaunay = new Delaunay();
        for (String str : resultat) {
            Triangle T = new Triangle();
            delaunay.ajouterTriangle(T);
            T.setGeometry(str);
            T.parseGeometry();
        }  
        for (Triangle T : delaunay.getListeTriangles()) {
            for (int y = 0; y < hauteur_grille; y++) {
                for (int x = 0; x < largeur_grille; x++) {
                    T.pointInclus(G.getMaillage()[x][y].getPosition());
                    }
            }
        }
        for (Triangle T:delaunay.getListeTriangles()){
            for (Point2D P:T.getPointInclus()){
                double altitude =T.InterpolationAltitude(P);
                G.getMaillage()[P.getAbs_grille()][P.getOrd_grille()].setAltitude(altitude);
            }
        }
         Graph graphique=new Graph(largeur_grille,hauteur_grille,G,0);
         G.calculDirection();
         Graph graph2=new Graph(largeur_grille,hauteur_grille,G,1);
         G.appelCalculVersantes();
         Graph graph3=new Graph(largeur_grille,hauteur_grille,G,2);
         G.trouveBassins(10);
         Graph graph4=new Graph(largeur_grille,hauteur_grille,G,3);
        System.out.println("fini");
        con.DBdisconnect();
    
    long fin = System.currentTimeMillis();
    long total = (fin - debut);
    System.out.println("Exécuté en " + total + " ms");          
    }
}
