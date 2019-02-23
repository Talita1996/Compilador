package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeFator {
	
	private NodeIdentificador id;
	private NodeExpressao expressao;
	private AbstratoLiteral literal;
	
	public NodeIdentificador getId() {
		return id;
	}
	public void setId(NodeIdentificador id) {
		this.id = id;
	}
	public NodeExpressao getExpressao() {
		return expressao;
	}
	public void setExpressao(NodeExpressao expressao) {
		this.expressao = expressao;
	}
	public AbstratoLiteral getLiteral() {
		return literal;
	}
	public void setLiteral(AbstratoLiteral literal) {
		this.literal = literal;
	}
	
	

}
