package compilador;

public class Scanner {
    private char currentChar = // first source character;
    private byte currentKind;
    private StringBuffer currentSpelling;
    
    private void take (char expectedChar) {
        if (currentChar == expectedChar) {
            currentSpelling.append(currentChar);
            currentChar = //next source character
        } else
            //report a lexical error
    }
    
    private void takeIt () {
       currentSpelling.append(currentChar);
       currentChar = //next source character
    } 
    
    private boolean isDigit (char c){
        
    }
    
    private boolean isLetter (char c){
        
    }
    
    private boolean isGraphic(char c){
        
    }
    
    private byte scanToken () {
        switch (currentChar) {
            case 'a' : case 'b' : case 'c' : 
            case 'd' : case 'e' : case 'f' : 
            case 'g' : case 'h' : case 'i' : 
            case 'j' : case 'k' : case 'l' : 
            case 'm' : case 'n' : case 'o' :
            case 'p' : case 'q' : case 'r' : 
            case 's' : case 't' : case 'u' : 
            case 'v' : case 'w' : case 'x' : 
            case 'y' : case 'z':
                takeIt();
                while (isLetter(currentChar) || isDigit(currentChar))
                    takeIt();
                return Token.ID;
                
            case '0' : case '1'  : case '2' : 
            case '3' : case '4' : case '5' : 
            case '6' : case '7' : case '8' : 
            case '9':
                takeIt();
                while (isDigit(currentChar)) {
                    takeIt();
                    if (currentChar == ',')
                        //Definir o FLOAT_LIT
                }
                return Token.INT_LIT;
            
            case ':=' :
                takeIt();
                return Token.BECOMES;
                
            case '(' :
                takeIt();
                return Token.LPAREN;
                
            case ')' :
                takeIt();
                return Token.RPAREN;
                
            case ':' :
                takeIt();
                return Token.COLON;
                
            case ';' :
                takeIt();
                return Token.SEMICOLON;
                
            case '+' :
                takeIt();
                return Token.PLUS;
            
            case '-' :
                takeIt();
                return Token.LESS;
                
            case '*' :
                takeIt();
                return Token.MULT;
                
            case '/' :
                takeIt();
                return Token.DIV;
               
            case '.' :
                takeIt();
                return Token.DOT;
                
            case '[' :
                takeIt();
                return Token.LCOLCH;
                
            case ']' :
                takeIt();
                return Token.RCOLCH;
                
            case '>' :
                takeIt();
                return Token.MAIOR;
                
            case '<' :
                takeIt();
                return Token.MENOR;
                
            case '>=' :
                takeIt();
                return Token.MAIOR_IG;
                
            case '<=' :
                takeIt();
                return Token.MENOR_IG;
                
            case '=' :
                takeIt();
                return Token.IGUAL;
                
            case '<>' :
                takeIt();
                return Token.DIF;
        }
    }
    
    private void scanSeparator (){
        
    }
    
    public Token scan () {
        while (currentChar == '!' || currentChar == ' ' || currentChar == '\n')
            scanSeparator();
        currentSpelling = new StringBuffer("");
        currentKind = scanToken();
        return new Token (currentKind, currentSpelling.toString());
    }
}
