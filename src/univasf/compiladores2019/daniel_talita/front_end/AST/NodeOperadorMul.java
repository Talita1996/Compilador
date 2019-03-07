package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeOperadorMul extends AbstratoTerminal {
	
	public NodeOperadorMul(String spelling) {
		this.spelling = spelling;
	}
        public void visit (Visitor v){
            v.visitNodeOperadorMul(this);
        }
}
