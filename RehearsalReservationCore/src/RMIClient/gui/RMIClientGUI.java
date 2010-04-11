package RMIClient.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.EventObject;
import java.util.List;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import javax.swing.WindowConstants;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import rehearsalServer.RehearsalRMIDTO;
import rehearsalServer.loginGateway.ValidationException;

import RMIClient.RehearsalController;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class RMIClientGUI  extends javax.swing.JFrame implements Observer , ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel jPanel1;
	private JPasswordField passWord;
	private JPasswordField passwordField;
	private JTable Table;
	private JTextField studentName;
	private JLabel label2;
	private JLabel titulo1;
	private JSeparator jSeparator;
	private JLabel studentLabel;
	private JLabel passLabel;
	private JLabel userLabel;
	private JTextField userName;
	private JButton login;
	private JButton exit;
	private JButton reserve;
	private JButton rehearsals;
	
	
	private static RehearsalController controller = null;
	List <RehearsalRMIDTO> list = null;
	
	public void update(java.util.Observable o, Object arg) {
		
		
	}
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				RMIClientGUI inst = new RMIClientGUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
		
		try {
			controller = new RehearsalController(args);
		} catch (RemoteException e) {

			e.printStackTrace();
		}
	}
	
	public RMIClientGUI() {
		super();
		initGUI();
		
	}
	
	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setFont(new java.awt.Font("Arial",1,9));
			{
				login = new JButton();
				login.setText("Login");
				login.addActionListener(this);	
			}
			{
				userName = new JTextField();
				userName.setText("");
			}
			{
				userLabel = new JLabel();
				userLabel.setText("User Name");
			}
			{
				TableModel TableModel = 
					new DefaultTableModel(
							new String[][] { { "One", "Two" }, { "Three", "Four" } },
							new String[] { "Column 1", "Column 2" });
				Table = new JTable();
				Table.setModel(TableModel);
			}
			{
				label2 = new JLabel();
				label2.setText("Final Rehearsal Seat Availability");
				label2.setFont(new java.awt.Font("Segoe UI",1,11));
			}
			{
				jSeparator = new JSeparator();
			}
			{
				titulo1 = new JLabel();
				titulo1.setText("Student Details");
				titulo1.setFont(new java.awt.Font("Segoe UI",1,11));
			}
			{
				jPanel1 = new JPanel();
			}
			{
				passwordField = new JPasswordField();
				passwordField.setText("jPasswordField1");
			}
			{
				passWord = new JPasswordField();
			}
			{
				passLabel = new JLabel();
				passLabel.setText("Password");
			}
			{
				studentLabel = new JLabel();
				studentLabel.setText("Student Name");
			}
			{
				studentName = new JTextField();
				studentName.setText("   ");
			}
			{
				rehearsals = new JButton();
				rehearsals.setText("Get Scheduled Rehearsals");
				rehearsals.addActionListener(this);
			}
			{
				reserve = new JButton();
				reserve.setText("Reserve Seat");
				reserve.addActionListener(this);
			}
			{
				exit = new JButton();
				exit.setText("Exit");
				exit.addActionListener(this);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addGap(8)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
				    .addComponent(titulo1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(7)
				        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
				        .addGap(8)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(userName, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(login, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
				    .addComponent(userLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(12)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(studentName, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
				    .addComponent(passLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(studentLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
				    .addComponent(passWord, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(24)
				.addComponent(jSeparator, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
				.addComponent(label2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(Table, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
				.addGap(0, 22, GroupLayout.PREFERRED_SIZE)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(exit, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				    .addComponent(reserve, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(rehearsals, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(50, 50));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
				.addGap(7)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(rehearsals, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 25, Short.MAX_VALUE)
				        .addComponent(reserve, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 22, GroupLayout.PREFERRED_SIZE)
				        .addComponent(exit, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
				        .addGap(54))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(label2, GroupLayout.Alignment.LEADING, 0, 208, Short.MAX_VALUE)
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(titulo1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
				                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
				                        .addGroup(thisLayout.createParallelGroup()
				                            .addComponent(passWord, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
				                            .addComponent(userName, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
				                        .addGap(45)))
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(studentLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
				                        .addGap(12))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addPreferredGap(studentLabel, login, LayoutStyle.ComponentPlacement.INDENT)
				                        .addComponent(login, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
				                .addComponent(studentName, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(23)
				                .addComponent(Table, 0, 319, Short.MAX_VALUE)
				                .addGap(23)))
				        .addGap(34))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGap(7)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 349, Short.MAX_VALUE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 351, Short.MAX_VALUE))
				            .addComponent(jSeparator, GroupLayout.Alignment.LEADING, 0, 414, Short.MAX_VALUE)))));
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
		if (e.getSource() instanceof JButton)
		{
			JButton buttonPressed=(JButton)e.getSource();
			
			if (buttonPressed==exit)
				try {
					controller.exit();
				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			else if (buttonPressed==login){
				String user = userName.getText();	
				String pass = new String(passWord.getPassword());
				try {
					studentName.setText(controller.login(user, pass));
				} catch (ValidationException e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
			else if (buttonPressed==rehearsals){
				
				try {					
					list = controller.getRehearsals();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (buttonPressed==reserve){
				//controller.reserveSeat(null, null);
			}
		}
		
	}
	

}
