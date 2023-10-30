package MonteCarloCardGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


public class HandEvaluator {
    private List<Card> deck;
    private List<Card> hand;

    public HandEvaluator() {
        deck = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String value : values) {
                int numericValue;
                switch (value) {
                    case "Jack":
                        numericValue = 11;
                        break;
                    case "Queen":
                        numericValue = 12;
                        break;
                    case "King":
                        numericValue = 13;
                        break;
                    case "Ace":
                        numericValue = 14;
                        break;
                    default:
                        numericValue = Integer.parseInt(value);
                        break;
                }
                deck.add(new Card(suit, String.valueOf(numericValue)));
            }
        }
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            System.out.println("The deck is empty. Please reset the deck.");
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(deck.size());
        return deck.remove(randomIndex);
    }

    public List<Card> drawHand(int handSize) {
        hand = new ArrayList<>();
        for (int i = 0; i < handSize; i++) {
            Card card = drawCard();
            if (card != null) {
                hand.add(card);
            } else {
                System.out.println("Cannot draw a hand; the deck is empty.");
                return null;
            }
        }
        return hand;
    }

    public void resetDeck() {
        deck.clear();
        initializeDeck();
    }
    
    // Method to print out a hand (FOR TESTING ONLY)
    public void printHand() {
        if (hand != null) {
            for (Card card : hand) {
                System.out.print(card + " ");
            }
            System.out.println();
        } else {
            System.out.println("Hand is empty.");
        }
    }
    
    

 // Method to evaluate if the hand contains a pair
    public boolean hasPair() {
    	for (int i = 0; i < hand.size() - 1; i++) {
            int count = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getValue().equals(hand.get(j).getValue())) {
                    count++;
                }
            }
            if (count == 2) {
                return true;
            }
        }
        return false;
    }

    // Method to evaluate if the hand contains three of a kind
    public boolean hasThreeOfAKind() {
    	for (int i = 0; i < hand.size(); i++) {
            int count = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getValue().equals(hand.get(j).getValue())) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

 // Method to evaluate if the hand contains four of a kind
    public boolean hasFourOfAKind() {
    	for (int i = 0; i < hand.size(); i++) {
            int count = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getValue().equals(hand.get(j).getValue())) {
                    count++;
                }
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    // Method to evaluate if the hand contains a straight
    public boolean hasStraight() {
    	List<Integer> values = new ArrayList<>();
        for (Card card : hand) {
            values.add(toIntValue(card.getValue()));  // Pass the card's value to toIntValue
        }

        Collections.sort(values);
        
        // Check for special case: Ace, 2, 3, 4, 5
        if (values.equals(Arrays.asList(2, 3, 4, 5, 14))) {
            return true;
        }

        int consecutiveCount = 0;
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) + 1 == values.get(i + 1)) {
                consecutiveCount++;
            } else {
                consecutiveCount = 0;
            }
            if (consecutiveCount == 4) {
                return true; // Found five consecutive values
            }
        }
        return false;
    }



    private Integer toIntValue(String CardValue) {
    	try {
            return Integer.parseInt(CardValue);
        } catch (NumberFormatException e) {
            switch (CardValue) {
                case "Jack":
                    return 11;
                case "Queen":
                    return 12;
                case "King":
                    return 13;
                case "Ace":
                    return 14;
                default:
                    return 0;  
            }
        }
    }
	

	// Method to evaluate if the hand contains a flush
    public boolean hasFlush() {
        String firstSuit = hand.get(0).getSuit();
        for (Card card : hand) {
            if (!card.getSuit().equals(firstSuit)) {
                return false;
            }
        }
        return true;
    }

    // Method to evaluate if the hand contains a full house
    public boolean hasFullHouse() {
    	// Check for Three of a Kind and Pair without a Four of a Kind
        return hasThreeOfAKind() && hasPair() && !hasFourOfAKind();
    }

    // Method to evaluate if the hand contains a straight flush
    public boolean hasStraightFlush() {
        return hasFlush() && hasStraight();
    }

    // Method to evaluate if the hand contains a royal flush
    public boolean hasRoyalFlush() {
    	if (!hasFlush()) return false; // all cards must have the same suit for a royal flush

        String[] royalValues = {"10", "Jack", "Queen", "King", "Ace"};
        List<String> cardValues = new ArrayList<>();

        for (Card card : hand) {
            cardValues.add(card.getValue());
        }

        for (String royalValue : royalValues) {
            if (!cardValues.contains(royalValue)) {
                return false;
            }
        }
        return true;
    }

	public static void simulateHands() {
		HandEvaluator evaluator = new HandEvaluator();
        int numSimulations = 10000;
        int handSize = 5;

        int pairCount = 0;
        int threeOfAKindCount = 0;
        int fourOfAKindCount = 0;
        int straightCount = 0;
        int flushCount = 0;
        int fullHouseCount = 0;
        int straightFlushCount = 0;
        int royalFlushCount = 0;

        for (int i = 0; i < numSimulations; i++) {
            List<Card> hand = evaluator.drawHand(handSize);
            evaluator.resetDeck();

            // Evaluate the hand for various poker hands
            if (evaluator.hasPair()) {
                pairCount++;
            }
            if (evaluator.hasThreeOfAKind()) {
                threeOfAKindCount++;
            }
            if (evaluator.hasFourOfAKind()) {
                fourOfAKindCount++;
            }
            if (evaluator.hasStraight()) {
                straightCount++;
            }
            if (evaluator.hasFlush()) {
                flushCount++;
            }
            if (evaluator.hasFullHouse()) {
                fullHouseCount++;
            }
            if (evaluator.hasStraightFlush()) {
                straightFlushCount++;
            }
            if (evaluator.hasRoyalFlush()) {
                royalFlushCount++;
            }
        }

        // Calculate probabilities
        double pairProbability = (double) pairCount / numSimulations;
        double threeOfAKindProbability = (double) threeOfAKindCount / numSimulations;
        double fourOfAKindProbability = (double) fourOfAKindCount / numSimulations;
        double straightProbability = (double) straightCount / numSimulations;
        double flushProbability = (double) flushCount / numSimulations;
        double fullHouseProbability = (double) fullHouseCount / numSimulations;
        double straightFlushProbability = (double) straightFlushCount / numSimulations;
        double royalFlushProbability = (double) royalFlushCount / numSimulations;

        // Output results to CSV file
        try (FileWriter writer = new FileWriter("monte_carlo.csv")) {
            writer.write("Poker Hand,Probability\n");
            writer.write("Pair," + pairProbability + "\n");
            writer.write("Three of a Kind," + threeOfAKindProbability + "\n");
            writer.write("Four of a Kind," + fourOfAKindProbability + "\n");
            writer.write("Straight," + straightProbability + "\n");
            writer.write("Flush," + flushProbability + "\n");
            writer.write("Full House," + fullHouseProbability + "\n");
            writer.write("Straight Flush," + straightFlushProbability + "\n");
            writer.write("Royal Flush," + royalFlushProbability + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
		
	}

}