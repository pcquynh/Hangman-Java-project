import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;


public class Hangman {
    private static String[] easyWords = {"apple","animal","banana","bear","bed","bird","can","candy",
            "car","farm","food","fish","fruit","game","green","horse","home","light","love","mouse",
            "phone","pillow","pony","phelf","tree","zebra"};
    private static String[] mediumWords = {"airplane", "building", "beautiful", "cellphone", "clock",
            "computer", "dance", "grain", "hello", "internet", "keyboard", "monitor", "motorcycle",
            "movie", "potato", "shelf", "restaurant", "sailboat", "secret", "television", "turkey",
            "videogame", "website", "wheat" };
    private static String[] hardWords = {"abruptly", "affix", "bandwagon", "buckaroo", "cockiness",
            "croquet", "disavow", "flapjack", "fuchsia", "glowworm", "iatrogenic", "jukebox",
            "kiwifruit", "knapsack", "megahertz", "numbskull", "pneumonia", "razzmatazz", "stymied",
            "transgress", "voyeurism", "whizzing", "zigzagging"};
    private static int wrongCount = 0;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        System.out.println("Let's play Hangman Game\n");
        Scanner sc = new Scanner(System.in);
        String continueInput = "y";
        do {
            System.out.println("Choose options: easy, medium or hard words (e/m/h): ");
            timer();
            String choice = sc.nextLine();
            String[] words = getChoice(choice);
            String word = getWord(words);
            System.out.println("The word contains " + word.length() + " letters");
            List<Character> playerGuesses = new ArrayList<>();
            int wrongCount = 0;
            while (true) {
                printWordState(word, playerGuesses);
                if(playerGuesses.size() > 0) {
                getPlayerWrongGuesses(word, playerGuesses);
                }
                if (!getPlayerGuess(sc, word, playerGuesses)) {
                    wrongCount++;
                    printHangManPicture(wrongCount, word);
                    System.out.println("Incorrect! Try again. \nYou made " + wrongCount + " wrong guesses.\n");
                }
                if (wrongCount >= 6) {
                    System.out.println("You loose! The word to find is " + word);
                    break;
                }
                if (isWinner(word, playerGuesses)) {
                    System.out.println("You win!");
                    break;
                }
                System.out.println("Please enter your guess for the word: ");
                if (sc.nextLine().equals(word)) {
                    System.out.println("You win!");
                    break;
                } else {
                    wrongCount ++;
                    printHangManPicture(wrongCount, word);
                    System.out.println("Nope! Try again.\nYou made " + wrongCount + " wrong guesses.\n");
                }
            }
            System.out.println("Do you want to play again? (y/n): ");
            continueInput = sc.nextLine();
        }while(continueInput.equals("y"));
    }

    /**
     * Choose word's level: easy, medium, hard
     * @param choice
     * @return the String of words
     */
    public static String[] getChoice(String choice){
        switch (choice){
            case "m":
                return mediumWords;
            case "h":
                return hardWords;
            default:
                return easyWords;
        }
    }

    /**
     * Get randomly a String word in String words
     * @param words
     * @return a String word
     */
    public static String getWord(String[] words){
        String word = words[(int) (Math.random() * words.length)];
        return word;
    }

    /**
     * Check if the guessed letter is contained in word
     * @param sc
     * @param word
     * @param playerGuesses
     * @return boolean True or False
     */
    public static boolean getPlayerGuess(Scanner sc,String word, List<Character> playerGuesses){
        System.out.println("Please enter a letter");
        String letterGuess = sc.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }

    /**
     * Print the word's state, the * is replaced with the right guessed letter
     * @param word
     * @param playerGuesses
     */
    public static void printWordState(String word, List<Character> playerGuesses ){
        for (int i = 0; i < word.length(); i++){
            if (playerGuesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
            }
            else{
                System.out.print("*");
            }
        }
        System.out.println();

    }

    /**
     * Print the list of wrong guessed character
     * @param word
     * @param playerGuesses
     */
    public static void getPlayerWrongGuesses(String word, List<Character> playerGuesses ){
        List<Character> playerWrongGuesses = new ArrayList<>();
        for (int i = 0; i < playerGuesses.size(); i++){
            if (!word.contains(playerGuesses.get(i).toString())){
                playerWrongGuesses.add(playerGuesses.get(i));
            }
        }
        if (!playerWrongGuesses.isEmpty()){
            System.out.println("Your wrong guesses:" + playerWrongGuesses);
        }
    }

    /**
     * Check if correct count is equal length of word
     * @param word
     * @param playerGuesses
     * @return boolean true or false
     */
    public static boolean isWinner(String word, List<Character> playerGuesses ){
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++){
            if (playerGuesses.contains(word.charAt(i))){
                correctCount++;
            }
        }
        return (word.length() == correctCount);
    }

    /**
     * Print the hangman picture following the wrong count
     * @param wrongCount
     * @param word
     */
    public static void printHangManPicture(int wrongCount, String word){
        if (wrongCount == 1) {
            System.out.println("   _________");
            System.out.println("   |      _|_");
            System.out.println("   |       0");
            System.out.println("   | ");
            System.out.println("   | ");
            System.out.println("___|___");
        }
        if (wrongCount == 2) {
            System.out.println("   _________");
            System.out.println("   |      _|_");
            System.out.println("   |       0");
            System.out.println("   |       |");
            System.out.println("   | ");
            System.out.println("   | ");
            System.out.println("___|___");
        }
        if (wrongCount == 3) {
            System.out.println("   _________");
            System.out.println("   |      _|_");
            System.out.println("   |       0");
            System.out.println("   |       |");
            System.out.println("   |       |");
            System.out.println("   | ");
            System.out.println("___|___");
        }
        if (wrongCount == 4) {
            System.out.println("   _________");
            System.out.println("   |      _|_");
            System.out.println("   |       0");
            System.out.println("   |      /|");
            System.out.println("   |       |");
            System.out.println("___|___");
        }
        if (wrongCount == 5) {
            System.out.println("   _________");
            System.out.println("   |      _|_");
            System.out.println("   |       0");
            System.out.println("   |      /|\\");
            System.out.println("   |       |");
            System.out.println("___|___");
        }
        if (wrongCount == 6) {
            System.out.println("   _________");
            System.out.println("   |      _|_");
            System.out.println("   |       0");
            System.out.println("   |      /|\\");
            System.out.println("   |       |");
            System.out.println("   |      / ");
            System.out.println("___|___");
        }

        if (wrongCount == 7) {
            System.out.println("   _________");
            System.out.println("   |      _|_");
            System.out.println("   |       0");
            System.out.println("   |      /|\\");
            System.out.println("   |       |");
            System.out.println("   |      / \\");
            System.out.println("___|___ ");
            System.out.println("GAME OVER! The word was " + word);
        }
    }

    /**
     * set timer for the game
     */
    public static void timer(){
        final Runnable runnable = new Runnable() {
            int countdownStarter = 100; //100 seconds
            public void run() {
                //System.out.println(countdownStarter);
                countdownStarter--;
                if (wrongCount == 7){
                    scheduler.shutdown();
                }
                if (countdownStarter < 0) {
                    System.out.println("Timer Over! Good Try");
                    scheduler.shutdown();
                    System.exit(0);
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}

