import java.util.Scanner;
import java.util.Random;
class Number
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        Random ram=new Random();

        int guessNumber;
        boolean playAgain=true;

        while(playAgain)
        {
            int attempt=10;
            boolean won=false;
            int toGuess=ram.nextInt(100)+1;

            System.out.println("            ----Welcome to the Number Game..----");
             System.out.println();
            System.out.println("You have "+attempt+" attempts");

            while(attempt>0)
            {
                 System.out.println();
                System.out.println("Enter the guessing number: ");
                guessNumber=sc.nextInt();
                attempt--;

                if(guessNumber==toGuess)
                {
                    System.out.println("Congratulations!.You Guessed the Number");
                    won=true;
                    break;
                }
                else if(guessNumber<toGuess)
                {
                     System.out.println("Your guess is too high");
                }
                else{
                    System.out.println("Your guess is too low");
                }
                System.out.println("Attempts left: "+attempt);
            }

            if(!won)
            {
                System.out.println("Sorry.You have used all ateempts. The number was : "+toGuess);

            }
            System.out.println("Do you want to play again?(Y/N)");
            sc.nextLine();

            String response=sc.nextLine();
            playAgain=response.equalsIgnoreCase("Y");
        }
        System.out.println("Thank you..!");
    }
}