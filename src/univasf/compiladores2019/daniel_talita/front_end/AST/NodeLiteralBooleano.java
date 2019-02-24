package univasf.compiladores2019.daniel_talita.front_end.AST;

public class NodeLiteralBooleano extends AbstratoLiteral {
	
	private Boolean valor;

    public NodeLiteralBooleano(Boolean valor) {
        this.valor = valor;
    }

	public Boolean getValor() {
		return valor;
	}

	public void setValor(Boolean valor) {
		this.valor = valor;
	}
    
    
}
