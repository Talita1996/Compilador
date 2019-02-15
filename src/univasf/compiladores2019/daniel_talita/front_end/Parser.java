package univasf.compiladores2019.daniel_talita.front_end;

import univasf.compiladores2019.daniel_talita.compilation_errors.LexicalError;
import univasf.compiladores2019.daniel_talita.compilation_errors.SintaxeError;
import univasf.compiladores2019.daniel_talita.front_end.AST.AbstratoTipo;
import univasf.compiladores2019.daniel_talita.front_end.AST.AbstratoComando;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeCorpo;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeDeclaracao;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeIdentificador;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodePrograma;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeTipoSimples;

public class Parser {

	/***************************** VARIAVEIS *****************************/
	private Token currentToken;
	private Scanner scanner;

	/**************************** CONSTRUTOR *****************************/
	public Parser(String codigoFonte) {
		this.scanner = new Scanner(codigoFonte);
	}

	/************************ METODOS AUXILIARES ************************/
	/**
	 * Verifica se o token corrente faz com que o codigo fonte esteja de acordo com
	 * a gramática da linguagem.
	 * 
	 * @param expectedKind
	 * @throws LexicalError
	 * @throws SintaxeError
	 */
	private void accept(byte expectedKind) throws LexicalError, SintaxeError {
		if (currentToken.kind == expectedKind) {
			currentToken = scanner.scan();
		} else {
			throw new SintaxeError(
					"Erro! O compilador esperava encontrar: \"" + Token.getSpellings(expectedKind)
							+ "\" , mas encontrou: \"" + currentToken.spelling + "\"",
					scanner.getLinha(), scanner.getColuna());
		}
	}

	/**
	 * Consome um token do codigo fonte.
	 * 
	 * @throws LexicalError
	 */
	private void acceptIt() throws LexicalError {
		currentToken = scanner.scan();
	}

	/************************
	 * METODOS DE ANALISE SINTATICA
	 * 
	 * @return
	 ************************/
	private NodePrograma parseProgram() throws LexicalError, SintaxeError {

		NodePrograma nodePrograma = new NodePrograma();

		accept(Token.PROGRAM);
		accept(Token.IDENTIFIER);
		accept(Token.SEMICOLON);

		nodePrograma.setCorpoDoPrograma(parseCorpo());

		accept(Token.DOT);

		return nodePrograma;
	}

	private NodeCorpo parseCorpo() throws LexicalError, SintaxeError {

		NodeCorpo nodeCorpo = new NodeCorpo();

		NodeDeclaracao aux, first, last;
		first = last = null;

		while (currentToken.kind == Token.VAR) {
			aux = parseDeclaracao();
			accept(Token.SEMICOLON);

			if (first == null)
				first = aux;
			else
				last.setNext(aux);
			
			while (aux.getNext() != null)
				aux = aux.getNext();
			
			last = aux;
		}

		nodeCorpo.setDeclaracoes(first);

		nodeCorpo.setComandos(parseComandoComposto());

		return nodeCorpo;
	}

	private NodeDeclaracao parseDeclaracao() throws LexicalError, SintaxeError {
		acceptIt();
		
		NodeDeclaracao firstDeclaration, last = null, aux = null; 
		firstDeclaration = new NodeDeclaracao();
		firstDeclaration.setName(new NodeIdentificador(currentToken.spelling));

		accept(Token.IDENTIFIER);

		while (currentToken.kind == Token.COMMA) {
			acceptIt();
			if(firstDeclaration.getNext() == null) {
				aux = new NodeDeclaracao();
				aux.setName(new NodeIdentificador(currentToken.spelling));
				firstDeclaration.setNext(aux);
			}else {
				aux = new NodeDeclaracao();
				aux.setName(new NodeIdentificador(currentToken.spelling));
				last.setNext(aux);
			}
			last = aux;
			
			accept(Token.IDENTIFIER);
		}

		accept(Token.COLON);
		firstDeclaration.setTipoDaVariavel(parseTipo());
		aux = firstDeclaration;
		
		while(aux.getNext() != null) {
			aux.setTipoDaVariavel(firstDeclaration.getTipoDaVariavel());
			aux = aux.getNext();
		}
		
		return firstDeclaration;

	}

	private AbstratoTipo parseTipo() throws LexicalError, SintaxeError {
		AbstratoTipo tipo = null;;
		
		switch (currentToken.kind) {
		case Token.ARRAY:
			tipo = parseTipoAgregado();
			break;
		case Token.INTEGER:
		case Token.BOOLEAN:
		case Token.REAL:
			acceptIt();
			tipo = new NodeTipoSimples();
			break;

		default:
			throw new SintaxeError("Tipo inválido", scanner.getLinha(), scanner.getColuna());
		}
		return tipo;
	}

