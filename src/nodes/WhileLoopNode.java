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
        if (tokens.size() == 0){
            // handle error
            // missing while id
            return null;
        }

        // While[<expr>]{<body>}
        Token token = tokens.get(0); // get first token
        if (token.getTokenType() != TokenType.ID_KEYWORD) {
            System.err.println("Syntax Error:");
            String unexpected = token.getTokenType().toString().toLowerCase();
            System.err.println("Expected id but got " + unexpected);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null;
        }
        String ID_While = token.getToken();
        if (!ID_While.equals("While")) {
            System.err.println("Syntax Error:");
            System.err.println("Expected id to be While but got " + ID_While);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        tokens.remove(0); // remove while ID

        if (tokens.size() == 0){
            System.err.println("Syntax Error:");
            System.err.println("Missing left bracket");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.L_BRACKET) {
            System.err.println("Syntax Error:");
            String unexpected = token.getTokenType().toString().toLowerCase();
            System.err.println("Expected [ but got " + unexpected);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        tokens.remove(0); // remove l_bracket token

        if (tokens.size() == 0){
            System.err.println("Syntax Error:");
            System.err.println("While Loop missing expression");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);
        if (expression == null){
            return null; 
        }

        if (tokens.size() == 0){
            System.err.println("Syntax Error:");
            System.err.println("Missing right bracket");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.R_BRACKET) {
            System.err.println("Syntax Error:");
            String unexpected = token.getTokenType().toString().toLowerCase();
            System.err.println("Expected ] but got " + unexpected);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        tokens.remove(0); // remove r_bracket token

        if (tokens.size() == 0){
            System.err.println("Syntax Error:");
            System.err.println("Missing left brace");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        } 
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.L_BRACE) {
            System.err.println("Syntax Error:");
            String unexpected = token.getTokenType().toString().toLowerCase();
            System.err.println("Expected { but got " + unexpected);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        tokens.remove(0); // remove l_brace token

        if (tokens.size() == 0){
            System.err.println("Syntax Error:");
            System.err.println("While Loop missing body");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        } 
        Body body = BodyNode.parseBodyNode(tokens); 
        if (body == null){
            return null; 
        }

        if (tokens.size() == 0){
            System.err.println("Syntax Error:");
            System.err.println("Missing right brace");
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.R_BRACE) {
            System.err.println("Syntax Error:");
            String unexpected = token.getTokenType().toString().toLowerCase();
            System.err.println("Expected } but got " + unexpected);
            System.err.println(token.getFilename() + ":" + token.getLineNum());
            return null; 
        }
        tokens.remove(0); // remove r_brace token

        return new WhileLoopNode(expression, body);
    }

    @Override
    public String convertToJott() {
        return "While[" + expression.convertToJott() + "]{" + body.convertToJott() + "}";
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
