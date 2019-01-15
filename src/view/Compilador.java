package view;

import compilation_error.LexicalError;
import front_end.Scanner;
import front_end.Token;

public class Compilador {

	public static void main(String[] args) {
		String codigofonte = "  "
				+ "  while abacaxi then bolinha. 1.33 .33 1. ..2 ..33 1..2 1.. ola !Isso eh um comentario"
				+ "\nhey man how are you do for while: ; ( ) [] ) ] ) [ ] ( )  @ * 234 21 2 0 \r / <> <= >= ::= := & ¨ 1.21.74..2.5... ";
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
	}
}
