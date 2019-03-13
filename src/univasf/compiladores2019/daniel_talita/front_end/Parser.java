package univasf.compiladores2019.daniel_talita.front_end;

import univasf.compiladores2019.daniel_talita.compilation_errors.LexicalError;
import univasf.compiladores2019.daniel_talita.compilation_errors.SintaxeError;
import univasf.compiladores2019.daniel_talita.front_end.AST.AbstratoTipo;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeComandoAtribuicao;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeComandoComposto;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeComandoCondicional;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeComandoIterativo;
import univasf.compiladores2019.daniel_talita.front_end.AST.AbstratoComando;
import univasf.compiladores2019.daniel_talita.front_end.AST.AbstratoLiteral;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeCorpo;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeDeclaracao;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeExpressao;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeExpressaoSimples;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeFator;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeIdentificador;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeLiteralBooleano;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeLiteralInteiro;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeOperadorAd;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeOperadorMul;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeOperadorRel;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeLiteralFloat;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodePrograma;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeTermo;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeTipoAgregado;
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
	 * a gram�tica da linguagem.
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
			if (firstDeclaration.getNext() == null) {
				aux = new NodeDeclaracao();
				aux.setName(new NodeIdentificador(currentToken.spelling));
				firstDeclaration.setNext(aux);
			} else {
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

		while (aux.getNext() != null) {
			aux.setTipoDaVariavel(firstDeclaration.getTipoDaVariavel());
			aux = aux.getNext();
		}

		return firstDeclaration;

	}

	private AbstratoTipo parseTipo() throws LexicalError, SintaxeError {
		AbstratoTipo tipo = null;

		switch (currentToken.kind) {
		case Token.ARRAY:
			tipo = parseTipoAgregado();
			break;
		case Token.INTEGER:
			//acceptIt();
			tipo = new NodeTipoSimples(currentToken.spelling);
			acceptIt();
                        break;
		case Token.BOOLEAN:
			//acceptIt();
			tipo = new NodeTipoSimples(currentToken.spelling);
                        acceptIt();
			break;
		case Token.REAL:
			//acceptIt();
			tipo = new NodeTipoSimples(currentToken.spelling);
                        acceptIt();
			break;

		default:
			throw new SintaxeError("Tipo inv�lido", scanner.getLinha(), scanner.getColuna());
		}
		return tipo;
	}

	private NodeTipoAgregado parseTipoAgregado() throws LexicalError, SintaxeError {
		NodeTipoAgregado tipoAgregado = new NodeTipoAgregado();
		acceptIt();
		accept(Token.LEFT_BRACKET);
		tipoAgregado.setLowerIndex(parseLiteral());
		accept(Token.DOUBLE_DOT);
		tipoAgregado.setHigherIndex(parseLiteral());
		accept(Token.RIGHT_BRACKET);
		accept(Token.OF);
		tipoAgregado.setTipo(parseTipo());
		return tipoAgregado;
	}

	private AbstratoLiteral parseLiteral() throws LexicalError, SintaxeError {
		AbstratoLiteral literal;
		switch (currentToken.kind) {
		case Token.INT_LITERAL:
			literal = new NodeLiteralInteiro(Integer.parseInt(currentToken.spelling));
			acceptIt();
			break;
		case Token.FLOAT_LITERAL:
			literal = new NodeLiteralFloat(Double.parseDouble(currentToken.spelling));
			acceptIt();
			break;
		case Token.TRUE:
		case Token.FALSE:
			literal = new NodeLiteralBooleano(Boolean.parseBoolean(currentToken.spelling));
			acceptIt();
			break;
		default:
			throw new SintaxeError("AbstratoLiteral inv�lido", scanner.getLinha(), scanner.getColuna());
		}
		return literal;
	}

	private NodeComandoComposto parseComandoComposto() throws LexicalError, SintaxeError {
		accept(Token.BEGIN);

		NodeComandoComposto comando = new NodeComandoComposto();

		AbstratoComando first, last, aux;
		first = last = null;

		while ((currentToken.kind == Token.IF) || (currentToken.kind == Token.WHILE)
				|| (currentToken.kind == Token.BEGIN) || (currentToken.kind == Token.IDENTIFIER)) {
			aux = parseComando();

			if (first == null)
				first = aux;
			else
				last.setNext(aux);
			last = aux;
			accept(Token.SEMICOLON);
		}
		accept(Token.END);

		comando.setNext(first);

		return comando;
	}

	private AbstratoComando parseComando() throws LexicalError, SintaxeError {

		AbstratoComando comando;

		switch (currentToken.kind) {

		case Token.IDENTIFIER:
			comando = parseAtribuicao();
			break;

		case Token.IF:
			comando = parseCondicional();
			break;

		case Token.WHILE:
			comando = parseIterativo();
			break;

		case Token.BEGIN:
			comando = parseComandoComposto();
			break;

		default:
			throw new SintaxeError("AbstratoComando inv�lido", scanner.getLinha(), scanner.getColuna());
		}
		return comando;

	}

	private NodeComandoAtribuicao parseAtribuicao() throws LexicalError, SintaxeError {

		NodeComandoAtribuicao comando = new NodeComandoAtribuicao();
		comando.setId(new NodeIdentificador(currentToken.spelling));

		acceptIt();

		NodeExpressao first, last, aux;
		first = last = null;

		while (currentToken.kind == Token.LEFT_BRACKET) {
			acceptIt();

			aux = parseExpressao();

			if (first == null)
				first = aux;
			else
				last.setNext(aux);
			last = aux;

			accept(Token.RIGHT_BRACKET);
		}

		comando.setDimensoesSeForAgregadoSimples(first);

		accept(Token.ASSIGENMENT);
		comando.setValorAtribuido(parseExpressao());
		return comando;
	}

	private NodeComandoCondicional parseCondicional() throws LexicalError, SintaxeError {

		NodeComandoCondicional comando = new NodeComandoCondicional();

		acceptIt();
		comando.setCondicao(parseExpressao());
		accept(Token.THEN);
		comando.setComandoIf(parseComando());
		if (currentToken.kind == Token.ELSE) {
			acceptIt();
			comando.setComandoElse(parseComando());
		}
		return comando;
	}

	private NodeComandoIterativo parseIterativo() throws LexicalError, SintaxeError {
		NodeComandoIterativo comando = new NodeComandoIterativo();
		acceptIt();
		comando.setExpressao(parseExpressao());
		accept(Token.DO);
		comando.setComando(parseComando());
		return comando;
	}

	private NodeExpressao parseExpressao() throws LexicalError, SintaxeError {
		NodeExpressao expressao = new NodeExpressao();
		expressao.setExpressaoEsquerda(parseExpressaoSimples());
		if (currentToken.kind == Token.LESS || currentToken.kind == Token.LESS_EQUAL || currentToken.kind == Token.MORE
				|| currentToken.kind == Token.MORE_EQUAL || currentToken.kind == Token.EQUALS
				|| currentToken.kind == Token.DIFERENT) {
			expressao.setOperador(new NodeOperadorRel(currentToken.spelling));
			acceptIt();
			expressao.setExpressaoDireita(parseExpressaoSimples());
		}
		return expressao;
	}

	private NodeExpressaoSimples parseExpressaoSimples() throws LexicalError, SintaxeError {
		NodeExpressaoSimples aux, expressaoSimples = new NodeExpressaoSimples();
		expressaoSimples.setTermo(parseTermo());
		aux = expressaoSimples;
		while (currentToken.kind == Token.PLUS || currentToken.kind == Token.MINUS || currentToken.kind == Token.OR) {
			aux.setOperador(new NodeOperadorAd(currentToken.spelling));
			acceptIt();
			NodeExpressaoSimples termoADireita = new NodeExpressaoSimples();
			termoADireita.setTermo(parseTermo());
			aux.setTermosADireita(termoADireita);
			aux = termoADireita;
		}
		return expressaoSimples;
	}

	private NodeTermo parseTermo() throws LexicalError, SintaxeError {
		NodeTermo aux, termo = new NodeTermo();
		termo.setFator(parseFator());
		aux = termo;
		while (currentToken.kind == Token.MULTIPLICATION || currentToken.kind == Token.DIVISION
				|| currentToken.kind == Token.AND) {
			aux.setOperador(new NodeOperadorMul(currentToken.spelling));
			acceptIt();
			NodeTermo fatorADireita = new NodeTermo();
			fatorADireita.setFator(parseFator());
			aux.setFatoresADireita(fatorADireita);
			aux = fatorADireita;
		}
		return termo;
	}

	private NodeFator parseFator() throws LexicalError, SintaxeError {

		NodeFator fator = new NodeFator();
		switch (currentToken.kind) {

		case Token.IDENTIFIER:
			NodeExpressao first, last, aux;
			first = last = null;

			fator.setId(new NodeIdentificador(currentToken.spelling));
			acceptIt();

			if (currentToken.kind == Token.LEFT_BRACKET) {
				acceptIt();
				first = last =  parseExpressao();
				accept(Token.LEFT_BRACKET);
				while (currentToken.kind == Token.LEFT_BRACKET) {
					acceptIt();
					aux = parseExpressao();
					last.setNext(aux);
					last = aux;
					accept(Token.LEFT_BRACKET);
				}
				fator.setExpressoes(first);
			}
			break;

		case Token.INT_LITERAL:
			fator.setLiteral(new NodeLiteralInteiro(Integer.valueOf(currentToken.spelling)));
			acceptIt();
			break;
		case Token.FLOAT_LITERAL:
			fator.setLiteral(new NodeLiteralFloat(Double.valueOf(currentToken.spelling)));
			acceptIt();
			break;
		case Token.TRUE:
		case Token.FALSE:
			fator.setLiteral(new NodeLiteralBooleano(Boolean.valueOf(currentToken.spelling)));
			acceptIt();
			break;

		case Token.LEFT_PARENTHESIS:
			acceptIt();
			fator.setExpressoes(parseExpressao());
			accept(Token.RIGHT_PARENTHESIS);
			break;

		default:
			throw new SintaxeError("Fator invalido", scanner.getLinha(), scanner.getColuna());
		}
		return fator;
	}

	/************************ METODO PRINCIPAL ************************/
	/**
	 * Verifica se as senten�as do codigo fonte estao de acordo com a gramatica da
	 * linguagem
	 * 
	 * @throws LexicalError
	 * @throws SintaxeError
	 */
	public NodePrograma parse() throws LexicalError, SintaxeError {
		currentToken = scanner.scan();
		NodePrograma arvoreSintaxeDoPrograma = parseProgram();

		if (currentToken.kind != Token.EOT)
			throw new SintaxeError("Fim de arquivo, inv�lido", scanner.getLinha(), scanner.getColuna());

		return arvoreSintaxeDoPrograma;
	}

}
