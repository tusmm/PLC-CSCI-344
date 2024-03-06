package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class ParamsTNode implements OperandNode {
    ExpressionNode expressionNode;

    public ParamsTNode(ExpressionNode expressionNode) {
        this.expressionNode = expressionNode;
    }

    public static ParamsTNode parseParamsTNode(ArrayList<Token> tokens) {
        // first check if the token list is empty
        if (tokens.size() == 0) {
            System.out.println("handle exception");
            return null;
        }

        Token token = tokens.get(0); // get the front of the token
        if (token.getTokenType() == TokenType.COMMA) {
            tokens.remove(0); // take off the comma
            
            if (tokens.size() == 0) {
                System.out.println("handle exception");
                return null;
            }

            if (ExpressionNode.parseExpressionNode(tokens) != null) {
                ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens);
                return new ParamsTNode(expressionNode);
            }
            return null;
        }
        return null;
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToJott'");
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
