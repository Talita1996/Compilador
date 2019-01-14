package front_end;

public class Token {

	public byte kind;
	public String spelling;

	public Token(byte kind, String spelling) {

		this.kind = kind;
		this.spelling = spelling;

		if (kind == IDENTIFIER)
			verificaSeEhPalavraReservada(this.spelling);
	}

	private void verificaSeEhPalavraReservada(String spelling) {
		for (byte k = 0; k < spellings.length; k++) {
			if (spelling.equals(spellings[k]))
				this.kind = k;
		}
	}
	
	/**
	 * Conjuto de tipos de token Mini-Triangle
	 */
	
	public static final byte IDENTIFIER = 0, INT_LITERAL = 1, FLOAT_LITERAL = 2, AND = 3, OR = 4, PROGRAM = 5, END = 6,
			IF = 8, ELSE = 7, THEN = 8, DO = 9, WHILE = 10, VAR = 11, ARRAY = 12, INTEGER = 13, REAL = 14, BOOLEAN = 15,
			OF = 16, TRUE = 17, FALSE = 18, BEGIN = 19, EOT = 20, COLON = 21, SEMICOLON = 22, ASSIGENMENT = 23,
			PLUS = 24, MINUS = 25, MULTIPLICATION = 26, DIVISION = 27, LESS = 28, MORE = 29, LESS_EQUAL = 30,
			MORE_EQUAL = 31, DIFERENT = 32, DOT = 33, DOUBLE_DOT = 34, LEFT_BRACKET = 35, RIGHT_BRACKET = 36,
			LEFT_PARENTHESIS = 37, RIGHT_PARENTHESIS = 38, EQUALS = 39, COMMA = 40;

	private final static String[] spellings = { "identifier", "int_literal", "float_literal", "and", "or", "program",
			"if", "else", "then", "do", "while", "var", "array", "integer", "real", "boolean", "of", "true", "false",
			"begin", "eot", ":", ";", ":=", "+", "-", "*", "/", "<", ">", "<=", ">=", "<>", ".", "..", "[", "]", "(",
			")", "=", "," };

	/**
	 * 
	 * Recebe o código do token encontrado e retorna descrição textual correspondente
	 * 
	 * @param index
	 * @return String com decrição do correspondente ao tipo do token.
	 */
	
	public static String getSpellings(int index) {
		if ((index < spellings.length) && (index >= 0))
			return (spellings[index]);
		return null;
	}

}
