package ThreeDoorGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ThreeDoorGame {
    private int trials;
    private int stayWins;
    private int switchWins;

    public ThreeDoorGame(int trials) {
        this.trials = trials;
    }

    public void play() {
        for (int i = 0; i < trials; i++) {
            if (playGame(false)) {
                stayWins++;
            }
            if (playGame(true)) {
                switchWins++;
            }
        }
    }

    public void displayResults() {
        System.out.println("Winning by staying: " + ((double) stayWins / trials) * 100 + "%");
        System.out.println("Winning by switching: " + ((double) switchWins / trials) * 100 + "%");
    }

    private boolean playGame(boolean switchChoice) {
        List<String> curtains = new ArrayList<>(Arrays.asList("G", "D1", "D2"));
        Random rand = new Random();
        
        // Randomly choose a curtain for the contestant
        String contestantChoice = curtains.get(rand.nextInt(3));
        String initialChoice = contestantChoice;

        // Host's turn to open a curtain
        curtains.remove(contestantChoice); // Remove contestant's choice first
        if (contestantChoice.equals("G")) {
            curtains.remove(rand.nextInt(2)); // Randomly remove one of the duds
        } else {
            curtains.remove("G"); // Remove the good prize if contestant's initial choice was a dud
        }

        if (switchChoice) {
            contestantChoice = curtains.get(0); // Only one curtain is left
        }

        return (switchChoice && !initialChoice.equals("G")) || (!switchChoice && initialChoice.equals("G"));
    }

      
}

