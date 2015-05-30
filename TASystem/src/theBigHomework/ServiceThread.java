package theBigHomework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class ServiceThread extends Thread {
	private BufferedReader buffreader;
	private BufferedWriter buffwriter;
	private Socket socket;
	private Student s;
	public ServiceThread(BufferedReader reader,BufferedWriter writer,Socket s){
		this.buffreader = reader;
		this.buffwriter = writer;
		this.socket = s;
	}
	private static boolean checkAdminLogin(String adminName,String adminKey) throws Throwable{
		return Logger.adminLogin(adminName, adminKey);
	}
	private static boolean checkStuLogin(String stuNo,String stuKey) throws Throwable{
		return Logger.stuLogin(stuNo, stuKey);
	}
	private static boolean checkTeaLogin(String teaNo,String teaKey) throws Throwable{
		return Logger.teaLogin(teaNo, teaKey);
	}
	private void adminLogic(String adminPass) throws IOException, CommunicationException{
		System.out.println("Start Admin Logic!");
		Administer a = new Administer(adminPass);
		String AdminCom;
		String[] change;
		String[] delete;
		Vector<String> allTeaNo = a.guiShowTeacher();
		Vector<String> allStuNo = a.guiShowStudent();
		Vector<String> allCourse = a.guiShowCourse();
		Vector<String> allTeaName = a.guiShowTeacherName();
		Vector<String> allStuName = a.guiShowStudentName();
		Vector<String> allCourseName = a.guiShowCourseName();
		Vector<String> allCourseTime = a.guiShowCourseTime();
		Vector<String> allCoursePlace = a.guiShowCoursePlace();
		

		this.buffwriter.write("Teacher  ");
		for(int i = 0;i<allTeaNo.size();i++){
			this.buffwriter.write(allTeaNo.get(i)+";"+allTeaName.get(i)+" ");
		}
		this.buffwriter.write("\r\n");
		this.buffwriter.flush();
		
		this.buffwriter.write("Student  ");
		for(int i = 0;i<allStuNo.size();i++){
			this.buffwriter.write(allStuNo.get(i)+";"+allStuName.get(i)+" ");
		}
		this.buffwriter.write("\r\n");
		this.buffwriter.flush();
		
		this.buffwriter.write("Course  ");
		for(int i = 0;i<allCourse.size();i++){
			this.buffwriter.write(allCourse.get(i)+";"+allCourseName.get(i)+";"+allCourseTime.get(i)+";"+allCoursePlace.get(i)+" ");
		}
		this.buffwriter.write("\r\n");
		this.buffwriter.flush();
		
		while(!this.socket.isClosed()){
			AdminCom = this.buffreader.readLine();
			if(AdminCom.startsWith("ChangePassword ")){
				change = AdminCom.split(" ");
				if(change.length == 2){
					boolean flag = a.changeAdminKey(change[1]);
					if(flag){
						this.buffwriter.write("True\r\n");
						this.buffwriter.flush();
					}else{
						this.buffwriter.write("False\r\n");
						this.buffwriter.flush();
					}
				}else{
					throw new CommunicationException("Admin changekey commend format exception!");
				}
			}else if(AdminCom.startsWith("Delete Teacher ")){
				delete = AdminCom.split(" ");
				if(delete.length == 3){
					Vector<String> couBeDele = a.deleteTea(delete[2]);
					for(int i = 0;i<couBeDele.size();i++){
						this.buffwriter.write(couBeDele.get(i)+" ");
					}
						this.buffwriter.write("\r\n");
						this.buffwriter.flush();
					
				}else{
					throw new CommunicationException("Admin delete commend format exception!");
				}				
			}else if(AdminCom.startsWith("Delete Student ")){
				delete = AdminCom.split(" ");
				if(delete.length == 3){
					boolean success = a.deleteStu(delete[2]);
					if(success){
						this.buffwriter.write("True\r\n");
						this.buffwriter.flush();
					}else{
						this.buffwriter.write("False\r\n");
						this.buffwriter.flush();
					}
				}else{
					throw new CommunicationException("Admin delete commend format exception!");
				}
			}else if(AdminCom.startsWith("Delete Course ")){
				delete = AdminCom.split(" ");
				if(delete.length == 3){
					boolean success = a.deleteCourse(delete[2]);
					if(success){
						this.buffwriter.write("True\r\n");
						this.buffwriter.flush();
					}else{
						this.buffwriter.write("False\r\n");
						this.buffwriter.flush();
					}
				}else{
					throw new CommunicationException("Admin delete commend format exception!");
				}
			}else if(AdminCom.startsWith("ShowStu ")){
				delete = AdminCom.split(" ");
				if(delete.length == 2){
					s = new Student(delete[1],"");
					Vector<String> couList = s.getMyCourseNo();
					if((couList.size()==0)||(couList.get(0).equals(""))||(couList.get(0).equals(" "))||(couList.get(0).equals("   "))){
						this.buffwriter.write("NoCou\r\n");
						this.buffwriter.flush();
					}else{
						for(int i = 0;i<couList.size();i++){
							this.buffwriter.write(couList.get(i)+" ");
						}
						this.buffwriter.write("\r\n");
						this.buffwriter.flush();						
					}
				}else{
					throw new CommunicationException("Admin showCourse commend format exception!");
				}
					
			}else if(AdminCom.startsWith("Quit ")){				
				delete = AdminCom.split(" ");
				if(delete.length == 2){
					String success = s.quitCourse(delete[1]);				
					if(success.equals("True")){
						this.buffwriter.write("True\r\n");
						this.buffwriter.flush();
					}else if(success.equals("False")){
						this.buffwriter.write("False\r\n");
						this.buffwriter.flush();
					}
				}else{
					throw new CommunicationException("Admin quit commend format exception!");
				}
			}
		}		
	}
	
	private void teaLogic(String teaNo,String teaPass) throws IOException, CommunicationException{
		Teacher t = new Teacher(teaNo,teaPass);
		String teaCom;
		String[] command;
		String[] publish;
		Vector<String> tempForTrans;
		Vector<String> myCourse = t.getMyCourseList();
		Vector<String> myCourseName = t.getMyCourseName();
		Vector<String> myCourseTime = t.getMyCourseTime();
		Vector<String> myCoursePlace = t.getMyCoursePlace();
		Vector<String> allCoursePro = t.guiShowAllCoursePor();
		
		this.buffwriter.write("TeacherCourse  ");
		for(int i = 0;i<myCourse.size();i++){
			this.buffwriter.write(myCourse.get(i)+";"+myCourseName.get(i)+";"+myCourseTime.get(i)+";"+myCoursePlace.get(i)+" ");
		}
		this.buffwriter.write("\r\n");
		this.buffwriter.flush();
		this.buffwriter.write("Course  ");
		for(int i = 0;i<allCoursePro.size();i++){
			this.buffwriter.write(allCoursePro.get(i));
		}
		this.buffwriter.write("\r\n");
		this.buffwriter.flush();
		
		while(!this.socket.isClosed()){
			teaCom = this.buffreader.readLine();
			if(teaCom.startsWith("ChangePassword ")){
				command = teaCom.split(" ");
				if(command.length == 2){
					boolean flag = t.changeteacherKey(command[1]);
					if(flag){
						this.buffwriter.write("True\r\n");
						this.buffwriter.flush();
					}else{
						this.buffwriter.write("False\r\n");
						this.buffwriter.flush();
					}
				}else{
					throw new CommunicationException("Teacher changekey commend format exception!");
				}
			}else if(teaCom.startsWith("Publish ")){
				publish = teaCom.split(" ");
				String[] vars = publish[1].split(",");
				if((publish.length == 2)&&(vars.length == 8)){					
					String[] coTea = vars[5].split(";");
					String[] ta = vars[6].split(";");
					boolean success = t.guiPublish(vars[0], vars[1], vars[2], vars[3], vars[4], coTea, ta, vars[7]);
					if(!success){
						this.buffwriter.write("False\r\n");
					}else{
						this.buffwriter.write("True\r\n");
					}
					this.buffwriter.flush();
				}else{
					throw new CommunicationException("Teacher publish commend format exception!");
				}				
			}else if(teaCom.startsWith("ShowStudent ")){
				command = teaCom.split(" ");
				if(command.length == 2){
					tempForTrans = t.getCourseStu(command[1]);
					for(int i = 0;i<tempForTrans.size();i++){
						this.buffwriter.write(tempForTrans.get(i)+" ");
					}
					this.buffwriter.write("\r\n");
					this.buffwriter.flush();
				}else{
					throw new CommunicationException("Teacher showStu commend format exception!");
				}
			}else if(teaCom.startsWith("UpdateCourse ")){
				publish = teaCom.split(" ");
				String[] vars = publish[2].split(",");
				if((publish.length == 3)&&(vars.length == 8)){					
					String[] coTea = vars[5].split(";");
					String[] ta = vars[6].split(";");
					boolean success = t.guiUpdate(publish[1],vars[0], vars[1], vars[2], vars[3], vars[4], coTea, ta, vars[7]);
					if(!success){
						this.buffwriter.write("False\r\n");
					}else{
						this.buffwriter.write("True\r\n");
					}
					this.buffwriter.flush();
				}else{
					throw new CommunicationException("Teacher update commend format exception!");
				}
			}else if(teaCom.startsWith("RecordScore ")){
				System.out.println("recordlogic get: "+teaCom);
				command = teaCom.split(" ");				
				if(command.length == 2){
					Vector<String> stuNo = t.getCourseStu(command[1]);
					if(stuNo.size() == 0){
						this.buffwriter.write("NoStu\r\n");
						this.buffwriter.flush();
					}else{
						for(int i = 0;i<stuNo.size();i++){
							this.buffwriter.write(stuNo.get(i)+" ");
						}
						this.buffwriter.write("\r\n");
						this.buffwriter.flush();						
					}
					
				}else{
					throw new CommunicationException("Teacher recordScore commend format exception!");
				}
			}else if(teaCom.startsWith("Record  ")){
				System.out.println("recordlogic get: "+teaCom);
				command = teaCom.split("  ");
				if(command.length == 3){
					String[] score =command[2].split(" ");				
					boolean success = t.guiRecordScore(command[1], score);
					if(!success){
						this.buffwriter.write("False\r\n");
					}else{
						this.buffwriter.write("True\r\n");
					}
					this.buffwriter.flush();
				}
			}else{
					throw new CommunicationException("Teacher recordScore commend format exception!");				
			}
		}		
	}
	private void stuLogic(String stuNo,String stuPass) throws IOException, CommunicationException{
		Student s = new Student(stuNo,stuPass);
		Vector<String> myCourseNo = s.getMyCourseNo();
		Vector<String> myCourseName = s.getMyCourseName();
		Vector<String> myCourseScore = s.getMyScore();
//		Vector<String> allCourse = s.getAllCourse();
//		Vector<String> allCourseName = s.getAllCourseName();
		Vector<String> allCoursePro = s.getAllCoursePor();
		String stuCom;
		String[] command;
		this.buffwriter.write("StudentCourse  ");
		for(int i = 0;i<myCourseNo.size();i++){
			this.buffwriter.write(myCourseNo.get(i)+";"+myCourseScore.get(i)+";"+myCourseName.get(i)+" ");
		}
		this.buffwriter.write("\r\n");
		this.buffwriter.flush();
		this.buffwriter.write("Course  ");
//		for(int i = 0;i<allCourse.size();i++){
//			this.buffwriter.write(allCourse.get(i)+";"+allCourseName.get(i)+" ");
//		}
		for(int i = 0;i<allCoursePro.size();i++){
			this.buffwriter.write(allCoursePro.get(i));
		}
		this.buffwriter.write("\r\n");
		this.buffwriter.flush();
		
		while(!this.socket.isClosed()){
			stuCom = this.buffreader.readLine();
			if(stuCom.startsWith("ChangePassword ")){
				command = stuCom.split(" ");
				if(command.length == 2){
					boolean flag = s.changeStudentKey(command[1]);
					if(flag){
						this.buffwriter.write("True\r\n");
						this.buffwriter.flush();
					}else{
						this.buffwriter.write("False\r\n");
						this.buffwriter.flush();
					}
				}else{
					throw new CommunicationException("Teacher changekey commend format exception!");
				}
			}else if(stuCom.startsWith("Select ")){
				command = stuCom.split(" ");
				if((command.length == 2)){					
					boolean success = s.selectCourse(command[1]);
					if(!success){
						this.buffwriter.write("False\r\n");
					}else{
						this.buffwriter.write("True\r\n");
					}
					this.buffwriter.flush();
				}else{
					throw new CommunicationException("Student select commend format exception!");
				}				
			}else if(stuCom.startsWith("Quit ")){
				command = stuCom.split(" ");
				if((command.length == 2)){					
					String success = s.quitCourse(command[1]);
					if(success.equals("False")){
						this.buffwriter.write("False\r\n");
					}else if(success.equals("True")){
						this.buffwriter.write("True\r\n");
					}
					this.buffwriter.flush();
				}else{
					throw new CommunicationException("Student quit commend format exception!");
				}				
			}
		}
	}
	public void run(){
		System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" connected!");
		String loginCom = null;
		boolean loginFlag = false;
		while(!loginFlag){
			try {
				System.out.println("reading loginCom ");
				loginCom = buffreader.readLine();
				System.out.println("receive loginCom "+loginCom);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
			}
			String[] logintemp = loginCom.split(" ");
			
			if(logintemp[0].equals("Admin")){
				//login
				try {
					loginFlag = ServiceThread.checkAdminLogin(logintemp[1], logintemp[2]);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				if(loginFlag){
					try {
						this.buffwriter.write("Admin\r\n");
						this.buffwriter.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
						return;
					}
					//do admin
					try {
						this.adminLogic(logintemp[2]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
						return;
					} catch (CommunicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						this.buffwriter.write("LoginFailed\r\n");
						this.buffwriter.flush();
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
						return;
					}
				}
			}else if(logintemp[0].equals("Teacher")){
				//login
				try {
					loginFlag = ServiceThread.checkTeaLogin(logintemp[1], logintemp[2]);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				if(loginFlag){
					try {
						this.buffwriter.write("Teacher\r\n");
						this.buffwriter.flush();
					} catch (IOException e1) {
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
						e1.printStackTrace();
						return;
					}
					//do teacher
					try {
						this.teaLogic(logintemp[1], logintemp[2]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
						e.printStackTrace();
						return;
					} catch (CommunicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						this.buffwriter.write("LoginFailed\r\n");
						this.buffwriter.flush();
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
						return;
					}
				}
			}else if(logintemp[0].equals("Student")){
				//login
				try {
					loginFlag = ServiceThread.checkStuLogin(logintemp[1], logintemp[2]);
				} catch (Throwable e) {
					e.printStackTrace();
				}
				if(loginFlag){
					try {
						this.buffwriter.write("Student\r\n");
						this.buffwriter.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
						return;
					}
					//do student
					try {
						this.stuLogic(logintemp[1], logintemp[2]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
					} catch (CommunicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						this.buffwriter.write("LoginFailed\r\n");
						this.buffwriter.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Client From "+this.socket.getRemoteSocketAddress()+" disconnected!");
						return;
					}
					
				}
			}else{
				return;
			}
			
		}
		}
		

}
