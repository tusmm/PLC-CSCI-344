package nodes;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.List;

public class FunctionDefParamsNode implements JottTree {
    // < func_def_params > -> <id >: < type > < function_def_params_t >* | e

    IDNode id;
    TypeNode type;
    List<FunctionDefParamsTypeNode> functionDefParamsTypes;

    boolean isEmpty;

    public FunctionDefParamsNode(IDNode id, TypeNode type, List<FunctionDefParamsTypeNode> functionDefParamsTypes, boolean isEmpty) {
        this.id = id;
        this.type = type;
        this.functionDefParamsTypes = functionDefParamsTypes;
        this.isEmpty = isEmpty;
    }

    public static FunctionDefParamsNode parseFunctionDefParamsNode(ArrayList<Token> tokens) {

        FunctionDefParamsNode emptyFunctionDefParamsNode =
                new FunctionDefParamsNode(null, null, null, true);

        if (tokens.isEmpty()) {
            return emptyFunctionDefParamsNode;
        }

        Token nextToken = tokens.get(0);
        if(nextToken.getTokenType() != TokenType.ID_KEYWORD) {
            return emptyFunctionDefParamsNode;
        }

        IDNode idNode = IDNode.parseIDNode(tokens);
        if(idNode == null) {
            System.err.println("Invalid id while parsing function definition parameters");
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
            System.err.println("Invalid type while parsing function definition parameters");
            return null;
        }

        if(tokens.isEmpty()) {
            System.err.println("No more tokens while parsing definition parameters");
            return null;
        }

        ArrayList<FunctionDefParamsTypeNode> functionDefParamsTypeNodes = new ArrayList<>();
        nextToken = tokens.get(0);
        while(nextToken.getTokenType() == TokenType.COMMA) {

            FunctionDefParamsTypeNode functionDefParamsTypeNode = FunctionDefParamsTypeNode.parseFunctionDefParamsTypeNode(tokens);
            if(functionDefParamsTypeNode == null) {
                System.err.println("Error while parsing function def params type in function definition params");
                return null;
            }

            functionDefParamsTypeNodes.add(functionDefParamsTypeNode);

        }

        return new FunctionDefParamsNode(idNode, typeNode, functionDefParamsTypeNodes, false);

    }

    @Override
    public String convertToJott() {

        if(isEmpty) {
            return "";
        }

        String jott = id.convertToJott() + ":" + type.convertToJott();

        for(FunctionDefParamsTypeNode functionDefParamsTypeNode : functionDefParamsTypes) {
            jott += functionDefParamsTypeNode.convertToJott();
        }

        return jott;
        
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
