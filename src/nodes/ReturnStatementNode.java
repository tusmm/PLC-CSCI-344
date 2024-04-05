package nodes;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class ReturnStatementNode implements JottTree {
    // < return_stmt > -> Return < expr >; | e

    private ExpressionNode expressionNode;
    private boolean isVoid;
    private int retLineNum;
    private String filename;

    public ReturnStatementNode(ExpressionNode expr, boolean isVoid, int retLineNum, String filename) {
        this.expressionNode = expr;
        this.isVoid = isVoid;
        this.retLineNum = retLineNum;
        this.filename = filename;
    }

    public static ReturnStatementNode parseReturnStatementNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reach EOF while parsing a return statement";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token retr = tokens.get(0);
        if (retr.getTokenType() != TokenType.ID_KEYWORD || !retr.getToken().equals("Return")) {
            return new ReturnStatementNode(null, true, retr.getLineNum(), retr.getFilename());
        }
        tokens.remove(0);

        ExpressionNode expr = ExpressionNode.parseExpressionNode(tokens);

        Token semicolon = tokens.get(0);
        if (semicolon.getTokenType() != TokenType.SEMICOLON) {
            throw new SyntaxErrorException("Expected semicolon but got: " + semicolon.getToken(),
                    semicolon.getLineNum(), semicolon.getFilename());
        }
        tokens.remove(0);

        return new ReturnStatementNode(expr, false, retr.getLineNum(), retr.getFilename());

    }

    @Override
    public String convertToJott() {

        if (isVoid) {
            return "";
        }

        return "Return " + expressionNode.convertToJott() + ";";
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
    public void validateTree() throws SemanticErrorException {
        String expectedReturnType = SymbolTable.getReturnType(SymbolTable.getCurrentScope());

        if (expectedReturnType.equals("Void")) {
            if (isVoid) {
                return;
            } else {
                throw new SemanticErrorException("Expected void return type but got non-void return type", retLineNum, filename);
            }
        }

        // check if the return type of the expression matches the expected return type
        String actualReturnType = expressionNode.getType();

        if (!expectedReturnType.equals(actualReturnType)) {
            throw new SemanticErrorException("Expected return type " + expectedReturnType + " but got " + actualReturnType, retLineNum, filename);
        }
    }

    public boolean isEmpty() {
        return isVoid;
    }
}
