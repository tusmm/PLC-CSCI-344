package provided;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

public class JottTokenizer {
    /**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename) {
        ArrayList<Token> tokens = new ArrayList<Token>();

        // buffReader will be closed automatically
        try(BufferedReader buffReader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;

            while ((line = buffReader.readLine()) != null) {
                lineNum++;

                for (int i = 0; i < line.length(); i++) {
                    char tokenChar = line.charAt(i);
                    // Comments (skip this line)
                    if (tokenChar == '#') {
                        break;
                    }

                    // Number
                    if (Character.isDigit(tokenChar) || tokenChar == '.') {
                        String tokenStr = "";
                        boolean fractional = false;

                        // add in case here for .
                        
                        for (; i < line.length(); i++) {
                            tokenChar = line.charAt(i);
                            
                            if (fractional == true && tokenChar == '.') {
                                // Handle errors
                                return null;
                            } else if (tokenChar == '.'){
                                fractional = true;
                            } else if (!Character.isDigit(tokenChar)) {
                                // Don't eat the next character
                                i--;
                                break;
                            }

                            tokenStr += tokenChar;
                        }

                        if (tokenStr.equals(".")) {
                            // error, only a ' . '
                            System.err.println("Syntax Error");
                            System.err.println("Invalid token \".\". \".\" expects the following digit");
                            System.err.println(filename + ":" + lineNum);
                            return null;
                        }

                        tokens.add(new Token(tokenStr, filename, lineNum, TokenType.NUMBER));
                        continue;
                    }

                    // Id/Keyword
                    if (Character.isLetter(tokenChar)) {
                        String tokenStr = "";

                        for (; i < line.length(); i++) {
                            tokenChar = line.charAt(i);

                            if (!Character.isLetter(tokenChar) && !Character.isDigit(tokenChar)) {
                                // Don't eat the next character
                                i--;
                                break;
                            }

                            tokenStr += tokenChar;
                        }

                        tokens.add(new Token(tokenStr, filename, lineNum, TokenType.ID_KEYWORD));
                        continue;
                    }

                    // Assign/Relative Operators
                    if (tokenChar == '=') {
                        if (i + 1 < line.length() && line.charAt(i + 1) == '=') {
                            tokens.add(new Token("==", filename, lineNum, TokenType.REL_OP));
                            i++;
                        } else {
                            tokens.add(new Token("=", filename, lineNum, TokenType.ASSIGN));
                        }

                        continue;
                    } else if (tokenChar == '<' || tokenChar == '>') {
                        if (i + 1 < line.length() && line.charAt(i + 1) == '=') {
                            tokens.add(new Token(tokenChar + "=", filename, lineNum, TokenType.REL_OP));
                            i++;
                        } else {
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.REL_OP));
                        }

                        continue;
                    } else if (tokenChar == '!') {
                        if (i + 1 < line.length() && line.charAt(i + 1) == '=') {
                            tokens.add(new Token("!=", filename, lineNum, TokenType.REL_OP));
                            i++;
                        } else {
                            // Handle errors
                            return null;
                        }

                        continue;
                    }

                    // string
                    if (tokenChar == '\"') {
                        String tokenStr = "\"";
                        i++;
                        for (; i < line.length(); i++) {
                          tokenChar = line.charAt(i);
                          if (tokenChar == '\"') {
                            break; // found the end
                          }else if ( Character.isDigit(tokenChar) || Character.isLetter(tokenChar) || Character.isWhitespace(tokenChar)) {
                            tokenStr += tokenChar;
                          } else {
                            System.err.println("Syntax Error:"); // not reached, but necessary based on DFA
                            System.err.println("Invalid character in string");
                            System.err.println(filename + ":" + lineNum);
                            return null;
                          }
                        }

                        if (tokenChar != '"') {
                            System.err.println("Syntax Error:");
                            System.err.println("Expected closing quote (\")");
                            System.err.println(filename + ":" + lineNum);
                            return null;
                        }

                        tokens.add(new Token(tokenStr + "\"", filename, lineNum, TokenType.STRING));
                        continue;
                    }

                    // :: header
                    if (tokenChar == ':' && i < line.length() - 1 && line.charAt( i + 1 ) == ':') {
                        tokens.add(new Token("::", filename, lineNum, TokenType.FC_HEADER));
                        i++;
                        continue;
                    }


                    // Single Character Tokens
                    switch (tokenChar) {
                        case ',':
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.COMMA));
                            break;
                        case ']':
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.R_BRACKET));
                            break;
                        case '[':
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.L_BRACKET));
                            break;
                        case '{':
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.L_BRACE));
                            break;
                        case '}':
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.R_BRACE));
                            break;
                        case ';':
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.SEMICOLON));
                            break;
                        case ':':
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.COLON));
                            break;
                        case '/':
                        case '*':
                        case '+':
                        case '-':
                            tokens.add(new Token("" + tokenChar, filename, lineNum, TokenType.MATH_OP));
                            break;
                        // Do nothing on whitespace
                        case ' ':
                            break;
                        // Unknown token, not whitespace
                        default:
                            // Handle errors
                            return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tokens;
    }
}