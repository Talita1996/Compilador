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
        if (node != null) {
            node.getId().visit(this);
            if (node.getDimensoesSeForAgregadoSimples() != null) {
                i++;
                indent();
                node.getDimensoesSeForAgregadoSimples().visit(this);
                i--;
            }
            System.out.print(":=");
            node.getValorAtribuido().visit(this);
        }
    }

    @Override
    public void visitNodeComandoComposto(NodeComandoComposto node) {
        if (node != null) {
            node.getNext().visit(this);
        }
    }

    @Override
    public void visitNodeComandoCondicional(NodeComandoCondicional node) {
        if (node != null) {
            i++;
            indent();
            node.getCondicao().visit(this);
            node.getComandoIf().visit(this);
            node.getComandoElse().visit(this);
            i--;
        }
    }

    @Override
    public void visitNodeComandoIterativo(NodeComandoIterativo node) {
        if (node != null) {
            i++;
            indent();
            node.getExpressao().visit(this);
            node.getComando().visit(this);
            i--;
        }
    }

    @Override
    public void visitNodeCorpo(NodeCorpo node) {
        if (node != null) {
            if (node.getDeclaracoes() != null) {
                node.getDeclaracoes().visit(this);
            }
            if (node.getComandos() != null){
                node.getComandos().visit(this);
            }
        }
    }

    @Override
    public void visitNodeDeclaracao(NodeDeclaracao node) {
        if (node != null) {
            node.getName().visit(this);
            if (node.getNext() != null) {
                System.out.println(",");
                i++;
                indent();
                node.getNext().visit(this);
                i--;
            }
            if ( node.getTipoDaVariavel() != null){
                System.out.print(":");
                node.getTipoDaVariavel().visit(this);
            }
        }
    }

    @Override
    public void visitNodeExpressao(NodeExpressao node) {
        if (node != null) {
            i++;
            indent();
            node.getExpressaoEsquerda().visit(this);
            i--;
            if (node.getOperador() != null) {
                i++;
                indent();
                node.getOperador().visit(this);
                node.getExpressaoDireita().visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitNodeExpressaoSimples(NodeExpressaoSimples node) {
        if (node != null) {
            i++;
            indent();
            node.getTermo().visit(this);
            i--;
            if (node.getOperador() != null) {
                i++;
                indent();
                node.getOperador().visit(this);
                node.getTermosADireita().visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitNodeFator(NodeFator node) {
         if (node != null) {
            if (node.getId() != null) {
                System.out.print(node.getId().spelling);
                if (node.getExpressoes() != null){
                    i++;
                    indent();
                    System.out.print("[");
                    node.getExpressoes().visit(this);
                    System.out.print("]");
                    i--;
                }
            } 
            if (node.getLiteral() != null) {
                i++;
                indent();
                node.getLiteral().visit(this);
                i--;
            }
            if (node.getExpressoes() != null) {
                i++;
                indent();
                System.out.print("(");
                node.getExpressoes().visit(this);
                System.out.print(")");
                i--;
            }
        }
    }

    @Override
    public void visitNodeIdentificador(NodeIdentificador node) {
        if (node != null)
            System.out.print(node.spelling);
    }

    @Override
    public void visitNodeLiteralBooleano(NodeLiteralBooleano node){
        if (node != null)
            System.out.print(node.getValor());
    }

    @Override
    public void visitNodeLiteralFloat(NodeLiteralFloat node) {
        if (node != null)
            System.out.print(node.getValor());
    }

    @Override
    public void visitNodeLiteralInteiro(NodeLiteralInteiro node) {
        if (node != null)
            System.out.print(node.getValor());
    }

    @Override
    public void visitNodeOperadorAd(NodeOperadorAd node) {
        if (node != null)
            System.out.println(node.spelling);
    }

    @Override
    public void visitNodeOperadorMul(NodeOperadorMul node) {
        if (node != null)
            System.out.println(node.spelling);
    }

    @Override
    public void visitNodeOperadorRel(NodeOperadorRel node) {
        if (node != null)
            System.out.println(node.spelling);
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
        if(node != null) {
            if (node.getFator() != null)
                node.getFator().visit(this);
            if(node.getOperador() != null) {
                i++;
                indent();
                node.getOperador().visit(this);
                node.getFatoresADireita().visit(this);
                i--;
            }
        }
    }

    @Override
    public void visitNodeTipoAgregado(NodeTipoAgregado node) {
        if(node != null) {
            System.out.println ("array [" +node.getLowerIndex()+ ".." +node.getHigherIndex()+ "] of ");
            i++;
            indent();
            node.getTipo().visit(this);
            i--;
        }
    }

    @Override
    public void visitNodeTipoSimples(NodeTipoSimples node) {
        if(node != null) {
            System.out.println (node.getNome());
        }
    }
    
    public void print (NodePrograma p) {
      System.out.println ("---> Iniciando impressao da arvore");
      p.visit (this);
   }
}
