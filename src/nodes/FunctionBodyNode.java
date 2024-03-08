package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class FunctionBodyNode implements JottTree{
    private ArrayList<VariableDeclarationNode> variableDeclarations;
    private BodyNode body;

    public FunctionBodyNode(ArrayList<VariableDeclarationNode> variableDeclarations, BodyNode body) {
        this.variableDeclarations = variableDeclarations;
        this.body = body;
    }

    public static FunctionBodyNode parseFunctionBodyNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        if (tokens.isEmpty()) {
            throw new SyntaxErrorException("No tokens to parse", 0, "FunctionBodyNode.java");
        }

        ArrayList<VariableDeclarationNode> variableDeclarations = new ArrayList<VariableDeclarationNode>();
        while (TypeNode.validTypes.contains(tokens.get(0).getToken())) {

            VariableDeclarationNode variableDeclaration = VariableDeclarationNode.parseVariableDeclarationNode(tokens);
            variableDeclarations.add(variableDeclaration);

        }

        BodyNode body = BodyNode.parseBodyNode(tokens);

        return new FunctionBodyNode(variableDeclarations, body);
    }


    @Override
    public String convertToJott() {
        String jott = "";
        for (VariableDeclarationNode variableDeclaration : variableDeclarations) {
            jott += variableDeclaration.convertToJott() + "\n";
        }
        jott += body.convertToJott();
        return jott;
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
