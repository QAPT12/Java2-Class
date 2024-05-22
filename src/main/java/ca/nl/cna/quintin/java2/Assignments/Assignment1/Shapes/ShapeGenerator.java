package ca.nl.cna.quintin.java2.Assignments.Assignment1.Shapes;
import java.util.Random;

public class ShapeGenerator {
    public ShapeGenerator(){
    }

    public static Shape generateShape(){
        int coinTossResult = new Random().nextInt(2);

        if (coinTossResult == 0){
            double radius = new Random().nextInt(-100, 100);
            try {
                return new Circle(radius);
            } catch (InvalidShapeParameterException e){
                System.out.println("Error creating circle: " + e);
            }
        }
        else{
            double length = new Random().nextInt(-100, 100);
            double width = new Random().nextInt(-100, 100);
            try{
                return new Rectangle(length,width);
            } catch (InvalidShapeParameterException e){
                System.out.println("Error creating rectangle: " + e);
            }
        }

        return null;
    }
}
