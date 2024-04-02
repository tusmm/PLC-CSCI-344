package nodes;

public class SemanticErrorException extends Exception {
    public SemanticErrorException(String message, int lineNum, String filename) {
        super("Semantic error\n" + message + "\n" + filename + ":" + lineNum);
    }
}