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

    /**
     * 
     * @param largeur  nombre de cellules sur l'axe horizontal.
     * @param hauteur  nombre de cellules sur l'axe vertical.
     * @param G  grille qui contient les mailles à représenter.
     * @param param paramètre qui spécifie de quel type de graphique il s'agit (0:altitudes, 1:direction,2:mailles déversantes,3:bassins).
     */
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
            case 3:
                frame.setTitle("Bassins d'accumulation");
                break;
        }
        JPanel panneau = new JPanel();
        GridLayout gestionnaire = new GridLayout(this.hauteur, this.largeur);
        panneau.setLayout(gestionnaire);
        for (int y = 0; y < this.hauteur; y++) {
            for (int x = 0; x < this.largeur; x++) {
                switch (param) {
                    case 0:
                        panneau.add(new JButton(Double.toString(G.getMaillage()[x][y].getAltitude())));
                        break;
                    case 1:
                        panneau.add(new JButton(G.getMaillage()[x][y].getDirection()));
                        break;
                    case 2:
                        panneau.add(new JButton(Integer.toString(G.getMaillage()[x][y].getMaillesDeversees())));
                        break;
                    case 3:
                        JButton bouton = new JButton();
                        if (G.getMaillage()[x][y].getEstBassin()) {
                            bouton.setBackground(Color.BLUE);
                        } else {
                            bouton.setBackground(Color.WHITE);
                        }
                        bouton.setOpaque(true);
                        panneau.add(bouton);
                        break;
                }

            }
        }
        frame.setContentPane(panneau);
        //frame.setSize(1366, 768);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
