package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface BodyStatementNode extends JottTree {

    public static BodyStatementNode parseBodyStatementNode(ArrayList<Token> tokens) throws SyntaxErrorException {

        if (tokens.size() == 0) {
            String message = "No tokens to parse"; 
            String filename = "BoolNode.java";
            int lineNum = 0; 
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        Token token = tokens.get(0);

        if(token.getToken().startsWith("If")) {
            return IfStatementNode.parseIfStatementNode(tokens);
        }
        if (token.getToken().startsWith("While")) {
            return WhileLoopNode.parseWhileLoopNode(tokens);
        }
        if(token.getTokenType() == TokenType.FC_HEADER) {

            FunctionCallNode fcn = FunctionCallNode.parseFunctionCallNode(tokens);
            if (tokens.size() == 0) {
                String message = "No tokens to parse";
                String filename = "BoolNode.java";
                int lineNum = 0;
                throw new SyntaxErrorException(message, lineNum, filename);
            }

            Token nextToken = tokens.get(0);
            if (nextToken.getTokenType() != TokenType.SEMICOLON) {
                throw new SyntaxErrorException("Missing semicolon after function call", nextToken.getLineNum(), nextToken.getFilename());
            }

            tokens.remove(0);
            return fcn;
        }
        return AssignmentNode.parseAssignmentNode(tokens);

    }

}
