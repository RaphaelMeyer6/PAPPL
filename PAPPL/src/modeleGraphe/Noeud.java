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

public class Noeud {
   private String nom;
   
   public String getNom() {
    return nom;
   }

   private Graphe graphe;

   public Noeud (String nom, Graphe graphe){
    this.nom = nom;
    this.graphe = graphe;
    graphe.listeNoeuds.add(this);
   }

   public String toString(){
    return new String("   noeud: "+ this.getNom());
   }

   public LinkedList<Noeud> getChildren(){
    LinkedList<Noeud> children = new LinkedList<Noeud> ();
    ListIterator<Arc> liArc = graphe.listeArcs.listIterator();
    while (liArc.hasNext()){
       Arc arcCourant = liArc.next();
       if (arcCourant.getNoeudOrigine().getNom().equals(nom)){
        children.add(arcCourant.getNoeudExtremite());
       }
    }
    return children;
   }

   public Boolean hasChildren(){
    return (getChildren().size() != 0 );
   }
}
