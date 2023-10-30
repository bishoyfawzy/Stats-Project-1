//
//
//package BirthdayMatch;
//import java.util.Random;
//
//public class BirthdayMatch {
//    public static void main(String[] args) {
//        java.util.Scanner input = new java.util.Scanner(System.in);
//        
//        // Input the number of people in the group
//        System.out.print("Enter the number of people in the group: ");
//        int numPeople = input.nextInt();
//        
//        // Input the number of trials to perform
//        System.out.print("How many trials would you like to perform?: ");
//        int numTrials = input.nextInt();
//        
//        int numMatches = 0;  // Count of trials with at least one matching birthday
//        
//        // Performing the specified number of trials
//        for (int i = 0; i < numTrials; i++) {
//            boolean[] birthdays = new boolean[365];  // Array that tracks birthdays
//            
//            // Generate a random birthday for each individual in the group
//            boolean hasMatch = false;
//            for (int j = 0; j < numPeople; j++) {
//                int randomDay = new Random().nextInt(365);
//                if (birthdays[randomDay]) {
//                    hasMatch = true;
//                    break;
//                } else {
//                    birthdays[randomDay] = true;
//                }
//            }
//            
//            // If at least one match is found, count it as a win
//            if (hasMatch) {
//                numMatches++;
//            }
//        }
//        
//        // Calculate the percentage of trials with at least one matching birthday
//        double percentage = (double) numMatches / numTrials * 100;
//        
//        System.out.println("Percentage of trials with at least one matching birthday: " + percentage + "%");
//        
//        // Close the scanner
//        input.close();
//    }
//}
//
package BirthdayMatch;

import java.util.Random;
import java.util.Scanner;

public class BirthdayMatch {
    private int numPeople;
    private int numTrials;

    public BirthdayMatch() {
        this.numPeople = getUserInput("Enter the number of people in the group: ");
        this.numTrials = getUserInput("How many trials would you like to perform?: ");
    }

    public void displayMatchingPercentage() {
        double percentage = calculateMatchingPercentage();
        System.out.println("Percentage of trials with at least one matching birthday: " + percentage + "%");
    }

    private double calculateMatchingPercentage() {
        int numMatches = 0; // Count of trials with at least one matching birthday

        // Performing the specified number of trials
        for (int i = 0; i < numTrials; i++) {
            if (hasMatchingBirthday()) {
                numMatches++;
            }
        }

        // Calculate the percentage of trials with at least one matching birthday
        return (double) numMatches / numTrials * 100;
    }

    private boolean hasMatchingBirthday() {
        boolean[] birthdays = new boolean[365]; // Array that tracks birthdays

        // Generate a random birthday for each individual in the group
        Random random = new Random();
        for (int j = 0; j < numPeople; j++) {
            int randomDay = random.nextInt(365);
            if (birthdays[randomDay]) {
                return true;
            } else {
                birthdays[randomDay] = true;
            }
        }
        return false;
    }

    private static int getUserInput(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        return input.nextInt();
    }
}

