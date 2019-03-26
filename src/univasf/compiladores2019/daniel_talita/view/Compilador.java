package univasf.compiladores2019.daniel_talita.view;

import univasf.compiladores2019.daniel_talita.compilation_errors.ContextualError;
import univasf.compiladores2019.daniel_talita.compilation_errors.LexicalError;
import univasf.compiladores2019.daniel_talita.compilation_errors.SintaxeError;
import univasf.compiladores2019.daniel_talita.front_end.Checker;
import univasf.compiladores2019.daniel_talita.front_end.Parser;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodePrograma;
import univasf.compiladores2019.daniel_talita.util.InterfaceGraficaUtil;
import univasf.compiladores2019.daniel_talita.visitor.Printer;

public class Compilador {

	private StringBuilder avisosDeCompilacao;
	private StringBuilder ast;
	private NodePrograma p;
	private Printer printer;
	private Checker checker;
	private Parser parser;

	public Compilador(String codigoFonte) {
		avisosDeCompilacao = new StringBuilder();
		ast = new StringBuilder();
		p = null;
		printer = new Printer();
		checker = new Checker();
		parser = new Parser(codigoFonte);
	}

	public SaidaDoCompilador compilar() {
		SaidaDoCompilador saida = new SaidaDoCompilador();
		try {

			p = parser.parse();

			avisosDeCompilacao.append(InterfaceGraficaUtil.SUCESSO_ANALISE_LEXICA_MSG).append("\n")
					.append(InterfaceGraficaUtil.SUCESSO_ANALISE_SINTATICA_MSG);

			ast.append(printer.print(p));
			checker.check(p);

			avisosDeCompilacao.append(InterfaceGraficaUtil.SUCESSO_ANALISE_CONTEXTO_MSG);

		} catch (LexicalError e) {
			avisosDeCompilacao.append(InterfaceGraficaUtil.FALHA_ANALISE_LEXICA_MSG).append("\n\n")
					.append(e.getMessage());
			e.printStackTrace();
		} catch (SintaxeError e) {
			avisosDeCompilacao.append(InterfaceGraficaUtil.FALHA_ANALISE_SINTATICA_MSG).append("\n\n")
					.append(e.getMessage());
			e.printStackTrace();
		} catch (ContextualError e) {
			avisosDeCompilacao.append(InterfaceGraficaUtil.FALHA_ANALISE_CONTEXTO_MSG).append("\n\n")
					.append(e.getMessage());
			e.printStackTrace();
		}

		saida.setAvisosDaCompilacao(avisosDeCompilacao.toString());
		saida.setArvoreDeSintaxeAbstrata(ast.toString());

		return saida;
	}

}
