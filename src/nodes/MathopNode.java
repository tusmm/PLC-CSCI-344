package nodes;

import java.util.ArrayList;

import provided.*;

public class MathopNode implements JottTree {

    Token token;

    public MathopNode(Token token) {
        this.token = token;
    }

    public static MathopNode parseMathOpNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing mathop";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token mathop = tokens.get(0);

        if (mathop.getTokenType() == TokenType.MATH_OP) {
            tokens.remove(0); // this is 'pop'
            return new MathopNode(mathop);
        }

        throw new SyntaxErrorException("expected mathop but got: " + mathop.getToken(), mathop.getLineNum(),
                mathop.getFilename());
    }

    @Override
    public String convertToJott() {
        return token.getToken();
    }

    @Override
    public String convertToJava(String className) {
        return token.getToken();
    }

    @Override
    public String convertToC() {
        return convertToJott();
    }

    @Override
    public String convertToPython() {
        return convertToJott();
    }

    @Override
    public void validateTree() {
    }

}
