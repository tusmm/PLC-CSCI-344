package nodes;

import provided.JottTree;
import provided.Token;

import java.util.ArrayList;
import java.util.List;

public class BodyNode implements JottTree {

    //< body > -> < body_stmt >*<return_stmt>

    private List<BodyStatementNode> bodyStatementNodes;
    private ReturnStatementNode returnStatementNode;

    public BodyNode(List<BodyStatementNode> bodyStatementNodes, ReturnStatementNode returnStatementNode) {
        this.bodyStatementNodes = bodyStatementNodes;
        this.returnStatementNode = returnStatementNode;
    }

    public static BodyNode parseBodyNode(ArrayList<Token> tokens) {

        Token nextToken = tokens.get(0);
        ArrayList<BodyStatementNode> bodyStatments = new ArrayList<>();

        while(!nextToken.getToken().equals("Return")) {

            bodyStatments.add(BodyStatementNode.parseBodyStatementNode(tokens));

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToPython'");
    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateTree'");
    }
}
