public class HangManGame {
    private final String[] words = {"appreciative","angel", "bells", "baking", "belief", "candle", "carol",
    "celebrate", "ceremony", "chimney", "chocolate", "cookies", "december", "decorations", "elf", "family",
    "festive", "gift", "gingerbread", "green", "holiday", "jesus", "lights", "magic", "mittens", "music",
    "ornaments", "party", "parade", "red", "ribbon", "snow", "star", "surprise", "toys", "tree", "wreath"};
    private final String word = words[((int)(Math.random()* words.length))];
    private String asterisk = new String(new char[word.length()]).replace("\0", "*");
    private int count = 0;
    private final int maxCount = 7;

    public String[] getWords() {
        return words;
    }

    public String getWord() {
        return word;
    }

    public String getAsterisk() {
        return asterisk;
    }

    public int getCount() {
        return count;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void playGame(String guessWord){
        String newAsterisk = "";
        for (int i = 0; i < word.length(); i++){
            if (this.word.charAt(i) == guessWord.charAt(0)){
                newAsterisk += guessWord.charAt(0);
            }
            else if (asterisk.charAt(i) != '*'){
                newAsterisk += asterisk.charAt(i);
            }
            else {
                newAsterisk += "*";
            }
        }
        if (asterisk.equals(word)){
            System.out.println("You win! The correct word is " + word);
        }
        else if(asterisk.equals(newAsterisk)){
            count++;
            hangManPicture(count);
        }
        else{
            asterisk = newAsterisk;
        }
    }

    public void hangManPicture(int count){
        switch(count){
            case 1:
                System.out.println("Incorrect letter! Please try again") ;
                System.out.println("/");
                break;
            case 2:
                System.out.println("Incorrect letter! Please try again") ;
                System.out.println("/ \\");
                break;
            case 3:
                System.out.println("Incorrect letter! Please try again") ;
                System.out.println(" |");
                System.out.println("/ \\");
                break;
            case 4:
                System.out.println("Incorrect letter! Please try again") ;
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("/ \\");
                break;
            case 5:
                System.out.println("Incorrect letter! Please try again") ;
                System.out.println("__");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println(" / \\");
                break;
            case 6:
                System.out.println("Incorrect letter! Please try again") ;
                System.out.println("__ __");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println(" / \\");
                break;
            default:
                System.out.println("Incorrect letter! Please try again") ;
                System.out.println("  O  ");
                System.out.println("__ __");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println(" / \\");
                break;
        }
    }
}

