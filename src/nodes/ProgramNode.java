package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ProgramNode implements JottTree {
    private ArrayList<FunctionDefNode> functionDefs;

    public ProgramNode(ArrayList<FunctionDefNode> functionDefs) {
        this.functionDefs = functionDefs;
    }

    public static ProgramNode parseProgramNode(ArrayList<Token> tokens) throws SyntaxErrorException, SemanticErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reach EOF while parsing the program";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        ArrayList<FunctionDefNode> functionDefs = new ArrayList<FunctionDefNode>();
        Token token = tokens.get(0);
        while (token.getTokenType() != TokenType.EOF) {
            FunctionDefNode functionDef = FunctionDefNode.parseFunctionDefNode(tokens);
            functionDefs.add(functionDef);
            token = tokens.get(0);
        }

        return new ProgramNode(functionDefs);
    }

    @Override
    public String convertToJott() {
        String jott = "";
        for (FunctionDefNode functionDef : functionDefs) {
            jott += functionDef.convertToJott() + "\n";
        }
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
