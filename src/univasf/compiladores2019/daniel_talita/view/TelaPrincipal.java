package univasf.compiladores2019.daniel_talita.view;

import static univasf.compiladores2019.daniel_talita.util.InterfaceGraficaUtil.imprimeTexto;
import static univasf.compiladores2019.daniel_talita.util.InterfaceGraficaUtil.inicializaFileChooser;
import static univasf.compiladores2019.daniel_talita.util.InterfaceGraficaUtil.limparTexto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class TelaPrincipal {

	private JFrame frmCompilador;
	private JTextField textDiretorioArquivo;

	private StringBuilder codigoFonte;
	private StringBuilder saidaDoCompilador;
	private Compilador compilador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmCompilador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCompilador = new JFrame();
		frmCompilador.getContentPane().setBackground(new Color(255, 255, 204));
		frmCompilador.setBackground(new Color(204, 255, 204));
		frmCompilador.setForeground(new Color(204, 255, 102));
		frmCompilador.setIconImage(
				Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/img/iconMenuAplicacao.png")));
		frmCompilador.setResizable(false);
		frmCompilador.setTitle("CompiladorOriginal Mini-Pascal (Daniel & Talita 2019.1)");
		frmCompilador.setBounds(100, 100, 800, 600);
		frmCompilador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCompilador.getContentPane().setLayout(null);

		textDiretorioArquivo = new JTextField();
		textDiretorioArquivo.setBounds(250, 130, 510, 30);
		frmCompilador.getContentPane().add(textDiretorioArquivo);
		textDiretorioArquivo.setColumns(10);

		JLabel lblCodigoFonte = new JLabel("C\u00F3digo Fonte");
		lblCodigoFonte.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCodigoFonte.setBounds(150, 172, 110, 17);
		frmCompilador.getContentPane().add(lblCodigoFonte);

		JLabel lblSaidaCompilador = new JLabel("Sa\u00EDda do compilador");
		lblSaidaCompilador.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSaidaCompilador.setBounds(507, 172, 166, 15);
		frmCompilador.getContentPane().add(lblSaidaCompilador);

		JTextPane textCodigoFonte = new JTextPane();
		textCodigoFonte.setEditable(false);
		JScrollPane scrollCodigoFonte = new JScrollPane(textCodigoFonte, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollCodigoFonte.setBounds(40, 190, 340, 320);
		scrollCodigoFonte.setAutoscrolls(true);
		frmCompilador.getContentPane().add(scrollCodigoFonte);

		JTextPane textSaidaCompilador = new JTextPane();
		textSaidaCompilador.setEditable(false);
		textSaidaCompilador.setContentType("text/html");
		JScrollPane scrollSaidaCompilador = new JScrollPane(textSaidaCompilador,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollSaidaCompilador.setBounds(420, 190, 340, 120);
		scrollSaidaCompilador.setAutoscrolls(true);
		frmCompilador.getContentPane().add(scrollSaidaCompilador);

		JTextPane textAST = new JTextPane();
		textAST.setEditable(false);
		JScrollPane scrollAST = new JScrollPane(textAST, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollAST.setBounds(420, 310, 340, 200);
		frmCompilador.getContentPane().add(scrollAST);

		JButton btnAbrirArquivo = new JButton("Selecionar arquivo");
		btnAbrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				codigoFonte = new StringBuilder();
				saidaDoCompilador = new StringBuilder();

				limparTexto(textDiretorioArquivo);
				limparTexto(textCodigoFonte);
				limparTexto(textSaidaCompilador);
				limparTexto(textAST);

				JFileChooser fileChooser = inicializaFileChooser();
				int respostaDoFileChooser = fileChooser.showOpenDialog(null);

				File arquivoCodigoFonte;

				if (respostaDoFileChooser == JFileChooser.APPROVE_OPTION) {

					limparTexto(textCodigoFonte);

					arquivoCodigoFonte = fileChooser.getSelectedFile();
					imprimeTexto(textDiretorioArquivo, arquivoCodigoFonte.getPath());

					int linhas = 2;
					StringBuilder codigoFonteEnumerado = new StringBuilder();
					codigoFonteEnumerado.append("1|");

					try {

						BufferedReader leitorDeCaracteres = new BufferedReader(
								new FileReader(arquivoCodigoFonte.getPath()));
						int aux;
						while ((aux = leitorDeCaracteres.read()) != -1) {
							codigoFonte.append((char) aux);
							codigoFonteEnumerado.append((char) aux);
							if ((char) aux == 10) {
								codigoFonteEnumerado.append((char) aux).append(linhas + "|");
								linhas++;
							}
						}

						codigoFonte.append((char) '\000');
						leitorDeCaracteres.close();

					} catch (FileNotFoundException ex) {
						saidaDoCompilador.append("ERRO!\nO arquivo indicado não foi localizado!");
					} catch (IOException ex) {
						saidaDoCompilador.append("ERRO!\nFalha na leitura do arquivo indicado!");
					}

					imprimeTexto(textCodigoFonte, codigoFonteEnumerado.toString());
					imprimeTexto(textSaidaCompilador, saidaDoCompilador.toString());
				}

			}
		});
		btnAbrirArquivo.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/img/openfolderIcon.png")));
		btnAbrirArquivo.setBounds(40, 130, 180, 30);
		frmCompilador.getContentPane().add(btnAbrirArquivo);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				codigoFonte = new StringBuilder();
				saidaDoCompilador = new StringBuilder();

				limparTexto(textDiretorioArquivo);
				limparTexto(textCodigoFonte);
				limparTexto(textSaidaCompilador);
				limparTexto(textAST);
			}
		});
		btnLimpar.setBounds(135, 530, 180, 30);
		frmCompilador.getContentPane().add(btnLimpar);

		JButton btnCompilar = new JButton("Compilar");
		btnCompilar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/img/compiler.png")));
		btnCompilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (codigoFonte.length() == 0) {
					imprimeTexto(textSaidaCompilador, "ERRO! Nenhum arquivo com código fonte foi carregado");
				} else {
					compilador = new Compilador(codigoFonte.toString());
					SaidaDoCompilador saidaDoCompilador = compilador.compilar();
					imprimeTexto(textSaidaCompilador, saidaDoCompilador.getAvisosDaCompilacao());
					imprimeTexto(textAST, saidaDoCompilador.getArvoreDeSintaxeAbstrata());
				}
			}
		});
		btnCompilar.setBounds(507, 530, 180, 30);
		frmCompilador.getContentPane().add(btnCompilar);
	}
}
