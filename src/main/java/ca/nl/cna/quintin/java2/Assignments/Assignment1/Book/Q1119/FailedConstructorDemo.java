package ca.nl.cna.quintin.java2.Assignments.Assignment1.Book.Q1119;

public class FailedConstructorDemo {

    public static void main(String[] args) {
        try{
            SomeClass badClass = new SomeClass();
        } catch (Exception e){
            System.err.println("Error: " + e);
        }
    }
}
