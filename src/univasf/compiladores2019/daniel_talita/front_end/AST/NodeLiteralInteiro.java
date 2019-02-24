package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeLiteralInteiro extends AbstratoLiteral {

	private Integer valor;

    public NodeLiteralInteiro(Integer valor) {
        this.valor = valor;
    }

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
    
    
}
