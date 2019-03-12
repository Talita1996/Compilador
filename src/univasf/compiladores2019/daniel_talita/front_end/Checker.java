package univasf.compiladores2019.daniel_talita.front_end;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void visitNodeComandoAtribuicao(NodeComandoAtribuicao node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeComandoComposto(NodeComandoComposto node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeComandoCondicional(NodeComandoCondicional node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeComandoIterativo(NodeComandoIterativo node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeCorpo(NodeCorpo node) {
        if(node != null) {
            if(node.getDeclaracoes() != null)
                node.getDeclaracoes().visit(this);
        } 
    }

    @Override
    public void visitNodeDeclaracao(NodeDeclaracao node) {
        if (node != null){
            Variavel var = new Variavel(node.getName().spelling, node);
            try {
                this.t.enter(var);
            } catch (ContextualError e) {
                System.out.println("Erro de contexto! "+e.getMessage());
            }
            if (node.getNext() != null){
                node.getNext().visit(this);
            }
        }
    }

    @Override
    public void visitNodeExpressao(NodeExpressao node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeExpressaoSimples(NodeExpressaoSimples node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeFator(NodeFator node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeIdentificador(NodeIdentificador node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeLiteralBooleano(NodeLiteralBooleano node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeLiteralFloat(NodeLiteralFloat node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeLiteralInteiro(NodeLiteralInteiro node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeOperadorAd(NodeOperadorAd node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeOperadorMul(NodeOperadorMul node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeOperadorRel(NodeOperadorRel node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodePrograma(NodePrograma node) {
        if(node != null) {
            if(node.getCorpoDoPrograma() != null)
                node.getCorpoDoPrograma().visit(this);
        }    
    }

    @Override
    public void visitNodeTermo(NodeTermo node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeTipoAgregado(NodeTipoAgregado node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visitNodeTipoSimples(NodeTipoSimples node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void check (NodePrograma p) {
     System.out.println ("---> Iniciando identificacao de nomes");
     p.visit (this);
   }
    
}
