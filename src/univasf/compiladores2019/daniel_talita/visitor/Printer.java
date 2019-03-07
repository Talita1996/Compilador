package univasf.compiladores2019.daniel_talita.visitor;

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

public class Printer implements Visitor{
    int i=0;

   void indent() {
      for (int j=0; j<i; j++) 
         System.out.print ("|");
   }

    @Override
    public void visitNodeComandoAtribuicao(NodeComandoAtribuicao node) {
       
    }

    @Override
    public void visitNodeComandoComposto(NodeComandoComposto node) {
    }

    @Override
    public void visitNodeComandoCondicional(NodeComandoCondicional node) {
    }

    @Override
    public void visitNodeComandoIterativo(NodeComandoIterativo node) {
    }

    @Override
    public void visitNodeCorpo(NodeCorpo node) {
    }

    @Override
    public void visitNodeDeclaracao(NodeDeclaracao node) {
    }

    @Override
    public void visitNodeExpressao(NodeExpressao node) {
    }

    @Override
    public void visitNodeExpressaoSimples(NodeExpressaoSimples node) {
    }

    @Override
    public void visitNodeFator(NodeFator node) {
    }

    @Override
    public void visitNodeIdentificador(NodeIdentificador node) {
    }

    @Override
    public void visitNodeLiteralBooleano(NodeLiteralBooleano node) {
    }

    @Override
    public void visitNodeLiteralFloat(NodeLiteralFloat node) {
    }

    @Override
    public void visitNodeLiteralInteiro(NodeLiteralInteiro node) {
    }

    @Override
    public void visitNodeOperadorAd(NodeOperadorAd node) {
    }

    @Override
    public void visitNodeOperadorMul(NodeOperadorMul node) {
    }

    @Override
    public void visitNodeOperadorRel(NodeOperadorRel node) {
    }

    @Override
    public void visitNodePrograma(NodePrograma node) {
    }

    @Override
    public void visitNodeTermo(NodeTermo node) {
    }

    @Override
    public void visitNodeTipoAgregado(NodeTipoAgregado node) {
    }

    @Override
    public void visitNodeTipoSimples(NodeTipoSimples node) {
    }
    
}
