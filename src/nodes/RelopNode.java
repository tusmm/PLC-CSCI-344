package nodes;

import java.util.ArrayList;

import provided.*;

public class RelopNode implements JottTree {

    Token token;

    public RelopNode(Token token) {
        this.token = token;
    }

    public static RelopNode parseRelopNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reach EOF while parsing a relop";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token relop = tokens.get(0);

        if (relop.getTokenType() == TokenType.REL_OP) {
            tokens.remove(0); // this is 'pop'
            return new RelopNode(relop);
        }

        // error, found not a relop
        throw new SyntaxErrorException("expected relop", relop.getLineNum(), relop.getFilename());
    }

    @Override
    public String convertToJott() {
        return token.getToken(); // that's it,,, i think
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
