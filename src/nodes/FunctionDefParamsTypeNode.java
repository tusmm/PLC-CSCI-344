package nodes;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;

public class FunctionDefParamsTypeNode implements JottTree {

    // < func_def_params_t > -> ,<id >: < type >

    IDNode id;
    TypeNode type;

    public FunctionDefParamsTypeNode(IDNode id, TypeNode type) {
        this.id = id;
        this.type = type;
    }

    public static FunctionDefParamsTypeNode parseFunctionDefParamsTypeNode(ArrayList<Token> tokens)
            throws SyntaxErrorException {

        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing function definition parameter type";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token comma = tokens.get(0);
        if (comma.getTokenType() != TokenType.COMMA) {
            throw new SyntaxErrorException("Expected comma but got: " + comma.getToken(), comma.getLineNum(),
                    comma.getFilename());
        }
        tokens.remove(0);

        IDNode idNode = IDNode.parseIDNode(tokens);

        Token colon = tokens.get(0);
        if (colon.getTokenType() != TokenType.COLON) {
            throw new SyntaxErrorException("Expected colon but got: " + colon.getToken(), colon.getLineNum(),
                    colon.getFilename());
        }
        tokens.remove(0);

        TypeNode typeNode = TypeNode.parseTypeNode(tokens);

        return new FunctionDefParamsTypeNode(idNode, typeNode);
    }

    @Override
    public String convertToJott() {
        return id.convertToJott() + ":" + type.convertToJott();
    }

    @Override
    public String convertToJava(String className) {
        // TODO Auto-generated method stub
        return type.convertToJava(className) + " " + id.convertToJava(className);
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
    public void validateTree() throws SemanticErrorException {
        if (SymbolTable.variableExistsInScope(id.toString())) {
            throw new SemanticErrorException("Duplicate variable name: " + id.toString(), id.token.getLineNum(), id.token.getFilename());
        }

        SymbolTable.addVariableToScope(id.toString(), type.toString());
    }

}
