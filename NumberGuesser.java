import java.util.Scanner;

public class NumberGuesser {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Provide an upper bound number for guessing: ");
    int upper = in.nextInt();
    int num = 0;
    int count = 0;
    int min = Integer.MAX_VALUE;
    boolean running = true;
    while (running) {
      int res = (int) (Math.random() * (upper - 1) + 1);
      while(num != res) {
        System.out.print("Guess a number between 1 and " + upper + ": ");
        num = in.nextInt();
        if (num < res) {
          System.out.println("Incorrect. The answer is greater than this value");
        } else {
          System.out.println("Incorrect. The answer is less than this value");
        }
        count++;
      }
      in.nextLine();
      min = Math.min(min, count);
      System.out.println("Congrats! The number was: " + res + ".");
      System.out.println("Number of guesses: " + count);
      System.out.println("Fewest number of guesses for this range: " + min);
      System.out.println("Play again? (Y)es, (N)o");
      String ans = in.nextLine();
      if(ans.equals("Y") || ans.equals("y")) {
        num = 0;
        count = 0;
        continue;
      } else if(ans.equals("N") || ans.equals("n")) {
        running = false;
      } else {
        System.out.println("Invalid input");
      }
    }
    
    in.close();
  }
}