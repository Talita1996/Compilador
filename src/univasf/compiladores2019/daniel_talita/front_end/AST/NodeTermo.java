package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeTermo extends AST {
	private NodeFator fator;
	private NodeOperadorMul operador;
	private NodeTermo fatoresADireita;
	
	public NodeFator getFator() {
		return fator;
	}
	public void setFator(NodeFator fator) {
		this.fator = fator;
	}
	public NodeOperadorMul getOperador() {
		return operador;
	}
	public void setOperador(NodeOperadorMul operador) {
		this.operador = operador;
	}
	public NodeTermo getFatoresADireita() {
		return fatoresADireita;
	}
	public void setFatoresADireita(NodeTermo fatoresADireita) {
		this.fatoresADireita = fatoresADireita;
	}
}
