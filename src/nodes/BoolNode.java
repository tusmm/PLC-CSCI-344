package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class BoolNode implements ExpressionNode {
    Token token;

    public BoolNode(Token token) {
        this.token = token;
    }

    public static BoolNode parseBoolNode(ArrayList<Token> tokens) {
        // check if empty
        if (tokens.size() == 0) {
            // handle error 
            return null;
        }

        Token token = tokens.get(0); // get first token
        if (token.getTokenType() != TokenType.ID_KEYWORD) {
            System.err.println("Syntax Error:");
            System.err.println("Expected id");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null;
        }

        String bool = token.getToken();
        if (bool.equals("True") || bool.equals("False")) {
            return new BoolNode(token);
        }

        System.err.println("Syntax Error:");
        System.err.println("Expected True or False but got neither");
        System.err.println(token.getFilename() + ":" + token.getLineNum());
        return null;
    }

    @Override
    public String convertToJott() {
        if (token.getToken().equals("True")) {
            return "True";
        } else {
            return "False";
        }
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