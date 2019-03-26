package univasf.compiladores2019.daniel_talita.front_end;
import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeComandoAtribuicao;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeComandoComposto;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeComandoCondicional;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeComandoIterativo;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeCorpo;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeDeclaracao;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeExpressao;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeExpressaoSimples;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeFator;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeIdentificador;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeLiteralBooleano;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeLiteralFloat;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeLiteralInteiro;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeOperadorAd;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeOperadorMul;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeOperadorRel;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodePrograma;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeTermo;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeTipoAgregado;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodeTipoSimples;
import univasf.compiladores2019.daniel_talita.visitor.Visitor;

public class Checker implements Visitor {
    
    IdentificationTable t = new IdentificationTable();

    @Override
    public void visitNodeComandoAtribuicao(NodeComandoAtribuicao node) throws ContextualError {
        if (node != null) {
            node.getId().visit(this);
            if (node.getDimensoesSeForAgregadoSimples() != null) {
                node.getDimensoesSeForAgregadoSimples().visit(this);
            }
            node.getValorAtribuido().visit(this);
        }
        if (node.getNext() != null)
            node.getNext().visit(this);
    }

    @Override
    public void visitNodeComandoComposto(NodeComandoComposto node) throws ContextualError {
        if (node != null) {
            node.getNext().visit(this);
        }
    }

    @Override
    public void visitNodeComandoCondicional(NodeComandoCondicional node) throws ContextualError {
         if (node != null) {
            node.getCondicao().visit(this);
            node.getComandoIf().visit(this);
            if (node.getComandoElse() != null)
                node.getComandoElse().visit(this);
            if (node.getNext() != null)
            node.getNext().visit(this);
        }
    }

    @Override
    public void visitNodeComandoIterativo(NodeComandoIterativo node) throws ContextualError {
        if (node != null) {
            node.getExpressao().visit(this);
            node.getComando().visit(this);
        }
        if (node.getNext() != null)
            node.getNext().visit(this);
    }

    @Override
    public void visitNodeCorpo(NodeCorpo node) throws ContextualError {
        if(node != null) {
            if(node.getDeclaracoes() != null)
                node.getDeclaracoes().visit(this);
            if (node.getComandos() != null)
                node.getComandos().visit(this);
        } 
    }

    @Override
    public void visitNodeDeclaracao(NodeDeclaracao node) throws ContextualError {
        if (node != null){
            Variavel var = new Variavel(node.getName().spelling, node);
                this.t.enter(var);
            if (node.getNext() != null){
                node.getNext().visit(this);
            }
        }
    }

    @Override
    public void visitNodeExpressao(NodeExpressao node) throws ContextualError {
        if (node != null) {
            if (node.getOperador() != null) {
                node.getOperador().visit(this);
            }
            node.getExpressaoEsquerda().visit(this);
            if (node.getExpressaoDireita() != null) {
                node.getExpressaoDireita().visit(this);
            }
        }
    }

    @Override
    public void visitNodeExpressaoSimples(NodeExpressaoSimples node) throws ContextualError {
       if (node != null) {
            if (node.getTermo() != null)
                if (node.getOperador() != null) {
                    node.getOperador().visit(this);
                }
                node.getTermo().visit(this);
            if (node.getTermosADireita() != null) {
                node.getTermosADireita().visit(this);
            }
        }
    }

    @Override
    public void visitNodeFator(NodeFator node) throws ContextualError {
        if (node != null) {
            if (node.getId() != null) {
               node.getId().visit(this);
            } 
            if (node.getLiteral() != null) {
                node.getLiteral().visit(this);
            }
            if (node.getExpressoes() != null) {
                node.getExpressoes().visit(this);
            }
        }
    }

    @Override
    public void visitNodeIdentificador(NodeIdentificador node) throws ContextualError {
        if (node != null)
                t.retrieve(node.spelling);
    }

    @Override
    public void visitNodeLiteralBooleano(NodeLiteralBooleano node) {
        node.getValor();
    }

    @Override
    public void visitNodeLiteralFloat(NodeLiteralFloat node) {
        node.getValor();
    }

    @Override
    public void visitNodeLiteralInteiro(NodeLiteralInteiro node) {
        node.getValor(); 
    }

    @Override
    public void visitNodeOperadorAd(NodeOperadorAd node) {
        //node.visit(this);
    }

    @Override
    public void visitNodeOperadorMul(NodeOperadorMul node) {
        //node.visit(this);
    }

    @Override
    public void visitNodeOperadorRel(NodeOperadorRel node) {
        //node.visit(this);
    }

    @Override
    public void visitNodePrograma(NodePrograma node) throws ContextualError {
        if(node != null) {
            if(node.getCorpoDoPrograma() != null)
                node.getCorpoDoPrograma().visit(this);
        }    
    }

    @Override
    public void visitNodeTermo(NodeTermo node) throws ContextualError {
        if(node != null) {
            if(node.getOperador() != null) {
                node.getOperador().visit(this);
            }
            node.getFator().visit(this);
            if (node.getFatoresADireita() != null)
                node.getFatoresADireita().visit(this);
        }
    }

    @Override
    public void visitNodeTipoAgregado(NodeTipoAgregado node) {
         if(node != null) {
            node.getTipo().visit(this);
        }
    }

    @Override
    public void visitNodeTipoSimples(NodeTipoSimples node) {
        node.getNome();
    }
    
    public void check (NodePrograma p) throws ContextualError {
     System.out.println ("\n---> Iniciando identificacao de nomes");
     p.visit (this);
   }
    
}
