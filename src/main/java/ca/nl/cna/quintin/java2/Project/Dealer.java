package ca.nl.cna.quintin.java2.Project;

import java.util.ArrayList;

/**
 * The Dealer class represents the dealer in a blackjack game.
 * It manages the dealer's hand and implements the dealer's play logic.
 */
public class Dealer {

    private BlackJackHand hand;

    /**
     * Constructs a new Dealer with an empty hand.
     */
    public Dealer() {
        this.hand = new BlackJackHand(new ArrayList<PlayingCard>());
    }

    /**
     * Gets the dealer's current hand of cards.
     *
     * @return the dealer's current hand.
     */
    public BlackJackHand getHand() {
        return hand;
    }

    /**
     * The dealer plays according to the standard blackjack rules.
     * The dealer draws cards until the hand's score is at least 17.
     *
     * @param deck the deck from which the dealer draws cards.
     */
    public void play(BlackJackDeck deck) {
        while (this.hand.getScore() < 17) {
            this.hand.addCardToHand(deck.draw());
        }
    }

    /**
     * Resets the dealer's hand by clearing all cards.
     */
    public void resetHand() {
        this.hand.resetHand();
    }
}
