package nodes;

import java.util.ArrayList;

import provided.*;

public class MathopNode implements JottTree{

    private Token token;

    public MathopNode(Token token) {
        this.token = token;
    }
    
    public static MathopNode parseRelopNode(ArrayList<Token> tokens) {
        if (tokens.size() == 0) {
            return null; // TODO add error
        }
        Token mathop = tokens.get(0);

        if (mathop.getTokenType() == TokenType.REL_OP) {
            tokens.remove(0); // this is 'pop'
            return new MathopNode(mathop);
        }

        System.err.println("Syntax Error:");
        System.err.println("Expected mathop (+, -, *, or /) but got " + mathop.getTokenType().toString().toLowerCase());
        System.err.println(mathop.getFilename() + ":" + mathop.getLineNum());
        return null;
    }


    @Override
    public String convertToJott() {
        return token.getToken();
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
