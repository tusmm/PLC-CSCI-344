package provided;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;


public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){
      ArrayList<Token> tokens = new ArrayList<Token>(); 
      int lineNum = 1; // iterate for every new line

      try (Scanner scanner = new Scanner(new File(filename))) {
        // gets rid of whitespace and new lines
        scanner.useDelimiter("\\s+");
        while (scanner.hasNext()) {
          String token = scanner.next();
          System.out.println(token);
        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

		return null;
	}
}