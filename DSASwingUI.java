import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DSASwingUI {

    private static final Font MONOSPACE_FONT = new Font("Consolas", Font.PLAIN, 10);
    private static final Dimension MAXIMUM_TEXTAREA_DIMENSION = new Dimension(2000, 40);

    private JFrame mainFrame;
    private JPanel signerPanel, verifierPanel;
    private JTextArea messageTextArea;
    private JTextArea signatureTextArea;
    private JTextArea eTextArea, dTextArea, nTextArea;
    private JTextArea eTextAreaVerifier, nTextAreaVerifier;
    private JTextArea encodedMessageTextArea;
    private JTextArea receivedMessageTextArea, encodedReceivedMessageTextArea, receivedSignatureTextArea, decryptedSignatureTextArea;
    private JButton generateKeysButton;
    private JButton encodeButton;
    private JButton signButton;
    private JButton verifyButton;
    private JButton receiveKeysButton;
    private JButton receiveSignatureButton;
    private JLabel verifiedLabel;
    private boolean obtainedKeys;
    private boolean obtainedSignature;

    private Signer signer;

    private BigInteger encodedMessage;
    private Signature signature;

    public void initialize() {
        mainFrame = new JFrame("DSA Tool");
        mainFrame.setLayout(new GridLayout(1, 2));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(1024, 512));

        signerPanel = new JPanel();
        signerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        signerPanel.setLayout(new BoxLayout(signerPanel, BoxLayout.Y_AXIS));
        JLabel signerLabel = new JLabel("Signer");
        signerPanel.add(signerLabel);


        // Keys text area
        signerPanel.add(new JLabel("Encryption exponent, e:"));
        eTextArea = new JTextArea(1, 0);
        eTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        eTextArea.setBackground(Color.LIGHT_GRAY);
        eTextArea.setEditable(false);
        eTextArea.setFont(MONOSPACE_FONT);
        eTextArea.setLineWrap(true);
        eTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        signerPanel.add(eTextArea);

        signerPanel.add(new JLabel("Decryption exponent, d:"));
        dTextArea = new JTextArea(4, 0);
        dTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        dTextArea.setBackground(Color.LIGHT_GRAY);
        dTextArea.setEditable(false);
        dTextArea.setFont(MONOSPACE_FONT);
        dTextArea.setLineWrap(true);
        dTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        signerPanel.add(dTextArea);

        signerPanel.add(new JLabel("System modulo, n:"));
        nTextArea = new JTextArea(4, 0);
        nTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        nTextArea.setBackground(Color.LIGHT_GRAY);
        nTextArea.setEditable(false);
        nTextArea.setFont(MONOSPACE_FONT);
        nTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        nTextArea.setLineWrap(true);
        signerPanel.add(nTextArea);
        // messageTextArea.setLineWrap(true);
        // messageTextArea.setScrollWrap(true);
        // JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
        // messageScrollPane.setPreferredSize(new Dimension(400, 200));
        // messagePanel.add(messageScrollPane, BorderLayout.CENTER);

        // Message text area
        signerPanel.add(new JLabel("Message:"));
        messageTextArea = new JTextArea(2, 0);
        messageTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        messageTextArea.setFont(MONOSPACE_FONT);
        messageTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        messageTextArea.setLineWrap(true);
        signerPanel.add(messageTextArea);

        // Encoded Message text area
        signerPanel.add(new JLabel("Message digest:"));
        encodedMessageTextArea = new JTextArea(1, 0);
        encodedMessageTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        encodedMessageTextArea.setBackground(Color.LIGHT_GRAY);
        encodedMessageTextArea.setEditable(false);
        encodedMessageTextArea.setFont(MONOSPACE_FONT);
        encodedMessageTextArea.setLineWrap(true);
        encodedMessageTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        signerPanel.add(encodedMessageTextArea);

        // Signature panel
        signerPanel.add(new JLabel("Signature:"));
        signatureTextArea = new JTextArea(4, 0);
        signatureTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        signatureTextArea.setBackground(Color.LIGHT_GRAY);
        signatureTextArea.setEditable(false);
        signatureTextArea.setLineWrap(true);
        signatureTextArea.setFont(MONOSPACE_FONT);
        signatureTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        signerPanel.add(signatureTextArea);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        generateKeysButton = new JButton("Generate Keys");
        generateKeysButton.addActionListener(new GenerateKeysActionListener());
        buttonsPanel.add(generateKeysButton);

        encodeButton = new JButton("Encode");
        encodeButton.setEnabled(false);
        encodeButton.addActionListener(new EncodeTextActionListener());
        buttonsPanel.add(encodeButton);

        signButton = new JButton("Sign");
        signButton.setEnabled(false);
        signButton.addActionListener(new SignActionListener());
        buttonsPanel.add(signButton);
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        signerPanel.add(buttonsPanel);
        mainFrame.add(signerPanel);

        verifierPanel = new JPanel();
        verifierPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        verifierPanel.setLayout(new BoxLayout(verifierPanel, BoxLayout.Y_AXIS));
        verifierPanel.add(new JLabel("Verifier"));

        // Keys text area
        verifierPanel.add(new JLabel("Encryption exponent, e:"));
        eTextAreaVerifier = new JTextArea(1, 0);
        eTextAreaVerifier.setAlignmentX(Component.LEFT_ALIGNMENT);
        eTextAreaVerifier.setFont(MONOSPACE_FONT);
        eTextAreaVerifier.setLineWrap(true);
        eTextAreaVerifier.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        verifierPanel.add(eTextAreaVerifier);

        verifierPanel.add(new JLabel("System modulo, n:"));
        nTextAreaVerifier = new JTextArea(4, 0);
        nTextAreaVerifier.setAlignmentX(Component.LEFT_ALIGNMENT);
        nTextAreaVerifier.setFont(MONOSPACE_FONT);
        nTextAreaVerifier.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        nTextAreaVerifier.setLineWrap(true);
        verifierPanel.add(nTextAreaVerifier);

        // Message text area
        verifierPanel.add(new JLabel("Message:"));
        receivedMessageTextArea = new JTextArea(2, 0);
        receivedMessageTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        receivedMessageTextArea.setFont(MONOSPACE_FONT);
        receivedMessageTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        receivedMessageTextArea.setLineWrap(true);
        verifierPanel.add(receivedMessageTextArea);

        
        verifierPanel.add(new JLabel("Signature:"));
        receivedSignatureTextArea = new JTextArea(4, 0);
        receivedSignatureTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        receivedSignatureTextArea.setEditable(false);
        receivedSignatureTextArea.setLineWrap(true);
        receivedSignatureTextArea.setFont(MONOSPACE_FONT);
        receivedSignatureTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        verifierPanel.add(receivedSignatureTextArea);

        // Encoded Message text area
        verifierPanel.add(new JLabel("Message digest:"));
        encodedReceivedMessageTextArea = new JTextArea(1, 0);
        encodedReceivedMessageTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        encodedReceivedMessageTextArea.setBackground(Color.LIGHT_GRAY);
        encodedReceivedMessageTextArea.setEditable(false);
        encodedReceivedMessageTextArea.setFont(MONOSPACE_FONT);
        encodedReceivedMessageTextArea.setLineWrap(true);
        encodedReceivedMessageTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        verifierPanel.add(encodedReceivedMessageTextArea);

        verifierPanel.add(new JLabel("Decrypted Signature:"));
        decryptedSignatureTextArea = new JTextArea(4, 0);
        decryptedSignatureTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        decryptedSignatureTextArea.setBackground(Color.LIGHT_GRAY);
        decryptedSignatureTextArea.setEditable(false);
        decryptedSignatureTextArea.setLineWrap(true);
        decryptedSignatureTextArea.setFont(MONOSPACE_FONT);
        decryptedSignatureTextArea.setMaximumSize(MAXIMUM_TEXTAREA_DIMENSION);
        verifierPanel.add(decryptedSignatureTextArea);

        // Buttons panel
        JPanel verifierButtonsPanel = new JPanel();
        receiveKeysButton = new JButton("Receive Public Key");
        receiveKeysButton.setEnabled(false);
        receiveKeysButton.addActionListener(new ReceiveKeysActionListener());
        verifierButtonsPanel.add(receiveKeysButton);

        receiveSignatureButton = new JButton("Receive Signature");
        receiveSignatureButton.setEnabled(false);
        receiveSignatureButton.addActionListener(new ReceiveSignatureActionListener());
        verifierButtonsPanel.add(receiveSignatureButton);

        verifyButton = new JButton("Verify");
        verifyButton.setEnabled(false);
        verifyButton.addActionListener(new VerifyActionListener());
        verifierButtonsPanel.add(verifyButton);
        verifierButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        verifiedLabel = new JLabel("");
        verifierButtonsPanel.add(verifiedLabel);
        verifierPanel.add(verifierButtonsPanel);

        mainFrame.add(verifierPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private class GenerateKeysActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            signer = new Signer();
            dTextArea.setText(toHexString(signer.rsaKeyPair.privateKey.d));
            eTextArea.setText(toHexString(signer.rsaKeyPair.publicKey.e));
            nTextArea.setText(toHexString(signer.rsaKeyPair.publicKey.n));
            encodeButton.setEnabled(true);
            receiveKeysButton.setEnabled(true);
        }
    }

    private class EncodeTextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageTextArea.getText();
            encodedMessage = signer.encode(message);
            encodedMessageTextArea.setText(toHexString(encodedMessage));
            signButton.setEnabled(true);
        }
    }

    private class SignActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageTextArea.getText();
            signature = signer.signMessage(message);
            signatureTextArea.setText(toHexString(signature.signedHash));
            receiveSignatureButton.setEnabled(true);
        }
    }


    private class ReceiveKeysActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            eTextAreaVerifier.setText(toHexString(signer.rsaKeyPair.publicKey.e));
            nTextAreaVerifier.setText(toHexString(signer.rsaKeyPair.publicKey.n));
            obtainedKeys = true;
            if (obtainedSignature)
                verifyButton.setEnabled(true);
        }
    }

    private class ReceiveSignatureActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            receivedMessageTextArea.setText(signature.messageString);
            receivedSignatureTextArea.setText(toHexString(signature.signedHash));
            obtainedSignature = true;
            if (obtainedKeys)
                verifyButton.setEnabled(true);
        }
    }
    private class VerifyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            RSAPublicKey publicKey;
            Signature signature;
            try {
                BigInteger p = new BigInteger(eTextAreaVerifier.getText(), 16); 
                BigInteger n = new BigInteger(nTextAreaVerifier.getText(), 16); 
                String receivedMessage = receivedMessageTextArea.getText(); 
                BigInteger receivedSignature = new BigInteger(receivedSignatureTextArea.getText(), 16); 
                publicKey = new RSAPublicKey(p, n);
                signature = new Signature(receivedMessage, receivedSignature);

                BigInteger encodedReceivedMessage = Verifier.encodeMessage(receivedMessage);
                encodedReceivedMessageTextArea.setText(toHexString(encodedReceivedMessage));

                BigInteger decryptedSignature = Verifier.decryptMessage(signature, publicKey);
                decryptedSignatureTextArea.setText(toHexString(decryptedSignature));

                boolean verified = Verifier.verifyMessage(signature, publicKey);
                verifiedLabel.setText(
                        verified ? "Digital Signature is verified." : "Can not authenticate the digital signature.");
            }
            catch (NumberFormatException exception) {
                verifiedLabel.setText("Invalid text");
            }
            
        }
    }


    public static void main(String[] args) {
        DSASwingUI dsaSwingUI = new DSASwingUI();
        dsaSwingUI.initialize();

    }

    private static String toHexString(BigInteger input) {
        return input.toString(16);
    }
}
