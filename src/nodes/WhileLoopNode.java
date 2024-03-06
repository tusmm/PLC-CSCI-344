package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class WhileLoopNode implements BodyStatementNode{

    ExpressionNode expression;
    BodyNode body; 

    public WhileLoopNode(ExpressionNode expression, BodyNode body) {
        this.expression = expression;
        this.body = body; 
    }

    public static WhileLoopNode parseWhileLoopNode(ArrayList<Token> tokens) {
        // check if token list is empty
        if (tokens.size() == 0) return null;

        // While[<expr>]{<body>}
        Token token = tokens.get(0); // get first token
        if (token.getTokenType() != TokenType.ID_KEYWORD) {
            
            return null;
        }
        String ID_While = token.getToken();
        if (!ID_While.equals("While")) {
            return null; 
        }
        tokens.remove(0); // remove while ID

        if (tokens.size() == 0) return null; 
        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            return null; 
        }
        tokens.remove(0); // remove l_bracket token

        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);
        if (expression == null){
            return null; 
        }

        if (tokens.size() == 0) return null; 
        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            return null; 
        }
        tokens.remove(0); // remove r_bracket token

        if (tokens.size() == 0) return null; 
        if (tokens.get(0).getTokenType() != TokenType.L_BRACE) {
            return null; 
        }
        tokens.remove(0); // remove l_brace token

        Body body = BodyNode.parseBodyNode(tokens); 
        if (body == null){
            return null; 
        }

        if (tokens.size() == 0) return null; 
        if (tokens.get(0).getTokenType() != TokenType.R_BRACE) {
            return null; 
        }
        tokens.remove(0); // remove r_brace token

        return new WhileLoopNode(expression, body);
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
