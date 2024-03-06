package nodes;

import java.util.ArrayList;

import provided.Token;

public class ExpressionRelopNode implements ExpressionNode {
    OperandNode operandNodeLeft;
    RelopNode relopNode;
    OperandNode operandNodeRight;

    public ExpressionRelopNode(OperandNode operandNodeLeft, 
                            ReplopNode relopNode, 
                            OperandNode operandNodeRight) {
        this.operandNodeLeft = operandNodeLeft;
        this.relopNode = relopNode;
        this.operandNodeRight = operandNodeRight;
    }

    public static ExpressionRelopNode parseExpressionRelopNode(ArrayList<Token> tokens) {
        if (tokens.size() == 0) {
            System.out.println("handle exception here");
            return null;
        }
        OperandNode operandNodeLeft;
        RelopNode relopNode;
        OperandNode operandNodeRight;
        if (OperandNode.parseOperandNode(tokens) != null) {
            operandNodeLeft = OperandNode.parseOperandNode(tokens);
            if (RelopNode.parseRelopNode(tokens) != null) {
                relopNode = RelopNode.parseRelopNode(tokens);
                if (OperandNode.parseOperandNode(tokens) != null) {
                    operandNodeRight = OperandNode.parseOperandNode(tokens);
                    return new ExpressionRelopNode(operandNodeLeft, relopNode, operandNodeRight);
                }
            }
        }
        return null;
    } 

    @Override
    public String convertToJott() {
        return operandNodeLeft.convertToJott() + relopNode.convertToJott() + operandNodeRight.convertToJott();
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
