/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
import java.awt.*;
import javax.swing.*;

public class Graph {

    private int hauteur;
    private int largeur;

    public Graph(int largeur, int hauteur, Grille G, int param) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        JFrame frame = new JFrame("Maillage");
        JPanel panneau = new JPanel();
        GridLayout gestionnaire = new GridLayout(hauteur, largeur);
        panneau.setLayout(gestionnaire);
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (param == 0) {
                    JButton label = new JButton(Integer.toString(G.getMaillage()[x][y].getAltitude()));
                    panneau.add(label);
                    System.out.println("x,y vaut: "+x+","+y);
                    System.out.println("abs,ord: " +G.getMaillage()[x][y].getPosition().getAbscisse()+","+G.getMaillage()[x][y].getPosition().getOrdonnee());
                    System.out.println("altitude: " + G.getMaillage()[x][y].getAltitude());
                } else {
                    JButton label = new JButton(G.getMaillage()[x][y].getDirection());
                    panneau.add(label);
                }
            }
        }
        frame.setContentPane(panneau);
        //frame.setSize(640, 480);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
