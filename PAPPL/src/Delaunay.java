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

public class Delaunay {
    private LinkedList<Triangle> liste_triangles;
    /**
     * Crée une LinkedList contenant les triangles du maillage de Delaunay.
     */
    public Delaunay(){
        liste_triangles= new LinkedList<>();
    }
    public LinkedList<Triangle> getListeTriangles(){
        return liste_triangles;
    }
    /**
     * Ajoute un triangle à la LinkedList du maille de Delaunay.
     * @param T triangle à ajouter.
     */
    public void ajouterTriangle(Triangle T){
        liste_triangles.add(T);
    }
    
}
