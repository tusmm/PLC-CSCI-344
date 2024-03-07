package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public interface OperandNode extends ExpressionNode {

    public static OperandNode parseOperandNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        if (tokens.size() == 0) {
            System.out.println("Handle error");
            return null;
        }

        Token token = tokens.get(0);
        if (token.getTokenType() == TokenType.ID_KEYWORD) {
            return IDNode.parseIDNode(tokens);
        } else if (token.getTokenType() == TokenType.NUMBER) {
            return NumberNode.parseNumberNode(tokens);
        } else if (token.getTokenType() == TokenType.FC_HEADER) {
            return FunctionCallNode.parseFunctionCallNode(tokens);
        } else {
            System.out.println("Handle error here");
            return null;
        }
    }
}