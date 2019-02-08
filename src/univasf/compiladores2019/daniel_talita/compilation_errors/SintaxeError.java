package univasf.compiladores2019.daniel_talita.compilation_errors;

public class SintaxeError extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SintaxeError(String mensagem,int linha, int coluna) {
		super(mensagem.concat("\n Linha: " + linha + " Coluna: " + coluna));
	}

}
