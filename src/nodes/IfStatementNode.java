package nodes;

import java.util.ArrayList;

import provided.*;

// this is going to assume a lot of things work bc not everything is on main yet
public class IfStatementNode implements BodyStatementNode {

    // < if_stmt > -> If [ < expr >]{ < body >} < elseif_lst >? < else >

    ExpressionNode expr;
    BodyNode body;
    ArrayList<ElseIfNode> elseif_lst;
    ElseNode elseNode;
    private Boolean willReturnEarly = null;

    public IfStatementNode(ExpressionNode expr, BodyNode body, ArrayList<ElseIfNode> elseif_lst, ElseNode elseNode) {
        this.expr = expr;
        this.body = body;
        this.elseif_lst = elseif_lst; // may be empty
        this.elseNode = elseNode; // may be null
    }

    public static IfStatementNode parseIfStatementNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing if statement";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.ID_KEYWORD) {
            // handle error: not an id
            throw new SyntaxErrorException("Expected id but got " + tossToken.getToken(), tossToken.getLineNum(),
                    tossToken.getFilename());
            // return null;
        }
        if (!tossToken.getToken().equals("If")) {
            throw new SyntaxErrorException("Expected 'If' but got " + tossToken.getToken(), tossToken.getLineNum(),
                    tossToken.getFilename());
            // handle error: not an if
            // return null;
        }
        tokens.remove(0); // pop if

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing left bracket
            throw new SyntaxErrorException("Missing left bracket", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACKET) {
            // handle error: expected left bracket
            throw new SyntaxErrorException("Expected [ but got " + tossToken.getToken(), tossToken.getLineNum(),
                    tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop [

        ExpressionNode expression = ExpressionNode.parseExpressionNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing right brace
            throw new SyntaxErrorException("Missing right brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACKET) {
            // handle error: expected right brace
            throw new SyntaxErrorException("Expected ] but got " + tossToken.getToken(), tossToken.getLineNum(),
                    tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop ]

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing left brace
            throw new SyntaxErrorException("Missing left brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.L_BRACE) {
            // handle error: expected left brace
            throw new SyntaxErrorException("Expected { but got " + tossToken.getToken(), tossToken.getLineNum(),
                    tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop {

        BodyNode body = BodyNode.parseBodyNode(tokens);

        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            // handle error: missing right brace
            throw new SyntaxErrorException("Missing right brace", tossToken.getLineNum(), tossToken.getFilename());
            // return null;
        }
        tossToken = tokens.get(0);
        if (tossToken.getTokenType() != TokenType.R_BRACE) {
            // handle error: expected left brace
            throw new SyntaxErrorException("Expected } but got " + tossToken.getToken(), tossToken.getLineNum(),
                    tossToken.getFilename());
            // return null;
        }
        tokens.remove(0); // pop }

        tossToken = tokens.get(0);
        ArrayList<ElseIfNode> elseIfNodes = new ArrayList<>();

        while (tossToken.getToken().equals("Elseif")) { //

            elseIfNodes.add(ElseIfNode.parseElseIfNode(tokens));

            // check if token list is empty
            if (tokens.get(0).getTokenType() == TokenType.EOF) {
                String message = "Reached EOF while parsing if statement";
                throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
            }

            tossToken = tokens.get(0);
        }

        ElseNode elseNode = ElseNode.parseElseNode(tokens);

        return new IfStatementNode(expression, body, elseIfNodes, elseNode); // yeah ??

    }

    @Override
    public String convertToJott() {

        String returnString = "If[" + expr.convertToJott() + "]{" + body.convertToJott() + "}";
        for (int i = 0; i < elseif_lst.size(); i++) {
            returnString += elseif_lst.get(i).convertToJott();
        }
        returnString += elseNode.convertToJott();

        return returnString;
    }

    @Override
    public String convertToJava(String className) {
        String javaString = "if ( " + expr.convertToJava(className) + " ) { " + body.convertToJava(className) + " } ";
        for (int i = 0; i < elseif_lst.size(); i++) {
            javaString += elseif_lst.get(i).convertToJava(className);
        }
        javaString += elseNode.convertToJott();
        return javaString;
    }

    @Override
    public String convertToC() {
        String c = "if(" + expr.convertToC() + "){" + body.convertToC() + "}";
        for (int i = 0; i < elseif_lst.size(); i++) {
            c += elseif_lst.get(i).convertToC();
        }
        c += elseNode.convertToC();

        return c;
    }

    @Override
    public String convertToPython() {
        String returnString = "if " + expr.convertToPython() + ":\n" + body.convertToPython();
        for (int i = 0; i < elseif_lst.size(); i++) {
            returnString += elseif_lst.get(i).convertToPython();
        }
        returnString += elseNode.convertToPython();

        return returnString;
    }

    @Override
    public void validateTree() throws SemanticErrorException {
        expr.validateTree();
        body.validateTree();

        boolean willReturn = body.willReturn();

        if (elseif_lst.size() != 0) {
            for (ElseIfNode elseif_node : elseif_lst) {
                // check each else if node and make sure it is valid
                elseif_node.validateTree();
                if (!elseif_node.willReturn()) {
                    willReturn = false;
                }
            }
        }
        if (elseNode.body != null) {
            elseNode.validateTree();
            if (!elseNode.willReturn()) {
                willReturn = false;
            }
        }

        willReturnEarly = willReturn;

        return;
    }

    @Override
    public boolean willReturn() {
        if (willReturnEarly == null) {
            System.err.println("Fatal compiler error: IfStatementNode.willReturn() called before validateTree()");
            System.exit(42);
        }

        return willReturnEarly;
    }

}
