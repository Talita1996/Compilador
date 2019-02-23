package univasf.compiladores2019.daniel_talita.front_end.AST;

public abstract class AbstratoComando extends AST {

	private AbstratoComando next;

	public AbstratoComando getNext() {
		return next;
	}

	public void setNext(AbstratoComando next) {
		this.next = next;
	}
}
