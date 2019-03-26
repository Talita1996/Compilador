package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeExpressaoSimples extends AbstratoExpressao {

	NodeTermo termo;
	NodeOperadorAd operador;
	NodeExpressaoSimples termosADireita;

	public NodeTermo getTermo() {
		return termo;
	}

	public void setTermo(NodeTermo termoEsquerdo) {
		this.termo = termoEsquerdo;
	}

	public NodeOperadorAd getOperador() {
		return operador;
	}

	public void setOperador(NodeOperadorAd operador) {
		this.operador = operador;
	}

	public NodeExpressaoSimples getTermosADireita() {
		return termosADireita;
	}

	public void setTermosADireita(NodeExpressaoSimples termosADireita) {
		this.termosADireita = termosADireita;
	}

	public void visit(Visitor v) throws ContextualError {
		v.visitNodeExpressaoSimples(this);
	}
}
