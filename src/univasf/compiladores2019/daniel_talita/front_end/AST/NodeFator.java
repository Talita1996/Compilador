package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeFator {
	
	private NodeIdentificador id;
	private NodeExpressao expressoes;
	private AbstratoLiteral literal;
	
	public NodeIdentificador getId() {
		return id;
	}
	public void setId(NodeIdentificador id) {
		this.id = id;
	}
	public AbstratoLiteral getLiteral() {
		return literal;
	}
	public void setLiteral(AbstratoLiteral literal) {
		this.literal = literal;
	}
	public NodeExpressao getExpressoes() {
		return expressoes;
	}
	public void setExpressoes(NodeExpressao expressoes) {
		this.expressoes = expressoes;
	}
}
