package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

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
    public void visit (Visitor v){
        v.visitNodeTipoSimples(this);
    }
}
