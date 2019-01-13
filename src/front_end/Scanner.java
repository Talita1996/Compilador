package front_end;

/**
 * 
 * Esta classe implementa métodos de analise léxica para reconhecer tokens da
 * linguagem de programação mini-triangle. O referencial teórico empregado pode
 * ser encontrado no livro Programming Language Processors in Java, por DAVID A
 * WATT e DERYCK F BROWN, e nas notas de aula do professor Dr. Marcus Vinícius
 * Midena Ramos da Universidade Federal do Vale do Sâo Francisco, disponivel em
 * <http://www.marcusramos.com.br/univasf/c-2018-2/index.html>.
 * 
 * Documentações auxiliares, a gramatica mini-triangle e a gramática léxica
 * podem ser acessados em <>
 * 
 * @author Daniel Lucas Nunes de Alencar Alves
 * @author Thalita
 * @author Jessica
 * 
 */

public class Scanner {

	private char currentChar;
	private byte currentKind;
	private StringBuffer currentSpelling = new StringBuffer();

	private String codigoFonte;
	private Integer posicaoDeLeitura;

	/**
	 * Recebe o código fonte no compilador e inicializa a leitura do mesmo.
	 */
	public Scanner(String codigoFonte) {
		if (codigoFonte != null && !codigoFonte.isEmpty()) {
			StringBuilder aux = new StringBuilder();
			this.codigoFonte = aux.append(codigoFonte).append((char) '\000').toString();
			this.posicaoDeLeitura = 0;
			this.currentChar = this.codigoFonte.charAt(0);
		}

	}

	public String getCodigoFonte() {
		return codigoFonte;
	}

	public void setCodigoFonte(String codigoFonte) {
		this.codigoFonte = codigoFonte;
	}

	private void take(char expectedChar) {
		if (currentChar == expectedChar) {
			currentSpelling.append(currentChar);
			posicaoDeLeitura++;
			currentChar = codigoFonte.charAt(posicaoDeLeitura);

		} else {
			// Falta lançar um erro lexico aqui
		}
	}

	private void takeIt() {
		currentSpelling.append(currentChar);
		posicaoDeLeitura++;
		currentChar = codigoFonte.charAt(posicaoDeLeitura);
	}

	private boolean isDigit(char c) {
		/**
		 * Verifica se o caractere é um digito, de acordo com o respectivo codigo decimal na tabela ASCII 
		 */
		boolean ehUmDigito = ((int) c >= 48 && (int) c <= 57);
		return ehUmDigito;
	}

	private boolean isLetter(char c) {
		
		/**
		 * Verifica se o caractere é uma letra, de acordo com o respectivo codigo decimal na tabela ASCII
		 */
		
		boolean ehLetraMinuscula = ((int) c >= 97 && (int)c <= 122);
		boolean ehLetraMaiscula =  ((int) c >= 65 && (int) c <= 90);
		
		return ehLetraMaiscula || ehLetraMinuscula;
	}

	private boolean isGraphic(char c) {
		/**
		 * Na tabela ASCII, os caracteres imprimiveis (ou seja, visiveis na tela) tem codigo decimal entre 32 e 126
		 */
		return ((int) c >= 32) && (((int) c) <= 126);
	}

	private byte scanToken() {


		if (isLetter(currentChar)) {
			takeIt();
			while (isLetter(currentChar) || isDigit(currentChar)) {
				takeIt();
			}
			return Token.IDENTIFIER;

		} else if (isDigit(currentChar)) {
			takeIt();
			while (isDigit(currentChar))
				takeIt();
			if (currentChar == '.') {
				if (codigoFonte.charAt(posicaoDeLeitura + 1) == '.')
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
				return Token.RIGHT_BRACKET;

			case ',':
				takeIt();
				return Token.COMMA;

			case '\000':
				return Token.EOT;

			default:
				takeIt();
				return 99;
			}
		}
	}

	private void scanSeparator() {
		switch (currentChar) {
		case '!':
			while (isGraphic(currentChar))
				takeIt();
			take('\n');
			break;
		case ' ':
		case '\r':
			takeIt();
			break;
		case '\n':
			takeIt();
			break;
		}
	}

	/**
	 * @return próximo token mini-triangle do código fonte.
	 */

	public Token scan() {

		while (currentChar == '!' || currentChar == ' ' || currentChar == '\n' || currentChar == '\r') {
			scanSeparator();
		}

		currentSpelling = new StringBuffer("");
		currentKind = scanToken();
		return new Token(currentKind, currentSpelling.toString());
	}
}
