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
        JFrame frame = new JFrame("Altitudes");
        switch (param) {
            case 1:
                frame.setTitle("Directions");
                break;
            case 2:
                frame.setTitle("Mailles déversantes");
                break;
        }
        JPanel panneau = new JPanel();
        GridLayout gestionnaire = new GridLayout(hauteur, largeur);
        panneau.setLayout(gestionnaire);
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                switch (param) {
                    case 0:
                        panneau.add(new JButton(Integer.toString(G.getMaillage()[x][y].getAltitude())));
                        break;
                    case 1:
                        panneau.add(new JButton(G.getMaillage()[x][y].getDirection()));
                        break;
                    case 2:
                        panneau.add(new JButton(Integer.toString(G.getMaillage()[x][y].getMaillesDeversees())));
                        break;
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
