package ca.nl.cna.quintin.java2.Project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Class to represent a hand of cards in a game of BlackJack.
 */
public class BlackJackHand {
    private ArrayList<PlayingCard> cardsInHand;

    /**
     * Constructs a new BlackJackHand with the given list of cards.
     *
     * @param cardsInHand the initial list of cards in the hand.
     */
    public BlackJackHand(ArrayList<PlayingCard> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    /**
     * Gets the list of cards currently in the hand.
     *
     * @return the list of cards in the hand.
     */
    public ArrayList<PlayingCard> getCardsInHand() {
        return this.cardsInHand;
    }

    /**
     * Adds a card to the hand.
     *
     * @param card the card to add.
     */
    public void addCardToHand(PlayingCard card) {
        this.cardsInHand.add(card);
    }

    /**
     * Calculates and returns the score of the hand.
     * Face cards (J, Q, K) are worth 10 points.
     * Aces are worth 11 points if it doesn't cause a bust, otherwise they are worth 1 point.
     *
     * @return the score of the hand.
     */
    public int getScore(){
        int score = 0;
        for (PlayingCard card: this.cardsInHand) {
            if(card.getValue() > 10) {
                score += 10;
            } else if(card.getValue() == 1 && score + 11 <= 21) {
                score += 11;
            } else {
                score += card.getValue();
            }
        }
        return score;
    }

    /**
     * Checks if the hand is a bust (score exceeds 21).
     *
     * @return true if the hand is bust, false otherwise.
     */
    public boolean isBust() {
        return this.getScore() > 21;
    }

    /**
     * Checks if the hand is a blackjack (score is 21 with exactly 2 cards).
     *
     * @return true if the hand is a blackjack, false otherwise.
     */
    public boolean hasBlackJack() {
        return this.getScore() == 21 && this.cardsInHand.size() == 2;
    }

    /**
     * Resets the hand by removing all cards.
     */
    public void resetHand() {
        this.cardsInHand.clear();
    }

    /**
     * Converts the hand to a JSON representation.
     *
     * @return a JSONObject representing the hand.
     */
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray cardsArray = new JSONArray();

        for (PlayingCard card : cardsInHand) {
            JSONObject cardJson = new JSONObject();
            cardJson.put("card", card.toString());
            cardsArray.add(cardJson);
        }

        json.put("cards", cardsArray);
        json.put("score", this.getScore());
        return json;
    }

    /**
     * Returns a string representation of the hand.
     *
     * @return a string representation of the hand.
     */
    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();

        for (PlayingCard card : this.cardsInHand) {
            handString.append(card.toString()).append(" ");
        }

        return handString.toString();
    }
}
