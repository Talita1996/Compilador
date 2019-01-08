package view;

import front_end.Scanner;
import front_end.Token;

public class Compilador {

	public static void main(String[] args) {
		String codigofonte = "  " + "  while abacaxi then bolinha";
		Scanner scanner = new Scanner(codigofonte);
		Token token = scanner.scan();
		System.out.println("A grafia do token retornado foi " + token.spelling);
		System.out.println("O tipo do token retornado foi " + token.kind + "\n");
		token = scanner.scan();
		System.out.println("A grafia do token retornado foi " + token.spelling);
		System.out.println("O tipo do token retornado foi " + token.kind + "\n");
		token = scanner.scan();
		System.out.println("A grafia do token retornado foi " + token.spelling);
		System.out.println("O tipo do token retornado foi " + token.kind + "\n");
		token = scanner.scan();
		System.out.println("A grafia do token retornado foi " + token.spelling);
		System.out.println("O tipo do token retornado foi " + token.kind + "\n");
		token = scanner.scan();
		System.out.println("A grafia do token retornado foi " + token.spelling);
		System.out.println("O tipo do token retornado foi " + token.kind + "\n");
		token = scanner.scan();
		System.out.println("A grafia do token retornado foi " + token.spelling);
		System.out.println("O tipo do token retornado foi " + token.kind + "\n");
	}
}
