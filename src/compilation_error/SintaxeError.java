package compilation_error;

public class SintaxeError extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SintaxeError(String mensagem,int linha, int coluna) {
		super(mensagem.concat("\n Linha: " + linha + " Coluna: " + coluna));
	}

}
