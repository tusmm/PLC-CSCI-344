package nodes;

import java.util.ArrayList;

import provided.*;

public class RelopNode implements JottTree {

    private Token token;

    public RelopNode(Token token) {
        this.token = token;
    }

    public static RelopNode parseRelopNode(ArrayList<Token> tokens) throws Exception {
        if (tokens.size() == 0) {
            return null; // TODO add error
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
        return token.getToken();    // that's it,,, i think
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
