package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class AssignmentNode implements BodyStatementNode {

    IDNode id;
    ExpressionNode expression;

    public AssignmentNode(IDNode id, ExpressionNode expression) {
        this.id = id;
        this.expression = expression;
    }

    public static AssignmentNode parseAssignmentNode(ArrayList<Token> tokens) {
        // check if token list is empty
        if (tokens.size() == 0) {
            return null;
        }
        // <id>=<expr>;
        IDNode id = IDNode.parseIDNode(tokens);

        if (tokens.size() == 0) {
            return null;
        }
        if (tokens.get(0).getTokenType() != TokenType.ASSIGN) {
            return null;
        }

        tokens.remove(0); // remove assign token

        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);

        if (tokens.size() == 0) {
            return null;
        }
        if (tokens.get(0).getTokenType() != TokenType.SEMICOLON) {
            return null;
        }

        tokens.remove(0); // remove semicolon token

        return new AssignmentNode(id, expression);
    }

    @Override
    public String convertToJott() {
        return id.convertToJott() + "=" + expression.convertToJott() + ";";
    }

    @Override
    public String convertToJava(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJava'");
    }

    @Override
    public String convertToC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToC'");
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }
}