package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeExpressao extends AbstratoExpressao {

	private NodeExpressaoSimples expressaoEsquerda, expressaoDireita;
	private NodeOperadorRel operador;
	private NodeExpressao next;

	public NodeExpressao getNext() {
		return next;
	}

	public void setNext(NodeExpressao next) {
		this.next = next;
	}

	public NodeExpressaoSimples getExpressaoEsquerda() {
		return expressaoEsquerda;
	}

	public void setExpressaoEsquerda(NodeExpressaoSimples expressaoEsquerda) {
		this.expressaoEsquerda = expressaoEsquerda;
	}

	public NodeExpressaoSimples getExpressaoDireita() {
		return expressaoDireita;
	}

	public void setExpressaoDireita(NodeExpressaoSimples expressaoDireita) {
		this.expressaoDireita = expressaoDireita;
	}

	public NodeOperadorRel getOperador() {
		return operador;
	}

	public void setOperador(NodeOperadorRel operador) {
		this.operador = operador;
	}

	public void visit(Visitor v) throws ContextualError {
		v.visitNodeExpressao(this);
	}
}
