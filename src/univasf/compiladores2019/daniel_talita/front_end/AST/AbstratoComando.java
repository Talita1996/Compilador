package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public abstract class AbstratoComando extends AST {

	private AbstratoComando next;
        
        public abstract void visit (Visitor v) throws ContextualError;

	public AbstratoComando getNext() {
		return next;
	}

	public void setNext(AbstratoComando next) {
		this.next = next;
	}
}
