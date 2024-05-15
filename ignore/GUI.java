import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.Scanner;

public class GUI{
    final private Font mainFont = new Font("Freeman", Font.BOLD,22);
    final private Font secondFont = new Font("Poetsen One",Font.BOLD,16);
    JTextField inputMessage;
    JLabel digitalSignature;
    JButton btnOK;
    
    
    public void initialize(){
        JFrame frame = new JFrame();
        //frame.set
        
        
        /* Panel1 - Signer */
        Scanner scr = new Scanner(System.in);
        JLabel lbSigner = new JLabel("Signer");
        JLabel lbinputMessage = new JLabel("\nEnter a message to sign digitally using algorithm :  ");
        lbinputMessage.setFont(mainFont);
        inputMessage = new JTextField();
        inputMessage.setFont(mainFont);
        //String inMessage = inputMessage;
        
        /* Panel2 - Receiver */
        JLabel lbReceiver = new JLabel("Receiver");
        
        /* RSA Label */
        //lbRSA = new JLabel();
        //lbRSA.setFont(mainFont);
        digitalSignature = new JLabel();
        digitalSignature.setFont(mainFont);
        
        JPanel Panel1 = new JPanel();
        Panel1.add(lbSigner);
        Panel1.add(inputMessage);
        
        JPanel Panel2 = new JPanel();
        Panel2.add(lbReceiver);
        Panel2.add(digitalSignature);
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.add(Panel1);
        panel.add(Panel2);
        
        // Button panel
        JButton btnOK = new JButton("OK");
        btnOK.setFont(mainFont);
        btnOK.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
                String inMessage = inputMessage.getText();
                //String dSign = digitalSignature.getText();
                digitalSignature.setText("Your plaintext is "+inMessage);
            }
            
        });
        
        JButton btnClear = new JButton("Clear");
        btnClear.setFont(mainFont);
        btnClear.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                inputMessage.setText(" ");
                digitalSignature.setText(" ");
                
            }
        });
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,5,5));
        buttonsPanel.add(btnOK);
        buttonsPanel.add(btnClear);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128,128,255));
        mainPanel.add(panel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel,BorderLayout.SOUTH);
        
        
        frame.add(mainPanel);
        
        //super("RSA");
        //setSize(500,600);
        //setMinimumSize(new Dimension(300,400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        GUI myFrame = new GUI();
        myFrame.initialize();
        
    }
    
}
