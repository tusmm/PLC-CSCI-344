package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class ParamsTNode implements OperandNode {
    ExpressionNode expressionNode;

    public ParamsTNode(ExpressionNode expressionNode) {
        this.expressionNode = expressionNode;
    }

    public static ParamsTNode parseParamsTNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing parameters list";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token token = tokens.get(0); // get the front of the token
        if (token.getTokenType() == TokenType.COMMA) {
            tokens.remove(0); // take off the comma

            // check if token list is empty
            if (tokens.get(0).getTokenType() == TokenType.EOF) {
                String message = "Reached EOF while parsing parameters list";
                throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
            }

            if (ExpressionNode.parseExpressionNode(tokens) != null) {
                ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens);
                return new ParamsTNode(expressionNode);
            }
            throw new SyntaxErrorException("Invalid ParamsNode", token.getLineNum(), token.getFilename());
        }

        throw new SyntaxErrorException("Invalid ParamsNode", token.getLineNum(), token.getFilename());
    }

    @Override
    public String convertToJott() {
        return "," + expressionNode.convertToJott();
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

    @Override
    public String getType() {
        return expressionNode.getType();
    }

}
