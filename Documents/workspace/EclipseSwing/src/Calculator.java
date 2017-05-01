import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Calculator {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textFieldans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField1 = new JTextField();
		textField1.setBounds(24, 31, 179, 53);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(215, 31, 169, 53);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1,num2,ans;
				try{
					num1=Integer.parseInt(textField1.getText());
					num2=Integer.parseInt(textField2.getText());
					ans=num1+num2;
					textFieldans.setText(Integer.toString(ans));
					
				}catch(Exception a){
					JOptionPane.showMessageDialog(null, "Please enter a valid number");
				}
			}
		});
		btnNewButton.setBounds(35, 96, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Minus");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1,num2,ans;
				try{
					num1=Integer.parseInt(textField1.getText());
					num2=Integer.parseInt(textField2.getText());
					ans=num1-num2;
					textFieldans.setText(Integer.toString(ans));
					
				}catch(Exception a){
					JOptionPane.showMessageDialog(null, "Please enter a valid number");
				}
			}
		});
		btnNewButton_1.setBounds(213, 96, 117, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		textFieldans = new JTextField();
		textFieldans.setBounds(238, 180, 130, 26);
		frame.getContentPane().add(textFieldans);
		textFieldans.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Answer");
		lblNewLabel.setBounds(142, 185, 61, 16);
		frame.getContentPane().add(lblNewLabel);
	}

}
