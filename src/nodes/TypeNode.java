package nodes;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.List;

public class TypeNode implements JottTree {

    private Token token;
    public static ArrayList<String> validTypes = new ArrayList<String>(
            List.of(new String[] { "Double", "Integer", "String", "Boolean" }));

    public TypeNode(Token token) {
        this.token = token;
    }

    public static TypeNode parseTypeNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reach EOF while parsing a type";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token nextToken = tokens.get(0);

        if (nextToken.getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxErrorException(
                    "Expected type keyword while parsing type but got: "
                            + nextToken.getTokenType().toString().toLowerCase(),
                    nextToken.getLineNum(), nextToken.getFilename());
        }

        if (!validTypes.contains(nextToken.getToken())) {
            throw new SyntaxErrorException("Not a valid type: " + nextToken.getToken(), nextToken.getLineNum(),
                    nextToken.getFilename());
        }

        tokens.remove(0);
        return new TypeNode(nextToken);

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
        return "";
    }

    @Override
    public void validateTree() {
        return; 
    }

    @Override
    public String toString() {
        return token.getToken();
    }
}
