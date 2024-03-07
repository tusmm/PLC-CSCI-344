package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;

public interface ExpressionNode extends JottTree {
    public static ExpressionNode parseExpressionNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        if (tokens.size() == 0) {
            throw new SyntaxErrorException("empty list", 0, "ExpressoinNode.java");
        }
        if (ExpressionRelopNode.parseExpressionRelopNode(tokens) != null) {
            return ExpressionRelopNode.parseExpressionRelopNode(tokens);
        }
        else if (ExpressionMathopNode.parseExpressionMathopNode(tokens) != null) {
            return ExpressionMathopNode.parseExpressionMathopNode(tokens);
        }
        else if (OperandNode.parseOperandNode(tokens) != null) {
            return OperandNode.parseOperandNode(tokens);
        } else if (BoolNode.parseBoolNode(tokens) != null) {
            return BoolNode.parseBoolNode(tokens); 
        } else if (StringLiteralNode.parseStringLiteralNode(tokens) != null) {
            return StringLiteralNode.parseStringLiteralNode(tokens);
        }

        return null;
    }
}
