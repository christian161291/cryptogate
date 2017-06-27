import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Builder {
	
	
	private Encryption encryption;

	
	public Builder(){
		
	encryption = new Encryption();
		
	//-------------------------------FRAME------------------------------
	
	JFrame frame = new JFrame();
	frame.setTitle("CryptoGate");
    frame.setSize(600,400);
    frame.setLocationRelativeTo(null);
	
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
	JLabel label = new JLabel("Plaintext: ");
	label.setForeground(Color.cyan);
   	JTextField plaint = new JTextField();
   	plaint.setToolTipText("Insert plaintext:");
   	plaint.setBackground(Color.cyan);
   	plaint.setPreferredSize(new Dimension(400, 40));
   	
   	JLabel label2 = new JLabel("Type: ");
   	label2.setForeground(Color.cyan);
   	String[] type = {"-","AES","DES","SHA","Hexadecimal","Binary","ASCII", "Base64"};
   	JComboBox pw = new JComboBox(type);
   	pw.setBackground(Color.CYAN);
 
   	JLabel label3 = new JLabel("Ciphertext: ");
   	label3.setForeground(Color.cyan);
   	JTextField ciphert = new JTextField();
   	ciphert.setToolTipText("ciphertext:");
   	ciphert.setBackground(Color.cyan);
   	ciphert.setPreferredSize(new Dimension(400, 40));
   	
   	JPanel border = new JPanel();
   	JLabel bor = new JLabel("----------------------------------------------   CRYPTOGATE   ----------------------------------------------");
   	bor.setForeground(Color.cyan);
   	JLabel bor3 = new JLabel("                                            ");
   	border.add(bor);
   	border.add(bor3);
   	border.setBackground(Color.DARK_GRAY);
   	
   	JPanel user = new JPanel();
   	user.add(label);
   	user.add(plaint);
   	user.setBackground(Color.DARK_GRAY);
   	
   	JPanel pass = new JPanel();
   	pass.add(label2);
   	pass.add(pw);
   	pass.setBackground(Color.DARK_GRAY);
   	
 	JPanel but = new JPanel();
   	JButton encode = new JButton("Encode");
   	//login.setBackground(Color.CYAN);
   	but.add(encode);
   	but.setBackground(Color.DARK_GRAY);
   	
   	JPanel pdb = new JPanel();
   	pdb.add(label3);
   	pdb.add(ciphert);
   	pdb.setBackground(Color.DARK_GRAY);
   	
   	panel.add(border);
   	panel.add(user);
   	panel.add(pass);
   	panel.add(but);
   	panel.add(pdb);
   
   	frame.add(panel);
   	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	frame.setVisible(true);
   	
   	//---------------------------------------END FRAME----------------------------------------
   	
   
   	encode.addActionListener(new ActionListener() {
		
	
   		
		public void actionPerformed(ActionEvent arg0) {
			
			String sel;
			String ciphertext;
			
			if((sel= (String) pw.getSelectedItem())=="-"){	
				
				ciphert.setText("^ Select an option ^");	
			}
			
			
			else if ((sel= (String) pw.getSelectedItem())=="Binary"){
				
				ciphertext = encryption.plainToBinary(plaint.getText());
				ciphert.setText(ciphertext);
				System.out.println(plaint.getText() + " = " + ciphertext);
			}
			
			
			else if((sel= (String) pw.getSelectedItem())=="SHA"){	
				
				try {
					
					ciphert.setText(encryption.plaintToSHA(plaint.getText()));
					
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}	
			}
			
			else if ((sel= (String) pw.getSelectedItem())=="DES"){
				
				try {
					
					ciphertext = encryption.plainToDes(plaint.getText());
					ciphert.setText(ciphertext);
					System.out.println(plaint.getText() + " = " + ciphertext);
					
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| IllegalBlockSizeException | BadPaddingException e) {
					
					e.printStackTrace();
				}
				
			}
			
			
			else if ((sel= (String) pw.getSelectedItem())=="Base64"){
				
				ciphertext = encryption.plainToBin64(plaint.getText());
				ciphert.setText(ciphertext);
				System.out.println(plaint.getText() + " = " + ciphertext);
			}
			
			
			else if ((sel= (String) pw.getSelectedItem())=="ASCII"){
				
				ciphertext = encryption.plainToASCII(plaint.getText());
				ciphert.setText(ciphertext);
				System.out.println(plaint.getText() + " = " + ciphertext);
			}
			
			else if ((sel= (String) pw.getSelectedItem())=="Hexadecimal"){
				
				ciphertext = encryption.plainToHex(plaint.getText());
				ciphert.setText(ciphertext);
				System.out.println(plaint.getText() + " = " + ciphertext);
			}
			
		
		
			else if ((sel= (String) pw.getSelectedItem())=="AES"){
				
				try {
					
					ciphertext = encryption.plainToAES(plaint.getText());
					ciphert.setText(ciphertext);
					System.out.println(plaint.getText() + " = " + ciphertext);
					
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| IllegalBlockSizeException | BadPaddingException e) {
					
					e.printStackTrace();
				}
				
			}
		
			
		
		}
	});
   	

	}

}
