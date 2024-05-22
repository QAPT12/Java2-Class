package ca.nl.cna.quintin.java2.InClassPractices.May7Review;

public class Circle extends Shape{

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    public String getDescription() {
        return String.format("This is a circle with Radius %.2f. Its Area is %.2f",this.radius, this.area());
    }
}
