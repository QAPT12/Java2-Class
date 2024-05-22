package ca.nl.cna.quintin.java2.Assignments.Assignment1.Shapes;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[5];

        int num = 0;
        for (Shape shape : shapes) {
            shape = ShapeGenerator.generateShape();
            shapes[num] = shape;
            num++;
            System.out.println(shape.getDescription());
        }
    }
}