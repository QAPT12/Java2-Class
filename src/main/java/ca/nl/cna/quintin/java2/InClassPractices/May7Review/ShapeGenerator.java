package ca.nl.cna.quintin.java2.InClassPractices.May7Review;
import java.util.ArrayList;
import java.util.Random;

public class ShapeGenerator {

    public static Shape generateShape() {
        Random random = new Random();
        Shape newShape;

        int x = random.nextInt();

        if (x % 2 == 0) {
            newShape = new Rectangle(random.nextDouble(100), random.nextDouble(100));
        } else {
            newShape = new Circle(random.nextDouble(100));
        }
        return newShape;
    }


    public static void main(String[] args) {
        ArrayList<Shape> shapesArray = new ArrayList<Shape>();

        for (int i = 0; i < 5; i++) {
            Shape shape = generateShape();
            shapesArray.add(shape);
        }


        for(Shape x : shapesArray){
            System.out.println(x.getDescription());
        }

    }

}
