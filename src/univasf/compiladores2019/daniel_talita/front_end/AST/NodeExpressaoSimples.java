package univasf.compiladores2019.daniel_talita.front_end.AST;

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

}
