package univasf.compiladores2019.daniel_talita.front_end.AST;
import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class NodeIdentificador extends AbstratoTerminal {

    public NodeIdentificador(String spelling) {
            this.spelling = spelling;
    }
    @Override
    public void visit (Visitor v) throws ContextualError{
        v.visitNodeIdentificador(this);
    }
}
