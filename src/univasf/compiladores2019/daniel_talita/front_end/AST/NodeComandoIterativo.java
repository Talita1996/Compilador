package univasf.compiladores2019.daniel_talita.front_end.AST;

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
	
}
