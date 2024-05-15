import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DSASwingUI {

    private static final int KEY_SIZE = 1024;

    private JFrame frame;
    private JTextField keySizeField;
    private JTextArea messageTextArea;
    private JTextArea signatureTextArea;
    private JButton generateKeysButton;
    private JButton signButton;
    private JButton verifyButton;

    private BigInteger p, q, g, x, y;

    public void initialize() {
        frame = new JFrame("DSA Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Key size panel
        JPanel keySizePanel = new JPanel();
        keySizePanel.add(new JLabel("Key Size (bits):"));
        keySizeField = new JTextField(5);
        keySizeField.setText(Integer.toString(KEY_SIZE));
        keySizePanel.add(keySizeField);
        frame.add(keySizePanel, BorderLayout.NORTH);

        // Message panel
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.add(new JLabel("Message:"), BorderLayout.NORTH);
        messageTextArea = new JTextArea();
        messageTextArea.setLineWrap(true);
        //messageTextArea.setScrollWrap(true);
        JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        messageScrollPane.setPreferredSize(new Dimension(400, 200));
        messagePanel.add(messageScrollPane, BorderLayout.CENTER);
        frame.add(messagePanel, BorderLayout.CENTER);

        // Signature panel
        JPanel signaturePanel = new JPanel();
        signaturePanel.setLayout(new BorderLayout());
        signaturePanel.add(new JLabel("Signature:"), BorderLayout.NORTH);
        signatureTextArea = new JTextArea();
        signatureTextArea.setLineWrap(true);
        //signatureTextArea.setScrollWrap(true);
        JScrollPane signatureScrollPane = new JScrollPane(signatureTextArea);
        signatureScrollPane.setPreferredSize(new Dimension(300, 150));
        signaturePanel.add(signatureScrollPane, BorderLayout.CENTER);
        frame.add(signaturePanel, BorderLayout.SOUTH);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        generateKeysButton = new JButton("Generate Keys");
        generateKeysButton.addActionListener(new GenerateKeysActionListener());
        buttonsPanel.add(generateKeysButton);
        signButton = new JButton("Sign");
        signButton.setEnabled(false);
        //signButton.addActionListener(new SignActionListener());
        buttonsPanel.add(signButton);
        verifyButton = new JButton("Verify");
        verifyButton.setEnabled(false);
        //verifyButton.addActionListener(new VerifyActionListener());
        buttonsPanel.add(verifyButton);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private class GenerateKeysActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int keySize = Integer.parseInt(keySizeField.getText());
                if (keySize <= 0) {
                    throw new IllegalArgumentException("Key size must be positive");
                }

                //generateDSAKeys(keySize);
                signButton.setEnabled(true);
                verifyButton.setEnabled(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid key size format", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        DSASwingUI dsaSwingUI = new DSASwingUI();
        dsaSwingUI.initialize();

    }
    

}
