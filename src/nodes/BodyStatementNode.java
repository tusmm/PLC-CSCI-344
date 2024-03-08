package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface BodyStatementNode extends JottTree {

    public static BodyStatementNode parseBodyStatementNode(ArrayList<Token> tokens) throws SyntaxErrorException {

        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "No tokens to parse"; 
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }
        
        Token token = tokens.get(0);

        if(token.getToken().equals("If")) {
            return IfStatementNode.parseIfStatementNode(tokens);
        }
        if (token.getToken().equals("While")) {
            return WhileLoopNode.parseWhileLoopNode(tokens);
        }
        if(token.getTokenType() == TokenType.FC_HEADER) {

            FunctionCallNode fcn = FunctionCallNode.parseFunctionCallNode(tokens);
            // check if token list is empty
            if (tokens.get(0).getTokenType() == TokenType.EOF) {
                String message = "No tokens to parse"; 
                throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
            }

            Token nextToken = tokens.get(0);
            if (nextToken.getTokenType() != TokenType.SEMICOLON) {
                throw new SyntaxErrorException("Missing semicolon after function call", nextToken.getLineNum(), nextToken.getFilename());
            }

            tokens.remove(0);
            fcn.setHasSemiColon(true);
            return fcn;
        }
        return AssignmentNode.parseAssignmentNode(tokens);

    }

}
