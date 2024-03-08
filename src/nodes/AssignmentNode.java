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

    public static AssignmentNode parseAssignmentNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.size() == 0) {
            String message = "No tokens to parse"; 
            String filename = "AssignmentNode.java";
            int lineNum = 0; 
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        // <id>=<expr>;

        IDNode id = IDNode.parseIDNode(tokens);

        if (tokens.size() == 0) {
            String message = "Missing =, out of tokens";
            throw new SyntaxErrorException(message, 0, "AssignmentNode.java");
        }
        Token token = tokens.get(0);
        if (token.getTokenType() != TokenType.ASSIGN) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected = but got " + unexpected; 
            String filename = token.getFilename(); 
            int lineNum = token.getLineNum(); 
            throw new SyntaxErrorException(message, lineNum, filename);
        }

        tokens.remove(0); // remove assign token

        if (tokens.size() == 0) {
            String message = "Assignment missing right side expression"; 
            String filename = token.getFilename(); 
            int lineNum = token.getLineNum(); 
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);

        if (tokens.size() == 0) {
            String message = "Missing semicolon"; 
            String filename = token.getFilename(); 
            int lineNum = token.getLineNum(); 
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.SEMICOLON) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected semicolon but got " + unexpected; 
            String filename = token.getFilename(); 
            int lineNum = token.getLineNum(); 
            throw new SyntaxErrorException(message, lineNum, filename);
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