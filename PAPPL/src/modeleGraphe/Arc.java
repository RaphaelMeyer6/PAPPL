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
import java.util.ListIterator;

public class Arc {

    private Noeud noeudOrigine;

    public Noeud getNoeudOrigine() {
        return noeudOrigine;
    }

    private Noeud noeudExtremite;

    public Noeud getNoeudExtremite() {
        return noeudExtremite;
    }

    public Arc(Noeud NoeudOrigine, Noeud NoeudExtremite, Graphe graphe) {

        // recherche noeud origine par son nom
        ListIterator<Noeud> liNoeuds = graphe.listeNoeuds.listIterator();
        Boolean trouveOrigine = false;
        while (liNoeuds.hasNext() && !trouveOrigine) {
            Noeud noeudCourant = liNoeuds.next();
            if (NoeudOrigine.equals(noeudCourant)) {
                noeudOrigine = noeudCourant;
                trouveOrigine = true;
            }
        }
        // recherche noeud extremite par son nom
        liNoeuds = graphe.listeNoeuds.listIterator();
        Boolean trouveExtremite = false;
        while (liNoeuds.hasNext() && !trouveExtremite) {
            Noeud noeudCourant = liNoeuds.next();
            if (NoeudExtremite.equals(noeudCourant)) {
                noeudExtremite = noeudCourant;
                trouveExtremite = true;
            }
        }
        if (trouveOrigine && trouveExtremite) {
            graphe.listeArcs.add(this);
        } else {
            // message d'erreur
            StringBuffer erreur = new StringBuffer("Erreur arc: (" + NoeudOrigine.getNom()
                    + " -> " + NoeudExtremite.getNom() + ")\n   ");
            if (!trouveOrigine) {
                erreur.append(NoeudOrigine.getNom() + "? ");
            }
            if (!trouveExtremite) {
                erreur.append(NoeudExtremite.getNom() + "? ");
            }
            System.out.println(erreur);
        }

    }

    public String toString() {
        return new String("   arc: ( "
                + this.getNoeudOrigine().getNom()
                + " -> "
                + this.getNoeudExtremite().getNom()
                + " )"
        );
    }

}
