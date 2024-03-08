package nodes;

import java.util.ArrayList;

import provided.Token;

public class ExpressionRelopNode implements ExpressionNode {
    OperandNode operandNodeLeft;
    RelopNode relopNode;
    OperandNode operandNodeRight;

    public ExpressionRelopNode(OperandNode operandNodeLeft, 
                            RelopNode relopNode, 
                            OperandNode operandNodeRight) {
        this.operandNodeLeft = operandNodeLeft;
        this.relopNode = relopNode;
        this.operandNodeRight = operandNodeRight;
    }

    public static ExpressionRelopNode parseExpressionRelopNode(ArrayList<Token> tokens, OperandNode operandNodeLeft) throws SyntaxErrorException {
        if (tokens.size() == 0) {
            throw new SyntaxErrorException("empty list", 0, "ExpressionRelopNode.java");
        }
        RelopNode relopNode = RelopNode.parseRelopNode(tokens);
        OperandNode operandNodeRight = OperandNode.parseOperandNode(tokens);

        return new ExpressionRelopNode(operandNodeLeft, relopNode, operandNodeRight);


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
