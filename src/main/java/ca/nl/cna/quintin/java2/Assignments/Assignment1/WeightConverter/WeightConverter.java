package ca.nl.cna.quintin.java2.Assignments.Assignment1.WeightConverter;

public class WeightConverter {
    public static double poundsToKilos(double pounds) {
        assert pounds > 0 : "Weight given must be greater than 0";
        return pounds / 2.205;
    }

    public static double kilosToPounds(double kilos) {
        assert kilos > 0 : "Weight given must be greater than 0";
        return kilos * 2.205;
    }

    public static void main(String[] args) {
        // pounds to kilos correct
        double correctPounds = 10.0;
        double kilos = poundsToKilos(correctPounds);
        System.out.printf("%.2f pounds = %.2f kilos%n", correctPounds, kilos);

        // pounds to kilos bad
        try{
            double badKilos = poundsToKilos(0);
        } catch (AssertionError e) {
            System.err.println("Error: " + e);
        }

        // kilos to pounds correct
        double correctKilos = 10.0;
        double pounds = poundsToKilos(correctKilos);
        System.out.printf("%.2f kilos = %.2f pounds%n", correctKilos, pounds);

        // kilos to pounds bad
        try{
            double badPounds = kilosToPounds(0);
        } catch (AssertionError e) {
            System.err.println("Error: " + e);
        }

    }
}
