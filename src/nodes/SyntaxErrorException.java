package nodes;

public class SyntaxErrorException extends Exception {
    public SyntaxErrorException(String message, int lineNum, String filename) {
        super("Syntax error\n" + message + "\n" + filename + ":" + lineNum);
    }
}
