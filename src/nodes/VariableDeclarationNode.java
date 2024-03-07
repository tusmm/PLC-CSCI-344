package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;

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

        // <type> <id>
        TypeNode type = TypeNode.parseTypeNode(tokens);
        if (type == null) {
            System.err.println("Failed to parse type");
            return null;
        }

        IDNode id = IDNode.parseIDNode(tokens);
        if (id == null) {
            System.err.println("Failed to parse id");
            return null;
        }

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
