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

    public static ExpressionMathopNode parseExpressionMathopNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        if (tokens.size() == 0) {
            throw new SyntaxErrorException("empty list", 0, "ExpressionRelopNode.java");
        }
        OperandNode operandNodeLeft;
        MathopNode mathopNode;
        OperandNode operandNodeRight;
        if (OperandNode.parseOperandNode(tokens) != null) {
            operandNodeLeft = OperandNode.parseOperandNode(tokens);
            if (MathopNode.parseRelopNode(tokens) != null) {
                mathopNode = MathopNode.parseRelopNode(tokens);
                if (OperandNode.parseOperandNode(tokens) != null) {
                    operandNodeRight = OperandNode.parseOperandNode(tokens);
                    return new ExpressionMathopNode(operandNodeLeft, mathopNode, operandNodeRight);
                }
            }
        }
        
        throw new SyntaxErrorException("Invalid parameter node", 0, "expressionmathop.java");
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