	private AbstratoTipo parseTipoAgregado() throws LexicalError, SintaxeError {
		acceptIt();
		accept(Token.LEFT_BRACKET);
		parseLiteral();
		accept(Token.DOUBLE_DOT);
		parseLiteral();
		accept(Token.RIGHT_BRACKET);
		accept(Token.OF);
		return parseTipo();
	}

	private void parseLiteral() throws LexicalError, SintaxeError {
		switch (currentToken.kind) {
		case Token.INT_LITERAL:
			acceptIt();
			break;
		case Token.FLOAT_LITERAL:
			acceptIt();
			break;
		case Token.TRUE:
		case Token.FALSE:
			acceptIt();
			break;
		default:
			throw new SintaxeError("AbstratoLiteral inválido", scanner.getLinha(), scanner.getColuna());
		}
	}

	private AbstratoComando parseComandoComposto() throws LexicalError, SintaxeError {
		accept(Token.BEGIN);
		while ((currentToken.kind == Token.IF) || (currentToken.kind == Token.WHILE)
				|| (currentToken.kind == Token.BEGIN) || (currentToken.kind == Token.IDENTIFIER)) {
			parseComando();
			accept(Token.SEMICOLON);
		}
		accept(Token.END);
		return null;
	}

	private void parseComando() throws LexicalError, SintaxeError {
		switch (currentToken.kind) {

		case Token.IDENTIFIER:
			parseAtribuicao();
			break;

		case Token.IF:
			parseCondicional();
			break;

		case Token.WHILE:
			parseIterativo();
			break;

		case Token.BEGIN:
			parseComandoComposto();
			break;

		default:
			throw new SintaxeError("AbstratoComando inválido", scanner.getLinha(), scanner.getColuna());
		}
	}

	private void parseAtribuicao() throws LexicalError, SintaxeError {
		acceptIt();
		while (currentToken.kind == Token.LEFT_BRACKET) {
			acceptIt();
			parseExpressao();
			accept(Token.RIGHT_BRACKET);
		}
		accept(Token.ASSIGENMENT);
		parseExpressao();
	}

	private void parseCondicional() throws LexicalError, SintaxeError {
		acceptIt();
		parseExpressao();
		accept(Token.THEN);
		parseComando();
		if (currentToken.kind == Token.ELSE) {
			acceptIt();
			parseComando();
		}
	}

	private void parseIterativo() throws LexicalError, SintaxeError {
		acceptIt();
		parseExpressao();
		accept(Token.DO);
		parseComando();
	}

	private void parseExpressao() throws LexicalError, SintaxeError {
		parseTermo();
		while (currentToken.kind == Token.PLUS || currentToken.kind == Token.MINUS || currentToken.kind == Token.OR) {
			acceptIt();
			parseTermo();
		}

		if (currentToken.kind == Token.LESS || currentToken.kind == Token.LESS_EQUAL || currentToken.kind == Token.MORE
				|| currentToken.kind == Token.MORE_EQUAL || currentToken.kind == Token.EQUALS
				|| currentToken.kind == Token.DIFERENT) {
			acceptIt();
			parseTermo();
			while (currentToken.kind == Token.PLUS || currentToken.kind == Token.MINUS
					|| currentToken.kind == Token.OR) {
				acceptIt();
				parseTermo();
			}
		}
	}

	private void parseTermo() throws LexicalError, SintaxeError {
		parseFator();
		while (currentToken.kind == Token.MULTIPLICATION || currentToken.kind == Token.DIVISION
				|| currentToken.kind == Token.AND) {
			acceptIt();
			parseFator();
		}
	}

	private void parseFator() throws LexicalError, SintaxeError {

		switch (currentToken.kind) {

		case Token.IDENTIFIER:
			acceptIt();
			while (currentToken.kind == Token.LEFT_BRACKET) {
				acceptIt();
				parseExpressao();
				accept(Token.LEFT_BRACKET);
			}
			break;

		case Token.INT_LITERAL:
		case Token.FLOAT_LITERAL:
		case Token.TRUE:
		case Token.FALSE:
			acceptIt();
			break;

		case Token.LEFT_PARENTHESIS:
			acceptIt();
			parseExpressao();
			accept(Token.RIGHT_PARENTHESIS);
			break;

		default:
			throw new SintaxeError("Fator invalido", scanner.getLinha(), scanner.getColuna());
		}
	}

	/************************ METODO PRINCIPAL ************************/
	/**
	 * Verifica se as sentenças do codigo fonte estao de acordo com a gramatica da
	 * linguagem
	 * 
	 * @throws LexicalError
	 * @throws SintaxeError
	 */
	public NodePrograma parse() throws LexicalError, SintaxeError {
		currentToken = scanner.scan();
		NodePrograma arvoreSintaxeDoPrograma = parseProgram();

		if (currentToken.kind != Token.EOT)
			throw new SintaxeError("Fim de arquivo, inválido", scanner.getLinha(), scanner.getColuna());

		return arvoreSintaxeDoPrograma;
	}

}
