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

    public static FunctionReturnNode parseFunctionReturnNode(ArrayList<Token> tokens) {

        if (tokens.isEmpty()) {
            System.err.println("No tokens left to parse type.");
            return null;
        }
        Token nextToken = tokens.get(0);

        if (nextToken.getTokenType() != TokenType.ID_KEYWORD) {
            System.err.println("Syntax Error:");
            String unexpected = nextToken.getTokenType().toString().toLowerCase();
            System.err.println("Expected return type but got " + unexpected);
            System.err.println(nextToken.getFilename() + ":" + nextToken.getLineNum());
            return null;
        }

        if (nextToken.getToken().equals("Void")) {
            tokens.remove(0);
            return new FunctionReturnNode(null, true);
        }

        TypeNode typeNode = TypeNode.parseTypeNode(tokens);
        if(typeNode == null) {
            System.err.println("Invalid type while parsing Return Statement");
            return null;
        }

        return new FunctionReturnNode(typeNode, false);

    }

    @Override
    public String convertToJott() {
        if(isVoid) {
            return "Void";
        }

        return type.convertToJott();
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
