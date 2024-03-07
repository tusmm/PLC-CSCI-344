package nodes;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.List;

public class TypeNode implements JottTree {

    private Token token;
    private static ArrayList<String> validTypes = new ArrayList(List.of(new String[]{"Double", "Integer", "String", "Boolean"}));

    public TypeNode(Token token) {
        this.token = token;
    }
    public static TypeNode parseTypeNode(ArrayList<Token> tokens) {

        if (tokens.isEmpty()) {
            System.err.println("No tokens left to parse type.");
            return null;
        }
        Token nextToken = tokens.get(0);

        if (nextToken.getTokenType() != TokenType.ID_KEYWORD) {
            System.err.println("Syntax Error:");
            String unexpected = nextToken.getTokenType().toString().toLowerCase();
            System.err.println("Expected type but got " + unexpected);
            System.err.println(nextToken.getFilename() + ":" + nextToken.getLineNum());
            return null;
        }

        if(!validTypes.contains(nextToken.getToken())) {
            System.err.println("Syntax Error:");
            String unexpected = nextToken.getTokenType().toString().toLowerCase();
            System.err.println("Not a valid type: " + unexpected);
            System.err.println(nextToken.getFilename() + ":" + nextToken.getLineNum());
            return null;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }
}
