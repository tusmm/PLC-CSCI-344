package nodes;

import java.util.ArrayList;

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
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }
}
