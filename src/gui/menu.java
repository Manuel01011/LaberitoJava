package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
public class menu extends JFrame {
    private JPanel panel1;
    public static menu menuu;
    private JButton generarButton;
    private JButton salirButton;

    public menu(){
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new Principal();
                    }
                });
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(WIDTH);
            }
        });

    }
    public JPanel getPanel1() {
        return panel1;
    }
}
