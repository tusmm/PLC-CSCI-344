package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public interface OperandNode extends ExpressionNode {

    public static OperandNode parseOperand(ArrayList<Token> tokens) {
        Token token = tokens.get(0);
        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            return IDNode.parseIDNode(tokens);
        }
        // write further code
        return null;
    }
}