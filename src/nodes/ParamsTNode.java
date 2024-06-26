package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ParamsTNode implements JottTree {
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


            ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens);
            return new ParamsTNode(expressionNode);

        }

        throw new SyntaxErrorException("Invalid ParamsNode", token.getLineNum(), token.getFilename());
    }

    @Override
    public String convertToJott() {
        return "," + expressionNode.convertToJott();
    }

    @Override
    public String convertToJava(String className) {
        return expressionNode.convertToJava(className);
    }

    @Override
    public String convertToC() {
        return ", " + expressionNode.convertToC();
    }

    @Override
    public String convertToPython() {
        return "," + expressionNode.convertToPython();
    }

    @Override
    public void validateTree() {
        return; 
    }
}
