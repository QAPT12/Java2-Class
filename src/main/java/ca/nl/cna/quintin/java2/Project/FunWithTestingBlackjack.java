package ca.nl.cna.quintin.java2.Project;

import java.util.ArrayList;

public class FunWithTestingBlackjack {

    public static void main(String[] args) {
        BlackJackDeck bjDeck = new BlackJackDeck(6);

        BlackJackHand hand = new BlackJackHand(new ArrayList<PlayingCard>());

        hand.addCardToHand(new PlayingCard(3, PlayingCard.Suit.SPADES));
        hand.addCardToHand(new PlayingCard(1, PlayingCard.Suit.SPADES));
        hand.addCardToHand(new PlayingCard(11, PlayingCard.Suit.SPADES));

        System.out.println(hand.getScore());


    }
}
