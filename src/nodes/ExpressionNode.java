package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;

public interface ExpressionNode extends JottTree {
    public static ExpressionNode parseExpressionNode(ArrayList<Token> tokens) {
        if (tokens.size() == 0) {
            System.out.println("handle error");
            return null;
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
