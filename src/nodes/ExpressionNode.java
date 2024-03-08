package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface ExpressionNode extends JottTree {
    public static ExpressionNode parseExpressionNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        if (tokens.size() == 0) {
            throw new SyntaxErrorException("empty list", 0, "ExpressoinNode.java");
        }
        Token nextToken = tokens.get(0);

        if(nextToken.getTokenType() == TokenType.STRING) {
            return StringLiteralNode.parseStringLiteralNode(tokens);
        }
        if (nextToken.getToken().equals("True") || nextToken.getToken().equals("False")) {
            return BoolNode.parseBoolNode(tokens);
        }

        OperandNode opNode1 = OperandNode.parseOperandNode(tokens);
        if (tokens.size() == 0) {
            return opNode1;
        }
        nextToken = tokens.get(0);

        if(nextToken.getTokenType() == TokenType.REL_OP) {
            return ExpressionRelopNode.parseExpressionRelopNode(tokens);
        }

        if(nextToken.getTokenType() == TokenType.MATH_OP) {
            return ExpressionMathopNode.parseExpressionMathopNode(tokens);
        }

        return opNode1;
    }
}
