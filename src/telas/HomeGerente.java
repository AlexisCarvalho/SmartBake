package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class HomeGerente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Exit Exit;
	private JTextField TxtGenerate;
	private JButton CaixaButton;
	private JButton EstoqueButton;
	private JButton FuncButton;
	private JButton GVButton;
	private Caixa Caixa;
	private Estoque Estoque;
	private Funcionarios Funcionarios;
	private GVendas GVendas;
	private JButton BackButton;
	private JButton MinButton;
	private JButton ExitButton;
	private JTextField UserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeGerente frame = new HomeGerente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeGerente() {
		setResizable(false);
		setMinimumSize(new Dimension(1280, 720));
		setUndecorated(true);
		setBounds(0, 0, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ExitButton = new JButton("");
		ExitButton.setFocusPainted(false);
		ExitButton.setFocusTraversalKeysEnabled(false);
		ExitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ExitButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/ExitButton2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ExitButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/ExitButton.png")));
			}
		});
		ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit = new Exit();
				Exit.setVisible(true);
			}
		});

		BackButton = new JButton("");
		BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		MinButton = new JButton("");
		MinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				MinButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/MinButton2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				MinButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/MinButton.png")));
			}
		});
		MinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeGerente.this.setExtendedState(ICONIFIED);
			}
		});
		
		UserName = new JTextField();
		UserName.setText(Login.loginUsr);
		UserName.setHorizontalAlignment(SwingConstants.CENTER);
		UserName.setSelectionColor(new Color(255, 195, 111));
		UserName.setOpaque(false);
		UserName.setForeground(new Color(114, 117, 118));
		UserName.setFont(new Font("Tahoma", Font.BOLD, 90));
		UserName.setEnabled(false);
		UserName.setEditable(false);
		UserName.setDisabledTextColor(new Color(114, 117, 118));
		UserName.setColumns(10);
		UserName.setBorder(null);
		UserName.setBounds(675, 396, 516, 115);
		contentPane.add(UserName);
		MinButton.setIcon(new ImageIcon(Login.class.getResource("/icons/MinButton.png")));
		MinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MinButton.setFocusTraversalKeysEnabled(false);
		MinButton.setFocusPainted(false);
		MinButton.setContentAreaFilled(false);
		MinButton.setBorderPainted(false);
		MinButton.setBorder(null);
		MinButton.setBounds(1160, 0, 31, 31);
		contentPane.add(MinButton);
		BackButton.setFocusPainted(false);
		BackButton.setFocusTraversalKeysEnabled(false);
		BackButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BackButton.png")));
		BackButton.setContentAreaFilled(false);
		BackButton.setBorder(null);
		BackButton.setBorderPainted(false);
		BackButton.setBounds(1200, 0, 31, 31);
		contentPane.add(BackButton);

		TxtGenerate = new JTextField();
		TxtGenerate.setDisabledTextColor(new Color(114, 117, 118));
		TxtGenerate.setEnabled(false);
		TxtGenerate.setOpaque(false);
		TxtGenerate.setEditable(false);
		TxtGenerate.setSelectionColor(new Color(255, 195, 111));
		TxtGenerate.setForeground(new Color(114, 117, 118));
		TxtGenerate.setFont(new Font("Tahoma", Font.BOLD, 45));
		TxtGenerate.setBorder(null);
		TxtGenerate.setBounds(25, 25, 306, 55);
		contentPane.add(TxtGenerate);
		TxtGenerate.setColumns(10);
		ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ExitButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/ExitButton.png")));
		ExitButton.setBorderPainted(false);
		ExitButton.setBorder(null);
		ExitButton.setBounds(1239, 0, 31, 31);
		contentPane.add(ExitButton);

		CaixaButton = new JButton("");
		CaixaButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				CaixaButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BCaixa2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				CaixaButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BCaixa.png")));
			}
		});
		CaixaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CaixaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		CaixaButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BCaixa.png")));
		CaixaButton.setFocusPainted(false);
		CaixaButton.setFocusTraversalKeysEnabled(false);
		CaixaButton.setContentAreaFilled(false);
		CaixaButton.setBorderPainted(false);
		CaixaButton.setBorder(null);
		CaixaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Caixa = new Caixa();
				Caixa.setVisible(true);

				HomeGerente.this.dispose();
			}
		});
		CaixaButton.setBounds(0, 100, 270, 70);
		contentPane.add(CaixaButton);

		EstoqueButton = new JButton("");
		EstoqueButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				EstoqueButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BEstoque2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				EstoqueButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BEStoque.png")));
			}
		});
		EstoqueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoque = new Estoque();
				Estoque.setVisible(true);

				HomeGerente.this.dispose();
			}
		});
		EstoqueButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EstoqueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		EstoqueButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BEstoque.png")));
		EstoqueButton.setFocusPainted(false);
		EstoqueButton.setFocusTraversalKeysEnabled(false);
		EstoqueButton.setContentAreaFilled(false);
		EstoqueButton.setBorderPainted(false);
		EstoqueButton.setBorder(null);
		EstoqueButton.setBounds(0, 170, 270, 70);
		contentPane.add(EstoqueButton);

		FuncButton = new JButton("");
		FuncButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				FuncButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BFuncionarios2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				FuncButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BFuncionarios.png")));
			}
		});
		FuncButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (Login.nivel) {

				case 1:
					Funcionarios = new Funcionarios();
					Funcionarios.setVisible(true);
					HomeGerente.this.dispose();
					break;
				case 2:

					break;

				default:
					break;
				}
			}
		});
		FuncButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FuncButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		FuncButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BFuncionarios.png")));
		FuncButton.setFocusPainted(false);
		FuncButton.setFocusTraversalKeysEnabled(false);
		FuncButton.setContentAreaFilled(false);
		FuncButton.setBorderPainted(false);
		FuncButton.setBorder(null);
		FuncButton.setBounds(0, 240, 270, 70);
		contentPane.add(FuncButton);

		GVButton = new JButton("");
		GVButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				GVButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BGrafico2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				GVButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BGrafico.png")));
			}
		});
		GVButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (Login.nivel) {

				case 1:
					GVendas = new GVendas();
					GVendas.setVisible(true);
					HomeGerente.this.dispose();
					break;
				case 2:

					break;

				default:
					break;
				}
			}
		});
		GVButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GVButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		GVButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BGrafico.png")));
		GVButton.setFocusPainted(false);
		GVButton.setFocusTraversalKeysEnabled(false);
		GVButton.setContentAreaFilled(false);
		GVButton.setBorderPainted(false);
		GVButton.setBorder(null);
		GVButton.setBounds(0, 310, 270, 70);
		contentPane.add(GVButton);

		JLabel HomePageBg = new JLabel("");
		HomePageBg.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/Bg/HomePageBg.png")));
		HomePageBg.setBounds(0, 0, 1280, 720);
		contentPane.add(HomePageBg);

		switch (Login.nivel) {

		case 1:
			TxtGenerate.setText("Gerente");

			break;
		case 2:
			TxtGenerate.setText("Funcionário");

			break;

		default:
			break;
		}

	}
}
