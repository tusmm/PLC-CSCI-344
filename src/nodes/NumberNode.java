package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;


public class NumberNode implements OperandNode {
   Token token;
   boolean isNeg;
   
   public NumberNode(Token token) { 
        this.token = token;
        this.isNeg = false;
   }

   public NumberNode(Token token, boolean isNeg) {
       this.token = token;
       this.isNeg = isNeg;
   }

    public static NumberNode parseNumberNode(ArrayList<Token> tokens) throws SyntaxErrorException {
       return parseNumberNode(tokens, false);
    }

   public static NumberNode parseNumberNode(ArrayList<Token> tokens, boolean isNeg) throws SyntaxErrorException {
        // first check if the token list is empty
           if (tokens.get(0).getTokenType() == TokenType.EOF) {
               String message = "No tokens to parse";
               throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
           }
        Token token = tokens.get(0); // get the front of the token
        if (token.getTokenType() == TokenType.NUMBER) {
            tokens.remove(0); // take off the first element
            return new NumberNode(token, isNeg);
        }
        throw new SyntaxErrorException("Invalid Number Node", token.getLineNum(), token.getFilename());
   }

@Override
public String convertToJott() {
    return (isNeg ? "-" : "") + token.getToken();
}

@Override
public String convertToJava(String className) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'convertToJava'");
}

@Override
public String convertToC() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'convertToC'");
}

@Override
public String convertToPython() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
}

@Override
public boolean validateTree() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
}
}
