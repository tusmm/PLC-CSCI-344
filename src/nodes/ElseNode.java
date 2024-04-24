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

    public ElseNode() {
    }

    public static ElseNode parseElseNode(ArrayList<Token> tokens) throws SyntaxErrorException {

        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing else";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }
        Token tossToken = tokens.get(0);
        if (!tossToken.getToken().equals("Else")) { // no else
            return new ElseNode();
        } // token will definitely be else
        tokens.remove(0); // pop Else

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing left brace
            throw new SyntaxErrorException("Missing left brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACE) {
            // handle error: expected left brace
            throw new SyntaxErrorException("Expected [ but got " + tossToken.getToken(), tossToken.getLineNum(),
                    tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop {

        BodyNode body = BodyNode.parseBodyNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing right brace
            throw new SyntaxErrorException("Missing right brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACE) {
            // handle error: expected right brace
            throw new SyntaxErrorException("Expected ] but got " + tossToken.getToken(), tossToken.getLineNum(),
                    tossToken.getFilename());
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
        if (body == null) {
            return "";
        } else {
            return "else { " + body.convertToJava(className) + " }";
        }
    }

    @Override
    public String convertToC() {
        return (body == null) ? "" : "else {" + body.convertToC() + "}";
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public void validateTree() throws SemanticErrorException {
        body.validateTree();
    }

    public boolean willReturn() {
        return body.willReturn();
    }
}
