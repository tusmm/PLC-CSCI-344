package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

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

    public static ExpressionMathopNode parseExpressionMathopNode(ArrayList<Token> tokens, OperandNode operandNodeLeft)
            throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing mathop expression";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
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
        return operandNodeLeft.convertToPython() + mathopNode.convertToPython() + operandNodeRight.convertToPython();
    }

    @Override
    public void validateTree() throws SemanticErrorException {

        if (operandNodeLeft.getType() != operandNodeRight.getType()) {
            throw new SemanticErrorException((operandNodeLeft.toString() + " and " + operandNodeRight.toString() + " do not have matching types"), mathopNode.token.getLineNum(), mathopNode.token.getFilename());
        } // types don't match

        // check for divide by 0 ??
        operandNodeLeft.validateTree();
        operandNodeRight.validateTree();

    }

    @Override
    public String getType() throws SemanticErrorException {
        // If the expression is valid both operands should have the same type
        return operandNodeLeft.getType();
    }

}
