import java.util.Scanner;

public class NumberGuesser {
  private int currentGuess;
  private int targetNum;
  private int currentGuessesCount;
  private int minGuessesCount;
  private int minNumberGuessable;
  private int maxNumberGuessable;
  private Scanner in;

  public NumberGuesser() {
    this.in = new Scanner(System.in);
    this.currentGuess = 0;
    this.targetNum = Integer.MAX_VALUE;
    this.currentGuessesCount = 0;
    this.minGuessesCount = Integer.MAX_VALUE;
    this.minNumberGuessable = 1;
    this.maxNumberGuessable = 1;
  }

  public void playGame() {
    this.queryUserForUpperBound();
    boolean isGameRunning = true;
    while (isGameRunning) {
      this.runGuessSession();
      this.displayCompletionStats();
      isGameRunning = this.doesUserWantToPlayAgain();
    }
  }

  private void queryUserForUpperBound() {
    this.maxNumberGuessable = this.queryIntegerInput("Provide an upper bound number for guessing: ");
  }

  private void runGuessSession() {
    this.targetNum = this.getRandomNumberInGuessingRange();
    while (this.currentGuess != this.targetNum) {
      this.askUserToGuess();
    }
    // Need to flush buffer since Scanner::nextInt does not read newlines
    this.flushRemainingInputBuffer();
  }

  private int getRandomNumberInGuessingRange() {
    return (int) (Math.random() * (this.maxNumberGuessable - this.minNumberGuessable) + this.minNumberGuessable);
  }

  private void askUserToGuess() {
    this.currentGuess = this.queryIntegerInput("Guess a number between 1 and " + this.maxNumberGuessable + ": ");
    this.currentGuessesCount++;
    if (this.currentGuess < this.targetNum) {
      System.out.println("Incorrect. The answer is greater than this value");
    } else if (this.currentGuess > this.targetNum) {
      System.out.println("Incorrect. The answer is less than this value");
    }
  }

  private void flushRemainingInputBuffer() {
    this.in.nextLine();
  }

  private void displayCompletionStats() {
    this.minGuessesCount = Math.min(this.minGuessesCount, this.currentGuessesCount);
    System.out.println("Congrats! The number was: " + this.targetNum + ".");
    System.out.println("Number of guesses: " + this.currentGuessesCount);
    System.out.println("Fewest number of guesses for this range: " + this.minGuessesCount);
  }

  private boolean doesUserWantToPlayAgain() {
    String response = this.queryStringInput("Play again? (Y)es, (N)o: ");
    if(response.equals("Y") || response.equals("y")) {
      this.resetCurrentSessionStats();
      return true;
    }
    return false;
  }

  private void resetCurrentSessionStats() {
    this.currentGuessesCount = 0;
    this.currentGuess = 0;
  }

  private String queryStringInput(String prompt) {
    System.out.print(prompt);
    String response = in.nextLine();
    return response;
  }

  private int queryIntegerInput(String prompt) {
    System.out.print(prompt);
    int response = in.nextInt();
    return response;
  }
}
