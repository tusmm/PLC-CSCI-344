package nodes;
import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public interface OperandNode extends ExpressionNode {

    public static OperandNode parseOperandNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "No tokens to parse"; 
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
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
            return NumberNode.parseNumberNode(tokens);
        }

        throw new SyntaxErrorException("Unexpected token: " + token.getToken(), token.getLineNum(), token.getFilename());
    }
}