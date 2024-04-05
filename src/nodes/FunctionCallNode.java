package nodes;

import java.util.ArrayList;
import java.util.List;

import provided.Token;
import provided.TokenType;

public class FunctionCallNode implements OperandNode, BodyStatementNode {
    IDNode id;
    ParamNode params;
    boolean hasSemiColon;

    public FunctionCallNode(IDNode id, ParamNode params) {
        this.id = id;
        this.params = params;
        this.hasSemiColon = false;
    }

    public void setHasSemiColon(boolean hasSemiColon) {
        this.hasSemiColon = hasSemiColon;
    }

    public static FunctionCallNode parseFunctionCallNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing function call";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        if (tokens.get(0).getTokenType() != TokenType.FC_HEADER) {
            throw new SyntaxErrorException("Expected :: but got: " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        tokens.remove(0); // dequeue the FC_Header
        IDNode id = IDNode.parseIDNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Missing left bracket";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }
        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            throw new SyntaxErrorException("Expected [ but got: " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        tokens.remove(0); // remove left bracket

        ParamNode pm = ParamNode.parseParamNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Missing right bracket";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }
        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            throw new SyntaxErrorException("Expected ] but got: " + tokens.get(0).getToken(),
                    tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        tokens.remove(0); // remove right bracket

        return new FunctionCallNode(id, pm);
    }

    @Override
    public String convertToJott() {
        return "::" + id.convertToJott() + "[" + params.convertToJott() + "]" + (hasSemiColon ? ";" : "");
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
    public void validateTree() throws SemanticErrorException {
        // check if the function exists in the symbol table
        if (!SymbolTable.functionExists(id.toString())) {
            throw new SemanticErrorException("Call to unknown function" + id.toString(), id.token.getLineNum(), id.token.getFilename());
        }

        // validate the function call
        id.validateTree();

        List<String> expectedParamTypes = SymbolTable.getFunctionParamTypes(id.toString()); 
        ArrayList<String> actualParamTypes = new ArrayList<String>();
        
        // append params.expressionNode.gettype to the paramst list 
        actualParamTypes.add(params.expressionNode.getType());
        for (ParamsTNode param : params.paramsTNode) {
            actualParamTypes.add(param.expressionNode.getType());
        }

        if (expectedParamTypes.size() != actualParamTypes.size()) {
            throw new SemanticErrorException("Function call to " + id.toString() + " has wrong number of arguments", id.token.getLineNum(), id.token.getFilename());
        }

        for (int i = 0; i < expectedParamTypes.size(); i++) {
            if (!expectedParamTypes.get(i).equals(actualParamTypes.get(i))) {
                throw new SemanticErrorException("Invalid type being passed into function param: " + id.toString() + "\nExpected " + actualParamTypes.get(i) + ", but got " + expectedParamTypes.get(i), id.token.getLineNum(), id.token.getFilename());
            }
        }
    }

    @Override
    public String getType() {
        return SymbolTable.getReturnType(id.token.getToken());
    }

    @Override
    public boolean willReturn() {
        // Call doesnt return, only returns when part of a return statement
        return false;
    }
}
