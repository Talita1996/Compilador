package univasf.compiladores2019.daniel_talita.front_end.AST;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public abstract class AbstratoTipo extends AST {
    public abstract void visit (Visitor v);
}
