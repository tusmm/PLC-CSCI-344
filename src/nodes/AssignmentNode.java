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
            // handle error
            // missing id
            return null;
        }
        // <id>=<expr>;
        Token token = tokens.get(0);
        IDNode id = IDNode.parseIDNode(tokens);
        if (id == null){
            return null; 
        }

        if (tokens.size() == 0) {
            // handle error 
            System.err.println("Syntax Error:");
            System.err.println("Missing =");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null;
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.ASSIGN) {
            System.err.println("Syntax Error:");
            String unexpected = token.getTokenType().toString().toLowerCase();
            System.err.println("Expected = but got " + unexpected);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null;
        }

        tokens.remove(0); // remove assign token

        if (tokens.size() == 0) {
            System.err.println("Syntax Error:");
            System.err.println("Assignment missing right side expression");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null;
        }
        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);
        if (expression == null){
            return null; 
        }

        if (tokens.size() == 0) {
            System.err.println("Syntax Error:");
            System.err.println("Missing semicolon");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null;
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.SEMICOLON) {
            System.err.println("Syntax Error:");
            String unexpected = token.getTokenType().toString().toLowerCase();
            System.err.println("Expected semicolon but got " + unexpected);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
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