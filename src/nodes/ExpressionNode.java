package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface ExpressionNode extends JottTree {
    public static ExpressionNode parseExpressionNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing expression";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }
        Token nextToken = tokens.get(0);

        if (nextToken.getTokenType() == TokenType.STRING) {
            return StringLiteralNode.parseStringLiteralNode(tokens);
        }
        if (nextToken.getToken().equals("True") || nextToken.getToken().equals("False")) {
            return BoolNode.parseBoolNode(tokens);
        }

        OperandNode opNode1 = OperandNode.parseOperandNode(tokens);
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            return opNode1;
        }
        nextToken = tokens.get(0);

        if (nextToken.getTokenType() == TokenType.REL_OP) {
            return ExpressionRelopNode.parseExpressionRelopNode(tokens, opNode1);
        }

        if (nextToken.getTokenType() == TokenType.MATH_OP) {
            return ExpressionMathopNode.parseExpressionMathopNode(tokens, opNode1);
        }

        return opNode1;
    }
}
