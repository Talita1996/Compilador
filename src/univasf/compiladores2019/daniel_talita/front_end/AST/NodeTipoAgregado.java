package univasf.compiladores2019.daniel_talita.front_end.AST;

import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeTipoAgregado extends AbstratoTipo {

	AbstratoTipo tipo;
        AbstratoLiteral lowerIndex, higherIndex;   

    public AbstratoTipo getTipo() {
        return tipo;
    }

    public AbstratoLiteral getLowerIndex() {
        return lowerIndex;
    }

    public AbstratoLiteral getHigherIndex() {
        return higherIndex;
    }

    public void setTipo(AbstratoTipo tipo) {
        this.tipo = tipo;
    }

    public void setLowerIndex(AbstratoLiteral first) {
        this.lowerIndex = first;
    }

    public void setHigherIndex(AbstratoLiteral last) {
        this.higherIndex = last;
    }    
        @Override
    public void visit (Visitor v){
        v.visitNodeTipoAgregado(this);
    }
}
