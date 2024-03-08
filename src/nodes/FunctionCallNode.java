package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class FunctionCallNode implements OperandNode, BodyStatementNode {
    IDNode id;
    ParamNode params;

    public FunctionCallNode(IDNode id, ParamNode params) {
        this.id = id;
        this.params = params;
    }

    public static FunctionCallNode parseFunctionCallNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        if (isEmptyTokensList(tokens)) {
            throw new SyntaxErrorException("empty list", 0, "FunctionCallNode.java");
        }
        if (tokens.get(0).getTokenType() != TokenType.FC_HEADER) {
            System.out.println("Handle error");
            return null;
        }
        
        tokens.remove(0); // dequeue the FC_Header
        IDNode id = IDNode.parseIDNode(tokens);
        if (id == null) {
            return null;
        }
        
        if (isEmptyTokensList(tokens)) {
            System.out.println("Handle exception here");
            return null;
        } 
        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            System.out.println("Handle error");
            return null;
        }
        
        tokens.remove(0); // remove left bracket

        ParamNode pm = ParamNode.parseParamNode(tokens);
        if (pm == null) {
            return null;
        }
        
        if (isEmptyTokensList(tokens)) {
            System.out.println("Handle exception here");
            return null;
        } 
        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            System.out.println("Handle error");
            return null;
        }
        
        tokens.remove(0); // remove right bracket

       return new FunctionCallNode(id, pm); 
    }

    @Override
    public String convertToJott() {
        return "::" + id.convertToJott() + "[" + params.convertToJott() + "];";
    }

    private static boolean isEmptyTokensList(ArrayList<Token> tokens) {
        return tokens.size() == 0;
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
