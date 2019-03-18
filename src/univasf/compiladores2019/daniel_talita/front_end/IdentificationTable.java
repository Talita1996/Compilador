package univasf.compiladores2019.daniel_talita.front_end;
import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeDeclaracao;
import java.util.ArrayList;

public class IdentificationTable {
//Variáveis representando a tabela de identificação
    int i;
    int last = -1;
    ArrayList<Variavel> identificador = new ArrayList();
            
    public IdentificationTable() {
        
    }
//Construtor
    public void enter (Variavel var) throws ContextualError {
    //Adiciona uma entrada na tabela associando o identificador com o atributo 
        i = 0;
        while ((i <= last) && (!identificador.get(i).getNome().equals(var.getNome()))) i++;
        if (i > last) {
            identificador.add(++last, var);
        }
        else {
            throw new ContextualError("A variavel " +var.getNome()+ " está sendo declarada duas vezes"); //Devemos retornar a linha e a colunas aqui? Se sim. Como? Chamando Scanner?
        }
    }

    public NodeDeclaracao retrieve (String var) throws ContextualError {
// Retorna o atributo associado com o identificador de entrada. Se não houver nenhum, retorna null
        i=0;
        while ((i <= last) && (!identificador.get(i).equals(var))) i++;
        if (i <= last){
            return identificador.get(i).getDeclaracao();
        }
        else {
            throw new ContextualError("A variavel " +var+ " não foi declarada");
        }
    }
}
