package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class IDNode implements OperandNode {
    Token token;

    public IDNode(Token token) {
        this.token = token;
    }

    public static IDNode parseIDNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing id";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token token = tokens.get(0); // get the front of the token
        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            tokens.remove(0); // take off the first element
            return new IDNode(token);
        }
        throw new SyntaxErrorException("Invalid ID: " + token.getToken(), token.getLineNum(), token.getFilename());
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
        return toString();
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public void validateTree() {
    }

    @Override
    public String toString() {
        return token.getToken();
    }

    @Override
    public String getType() {
        return SymbolTable.getVariableType(SymbolTable.getCurrentScope(), token.getToken());
    }
}
