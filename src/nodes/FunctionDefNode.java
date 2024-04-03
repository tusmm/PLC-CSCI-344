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

    public FunctionDefNode(IDNode id, FunctionDefParamsNode functionDefParams, FunctionReturnNode functionReturn,
            FunctionBodyNode functionBody) {
        this.id = id;
        this.functionDefParams = functionDefParams;
        this.functionReturn = functionReturn;
        this.functionBody = functionBody;

    }

    public static FunctionDefNode parseFunctionDefNode(ArrayList<Token> tokens) throws SyntaxErrorException, SemanticErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing function definition";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        // Def <id >[ func_def_params ]: < function_return >{ < f_body >}
        if (tokens.get(0).getToken().equals("Def")) {
            tokens.remove(0);
        } else {
            throw new SyntaxErrorException("Expected 'Def' but got " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        IDNode id = IDNode.parseIDNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.L_BRACKET) {
            tokens.remove(0);
        } else {
            throw new SyntaxErrorException("Expected '[' but got " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        FunctionDefParamsNode functionDefParams = FunctionDefParamsNode.parseFunctionDefParamsNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.R_BRACKET) {
            tokens.remove(0);
        } else {
            throw new SyntaxErrorException("Expected ']' but got " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        if (tokens.get(0).getTokenType() == TokenType.COLON) {
            tokens.remove(0);
        } else {
            throw new SyntaxErrorException("Expected ':' but got " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        FunctionReturnNode functionReturn = FunctionReturnNode.parseFunctionReturnNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.L_BRACE) {
            tokens.remove(0);
        } else {
            throw new SyntaxErrorException("Expected '{' but got " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        // add function to symbol table and set scope
        if(!SymbolTable.addFunction(id.toString(), functionDefParams.asList(), functionReturn.toString())) {
            throw new SemanticErrorException("Duplicate function name: " + id.toString(), id.token.getLineNum(), id.token.getFilename());
        }
        SymbolTable.setCurrentScope(id.toString());

        if(!functionDefParams.isEmpty) {
            // add function params to function scope
            for (FunctionDefParamsTypeNode n : functionDefParams.functionDefParamsTypes) {
                String varName = n.id.toString();
                String varType = n.type.toString();

                if (SymbolTable.variableExistsInScope(varName)) {
                    throw new SemanticErrorException("Duplicate variable name: " + n.id.toString(), n.id.token.getLineNum(), n.id.token.getFilename());
                }

                SymbolTable.addVariableToScope(varName, varType);
            }
        }

        FunctionBodyNode functionBody = FunctionBodyNode.parseFunctionBodyNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.R_BRACE) {
            tokens.remove(0);
        } else {
            throw new SyntaxErrorException("Expected '}' but got " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        return new FunctionDefNode(id, functionDefParams, functionReturn, functionBody);
    }

    @Override
    public String convertToJott() {
        return "Def " + id.convertToJott() + "[" + functionDefParams.convertToJott() + "]:"
                + functionReturn.convertToJott() + "{" + functionBody.convertToJott() + "}";
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
