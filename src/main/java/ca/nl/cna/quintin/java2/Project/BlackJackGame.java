package ca.nl.cna.quintin.java2.Project;

/**
 * The BlackJackGame class represents a game of blackjack.
 * It manages the deck, player, dealer, and the current bet.
 */
public class BlackJackGame {

    private BlackJackDeck deck;
    public Player player;
    public Dealer dealer;
    private int currentBet;

    /**
     * Constructs a new BlackJackGame with the given player.
     *
     * @param player the player participating in the game.
     */
    public BlackJackGame(Player player) {
        this.deck = new BlackJackDeck(6);
        this.player = player;
        this.dealer = new Dealer();
        this.currentBet = 0;
    }

    /**
     * Shuffles the deck.
     */
    public void shuffleDeck() {
        this.deck.shuffle();
    }

    /**
     * Adds a card to the player's hand.
     */
    public void playerHit() {
        player.getHand().addCardToHand(deck.draw());
    }

    /**
     * Executes the dealer's play logic.
     */
    public void dealerPlay() {
        dealer.play(deck);
    }

    /**
     * Gets the value of the player's hand.
     *
     * @return the score of the player's hand.
     */
    public int getPlayerHandValue() {
        return player.getHand().getScore();
    }

    /**
     * Gets the value of the dealer's hand.
     *
     * @return the score of the dealer's hand.
     */
    public int getDealerHandValue() {
        return dealer.getHand().getScore();
    }

    /**
     * Checks if the player is bust (score exceeds 21).
     *
     * @return true if the player is bust, false otherwise.
     */
    public boolean isPlayerBust() {
        return player.getHand().isBust();
    }

    /**
     * Checks if the dealer is bust (score exceeds 21).
     *
     * @return true if the dealer is bust, false otherwise.
     */
    public boolean isDealerBust() {
        return dealer.getHand().isBust();
    }

    /**
     * Checks if the player has a blackjack (score is 21 with exactly 2 cards).
     *
     * @return true if the player has a blackjack, false otherwise.
     */
    public boolean playerBlackJack() {
        return player.getHand().hasBlackJack();
    }

    /**
     * Checks if the dealer has a blackjack (score is 21 with exactly 2 cards).
     *
     * @return true if the dealer has a blackjack, false otherwise.
     */
    public boolean dealerBlackJack() {
        return dealer.getHand().hasBlackJack();
    }

    /**
     * Sets the current bet amount.
     *
     * @param bet the bet amount.
     */
    public void setCurrentBet(int bet) {
        this.currentBet = bet;
    }

    /**
     * Initializes the hands of the player and dealer by dealing two cards to each.
     */
    public void initializeHands() {
        player.resetHand();
        dealer.resetHand();
        dealer.getHand().addCardToHand(deck.draw());
        dealer.getHand().addCardToHand(deck.draw());
        player.getHand().addCardToHand(deck.draw());
        player.getHand().addCardToHand(deck.draw());
    }

    /**
     * Resolves the current bet based on the hands of the player and dealer.
     *
     * @return a string describing the result of the bet and the player's new bank amount.
     */
    public String resolveBet() {
        System.out.println("Dealer hand: " + dealer.getHand());
        System.out.println("Player hand: " + player.getHand());
        if (isPlayerBust()) {
            return "Dealer Wins, Player Bust. New Bank: " + player.getBank();
        } else if (isDealerBust()) {
            player.winBet(currentBet);
            return "Player Wins, Dealer Bust. New Bank: " + player.getBank();
        } else if (getPlayerHandValue() > getDealerHandValue()) {
            player.winBet(currentBet);
            return "Player Wins the Hand. New Bank: " + player.getBank();
        } else if (getPlayerHandValue() < getDealerHandValue()) {
            return "Dealer Wins the Hand. New Bank: " + player.getBank();
        } else if (getPlayerHandValue() == getDealerHandValue()) {
            player.addMoney(currentBet);
            return "Tie game. New Bank: " + player.getBank();
        }
        this.currentBet = 0;
        return null;
    }
}
