package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class FunctionDefNode implements JottTree {
    private IDNode id;
    private FunctionDefParamsNode functionDefParams;
    private FunctionReturnNode functionReturn;
    private FunctionBodyNode functionBody;
    
    public FunctionDefNode(IDNode id, FunctionDefParamsNode functionDefParams, FunctionReturnNode functionReturn, FunctionBodyNode functionBody) {
        this.id = id;
        this.functionDefParams = functionDefParams;
        this.functionReturn = functionReturn;
        this.functionBody = functionBody;
    }

    public static FunctionDefNode parseFunctionDefNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        if (tokens.isEmpty()) {
            System.err.println("Empty token list");
            return null;
        }

        // Def <id >[ func_def_params ]: < function_return >{ < f_body >}
        if (tokens.get(0).getToken().equals("Def")) {
            tokens.remove(0);
        } else {
            System.err.println("Syntax Error:");
            System.err.println("Expected 'Def' but got " + tokens.get(0).getToken());
            System.err.println(tokens.get(0).getFilename() + ":" + tokens.get(0).getLineNum());
            return null;
        }

        IDNode id = IDNode.parseIDNode(tokens);
        if (id == null) {
            System.err.println("Failed to parse id");
            return null;
        }

        if (tokens.get(0).getTokenType() == TokenType.L_BRACKET) {
            tokens.remove(0);
        } else {
            System.err.println("Syntax Error:");
            System.err.println("Expected '[' but got " + tokens.get(0).getToken());
            System.err.println(tokens.get(0).getFilename() + ":" + tokens.get(0).getLineNum());
            return null;
        }

        FunctionDefParamsNode functionDefParams = FunctionDefParamsNode.parseFunctionDefParamsNode(tokens);
        if (functionDefParams == null) {
            System.err.println("Failed to parse function def params");
            return null;
        }

        if (tokens.get(0).getTokenType() == TokenType.R_BRACKET) {
            tokens.remove(0);
        } else {
            System.err.println("Syntax Error:");
            System.err.println("Expected ']' but got " + tokens.get(0).getToken());
            System.err.println(tokens.get(0).getFilename() + ":" + tokens.get(0).getLineNum());
            return null;
        }

        if (tokens.get(0).getTokenType() == TokenType.COLON) {
            tokens.remove(0);
        } else {
            System.err.println("Syntax Error:");
            System.err.println("Expected ':' but got " + tokens.get(0).getToken());
            System.err.println(tokens.get(0).getFilename() + ":" + tokens.get(0).getLineNum());
            return null;
        }
        
        FunctionReturnNode functionReturn = FunctionReturnNode.parseFunctionReturnNode(tokens);
        if (functionReturn == null) {
            System.err.println("Failed to parse function return");
            return null;
        }

        if (tokens.get(0).getTokenType() == TokenType.L_BRACE) {
            tokens.remove(0);
        } else {
            System.err.println("Syntax Error:");
            System.err.println("Expected '{' but got " + tokens.get(0).getToken());
            System.err.println(tokens.get(0).getFilename() + ":" + tokens.get(0).getLineNum());
            return null;
        }

        FunctionBodyNode functionBody = FunctionBodyNode.parseFunctionBodyNode(tokens);
        if (functionBody == null) {
            System.err.println("Failed to parse function body");
            return null;
        }

        if (tokens.get(0).getTokenType() == TokenType.R_BRACE) {
            tokens.remove(0);
        } else {
            System.err.println("Syntax Error:");
            System.err.println("Expected '}' but got " + tokens.get(0).getToken());
            System.err.println(tokens.get(0).getFilename() + ":" + tokens.get(0).getLineNum());
            return null;
        }

        return new FunctionDefNode(id, functionDefParams, functionReturn, functionBody);
    }


    @Override
    public String convertToJott() {
        return "Def " + id.convertToJott() + "[" + functionDefParams.convertToJott() + "]: " + functionReturn.convertToJott() + " " + functionBody.convertToJott();
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
