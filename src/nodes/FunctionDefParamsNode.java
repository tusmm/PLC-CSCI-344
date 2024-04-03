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

    public FunctionDefParamsNode(IDNode id, TypeNode type, List<FunctionDefParamsTypeNode> functionDefParamsTypes,
            boolean isEmpty) {
        this.id = id;
        this.type = type;
        this.functionDefParamsTypes = functionDefParamsTypes;
        this.isEmpty = isEmpty;
    }

    public static FunctionDefParamsNode parseFunctionDefParamsNode(ArrayList<Token> tokens)
            throws SyntaxErrorException {

        FunctionDefParamsNode emptyFunctionDefParamsNode = new FunctionDefParamsNode(null, null, null, true);

        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "No tokens to parse";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token nextToken = tokens.get(0);
        if (nextToken.getTokenType() != TokenType.ID_KEYWORD) {
            return emptyFunctionDefParamsNode;
        }

        IDNode idNode = IDNode.parseIDNode(tokens);

        Token colon = tokens.get(0);
        if (colon.getTokenType() != TokenType.COLON) {
            throw new SyntaxErrorException(
                    "Expected colon while parsing function def params but got: " + colon.getToken(), colon.getLineNum(),
                    colon.getFilename());
        }
        tokens.remove(0);

        TypeNode typeNode = TypeNode.parseTypeNode(tokens);

        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing function definition parameters";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }
        ArrayList<FunctionDefParamsTypeNode> functionDefParamsTypeNodes = new ArrayList<>();
        nextToken = tokens.get(0);
        while (nextToken.getTokenType() == TokenType.COMMA) {

            FunctionDefParamsTypeNode functionDefParamsTypeNode = FunctionDefParamsTypeNode
                    .parseFunctionDefParamsTypeNode(tokens);
            functionDefParamsTypeNodes.add(functionDefParamsTypeNode);

            if (tokens.get(0).getTokenType() == TokenType.EOF) {
                break;
            }
            nextToken = tokens.get(0);
        }

        return new FunctionDefParamsNode(idNode, typeNode, functionDefParamsTypeNodes, false);

    }

    @Override
    public String convertToJott() {

        if (isEmpty) {
            return "";
        }

        String jott = id.convertToJott() + ":" + type.convertToJott();

        for (FunctionDefParamsTypeNode functionDefParamsTypeNode : functionDefParamsTypes) {
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

    public List<String> asList() {
        List<String> typeList = new ArrayList<>();
        if(isEmpty) {
            return typeList;
        }
        for(FunctionDefParamsTypeNode t : functionDefParamsTypes) {
            typeList.add(t.type.toString());
        }
        return typeList;
    }
}
