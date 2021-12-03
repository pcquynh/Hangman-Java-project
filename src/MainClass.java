import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        HangManGame hangManGame = new HangManGame();
        System.out.println("Welcome to Hangman Game with Christmas Holiday theme ");
        Scanner scanner = new Scanner(System.in);
        while (hangManGame.getCount() < hangManGame.getMaxCount()
                && hangManGame.getAsterisk().contains(("*"))) {
            System.out.println("Guess the word with " + hangManGame.getWord().length() + " characters.");
            System.out.println(hangManGame.getAsterisk());
            System.out.println("Guess any letter: ");
            String guessWord = scanner.nextLine();
            hangManGame.playGame(guessWord);
        }
        System.out.println("You lost the game. The word to find is " + hangManGame.getWord());
    }
}
