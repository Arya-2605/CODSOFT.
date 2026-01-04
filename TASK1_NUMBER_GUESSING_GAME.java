
import java.util.Random;
import java.util.Scanner;
import java.util.random.*;
public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
         int numberToguess = random.nextInt(100)+1;
         int userGuess = 0;
         int attempts = 0;

         System.out.println("Welcome to the Number Guessing Game !");
         System.out.println("I have selected thenumber between 1 to 100 . Try to guess it");

         while (userGuess != numberToguess)
         {
            System.out.println("Enter your Guess =");
            userGuess= scanner.nextInt();
            attempts++;
             if (userGuess<numberToguess){
                System.out.println("Too low...Try again");
             }
             else if(userGuess>numberToguess)
             {
                System.out.println("Too high....Try again");

             }
             else {
                System.out.println("Congratulations ! Ypu guessed the number ! ");
                System.out.println("The correct number was :"+ numberToguess);
                System.out.println("Total attempts ="+ attempts);
             }
         }
         scanner.close();
    }
}
