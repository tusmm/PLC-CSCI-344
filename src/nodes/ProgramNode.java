package nodes;

import java.util.ArrayList;

import provided.JottTree;
import provided.Token;
import provided.TokenType;

public class ProgramNode implements JottTree {
    private ArrayList<FunctionDefNode> functionDefs;

    public ProgramNode(ArrayList<FunctionDefNode> functionDefs) {
        this.functionDefs = functionDefs;
    }

    public static ProgramNode parseProgramNode(ArrayList<Token> tokens) throws SyntaxErrorException {
        // check if token list is empty
        if (tokens.get(0).getTokenType() == TokenType.EOF) {
            String message = "Reach EOF while parsing the program";
            throw new SyntaxErrorException(message, tokens.get(0).getLineNum(), tokens.get(0).getFilename());
        }

        ArrayList<FunctionDefNode> functionDefs = new ArrayList<FunctionDefNode>();
        Token token = tokens.get(0);
        while (token.getTokenType() != TokenType.EOF) {
            FunctionDefNode functionDef = FunctionDefNode.parseFunctionDefNode(tokens);
            functionDefs.add(functionDef);
            token = tokens.get(0);
        }

        return new ProgramNode(functionDefs);
    }

    @Override
    public String convertToJott() {
        String jott = "";
        for (FunctionDefNode functionDef : functionDefs) {
            jott += functionDef.convertToJott() + "\n";
        }
        return jott;
    }

    @Override
    public String convertToJava(String className) {

        String java = "public class " + className + " {\n";

        for (FunctionDefNode functionDef : functionDefs) {
            java += "\n" + functionDef.convertToJava(className) + "\n";
        }

        return java + "\n}";
    }

    @Override
    public String convertToC() {
        String c = """
                #include <stdio.h>
                #include <stdlib.h>
                #include <string.h>
                #include <stdbool.h>
                                   
                char* concat(char* s1, char* s2) {
                    char* result = malloc(strlen(s1) + strlen(s2) + 1);
                    strcpy(result, s1);
                    strcat(result, s2);
                    return result;
                }
                
                """;
        for (FunctionDefNode functionDef : functionDefs) {
            c += functionDef.convertToC() + "\n";
        }
        return c;
    }

    @Override
    public String convertToPython() {
        String python = "";
        for (FunctionDefNode functionDef : functionDefs) {
            python += functionDef.convertToPython() + "\n";
        }
        python += "main()";
        return python;
    }

    @Override
    public void validateTree() throws SemanticErrorException {
        for (FunctionDefNode functionDef : functionDefs) {
            functionDef.validateTree();
        }
    }
}
