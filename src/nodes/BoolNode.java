package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class BoolNode implements ExpressionNode {
    Token token;

    public BoolNode(Token token) {
        this.token = token;
    }

    public static BoolNode parseBoolNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing boolean";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token token = tokens.get(0); // get first token
        if (token.getTokenType() != TokenType.ID_KEYWORD) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected id but got " + unexpected;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }

        String bool = token.getToken();
        if (bool.equals("True") || bool.equals("False")) {
            tokens.remove(0);
            return new BoolNode(token);
        }

        String message = "Expected True or False but got neither";
        String filename = token.getFilename();
        int lineNum = token.getLineNum();
        throw new SyntaxErrorException(message, lineNum, filename);
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
        if (token.getToken().equals("True")) {
            return "true";
        } else {
            return "false";
        }
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
    public void validateTree() {
        return; 
    }

    @Override
    public String getType() {
        return "Bool";
    }
}