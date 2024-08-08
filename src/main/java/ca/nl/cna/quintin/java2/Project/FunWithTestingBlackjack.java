package ca.nl.cna.quintin.java2.Project;

import java.util.ArrayList;

public class FunWithTestingBlackjack {

    public static void main(String[] args) {
        BlackJackDeck bjDeck = new BlackJackDeck(6);

        Player player = new Player(100);

        System.out.println(player.getHand());

        player.getHand().addCardToHand(bjDeck.draw());

        System.out.println(player.getHand());


    }
}
