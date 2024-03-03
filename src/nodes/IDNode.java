package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;


public class IDNode implements OperandNode {
   Token token;
   
   public IDNode(Token token) { 
        this.token = token;
   }

   public static IDNode parseIDNode(ArrayList<Token> tokens) {
        // first check if the token list is empty
        if (tokens.size() == 0) {
            System.out.println("Handle exception here");
        }        
        Token name = tokens.get(0); // get the front of the token
        if (name.getTokenType() == TokenType.ID_KEYWORD) {
            tokens.remove(0); // take off the first element
            return new IDNode(name);
        }
        System.out.println("Handle exception here");
        return null;
   }
}
