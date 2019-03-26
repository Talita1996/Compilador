package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

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
        public void visit (Visitor v) throws ContextualError{
            v.visitNodeFator(this);
        }
}
