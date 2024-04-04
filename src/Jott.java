//package src;

import java.util.ArrayList;
import java.util.Arrays;

import provided.JottParser;
import provided.JottTokenizer;
import provided.JottTree;
import provided.Token;

public class Jott {

    private static boolean isValidExec(String[] args) {
        // check for correct number of arguments
        if (args.length != 3) {
            System.out.println("Usage: java Jott <input_file> <output_file> <language>");
            return false;
        }

        // check that the first argument is a .jott file
        if (!args[0].endsWith(".jott")) {
            System.out.println("Input file must be a .jott file");
            return false;
        }
        
        // check that second argument is ends with ("java", "c", "python", "jott")
        ArrayList<String> outputFileTypes = new ArrayList<String>(
            Arrays.asList(".java", ".c", ".python", ".jott")
        );
        
        int lastDotIndex = args[1].lastIndexOf(".");
        // String beforeString = args[1].substring(0, lastDotIndex);    
        String outputFileType = args[1].substring(lastDotIndex);
        boolean isValidOutputFileType = outputFileTypes.contains(outputFileType);
        // output.java.jott, .jott will be detected as the file type
        // checks if the output file is a valid type
        if (!isValidOutputFileType) {
            System.out.println("Output file must be a .java, .c, .py, or .jott file");
            return false;
        }
        
        // make sure the third argument is a valid language
        ArrayList<String> validLanguages = new ArrayList<String>(
            Arrays.asList("Java", "C", "Python", "Jott")
        );

        boolean isValidLanguage = validLanguages.contains(args[2]);

        // jAvA, wont be accepted, unless we want it to, we can add a tolwercase to our check
        // check if valid language input
        if (!isValidLanguage) {
            System.out.println("Language must be Java, C, Python, or Jott");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {        
        // check if the arguments are valid
        if (!isValidExec(args)) {
            System.exit(1);
        }
        // at this point, args should be valid

        // tokenize the input file
        ArrayList<Token> tokens = JottTokenizer.tokenize(args[0]);
        if (tokens == null) {
            System.out.println("Error tokenizing file");
            System.exit(1);
        }
        
        // parse the tokens and create parse tree
        JottTree root = JottParser.parse(tokens);
        // program will handle validation
        if (root == null) {
            System.out.println("Error parsing tokens");
            System.exit(1);
        }

        // add translation code here
        System.out.println("Tree is valid"); 
    }
}
