package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class StringLiteralNode implements ExpressionNode{
    Token token;

    public StringLiteralNode(Token token) {
        this.token = token;
    }

    public static StringLiteralNode parseStringLiteralNode(ArrayList<Token> tokens) {
        // check if empty
        if (tokens.size() == 0) {
            // handle error 
            // missing string literal
            return null;
        }

        Token token = tokens.get(0);
        if (token.getTokenType() != TokenType.STRING) {
            System.err.println("Syntax Error:");
            String unexpected = token.getTokenType().toString().toLowerCase();
            System.err.println("Expected string but got " + unexpected);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null;
        }

        tokens.remove(0); // remove string literal
        return new StringLiteralNode(token);
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
