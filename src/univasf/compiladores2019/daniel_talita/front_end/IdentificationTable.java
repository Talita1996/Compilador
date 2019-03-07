package univasf.compiladores2019.daniel_talita.front_end;
import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import java.util.ArrayList;

public class IdentificationTable {
//Variáveis representando a tabela de identificação
    int i;
    int last = -1;
    ArrayList<String> identificador = new ArrayList();
            
    public IdentificationTable() {
    }
//Construtor
    public void enter (String id, Attribute attr) throws ContextualError{
    //Adiciona uma entrada na tabela associando o identificador com o atributo 
        i = 0;
        while ((i <= last) && (!identificador.get(i).equals(id))) i++;
        if (i > last) {
            last++;
            identificador.add(last, id); 
        }
        else {
            throw new ContextualError("Erro! A variavel" +id+ " está sendo declarada duas vezes"); //Devemos retornar a linha e a colunas aqui? Se sim. Como? Chamando Scanner?
        }
    }

    public Attribute retrieve (String id) {
// Retorna o atributo associado com o identificador de entrada. Se não houver nenhum, retorna null

    }
}
