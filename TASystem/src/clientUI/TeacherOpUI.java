package clientUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class TeacherOpUI {
	private JFrame teaMainF = new JFrame("Class Manager System Teacher");
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
	private JFrame teaPublishF = new JFrame("Class Manager System Teacher Publish");
	private JFrame teaUpdateF = new JFrame("Class Manager System Teacher Update");
	private JFrame teaRecordF = new JFrame("Class Manager System Teacher Record");
	//tab 1 change password
	private JPanel changePasswordPanel = new JPanel();
	private JLabel passwordLabel = new JLabel("new Password:");
	private JPasswordField password = new JPasswordField();
	private JButton confirmNewPass = new JButton("confirm");
	//tab 2 my class
	private JPanel myClaListP1 = new JPanel();
	private DefaultTableModel myClaTaDTM;
	private JTable myClaTable = new JTable();	
	private JScrollPane spMyCourse = new JScrollPane();
	private JPanel myClaButtonP = new JPanel();
	private JButton publishClaButton = new JButton("Publish Course");
	private JButton updateClaButton = new JButton("Update Course");
	private JButton recordButton = new JButton("Record Course");
	//tab 3 all class
	private JPanel allClaListP1 = new JPanel();
	private DefaultTableModel allClaTaDTM;
	private JTable allClaTable = new JTable();	
	private JScrollPane spAllCourse = new JScrollPane();
	//Table data
	private Vector<String> myCourseNo = new Vector<String>();
	private Vector<String> myCourseName = new Vector<String>();
	private Vector<String> myCourseTime = new Vector<String>();
	private Vector<String> myCoursePlace = new Vector<String>();
	private Vector<Vector<String>> allClaPro = new Vector<Vector<String>>();
	
	private Vector<Vector<String>> vdata = new Vector<Vector<String>>();
	private Vector<String> vcolumn = new Vector<String>();
	private Vector<Vector<String>> vdataAll = this.allClaPro;
	private Vector<String> vcolumnAll = new Vector<String>();
	
	private int selected;
	//Teacher Publish Frame
	private JPanel publishLine1 = new JPanel();
	private JLabel pubCourseNoL = new JLabel("Course Number:");
	private JLabel pubCourseNameL = new JLabel("Name:");
	private JLabel pubCourseLengthL = new JLabel("Time&Length:");
	private JLabel pubCoursePointL = new JLabel("Point:");
	private JLabel pubCoursePlaceL = new JLabel("Place:");
	private JLabel pubCourseCoTeaL = new JLabel("co-Teachers:(spilt by English ';')");
	private JLabel pubCourseTAL = new JLabel("Teacher Assistents:(spilt by English ';')");
	private JTextField pubCourseNo = new JTextField();
	private JTextField pubCourseName = new JTextField();
	private JTextField pubCourseLength = new JTextField();
	private JTextField pubCoursePoint = new JTextField();
	private JTextField pubCoursePlace = new JTextField();
	private JTextField pubCourseCoTea = new JTextField();
	private JTextField pubCourseTA = new JTextField();
	private JPanel pubCoursePro = new JPanel();
	private JButton submitCla = new JButton("Submit");
	private boolean publishFlag = false;
	//Teacher Update Frame
	private JPanel updatelishLine1 = new JPanel();
	private JLabel updateCourseNoL = new JLabel("Course Number:");
	private JLabel updateCourseNameL = new JLabel("Name:");
	private JLabel updateCourseLengthL = new JLabel("Time&Length:");
	private JLabel updateCoursePointL = new JLabel("Point:");
	private JLabel updateCoursePlaceL = new JLabel("Place:");
	private JLabel updateCourseCoTeaL = new JLabel("co-Teachers:(spilt by English ';')");
	private JLabel updateCourseTAL = new JLabel("Teacher Assistents:(spilt by English ';')");
	private JTextField updateCourseNo = new JTextField();
	private JTextField updateCourseName = new JTextField();
	private JTextField updateCourseLength = new JTextField();
	private JTextField updateCoursePoint = new JTextField();
	private JTextField updateCoursePlace = new JTextField();
	private JTextField updateCourseCoTea = new JTextField();
	private JTextField updateCourseTA = new JTextField();
	private JPanel updateCoursePro = new JPanel();
	private JButton updateSubmitCla = new JButton("Submit");
	private boolean updateFlag = false;
	//Course change data
	private String couNo = null;
	private String couName = null;
	private String couLength = null;		
	private String couPlace = null;
	private String couPro = null;
	private String couPoint = null;
	private String couCoTea = null;
	private String couTA = null;
	//Teacher Record Frame
	private JLabel caution = new JLabel("Double click cells to input score \r\nand cleck somewhere else before submit.");
	private JPanel recordP = new JPanel();
	private JScrollPane recordSP = new JScrollPane();
	private JTable recordTable  = new JTable();
	private DefaultTableModel recordDTM;
	private JButton recordSub = new JButton("Submit");
	private String selectCouNo;
	
	public TeacherOpUI(Vector<String> courseNo1,Vector<String> courseName1,Vector<String> courseTime1,Vector<String> coursePlace1,Vector<Vector<String>> allCoursePro){
		this.myCourseNo = courseNo1;
		this.myCourseName = courseName1;
		this.myCourseTime = courseTime1;
		this.myCoursePlace = coursePlace1;
		this.allClaPro = allCoursePro;
		
		
		
		
		vcolumn.addElement("Course Number");
		vcolumn.addElement("Course Name");
		vcolumn.addElement("Course Time");
		vcolumn.addElement("Course Place");
		for(int i = 0;i<this.myCourseNo.size();i++){
			Vector<String> item = new Vector<String>();
			item.addElement(this.myCourseNo.get(i));
			item.addElement(this.myCourseName.get(i));
			item.addElement(this.myCourseTime.get(i));
			item.addElement(this.myCoursePlace.get(i));
			vdata.addElement(item);
		}
		System.gc();		
		myClaTaDTM = new DefaultTableModel(vdata,vcolumn);
		myClaTable.setModel(myClaTaDTM);
		
		
		
		vcolumnAll.addElement("Course Number");
		vcolumnAll.addElement("Course Name");
		vcolumnAll.addElement("Course Time");
		vcolumnAll.addElement("Course Point");
		vcolumnAll.addElement("Course Place");
		vcolumnAll.addElement("Course Teacher");
		vcolumnAll.addElement("Course TA");
		vcolumnAll.addElement("1 Compulsory/0 Elective");
		vdataAll = this.allClaPro;
		System.out.println(vdataAll);
		System.gc();		
		allClaTaDTM = new DefaultTableModel(vdataAll,vcolumnAll);
		allClaTable.setModel(allClaTaDTM);
		
	}
	public void initialTeaMainF(){
		initialTeaPublishF();
		this.teaPublishF.setVisible(false);
		initialTeaUpdateF();
		this.teaUpdateF.setVisible(false);
		initialTeaRecordF();
		this.teaRecordF.setVisible(false);
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
		//tab 2 my class
		myClaListP1.setLayout(new BoxLayout(myClaListP1,BoxLayout.Y_AXIS));
		spMyCourse.setViewportView(myClaTable);
		myClaListP1.add(spMyCourse);
		
		myClaButtonP.add(publishClaButton);
		myClaButtonP.add(updateClaButton);
		myClaButtonP.add(recordButton);
		
		myClaListP1.add(myClaButtonP);
		publishClaButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				teaPublishF.setVisible(true);
			}
		});
		updateClaButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!myClaTaDTM.getDataVector().isEmpty()){	
					selected = myClaTable.getSelectedRow();
					teaUpdateF.setVisible(true);					
				}else{
					JOptionPane.showMessageDialog(null, "No Course To Update Failed!", "Update Course Failed", JOptionPane.ERROR_MESSAGE);
				}			
			}
			
		});
		recordButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				int selected = myClaTable.getSelectedRow();
				if(selected != -1){
//					if(lastSelected != selected){
						selectCouNo = (String) myClaTaDTM.getValueAt(selected, 0);
						String[] stuList = Client.requestClaStu(selectCouNo);
						if(stuList == null){
							JOptionPane.showMessageDialog(null, "no student under this course!", "Record Course Failed", JOptionPane.ERROR_MESSAGE);
							return;
						}					
						Vector<Vector<String>> stuData = new Vector<Vector<String>>();
						for(int i = 0;i<stuList.length;i++){
							Vector<String> item = new Vector<String>(2);
							item.addElement(stuList[i]);
							item.addElement("");
							stuData.addElement(item);
							System.gc();
						}
						Vector<String> stuColumn = new Vector<String>(2);
						stuColumn.addElement("Student No.");
						stuColumn.addElement("Score");
						recordDTM = new DefaultTableModel(stuData,stuColumn);
						recordTable.setModel(recordDTM);
						teaRecordF.setVisible(true);
//						lastSelected = selected;
//					}else{
//						teaRecordF.setVisible(true);
//					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Select a course first!", "Record Course Failed", JOptionPane.ERROR_MESSAGE);
				}
				
			}			
		});
		
		//tab 3 all course
		allClaListP1.setLayout(new BoxLayout(allClaListP1,BoxLayout.Y_AXIS));
		spAllCourse.setViewportView(allClaTable);
		allClaListP1.add(spAllCourse);
				
		tabbedPane.addTab("change password", null, null);
		tabbedPane.addTab("My Course List", null, null);
		tabbedPane.addTab("All Class List", null, null);
		
		tabbedPane.setComponentAt(0, changePasswordPanel);
		tabbedPane.setComponentAt(1, myClaListP1);
		tabbedPane.setComponentAt(2, allClaListP1);
				
		teaMainF.add(tabbedPane, BorderLayout.CENTER);
		
		teaMainF.setPreferredSize(new Dimension(500,500));
		teaMainF.pack();
		teaMainF.addWindowListener(new WindowListener(){
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
//		teaMainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		teaMainF.setVisible(true);
		
	}
	private void initialTeaPublishF(){
		this.pubCoursePro.add(new ButtonPanel(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JRadioButton actionSource = (JRadioButton) e.getSource();
				String selection = actionSource.getActionCommand();
				if(selection.equals("Elective")){
					couPro = "0";
				}else if(selection.equals("Compulsory")){
					couPro = "1";
				}
			}			
		},"Class Property",new String[]{"Elective","Compulsory"}));
		this.submitCla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(couPro!=null){
					couNo = pubCourseNo.getText();
					couName = pubCourseName.getText();
					couLength = pubCourseLength.getText();
					couPlace = pubCoursePlace.getText();
					couCoTea = pubCourseCoTea.getText();
					couTA = pubCourseTA.getText();
					couPoint = pubCoursePoint.getText();
					if(couCoTea.equals("")){
						couCoTea = "null";
					}
					if(couTA.equals("")){
						couTA = "null";
					}
					System.out.println("Publish Course: "+couNo+" "+couName+" "+couLength+" "+couPoint+" "+couPlace+" "+couCoTea+" "+couTA+" "+couPro);
					publishFlag = Client.publish(couNo,couName,couLength,couPoint,couPlace,couCoTea,couTA,couPro);
					if(publishFlag){
						Vector<String> itemForMy = new Vector<String>();
						Vector<String> itemForAll = new Vector<String>();
						itemForMy.addElement(couNo);
						itemForMy.addElement(couName);
						itemForMy.addElement(couLength);
						itemForMy.addElement(couPlace);
						myClaTaDTM.addRow(itemForMy);
						itemForAll.addElement(couNo);
						itemForAll.addElement(couName);
						itemForAll.addElement(couLength);
						itemForAll.addElement(couPoint);
						itemForAll.addElement(couPlace);
						itemForAll.addElement(couCoTea);
						itemForAll.addElement(couTA);
						itemForAll.addElement(couPro);
						allClaTaDTM.addRow(itemForAll);						
						JOptionPane.showMessageDialog(null, "Publish Course succeed!", "Publish Course succeed", JOptionPane.WARNING_MESSAGE);
						publishFlag = false;
						teaPublishF.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, "Publish Course failed!", "Publish Course failed", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Please select course Property!", "Publish Course failed", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		this.publishLine1.add(this.pubCourseNoL);
		this.publishLine1.add(this.pubCourseNo);
		this.publishLine1.add(this.pubCourseNameL);
		this.publishLine1.add(this.pubCourseName);
		this.publishLine1.add(this.pubCourseLengthL);
		this.publishLine1.add(this.pubCourseLength);
		this.publishLine1.add(this.pubCoursePlaceL);
		this.publishLine1.add(this.pubCoursePlace);		
		this.publishLine1.add(this.pubCourseCoTeaL);
		this.publishLine1.add(this.pubCourseCoTea);
		this.publishLine1.add(this.pubCourseTAL);
		this.publishLine1.add(this.pubCourseTA);
		this.publishLine1.add(this.pubCoursePro);
		this.publishLine1.add(this.pubCoursePointL);
		this.publishLine1.add(this.pubCoursePoint);
		this.publishLine1.add(this.submitCla);
		this.publishLine1.setLayout(new GridLayout(5,3,2,2));
		this.teaPublishF.add(this.publishLine1);
		
		teaPublishF.setPreferredSize(new Dimension(1000,400));
		teaPublishF.pack();
		teaPublishF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		teaPublishF.setVisible(true);
		
		
		
	}
	private void initialTeaUpdateF(){
		this.updateCoursePro.add(new ButtonPanel(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JRadioButton actionSource = (JRadioButton) e.getSource();
				String selection = actionSource.getActionCommand();
				if(selection.equals("Elective")){
					couPro = "0";
				}else if(selection.equals("Compulsory")){
					couPro = "1";
				}
			}			
		},"Class Property",new String[]{"Elective","Compulsory"}));
		this.updateSubmitCla.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!myClaTaDTM.getDataVector().isEmpty()){
					if(couPro!=null){
						couNo = updateCourseNo.getText();
						couName = updateCourseName.getText();
						couLength = updateCourseLength.getText();
						couPlace = updateCoursePlace.getText();
						couCoTea = updateCourseCoTea.getText();
						couTA = updateCourseTA.getText();
						couPoint = updateCoursePoint.getText();
						if(couCoTea.equals("")){
							couCoTea = "null";
						}
						if(couTA.equals("")){
							couTA = "null";
						}
						System.out.println("Update Course: "+myClaTaDTM.getValueAt(selected, 0)+" "+couNo+" "+couName+" "+couLength+" "+couPoint+" "+couPlace+" "+couCoTea+" "+couTA+" "+couPro);
						updateFlag = Client.update((String)myClaTaDTM.getValueAt(selected, 0),couNo,couName,couLength,couPoint,couPlace,couCoTea,couTA,couPro);
						if(updateFlag){
							Vector<String> itemForMy = new Vector<String>();
							Vector<String> itemForAll = new Vector<String>();
							itemForMy.addElement(couNo);
							itemForMy.addElement(couName);
							itemForMy.addElement(couLength);
							itemForMy.addElement(couPlace);
							vdata.setElementAt(itemForMy, selected);
							myClaTaDTM.setDataVector(vdata, vcolumn);
							itemForAll.addElement(couNo);
							itemForAll.addElement(couName);
							itemForAll.addElement(couLength);
							itemForAll.addElement(couPoint);
							itemForAll.addElement(couPlace);
							itemForAll.addElement(couCoTea);
							itemForAll.addElement(couTA);
							itemForAll.addElement(couPro);
							vdataAll.setElementAt(itemForAll, selected);
							allClaTaDTM.setDataVector(vdataAll, vcolumnAll);						
							JOptionPane.showMessageDialog(null, "Update Course succeed!", "Update Course succeed", JOptionPane.WARNING_MESSAGE);
							updateFlag = false;
							teaUpdateF.setVisible(false);
						}else{
							JOptionPane.showMessageDialog(null, "Update Course failed!", "Update Course failed", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Please select course Property!", "Update Course failed", JOptionPane.ERROR_MESSAGE);
					}
				}			
			}			
		});
		this.updatelishLine1.add(this.updateCourseNoL);
		this.updatelishLine1.add(this.updateCourseNo);
		this.updatelishLine1.add(this.updateCourseNameL);
		this.updatelishLine1.add(this.updateCourseName);
		this.updatelishLine1.add(this.updateCourseLengthL);
		this.updatelishLine1.add(this.updateCourseLength);
		this.updatelishLine1.add(this.updateCoursePlaceL);
		this.updatelishLine1.add(this.updateCoursePlace);		
		this.updatelishLine1.add(this.updateCourseCoTeaL);
		this.updatelishLine1.add(this.updateCourseCoTea);
		this.updatelishLine1.add(this.updateCourseTAL);
		this.updatelishLine1.add(this.updateCourseTA);
		this.updatelishLine1.add(this.updateCoursePro);
		this.updatelishLine1.add(this.updateCoursePointL);
		this.updatelishLine1.add(this.updateCoursePoint);
		this.updatelishLine1.add(this.updateSubmitCla);
		this.updatelishLine1.setLayout(new GridLayout(5,3,2,2));
		this.teaUpdateF.add(this.updatelishLine1);
		
		teaUpdateF.setPreferredSize(new Dimension(1000,400));
		teaUpdateF.pack();
		teaUpdateF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		teaUpdateF.setVisible(true);
	}
	private void initialTeaRecordF(){
		this.recordSub.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean formatFlag = true;
				boolean recordFlag = false;
				String[] scoreRecord = new String[recordTable.getRowCount()];
				for(int i = 0;i<recordTable.getRowCount();i++){
					try{
						if(((String)recordDTM.getValueAt(i, 1)).equals("")){
							scoreRecord[i] = "null";
						}else{
							Integer.valueOf((String) recordDTM.getValueAt(i, 1));
							scoreRecord[i] = (String) recordDTM.getValueAt(i, 1);
						}
					}catch(java.lang.NumberFormatException e){
						JOptionPane.showMessageDialog(null, "Score at line "+i+" is not pure number!", "Record Score Failed", JOptionPane.ERROR_MESSAGE);
						formatFlag = false;
					}					
				}
				if(formatFlag){
					System.out.println("going to record!\r\n"+selectCouNo+"\r\n");
					recordFlag = Client.recordScore(selectCouNo,scoreRecord);
					selectCouNo = null;
				}
				if(!recordFlag){
					JOptionPane.showMessageDialog(null, "Record Score Failed!", "Record Score Failed", JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Record Score Succeeded!", "Record Score succeeded", JOptionPane.WARNING_MESSAGE);
					teaRecordF.setVisible(false);
				}
			}
			
		});
		this.recordSP.setViewportView(recordTable);
		this.recordP.setLayout(new BorderLayout());
		this.recordP.add(this.caution,BorderLayout.NORTH);
		this.recordP.add(recordSP,BorderLayout.CENTER);
		this.teaRecordF.setLayout(new BorderLayout());
		this.teaRecordF.add(recordP,BorderLayout.CENTER);
		this.teaRecordF.add(recordSub,BorderLayout.SOUTH);
		this.teaRecordF.pack();
		this.teaRecordF.setPreferredSize(new Dimension(500,500));
		this.teaRecordF.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.teaRecordF.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<String> myCourseNo = new Vector<String>();		
		Vector<String> myCourseName = new Vector<String>();		
		Vector<String> myCourseTime = new Vector<String>();		
		Vector<String> myCoursePlace = new Vector<String>();
		Vector<Vector<String>> all = new Vector<Vector<String>>();
		Vector<String> item  = new Vector<String>();
		
		myCourseNo.addElement("c0783");
		myCourseName.addElement("asfsaf");
		myCourseTime.addElement("19:00-21:00");
		myCoursePlace.addElement("fsafdfds");
		item.addElement("c0783");
		item.addElement("sdfafd");
		item.addElement("dsfaf");
		item.addElement("fdfsff");
		item.addElement("fdfsff");
		item.addElement("fdfsff");
		item.addElement("fdfsff");
		item.addElement("fdfsff");
		all.addElement(item);
		System.out.println(all);
//		item.clear();
//		item.addElement("c0782");
//		item.addElement("c0782");
//		item.addElement("c0782");
//		item.addElement("c0782");
//		item.addElement("c0782");
//		item.addElement("c0782");
//		item.addElement("c0782");
//		item.addElement("c0782");
//		all.addElement(item);
		new TeacherOpUI(myCourseNo, myCourseName, myCourseTime, myCoursePlace, all).initialTeaMainF();
	}

}
