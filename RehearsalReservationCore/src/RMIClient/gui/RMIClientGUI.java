package RMIClient.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
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
	private DefaultTableModel model; 
	private JTable Table;
	private JTextField studentName;
	private JLabel label2;
	private JScrollPane jScrollPane1;
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
		
		 RehearsalRMIDTO r = (RehearsalRMIDTO)arg;
		 //Mejorar este metodo
		 try {
			list = controller.getRehearsals();
			list.set(Table.getSelectedRow(), r);
			Iterator iter = list.iterator();
			model.setRowCount(0);
			
			while (iter.hasNext()){
			  r = (RehearsalRMIDTO) iter.next();
			  
			  Object [] row = new Object[4];
			  row[0] = r.getOperaHouse();
			  row[1] = r.getOperaName();
			  row[2] = r.getDate();
			  row[3] = r.getAvailableSeats();
			  
			  model.addRow ( row );

			}		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
		 
	}
	/**
	* Auto-generated main method to display this JFrame
	*/

	
	public RMIClientGUI(RehearsalController clientController) {
		super();
		initGUI();
		controller = clientController;
		controller.addLocalObserver(this);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

			}
		});
		
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
				label2 = new JLabel();
				label2.setText("Final Rehearsal Seat Availability");
				label2.setFont(new java.awt.Font("Segoe UI",1,11));
			}
			{
				jScrollPane1 = new JScrollPane();
				{
					model = 
						new DefaultTableModel(
								new String[][] { },
								new String[] { "Opera House", "Opera Name", "Date", "Availability" });
					
					Table = new JTable();
					jScrollPane1.setViewportView(Table);
					Table.setModel(model);
					Table.setPreferredSize(new java.awt.Dimension(396, 82));
				}
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
				.addContainerGap()
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
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(label2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(jScrollPane1, 0, 87, Short.MAX_VALUE)
				.addGap(31)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(exit, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				    .addComponent(reserve, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(rehearsals, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(18, 18));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, 0, 414, Short.MAX_VALUE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addPreferredGap(jScrollPane1, titulo1, LayoutStyle.ComponentPlacement.INDENT)
				                .addComponent(titulo1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
				                .addGap(29)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(passWord, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
				                    .addComponent(userName, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
				                .addGap(57)
				                .addComponent(login, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
				                .addComponent(studentName, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 12, Short.MAX_VALUE)))
				        .addGap(15))
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(jSeparator, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
				                .addComponent(studentLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGroup(thisLayout.createParallelGroup()
				                    .addComponent(rehearsals, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
				                        .addGap(83))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
				                        .addGap(85)))
				                .addGap(40)
				                .addComponent(reserve, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
				                .addGap(29)))
				        .addComponent(exit, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 50, Short.MAX_VALUE)))
				.addGap(7));
			pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
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
					controller.deleteLocalObserver(this);
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
					
					RehearsalRMIDTO r= null;
					Iterator iter = list.iterator();
					model.setRowCount(0);
					
					while (iter.hasNext()){
					  r = (RehearsalRMIDTO) iter.next();
					  
					  Object [] row = new Object[4];
					  row[0] = r.getOperaHouse();
					  row[1] = r.getOperaName();
					  row[2] = r.getDate();
					  row[3] = r.getAvailableSeats();
					  
					  model.addRow ( row );

					}
					
					
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			else if (buttonPressed==reserve){
				
				int rowNumber = Table.getSelectedRow() ;
				
				try {
					controller.reserveSeat((String)model.getValueAt(rowNumber, 0), (String)model.getValueAt(rowNumber, 1));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
	

}
