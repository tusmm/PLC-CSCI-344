package nodes;

import java.util.ArrayList;

import provided.Token;

public class ExpressionMathopNode implements ExpressionNode {
    OperandNode operandNodeLeft;
    MathopNode mathopNode;
    OperandNode operandNodeRight;
     
    public ExpressionMathopNode(OperandNode operandNodeLeft, 
                            MathopNode mathopNode, 
                            OperandNode operandNodeRight) {
        this.operandNodeLeft = operandNodeLeft;
        this.mathopNode = mathopNode;
        this.operandNodeRight = operandNodeRight;
    }

    public static ExpressionMathopNode parseExpressionMathopNode(ArrayList<Token> tokens, OperandNode operandNodeLeft) throws SyntaxErrorException {
        if (tokens.size() == 0) {
            throw new SyntaxErrorException("empty list", 0, "ExpressionRelopNode.java");
        }

        MathopNode mathopNode = MathopNode.parseMathOpNode(tokens);
        OperandNode operandNodeRight = OperandNode.parseOperandNode(tokens);

        return new ExpressionMathopNode(operandNodeLeft, mathopNode, operandNodeRight);

    } 

    @Override
    public String convertToJott() {
        return operandNodeLeft.convertToJott() + mathopNode.convertToJott() + operandNodeRight.convertToJott(); 
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
