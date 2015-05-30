package theBigHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
//menu split by one space
	public static void showMenu() {
		System.out.println("-------Top Menu------");
		System.out.println("1.   Login Admin [key]");
		System.out.println("2.   Login Teacher [TeacherNumber] [Key]");
		System.out.println("3.   Login Student [StudentAccount] [Key]");
		System.out.println("4.   Register Teacher [TeacherNumber] [TeacherName] [Key]");
		System.out.println("5.   Register Student [StudentNumber] [StudentName] [Key]");
		System.out.println("6.   Exit");
		System.out.println("Please input your command...");
		System.out.println("---------------------");
	}

	public static void showAdminMenu() {
		System.out.println("------Admin Menu-----");
		System.out.println("1.   ChangePassword Admin [newkey]");
		System.out.println("2.   Show [list name]");
		System.out.println("3.   Delete [list name] [Line index] [Item index]");
		System.out.println("4.   delete Teacher [TeacherNumber]");
		System.out.println("5.   delete Student [StudentNumber]");
		System.out.println("6.   delete Course [courseNumber]");
		System.out.println("7.   Exit Admin");
		System.out.println("Please input your command...");
		System.out.println("---------------------");
	}
	
	public static void showTeacherMenu(){
		System.out.println("----Teacher Menu-----");
		System.out.println("1.   ChangePassword [newkey]");
		System.out.println("2.   Publish  ");
		System.out.println("3.   Update [courseNumber]");
		System.out.println("4.   Show course [courseNumber]");
		System.out.println("5.   Show student [courseNumber]");
		System.out.println("5.   Record score [courseNumber]");
		System.out.println("6.   Exit Teacher");
		System.out.println("Please input your command...");
		System.out.println("---------------------");
	}
	
	public static void showStudentMenu(){
		System.out.println("----Student Menu-----");
		System.out.println("1.   ChangePassword [newkey]");
		System.out.println("2.   Show mycourseList");
		System.out.println("3.   Select course [courseNumber]");
		System.out.println("4.   Quit course [courseNumber]");
		System.out.println("5.   Show score [courseNumber]");
		System.out.println("6.   Exit Student");
		System.out.println("Please input your command...");
		System.out.println("---------------------");
	}
	private static void useAdminMenu(Administer admin) throws Throwable{
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				System.in));
		administer: while (true) {
			showAdminMenu();
			//get command
			String command2 = buff.readLine();
			// change key
			if (command2.startsWith("ChangePassword Admin")) {
				String[] changeKey = command2.split(" ");
				if (changeKey.length == 3) {
					admin.changeAdminKey(changeKey[2]);
				} else {
					System.out.println("command ChangePassword illigal!");
				}
			}
			// Show
			else if (command2.startsWith("Show ")) {
				String[] showComm = command2.split(" ");
				if (showComm.length == 2) {
					admin.lookList(showComm[1]);
				} else {
					System.out
							.println("command show illegal!");
				}
			}
			// Delete
			else if (command2.startsWith("Delete ")) {
				String[] deleteComm = command2.split(" ");

				if (deleteComm.length == 4) {
					Integer itemIndex = Integer
							.parseInt(deleteComm[2]);
					Integer itemNum = Integer
							.parseInt(deleteComm[3]);
					admin.deleteItem(deleteComm[1],
							itemIndex, itemNum);
				} else {
					System.out
							.println("command delete illegal!");
				}
			}
			//delete teacher
			else if (command2.startsWith("delete Teacher ")) {
				String[] deleteComm = command2.split(" ");

				if (deleteComm.length == 3) {
					admin.deleteTea(deleteComm[2]);
				} else {
					System.out
							.println("command delete illegal!");
				}
			}//delete student
			else if (command2.startsWith("delete Student ")) {
				String[] deleteComm = command2.split(" ");

				if (deleteComm.length == 3) {
					admin.deleteStu(deleteComm[2]);
				} else {
					System.out
							.println("command delete illegal!");
				}
			}//delete course
			else if (command2.startsWith("delete Course")) {
				String[] deleteComm = command2.split(" ");

				if (deleteComm.length == 3) {
					admin.deleteCourse(deleteComm[2]);
				} else {
					System.out
							.println("command delete illegal!");
				}
			} else if (command2.equals("Exit Admin")) {
				admin = null;
				break administer;
			} else {
				System.out
						.println("Admin command illegal!");
			}
		}
	}
	private static void useTeaMenu(Teacher tea) throws IOException {
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				System.in));
		teacher:while(true){
			Console.showTeacherMenu();
			//get command
			String command2 = buff.readLine();
			// change key
			if (command2.startsWith("ChangePassword ")) {
				String[] changeKey = command2.split(" ");
				if (changeKey.length == 2) {
					tea.changeteacherKey(changeKey[1]);
				} else {
					System.out.println("command ChangePassword illigal!");
				}
			}else if(command2.startsWith("Publish")){
				boolean a = tea.publish();
				if(a == true){
					System.out.println("Operation succeeded!");
				}else{
					System.out.println("Operation failed");
				}
			}else if(command2.startsWith("Update")){
				String[] course = command2.split(" ");
				if(course.length == 2){
					boolean a = tea.updateCourse(course[1]);
					if(a == true){
						System.out.println("Operation succeeded!");
					}else{
						System.out.println("Operation failed");
					}
				}else{
					System.out.println("command Update illigal!");
				}				
			}else if(command2.startsWith("Show course ")){
				String[] course = command2.split(" ");
				if(course.length == 3){
					boolean a = tea.showCourse(course[2]);
					if(a == true){
						System.out.println("Operation succeeded!");
					}else{
						System.out.println("Operation failed");
					}
				}else{
					System.out.println("command show course illigal!");
				}	
			}else if(command2.startsWith("Show student ")){
				String[] course = command2.split(" ");
				if(course.length == 3){
					boolean a = tea.showStudent(course[2]);
					if(a == true){
						System.out.println("Operation succeeded!");
					}else{
						System.out.println("Operation failed");
					}
				}else{
					System.out.println("command show student illigal!");
				}	
			}else if(command2.startsWith("Record score ")){
				String[] course = command2.split(" ");
				if(course.length == 3){
					boolean a = tea.recordScore(course[2]);
					if(a == true){
						System.out.println("Operation succeeded!");
					}else{
						System.out.println("Operation failed");
					}
				}else{
					System.out.println("command Record score illigal!");
				}	
			}else if(command2.startsWith("Exit Teacher")){
				tea=null;
				break teacher;
			}else{
				System.out.println("Teacher command illegal!");
			}
		}
		
	}
	private static void useStuMenu(Student stu) throws IOException{
		BufferedReader buff = new BufferedReader(new InputStreamReader(
				System.in));
		student:while(true){
			Console.showStudentMenu();
			//get command
			String command2 = buff.readLine();
			// change key
			if (command2.startsWith("ChangePassword ")) {
				String[] changeKey = command2.split(" ");
				if (changeKey.length == 2) {
					stu.changeStudentKey(changeKey[1]);
				} else {
					System.out.println("command ChangePassword illigal!");
				}
			}else if(command2.startsWith("Show mycourseList")){
				stu.showMycourse();
			}else if(command2.startsWith("Select course ")){
				String[] course = command2.split(" ");
				if(course.length == 3){
					boolean a = stu.selectCourse(course[2]);
					if(a == true){
						System.out.println("Operation succeeded!");
					}else{
						System.out.println("Operation failed");
					}
				}else{
					System.out.println("command Select course illigal!");
				}				
			}else if(command2.startsWith("Quit course ")){
				String[] course = command2.split(" ");
				if(course.length == 3){
					String a = stu.quitCourse(course[2]);
					if(a.equals("True")){
						System.out.println("Operation succeeded!");
					}else if(a.equals("False")){
						System.out.println("Operation failed");
					}
				}else{
					System.out.println("command Quit course illigal!");
				}	
			}else if(command2.startsWith("Show score ")){
				String[] course = command2.split(" ");
				if(course.length == 3){
					stu.showScore(course[2]);
				}else{
					System.out.println("command Show score illigal!");
				}	
			}else if(command2.startsWith("Exit Student")){
				stu=null;
				break student;
			}else{
				System.out.println("Student command illegal!");
			}
		}
		
	}

	public static void main(String[] args) throws Throwable {
		first: while (true) {

			showMenu();
			BufferedReader buff = new BufferedReader(new InputStreamReader(
					System.in));
			String command = buff.readLine();
			// Command
			if (!command.isEmpty()) {
				// login administer
				if (command.startsWith("Login Admin")) {
					String[] comLog = command.split(" ");
					if (comLog.length == 3) {
						// try login admin
						if (Logger.adminLogin("Admin", comLog[2])) {
							Administer admin = new Administer(comLog[2]);
							System.out.println("Login successed.You are Admin\r\nNow the key is "+ admin.getKey());
							Console.useAdminMenu(admin);
						} else if(!Logger.adminLogin("Admin", comLog[2])){
							System.out.println("Wrong key!");
						}else{
							
						}
					} else {
						System.out.println("command Login illegal!");
					}

				}
				else if (command.startsWith("Login Teacher ")) {
					String[] comLog = command.split(" ");
					if (comLog.length == 4) {						
						// try login Teacher
						if (Logger.teaLogin(comLog[2], comLog[3])) {
							Teacher tea = new Teacher(comLog[2],comLog[3]);
							System.out.println("Teacher successfully created!You are:"+comLog[2]+"  Key:"+comLog[3]);
							Console.useTeaMenu(tea);						
						}else{
							System.out.println("Login failed");
						}
					}else {
						System.out.println("command Login illegal!");
					}
				}else if(command.startsWith("Login Student ")){
					String[] comLog = command.split(" ");
					if (comLog.length == 4) {
						if (Logger.stuLogin(comLog[2], comLog[3])) {
							Student stu = new Student(comLog[2],comLog[3]);
							System.out.println("Student successfully created!You are:"+comLog[2]+"  Key:"+comLog[3]);
							Console.useStuMenu(stu);
						}else{
							System.out.println("Login failed");
						}
					}else {
						System.out.println("command Login illegal!");
					}
				}else if(command.startsWith("Register Teacher ")){
					String[] comReg = command.split(" ");
					if(comReg.length == 5){
						System.out.println("Teacher:  "+comReg[2]+"  name:"+comReg[3]+"  Key:"+comReg[4]+"  comfirm Register?[Y/N]");
						String confirm = buff.readLine();
						if(confirm.equals("Y")){
							Register.teaRegister(comReg[2], comReg[3],comReg[4]);
							System.out.println("Register succeeded!");
						}else if(confirm.equals("N")){
							System.out.println("Register cancealed");
						}else{
							System.out.println("Bad input!Register cancealed");
						}
					}else{
						System.out.println("Register command illegal!");
					}					
				}else if(command.startsWith("Register Student ")){
					String[] comReg = command.split(" ");
					if(comReg.length == 5){
						System.out.println("Student:  "+comReg[2]+"  name:"+comReg[3]+"  Key:"+comReg[4]+"  comfirm Register?[Y/N]");
						String confirm = buff.readLine();
						if(confirm.equals("Y")){
							Register.stuRegister(comReg[2], comReg[3],comReg[4]);
							System.out.println("Register succeeded!");
						}else if(confirm.equals("N")){
							System.out.println("Register cancealed");
						}else{
							System.out.println("Bad input!Register cancealed");
						}
					}else{
						System.out.println("Register command illegal!");
					}
					
				}else if (command.equals("Exit")) {
					break first;
				} else {
					System.out.println(command);
					System.out.println("command illegal!");
				}
			} else {
				System.out.println("command empty!");
			}
			System.gc();
			
		}

	}

	

}
