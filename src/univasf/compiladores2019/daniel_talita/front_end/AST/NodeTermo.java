package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

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
        public void visit (Visitor v) throws ContextualError{
            v.visitNodeTermo(this);
        }
}
