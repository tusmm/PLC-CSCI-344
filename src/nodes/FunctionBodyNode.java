package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class FunctionBodyNode implements JottTree {
    private ArrayList<VariableDeclarationNode> variableDeclarations;
    private BodyNode body;

    public FunctionBodyNode(ArrayList<VariableDeclarationNode> variableDeclarations, BodyNode body) {
        this.variableDeclarations = variableDeclarations;
        this.body = body;
    }

    public static FunctionBodyNode parseFunctionBodyNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing function body";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
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

        String javaString = "";
        for (VariableDeclarationNode variableDeclaration : variableDeclarations) {
            javaString += variableDeclaration.convertToJava(className) + " ";
        }
        javaString += body.convertToJava(className);
        return javaString;
    }

    @Override
    public String convertToC() {
        String c = "";
        for (VariableDeclarationNode variableDeclaration : variableDeclarations) {
            c += variableDeclaration.convertToC() + "\n";
        }
        c += body.convertToC();
        return c;
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public void validateTree() throws SemanticErrorException {
        if (variableDeclarations.size() != 0) {
            for (VariableDeclarationNode varDecNode : variableDeclarations) {
                varDecNode.validateTree();
            }
        }
        
        body.validateTree();
    }

}
