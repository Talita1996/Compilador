package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodePrograma extends AST{
	
	private NodeCorpo corpoDoPrograma;
	
	public NodeCorpo getCorpoDoPrograma() {
		return corpoDoPrograma;
	}
	public void setCorpoDoPrograma(NodeCorpo corpoDoPrograma) {
		this.corpoDoPrograma = corpoDoPrograma;
	}
        public void visit (Visitor v){
            v.visitNodePrograma(this);
        }
}
