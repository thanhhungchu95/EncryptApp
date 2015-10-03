package gos.app.encrypt;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EncryptApp extends JFrame {
    private DefaultComboBoxModel<String> model;
    private JComboBox<String> boxAlgorithm;
    private JPanel pnlText;
    private JPanel pnlButton;
    private JTextField fldOriginalMessage;
    private JTextField fldGenerateMessage;
    private JButton btnExit;
    private JButton btnGenerate;
    private String algorithm;
    private final String name[] = {"MD5", "SHA-1", "SHA-256", "SHA-512"};
    
    public EncryptApp() {
        initialize();
        addListener();
    }

    public static void main(String[] args) {
        EncryptApp app = new EncryptApp();
        app.showWindow();      
    }

    private void initialize() {
        model = new DefaultComboBoxModel<String>();
        algorithm = "MD5";
        for (String n : name) model.addElement(n);

        boxAlgorithm = new JComboBox<String>();
        boxAlgorithm.setModel(model);
        boxAlgorithm.setToolTipText("Choose algorithm");
        boxAlgorithm.setBounds(100, 50, 100, 25);

        fldOriginalMessage = new JTextField();
        fldOriginalMessage.setToolTipText("Type your message ...");
        fldOriginalMessage.setBounds(10, 10, 280, 30);
        fldGenerateMessage = new JTextField();
        fldGenerateMessage.setToolTipText("Crypt message");
        fldGenerateMessage.setBounds(10, 85, 280, 30);
        fldGenerateMessage.setEditable(false);

        btnGenerate = new JButton("Generate");
        btnExit = new JButton("Exit");

        pnlText = new JPanel(null);
        pnlText.add(fldOriginalMessage);
        pnlText.add(boxAlgorithm);
        pnlText.add(fldGenerateMessage);

        pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton.add(btnGenerate);
        pnlButton.add(btnExit);

        setTitle("Demo Encrypt Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 100, 300, 200);
        getContentPane().add(pnlText, "Center");
        getContentPane().add(pnlButton, "South");
    }

    private void addListener() {
        boxAlgorithm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == evt.SELECTED) {
                    algorithm = (String)evt.getItem();
                }
            }
        });

        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String gen = Encrypt.crypt(algorithm, fldOriginalMessage.getText());
                fldGenerateMessage.setText(gen);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

    }

    public void showWindow() {
        setVisible(true);
    }
}
