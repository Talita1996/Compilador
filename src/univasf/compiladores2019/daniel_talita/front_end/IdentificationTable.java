package univasf.compiladores2019.daniel_talita.front_end;
import java.util.ArrayList;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeDeclaracao;

public class IdentificationTable {
//Vari√°veis representando a tabela de identifica√ß√£o
    int i;
    int last = -1;
    @SuppressWarnings({ "unchecked", "rawtypes" })
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
            throw new ContextualError("A vari·vel " +var.getNome()+ " est· sendo declarada duas vezes"); //Devemos retornar a linha e a colunas aqui? Se sim. Como? Chamando Scanner?
        }
    }

    public NodeDeclaracao retrieve (String var) throws ContextualError {
// Retorna o atributo associado com o identificador de entrada. Se n√£o houver nenhum, retorna null
        i=0;
        while ((i <= last) && (!identificador.get(i).getNome().equals(var))) i++;
        if (i <= last){
            return identificador.get(i).getDeclaracao();
        }
        else {
            throw new ContextualError("A vari·vel " + var + " n„o foi declarada");
        }
    }
}
