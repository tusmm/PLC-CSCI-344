package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ParamNode implements JottTree {
    ExpressionNode expressionNode;
    ArrayList<ParamsTNode> paramsTNode;

    // constructor if there are parameters
    public ParamNode(ExpressionNode expressionNode, ArrayList<ParamsTNode> paramsTNode) {
        this.expressionNode = expressionNode;
        this.paramsTNode = paramsTNode;
    }

    // empty parameter function call
    public ParamNode() {
    }

    // < params > -> < expr > < params_t >? | epsilon
    public static ParamNode parseParamNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing parameter";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        // checks the front token to see if the parameter list is empty,
        // returns an empty constructor if so
        Token token = tokens.get(0);
        if (token.getTokenType() == TokenType.R_BRACKET) {
            return new ParamNode();
        }

        ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens);

        // begin reading in paramTNodes
        ArrayList<ParamsTNode> paramTNodes = new ArrayList<ParamsTNode>();
        token = tokens.get(0);
        while (token.getTokenType() != TokenType.R_BRACKET) {
            // check if token list is empty
            if (tokens.get(0).getTokenType() == TokenType.EOF) {
                String message = "Expected params, got EOF";
                throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
            }
            paramTNodes.add(ParamsTNode.parseParamsTNode(tokens));
            token = tokens.get(0);
        }

        return new ParamNode(expressionNode, paramTNodes);

    }

    @Override
    public String convertToJott() {
        String jottString = "";
        if (expressionNode != null) {
            jottString += expressionNode.convertToJott();
        }
        if (paramsTNode != null && !paramsTNode.isEmpty()) {
            for (ParamsTNode param : paramsTNode) {
                jottString += param.convertToJott();
            }
        }
        return jottString;
    }

    @Override
    public String convertToJava(String className) {
        String javaString = "";
        if (expressionNode != null) {
            javaString += expressionNode.convertToJava(className);
        }
        for (ParamsTNode param: paramsTNode) {
            javaString += ", " + param.convertToJava(className);
        }

        return javaString;
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'convertToJava'");
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
    public void validateTree() {
        return;
    }
}
