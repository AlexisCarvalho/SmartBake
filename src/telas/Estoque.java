package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.FuncaoEstoque;
import controller.FuncaoGerente;
import model.bean.Produto;

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
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Estoque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Exit Exit;
	private JTextField TxtGenerate;
	private JButton CaixaButton;
	private JButton EstoqueButton;
	private JButton FuncButton;
	private JButton GVButton;
	private Caixa Caixa;
	private Funcionarios Funcionarios;
	private GVendas GVendas;
	private HomeGerente HomeGerente;
	private JButton BackButton;
	private JButton MinButton;
	private JButton ExitButton;
	private JTextField idProduto;
	private JTextField valorProduto;
	private JTextField nomeProduto;
	private JTextField descricaoProduto;
	private JScrollPane scrollPane;
	private JTable table;
	private FuncaoGerente fGerente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoque frame = new Estoque();
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
	public Estoque() {
		fGerente = new FuncaoGerente();
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
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				BackButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BackButton2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				BackButton.setIcon(new ImageIcon(HomeGerente.class.getResource("/icons/BackButton.png")));
			}
		});
		BackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeGerente = new HomeGerente();
				HomeGerente.setVisible(true);

				Estoque.this.dispose();
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
				Estoque.this.setExtendedState(ICONIFIED);
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(389, 100, 842, 441);
		contentPane.add(scrollPane);
		
		Produto produto = new Produto();
		FuncaoEstoque fEstoque = new FuncaoEstoque();
		
		fEstoque.openConn();
		
		DefaultTableModel modelo = new DefaultTableModel();
		table = new JTable(modelo);
		
		modelo.addColumn("ID");
		modelo.addColumn("VALOR");
		modelo.addColumn("NOME");
		modelo.addColumn("DESCRIÇÃO");
		
		fEstoque.gerarTabela(produto, modelo);
		
		try {
			fEstoque.closeConn();
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
		}
		
		scrollPane.setViewportView(table);
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

				Estoque.this.dispose();
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
					Estoque.this.dispose();
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
					Estoque.this.dispose();
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

		idProduto = new JTextField();
		idProduto.setSelectionColor(new Color(255, 195, 111));
		idProduto.setForeground(new Color(114, 117, 118));
		idProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		idProduto.setBorder(null);
		idProduto.setBounds(389, 590, 209, 30);
		contentPane.add(idProduto);
		idProduto.setColumns(10);

		valorProduto = new JTextField();
		valorProduto.setSelectionColor(new Color(255, 195, 111));
		valorProduto.setForeground(new Color(114, 117, 118));
		valorProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		valorProduto.setBorder(null);
		valorProduto.setColumns(10);
		valorProduto.setBounds(607, 590, 193, 30);
		contentPane.add(valorProduto);

		nomeProduto = new JTextField();
		nomeProduto.setSelectionColor(new Color(255, 195, 111));
		nomeProduto.setForeground(new Color(114, 117, 118));
		nomeProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		nomeProduto.setBorder(null);
		nomeProduto.setColumns(10);
		nomeProduto.setBounds(813, 590, 193, 30);
		contentPane.add(nomeProduto);

		descricaoProduto = new JTextField();
		descricaoProduto.setSelectionColor(new Color(255, 195, 111));
		descricaoProduto.setForeground(new Color(114, 117, 118));
		descricaoProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		descricaoProduto.setBorder(null);
		descricaoProduto.setColumns(10);
		descricaoProduto.setBounds(1019, 590, 200, 30);
		contentPane.add(descricaoProduto);

		JButton Registrar = new JButton("");
		Registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGerente.openConn();
				try {
				fGerente.cadastrarProduto(valorProduto.getText().replace(",", "."), nomeProduto.getText(), descricaoProduto.getText());
				try {
					fGerente.closeConn();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				fEstoque.openConn();
				fEstoque.gerarTabela(produto, modelo);
				try {
					fEstoque.closeConn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				} catch (IllegalArgumentException edr) {
					System.err.println(edr.getMessage());
				}
			}
		});
		Registrar.setPressedIcon(new ImageIcon(Estoque.class.getResource("/icons/Buttons/BRegistrarPressed.png")));
		Registrar.setIcon(new ImageIcon(Estoque.class.getResource("/icons/Buttons/BRegistrar.png")));
		Registrar.setFocusTraversalKeysEnabled(false);
		Registrar.setFocusPainted(false);
		Registrar.setContentAreaFilled(false);
		Registrar.setBorderPainted(false);
		Registrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Registrar.setBorder(null);
		Registrar.setBounds(424, 634, 220, 60);
		contentPane.add(Registrar);

		JButton Atualizar = new JButton("");
		Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto teste = new Produto();
				fEstoque.openConn();
				try {
				fEstoque.atualizarTudo(Integer.valueOf(idProduto.getText()), valorProduto.getText(), nomeProduto.getText(), descricaoProduto.getText());
				
				fEstoque.gerarTabela(teste, modelo);
				try {
					fEstoque.closeConn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				} catch(IllegalArgumentException ede) {
					System.err.println("Campos vazios");
				}
			}
		});
		Atualizar.setPressedIcon(new ImageIcon(Estoque.class.getResource("/icons/Buttons/BAtualizarPressed.png")));
		Atualizar.setIcon(new ImageIcon(Estoque.class.getResource("/icons/Buttons/BAtualizar.png")));
		Atualizar.setFocusTraversalKeysEnabled(false);
		Atualizar.setFocusPainted(false);
		Atualizar.setContentAreaFilled(false);
		Atualizar.setBorderPainted(false);
		Atualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Atualizar.setBorder(null);
		Atualizar.setBounds(699, 634, 220, 60);
		contentPane.add(Atualizar);

		JButton Deletar = new JButton("");
		Deletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fGerente.openConn();
				try {
				fGerente.deletarProduto(Integer.valueOf(idProduto.getText()));
				try {
					fGerente.closeConn();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				fEstoque.openConn();
				fEstoque.gerarTabela(produto, modelo);
				try {
					fEstoque.closeConn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				} catch (IllegalArgumentException edr) {
					System.err.println(edr.getMessage());
				}
			}
		});
		Deletar.setPressedIcon(new ImageIcon(Estoque.class.getResource("/icons/Buttons/BDeletarPressed.png")));
		Deletar.setIcon(new ImageIcon(Estoque.class.getResource("/icons/Buttons/BDeletar.png")));
		Deletar.setFocusTraversalKeysEnabled(false);
		Deletar.setFocusPainted(false);
		Deletar.setContentAreaFilled(false);
		Deletar.setBorderPainted(false);
		Deletar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Deletar.setBorder(null);
		Deletar.setBounds(974, 634, 220, 60);
		contentPane.add(Deletar);

		JLabel EstoqueBg = new JLabel("");
		EstoqueBg.setIcon(new ImageIcon(Estoque.class.getResource("/icons/Bg/EstoqueBg.png")));
		EstoqueBg.setBounds(0, 0, 1280, 720);
		contentPane.add(EstoqueBg);

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
