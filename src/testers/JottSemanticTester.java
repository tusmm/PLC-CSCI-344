package testers;

/*
  Jott semantic tester. This will test the semantic phase of the Jott
  project.

  This tester assumes a working and valid tokenizer.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import nodes.SemanticErrorException;
import nodes.SyntaxErrorException;
import provided.*;

public class JottSemanticTester {
    ArrayList<TestCase> testCases;

    private static class TestCase{
        String testName;
        String fileName;
        boolean error;

        public TestCase(String testName, String fileName, boolean error) {
            this.testName = testName;
            this.fileName = fileName;
            this.error = error;
        }
    }

    private boolean tokensEqualNoFileData(Token t1, Token t2){
        return t1.getTokenType() == t2.getTokenType() &&
                t1.getToken().equals(t2.getToken());
    }

    private void createTestCases(){
        this.testCases = new ArrayList<>();
        // add phase3 test cases here
        testCases.add(new TestCase("funCallParamInvalid (error)", "funcCallParamInvalid.jott", true ));
        testCases.add(new TestCase("funcNotDefined (error)", "funcNotDefined.jott", true ));
        testCases.add(new TestCase("funcReturnInExpr (error)", "funcReturnInExpr.jott", true ));
        testCases.add(new TestCase("funcWrongParamType (error)", "funcWrongParamType.jott", true ));
        testCases.add(new TestCase("helloWorld", "helloWorld.jott", false ));
        testCases.add(new TestCase("ifStmtReturns", "ifStmtReturns.jott", false ));
        testCases.add(new TestCase("largerValid", "largerValid.jott", false ));
        testCases.add(new TestCase("mainReturnNotInt (error)", "mainReturnNotInt.jott", true ));
        testCases.add(new TestCase("mismatchedReturn (error)", "mismatchedReturn.jott", true ));
        testCases.add(new TestCase("missingFuncParams (error)", "missingFuncParams.jott", true ));
        testCases.add(new TestCase("missingMain (error)", "missingMain.jott", true ));
        testCases.add(new TestCase("missingReturn (error)", "missingReturn.jott", true ));
        testCases.add(new TestCase("noReturnIf (error)", "noReturnIf.jott", true ));
        testCases.add(new TestCase("noReturnWhile (error)", "noReturnWhile.jott", true ));
        testCases.add(new TestCase("providedExample1", "providedExample1.jott", false ));
        testCases.add(new TestCase("returnId (error)", "returnId.jott", true ));
        testCases.add(new TestCase("validLoop", "validLoop.jott", false ));
        testCases.add(new TestCase("voidReturn (error)", "voidReturn.jott", true ));
        testCases.add(new TestCase("whileKeyword (error)", "whileKeyword.jott", true ));
    }

    private boolean semanticTest(TestCase test, String orginalJottCode){
        try {
            ArrayList<Token> tokens = JottTokenizer.tokenize("phase3testcases/" + test.fileName);

            if (tokens == null) {
                System.err.println("\tFailed Test: " + test.testName);
                System.err.println("\t\tExpected a list of tokens, but got null");
                System.err.println("\t\tPlease verify your tokenizer is working properly");
                return false;
            }
            // System.out.println(tokenListString(tokens));
            ArrayList<Token> cpyTokens = new ArrayList<>(tokens);
            JottTree root;
            root = JottParser.parse(tokens);
            // syntax error
            // if (root == null) {
            //     if (!test.error) {
            //         System.err.println("\tFailed Test: " + test.testName);
            //         System.err.println("\t\tExpected no error, but got error: SyntaxErrorException");
            //         return false;
            //     }
            //     return true;
            // }

            try { 
                root.validateTree();
            } catch (SemanticErrorException e) {
                if (!test.error) {
                    System.err.println("\tFailed Test: " + test.testName);
                    System.err.println("\t\tExpected no error, but got error: " + e.getMessage());
                    return false;
                }
                return true;
            }

            // System.out.println("Orginal Jott Code:\n");
            // System.out.println(orginalJottCode);
            // System.out.println();

            // String jottCode = root.convertToJott();
            // System.out.println("Resulting Jott Code:\n");
            // System.out.println(jottCode);

            // try {
            //     FileWriter writer = new FileWriter("phase3testcases/semanticTestTemp.jott");
            //     if (jottCode == null) {
            //         System.err.println("\tFailed Test: " + test.testName);
            //         System.err.println("Expected a program string; got null");
            //         return false;
            //     }
            //     writer.write(jottCode);
            //     writer.close();
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }

            // ArrayList<Token> newTokens = JottTokenizer.tokenize("phase3testcases/semanticTestTemp.jott");

            // if (newTokens == null) {
            //     System.err.println("\tFailed Test: " + test.testName);
            //     System.err.println("Tokenization of files dot not match.");
            //     System.err.println("Similar files should have same tokenization.");
            //     System.err.println("Expected: " + tokenListString(tokens));
            //     System.err.println("Got: null");
            //     return false;
            // }

            // if (newTokens.size() != cpyTokens.size()) {
            //     System.err.println("\tFailed Test: " + test.testName);
            //     System.err.println("Tokenization of files dot not match.");
            //     System.err.println("Similar files should have same tokenization.");
            //     System.err.println("Expected: " + tokenListString(cpyTokens));
            //     System.err.println("Got:    : " + tokenListString(newTokens));
            //     return false;
            // }

            // for (int i = 0; i < newTokens.size(); i++) {
            //     Token n = newTokens.get(i);
            //     Token t = cpyTokens.get(i);

            //     if (!tokensEqualNoFileData(n, t)) {
            //         System.err.println("\tFailed Test: " + test.testName);
            //         System.err.println("Token mismatch: Tokens do not match.");
            //         System.err.println("Similar files should have same tokenization.");
            //         System.err.println("Expected: " + tokenListString(cpyTokens));
            //         System.err.println("Got     : " + tokenListString(newTokens));
            //         return false;
            //     }
            // }
            return true;
        }catch (Exception e){
            System.err.println("\tFailed Test: " + test.testName);
            System.err.println("Unknown Exception occured.");
            e.printStackTrace();
            return false;
        }
    }

    private String tokenListString(ArrayList<Token> tokens){
        StringBuilder sb = new StringBuilder();
        for (Token t: tokens) {
            sb.append(t.getToken());
            sb.append(":");
            sb.append(t.getTokenType().toString());
            sb.append(" ");
        }
        return sb.toString();
    }

    private boolean runTest(TestCase test){
        System.out.println("Running Test: " + test.testName);
        String orginalJottCode;
        try {
            orginalJottCode = new String(
                    Files.readAllBytes(Paths.get("phase3testcases/" + test.fileName)));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return semanticTest(test, orginalJottCode);

    }

    public static void main(String[] args) {
        System.out.println("NOTE: System.err may print at the end. This is fine.");
        JottSemanticTester tester = new JottSemanticTester();

        int numTests = 0;
        int passedTests = 0;
        tester.createTestCases();
        for(JottSemanticTester.TestCase test: tester.testCases){
            numTests++;
            if(tester.runTest(test)){
                passedTests++;
                System.out.println("\tPassed\n");
            }
            else{
                System.out.println("\tFailed\n");
            }
        }

        System.out.printf("Passed: %d/%d%n", passedTests, numTests);
    }
}
