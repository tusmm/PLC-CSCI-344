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

    public static FunctionDefParamsTypeNode parseFunctionDefParamsTypeNode(ArrayList<Token> tokens) throws SyntaxErrorException {

        if (tokens.isEmpty()) {
            throw new SyntaxErrorException("No tokens left to parse", 0, "");
        }

        Token comma = tokens.get(0);
        if(comma.getTokenType() != TokenType.COMMA) {
            throw new SyntaxErrorException("Expected comma but got: " + comma.getToken(), comma.getLineNum(), comma.getFilename());
        }
        tokens.remove(0);

        IDNode idNode = IDNode.parseIDNode(tokens);

        Token colon = tokens.get(0);
        if(colon.getTokenType() != TokenType.COLON) {
            throw new SyntaxErrorException("Expected colon but got: " + colon.getToken(), colon.getLineNum(), colon.getFilename());
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
