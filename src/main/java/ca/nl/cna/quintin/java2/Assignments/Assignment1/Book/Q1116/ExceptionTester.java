package ca.nl.cna.quintin.java2.Assignments.Assignment1.Book.Q1116;

public class ExceptionTester {
    public static void main(String[] args) {
        // throwing A
        try {
            throw new ExceptionA("this is exception A");
        } catch (ExceptionA e){
            System.err.println(e);
        }

        // Throwing B
        try {
            throw new ExceptionB("this is exception B");
        } catch (ExceptionA e){
            System.err.println(e);
        }

        // Throwing C
        try {
            throw new ExceptionC("this is exception C");
        } catch (ExceptionA e){
            System.err.println(e);
        }
    }
}
