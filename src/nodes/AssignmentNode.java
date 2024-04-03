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
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing an assignment";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }
        // <id>=<expr>;
        Token token = tokens.get(0);
        IDNode id = IDNode.parseIDNode(tokens);

        if (tokens.size() == 0) {
            String message = "Missing =";
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.ASSIGN) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected = but got " + unexpected;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }

        tokens.remove(0); // remove assign token

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Assignment missing right side expression";
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
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
    public boolean validateTree() throws SemanticErrorException {
        
        if(SymbolTable.variableExistsInScope(id.toString())) {
            throw new SemanticErrorException("Uniti: " + id.toString(), id.token.getLineNum(), id.token.getFilename());
        }

        SymbolTable.addVariableToScope(id.toString(), expression.toString());
        
        return true; 
    }
}