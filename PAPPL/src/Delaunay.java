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
    
    public Delaunay(){
        liste_triangles= new LinkedList<>();
    }
    public LinkedList<Triangle> getListeTriangles(){
        return liste_triangles;
    }
    public void ajouterTriangle(Triangle T){
        liste_triangles.add(T);
    }
    
}
