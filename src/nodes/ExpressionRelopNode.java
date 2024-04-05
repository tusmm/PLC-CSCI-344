package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

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

    public static ExpressionRelopNode parseExpressionRelopNode(ArrayList<Token> tokens, OperandNode operandNodeLeft)
            throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing relop expression";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
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
    public void validateTree() throws SemanticErrorException {
        if (operandNodeLeft.getType() != operandNodeLeft.getType()) {
            throw new SemanticErrorException((operandNodeLeft.toString() + " and " + operandNodeRight.toString() + " do not have matching types"), relopNode.token.getLineNum(), relopNode.token.getFilename());
        } 
        operandNodeLeft.validateTree();
        operandNodeRight.validateTree(); // these will catch if a function isn't valid,, but now i need to implement the rest grawrg
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Boolean";
    }

}
