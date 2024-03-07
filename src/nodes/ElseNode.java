package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ElseNode implements JottTree {

    BodyNode body;

    public ElseNode(BodyNode body) {
        this.body = body;
    }

    public ElseNode() {}

    public static ElseNode parseElseNode(ArrayList<Token> tokens) throws SyntaxErrorException {

        if (tokens.size() == 0) {
            // handle error: no tokens
            return null;
        }
        Token tossToken = tokens.get(0);
        if (tossToken.getToken() != "Else") { // no else
            return new ElseNode();
        } // token will definitely be else
        tokens.remove(0); // pop Else

        if (tokens.size() == 0) {
            // handle error: missing left brace
            throw new SyntaxErrorException("missing left brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACE) {
            // handle error: expected left brace
            throw new SyntaxErrorException("expected [", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop {

        BodyNode body = BodyNode.parseBodyNode(tokens);

        if (tokens.size() == 0) {
            // handle error: missing right brace
            throw new SyntaxErrorException("missing right brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACE) {
            // handle error: expected right brace
            throw new SyntaxErrorException("expected ]", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop }

        return new ElseNode(body);

    }

    @Override
    public String convertToJott() {
        
        if (body == null) {
            return "";
        } else {
            return "Else{" + body.convertToJott() + "}";
        }
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
