package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import controller.FuncaoCaixa;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import model.bean.ProdutoVendido;
import util.RetornarTroco;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Caixa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Exit Exit;
	private JTextField CodCliente;
	private JTextField CodItem;
	private JTextField Qnt;
	private JTextField ValorT;
	private JTextField ValorD;
	private JTextField Msg;
	private FuncaoCaixa fc;
	private BigDecimal totalDesconto;
	private HomeGerente HomeGerente;
	private JButton BackButton;
	private JButton MinButton;
	private JButton ExitButton;
	private JTextField ValorPago;
	private JTextField Troco;
	private BigDecimal valorJaPago;
	private JTextArea sumario;
	public static String cpfCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Caixa frame = new Caixa();
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
	public Caixa() {
		fc = new FuncaoCaixa();
		totalDesconto = new BigDecimal("0");
		valorJaPago = new BigDecimal("0");
		setMinimumSize(new Dimension(1280, 720));
		setResizable(false);
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

				Caixa.this.dispose();
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
				Caixa.this.setExtendedState(ICONIFIED);
			}
		});

		JButton PayButton = new JButton("");
		PayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fc.openConn();
				valorJaPago = valorJaPago.add(new BigDecimal(ValorPago.getText().replace(",", ".")));

				try {

					fc.receber(ValorPago.getText().replace(",", "."));
					ValorT.setText(fc.valorAReceber(false).replace(".", ","));
					ValorPago.setText("");
					Troco.setText("");
				} catch (RetornarTroco rt) {

					ValorT.setText("0.0");

					Troco.setText(fc.valorTroco().replace(".", ","));

					fc.sendToDB();
					fc.atualizarDescontoDeTodos();

					try {
						fc.closeConn();

						totalDesconto = new BigDecimal("0");
						valorJaPago = new BigDecimal("0");

						CodCliente.setText("");
						CodItem.setText("");
						Qnt.setText("");
						ValorT.setText("");
						ValorD.setText("");
						Msg.setText("");
						ValorPago.setText("");
						sumario.setText("");

						fc = new FuncaoCaixa();

					} catch (SQLException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		});

		PayButton.setPressedIcon(new ImageIcon(Caixa.class.getResource("/icons/Buttons/BPagarPressed.png")));
		PayButton.setIcon(new ImageIcon(Caixa.class.getResource("/icons/Buttons/BPagar.png")));
		PayButton.setFocusTraversalKeysEnabled(false);
		PayButton.setFocusPainted(false);
		PayButton.setBorder(null);
		PayButton.setContentAreaFilled(false);
		PayButton.setBorderPainted(false);
		PayButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		PayButton.setBounds(95, 496, 260, 90);
		contentPane.add(PayButton);
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
		ExitButton.setIcon(new ImageIcon(Caixa.class.getResource("/icons/ExitButton.png")));
		ExitButton.setContentAreaFilled(false);
		ExitButton.setBorderPainted(false);
		ExitButton.setBorder(null);
		ExitButton.setBounds(1239, 0, 31, 31);
		contentPane.add(ExitButton);

		sumario = new JTextArea();
		sumario.setEditable(false);
		sumario.setLineWrap(true);
		sumario.setSelectionColor(new Color(255, 195, 111));
		sumario.setForeground(new Color(114, 117, 118));
		sumario.setFont(new Font("Tahoma", Font.BOLD, 15));
		sumario.setBounds(441, 187, 775, 463);
		contentPane.add(sumario);

		CodCliente = new JTextField();
		CodCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						fc.openConn();
						Msg.setText(fc.setCliente(CodCliente.getText()));
						fc.closeConn();
					} catch (IllegalArgumentException clienteExcep) {
						Msg.setText(clienteExcep.getMessage());
						cpfCliente = CodCliente.getText();
						RegistrarCliente rc = new RegistrarCliente();
						rc.setVisible(true);
					} catch (SQLException e1) {
						System.err.println(e1.getMessage());
					}
				}
			}
		});
		CodCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		CodCliente.setFont(new Font("Tahoma", Font.BOLD, 26));
		CodCliente.setSelectionColor(new Color(255, 195, 111));
		CodCliente.setForeground(new Color(114, 117, 118));
		CodCliente.setBorder(null);
		CodCliente.setBounds(68, 107, 315, 40);
		contentPane.add(CodCliente);
		CodCliente.setColumns(10);

		CodItem = new JTextField();
		CodItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CodItem.setText("");
			}
		});
		CodItem.setHorizontalAlignment(SwingConstants.RIGHT);
		CodItem.setFont(new Font("Tahoma", Font.BOLD, 23));
		CodItem.setSelectionColor(new Color(255, 195, 111));
		CodItem.setForeground(new Color(114, 117, 118));
		CodItem.setBorder(null);
		CodItem.setBounds(68, 206, 185, 33);
		contentPane.add(CodItem);
		CodItem.setColumns(10);

		Qnt = new JTextField();
		Qnt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Qnt.setText("");
			}
		});
		Qnt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					String produtos = "";
					String produto = "";
					int idSumario = 0;

					try {
						fc.openConn();
						fc.registrarProduto(Integer.valueOf(CodItem.getText()), Integer.valueOf(Qnt.getText()));
						fc.closeConn();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}

					totalDesconto = totalDesconto.add(new BigDecimal(fc.valorDescontado()));
					totalDesconto = totalDesconto.setScale(2, RoundingMode.HALF_EVEN);

					fc.valorAReceber(true);

					try {
						fc.receber(valorJaPago.toString());
					} catch (RetornarTroco e1) {
						System.err.println(e1.getMessage() + " - No local errado");
					}

					ValorT.setText(fc.valorAReceber(false).replace(".", ","));
					ValorD.setText(totalDesconto.toString().replace(".", ","));

					fc.openConn();
					for (ProdutoVendido a : fc.getProdutoVendidoList()) {
						idSumario++;
						produto = idSumario + "- " + a.getQuantidade() + "x  " + fc.getNomeProduto(a.getIdProduto())
								+ ": " + a.getValorPago().replace(".", ",") + "$\n";

						produtos = produtos + produto;
					}
					try {
						fc.closeConn();
					} catch (SQLException e1) {
						System.err.print(e1.getMessage());
					}

					sumario.setText(produtos);
				}
			}
		});
		Qnt.setHorizontalAlignment(SwingConstants.RIGHT);
		Qnt.setFont(new Font("Tahoma", Font.BOLD, 23));
		Qnt.setSelectionColor(new Color(255, 195, 111));
		Qnt.setForeground(new Color(114, 117, 118));
		Qnt.setBorder(null);
		Qnt.setBounds(298, 206, 85, 33);
		contentPane.add(Qnt);
		Qnt.setColumns(10);

		ValorT = new JTextField();
		ValorT.setOpaque(false);
		ValorT.setEditable(false);
		ValorT.setDisabledTextColor(new Color(255, 255, 255));
		ValorT.setHorizontalAlignment(SwingConstants.RIGHT);
		ValorT.setFont(new Font("Tahoma", Font.BOLD, 23));
		ValorT.setSelectionColor(new Color(255, 195, 111));
		ValorT.setForeground(new Color(114, 117, 118));
		ValorT.setBorder(null);
		ValorT.setBounds(68, 289, 315, 33);
		contentPane.add(ValorT);
		ValorT.setColumns(10);

		ValorD = new JTextField();
		ValorD.setEditable(false);
		ValorD.setOpaque(false);
		ValorD.setHorizontalAlignment(SwingConstants.RIGHT);
		ValorD.setFont(new Font("Tahoma", Font.BOLD, 23));
		ValorD.setSelectionColor(new Color(255, 195, 111));
		ValorD.setForeground(new Color(114, 117, 118));
		ValorD.setBorder(null);
		ValorD.setBounds(68, 371, 315, 33);
		contentPane.add(ValorD);
		ValorD.setColumns(10);

		Msg = new JTextField();
		Msg.setOpaque(false);
		Msg.setEditable(false);
		Msg.setFont(new Font("Tahoma", Font.BOLD, 26));
		Msg.setSelectionColor(new Color(255, 195, 111));
		Msg.setForeground(new Color(114, 117, 118));
		Msg.setBorder(null);
		Msg.setBounds(441, 80, 775, 65);
		contentPane.add(Msg);
		Msg.setColumns(10);

		ValorPago = new JTextField();
		ValorPago.setHorizontalAlignment(SwingConstants.RIGHT);
		ValorPago.setSelectionColor(new Color(255, 195, 111));
		ValorPago.setBorder(null);
		ValorPago.setForeground(new Color(114, 117, 118));
		ValorPago.setFont(new Font("Tahoma", Font.BOLD, 23));
		ValorPago.setBounds(68, 457, 315, 33);
		contentPane.add(ValorPago);
		ValorPago.setColumns(10);

		Troco = new JTextField();
		Troco.setHorizontalAlignment(SwingConstants.RIGHT);
		Troco.setOpaque(false);
		Troco.setEditable(false);
		Troco.setSelectionColor(new Color(255, 195, 111));
		Troco.setBorder(null);
		Troco.setForeground(new Color(114, 117, 118));
		Troco.setFont(new Font("Tahoma", Font.BOLD, 23));
		Troco.setBounds(68, 623, 315, 33);
		contentPane.add(Troco);
		Troco.setColumns(10);

		JLabel CaixaBg = new JLabel("");
		CaixaBg.setIcon(new ImageIcon(Caixa.class.getResource("/icons/Bg/CaixaBg.png")));
		CaixaBg.setBounds(0, 0, 1330, 720);
		contentPane.add(CaixaBg);
	}
}
