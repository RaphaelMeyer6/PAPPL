/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
import java.util.Random;
public class PAPPL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long debut = System.currentTimeMillis();
        int hauteur=10;
        int largeur=10;
        Grille G = new Grille(largeur,hauteur);
        for (int y=0;y<hauteur;y++){
            for (int x=0;x<largeur;x++){
                Point2D P=new Point2D(x,y);
                Random r = new Random();
                Double valeur = 100*r.nextDouble();
                Maille M=new Maille(P,valeur,G);
            }
        }
        Graph graphique=new Graph(largeur,hauteur,G,0);
        G.calculDirection();
        Graph graph2=new Graph(largeur,hauteur,G,1);
        G.appelCalculVersantes();
        Graph graph3=new Graph(largeur,hauteur,G,2);
        G.trouveBassins(10);
        Graph graph4=new Graph(largeur,hauteur,G,3);
        long fin = System.currentTimeMillis();
        long total = (fin-debut);
    System.out.println("Exécuté en "+ total +" ms");
}
    
}
