package ca.nl.cna.quintin.java2.Project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The BlackJackClientHandler class handles client requests for the blackjack game.
 * It manages the communication between the client and the game logic.
 */
public class BlackJackClientHandler implements Runnable {
    private Socket clientSocket;
    private Player player;
    private BlackJackGame game;
    private JSONParser parser = new JSONParser();
    private int currentBet;

    /**
     * Constructs a new BlackJackClientHandler with the given client socket.
     *
     * @param socket the client socket.
     */
    public BlackJackClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.player = new Player(100);
        this.game = new BlackJackGame(this.player);
        this.currentBet = 0;
    }

    /**
     * The run method to handle client requests in a separate thread.
     */
    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                String request = in.readUTF();
                System.out.println("Received request: " + request);

                JSONObject response = handleRequest(request);
                System.out.println("Sending response: " + response.toJSONString());
                out.writeUTF(response.toJSONString());
                out.flush();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the client request and returns a response in JSON format.
     *
     * @param request the client request in JSON string format.
     * @return the response as a JSONObject.
     * @throws ParseException if the request cannot be parsed.
     */
    private JSONObject handleRequest(String request) throws ParseException {
        JSONObject jsonRequest = (JSONObject) parser.parse(request);
        String action = (String) jsonRequest.get("action");

        JSONObject response = new JSONObject();

        switch (action) {
            case "GAME_START":
                game.shuffleDeck();
                response.put("status", "Game started");
                break;
            case "GET_FUNDS":
                response.put("funds", game.player.getBank());
                break;
            case "RESET_HANDS":
                game.initializeHands();
                if (game.playerBlackJack() || game.dealerBlackJack()) {
                    response.put("status", "Blackjack");
                } else if (game.isDealerBust() || game.isPlayerBust()) {
                    response.put("status", "Bust");
                } else {
                    response.put("status", "Hands reset");
                }
                break;
            case "GET_HANDS":
                response.put("dealerHand", game.dealer.getHand().toJson());
                response.put("playerHand", game.player.getHand().toJson());
                break;
            case "PLACE_BET":
                currentBet = ((Long) jsonRequest.get("bet")).intValue();
                if (currentBet <= 0 || currentBet > player.getBank()) {
                    response.put("status", "Invalid Bet");
                } else {
                    player.makeBet(currentBet);
                    game.setCurrentBet(currentBet);
                    response.put("status", "Bet placed");
                }
                break;
            case "HIT":
                game.playerHit();
                response.put("status", "Player hit");
                response.put("playerHand", game.player.getHand().toJson());
                if (game.isPlayerBust()) {
                    response.put("status", "Player bust");
                    game.resolveBet();
                } else {
                    response.put("isBust", false);
                }
                break;
            case "RESOLVE":
                game.dealerPlay();
                response.put("status", "Resolving game");
                response.put("dealerHand", game.dealer.getHand().toJson());
                response.put("playerHand", game.player.getHand().toJson());
                String result = game.resolveBet();
                System.out.println("RESULT: " + result);
                response.put("result", result);
                break;
            default:
                response.put("status", "Unknown action");
        }

        return response;
    }
}
