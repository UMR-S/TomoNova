package umaru.tomonova.tomonova.utils.particles;

import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;


public class ParticlesShape {

    public static Collection<Vector> collectionVectSphere(double radius, int nbPoints){
        if(radius < 0){
            throw new IllegalArgumentException("Negative Radius :" + radius);
        }
        Collection<Vector> collectionVectSphere = new ArrayList<Vector>();
        double theta = 0; //Appartient à [0,Pi]
        double phi = 0; //Appartient à [0,2 Pi[
        double pas = Math.PI/ nbPoints;
        while (theta <= Math.PI) {
            while (phi < 2*Math.PI){
                Vector vecteurPoint= new Vector(radius*Math.sin(theta)*Math.cos(phi), radius*Math.sin(theta)*Math.sin(phi),radius*Math.cos(theta));
                collectionVectSphere.add(vecteurPoint);

                phi = phi + 2*pas;
            }
            theta = theta + pas;
            phi = 0;
        }
        return collectionVectSphere;
    }

}
