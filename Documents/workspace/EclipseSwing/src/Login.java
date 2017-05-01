import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;



public class Login {

	private JFrame frame;
	private JTextField textField1;
	private JTextField textField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	Connection connection=null;
	public Login() throws Exception{
		initialize();
		connection=Connector.getConnection();
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
		textField1.setBounds(305, 46, 123, 26);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(219, 51, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(219, 104, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField2 = new JTextField();
		textField2.setBounds(305, 99, 123, 26);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		Image img=new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
                  String query="select * from info where Username=? and Password=? ";
                  PreparedStatement pat=connection.prepareStatement(query);
				  pat.setString(1, textField1.getText());
				  pat.setString(2, textField2.getText());
				  ResultSet rs=pat.executeQuery();
				  int count=0;
				  while(rs.next()){
					 count=count+1;
				  }
				  if(count==1){
					  JOptionPane.showMessageDialog(null, "correct");
					  frame.dispose();
					  Employee emp1=new Employee();
					  emp1.setVisible(true);
				  }
				  else if(count>1){
					  JOptionPane.showMessageDialog(null, "duplicate");
				  }
				  else{
					  JOptionPane.showMessageDialog(null, "Incorrect,try again");
				  }
				  rs.close();
				  pat.close();
				  
				}
				catch(Exception a){
					System.out.println(a);
				}
				
			}
		});
		btnLogin.setBounds(313, 151, 115, 50);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image img1=new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img1));
		lblNewLabel_2.setBounds(73, 6, 123, 223);
		frame.getContentPane().add(lblNewLabel_2);
	}

}
