import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Employee extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
	
    Connection connection=null; //make connection with database;
    private JTextField fn;
    private JTextField id;
    private JTextField ln;
    private JTextField un;
    private JPasswordField password;
    private JTextField age;
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Employee() throws Exception {
        connection=Connector.getConnection(); // make connection with database;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Load employee data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from info";
					PreparedStatement pat=connection.prepareStatement(query);
					ResultSet rs=pat.executeQuery();
					JTable table=new JTable(buildTableModel(rs));
					JOptionPane.showMessageDialog(null, new JScrollPane(table));
					
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(94, 228, 286, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(94, 16, 61, 16);
		contentPane.add(lblId);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(94, 44, 87, 16);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(94, 72, 79, 16);
		contentPane.add(lblLastname);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(94, 100, 95, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(94, 130, 61, 16);
		contentPane.add(lblPassword);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(94, 158, 61, 16);
		contentPane.add(lblAge);
		
		fn = new JTextField();
		fn.setBounds(250, 39, 130, 26);
		contentPane.add(fn);
		fn.setColumns(10);
		
		id = new JTextField();
		id.setBounds(250, 11, 130, 26);
		contentPane.add(id);
		id.setColumns(10);
		
		ln = new JTextField();
		ln.setBounds(250, 67, 130, 26);
		contentPane.add(ln);
		ln.setColumns(10);
		
		un = new JTextField();
		un.setBounds(250, 95, 130, 26);
		contentPane.add(un);
		un.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(250, 125, 130, 26);
		contentPane.add(password);
		
		age = new JTextField();
		age.setBounds(250, 153, 130, 26);
		contentPane.add(age);
		age.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="insert into info (ID,Firstname,Lastname,Username,Password,Age) values (?,?,?,?,?,?)";
					PreparedStatement pat=connection.prepareStatement(query);
					
					pat.setString(1, id.getText());
					pat.setString(2, fn.getText());
					pat.setString(3, ln.getText());
					pat.setString(4, un.getText());
					pat.setString(5, password.getText());
					pat.setString(6, age.getText());
					
					pat.execute();
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pat.close();
		
					
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		btnSave.setBounds(179, 191, 117, 29);
		contentPane.add(btnSave);
	}
}
