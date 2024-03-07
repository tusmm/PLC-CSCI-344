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

        if (tokens.size() == 0) {
            // handle error: no tokens
            return null;
        }
        Token tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.ID_KEYWORD) {
            // handle error: not an id
            throw new SyntaxErrorException("not an id", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        if (!tossToken.getToken().equals("Elseif")) {
            // handle error: not an elseif
            throw new SyntaxErrorException("not an elseif", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop Elseif

        if (tokens.size() == 0) {
            // handle error: missing left bracket
            throw new SyntaxErrorException("missing left bracket", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACKET) {
            // handle error: expected [
                throw new SyntaxErrorException("expected ]", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop [

        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens); // assumes correct
        
        if (tokens.size() == 0) {
            // handle error: missing right bracket
            throw new SyntaxErrorException("missing right bracket", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACKET) {
            // handle error: expected ]
            throw new SyntaxErrorException("expected ]", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop ]

        if (tokens.size() == 0) {
            // handle error: missing left brace
            throw new SyntaxErrorException("missing left brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACE) {
            // handle error: expected {
                throw new SyntaxErrorException("expected {", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop {

        BodyNode body = BodyNode.parseBodyNode(tokens); // assumes correct

        if (tokens.size() == 0) {
            // handle error: missing right brace
            throw new SyntaxErrorException("missing right brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACE) {
            // handle error: expected }
            throw new SyntaxErrorException("expected }", tossToken.getLineNum(), tossToken.getFilename());
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
