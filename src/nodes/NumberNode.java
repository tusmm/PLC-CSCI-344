package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;


public class NumberNode implements OperandNode {
   Token token;
   
   public NumberNode(Token token) { 
        this.token = token;
   }

   public static NumberNode parseNumberNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // first check if the token list is empty
        if (tokens.size() == 0) {
            throw new SyntaxErrorException("No tokens to parse", 0, "NumberNode.java");
        }        
        Token token = tokens.get(0); // get the front of the token
        if (token.getTokenType() == TokenType.NUMBER) {
            tokens.remove(0); // take off the first element
            return new NumberNode(token);
        }
        throw new SyntaxErrorException("Invalid Number Node", token.getLineNum(), token.getFilename());
   }

@Override
public String convertToJott() {
    return token.getToken();
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
