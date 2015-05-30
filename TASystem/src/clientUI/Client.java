package clientUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Client {
	private static boolean loginAdmin = false;
	private static boolean loginStu = false;
	private static boolean loginTea = false;
	private static Socket s = null;
	private static BufferedWriter buffw;
	private static BufferedReader buffr;
	private static void initialClient() {
		// TODO Auto-generated method stub
		try {
			Client.s = new Socket("Localhost",30000);
			s.setSoTimeout(5000);
			Client.buffw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			Client.buffr = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	public static void close() {
		
		try {
			buffw.close();
			buffr.close();
			s.close();
			System.out.println("sourse recycled");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot close!", "Exit Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	//for login
	/**
	 * 
	 * @param adminPass
	 * @return
	 * @throws IOException
	 */
	public static boolean checkAdminLogin(String adminPass) throws IOException{
		Client.buffw.write("Admin Admin "+adminPass+" \r\n");
		Client.buffw.flush();
		System.out.println("checkAdminLogin: Admin Admin "+adminPass+" \r\nwritten!");
		String echo = buffr.readLine();
		System.out.println("checkAdminLogin: get replay "+echo);
		if(echo.equals("Admin")){
			Client.loginAdmin = true;
			return true;
		}else{
			return false;
		}
	}
	public static boolean checkStuLogin(String stuNo,String stuPass) throws IOException{
		Client.buffw.write("Student "+stuNo+" "+stuPass+" \r\n");
		Client.buffw.flush();
		String echo = buffr.readLine();
		if(echo.equals("Student")){
			Client.loginStu = true;
			return true;
		}else{
			return false;
		}
	}
	public static boolean checkTeaLogin(String teaNo,String teaPass) throws IOException{
		Client.buffw.write("Teacher "+teaNo+" "+teaPass+" \r\n");
		Client.buffw.flush();
		String echo = buffr.readLine();
		if(echo.equals("Teacher")){
			Client.loginTea = true;
			return true;
		}else{
			return false;
		}
	}
	private static String[] getAPackage() throws IOException{
		String[] temp = Client.buffr.readLine().split("  ");
		if(temp.length<2){
			return new String[0];
		}else{
			return temp[1].split(" ");
		}
		
	}
	public static boolean changePassword(String newPassword){
		String reply = "False";
		try {
			Client.buffw.write("ChangePassword "+newPassword+"\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();			
			return false;
		}
		if(reply.equals("True")){
			System.out.println("change pass to "+newPassword);
			return true;
		}else{
			return false;
		}
		
	}
	//for Administer	
	public static String[] removeTea(String teaNo){
		String reply;
		try {
			Client.buffw.write("Delete Teacher "+teaNo+"\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
		return reply.split(" ");
	}
	public static boolean removeStu(String stuNo){
		String reply = "False";
		try {
			Client.buffw.write("Delete Student "+stuNo+"\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		if(reply.equals("True")){
			System.out.println("Student "+stuNo+ "is removed");
			return true;
		}else{
			return false;
		}
	}
	public static boolean removeCla(String claNo){
		String reply = "False";
		try {
			Client.buffw.write("Delete Course "+claNo+"\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		if(reply.equals("True")){
			System.out.println("Student "+claNo+ "is removed");
			return true;
		}else{
			return false;
		}
	}
	//for Student
	public static boolean quitCla(String claNo){
		String reply = "False";
		try {
			Client.buffw.write("Quit "+claNo+"\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		if(reply.equals("True")){
			System.out.println("Course "+claNo+ "is quited");
			return true;
		}else{
			return false;
		}
	}
	public static boolean selectCla(String claNo){
		String reply = "False";
		try {
			Client.buffw.write("Select "+claNo+"\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		if(reply.equals("True")){
			System.out.println("Course "+claNo+ "is selected");
			return true;
		}else{
			return false;
		}
	}
	public static String[] requestStuCla(String selectStuNo) {
		// actually used by Administer
		String[] claList = null;
		try {
			Client.buffw.write("ShowStu "+selectStuNo+"\r\n");
			Client.buffw.flush();
			System.out.println("Request student send!\r\nShow selected course "+selectStuNo+"\r\n");
			claList = Client.buffr.readLine().split(" ");
			if(claList[0].equals("NoCou")){
				return null;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return claList;
	}
	//for Teacher
	public static boolean publish(String couNo, String couName, String couLength,
			String couPoint, String couPlace, String couCoTea, String couTA,
			String couPro) {
		String reply = "False";
		try {
			Client.buffw.write("Publish "+couNo+","+couName+","+couLength+","+couPoint+","+couPlace+","+couCoTea+","+couTA+","+couPro+"\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		if(reply.equals("True")){
			System.out.println("Course "+couNo+ "is published");
			return true;
		}else{
			return false;
		}		
	}
	public static boolean update(String oldCourseNo,String couNo, String couName,
			String couLength, String couPoint, String couPlace,
			String couCoTea, String couTA, String couPro) {
		String reply = "False";
		try {
			Client.buffw.write("Update "+oldCourseNo+" "+couNo+","+couName+","+couLength+","+couPoint+","+couPlace+","+couCoTea+","+couTA+","+couPro+"\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		if(reply.equals("True")){
			System.out.println("Course "+couNo+ "is updated");
			return true;
		}else{
			return false;
		}		
	}
	public static String[] requestClaStu(String selectCouNo) {
		String[] stuList = null;
		try {
			Client.buffw.write("RecordScore "+selectCouNo+"\r\n");
			Client.buffw.flush();
			System.out.println("Request student send!\r\nRecordScore "+selectCouNo+"\r\n");
			stuList = Client.buffr.readLine().split(" ");
			if(stuList[0].equals("NoStu")){
				return null;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return null;
		}
		
		return stuList;
	}
	public static boolean recordScore(String selectCouNo,String[] scoreRecord) {
		String reply = "False";
		try {
			Client.buffw.write("Record  "+selectCouNo+"  ");
			for(int i = 0;i<scoreRecord.length;i++){
				Client.buffw.write(scoreRecord[i]+" ");
			}
			Client.buffw.write("\r\n");
			Client.buffw.flush();
			reply = Client.buffr.readLine();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;
		}
		if(reply.equals("True")){
			System.out.println("score recorded!");
			return true;
		}else{
			return false;
		}
		
		
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Client.initialClient();		
		LoginUI loginUI = new LoginUI();
		loginUI.init();
		while(!(Client.loginAdmin||Client.loginStu||Client.loginTea));		
		System.out.println(loginUI.getAccountNo()+"  "+loginUI.getAccountPass()+"  Login Complete");
		
		if((Client.loginAdmin)&&(!Client.loginStu)&&(!Client.loginTea)){
			Vector<String> teaNo = new Vector<String>();
			Vector<String> teaName = new Vector<String>();
			
			Vector<String> stuNo = new Vector<String>();
			Vector<String> stuName = new Vector<String>();
			
			Vector<String> claNo = new Vector<String>();
			Vector<String> claName = new Vector<String>();
			Vector<String> claTime = new Vector<String>();
			Vector<String> claPlace = new Vector<String>();
			
			String[] aPro = Client.getAPackage();			
			System.out.println("----receive----");
			System.out.println("getTeacherPro:");
			for(int i = 0;i<aPro.length;i++){
				System.out.println(aPro[i]);
			}			
			for(int i = 0;i<aPro.length;i++){
				String[] temp = aPro[i].split(";");
				teaNo.addElement(temp[0]);
				teaName.addElement(temp[1]);				
			}			
			aPro = Client.getAPackage();			
			System.out.println("getStudentPro:");
			for(int i = 0;i<aPro.length;i++){
				System.out.println(aPro[i]);
			}			
			for(int i = 0;i<aPro.length;i++){
				String[] temp = aPro[i].split(";");
				stuNo.addElement(temp[0]);
				stuName.addElement(temp[1]);
			}
			aPro = Client.getAPackage();
			System.out.println("getCoursePro:");
			for(int i = 0;i<aPro.length;i++){
				System.out.println(aPro[i]);
			}
			System.out.println("----receive------");
			//Course Profile
			for(int i = 0;i<aPro.length;i++){
				String[] temp = aPro[i].split(";");
				claNo.addElement(temp[0]);
				claName.addElement(temp[1]);
				claTime.addElement(temp[2]);
				claPlace.addElement(temp[3]);
			}			
			AdminUI adminUI = new AdminUI(teaNo,teaName,stuNo,stuName,claNo,claName,claTime,claPlace);
			adminUI.inita();
		}else if((!Client.loginAdmin)&&(Client.loginStu)&&(!Client.loginTea)){
			//do student
			Vector<String> myCourseNo = new Vector<String>();
			Vector<String> myCourseName = new Vector<String>();
			Vector<String> myCourseScore = new Vector<String>();
			
			
			Vector<Vector<String>> allCoursePro = new Vector<Vector<String>>();
						
			String[] aPro = Client.getAPackage();
			System.out.println("----receive-----");
			System.out.println("getMyCourse:");
			for(int i = 0;i<aPro.length;i++){
				System.out.println(aPro[i]);
			}			
			for(int i = 0;i<aPro.length;i++){
				String[] temp = aPro[i].split(";");
				myCourseNo.addElement(temp[0]);
				myCourseScore.addElement(temp[1]);
				myCourseName.addElement(temp[2]);
			}
			aPro = Client.getAPackage();			
			System.out.println("getAllCoursePro:");
			for(int i = 0;i<aPro.length;i++){
				System.out.println(aPro[i]);
			}
			for(int i = 0;i<aPro.length;i++){
				String[] temp = aPro[i].split(",");
				Vector<String> item = new Vector<String>();
				for(int j = 0;j<temp.length;j++){
					item.addElement(temp[j]);
				}
				allCoursePro.addElement(item);
			}
			System.gc();
			System.out.println("----receive-----");
			StudentOpUI stu = new StudentOpUI(myCourseNo,myCourseName,myCourseScore,allCoursePro);
			stu.init();
			
			
		}else if((!Client.loginAdmin)&&(!Client.loginStu)&&(Client.loginTea)){
			//do teacher
			Vector<String> myCourseNo = new Vector<String>();
			Vector<String> myCourseName = new Vector<String>();
			Vector<String> myCourseTime = new Vector<String>();
			Vector<String> myCoursePlace = new Vector<String>();
			Vector<Vector<String>> allCoursePro = new Vector<Vector<String>>();
			
			String[] aPro = Client.getAPackage();
			
			System.out.println("----receive-----");
			System.out.println("getMyCourse:");
			for(int i = 0;i<aPro.length;i++){
				System.out.println(aPro[i]);
			}			
			for(int i = 0;i<aPro.length;i++){
				String[] temp = aPro[i].split(";");
				myCourseNo.addElement(temp[0]);
				myCourseName.addElement(temp[1]);
				myCourseTime.addElement(temp[2]);
				myCoursePlace.addElement(temp[3]);
			}
			aPro = Client.getAPackage();			
			System.out.println("getAllCoursePro:");
			for(int i = 0;i<aPro.length;i++){
				System.out.println(aPro[i]);
			}
			for(int i = 0;i<aPro.length;i++){
				String[] temp = aPro[i].split(",");
				Vector<String> item = new Vector<String>();
				for(int j = 0;j<temp.length;j++){
					item.addElement(temp[j]);
				}
				allCoursePro.addElement(item);
			}
			System.gc();
			System.out.println("----receive-----");
			TeacherOpUI tea = new TeacherOpUI(myCourseNo,myCourseName,myCourseTime,myCoursePlace,allCoursePro);
			tea.initialTeaMainF();
			
		}else{
			//report error
		}
		
	}
	
	
	
	
	
	
	

}
