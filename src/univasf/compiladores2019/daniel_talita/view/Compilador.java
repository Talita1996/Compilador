package univasf.compiladores2019.daniel_talita.view;

import univasf.compiladores2019.daniel_talita.compilation_errors.LexicalError;
import univasf.compiladores2019.daniel_talita.compilation_errors.SintaxeError;
import univasf.compiladores2019.daniel_talita.front_end.Parser;
import univasf.compiladores2019.daniel_talita.front_end.Scanner;
import univasf.compiladores2019.daniel_talita.front_end.Token;

public class Compilador {

	public static void main(String[] args) {
		String codigofonte = "  "
				+ "  while abacaxi then bolinha. 1.33 .33 1. ..2 ..33 1..2 1.. ola !Isso eh um comentario"
				+ "\nhey man how are you do for while: ; ( ) [] ) ] ) [ ] ( ) * 234 21 2 0 \r / <> <= >= ::= := 1.21.74..2.5...".concat(Character.toString('\000'));
		codigofonte = "program bolinhaCao; \n begin \n perolaCao := 23; \n gato := true; \n end.".concat(Character.toString('\000'));
		codigofonte = "program Compilador; \n "
				+ "var X,Y,Z:integer; \n "
				+ "var A,B,C: boolean; \n "
				+ "var abacaxi: array[2..2] of integer ; \n"
				+ " begin \n"
				+ " begin \n"
				+ " Telefone[2+2] := 5+6 > 7+8; \n"
				+ " if 3+1 then \n"
				+ " Telefone[0] = 1 \n"
				+ " else \n"
				+ " Telefone[1] = 0 \n"
				+ " end \n"
				+ " end.";
		Scanner scanner = new Scanner(codigofonte);

		Token token;
		
		try {
			token = scanner.scan();
			while (token.kind != Token.EOT) {
				System.out.println("A grafia do token retornado foi " + token.spelling);
				System.out.println("O tipo do token retornado foi " + token.kind + "\n");
				token = scanner.scan();
			}
		} catch (LexicalError e) {
			System.out.println("Erro léxico:\n" + e.getMessage());
		}
		
		
		System.out.println("\n\n\nIniciando analise sintatica");
		codigofonte = "program bolinhaCao; \n begin \n perolaCao := 23; \n gato := true; \n end.";
		
		
		codigofonte = "program Compilador; \n "
				+ "var X,Y,Z:integer; \n "
				+ "var A,B,C: boolean; \n "
				+ "var A,B,C: real;\n"
				+ "var abacaxi: array[2..2] of integer; \n"
				+ " begin \n"
				+ " begin \n"
				+ " Telefone[2+2] := 5+6 > 7+8; \n"
				+ " if 3+1 then \n"
				+ " Telefone[0] := 1 \n"
				+ " else \n"
				+ " Telefone[1] := 0; \n"
				+ " end; \n"
				+ " end.";

//		System.out.println(codigofonte);
		
		Parser parser = new Parser(codigofonte);
		try {
			parser.parse();
			System.out.println("Analise com sucesso!");
		} catch (LexicalError e) {
			System.out.println("Erro lexico");
			e.printStackTrace();
		} catch (SintaxeError e) {
			System.out.println("Erro sintaxe");
			e.printStackTrace();
		}
		
	}
}
