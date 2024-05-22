package ca.nl.cna.quintin.java2.InClassPractices.May7Review;

public class Rectangle extends Shape{

    private double width;
    private double length;

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double area() {
        return this.length * this.width;
    }

    @Override
    public String getDescription() {
        return String.format("This is a rectangle with Width %.2f and Length %.2f. Its Area is %.2f",this.width, this.length,  this.area());
    }
}
