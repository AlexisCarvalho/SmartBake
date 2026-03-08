package telas;

import controller.FuncaoGerente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class RegistrarCliente extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JTextField nomeCliente;
    private final FuncaoGerente fGer;

    /**
     * Create the frame.
     */
    public RegistrarCliente() {
        fGer = new FuncaoGerente();
        setType(Type.POPUP);
        setBounds(390, 210, 516, 339);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        nomeCliente = new JTextField();
        nomeCliente.setSelectionColor(new Color(255, 195, 111));
        nomeCliente.setForeground(new Color(114, 117, 118));
        nomeCliente.setFont(new Font("Tahoma", Font.BOLD, 24));
        nomeCliente.setColumns(10);
        nomeCliente.setCaretColor(new Color(114, 117, 118));
        nomeCliente.setBorder(null);
        nomeCliente.setBounds(86, 84, 330, 49);
        contentPane.add(nomeCliente);

        JButton Registrar = new JButton("");
        Registrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    fGer.openConn();

                    fGer.cadastrarCliente(Caixa.cpfCliente, nomeCliente.getText(), 0);
                } catch (IllegalArgumentException ile) {
                    System.err.println(ile.getMessage());
                }

                fGer.closeConn();

                try {
                    Thread.sleep(500);
                    RegistrarCliente.this.dispose();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        Registrar.setPressedIcon(
                new ImageIcon(RegistrarCliente.class.getResource("/icons/Buttons/BRegistrarPressed.png")));
        Registrar.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/icons/Buttons/BRegistrar.png")));
        Registrar.setFocusTraversalKeysEnabled(false);
        Registrar.setFocusPainted(false);
        Registrar.setContentAreaFilled(false);
        Registrar.setBorderPainted(false);
        Registrar.setBorder(null);
        Registrar.setBounds(135, 173, 220, 60);
        contentPane.add(Registrar);

        JLabel RegistroBg = new JLabel("");
        RegistroBg.setIcon(new ImageIcon(RegistrarCliente.class.getResource("/icons/Bg/RegistroBg.png")));
        RegistroBg.setBounds(0, 0, 500, 300);
        contentPane.add(RegistroBg);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegistrarCliente frame = new RegistrarCliente();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
