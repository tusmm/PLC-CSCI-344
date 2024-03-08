package nodes;

import java.util.ArrayList;

import provided.*;

public class ElseIfNode implements JottTree {

    // these are implemented elsewhere i promise
    private ExpressionNode expr;
    private BodyNode body;

    public ElseIfNode(ExpressionNode expr, BodyNode body) {
        this.expr = expr;
        this.body = body;
    }

    public static ElseIfNode parseElseIfNode(ArrayList<Token> tokens) throws SyntaxErrorException {

        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing else if"; 
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.ID_KEYWORD) {
            // handle error: not an id
            throw new SyntaxErrorException("Expected id but got " + tossToken.getToken(), tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        if (!tossToken.getToken().equals("Elseif")) {
            // handle error: not an elseif
            throw new SyntaxErrorException("Expected Elseif but got " + tossToken.getToken(), tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop Elseif

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing left bracket
            throw new SyntaxErrorException("Missing left bracket", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACKET) {
            // handle error: expected [
                throw new SyntaxErrorException("Expected ] but got " + tossToken.getToken(), tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop [

        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens); // assumes correct
        
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing right bracket
            throw new SyntaxErrorException("Missing right bracket", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACKET) {
            // handle error: expected ]
            throw new SyntaxErrorException("Expected ] but got " + tossToken.getToken(), tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop ]

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing left brace
            throw new SyntaxErrorException("Missing left brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACE) {
            // handle error: expected {
                throw new SyntaxErrorException("Expected { but got " + tossToken.getToken(), tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop {

        BodyNode body = BodyNode.parseBodyNode(tokens); // assumes correct

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing right brace
            throw new SyntaxErrorException("Missing right brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACE) {
            // handle error: expected }
            throw new SyntaxErrorException("Expected } but got " + tossToken.getToken(), tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop }

        return new ElseIfNode(expression, body); // should work fine
    }

    @Override
    public String convertToJott() {
        return "Elseif[" + expr.convertToJott() + "]{" + body.convertToJott() + "}";
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
