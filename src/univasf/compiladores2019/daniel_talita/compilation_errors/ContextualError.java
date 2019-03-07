package univasf.compiladores2019.daniel_talita.compilation_errors;

public class ContextualError extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ContextualError (String mensagem) {
		super(mensagem);
	}
}
