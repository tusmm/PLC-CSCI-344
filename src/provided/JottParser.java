package provided;

/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */

import java.util.ArrayList;

import nodes.ProgramNode;
import nodes.SyntaxErrorException;

public class JottParser {

    /**
     * Parses an ArrayList of Jotton tokens into a Jott Parse Tree.
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     */
    public static JottTree parse(ArrayList<Token> tokens){
      JottTree root;
      Token lastElement = tokens.get(tokens.size() - 1);
      tokens.add(new Token("EOF", lastElement.getFilename(), lastElement.getLineNum(), TokenType.EOF));
       try {
           root = ProgramNode.parseProgramNode(tokens);
       } catch(SyntaxErrorException e) {
           System.err.println(e.getMessage());
           e.printStackTrace();
           return null;
       }

      return root;
    }
}
