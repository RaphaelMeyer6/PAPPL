/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
public class Point2D {
    private int abscisse;
    private int ordonnee;
/*
    @param x l'abcisse du point
    @param y l'ordonnee du point
    */
   public Point2D(int x,int y){
       this.abscisse=x;
       this.ordonnee=y;
   }
   public Point2D(Point2D A){
       this.abscisse=A.getAbscisse();
       this.ordonnee=A.getOrdonnee();
   }
   public int getAbscisse(){
       return abscisse;
   }
   public int getOrdonnee(){
       return ordonnee;
   }
   public void setAbscisse(int x){
       this.abscisse=x;
   }
   public void setOrdonnee(int y){
       this.ordonnee=y;
   }
   public String toString(){
       String res= "("+abscisse+","+ordonnee+")";
       return res;
   }
}
