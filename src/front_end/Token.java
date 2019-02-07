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
			IF = 7, ELSE = 8, THEN = 9, DO = 10, WHILE = 11, VAR = 12, ARRAY = 13, INTEGER = 14, REAL = 15, BOOLEAN = 16,
			OF = 17, TRUE = 18, FALSE = 19, BEGIN = 20, EOT = 21, COLON = 22, SEMICOLON = 23, ASSIGENMENT = 24,
			PLUS = 25, MINUS = 26, MULTIPLICATION = 27, DIVISION = 28, LESS = 29, MORE = 30, LESS_EQUAL = 31,
			MORE_EQUAL = 32, DIFERENT = 33, DOT = 34, DOUBLE_DOT = 35, LEFT_BRACKET = 36, RIGHT_BRACKET = 37,
			LEFT_PARENTHESIS = 38, RIGHT_PARENTHESIS = 39, EQUALS = 40, COMMA = 41;

	private final static String[] spellings = { "identifier", "int_literal", "float_literal", "and", "or", "program",
			"end", "if", "else", "then", "do", "while", "var", "array", "integer", "real", "boolean", "of", "true",
			"false", "begin", "eot", ":", ";", ":=", "+", "-", "*", "/", "<", ">", "<=", ">=", "<>", ".", "..", "[",
			"]", "(", ")", "=", "," };

	/**
	 * 
	 * Recebe o código do token encontrado e retorna descrição textual
	 * correspondente
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
