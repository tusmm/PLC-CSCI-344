package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;

public class ProgramNode implements JottTree{
    private ArrayList<FunctionDefNode> functionDefs;

    public ProgramNode(ArrayList<FunctionDefNode> functionDefs) {
        this.functionDefs = functionDefs;
    }
    
    public static ProgramNode parseProgramNode(ArrayList<Token> tokens) {
        if (tokens.isEmpty()) {
            System.err.println("Empty token list");
            return null;
        }

        ArrayList<FunctionDefNode> functionDefs = new ArrayList<FunctionDefNode>();
        while (!tokens.isEmpty()) {
            FunctionDefNode functionDef = FunctionDefNode.parseFunctionDefNode(tokens);
            if (functionDef == null) {
                System.err.println("Failed to parse function def");
                return null;
            }
            functionDefs.add(functionDef);
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
