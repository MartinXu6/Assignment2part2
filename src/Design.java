/**
 * @author Martin Xu
 * @version 2026-03-10
 * <p>
 * Solution to the code gold problem -superstitious-hotel-elevator.
 * <p>
 * Optionally: The class has a method elevator which prints out the buttons as the problem requests given the input from the
 * main method using scanner.
 */
import java.util.Scanner;

public class Design {

    // Constants for the rules of which numbers to skip
    private static final int SKIPPED_NUMBER = 13;
    private static final String FORBIDDEN_DIGIT = "4";
    private static final int COLUMNS = 2;  // Two columns in the button layout

    /**
     * Validates that input is a positive even integer.
     * Returns true if valid, false otherwise with an error message.
     */
    public static boolean isValidInput(String input) {
        int inputNum;
        try {
            // Integer.parseInt() converts a String like "14" into the integer 14
            // If the String contains letters (like "abc") or decimals (like "3.5"),
            // it throws a NumberFormatException which we catch below
            inputNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Input needs to be an Integer, please try again.");
            return false;  // Stop execution and indicate invalid input
        }

        // Check if the number is positive (greater than 0)
        // <= 0 catches negative numbers like -5 and zero
        if (inputNum <= 0) {
            System.out.println("Input needs to be a positive integer, please try again.");
            return false;
        }

        // The % operator gives the remainder after division
        // inputNum % COLUMNS == 0 means the number divides 2 with 0 remainder, meaning it is even
        // Example: 14 % 2 = 0 (even), 15 % 2 = 1 (odd)
        if (inputNum % COLUMNS != 0) {
            System.out.println("Input needs to be even, please try again.");
            return false;
        }
        return true;  // All validation checks passed
    }

    /**
     * Generates and prints the elevator button layout.
     * Buttons are: -1 followed by numbers that aren't 13 and don't contain digit 4.
     * Printed in two columns, reading bottom-to-top gives ascending order.
     */
    public static void printButtonLayout(int floorCount) {
        // Create an array to store all valid button numbers in ascending order
        // The array size is floorCount because we need exactly that many buttons
        int[] validNumbers = new int[floorCount];

        // The first button is always -1 (represents basement)
        validNumbers[0] = -1;

        int currentIndex = 1;  // Tracks next empty position in the array (index 1 is second slot)
        int candidate = 1;      // The next number we'll test for validity, starting from 1

        // Keep adding numbers until we've filled the array completely
        // When currentIndex equals floorCount, the array is full
        while (currentIndex < floorCount) {
            // Check if this candidate violates the "skip 13" rule
            boolean isSkipped = (candidate == SKIPPED_NUMBER);

            // Integer.toString(candidate) converts the number to a String like "14"
            // .contains(FORBIDDEN_DIGIT) checks if the String contains "4"
            // Example: "14".contains("4") returns true, "15".contains("4") returns false
            // This is how we skip numbers like 4, 14, 24, 40, 41, etc.
            boolean containsForbiddenDigit = Integer.toString(candidate).contains(FORBIDDEN_DIGIT);

            // Only add the number if it passes both checks
            // !isSkipped means "our current number is NOT 13"
            // !containsForbiddenDigit means "our current number does NOT contain digit 4"
            // && means both conditions must be true (logical AND operation)
            if (!isSkipped && !containsForbiddenDigit) {
                // Store the valid number in the current empty position
                validNumbers[currentIndex] = candidate;
                currentIndex++;  // Move to the next position in the array
            }
            // Always increment candidate to check the next number
            // This continues until we've found enough valid numbers to fill the array
            candidate++;
        }

        // Print the buttons in reverse order with two per line
        // Starting from the last pair and moving backwards to the first pair
        // floorCount - COLUMNS gives the index of the first number in the last pair
        // Example: if floorCount = 14, we start at index 12 (positions 12 and 13)
        // We decrement by 2 (COLUMNS) each time to move to the previous pair
        for (int i = floorCount - COLUMNS; i >= 0; i -= COLUMNS) {
            // validNumbers[i] is the left button, validNumbers[i + 1] is the right button
            // "\t" inserts a tab character between them for proper spacing
            System.out.println(validNumbers[i] + "\t" + validNumbers[i + 1]);
        }
        // This prints pairs from largest to smallest, but when read bottom-to-top
        // (last line first, then moving up), we get the original ascending order
    }

    public static void main(String[] args) {
        // Scanner reads input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Read the first line of input from the user
        String userInput = scanner.nextLine();

        // Keep asking for input until the user provides a valid number
        // isValidInput prints an error message and returns false for invalid input
        while (!isValidInput(userInput)) {
            userInput = scanner.nextLine();  // Read another attempt if the previous one failed the validation check
        }

        // Convert the validated input from String to int
        int floorCount = Integer.parseInt(userInput);

        // Generate and print the button layout
        printButtonLayout(floorCount);

        scanner.close();
    }
}
