package nodes;

import java.util.ArrayList;

import provided.Token;
import provided.TokenType;

public class FunctionCallNode extends OperandNode {
    IDNode id;
    ParamNode params;

    public FunctionCallNode(IDNode id, ParamNode params) {
        this.id = id;
        this.params = params;
    }

    public static FunctionCallNode parseFunctionCallNode(ArrayList<Token> tokens) {
        if (isEmptyTokensList(tokens)) {
            System.out.println("Handle exception here");
        }
        if (tokens.get(0).getTokenType() != TokenType.FC_HEADER) {
            System.out.println("Handle error");
        }
        
        tokens.remove(0); // dequeue the FC_Header
        IDNode id = IDNode.parseIDNode(tokens);
        
        if (isEmptyTokensList(tokens)) {
            System.out.println("Handle exception here");
        } 
        if (tokens.get(0).getTokenType() != TokenType.L_BRACKET) {
            System.out.println("Handle error");
        }

        // do you need to pop here? wont the loewr level method call do it?
        ParamNode pm = ParamNode.parseParamNode(tokens);
        
        if (isEmptyTokensList(tokens)) {
            System.out.println("Handle exception here");
        } 
        if (tokens.get(0).getTokenType() != TokenType.R_BRACKET) {
            System.out.println("Handle error");
        }

       // do you need to pop here? wont lower leve method call do it?
       return new FunctionCallNode(id, pm); 
    }

    @Override
    public String convertToJott() {
        return "::" + id.convertToJott() + "[" + params.convertToJott() + "]";
    }

    private static boolean isEmptyTokensList(ArrayList<Token> tokens) {
        return tokens.size() != 0;
    }
}
