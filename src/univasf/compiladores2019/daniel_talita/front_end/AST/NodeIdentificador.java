package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeIdentificador extends AbstratoTerminal {

	public NodeIdentificador(String spelling) {
		this.spelling = spelling;
	}
        public void visit (Visitor v){
            v.visitNodeIdentificador(this);
        }
}
