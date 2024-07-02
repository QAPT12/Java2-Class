package ca.nl.cna.quintin.java2.Assignments.Assignment4.Book.Q1615;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class ReverseLinkedList {

    public static void main(String[] args) {
        LinkedList<Character> characterList = new LinkedList<Character>(Arrays.asList('a','b','c','d','e','f','g','h','i','j'));
        LinkedList<Character> characterListCopy = new LinkedList<Character>(characterList);

        Collections.reverse(characterListCopy);

        System.out.println("character List: " + characterList);
        System.out.println("Character List Reversed " + characterListCopy);
    }

}
