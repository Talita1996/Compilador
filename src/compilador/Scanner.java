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
       currentChar = //nest source character
    } 
    private boolean isDigit (char c){
        
    }
    
    private boolean isLetter (char c){
        
    }
    
    private boolean isGraphic(char c){
        
    }
    
    private byte scanToken (){
        
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
