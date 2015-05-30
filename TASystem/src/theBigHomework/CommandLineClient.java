package theBigHomework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommandLineClient {
	private static Socket s = null;
	private static BufferedWriter buffw;
	private static BufferedReader buffr;
	
	private static void initial() {
		// TODO Auto-generated method stub
		try {
			CommandLineClient.s = new Socket("Localhost",30000);
			s.setSoTimeout(5000);
			CommandLineClient.buffw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			CommandLineClient.buffr = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot connect to server!");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot connect to server!");
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
			System.out.println("Cannot close!");
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	//menu split by one space
		private static void showMenu() {
			System.out.println("-------Top Menu------");
			System.out.println("1.   Login Admin [key]");
			System.out.println("2.   Login Teacher [TeacherNumber] [Key]");
			System.out.println("3.   Login Student [StudentAccount] [Key]");
			System.out.println("4.  工事中- Register Teacher [TeacherNumber] [TeacherName] [Key]");
			System.out.println("5.  工事中- Register Student [StudentNumber] [StudentName] [Key]");
			System.out.println("6.   Exit");
			System.out.println("Please input your command...");
			System.out.println("---------------------");
		}

		private static void showAdminMenu() {
			System.out.println("------Admin Menu-----");
			System.out.println("1.   ChangePassword Admin [newkey]");
			System.out.println("2.   工事中--Show [list name]");
			System.out.println("3.   工事中--Delete [list name] [Line index] [Item index]");
			System.out.println("4.   delete Teacher [TeacherNumber]");
			System.out.println("5.   delete Student [StudentNumber]");
			System.out.println("6.   delete Course [courseNumber]");
			System.out.println("7.   Exit Admin");
			System.out.println("Please input your command...");
			System.out.println("---------------------");
		}
		
		private static void showTeacherMenu(){
			System.out.println("----Teacher Menu-----");
			System.out.println("1.   ChangePassword [newkey]");
			System.out.println("2.   Publish  ");
			System.out.println("3.   Update oldCourseNo courseNo,courseName,courseLength,coursePoint,coursePlace,coTeacher;coTeacher;......,ta;ta;ta.....,courseProperty\r\n");
			System.out.println("4.   Show student [courseNumber]");
			System.out.println("5.   Record score [courseNumber]");
			System.out.println("6.   Exit Teacher");
			System.out.println("Please input your command...");
			System.out.println("---------------------");
		}
		
		private static void showStudentMenu(){
			System.out.println("----Student Menu-----");
			System.out.println("1.   ChangePassword [newkey]");
			System.out.println("2.   Select course [courseNumber]");
			System.out.println("3.   Quit course [courseNumber]");
			System.out.println("4.   Exit Student");
			System.out.println("Please input your command...");
			System.out.println("---------------------");
		}
		private static void useAdminMenu() throws Throwable{
			showAdminMenu();
			System.out.println(CommandLineClient.buffr.readLine());
			System.out.println(CommandLineClient.buffr.readLine());
			System.out.println(CommandLineClient.buffr.readLine());
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

						CommandLineClient.buffw.write("ChangePassword "+changeKey[2]+"\r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Change succeeded,now key is "+changeKey[2]);
						}else{
							System.out.println("Change failed!");
						}
					} else {
						System.out.println("command ChangePassword illigal!");
					}
				}
				//delete teacher
				else if (command2.startsWith("delete Teacher ")) {
					String[] deleteComm = command2.split(" ");
					if (deleteComm.length == 3) {
						CommandLineClient.buffw.write("Delete Teacher "+deleteComm[2]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Delete "+deleteComm[2]+" succeeded");
						}else{
							System.out.println("Delete failed!");
						}						
					} else {
						System.out
								.println("command delete illegal!");
					}
				}//delete student
				else if (command2.startsWith("delete Student ")) {
					String[] deleteComm = command2.split(" ");
					if (deleteComm.length == 3) {
						CommandLineClient.buffw.write("Delete Student "+deleteComm[2]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Delete "+deleteComm[2]+" succeeded");
						}else{
							System.out.println("Delete failed!");
						}
					} else {
						System.out
								.println("command delete illegal!");
					}
				}//delete course
				else if (command2.startsWith("delete Course")) {
					String[] deleteComm = command2.split(" ");
					if (deleteComm.length == 3) {
						CommandLineClient.buffw.write("Delete Course "+deleteComm[2]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Delete "+deleteComm[2]+" succeeded");
						}else{
							System.out.println("Delete failed!");
						}
					} else {
						System.out
								.println("command delete illegal!");
					}
				} else if (command2.equals("Exit Admin")) {
					break administer;
				} else {
					System.out
							.println("Admin command illegal!");
				}
			}
		}
		private static void useTeaMenu() throws IOException {
			showTeacherMenu();
			System.out.println(CommandLineClient.buffr.readLine());
			System.out.println(CommandLineClient.buffr.readLine());
			BufferedReader buff = new BufferedReader(new InputStreamReader(
					System.in));
			teacher:while(true){
				showTeacherMenu();
				//get command
				String command2 = buff.readLine();
				// change key
				if (command2.startsWith("ChangePassword ")) {
					String[] changeKey = command2.split(" ");
					if (changeKey.length == 2) {
						CommandLineClient.buffw.write("ChangePassword "+changeKey[1]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Change succeeded,now key is "+changeKey[2]);
						}else{
							System.out.println("Change failed!");
						}
					} else {
						System.out.println("command ChangePassword illigal!");
					}
				}else if(command2.startsWith("Publish")){
					System.out.println("Please input in this format...[at least one Teacher][if no TA,input null]\r\n" +
							"Publish courseNo,courseName,courseLength,coursePoint,coursePlace,TeacherNo;TeacherNo;......,TANo;TANo;TANo.....,courseProperty\r\n"
							);
					String pub = buff.readLine();
					String[] course = pub.split(" ");
					if(course.length == 2){
						CommandLineClient.buffw.write(pub+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Publish succeeded");
						}else{
							System.out.println("Publish failed!");
						}
					}else{
						System.out.println("command Publish illigal!");
					}					
				}else if(command2.startsWith("Update")){
					String[] course = command2.split(" ");
					if(course.length == 3){
						CommandLineClient.buffw.write(command2+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Update succeeded");
						}else{
							System.out.println("Update failed!");
						}
					}else{
						System.out.println("command Update illigal!");
					}				
				}else if(command2.startsWith("Show student ")){
					String[] course = command2.split(" ");
					if(course.length == 3){
						CommandLineClient.buffw.write("RecordScore "+course[2]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						System.out.println(reply);
					}else{
						System.out.println("command show student illigal!");
					}	
				}else if(command2.startsWith("Record score ")){
					String[] course = command2.split(" ");
					if(course.length == 3){
						CommandLineClient.buffw.write("RecordScore "+course[2]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						System.out.println(reply);						
						if(reply.equals("NoStu")){
							System.out.println("No student under this course!");
						}else{
							System.out.println("write down score one by one in format...\r\n" +
								"Record  courseNo  score score score...\r\n"
								);
							String record = buff.readLine();
							CommandLineClient.buffw.write(record+" \r\n");
							CommandLineClient.buffw.flush();
							reply = CommandLineClient.buffr.readLine();
							if(reply.equals("True")){
								System.out.println("Record succeeded");
							}else{
								System.out.println("Record failed!");
							}
						}
					}else{
						System.out.println("command Record score illigal!");
					}	
				}else if(command2.startsWith("Exit Teacher")){
					break teacher;
				}else{
					System.out.println("Teacher command illegal!");
				}
			}
			
		}
		private static void useStuMenu() throws IOException{
			showStudentMenu();			
			System.out.println(CommandLineClient.buffr.readLine());
			System.out.println(CommandLineClient.buffr.readLine());
			BufferedReader buff = new BufferedReader(new InputStreamReader(
					System.in));
			student:while(true){
				showStudentMenu();
				//get command
				String command2 = buff.readLine();
				// change key
				if (command2.startsWith("ChangePassword ")) {
					String[] changeKey = command2.split(" ");
					if (changeKey.length == 2) {
						CommandLineClient.buffw.write("ChangePassword "+changeKey[1]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Change succeeded,now key is "+changeKey[2]);
						}else{
							System.out.println("Change failed!");
						}
					} else {
						System.out.println("command ChangePassword illigal!");
					}		
				}else if(command2.startsWith("Quit course ")){
					String[] course = command2.split(" ");
					if(course.length == 3){
						CommandLineClient.buffw.write("Quit "+course[2]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Operation succeeded!");
						}else if(reply.equals("False")){
							System.out.println("Operation failed");
						}
					}else{
						System.out.println("command Quit course illigal!");
					}	
				}else if(command2.startsWith("Select course ")){
					String[] course = command2.split(" ");
					if(course.length == 3){
						CommandLineClient.buffw.write("Select "+course[2]+" \r\n");
						CommandLineClient.buffw.flush();
						String reply = CommandLineClient.buffr.readLine();
						if(reply.equals("True")){
							System.out.println("Operation succeeded!");
						}else if(reply.equals("False")){
							System.out.println("Operation failed");
						}
					}else{
						System.out.println("command Quit course illigal!");
					}	
				}else if(command2.startsWith("Exit Student")){
					break student;
				}else{
					System.out.println("Student command illegal!");
				}
			}
			
		}

		public static void main(String[] args) throws Throwable {
			CommandLineClient.initial();
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
							CommandLineClient.buffw.write("Admin Admin "+comLog[2]+" \r\n");
							CommandLineClient.buffw.flush();
							String reply = CommandLineClient.buffr.readLine();
							if (reply.equals("Admin")) {
								System.out.println("Login successed.You are Admin\r\nNow the key is "+ comLog[2]);
								CommandLineClient.useAdminMenu();
							} else if(reply.equals("LoginFailed")){
								System.out.println("Wrong key!");
							}else{
								System.out.println("Unknow reply!");
							}
						} else {
							System.out.println("command Login illegal!");
						}

					}
					else if (command.startsWith("Login Teacher ")) {
						String[] comLog = command.split(" ");
						if (comLog.length == 4) {
							CommandLineClient.buffw.write("Teacher "+comLog[2]+" "+comLog[3]+" \r\n");
							CommandLineClient.buffw.flush();
							String reply = CommandLineClient.buffr.readLine();
							// try login Teacher
							if (reply.equals("Teacher")) {
								System.out.println("Teacher successfully created!You are:"+comLog[2]+"  Key:"+comLog[3]);
								CommandLineClient.useTeaMenu();						
							}else if(reply.equals("LoginFailed")){
								System.out.println("Login Failed");
							}
						}else {
							System.out.println("command Login illegal!");
						}
					}else if(command.startsWith("Login Student ")){
						String[] comLog = command.split(" ");
						if (comLog.length == 4) {
							CommandLineClient.buffw.write("Student "+comLog[2]+" "+comLog[3]+" \r\n");
							CommandLineClient.buffw.flush();
							String reply = CommandLineClient.buffr.readLine();
							if (reply.equals("Student")) {
								System.out.println("Student successfully created!You are:"+comLog[2]+"  Key:"+comLog[3]);
								CommandLineClient.useStuMenu();
							}else if(reply.equals("LoginFailed")){
								System.out.println("Login failed");
							}
						}else {
							System.out.println("command Login illegal!");
						}
					}else if (command.equals("Exit")) {
						CommandLineClient.close();
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
