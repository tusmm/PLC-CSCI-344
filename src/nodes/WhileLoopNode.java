package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class WhileLoopNode implements BodyStatementNode {

    ExpressionNode expression;
    BodyNode body;

    public WhileLoopNode(ExpressionNode expression, BodyNode body) {
        this.expression = expression;
        this.body = body;
    }

    public static WhileLoopNode parseWhileLoopNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing while";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        // While[<expr>]{<body>}
        Token token = tokens.get(0); // get first token
        if (token.getTokenType() != TokenType.ID_KEYWORD) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected id but got " + unexpected;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        String ID_While = token.getToken();
        if (!ID_While.equals("While")) {
            String message = "Expected id to be While but got " + ID_While;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        tokens.remove(0); // remove while ID

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Missing left bracket";
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.L_BRACKET) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected [ but got " + unexpected;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        tokens.remove(0); // remove l_bracket token

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "While Loop missing expression";
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Missing right bracket";
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.R_BRACKET) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected ] but got " + unexpected;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        tokens.remove(0); // remove r_bracket token

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Missing left brace";
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.L_BRACE) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected { but got " + unexpected;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        tokens.remove(0); // remove l_brace token

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "While Loop Missing Body";
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        BodyNode body = BodyNode.parseBodyNode(tokens);

        if (tokens.size() == 0) {
            String message = "Missing right brace";
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
        }
        token = tokens.get(0);
        if (token.getTokenType() != TokenType.R_BRACE) {
            String unexpected = token.getTokenType().toString().toLowerCase();
            String message = "Expected } but got " + unexpected;
            String filename = token.getFilename();
            int lineNum = token.getLineNum();
            throw new SyntaxErrorException(message, lineNum, filename);
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
