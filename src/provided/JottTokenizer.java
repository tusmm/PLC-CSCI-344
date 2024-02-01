package provided;

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author 
 **/

import java.util.ArrayList;
import java.io.*;
import java.lang.Character;

public class JottTokenizer {

	/**
     * Takes in a filename and tokenizes that file into Tokens
     * based on the rules of the Jott Language
     * @param filename the name of the file to tokenize; can be relative or absolute path
     * @return an ArrayList of Jott Tokens
     */
    public static ArrayList<Token> tokenize(String filename){
      ArrayList<Token> tokens = new ArrayList<Token>(); 
      int lineNum = 1; // iterate for every new line

      try {
        FileReader fileReader 
            = new FileReader( 
                filename);
  
        // Convert fileReader to 
        // bufferedReader 
        BufferedReader buffReader 
            = new BufferedReader( 
                fileReader); 
        
        char ch;
        while (buffReader.ready()) { 
          // Read and print characters one by one 
          // by converting into character 
          ch = (char)buffReader.read(); 
          // NEWLINE
          if (ch == '\n') {
            lineNum++;
            continue;
          } else if (ch == ' ') { // white space
            continue;
          } else if (ch == '#') { // comments
            while(ch != '\n'){
              ch = (char)buffReader.read(); 
            }
            lineNum++; 
            continue; 
          } else if (ch == ','){
            String commaString = "" + ch; 
            Token commaToken = new Token(commaString, filename, lineNum, TokenType.COMMA);
            tokens.add(commaToken);
          } else if (ch == ']'){
            String rbracketString = "" + ch; 
            Token rbracketToken = new Token(rbracketString, filename, lineNum, TokenType.R_BRACKET);
            tokens.add(rbracketToken);
          } else if (ch == '['){
            String lbracketString = "" + ch; 
            Token lbracketToken = new Token(lbracketString, filename, lineNum, TokenType.L_BRACKET);
            tokens.add(lbracketToken);
          } else if (ch == '{'){
            String rbraceString = "" + ch; 
            Token rbraceToken = new Token(rbraceString, filename, lineNum, TokenType.R_BRACE);
            tokens.add(rbraceToken);
          } else if (ch == '}'){
            String lbraceString = "" + ch; 
            Token lbraceToken = new Token(lbraceString, filename, lineNum, TokenType.L_BRACE);
            tokens.add(lbraceToken);

            // COMMENT
          } else if (ch == '#') {
            
            
            // NUMBER
          } else if (Character.isDigit(ch) || ch == '.') {
            // needs to return an error when a character starts with a '.' but doesn't have
            // a digit or whitespace after

            // if a digit is found, add to token string and do a while
            String token = "" + ch;
            
            // peek  in front, if it's not good, it will exit this loop and then 
            // be unread so that the character is in the front again. this is so
            // the character is not lost
            PushbackReader pr = new PushbackReader(buffReader);
            char peek = (char)pr.read();
            
            if (ch == '.' && !Character.isDigit(peek)) {
                // error has occured due to the letter after a '.' not being a digit
                System.err.println("Syntax Error:\nInvalid token \"" + token + "\"\n" + filename + ":" + lineNum);
                // tokens.replaceAll(null); // an error has occured, return null?
                break;
              }

            while (Character.isDigit(peek) || peek == '.') {
              // this line might be sus, might drop a character
              // ch = (char)buffReader.read();
              // if end of token is '.'
              if (token.charAt(token.length() - 1) == '.' && peek != ' ' && !Character.isDigit(peek)) {
                // error has occured due to being stuck in the loop
                System.err.println("Syntax Error:\nInvalid token \"" + token + "\"\n" + filename + ":" + lineNum);
              }

              token += peek;
              peek = (char)pr.read();

              if (peek == '.' && ch == '.') {
                break;
              } 
              
            } 

            pr.unread((int)peek);
            
            Token numberToken = new Token(token, filename, lineNum, TokenType.NUMBER);
            tokens.add(numberToken);
          }
          
          // System.out.println("Char :"
          //                   + ch);
        }

        buffReader.close();

      } catch (IOException e) {
        e.printStackTrace();
      }

		return tokens;
	}
}