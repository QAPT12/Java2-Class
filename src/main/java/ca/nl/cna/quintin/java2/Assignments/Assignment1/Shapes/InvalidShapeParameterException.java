package ca.nl.cna.quintin.java2.Assignments.Assignment1.Shapes;

public class InvalidShapeParameterException extends Exception{
    private final static String MSG = "Shape parameter is not valid!";

    public InvalidShapeParameterException() {
        super(MSG);
    }
}
