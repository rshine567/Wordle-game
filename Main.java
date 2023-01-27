import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {
  public static String[] wordsArray = new String[1859];
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static void main(String[] args) {
    StringBuilder b = new StringBuilder();
    System.out.print("\033[H\033[2J");  
    System.out.flush();  //clears the console     
    System.out.println("\n\nWelcome to WORDLE!!!!");
    System.out.println("\nRules: Try to guess a 5 letter word ");
    System.out.println("\nThe color of the tiles will change to show how close your guess was to the word");
    System.out.println(b.append(ANSI_GREEN) + "\nGreen Letter - Correct spot");
    System.out.println(b.append(ANSI_YELLOW) + "\nYellow Letter - Letter in the wrong spot");
    System.out.println(b.append(ANSI_RESET)+ "\nWhite Letter - Word doesn't contain that letter");
    
    // Prints the rules and instructions for the game
    Scanner scanner = new Scanner(System.in);
    String userInput =" ";
    do {
		  System.out.print("\n\n\nEnter OK if you have read the rules above: "+ "\n");  //ensures that the reader has read the instructions to proceed to the next step
			userInput = scanner.nextLine().toLowerCase();
      if  (!userInput.equals("ok")) {
		    System.out.println("Try again: ");
		  } 
		} while (!userInput.equals("ok"));
    System.out.print("\033[H\033[2J");  
    System.out.flush();  //clears the console

	try {
    File words = new File("list.txt");
    Scanner myReader = new Scanner(words);
    int index = 0;
    while (myReader.hasNextLine()) {
      wordsArray[index] = myReader.nextLine();
      index = index+1;
    }
    myReader.close();
	} catch (Exception e) {
		System.out.println("Something is wrong with the file.");
	}
    
    String answer = wordsArray [new Random().nextInt(wordsArray.length)].toLowerCase();

    WordleBoard board = new WordleBoard(wordsArray);

    Scanner input = new Scanner (System.in);

    Display display = new Display(board, input);
    
    while (!board.isGameOver()){
      display.print();
      display.promptGuess();
      // System.out.print("\033[H\033[2J");  
      // System.out.flush();
    }
    display.print();
    if(board.didWin()){
      System.out.println("Yoohooo you guessed correctly");
    }
    else{
      System.out.println("Sorry, you did not guess correctly");
    }
    System.out.println("The answer was " + board.getWord());
      input.close();
    
  }
}
