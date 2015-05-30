package clientUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
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

public class AdminUI {
	private JFrame adminF = new JFrame("Class Manager System Admin");
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
	//tab 1 change password
	private JPanel changePasswordPanel = new JPanel();
	private JLabel passwordLabel = new JLabel("new Password:");
	private JPasswordField password = new JPasswordField();
	private JButton confirmNewPass = new JButton("confirm");
	//tab 2 Teacher List
	private JPanel teaListP1 = new JPanel();
	private DefaultTableModel teaTaDTM;
	private JTable teaTable = new JTable();
	private JButton deleteTeaButton = new JButton("Delete Teacher");
	private JScrollPane spt = new JScrollPane();
	//tab 3 Student List
	private JPanel stuListP1 = new JPanel();
	private DefaultTableModel stuTaDTM ;
	private JTable stuTable = new JTable();
	private JButton deleteStuButton = new JButton("Delete Student");
	private JScrollPane sps = new JScrollPane();
	private JButton showStuCou = new JButton("Show Student Course");
	//tab 4 Class List
	private JPanel claListP1 = new JPanel();
	private DefaultTableModel claTaDTM ;
	private JTable claTable = new JTable();
	private JButton deleteClaButton = new JButton("Delete Class");
	private JScrollPane spc = new JScrollPane();
	//data
	private Vector<String> teaNo = new Vector<String>();
	private Vector<String> teaName = new Vector<String>();
	
	private Vector<String> stuNo = new Vector<String>();
	private Vector<String> stuName = new Vector<String>();
	
	private Vector<String> claNo = new Vector<String>();
	private Vector<String> claName = new Vector<String>();
	private Vector<String> claTime = new Vector<String>();
	private Vector<String> claPlace = new Vector<String>();
	
	//show student selected course
	private JFrame stuCou = new JFrame("Student Course");
	private JScrollPane spShowStu = new JScrollPane();
	private DefaultTableModel stuClaTaDTM ;
	private JTable stuClaTable = new JTable();
	private JButton deStuClaButton = new JButton("Quit Class");
	private JPanel stuCouP = new JPanel();
	
	
	public AdminUI(Vector<String> teacherNo,Vector<String> teacherName,Vector<String> studentNo,Vector<String> studentName,Vector<String> classNo,Vector<String> className,Vector<String> classTime ,Vector<String> classPlace) throws IOException{
		this.teaNo = teacherNo;
		this.teaName = teacherName;
		this.stuNo = studentNo;
		this.stuName = studentName;
		this.claNo = classNo;
		this.claName = className;
		this.claTime = classTime;
		this.claPlace = classPlace;
		Vector<Vector<String>> vdatat = new Vector<Vector<String>>();
		Vector<String> vcolumnt = new Vector<String>();
		
		Vector<Vector<String>> vdatas = new Vector<Vector<String>>();
		Vector<String> vcolumns = new Vector<String>();
		
		Vector<Vector<String>> vdatac = new Vector<Vector<String>>();
		Vector<String> vcolumnc = new Vector<String>();
		
		vcolumnt.addElement("Teacher Number");
		vcolumnt.addElement("Teacher Name");
		for(int i = 0;i<this.teaNo.size();i++){
			Vector<String> item = new Vector<String>();
			item.addElement(this.teaNo.get(i));
			item.addElement(this.teaName.get(i));
			vdatat.addElement(item);
		}
		System.gc();		
		teaTaDTM = new DefaultTableModel(vdatat,vcolumnt);
		teaTable.setModel(teaTaDTM);
		
		vcolumns.addElement("Student Number");
		vcolumns.addElement("Student Name");
		for(int i = 0;i<this.stuNo.size();i++){
			Vector<String> item = new Vector<String>();
			item.addElement(this.stuNo.get(i));
			item.addElement(this.stuName.get(i));
			vdatas.addElement(item);
		}
		System.gc();	
		stuTaDTM = new DefaultTableModel(vdatas,vcolumns);
		stuTable.setModel(stuTaDTM);
				
		vcolumnc.addElement("Class Number");
		vcolumnc.addElement("Class Name");
		vcolumnc.addElement("Class Time");
		vcolumnc.addElement("Class Place");
		for(int i = 0;i<this.claNo.size();i++){
			Vector<String> item = new Vector<String>();
			item.addElement(this.claNo.get(i));
			item.addElement(this.claName.get(i));
			item.addElement(this.claTime.get(i));
			item.addElement(this.claPlace.get(i));
			vdatac.addElement(item);
		}
		claTaDTM = new DefaultTableModel(vdatac,vcolumnc);
		claTable.setModel(claTaDTM);
		
		
	}
	
