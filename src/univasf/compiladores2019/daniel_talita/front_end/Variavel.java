package univasf.compiladores2019.daniel_talita.front_end;

import univasf.compiladores2019.daniel_talita.front_end.AST.NodeDeclaracao;

class Variavel {
    private String nome;
    private NodeDeclaracao declaracao;

    public Variavel(String nome, NodeDeclaracao declaracao) {
        this.nome = nome;
        this.declaracao = declaracao;
    }

    public String getNome() {
        return nome;
    }

    public NodeDeclaracao getDeclaracao() {
        return declaracao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDeclaracao(NodeDeclaracao declaracao) {
        this.declaracao = declaracao;
    }
}
