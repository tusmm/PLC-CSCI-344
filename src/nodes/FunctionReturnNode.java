package nodes;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class FunctionReturnNode implements JottTree {

    private TypeNode type;
    private boolean isVoid;

    public FunctionReturnNode(TypeNode type, boolean isVoid) {
        this.type = type;
        this.isVoid = isVoid;
    }

    public static FunctionReturnNode parseFunctionReturnNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing function return";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token nextToken = tokens.get(0);

        if (nextToken.getTokenType() != TokenType.ID_KEYWORD) {
            throw new SyntaxErrorException("Expected return type but got: " + nextToken.getToken(),
                    nextToken.getLineNum(), nextToken.getFilename());
        }

        if (nextToken.getToken().equals("Void")) {
            tokens.remove(0);
            return new FunctionReturnNode(null, true);
        }

        TypeNode typeNode = TypeNode.parseTypeNode(tokens);

        return new FunctionReturnNode(typeNode, false);

    }

    @Override
    public String convertToJott() {
        if (isVoid) {
            return "Void";
        }

        return type.convertToJott();
    }

    @Override
    public String convertToJava(String className) {
        if (isVoid) {
            return "void";
        }
        return type.convertToJava(className);
    }

    @Override
    public String convertToC() {
        return (isVoid ? "void" : type.convertToC());
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
        return isVoid ? "Void" : type.toString();
    }
}
