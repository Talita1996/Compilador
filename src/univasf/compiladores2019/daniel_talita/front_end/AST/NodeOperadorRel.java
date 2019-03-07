package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeOperadorRel extends AbstratoTerminal {

	public NodeOperadorRel(String spelling) {
		this.spelling = spelling;
	}
        public void visit (Visitor v){
            v.visitNodeOperadorRel(this);
        }
}
