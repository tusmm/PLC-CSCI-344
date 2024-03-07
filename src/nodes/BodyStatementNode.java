package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;

public interface BodyStatementNode extends JottTree {

    public static BodyStatementNode parseBodyStatementNode(ArrayList<Token> tokens) throws SyntaxErrorException {

        if (tokens.size() == 0) {
            String message = "No tokens to parse"; 
            String filename = "BoolNode.java";
            int lineNum = 0; 
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        Token token = tokens.get(0);
        if (IfStatementNode.parseIfStatementNode(tokens) != null) {
            return IfStatementNode.parseIfStatementNode(tokens);
        } else if (WhileLoopNode.parseWhileLoopNode(tokens) != null) {
            return WhileLoopNode.parseWhileLoopNode(tokens);
        } else if (AssignmentNode.parseAssignmentNode(tokens) != null) {
            return AssignmentNode.parseAssignmentNode(tokens);
        } else if (FunctionCallNode.parseFunctionCallNode(tokens) != null) {
            return FunctionCallNode.parseFunctionCallNode(tokens);
        } else {
            String message = "Invalid Body Statement"; 
            String filename = token.getFilename(); 
            int lineNum = token.getLineNum(); 
            throw new SyntaxErrorException(message, lineNum, filename);
        }

    }

}
