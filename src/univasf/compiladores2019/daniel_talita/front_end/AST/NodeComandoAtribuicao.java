package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeComandoAtribuicao extends AbstratoComando {

	private NodeIdentificador id;
	private NodeExpressao dimensoesSeForAgregadoSimples;
	private NodeExpressao valorAtribuido;
	
	public NodeIdentificador getId() {
		return id;
	}
	public void setId(NodeIdentificador id) {
		this.id = id;
	}
	public NodeExpressao getDimensoesSeForAgregadoSimples() {
		return dimensoesSeForAgregadoSimples;
	}
	public void setDimensoesSeForAgregadoSimples(NodeExpressao dimensoesSeForAgregadoSimples) {
		this.dimensoesSeForAgregadoSimples = dimensoesSeForAgregadoSimples;
	}
	public NodeExpressao getValorAtribuido() {
		return valorAtribuido;
	}
	public void setValorAtribuido(NodeExpressao valorAtribuido) {
		this.valorAtribuido = valorAtribuido;
	}
        
        @Override
	public void visit (Visitor v) throws ContextualError{
            v.visitNodeComandoAtribuicao(this);
        }
}
