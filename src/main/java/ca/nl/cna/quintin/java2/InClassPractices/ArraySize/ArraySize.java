package ca.nl.cna.quintin.java2.InClassPractices.ArraySize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySize {
    /**
     * Class to test out how to loop backwards through an array list using for loop.
     * Done while helping josh with RSS Feed Assignment
     * 
     */

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        for (int i = array.size() - 1; i >= array.size() - 3; i--) {
            System.out.println(array.get(i));
        }
    }
}


