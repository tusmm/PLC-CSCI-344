package nodes;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

import java.util.ArrayList;
import java.util.List;

public class BodyNode implements JottTree {

    // < body > -> < body_stmt >*<return_stmt>

    private List<BodyStatementNode> bodyStatementNodes;
    private ReturnStatementNode returnStatementNode;
    private boolean returnsEarly = false;

    public BodyNode(List<BodyStatementNode> bodyStatementNodes, ReturnStatementNode returnStatementNode) {
        this.bodyStatementNodes = bodyStatementNodes;
        this.returnStatementNode = returnStatementNode;
    }

    public static BodyNode parseBodyNode(ArrayList<Token> tokens) throws SyntaxErrorException {

        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "EOF reached while parsing body";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }
        Token nextToken = tokens.get(0);
        ArrayList<BodyStatementNode> bodyStatments = new ArrayList<>();

        while (!nextToken.getToken().equals("Return")) {

            if (nextToken.getTokenType() == TokenType.R_BRACE) {
                break;
            }

            BodyStatementNode bodyStatement = BodyStatementNode.parseBodyStatementNode(tokens);
            bodyStatments.add(bodyStatement);

            // check if token list is empty
            if (tokens.get(0).getTokenType() == TokenType.EOF) {
                String message = "EOF reached while parsing body";
                throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
            }
            nextToken = tokens.get(0);
        }

        ReturnStatementNode returnStatement = ReturnStatementNode.parseReturnStatementNode(tokens);

        return new BodyNode(bodyStatments, returnStatement);

    }

    @Override
    public String convertToJott() {
        String jott = "";
        for (BodyStatementNode bodyStatement : bodyStatementNodes) {
            jott += bodyStatement.convertToJott();
        }
        jott += returnStatementNode.convertToJott();
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
        String body = "";
        for (BodyStatementNode bodyStatement : bodyStatementNodes) {
            body += bodyStatement.convertToPython() + "\n";
        }
        body += returnStatementNode.convertToPython();
        return body;
    }

    @Override
    public void validateTree() throws SemanticErrorException {
        if (bodyStatementNodes.size() != 0) {
            for (BodyStatementNode bodyStmtNode : bodyStatementNodes) {
                bodyStmtNode.validateTree();
                if (bodyStmtNode.willReturn()) {
                    returnsEarly = true;
                }
            }
        }

        // Ignore if we return early
        if (!returnsEarly) {
            returnStatementNode.validateTree();
        }
    }

    public boolean willReturn() {
        return returnsEarly || !returnStatementNode.isEmpty();
    }
}
