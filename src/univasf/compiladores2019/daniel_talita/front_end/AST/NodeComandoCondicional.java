package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeComandoCondicional extends AbstratoComando {

	NodeExpressao condicao;
	AbstratoComando comandoIf;
	AbstratoComando comandoElse;
	public NodeExpressao getCondicao() {
		return condicao;
	}
	public void setCondicao(NodeExpressao condicao) {
		this.condicao = condicao;
	}
	public AbstratoComando getComandoIf() {
		return comandoIf;
	}
	public void setComandoIf(AbstratoComando comandoIf) {
		this.comandoIf = comandoIf;
	}
	public AbstratoComando getComandoElse() {
		return comandoElse;
	}
	public void setComandoElse(AbstratoComando comandoElse) {
		this.comandoElse = comandoElse;
	}
        public void visit (Visitor v) throws ContextualError{
            v.visitNodeComandoCondicional(this);
        }
}
