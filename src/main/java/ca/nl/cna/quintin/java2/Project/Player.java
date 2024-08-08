package ca.nl.cna.quintin.java2.Project;

import java.util.ArrayList;

/**
 * The Player class represents a player in a blackjack game.
 * It manages the player's bank (money) and hand of cards.
 */
public class Player {
    private int bank;
    private BlackJackHand hand;

    /**
     * Constructs a new Player with the specified initial bank amount.
     *
     * @param bank the initial amount of money the player has.
     */
    public Player(int bank) {
        this.bank = bank;
        this.hand = new BlackJackHand(new ArrayList<PlayingCard>());
    }

    /**
     * Gets the current bank amount of the player.
     *
     * @return the current bank amount.
     */
    public int getBank() {
        return bank;
    }

    /**
     * Gets the player's current hand of cards.
     *
     * @return the player's current hand.
     */
    public BlackJackHand getHand() {
        return hand;
    }

    /**
     * Resets the player's hand by clearing all cards.
     */
    public void resetHand() {
        this.hand.resetHand();
    }

    /**
     * Makes a bet by subtracting the bet amount from the player's bank if the bet is valid.
     * A valid bet is greater than 0 and less than or equal to the current bank.
     *
     * @param bet the amount to bet.
     * @return the bet amount if the bet is valid, otherwise 0.
     */
    public int makeBet(int bet) {
        if (bet <= this.bank && bet > 0) {
            this.bank -= bet;
            return bet;
        } else {
            return 0;
        }
    }

    /**
     * Adds the winnings from a bet to the player's bank.
     * The winnings are twice the bet amount.
     *
     * @param bet the amount bet.
     */
    public void winBet(int bet) {
        this.bank += 2 * bet;
    }

    /**
     * Adds a specified amount of money to the player's bank.
     *
     * @param amount the amount to add.
     */
    public void addMoney(int amount) {
        this.bank += amount;
    }
}
