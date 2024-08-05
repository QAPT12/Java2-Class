package ca.nl.cna.quintin.java2.Project;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class to represent a deck of playing cards.
 *
 * @author quintin.tuck
 */
public class PlayingCardDeck {

    private ArrayList<PlayingCard> cardsArray;

    /**
     * Constructor for the deck of cards. Loops through values 1 - 13 and adds a card for each suit to the array list.
     */
    public PlayingCardDeck() {
        cardsArray = new ArrayList<>();
        for (int i = 1; i <= 13 ; i++) {
            this.cardsArray.add(new PlayingCard(i, PlayingCard.Suit.DIAMONDS));
            this.cardsArray.add(new PlayingCard(i, PlayingCard.Suit.HEARTS));
            this.cardsArray.add(new PlayingCard(i, PlayingCard.Suit.SPADE));
            this.cardsArray.add(new PlayingCard(i, PlayingCard.Suit.CLUBS));
        }
    }

    /**
     * printDeck. Method print out the contents of the deck.
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
        PlayingCard drawnCard = this.cardsArray.get(0);
        this.cardsArray.remove(0);
        return drawnCard;
    }

}
