package compilador;

public class Token {
    
    public byte kind;
    public String spelling;
    
    public Token (byte kind, String spelling) {
        this.kind = kind;
        this.spelling = spelling;
    }
    
    public final static byte
         INT_LIT = 0,
         FLOAT_LIT = 1,
         ID = 2,
         TRUE = 3,
         FALSE = 4,
         BEGIN = 5,
         END = 6,
         IF = 7,
         THEN = 8,
         ELSE = 9,
         VAR = 10,
         WHILE = 11,
         DO = 12,
         PROGRAM = 13,
         ARRAY = 14,
         OF = 15,
         AND = 16,
         OR = 17,
         BECOMES = 18,      // :=
         LPAREN = 19,       // (
         RPAREN = 20,       // )
         COLON = 21,        // :
         SEMICOLON = 22,    // ;
         PLUS = 23,         // +
         LESS = 24,         // -
         MULT = 25,         // *
         DIVI = 26,         // /
         DOT = 27,          // .
         LCOLCH = 28,       // [
         RCOLCH = 29,       // ]
         MAIOR = 30,        // >
         MENOR = 31,        // <
         MAIOR_IG = 32,     // >=
         MENOR_IG = 33,     // <=
         IGUAL = 34,        // =
         DIF = 35;          // <>
}
