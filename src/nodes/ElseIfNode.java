package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ElseIfNode implements JottTree {

    // these are implemented elsewhere i promise
    private ExpressionNode expr;
    private BodyNode body;

    public ElseIfNode(ExpressionNode expr, BodyNode body) {
        this.expr = expr;
        this.body = body;
    }

    public static ElseIfNode parseEsElseIfNode(ArrayList<Token> tokens) {

        if (tokens.size() == 0) {
            // handle error: no tokens
            return null;
        }
        Token tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.ID_KEYWORD) {
            // handle error: not an id
            return null;
        }
        if (!tossToken.getToken().equals("Elseif")) {
            // handle error: not an elseif
            return null;
        }
        tokens.remove(0); // pop Elseif

        if (tokens.size() == 0) {
            // handle error: missing left bracket
            return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACKET) {
            // handle error: expected [
            return null;
        }
        tokens.remove(0); // pop [

        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens); // assumes correct
        
        if (tokens.size() == 0) {
            // handle error: missing right bracket
            return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACKET) {
            // handle error: expected ]
            return null;
        }
        tokens.remove(0); // pop ]

        if (tokens.size() == 0) {
            // handle error: missing left brace
            return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACE) {
            // handle error: expected {
            return null;
        }
        tokens.remove(0); // pop {

        BodyNode body = BodyNode.parseBodyNode(tokens); // assumes correct

        if (tokens.size() == 0) {
            // handle error: missing right brace
            return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACE) {
            // handle error: expected }
            return null;
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
