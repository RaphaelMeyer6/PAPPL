package modeleGraphe;

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
import java.util.ListIterator;

public class Graphe {

    LinkedList<Noeud> listeNoeuds = new LinkedList<Noeud>();
    LinkedList<Arc> listeArcs = new LinkedList<Arc>();

    public LinkedList<Noeud> getNoeuds() {
        return listeNoeuds;
    }

    public LinkedList<Arc> getArcs() {
        return listeArcs;
    }

    public String toString() {
        StringBuffer texte = new StringBuffer("*** graphe ***\n");
        ListIterator<Noeud> liNoeud = this.listeNoeuds.listIterator();
        while (liNoeud.hasNext()) {
            Noeud noeudCourant = liNoeud.next();
            texte.append(noeudCourant.toString() + "\n");
        }
        ListIterator<Arc> liArc = this.listeArcs.listIterator();
        while (liArc.hasNext()) {
            Arc arcCourant = liArc.next();
            texte.append(arcCourant.toString() + "\n");
        }
        return texte.toString();
    }
}
