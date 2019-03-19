package univasf.compiladores2019.daniel_talita.view;

import univasf.compiladores2019.daniel_talita.compilation_errors.LexicalError;
import univasf.compiladores2019.daniel_talita.compilation_errors.SintaxeError;
import univasf.compiladores2019.daniel_talita.front_end.Checker;
import univasf.compiladores2019.daniel_talita.front_end.Parser;
import univasf.compiladores2019.daniel_talita.front_end.AST.NodePrograma;
import univasf.compiladores2019.daniel_talita.util.InterfaceGraficaUtil;
import univasf.compiladores2019.daniel_talita.visitor.Printer;

public class Compilador {

	private StringBuilder saidaDoCompilador;
	private NodePrograma p;
	private Printer printer;
	private Checker checker;
	private Parser parser;

	public Compilador(String codigoFonte) {
		saidaDoCompilador = new StringBuilder();
		p = null;
		printer = new Printer();
		checker = new Checker();
		parser = new Parser(codigoFonte);
	}

	public String compilar() {
		try {
			p = parser.parse();

			saidaDoCompilador.append(InterfaceGraficaUtil.SUCESSO_ANALISE_LEXICA_MSG).append("\n")
					.append(InterfaceGraficaUtil.SUCESSO_ANALISE_SINTATICA_MSG);

		} catch (LexicalError e) {
			saidaDoCompilador.append(InterfaceGraficaUtil.FALHA_ANALISE_LEXICA_MSG).append("\n\n")
					.append(e.getMessage());
			e.printStackTrace();
		} catch (SintaxeError e) {
			saidaDoCompilador.append(InterfaceGraficaUtil.FALHA_ANALISE_SINTATICA_MSG).append("\n\n")
					.append(e.getMessage());
			e.printStackTrace();
		}
		printer.print(p);
		checker.check(p);

		return saidaDoCompilador.toString();
	}

}
