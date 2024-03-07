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
        }

    }

}
