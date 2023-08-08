package com.rahul;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        
        int attempts = 0;
        int maxAttempts = 10; // You can adjust this value as needed
        
        System.out.println("Welcome to Guess the Number!");
        System.out.println("I've chosen a number between " + lowerBound + " and " + upperBound + ".");
        
        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;
            
            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                break;
            } else if (userGuess < targetNumber) {
                System.out.println("Try a higher number.");
            } else {
                System.out.println("Try a lower number.");
            }
            
            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've reached the maximum number of attempts.");
                System.out.println("The correct number was: " + targetNumber);
            }
        }
        
        System.out.println("Thank you for playing Guess the Number!");
    }
}
