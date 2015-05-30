package clientUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentOpUI {
	private JFrame stuF = new JFrame("Class Manager System Student");
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
	//tab 1 change password
	private JPanel changePasswordPanel = new JPanel();
	private JLabel passwordLabel = new JLabel("new Password:");
	private JPasswordField password = new JPasswordField();
	private JButton confirmNewPass = new JButton("confirm");
	//tab 2 myCourse
	private JPanel myClaListP1 = new JPanel();
	private DefaultTableModel myClaTaDTM;
	private JTable myClaTable = new JTable();
	private JButton quitClaButton = new JButton("Quit Course");
	private JScrollPane spMyCourse = new JScrollPane();
	//tab 3 selectCourse
	private JPanel allClaListP1 = new JPanel();
	private DefaultTableModel allClaTaDTM;
	private JTable allClaTable = new JTable();
	private JButton selectClaButton = new JButton("Select Course");
	private JScrollPane spAllCourse = new JScrollPane();
	//data
	Vector<String> myCourseNo = new Vector<String>();
	Vector<String> myCourseName = new Vector<String>();
	Vector<String> myCourseScore = new Vector<String>();	
	Vector<Vector<String>> allCoursePro = new Vector<Vector<String>>();
	
	public StudentOpUI(Vector<String> myClaNo,Vector<String> myClaName,Vector<String> myClaScore,Vector<Vector<String>> allClaPro){
		this.myCourseNo = myClaNo;
		this.myCourseName = myClaName;
		this.myCourseScore = myClaScore;
		this.allCoursePro = allClaPro;
		
		Vector<Vector<String>> vdataMy = new Vector<Vector<String>>();
		Vector<String> vcolumnMy = new Vector<String>();
		
		Vector<Vector<String>> vdataAll = this.allCoursePro;
		Vector<String> vcolumnAll = new Vector<String>();
		
		vcolumnMy.addElement("My Course Number");
		vcolumnMy.addElement("My Course Name");
		vcolumnMy.addElement("My Course Score");
		for(int i = 0;i<this.myCourseNo.size();i++){
			Vector<String> item = new Vector<String>();
			item.addElement(this.myCourseNo.get(i));
			item.addElement(this.myCourseName.get(i));
			item.addElement(this.myCourseScore.get(i));
			vdataMy.addElement(item);
		}
		System.gc();		
		myClaTaDTM = new DefaultTableModel(vdataMy,vcolumnMy);
		myClaTable.setModel(myClaTaDTM);
		
		vcolumnAll.addElement("All Course Number");
		vcolumnAll.addElement("All Course Name");
		vcolumnAll.addElement("All Course Time");
		vcolumnAll.addElement("All Course Point");
		vcolumnAll.addElement("All Course Place");
		vcolumnAll.addElement("All Course Teacher");
		vcolumnAll.addElement("All Course TA");
		vcolumnAll.addElement("1 Compulsory/0 Elective");
		
		
		System.gc();		
		allClaTaDTM = new DefaultTableModel(vdataAll,vcolumnAll);
		allClaTable.setModel(allClaTaDTM);
		
		
	}
	
	public void init(){
		//tab 1 change password
		changePasswordPanel.setLayout(new BoxLayout(changePasswordPanel,BoxLayout.Y_AXIS));
		changePasswordPanel.add(passwordLabel);
		changePasswordPanel.add(password);
		changePasswordPanel.add(confirmNewPass);
		confirmNewPass.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				char[] temp = password.getPassword();
				StringBuilder s = new StringBuilder();
				s.append(temp);
				String newPass = s.toString();
				boolean flag = Client.changePassword(newPass);
				if(flag){
					JOptionPane.showMessageDialog(null, "Change Password to "+newPass, "Change PasswordFailed Succeed", JOptionPane.WARNING_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Change Failed!", "Change PasswordFailed Failed", JOptionPane.ERROR_MESSAGE);
				}
				password.setText("");
			}			
		});
		//tab 2 my course
		myClaListP1.setLayout(new BoxLayout(myClaListP1,BoxLayout.Y_AXIS));
		spMyCourse.setViewportView(myClaTable);
		myClaListP1.add(spMyCourse);
		myClaListP1.add(quitClaButton);
		quitClaButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = myClaTable.getSelectedRow();
				if(!myClaTaDTM.getDataVector().isEmpty()){
					boolean flag = Client.quitCla(myCourseNo.get(selected));
					if(flag){
						myClaTaDTM.removeRow(selected);
						myCourseNo.removeElementAt(selected);
						myCourseName.removeElementAt(selected);
						myCourseScore.removeElementAt(selected);
					}else{
						JOptionPane.showMessageDialog(null, "Qiut Course Failed!", "Qiut Course Failed", JOptionPane.ERROR_MESSAGE);
					}
				}
			}			
		});
		//tab 3 All course
		allClaListP1.setLayout(new BoxLayout(allClaListP1,BoxLayout.Y_AXIS));
		spAllCourse.setViewportView(allClaTable);
		allClaListP1.add(spAllCourse);
		allClaListP1.add(selectClaButton);
		selectClaButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = allClaTable.getSelectedRow();
				if(!allClaTaDTM.getDataVector().isEmpty()){
					boolean flag = Client.selectCla(allCoursePro.get(selected).get(0));
					if(flag){
						Vector<String> newCla = new Vector<String>();
						newCla.addElement(allCoursePro.get(selected).get(0));
						newCla.addElement(allCoursePro.get(selected).get(1));
						newCla.addElement("NA");
						myCourseNo.addElement(allCoursePro.get(selected).get(0));
						myCourseName.addElement(allCoursePro.get(selected).get(1));
						myCourseScore.addElement("NA");
						myClaTaDTM.addRow(newCla);
					}else{
						JOptionPane.showMessageDialog(null, "Select Course Failed!", "Select Course Failed", JOptionPane.ERROR_MESSAGE);
					}
				}
			}			
		});
		
		tabbedPane.addTab("change password", null, null);
		tabbedPane.addTab("my course", null, null);
		tabbedPane.addTab("select course", null, null);		
		
		tabbedPane.setComponentAt(0, changePasswordPanel);
		tabbedPane.setComponentAt(1, myClaListP1);
		tabbedPane.setComponentAt(2, allClaListP1);
				
		stuF.add(tabbedPane, BorderLayout.CENTER);
		
		stuF.setPreferredSize(new Dimension(500,500));
		stuF.pack();
		stuF.addWindowListener(new WindowListener(){
			@Override
			public void windowActivated(WindowEvent arg0) {
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				Client.close();
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
			}
			@Override
			public void windowOpened(WindowEvent e) {
			}			
		});
//		stuF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stuF.setVisible(true);
		
		
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
