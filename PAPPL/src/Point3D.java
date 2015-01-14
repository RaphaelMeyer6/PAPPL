/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raphael
 */
public class Point3D {
    private double abscisse;
    private double ordonnee;
    private double altitude;
/*
    @param x l'abcisse du podouble
    @param y l'ordonnee du podouble
    */
   public Point3D(double x,double y,double z){
       this.abscisse=x;
       this.ordonnee=y;
       this.altitude=z;
   }
   public Point3D(Point3D A){
       this.abscisse=A.getAbscisse();
       this.ordonnee=A.getOrdonnee();
       this.altitude=A.getAltitude();
   }
   public double getAbscisse(){
       return abscisse;
   }
   public double getOrdonnee(){
       return ordonnee;
   }
   public void setAbscisse(double x){
       this.abscisse=x;
   }
   public void setOrdonnee(double y){
       this.ordonnee=y;
   }
   public void setAltitude(double z){
       this.altitude=z;
   }
   public double getAltitude(){
       return altitude;
   }
   public String toString(){
       String res= "("+abscisse+","+ordonnee+","+altitude+")";
       return res;
   }
}
