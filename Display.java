import java.util.Scanner;

public class Display{
  WordleBoard board;
  Scanner input;

  public Display(WordleBoard board, Scanner input){
    this.board = board;
    this.input = input;
  }

  public void print() {
    printGuesses();
    printBlanks();
  }

   public static boolean binarySearchWord(String word, int min, int max) {

    if (min > max){
      return false;
    }
    else {
      int mid =(min + max)/2;
      if(word.equals(Main.wordsArray[mid-1])){
        return true;
      }
      else if(word.compareTo(Main.wordsArray[mid-1])>0){
        return binarySearchWord(word, mid +1, max); //mid + 1 is the new min 
      }
      else{
        return binarySearchWord(word, min, mid-1); //mid-1 is the new max 
      }
    }
  }
  public static boolean isCommonWord(String word){
    return binarySearchWord(word, 0, Main.wordsArray.length);
  }
    

  public String promptGuess() {
    while(true) {
      System.out.println("\nEnter a 5 letter word:");
      String guess = input.nextLine().toLowerCase();
 
			boolean validWord = isCommonWord(guess);
			if (!validWord) {
        System.out.println("\nPlease enter a valid word");
			} else {
        board.guess(guess);
        return guess;
      }
    }
  }

  public void printGuesses() {
    StringBuilder b = new StringBuilder();
    for (String s:board.guesses){
      for(int i = 0;i < s.length(); i++){
        String answer = board.getWord();
        char c = s.charAt(i);

        if(answer.charAt(i) == c){
          b.append(Main.ANSI_GREEN + c + Main.ANSI_RESET);
        } else if(answer.contains(Character.toString(c))){
          b.append(Main.ANSI_YELLOW + c + Main.ANSI_RESET);
        } else{
          b.append(c);
        }
        b.append("|");
      }
      b.setLength((b.length() - 1));
      System.out.println(b.toString());
      b = new StringBuilder();
    }
  }

  public void printBlanks() {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < board.getAttemptsRemaining(); i++) {
      for (int j = 0; j < board.getWord().length(); j++) {
        b.append("_|");
      }
      b.setLength(b.length() - 1);
      System.out.println(b.toString());
      b = new StringBuilder();
    }
  }
}

//define all 26 alphabets a-z 
//create array/variable and have a list that has list of all banned characters
//print the characters in the first list of alphabets that is not in the second banend list 
//keep updating the banned characters 
//all unique letters (not have double letters or triple... )
