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
    public static void elevator(int total_buttons){
        int[] valid_buttons = new int[total_buttons];
        valid_buttons[0] = -1;
        int current_index = 1;
        int current_num = 1;
        // looping and putting numbers which are valid into the button array
        // the loop stops when the array is full, meaning requested amount of buttons have been filled
        while (current_index != total_buttons){
            // if number is not 13 and does not contain 4, then it is a valid button and will be put into the array
            if ( current_num != 13 && !Integer.toString(current_num).contains("4")){
                valid_buttons[current_index] = current_num;
                current_index += 1;
            }
            // increment the current number by 1 after each check
            current_num += 1;
        }
        // printing out the numbers in pairs as requested from biggest to smallest
        for (int i = total_buttons-2; i >=0;i -=2){
            System.out.println(Integer.toString(valid_buttons[i]) + "  " + Integer.toString(valid_buttons[i+1]));
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();;
        elevator(num);
    }
}
