package nodes;

import java.util.ArrayList;

import provided.Token;

public class ParamNode implements OperandNode {
    ExpressionNode expressionNode;
    ParamsTNode paramsT;
    
    public ParamNode() {
        
    }

    public static ParamNode parseParamNode(ArrayList<Token> tokens) {
       if (isEmptyTokensList(tokens)) {
        System.out.println("Handle exception here");
        return null;
       }
       

    }

    private static boolean isEmptyTokensList(ArrayList<Token> tokens) {
        return tokens.size() != 0;
    }

    public String convertToJott() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJott'");
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
