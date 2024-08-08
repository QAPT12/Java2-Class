package ca.nl.cna.quintin.java2.Project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * The BlackJackGameClient class manages the client's interaction with the blackjack game server.
 * It handles sending requests to the server and processing the responses.
 */
public class BlackJackGameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;
    private static final JSONParser parser = new JSONParser();

    /**
     * Parses a JSON hand into a string representation.
     *
     * @param hand the hand in JSON format.
     * @param hideFirst whether to hide the first card (for the dealer's hand).
     * @return the string representation of the hand.
     */
    public static String parseJsonHand(JSONObject hand, Boolean hideFirst) {
        StringBuilder handString = new StringBuilder();
        JSONArray cards = (JSONArray) hand.get("cards");

        for (Object cardObject : cards) {
            JSONObject card = (JSONObject) cardObject;
            String cardString = (String) card.get("card");

            if (hideFirst) {
                handString.append("?").append("?").append("  ");
                hideFirst = false;
            } else {
                handString.append(cardString).append("  ");
            }
        }

        return handString.toString().trim();
    }

    /**
     * The main method to run the blackjack game client.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            // Start the game
            JSONObject startRequest = new JSONObject();
            startRequest.put("action", "GAME_START");
            out.writeUTF(startRequest.toJSONString());
            out.flush();
            in.readUTF(); // Read the response to start request (even if not used)
            System.out.println("Welcome to BlackJack game\n");

            while (true) {
                // Show the current funds
                JSONObject fundsRequest = new JSONObject();
                fundsRequest.put("action", "GET_FUNDS");
                out.writeUTF(fundsRequest.toJSONString());
                out.flush();
                String fundsResponse = in.readUTF();
                JSONObject fundsJson = (JSONObject) parser.parse(fundsResponse);
                long funds = (long) fundsJson.get("funds");
                if (funds <= 0) {
                    System.out.println("Looks like you're out of money, chump.");
                    break;
                } else {
                    System.out.println("Current Funds: " + funds);
                }

                // Get a bet
                int bet = -1;
                boolean validBet = false;
                while (!validBet) {
                    System.out.println("Enter bet: ");
                    try {
                        bet = scanner.nextInt();
                        scanner.nextLine();

                        if (bet <= 0) {
                            System.out.println("Bet must be a positive integer.");
                        } else {
                            validBet = true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.next();
                    }
                }
                JSONObject betRequest = new JSONObject();
                betRequest.put("action", "PLACE_BET");
                betRequest.put("bet", bet);
                out.writeUTF(betRequest.toJSONString());
                out.flush();
                String betResponse = in.readUTF();
                JSONObject betJson = (JSONObject) parser.parse(betResponse);
                System.out.println(betJson.get("status"));

                if (betResponse.contains("Invalid Bet")) {
                    continue;
                }

                // Reset the hands
                JSONObject resetRequest = new JSONObject();
                resetRequest.put("action", "RESET_HANDS");
                out.writeUTF(resetRequest.toJSONString());
                out.flush();
                String resetResponse = in.readUTF();

                while (true) {
                    if (resetResponse.contains("Blackjack") || resetResponse.contains("Bust")) {
                        break;
                    }
                    // Show hands
                    JSONObject handRequest = new JSONObject();
                    handRequest.put("action", "GET_HANDS");
                    out.writeUTF(handRequest.toJSONString());
                    out.flush();
                    String handResponse = in.readUTF();
                    JSONObject handJson = (JSONObject) parser.parse(handResponse);

                    // Handle hands
                    JSONObject playerHand = (JSONObject) handJson.get("playerHand");
                    JSONObject dealerHand = (JSONObject) handJson.get("dealerHand");

                    System.out.println();
                    System.out.println("Dealer Hand: " + BlackJackGameClient.parseJsonHand(dealerHand, true));
                    System.out.println("Player Hand: " + BlackJackGameClient.parseJsonHand(playerHand, false));

                    // Hit or stay
                    System.out.println("Enter action (HIT, STAY): ");
                    String action = scanner.nextLine().toUpperCase();
                    if (action.equals("STAY")) {
                        break;
                    }

                    JSONObject actionRequest = new JSONObject();
                    actionRequest.put("action", action);
                    out.writeUTF(actionRequest.toJSONString());
                    out.flush();

                    String actionResponse = in.readUTF();

                    if (actionResponse.contains("Player bust")) {
                        System.out.println("Player Bust.");
                        break;
                    }
                    if (actionResponse.contains("Unknown action")) {
                        System.out.println("Unknown action, please choose HIT or STAY");
                    }
                }
                // Handle message for player winning or losing etc.
                JSONObject resolveRequest = new JSONObject();
                resolveRequest.put("action", "RESOLVE");
                out.writeUTF(resolveRequest.toJSONString());
                out.flush();
                String resolveResponse = in.readUTF();

                JSONObject resolveJson = (JSONObject) parser.parse(resolveResponse);
                // Handle hands
                JSONObject playerHand = (JSONObject) resolveJson.get("playerHand");
                JSONObject dealerHand = (JSONObject) resolveJson.get("dealerHand");

                System.out.println();
                System.out.println(resolveJson.get("result"));
                System.out.println("Dealer Hand: " + BlackJackGameClient.parseJsonHand(dealerHand, false));
                System.out.println("Player Hand: " + BlackJackGameClient.parseJsonHand(playerHand, false));
                System.out.println();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
