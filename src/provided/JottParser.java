package provided;

/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */

import java.util.ArrayList;

import nodes.FunctionCallNode;
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
<<<<<<< Updated upstream
      try {
          root = FunctionCallNode.parseFunctionCallNode(tokens);
      } catch(SyntaxErrorException e) {
          System.err.println(e.getMessage());
          return null;
      }
=======
       try {
           root = ProgramNode.parseProgramNode(tokens);
       } catch(SyntaxErrorException e) {
           System.err.println(e.getMessage());
           e.printStackTrace();
           return null;
       }
>>>>>>> Stashed changes

      return root;
    }
}
