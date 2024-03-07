package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class ParamNode implements OperandNode {
    ExpressionNode expressionNode;
    ArrayList<ParamsTNode> paramsTNode;

    // constructor if there are parameters
    public ParamNode(ExpressionNode expressionNode, ArrayList<ParamsTNode> paramsTNode) {
        this.expressionNode = expressionNode;
        this.paramsTNode = paramsTNode;
    }

    // empty parameter function call
    public ParamNode() { }

    // < params > -> < expr > < params_t >? | epsilon
    public static ParamNode parseParamNode(ArrayList<Token> tokens) {
        if (isEmptyTokensList(tokens)) {
            System.out.println("Handle exception here");
            return null;
        }
        
        // checks the front token to see if the parameter list is empty,
        // returns an empty constructor if so
        if (tokens.get(0).getTokenType() == TokenType.R_BRACKET) {
            return new ParamNode();
        }

        ExpressionNode expressionNode = ExpressionNode.parseExpressionNode(tokens);

<<<<<<< Updated upstream
            // begin reading in paramTNodes
            ArrayList<ParamsTNode> paramTNodes = new ArrayList<ParamsTNode>();
            Token token = tokens.get(0);
            while (token.getTokenType() != TokenType.R_BRACKET) {
                if (tokens.size() == 0) {
                    System.out.println("Handle error");
                    return null;
                }
                paramTNodes.add(ParamsTNode.parseParamsTNode(tokens));
=======
        // begin reading in paramTNodes
        ArrayList<ParamsTNode> paramTNodes = new ArrayList<ParamsTNode>();
        token = tokens.get(0);
        while (token.getTokenType() != TokenType.R_BRACKET) {
            if (tokens.size() == 0) {
                System.out.println("Handle error");
                return null;
>>>>>>> Stashed changes
            }
            paramTNodes.add(ParamsTNode.parseParamsTNode(tokens));
            token = tokens.get(0);
        }

<<<<<<< Updated upstream
        // problems persist and nothing was returned, return null
        return null;
=======
        return new ParamNode(expressionNode, paramTNodes);

>>>>>>> Stashed changes
    }

    private static boolean isEmptyTokensList(ArrayList<Token> tokens) {
        return tokens.size() == 0;
    }

    @Override
    public String convertToJott() {
        String jottString = "";
        jottString += expressionNode.convertToJott();
        for (ParamsTNode param : paramsTNode) {
            jottString += param.convertToJott();
        }
        return jottString;
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
