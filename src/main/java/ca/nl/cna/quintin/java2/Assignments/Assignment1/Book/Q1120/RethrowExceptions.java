package ca.nl.cna.quintin.java2.Assignments.Assignment1.Book.Q1120;

public class RethrowExceptions {

    public static void main(String[] args) {
        try{
            Method1();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void Method1() throws Exception {
        try{
            Method2();
        } catch(Exception e){
            throw e;
        }
    }

    public static void Method2() throws Exception {
        throw new Exception("thrown in method 2");
    }



}
