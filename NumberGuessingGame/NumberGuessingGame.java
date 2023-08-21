import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int targetNumber = random.nextInt(100) + 1;
        int attempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100. You have only 5 attempts to guess a number");

        while (attempts < 5) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                break;
            } else if (userGuess < targetNumber) {
                System.out.println("Try a higher number. Attempts: " + attempts);
            } else {
                System.out.println("Try a lower number. Attempts: " + attempts);
            }
        }

        if (attempts >= 5) {
            System.out.println("Game over. The number was: " + targetNumber);
        }

        scanner.close();
    }
}
