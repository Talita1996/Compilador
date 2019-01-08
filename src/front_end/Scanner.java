package front_end;

import java.util.ArrayList;

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

	private ArrayList<Character> codigoFonte = new ArrayList<>();
	private Integer posicaoDeLeitura;

	public Scanner(String codigoFonte) {
		/**
		 * Converte o codigo fonte String -> ArrayList<Character> e inicializa o
		 * currentChar
		 */
		if (codigoFonte != null && !codigoFonte.isEmpty()) {
			for (char ch : codigoFonte.toCharArray())
				this.codigoFonte.add(ch);
			this.codigoFonte.add((char) '\000');
		}

		this.posicaoDeLeitura = 0;
		this.currentChar = this.codigoFonte.get(0);
		System.out.println(this.codigoFonte);
	}

	public ArrayList<Character> getCodigoFonte() {
		return codigoFonte;
	}

	public void setCodigoFonte(ArrayList<Character> codigoFonte) {
		this.codigoFonte = codigoFonte;
	}

	private void take(char expectedChar) {
		if (currentChar == expectedChar) {
			currentSpelling.append(currentChar);
			posicaoDeLeitura++;
			currentChar = codigoFonte.get(posicaoDeLeitura);

		} else {
		}
		// report a lexical error
	}

	private void takeIt() {
		currentSpelling.append(currentChar);
		posicaoDeLeitura++;
		currentChar = codigoFonte.get(posicaoDeLeitura);
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
				return a;
			// Lancar exception erro sintaxe
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
