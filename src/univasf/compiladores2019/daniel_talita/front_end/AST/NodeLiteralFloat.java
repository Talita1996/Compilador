package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeLiteralFloat extends AbstratoLiteral {

	Double valor;

    public NodeLiteralFloat(Double valor) {
        this.valor = valor;
    }
}
