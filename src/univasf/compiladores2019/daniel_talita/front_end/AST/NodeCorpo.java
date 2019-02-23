package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeCorpo extends AST {
	
	private NodeDeclaracao declaracoes;
	private NodeComandoComposto comandos;
	
	
	public NodeDeclaracao getDeclaracoes() {
		return declaracoes;
	}
	public void setDeclaracoes(NodeDeclaracao declaracoes) {
		this.declaracoes = declaracoes;
	}
	public AbstratoComando getComandos() {
		return comandos;
	}
	public void setComandos(NodeComandoComposto comandos) {
		this.comandos = comandos;
	}
}
