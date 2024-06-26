package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class StringLiteralNode implements ExpressionNode {
    Token token;

    public StringLiteralNode(Token token) {
        this.token = token;
    }

    public static StringLiteralNode parseStringLiteralNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reach EOF while parsing a String literal";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token token = tokens.get(0);
        if (token.getTokenType() != TokenType.STRING) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected string but got " + unexpected;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
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
        return token.getToken();
    }

    @Override
    public String convertToC() {
        return convertToJott();
    }

    @Override
    public String convertToPython() {
        return token.getToken();
    }

    @Override
    public void validateTree() {
        
    }

    @Override
    public String getType() {
        return "String";
    }
}