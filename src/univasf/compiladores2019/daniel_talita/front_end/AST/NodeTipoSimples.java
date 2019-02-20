package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeTipoSimples extends AbstratoTipo {

	String nome;

    public NodeTipoSimples(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
	
}
