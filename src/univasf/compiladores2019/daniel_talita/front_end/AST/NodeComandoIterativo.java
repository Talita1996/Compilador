package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeComandoIterativo extends AbstratoComando {
	
	NodeExpressao expressao;
	AbstratoComando comando;
	public NodeExpressao getExpressao() {
		return expressao;
	}
	public void setExpressao(NodeExpressao expressao) {
		this.expressao = expressao;
	}
	public AbstratoComando getComando() {
		return comando;
	}
	public void setComando(AbstratoComando comando) {
		this.comando = comando;
	}
	public void visit (Visitor v) throws ContextualError{
            v.visitNodeComandoIterativo(this);
        }
}
