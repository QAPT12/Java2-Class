package ca.nl.cna.quintin.java2.Project;

/**
 * Class representing a playing card.
 *
 * @author quintin.tuck
 */

public class PlayingCard {

    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    private final int value;
    private final Suit suit;

    /**
     * Constructor for the playing card. Takes a face value as an int and suit.
     * @param value Face value of the card. Must tbe between ACE and KING, default is ACE
     * @param suit suit of the card i.e. "DIAMONDS".
     */
    public PlayingCard(int value, Suit suit) {
        if(value < ACE || value > KING) {
            throw new IllegalArgumentException("Invalid value for card");
        }
        if(suit == null) {
            throw new IllegalArgumentException("Must specify a suit");
        }

        this.value = value;
        this.suit = suit;
    }

    /**
     * Get suit.
     * @return suit, the suit of the playing card. i.e. "DIAMONDS".
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Get value.
     * @return value, the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Converts value of card to its face name if card is a face card.
     * Uses unicode characters for suits to display the suit.
     * @return returnString, string combination of the cards value and suit.
     */
    @Override
    public String toString() {
        String returnString = "";

        if(this.value == ACE){
            returnString += "A";
        } else if (this.value == JACK) {
            returnString += "J";
        } else if (this.value == QUEEN) {
            returnString += "Q";
        } else if (this.value == KING){
            returnString += "K";
        } else {
            returnString += this.value;
        }

        if(this.suit == Suit.DIAMONDS){
            returnString += "♦";
        } else if (this.suit == Suit.HEARTS) {
            returnString += "♥";
        } else if (this.suit == Suit.SPADES) {
            returnString += "♠";
        } else if (this.suit == Suit.CLUBS) {
            returnString += "♣";
        }

        return returnString;
    }

    /**
     * Stored values of the playing card suits.
     */
    public enum Suit {DIAMONDS, HEARTS, CLUBS, SPADES}
}
