/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

public class Grille {

    private int largeur;
    private int hauteur;
    private Maille[][] maillage;
    
    /**
     @param l largeur de la grille.
     @param h hauteur de la grille.
     @param pas pas du maillage.
     */
    public Grille(int l, int h, int pas) {
        this.largeur = l;
        this.hauteur = h;
        maillage = new Maille[largeur][hauteur];
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Point2D P = new Point2D((2 * x - largeur) * pas / 2, (2 * y - hauteur) * pas / 2);
                Maille M = new Maille(P, Double.NaN, this);
                maillage[x][y] = M;
                P.setAbs_grille(x);
                P.setOrd_grille(y);
            }
        }
    }

    public Maille[][] getMaillage() {
        return maillage;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setHauteur(int h) {
        hauteur = h;
    }

    public void setLargeur(int h) {
        largeur = h;
    }

    /**
     * Permet de récupérer les mailles adjacentes à une maille M.
     * @param M maille dont on cherche les mailles adjacentes(diagonale compris).
     * @return LinkedList contenant les mailles autour (diagonale compris) de M, se situant dans la grille.
     */
    public LinkedList<Maille> raster(Maille M) {
        LinkedList<Maille> liste_mailles = new LinkedList<>();
        int abs = (int) M.getPosition().getAbs_grille();
        int ord = (int) M.getPosition().getOrd_grille();
        double altitude = M.getAltitude();
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {

                if (!horsGrille(abs + k, ord + l) && !Double.isNaN(maillage[abs + k][ord + l].getAltitude())) {
                    if (k * l != 0) {
                        maillage[abs + k][ord + l].calculMaximumDrop(altitude, Math.sqrt(2));
                    } else {
                        maillage[abs + k][ord + l].calculMaximumDrop(altitude, 1);
                    }

                    liste_mailles.add(maillage[abs + k][ord + l]);
                }
            }
        }
        return liste_mailles;
    }

    /**
     * Méthode récursive privée appelée pour ajouter les mailles se déversant dans chaque maille de la liste d'entrée.
     * @param liste contient toutes les mailles se déversant dans la maille appelée par calculVersantes.
     * @return la liste d'entrée, complétée avec les nouvelles mailles se déversant dans les mailles de la liste.
     */
    private LinkedList<Maille> rasterVersantes(LinkedList<Maille> liste) {
        ListIterator<Maille> iterator = liste.listIterator();
        while (iterator.hasNext()) {
            Maille m = iterator.next();
            if (!m.getTraitee()) {
                int abs = (int) m.getPosition().getAbs_grille();
                int ord = (int) m.getPosition().getOrd_grille();
                m.setTraitee(true);
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if (!horsGrille(abs + k, ord + l)) {
                            if (k == 1 && l == 1 && maillage[abs + k][ord + l].getDirection() == "NO") {
                                iterator.add(maillage[abs + k][ord + l]);
                            } else if (k == 1 && l == 0 && maillage[abs + k][ord + l].getDirection() == "O") {
                                iterator.add(maillage[abs + k][ord + l]);
                            } else if (k == 1 && l == -1 && maillage[abs + k][ord + l].getDirection() == "SO") {
                                iterator.add(maillage[abs + k][ord + l]);
                            } else if (k == 0 && l == 1 && maillage[abs + k][ord + l].getDirection() == "N") {
                                iterator.add(maillage[abs + k][ord + l]);
                            } else if (k == 0 && l == -1 && maillage[abs + k][ord + l].getDirection() == "S") {
                                iterator.add(maillage[abs + k][ord + l]);
                            } else if (k == -1 && l == 1 && maillage[abs + k][ord + l].getDirection() == "NE") {
                                iterator.add(maillage[abs + k][ord + l]);
                            } else if (k == -1 && l == 0 && maillage[abs + k][ord + l].getDirection() == "E") {
                                iterator.add(maillage[abs + k][ord + l]);
                            } else if (k == -1 && l == -1 && maillage[abs + k][ord + l].getDirection() == "SE") {
                                iterator.add(maillage[abs + k][ord + l]);
                            }
                        }
                    }
                }
            }

        }
        return liste;
    }

    /**
     * Calcule la direction découlement de toutes les mailles de la grille.
     */
    public void calculDirection() {
        //parcours de la grille via les indices i et j
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                if (Double.isNaN(maillage[i][j].getAltitude())) {
                    maillage[i][j].setDirection("NaN");
                } else {
                    LinkedList<Maille> liste_mailles = new LinkedList<>();
                    liste_mailles = raster(maillage[i][j]);
                    Collections.sort(liste_mailles);
                    int diff_abscisse = (int) liste_mailles.get(0).getPosition().getAbs_grille() - i;
                    int diff_ordonnee = (int) liste_mailles.get(0).getPosition().getOrd_grille() - j;
                    if (diff_abscisse == 0 && diff_ordonnee == 0 || liste_mailles.get(0).getAltitude()==maillage[i][j].getAltitude()) {
                        maillage[i][j].setDirection("X");
                    } else if (diff_abscisse == 1 && diff_ordonnee == 1) {
                        maillage[i][j].setDirection("SE");
                    } else if (diff_abscisse == 1 && diff_ordonnee == 0) {
                        maillage[i][j].setDirection("E");
                    } else if (diff_abscisse == 1 && diff_ordonnee == -1) {
                        maillage[i][j].setDirection("NE");
                    } else if (diff_abscisse == 0 && diff_ordonnee == 1) {
                        maillage[i][j].setDirection("S");
                    } else if (diff_abscisse == 0 && diff_ordonnee == -1) {
                        maillage[i][j].setDirection("N");
                    } else if (diff_abscisse == -1 && diff_ordonnee == 1) {
                        maillage[i][j].setDirection("SO");
                    } else if (diff_abscisse == -1 && diff_ordonnee == 0) {
                        maillage[i][j].setDirection("O");
                    } else if (diff_abscisse == -1 && diff_ordonnee == -1) {
                        maillage[i][j].setDirection("NO");
                    }
                }
            }
        }
    }

    /**
     * 
     * @param i abscisse de la maille.
     * @param j ordonnée de la maille.
     * @return true si la maile est en dehors de la grille, false sinon.
     */
    public boolean horsGrille(int i, int j) {
        if (i < 0 || i >= largeur || j < 0 || j >= hauteur) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calcule le nombre de mailles se déversant dans une maille donnée M.
     * @param M maille pour laquelle on cherche combien de mailles se déversent à l'intérieur.
     */
    private void calculVersantes(Maille M) {
        LinkedList<Maille> maillesVersantes = new LinkedList<>();
        maillesVersantes.add(M);
        int traites = 0;
        boolean termine = false;
        do {
            // Lancement du calcul des mailles versantes sur les mailles contenues dans la liste
            maillesVersantes = rasterVersantes(maillesVersantes);
            traites = 0;
            //Compte le nombre de mailles traitées
            for (Maille m : maillesVersantes) {
                if (m.getTraitee()) {
                    traites = traites + 1;
                }
            }
            //Vérifie que toutes les mailles sont traitées avant de terminer la boucle while
            if (traites == maillesVersantes.size()) {
                termine = true;
            }
        } while (!termine);
        for (Maille m : maillesVersantes) {
            m.setTraitee(false);
            M.setMaillesDeversees(maillesVersantes.size() - 1);
        }
    }

    /**
     * Calcule le nombre de mailles se déversant dans chaque maille de la grille.
     */
    public void appelCalculVersantes() {
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                calculVersantes(maillage[i][j]);
            }
        }
    }
/**
 * Méthode qui détermine où se trouvent les bassins d'accumulation d'eau. A utiliser après éxécution des méthodes calculDirection et appelCalculVersantes.
 * @param accumulation critère (nombre de cellules déversantes minimum) pour qualifier une cellule de bassin d'accumulation.
 */
    public void trouveBassins(int accumulation) {
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                if (maillage[i][j].getMaillesDeversees() > accumulation) {
                    maillage[i][j].setEstBassin(true);
                }
            }
        }
    }
}
