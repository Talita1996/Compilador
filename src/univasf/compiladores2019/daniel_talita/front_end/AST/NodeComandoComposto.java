package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeComandoComposto extends AbstratoComando{
	public void visit (Visitor v){
            v.visitNodeComandoComposto(this);
        }
}