	public void inita(){
		initialStuCou();
		this.stuCou.setVisible(false);
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
		//tab 2 Teacher List		
		teaListP1.setLayout(new BoxLayout(teaListP1,BoxLayout.Y_AXIS));
		spt.setViewportView(teaTable);
		teaListP1.add(spt);
		teaListP1.add(deleteTeaButton);
		deleteTeaButton.addActionListener(new ActionListener(){

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = teaTable.getSelectedRow();
				if(!teaTaDTM.getDataVector().isEmpty()){
					String[] couBeDele = Client.removeTea(teaNo.get(selected));
					if(couBeDele != null){
						teaTaDTM.removeRow(selected);
						teaNo.removeElementAt(selected);
						teaName.removeElementAt(selected);
						Vector<Vector<String>> course = (Vector<Vector<String>>)claTaDTM.getDataVector(); 						
						for(int i = 0;i<couBeDele.length;i++)
						{
							for(int j = 0;j<course.size();j++)
							{
								if(course.get(j).contains(couBeDele[i])){
									course.removeElementAt(j);
									break;
								}
							}
							System.out.println("Removed a course!");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Remove Teacher Failed!", "Remove Teacher Failed", JOptionPane.ERROR_MESSAGE);
					}
				}
			}			
		});
		//tab 3 Student List
		stuListP1.setLayout(new BoxLayout(stuListP1,BoxLayout.Y_AXIS));
		sps.setViewportView(stuTable);
		stuListP1.add(sps);
		stuListP1.add(deleteStuButton);
		stuListP1.add(showStuCou);
		deleteStuButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!stuTaDTM.getDataVector().isEmpty()){
					int selected = stuTable.getSelectedRow();
					boolean flag = Client.removeStu(stuNo.get(selected));
					if(flag){
						stuTaDTM.removeRow(selected);
						stuNo.removeElementAt(selected);
						stuName.removeElementAt(selected);	
					}else{
						JOptionPane.showMessageDialog(null, "Remove Student Failed!", "Remove Student Failed", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}			
		});
		
		showStuCou.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selected = stuTable.getSelectedRow();
				
				if(selected == -1){
					JOptionPane.showMessageDialog(null, "Select a student first!", "Course Operation Failed", JOptionPane.ERROR_MESSAGE);
				}else if(selected != -1){
						String selectStuNo = (String) stuTaDTM.getValueAt(selected, 0);
						String[] couList = Client.requestStuCla(selectStuNo);
						if(couList == null){
							JOptionPane.showMessageDialog(null, "no course under this student!", "Course Operation Failed", JOptionPane.ERROR_MESSAGE);
							return;
						}					
						Vector<Vector<String>> couData = new Vector<Vector<String>>();
						for(int i = 0;i<couList.length;i++){
							Vector<String> item = new Vector<String>(1);
							item.addElement(couList[i]);
							couData.addElement(item);
							System.gc();
						}
						Vector<String> couColumn = new Vector<String>(1);
						couColumn.addElement("Course No.");
						stuClaTaDTM = new DefaultTableModel(couData,couColumn);
						stuClaTable.setModel(stuClaTaDTM);
						stuCou.setVisible(true);
				}
				
			}
			
		});
		//tab 4 Class List
		claListP1.setLayout(new BoxLayout(claListP1,BoxLayout.Y_AXIS));
		spc.setViewportView(claTable);
		claListP1.add(spc);
		claListP1.add(deleteClaButton);
		deleteClaButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!claTaDTM.getDataVector().isEmpty()){
					int selected = claTable.getSelectedRow();					
					boolean flag = Client.removeCla(claNo.get(selected));
					if(flag){
						claTaDTM.removeRow(selected);
						claNo.removeElement(selected);
						claName.removeElement(selected);
						claTime.removeElement(selected);
						claPlace.removeElement(selected);
					}else{
						JOptionPane.showMessageDialog(null, "Remove Course Failed!", "Remove Course Failed", JOptionPane.ERROR_MESSAGE);
					}
					
				}				
			}			
		});
		
		
		tabbedPane.addTab("change password", null, null);
		tabbedPane.addTab("Teacher List", null, null);
		tabbedPane.addTab("Student List", null, null);
		tabbedPane.addTab("Class List", null, null);
		
		tabbedPane.setComponentAt(0, changePasswordPanel);
		tabbedPane.setComponentAt(1, teaListP1);
		tabbedPane.setComponentAt(2, stuListP1);
		tabbedPane.setComponentAt(3, claListP1);
		
		adminF.add(tabbedPane, BorderLayout.CENTER);
		
		adminF.setPreferredSize(new Dimension(500,500));
		adminF.pack();
		adminF.addWindowListener(new WindowListener(){
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
//		adminF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminF.setVisible(true);
		
	}
	
	
	private void initialStuCou() {
		this.deStuClaButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selected = stuClaTable.getSelectedRow();
				String couNo = (String) stuClaTaDTM.getValueAt(selected, 0);
				boolean deleteFlag = false;
				deleteFlag = Client.quitCla(couNo);
				if(deleteFlag){
					System.out.println("going to quit!\r\n"+couNo+"\r\n");
					stuClaTaDTM.removeRow(selected);
				}else{
					JOptionPane.showMessageDialog(null, "Qiut Failed!", "Quit Course Failed", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		this.spShowStu.setViewportView(stuClaTable);
		this.stuCouP.add(showStuCou);
		this.stuCou.setLayout(new BorderLayout());
		this.stuCou.add(stuClaTable,BorderLayout.CENTER);
		this.stuCou.add(deStuClaButton,BorderLayout.SOUTH);
		
		this.stuCou.setPreferredSize(new Dimension(500,500));
		this.stuCou.pack();
		this.stuCou.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.stuCou.setVisible(true);
		
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
