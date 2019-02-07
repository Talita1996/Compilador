package compilation_error;

public class LexicalError extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LexicalError(String mensagem,int linha, int coluna) {
		super(mensagem.concat("\n Linha: " + linha + " Coluna: " + coluna));
	}

}
