package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeDeclaracao extends AST {
	
	private NodeIdentificador name;
	private AbstratoTipo tipoDaVariavel;
	private NodeDeclaracao next;
	
	
	public NodeIdentificador getName() {
		return name;
	}
	public void setName(NodeIdentificador nome) {
		this.name = nome;
	}
	public AbstratoTipo getTipoDaVariavel() {
		return tipoDaVariavel;
	}
	public void setTipoDaVariavel(AbstratoTipo tipoDaVariavel) {
		this.tipoDaVariavel = tipoDaVariavel;
	}
	public NodeDeclaracao getNext() {
		return next;
	}
	public void setNext(NodeDeclaracao next) {
		this.next = next;
	}
        public void visit (Visitor v){
            v.visitNodeDeclaracao(this);
        }
}
