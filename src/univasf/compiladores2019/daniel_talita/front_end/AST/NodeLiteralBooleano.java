package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeLiteralBooleano extends AbstratoLiteral {
	
	Boolean valor;

    public NodeLiteralBooleano(Boolean valor) {
        this.valor = valor;
    }
}
