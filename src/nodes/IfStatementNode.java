package nodes;

import java.util.ArrayList;

import provided.*;

// this is going to assume a lot of things work bc not everything is on main yet
public class IfStatementNode implements BodyStatementNode {

    //< if_stmt > -> If [ < expr >]{ < body >} < elseif_lst >? < else >

    ExpressionNode expr;
    BodyNode body;
    ArrayList<ElseIfNode> elseif_lst;
    ElseNode elseNode;

    public IfStatementNode(ExpressionNode expr, BodyNode body, ArrayList<ElseIfNode> elseif_lst, ElseNode elseNode) {
        this.expr = expr;
        this.body = body;
        this.elseif_lst = elseif_lst;   // may be empty
        this.elseNode = elseNode;   // may be null
    }

    public static IfStatementNode parseIfStatementNode(ArrayList<Token> tokens) {

        if (tokens.size() == 0) {
            // handle error: no tokens
            return null;
        }
        Token tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.ID_KEYWORD) {
            // handle error: not an id
            return null;
        }
        if (!tossToken.getToken().equals("If")) {
            // handle error: not an if
            return null;
        }
        tokens.remove(0); // pop if

        if (tokens.size() == 0) {
            // handle error: missing left bracket
            return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACKET) {
            // handle error: expected left bracket
            return null;
        }
        tokens.remove(0); // pop [

        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);

        if (tokens.size() == 0) {
            // handle error: missing right brace
            return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACE) {
            // handle error: expected right brace
            return null;
        }
        tokens.remove(0); // pop ]

        if (tokens.size() == 0) {
            // handle error: missing left brace
            return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACE) {
            // handle error: expected left brace
            return null;
        }
        tokens.remove(0); // pop {

        BodyNode body = BodyNode.parseBodyNode(tokens);

        if (tokens.size() == 0) {
            // handle error: missing right brace
            return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACE) {
            // handle error: expected left brace
            return null;
        }
        tokens.remove(0); // pop }

        tossToken = tokens.get(0);
        ArrayList<ElseIfNode> elseIfNodes = new ArrayList<>();

        while ( tossToken.getToken() == "Elseif") { // 

            elseIfNodes.add( ElseIfNode.parseEsElseIfNode(tokens) );
            
            if (tokens.size() == 0) {
                // handle error: ... something
                return null;
            }
            tossToken = tokens.get(0);
        }

        ElseNode elseNode = ElseNode.parseElseNode(tokens);

        return new IfStatementNode(expression, body, elseIfNodes, elseNode); // yeah ??

    }

    @Override
    public String convertToJott() {
        
        String returnString =  "If[" + expr.convertToJott() + "]{" + body.convertToJott() + "}";
        for (int i = 0; i < elseif_lst.size(); i++) {
            returnString += elseif_lst.get(i).convertToJott();
        }
        returnString += elseNode.convertToJott();

        return returnString;
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
