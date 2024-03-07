package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public interface BodyStatementNode extends JottTree {

    public static BodyStatementNode parseBodyStatementNode(ArrayList<Token> tokens) {

        if (tokens.size() == 0) {
            return null;
        }
        Token token = tokens.get(0);
<<<<<<< Updated upstream
        if (IfStatementNode.parseIfStatementNode(tokens) != null) {
            return IfStatementNode.parseIfStatementNode(tokens);
        } else if (WhileLoopNode.parseWhileLoopNode(tokens) != null) {
            return WhileLoopNode.parseWhileLoopNode(tokens);
        } else if (AssignmentNode.parseAssignmentNode(tokens) != null) {
            return AssignmentNode.parseAssignmentNode(tokens);
        } else if (FunctionCallNode.parseFunctionCallNode(tokens) != null) {
            return FunctionCallNode.parseFunctionCallNode(tokens);
        } else {
            System.err.println("Syntax Error:");
            System.err.println("Invalid Body Statement");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null;
=======
        //System.out.println(token.getToken());

        if(token.getToken().startsWith("If")) {
            IfStatementNode.parseIfStatementNode(tokens);
        } else if(token.getToken().startsWith("While")) {
            WhileLoopNode.parseWhileLoopNode(tokens);
        } else if(token.getTokenType() == TokenType.FC_HEADER) {
            FunctionCallNode fcn = FunctionCallNode.parseFunctionCallNode(tokens);

            if (tokens.size() == 0) {
                String message = "No tokens to parse";
                String filename = "BoolNode.java";
                int lineNum = 0;
                throw new SyntaxErrorException(message, lineNum, filename);
            }

            Token nextToken = tokens.get(0);
            if(nextToken.getTokenType() != TokenType.SEMICOLON) {
                throw new SyntaxErrorException("Missing semicolon after function call", nextToken.getLineNum(), nextToken.getFilename());
            }

            tokens.remove(0);
            return fcn;

>>>>>>> Stashed changes
        }

        return AssignmentNode.parseAssignmentNode(tokens);


    }

}
