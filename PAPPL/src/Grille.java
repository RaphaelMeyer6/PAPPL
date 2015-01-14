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
    /*
     @param L la largeur de la griller
     @param l la hauteur de la grille
     */

    public Grille(int l, int h) {
        this.largeur = l;
        this.hauteur = h;
        maillage = new Maille[largeur][hauteur];
        Maille M = new Maille();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                maillage[x][y] = M;
            }
        }
    }

    public void ajouterMaille(Maille M, int x, int y) {
        maillage[x][y] = M;
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

    public LinkedList<Maille> raster(Maille M) {
        LinkedList<Maille> liste_mailles = new LinkedList<>();
        int abs = (int)M.getPosition().getAbscisse();
        int ord = (int)M.getPosition().getOrdonnee();
        double altitude=M.getAltitude();
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {

                if (!horsGrille(abs + k, ord + l)) {
                    if (k*l!=0){
                      maillage[abs+k][ord+l].calculMaximumDrop(altitude,Math.sqrt(2));  
                    }
                    else {
                        maillage[abs+k][ord+l].calculMaximumDrop(altitude,1);
                    }
                    
                    liste_mailles.add(maillage[abs + k][ord + l]);
                }
            }
        }
        return liste_mailles;
    }

    public LinkedList<Maille> rasterVersantes(LinkedList<Maille> liste) {
        ListIterator<Maille> iterator = liste.listIterator();
        while (iterator.hasNext()) {
            Maille m = iterator.next();
            if (!m.getTraitee()) {
                int abs = (int)m.getPosition().getAbscisse();
                int ord = (int)m.getPosition().getOrdonnee();
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

    public void calculDirection() {
        //parcours de la grille via les indices i et j
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                LinkedList<Maille> liste_mailles = new LinkedList<>();
                liste_mailles = raster(maillage[i][j]);
                Collections.sort(liste_mailles);
                int diff_abscisse = (int)liste_mailles.get(0).getPosition().getAbscisse() - i;
                int diff_ordonnee = (int)liste_mailles.get(0).getPosition().getOrdonnee() - j;
                if (diff_abscisse == 0 && diff_ordonnee == 0) {
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

    public boolean horsGrille(int i, int j) {
        if (i < 0 || i >= largeur || j < 0 || j >= hauteur) {
            return true;
        } else {
            return false;
        }
    }

    public LinkedList<Maille> calculVersantes(Maille M) {
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
        return maillesVersantes;
    }

    public void appelCalculVersantes() {
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                calculVersantes(maillage[i][j]);
            }
        }
    }

    public void MaximaLocaux() {
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                int compteur = 0;
                LinkedList<Maille> raster = raster(maillage[i][j]);
                for (Maille m : raster) {
                    if (m.getMaillesDeversees() <= maillage[i][j].getMaillesDeversees()) {
                        compteur++;
                    }
                }
                if (compteur==raster.size()){
                    maillage[i][j].setEstMaxima(true);
                }
            }

        }
    }
    public void trouveBassins(int accumulation){
        for (int j = 0; j < hauteur; j++) {
            for (int i = 0; i < largeur; i++) {
                if (maillage[i][j].getMaillesDeversees()>accumulation){
                    maillage[i][j].setEstBassin(true);
                }
            }
    }}
}
