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

        if (IfStatementNode.parseIfStatementNode(tokens) != null) {
            return IfStatementNode.parseIfStatementNode(tokens);
        } else if (WhileLoopNode.parseWhileLoopNode(tokens) != null) {
            return WhileLoopNode.parseWhileLoopNode(tokens);
        } else if (AssignmentNode.parseAssignmentNode(tokens) != null) {
            return AssignmentNode.parseAssignmentNode(tokens);
        } else if (FunctionCallNode.parseFunctionCallNode(tokens) != null) {
            return FunctionCallNode.parseFunctionCallNode(tokens);
        } else {
            return null;
        }

    }

}
