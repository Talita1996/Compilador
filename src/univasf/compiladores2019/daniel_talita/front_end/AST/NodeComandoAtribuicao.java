package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeComandoAtribuicao extends AbstratoComando {

	
	private NodeIdentificador id;
	private NodeExpressao dimensoesSeForAgregadoSimples;
	private NodeExpressao valorAtribuido;
	
	public NodeIdentificador getId() {
		return id;
	}
	public void setId(NodeIdentificador id) {
		this.id = id;
	}
	public NodeExpressao getDimensoesSeForAgregadoSimples() {
		return dimensoesSeForAgregadoSimples;
	}
	public void setDimensoesSeForAgregadoSimples(NodeExpressao dimensoesSeForAgregadoSimples) {
		this.dimensoesSeForAgregadoSimples = dimensoesSeForAgregadoSimples;
	}
	public NodeExpressao getValorAtribuido() {
		return valorAtribuido;
	}
	public void setValorAtribuido(NodeExpressao valorAtribuido) {
		this.valorAtribuido = valorAtribuido;
	}
	
	
	
}
