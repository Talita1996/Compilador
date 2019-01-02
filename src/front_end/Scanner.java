package front_end;

import java.util.ArrayList;

public class Scanner {

	private char currentChar;
	private byte currentKind;
	private StringBuffer currentSpelling;

	private ArrayList<Character> codigoFonte;
	private Integer posicaoDeLeitura;

	private void take(char expectedChar) {
		if (currentChar == expectedChar) {
			currentSpelling.append(currentChar);
			currentChar = codigoFonte.get(posicaoDeLeitura);
		} else {
		}
		// report a lexical error
	}

	private void takeIt() {
		currentSpelling.append(currentChar);
		currentChar = codigoFonte.get(posicaoDeLeitura);
	}

	private boolean isDigit(char c) {
		switch (currentChar) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return true;
		default:
			return false;
		}
	}

	private boolean isLetter(char c) {
		switch (currentChar) {
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'x':
		case 'y':
		case 'z':

		case 'A':
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'X':
		case 'Y':
		case 'Z':
			return true;
		default:
			return false;
		}
	}

	private boolean isGraphic(char c) {
		return ((int) currentChar >= 32) && (((int) currentChar) <= 126);
		/* 32 a 126 sao os printable characters da ASCII */
	}

	private byte scanToken() {

		if (isLetter(currentChar)) {
			takeIt();
			while (isLetter(currentChar) || isDigit(currentChar))
				takeIt();
			return Token.IDENTIFIER;

		} else if (isDigit(currentChar)) {

			takeIt();
			while (isDigit(currentChar))
				takeIt();
			if (currentChar == '.') {
				if (codigoFonte.get(posicaoDeLeitura + 1) == '.')
					return Token.INT_LITERAL;
				takeIt();
				while (isDigit(currentChar))
					takeIt();
				return Token.FLOAT_LITERAL;
			}
			return Token.INT_LITERAL;

		} else {

			switch (currentChar) {

			case '.':
				takeIt();
				if (currentChar == '.') {
					takeIt();
					return Token.DOUBLE_DOT;
				}
				if (isDigit(currentChar)) {
					takeIt();
					while (isDigit(currentChar))
						takeIt();
					return Token.FLOAT_LITERAL;
				}
				return Token.DOT;

			case '+':
				takeIt();
				return Token.PLUS;

			case '-':
				takeIt();
				return Token.MINUS;

			case '*':
				takeIt();
				return Token.MULTIPLICATION;
			case '/':
				takeIt();
				return Token.DIVISION;

			case '=':
				takeIt();
				return Token.EQUALS;

			case '<':
				takeIt();
				switch (currentChar) {
				case '=':
					takeIt();
					return Token.LESS_EQUAL;
				case '>':
					takeIt();
					return Token.DIFERENT;
				default:
					return Token.MINUS;
				}

			case '>':
				takeIt();
				switch (currentChar) {
				case '=':
					takeIt();
					return Token.MORE_EQUAL;
				default:
					return Token.MORE;
				}

			case ';':
				takeIt();
				return Token.SEMICOLON;

			case ':':
				takeIt();
				if (currentChar == '=') {
					takeIt();
					return Token.ASSIGENMENT;
				}
				return Token.COLON;

			case '(':
				takeIt();
				return Token.LEFT_PARENTHESIS;

			case ')':
				takeIt();
				return Token.RIGHT_PARENTHESIS;

			case '[':
				takeIt();
				return Token.LEFT_BRACKET;

			case ']':
				takeIt();
				return Token.RIGHT_PARENTHESIS;

			case ',':
				takeIt();
				return Token.COMMA;

			case '\000':
				return Token.EOT;

			default:
				takeIt();
				byte a = 'a';
				return  a;
				//Lancar exception erro sintaxe
			}
		}
	}

	private void scanSeparator() {

	}

	public Token scan() {
		while (currentChar == '!' || currentChar == ' ' || currentChar == '\n')
			scanSeparator();
		currentSpelling = new StringBuffer("");
		currentKind = scanToken();
		return new Token(currentKind, currentSpelling.toString());
	}
}
