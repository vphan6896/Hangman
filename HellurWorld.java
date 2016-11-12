import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		intro();
		PhraseBank phrases = buildPhraseBank(args);
		// CS312 Students -- Do not create any additional Scanners.
		Scanner keyboard = new Scanner(System.in);

		// CS12 students: add your code here
		game(keyboard, phrases);
		keyboard.close();
	}

	// CS12 students: add your methods here.

	// Build the PhraseBank.
	// If args is empty or null, build the default phrase bank.
	// If args is not null and has a length greater than 0
	// then the first elements is assumed to be the name of the
	// file to build the PhraseBank from.
	public static PhraseBank buildPhraseBank(String[] args) {
		PhraseBank result;
		if (args == null || args.length == 0 || args[0] == null || args[0].length() == 0)
			result = new PhraseBank();
		else
			result = new PhraseBank(args[0]);
		return result;
	}

	// show the intro to the program
	public static void intro() {
		System.out.println("This program plays the game of hangman.");
		System.out.println();
		System.out.println("The computer will pick a random phrase.");
		System.out.println("Enter letters for your guess.");
		System.out.println("After 5 wrong guesses you lose.");
	}

	// run the entirety of the hangman game and ask if the player would like to
	// play another method
	public static void game(Scanner keyboard, PhraseBank phrases) {
		System.out.println();
		System.out.println("I am thinking of a " + phrases.getTopic() + " ...");
		System.out.println();
		String secretPhrase = phrases.getNextPhrase();
		String currentPhrase = makeCurrentPhrase(secretPhrase);
		String abc = makeABC();
		int wrong = 0;

		while (wrong < 5 && !currentPhrase.equals(secretPhrase)) {
			String guess = round(keyboard, phrases, secretPhrase, currentPhrase, abc);
			currentPhrase = guessResults(guess, secretPhrase, currentPhrase);
			abc = changeABC(guess, abc);
			wrong += wrongGuess(guess, secretPhrase);
			System.out.println("Number of wrong guesses so far: " + wrong);
		}
		winOrLose(currentPhrase, secretPhrase);
		playAgain(keyboard, phrases);
	}

	// asks the user if they want to play again and if so play the game method
	// again
	public static void playAgain(Scanner keyboard, PhraseBank phrases) {
		System.out.println("Do you want to play again?");
		System.out.print("Enter 'Y' or 'y' to play again: ");
		String answer = keyboard.nextLine();
		if (answer.equals("Y") || answer.equals("y")) {
			game(keyboard, phrases);
		}
	}

	// determines if the player won or lost
	public static void winOrLose(String currentPhrase, String secretPhrase) {
		if (currentPhrase.equals(secretPhrase)) {
			System.out.println("The phrase is " + secretPhrase + ".");
			System.out.println("You win!!");
		} else {
			System.out.println("You lose. The secret phrase was " + secretPhrase);
		}
	}

	// run a round of the game: get the guess, check it for errors and, returns
	// the guess
	public static String round(Scanner keyboard, PhraseBank phrases, String secretPhrase, String currentPhrase,
			String abc) {
		String guess = getGuess(keyboard, phrases, currentPhrase, abc);
		guess = errorCheck(keyboard, guess, abc, currentPhrase);
		return guess;
	}

	// prints the current phrase, prints the string of ABC's, and returns the
	// guess
	public static String getGuess(Scanner keyboard, PhraseBank phrases, String currentPhrase, String abc) {
		System.out.println("The current phrase is " + currentPhrase);
		System.out.println();
		System.out.println("The letters you have not guessed yet are: ");
		System.out.println(abc);
		System.out.println();
		System.out.print("Enter your next guess: ");
		String g = keyboard.nextLine();
		g = g.toUpperCase();
		return g;
	}

	// checks the guess and makes a new current phrase if the guess was a letter
	// of the secret phrase
	public static String guessResults(String guess, String secretPhrase, String currentPhrase) {
		System.out.println();
		System.out.println("You guessed " + guess + ".");
		System.out.println();

		if (secretPhrase.contains(guess)) {
			currentPhrase = newCurrentPhrase(guess, secretPhrase, currentPhrase);
			System.out.println("That is present in the secret phrase.");
			System.out.println();
		} else {
			System.out.println("That is not present in the secret phrase.");
			System.out.println();
		}
		return currentPhrase;
	}

	// checks to see if the guess was a letter of the secret phrase and then
	// returns 1 if the guess is wrong or 0 if it is right
	public static int wrongGuess(String guess, String secretPhrase) {
		int numWrong = 0;
		if (!secretPhrase.contains(guess)) {
			numWrong++;
		}
		return numWrong;
	}

	// returns a string of asterisks based off the length of the secret phrase
	public static String makeCurrentPhrase(String sp) {
		String phrase = "";
		for (int i = 0; i < sp.length(); i++) {
			String spString = sp.charAt(i) + "";
			if (spString.equals("_")) {
				phrase += "_";
			} else
				phrase += "*";
		}
		return phrase;
	}

	// returns a string that consists of the alphabet separated by underscores
	public static String makeABC() {
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String newABC = "";

		for (int i = 0; i < abc.length() - 1; i++) {
			newABC += abc.charAt(i) + "_";
		}
		newABC += abc.charAt(abc.length() - 1);

		return newABC;
	}

	// changes the abc string based off of the guess
	public static String changeABC(String guess, String abc) {
		String beginning = abc.substring(0, abc.indexOf(guess));
		String end = "";

		if (!guess.equals("Z")) {
			end = abc.substring(abc.indexOf(guess) + 2);
		} else {
			beginning = abc.substring(0, abc.indexOf(guess) - 1);
		}
		return beginning + end;
	}

	// checks to see if the character given is a letter or not
	public static String errorCheck(Scanner keyboard, String guess, String abc, String phrase) {
		if (guess.length() > 1) { // changes a word to the first letter
			guess = guess.charAt(0) + "";
		}
		// checks if the guess is at least one character, part of the alphabet,
		// or hasn't already been
		// guessed
		while (guess.length() < 1 || !abc.contains(guess) || phrase.contains(guess) || guess.equals("_")) {
			System.out.println();
			System.out.println(guess + " is not a valid guess.");
			System.out.println("The letters you have not guessed yet are: ");
			System.out.println(abc);
			System.out.println();
			System.out.print("Enter your next guess: ");
			guess = keyboard.nextLine();
			guess = guess.toUpperCase();
		}
		return guess;
	}

	// changes the current phrase to insert the guess if it matches a letter in
	// the secret phrase
	public static String newCurrentPhrase(String guess, String phrase, String display) {
		String result = "";

		for (int i = 0; i < phrase.length(); i++) {
			String letterString = phrase.charAt(i) + "";
			if (letterString.equals(guess)) {
				result += guess;
			} else if (letterString.equals("_")) {
				result += "_";
			} else
				result += display.charAt(i) + "";
		}
		return result;
	}

}
