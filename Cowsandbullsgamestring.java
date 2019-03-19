/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cowsandbullsgamestring;

/**
 *
 * @author bhanu
 */
import java.util.*;
import java.io.*;

public class Cowsandbullsgamestring {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Set<String> possibleChars = generatePossibleString();
        int guesses = 0;
        System.out.println("Play a game with me!\nAnd rules of game goes here...");
        System.out.println("The cows and bulls game, You choose a word and I guesses a word.\n You say bulls when a character"
                + " in my guess's match with \n a character in your's word and also it is in the \n"
                + " correct position as in your's word. You say cows, when a \n character in my guess's word"
                + " match the character in your word, \n but it is not in the correct position."
                + "\n\nALERT: Don't utter your word only say cows and bulls ");
        System.out.println("\n\nThink of a 4 letter word from the list above and I will guess it!\n\n");
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                guesses++;
                Iterator<String> iterate = possibleChars.iterator();
                String ComputerGuess = iterate.next();
                System.out.println("My guess is: " + ComputerGuess);
                System.out.print("Number of cows:");
                int numberOfCows = in.nextInt();
                System.out.print("Number of bulls:");
                int numberOfBulls = in.nextInt();
                removeWrongChars(new CountBullsCows(numberOfBulls, numberOfCows), ComputerGuess, possibleChars);
                if (numberOfBulls == 4) {
                    System.out.println("Guessed in " + guesses + " guesses");
                    break;
                }
            }
        }
    }
    
    /**
     *
     * @param guess
     * @param user
     * @return
     */
    public static CountBullsCows calculateBullandCowCount(String guess, String user) {
        CountBullsCows CountBC = new CountBullsCows(0, 0);
        for (int i = 0; i < user.length(); i++) {
            if (guess.charAt(i) == user.charAt(i)) {
                CountBC.countBull++;
            } else if (guess.contains(String.valueOf(user.charAt(i)))) {
                CountBC.countCow++;
            }
        }
        return CountBC;
    }
    
    /**
     *
     * @param guessCountBC
     * @param guess
     * @param possibleChars
     */
    public static void removeWrongChars(CountBullsCows guessCountBC, String guess,
            Set<String> possibleChars) {
        Iterator<String> iterate = possibleChars.iterator();
        while (iterate.hasNext()) {
            String str = iterate.next();
            if (calculateBullandCowCount(guess, str).equals(guessCountBC) == false) {
                iterate.remove();
            }
        }
    }
    
    public static Set<String> generatePossibleString() throws FileNotFoundException, IOException {
        Set<String> set = new HashSet<>();
        try (FileReader fr = new FileReader("/Users/bhanu/Desktop/project/4 letter dictionary words.txt"); 
                BufferedReader br = new BufferedReader(fr)) {
            String buffer;
            while ((buffer = br.readLine()) != null) {
                //System.out.println(buffer);
                set.add(String.valueOf(buffer));
            }
        }
        Iterator<String> iterate = set.iterator();
        while (iterate.hasNext()) {
            String str = iterate.next();
            Set<Character> chars = new HashSet<>();
            for (char c : str.toCharArray()) {
                if (chars.contains(c)) {
                    iterate.remove();
                    break;
                }
                chars.add(c);
            }
        }
        return set;
    }
}
