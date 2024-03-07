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
            System.err.println("No tokens left to parse type.");
            return null;
        }

        Token comma = tokens.get(0);
        if(comma.getTokenType() != TokenType.COMMA) {
            System.err.println("Syntax Error:");
            String unexpected = comma.getTokenType().toString().toLowerCase();
            System.err.println("Expected comma but got " + unexpected);
            System.err.println(comma.getFilename() + ":" + comma.getLineNum());
            return null;
        }
        tokens.remove(0);

        IDNode idNode = IDNode.parseIDNode(tokens);
        if(idNode == null) {
            System.err.println("Invalid id while parsing function definition parameters type");
            return null;
        }

        Token colon = tokens.get(0);
        if(colon.getTokenType() != TokenType.COLON) {
            System.err.println("Syntax Error:");
            String unexpected = colon.getTokenType().toString().toLowerCase();
            System.err.println("Expected colon but got " + unexpected);
            System.err.println(colon.getFilename() + ":" + colon.getLineNum());
            return null;
        }
        tokens.remove(0);

        TypeNode typeNode = TypeNode.parseTypeNode(tokens);
        if(typeNode == null) {
            System.err.println("Invalid type while parsing function definition parameters type");
            return null;
        }

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
