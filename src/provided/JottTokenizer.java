package provided;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.util.ArrayList;
import java.io.*;


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

      try {
        BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                            new FileInputStream(filename), "UTF8"));
    
        String line;
        while ((line = br.readLine()) != null) {
          for (int i = 0; i < line.length(); i++) {
            // all characters are passed thru here beside new lines
            System.out.println("Char at: " + line.charAt(i));
          }
            // new lines detected here
          System.out.println("Another line");
        }
        br.close();
    
        } catch (IOException e) {
            e.printStackTrace();
      }

		return tokens;
	}
}