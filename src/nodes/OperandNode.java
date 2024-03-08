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
        }
        if (token.getTokenType() == TokenType.NUMBER) {
            return NumberNode.parseNumberNode(tokens);
        }
        if (token.getTokenType() == TokenType.FC_HEADER) {
            return FunctionCallNode.parseFunctionCallNode(tokens);
        }
        if(token.getToken().startsWith("-")) {
            tokens.remove(0);
            return NumberNode.parseNumberNode(tokens, true);
        }

        throw new SyntaxErrorException("Unexpected token: " + token.getToken(), token.getLineNum(), token.getFilename());
    }
}