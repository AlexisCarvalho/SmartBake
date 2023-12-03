package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

public class Exit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exit frame = new Exit();
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
	public Exit() {
		setFont(new Font("Tahoma", Font.BOLD, 13));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exit.class.getResource("/icons/Warning.png")));
		setMinimumSize(new Dimension(500, 300));
		setResizable(false);
		setTitle("Sair da aplicação?");
		setType(Type.POPUP);
		setBounds(390, 210, 516, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton ButtonSim = new JButton("");
		ButtonSim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ButtonSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ButtonSim.setIcon(new ImageIcon(Exit.class.getResource("/icons/Buttons/BSim.png")));
		ButtonSim.setPressedIcon(new ImageIcon(Exit.class.getResource("/icons/Buttons/BSimPressed.png")));
		ButtonSim.setSelected(true);
		ButtonSim.setFocusable(false);
		ButtonSim.setFocusTraversalKeysEnabled(false);
		ButtonSim.setFocusPainted(false);
		ButtonSim.setContentAreaFilled(false);
		ButtonSim.setBorderPainted(false);
		ButtonSim.setBorder(null);
		ButtonSim.setBounds(10, 218, 138, 60);
		contentPane.add(ButtonSim);

		JButton ButtonNao = new JButton("");
		ButtonNao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ButtonNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit.this.dispose();
			}
		});
		ButtonNao.setPressedIcon(new ImageIcon(Exit.class.getResource("/icons/Buttons/BNaoPressed.png")));
		ButtonNao.setIcon(new ImageIcon(Exit.class.getResource("/icons/Buttons/BNao.png")));
		ButtonNao.setFocusable(false);
		ButtonNao.setFocusTraversalKeysEnabled(false);
		ButtonNao.setFocusPainted(false);
		ButtonNao.setContentAreaFilled(false);
		ButtonNao.setBorderPainted(false);
		ButtonNao.setBorder(null);
		ButtonNao.setSelected(true);
		ButtonNao.setBounds(354, 218, 138, 60);
		contentPane.add(ButtonNao);

		JLabel ExitBg = new JLabel("");
		ExitBg.setIcon(new ImageIcon(Exit.class.getResource("/icons/Bg/ExitBg.png")));
		ExitBg.setBounds(0, 0, 500, 300);
		contentPane.add(ExitBg);
	}
}
