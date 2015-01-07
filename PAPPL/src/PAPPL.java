/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
public class PAPPL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long debut = System.currentTimeMillis();
        int hauteur=3000;
        int largeur=3000;
        Grille G = new Grille(largeur,hauteur);
        for (int y=0;y<hauteur;y++){
            for (int x=0;x<largeur;x++){
                Point2D P=new Point2D(x,y);
                Random r = new Random();
                int valeur = 0 + r.nextInt(100);
                Maille M=new Maille(P,valeur,G);
            }
        }
        System.out.println(G.getMaillage()[40][300]);
        System.out.println(G.getMaillage()[39][299]);
        System.out.println(G.getMaillage()[39][300]);
        System.out.println(G.getMaillage()[39][301]);
        System.out.println(G.getMaillage()[40][299]);
        System.out.println(G.getMaillage()[40][301]);
        System.out.println(G.getMaillage()[41][299]);
        System.out.println(G.getMaillage()[41][300]);
        System.out.println(G.getMaillage()[41][301]);
        //G.getMaillage()[1][1].setAltitude(30);
        //Graph graphique=new Graph(largeur,hauteur,G,0);
        G.calculDirection();
        System.out.println(G.getMaillage()[40][300].getDirection());
        //Graph graph2=new Graph(largeur,hauteur,G,1);
        /*LinkedList<Maille> maillesVersantes = new LinkedList<>();
        maillesVersantes=G.calculVersantes(G.getMaillage()[1][1]);
        for (Maille m :maillesVersantes ){
            System.out.println(m);
        }*/
        long fin = System.currentTimeMillis();
    System.out.println("Exécuté en "+ (fin-debut)/1000 +" s");
}
    
}
