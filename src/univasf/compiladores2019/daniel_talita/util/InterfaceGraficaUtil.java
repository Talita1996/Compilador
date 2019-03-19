package univasf.compiladores2019.daniel_talita.util;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

abstract public class InterfaceGraficaUtil {

	public static String SUCESSO_ANALISE_LEXICA_MSG = "<p><font color=\"green\">Analise léxica concluida com sucesso!</font>  <resources alt=\"yes\" height=\"20\" src=\"https://jeitodigital.com.br/sistema/ckeditor/plugins/smiley/images/thumbs_up.gif\" title=\"yes\" width=\"20\" /></p>";
	public static String FALHA_ANALISE_LEXICA_MSG = "<p><font color=\"red\">Falha na analise léxica!</font>  <resources alt=\"yes\" height=\"20\" src=\"https://jeitodigital.com.br/sistema/ckeditor/plugins/smiley/images/thumbs_down.gif\" title=\"yes\" width=\"20\" /></p>";
	public static String SUCESSO_ANALISE_SINTATICA_MSG = "<p><font color=\"green\">Analise sintática concluida com sucesso!</font>  <resources alt=\"yes\" height=\"20\" src=\"https://jeitodigital.com.br/sistema/ckeditor/plugins/smiley/images/thumbs_up.gif\" title=\"yes\" width=\"20\" /></p>";
	public static String FALHA_ANALISE_SINTATICA_MSG = "<p><font color=\"red\">Falha na analise sintatica!</font>  <resources alt=\"yes\" height=\"20\" src=\"https://jeitodigital.com.br/sistema/ckeditor/plugins/smiley/images/thumbs_down.gif\" title=\"yes\" width=\"20\" /></p>";

	public static JFileChooser inicializaFileChooser() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecione o arquivo com o codigo fonte a ser compilado");

		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");

		fileChooser.setFileFilter(filter);

		return fileChooser;
	}

	public static void imprimeTexto(JTextComponent textCompontent, String texto) {
		textCompontent.setText(texto);
	}

	public static void limparTexto(JTextComponent textComponent) {
		textComponent.setText("");
	}
}
