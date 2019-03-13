package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeLiteralFloat extends AbstratoLiteral {

	private Double valor;

    public NodeLiteralFloat(Double valor) {
        this.valor = valor;
    }

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
        @Override
        public void visit (Visitor v){
            v.visitNodeLiteralFloat(this);
        }
}
