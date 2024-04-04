 package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class VariableDeclarationNode implements JottTree {
    private TypeNode type;
    private IDNode id;

    public VariableDeclarationNode(TypeNode type, IDNode id) throws SemanticErrorException {
        this.type = type;
        this.id = id;
    }

    public static VariableDeclarationNode parseVariableDeclarationNode(ArrayList<Token> tokens)
            throws SyntaxErrorException, SemanticErrorException {
        // check if token list is empty// check if the function exists in the symbol table
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reached EOF while parsing a variable dec";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        // <type> <id>
        TypeNode type = TypeNode.parseTypeNode(tokens);
        IDNode id = IDNode.parseIDNode(tokens);

        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Expected semicolon but got EOF";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        Token semicolon = tokens.get(0);
        if (semicolon.getTokenType() != TokenType.SEMICOLON) {
            throw new SyntaxErrorException("Expected semicolon but got: " + semicolon.getToken(),
                    semicolon.getLineNum(), semicolon.getFilename());
        }
        tokens.remove(0);

        return new VariableDeclarationNode(type, id);
    }

    @Override
    public String convertToJott() {
        return type.convertToJott() + " " + id.convertToJott() + ";";
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
    public boolean validateTree() throws SemanticErrorException {
        if(SymbolTable.variableExistsInScope(id.toString())) {
            throw new SemanticErrorException("Duplicate variable name: " + id.toString(), id.token.getLineNum(), id.token.getFilename());
        }

        SymbolTable.addVariableToScope(id.toString(), type.toString());
        
        return true; 
    }
}
