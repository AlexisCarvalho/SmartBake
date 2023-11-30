package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controller.FuncaoLogin;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pwdSenhausuario;
	private Exit Exit;
	private HomeGerente HomeGerente;
	private FuncaoLogin fLogin;
	public static int nivel;
	public static String loginUsr;
	private JButton MinButton;
	private JButton BackButton;
	private JButton ExitButton;
	private JTextField pwdSenhaVT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setName("frame1");
		setResizable(false);
		fLogin = new FuncaoLogin();
		setMinimumSize(new Dimension(1280, 720));
		setUndecorated(true);
		setBounds(0, 0, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsuario.setText("");
				;
			}
		});

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
				Login.this.setExtendedState(ICONIFIED);
			}
		});

		pwdSenhausuario = new JPasswordField();
		pwdSenhausuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdSenhausuario.setText("");
			}
		});

		JButton Eye = new JButton("");
		Eye.setIcon(new ImageIcon(Login.class.getResource("/icons/Eye.png")));
		Eye.setFocusTraversalKeysEnabled(false);
		Eye.setFocusPainted(false);
		Eye.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Eye.setContentAreaFilled(false);
		Eye.setBorderPainted(false);
		Eye.setBorder(null);
		Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pwdSenhaVT.setOpaque(true);
				pwdSenhausuario.setOpaque(false);
				pwdSenhaVT.setText(pwdSenhausuario.getText());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pwdSenhaVT.setOpaque(false);
				pwdSenhausuario.setOpaque(true);
				pwdSenhaVT.setText("");
			}
		});
		Eye.setBounds(489, 343, 31, 35);
		contentPane.add(Eye);
		pwdSenhausuario.setText("SenhaUsuario");
		pwdSenhausuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdSenhausuario.setForeground(new Color(114, 117, 118));
		pwdSenhausuario.setSelectionColor(new Color(255, 195, 111));
		pwdSenhausuario.setCaretColor(new Color(114, 117, 118));
		pwdSenhausuario.setBorder(null);
		pwdSenhausuario.setBounds(190, 343, 300, 35);
		contentPane.add(pwdSenhausuario);

		pwdSenhaVT = new JTextField();
		pwdSenhaVT.setForeground(new Color(114, 117, 118));
		pwdSenhaVT.setFont(new Font("Tahoma", Font.BOLD, 24));
		pwdSenhaVT.setSelectionColor(new Color(255, 195, 111));
		pwdSenhaVT.setOpaque(false);
		pwdSenhaVT.setBorder(null);
		pwdSenhaVT.setAutoscrolls(false);
		pwdSenhaVT.setBounds(190, 343, 300, 35);
		contentPane.add(pwdSenhaVT);
		pwdSenhaVT.setColumns(10);
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
		ExitButton.setIcon(new ImageIcon(Login.class.getResource("/icons/ExitButton.png")));
		ExitButton.setBorder(null);
		ExitButton.setContentAreaFilled(false);
		ExitButton.setBorderPainted(false);
		ExitButton.setBounds(1239, 0, 31, 31);
		contentPane.add(ExitButton);
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtUsuario.setForeground(new Color(114, 117, 118));
		txtUsuario.setText("Usuário");
		txtUsuario.setSelectionColor(new Color(255, 195, 111));
		txtUsuario.setCaretColor(new Color(114, 117, 118));
		txtUsuario.setBorder(null);
		txtUsuario.setBounds(190, 244, 330, 35);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JButton EnterButton = new JButton("");
		EnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fLogin.openConn();
					fLogin.autenticar(txtUsuario.getText(), pwdSenhausuario.getText());

					nivel = fLogin.getNivel();
					loginUsr = fLogin.getLogin();

					fLogin.closeConn();

					HomeGerente = new HomeGerente();
					HomeGerente.setVisible(true);

					Login.this.dispose();

				} catch (IllegalArgumentException loginFalho) {
					System.err.println(loginFalho.getMessage());
				} catch (SQLException e1) {
					System.err.println(e1.getMessage());
				}
			}
		});
		EnterButton.setPressedIcon(new ImageIcon(Login.class.getResource("/icons/Buttons/BEntrarPressed.png")));
		EnterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EnterButton.setContentAreaFilled(false);
		EnterButton.setBorderPainted(false);
		EnterButton.setBorder(null);
		EnterButton.setIcon(new ImageIcon(Login.class.getResource("/icons/Buttons/BEntrar.png")));
		EnterButton.setBounds(206, 430, 262, 64);
		contentPane.add(EnterButton);

		JLabel LoginBG = new JLabel("");
		LoginBG.setIcon(new ImageIcon(Login.class.getResource("/icons/Bg/LoginBg.png")));
		LoginBG.setBounds(0, 0, 1280, 720);
		contentPane.add(LoginBG);
	}
}
