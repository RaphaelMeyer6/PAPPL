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
import modeleGraphe.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
public class PAPPL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int hauteur=9;
        int largeur=10;
        Grille G = new Grille(largeur,hauteur);
        for (int y=0;y<hauteur;y++){
            for (int x=0;x<largeur;x++){
                Point2D P=new Point2D(x,y);
                Random r = new Random();
                int valeur = 0 + r.nextInt(100);
                Maille M=new Maille(P,valeur,G);
            }
        }
        G.getMaillage()[1][1].setAltitude(30);
        Graph graphique=new Graph(largeur,hauteur,G,0);
        G.calculDirection();
        Graph graph2=new Graph(largeur,hauteur,G,1);
        LinkedList<Maille> maillesVersantes = new LinkedList<>();
        maillesVersantes=G.calculVersantes(G.getMaillage()[1][1]);
        for (Maille m :maillesVersantes ){
            System.out.println(m);
        }
}
}
