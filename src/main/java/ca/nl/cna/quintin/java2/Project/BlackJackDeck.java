package ca.nl.cna.quintin.java2.Project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to represent a BlackJack deck made up of three decks of playing cards.
 */
public class BlackJackDeck {

    private ArrayList<PlayingCard> cardsArray;

    /**
     * Constructor for the BlackJack deck. Creates three PlayingCardDecks and combines them.
     */
    public BlackJackDeck(int numDecks) {
        cardsArray = new ArrayList<>();

        // Create and add three PlayingCardDecks to the cardsArray
        for (int i = 0; i < numDecks; i++) {
            PlayingCardDeck deck = new PlayingCardDeck();
            cardsArray.addAll(deck.getCardsArray());
        }

        // Shuffle the combined deck
        shuffle();
    }

    /**
     * printDeck. Method to print out the contents of the deck.
     * @return Cards of the deck printed out in order.
     */
    public StringBuilder printDeck() {
        StringBuilder returnString = new StringBuilder();
        for (PlayingCard s: this.cardsArray) {
            returnString.append(s).append(" ");
        }
        return returnString;
    }

    /**
     * getDeckSize.
     * @return Amount of cards left in the deck.
     */
    public int getDeckSize() {
        return this.cardsArray.size();
    }

    /**
     * Shuffle. Randomize the order of the cards array to simulate shuffling a deck of cards.
     */
    public void shuffle(){
        Collections.shuffle(this.cardsArray);
    }

    /**
     * Draw a card. Gets the first card in the cardsArray, or the 'top' card of the deck.
     * @return PlayingCard, the PlayingCard object in position 0 of the arraylist.
     */
    public PlayingCard draw(){
        if (this.cardsArray.isEmpty()) {
            throw new IllegalStateException("No cards left in the deck to draw");
        }
        PlayingCard drawnCard = this.cardsArray.get(0);
        this.cardsArray.remove(0);
        return drawnCard;
    }
}

