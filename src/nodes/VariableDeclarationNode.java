package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class VariableDeclarationNode implements JottTree {
    private TypeNode type;
    private IDNode id;

    public VariableDeclarationNode(TypeNode type, IDNode id) {
        this.type = type;
        this.id = id;
    }

    public static VariableDeclarationNode parseVariableDeclarationNode(ArrayList<Token> tokens) {
        if (tokens.isEmpty()) {
            System.err.println("Empty token list");
            return null;
        }

        // <type><id>;
        TypeNode type = TypeNode.parseTypeNode(tokens);
        if (type == null) {
            System.err.println("Failed to parse type");
            return null;
        }

        IDNode id = IDNode.parseIDNode(tokens);
<<<<<<< Updated upstream
        if (id == null) {
            System.err.println("Failed to parse id");
            return null;
        }
=======
        if (tokens.isEmpty()) {
            throw new SyntaxErrorException("No tokens to parse", 0, "VariableDeclarationNode.java");
        }
        Token semicolon = tokens.get(0);
        if(semicolon.getTokenType() != TokenType.SEMICOLON) {
            throw new SyntaxErrorException("Expected semicolon but got: " + semicolon.getToken(), semicolon.getLineNum(), semicolon.getFilename());
        }
        tokens.remove(0);
>>>>>>> Stashed changes

        return new VariableDeclarationNode(type, id);
    }

    @Override
    public String convertToJott() {
        return type.convertToJott() + " " + id.convertToJott();
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
