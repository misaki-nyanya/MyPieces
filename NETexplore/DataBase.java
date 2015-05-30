package getURL;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

	private static Connection sm_connect;
	private static Statement sm_state;
	public static void init() throws ClassNotFoundException,SQLException
	{
		Class.forName("org.sqlite.JDBC");
	    sm_connect=DriverManager.getConnection("jdbc:sqlite:CourseSystemServer.db");
	    sm_state=sm_connect.createStatement();
	    execute("create table if not exists Course(id,name,type,marks,faculty_id,teacher_id,classes,description,student_ids,term);");
	}
	public static void exit() throws SQLException
	{
		sm_state.close();
		sm_connect.close();
	}
	protected synchronized static ResultSet execute(String p_sql) throws SQLException
	{
		sm_state.execute(p_sql);
		if(p_sql.toLowerCase().startsWith("select"))
			return sm_state.getResultSet();
		return null;
	}
	public static void addCourse() throws ClassNotFoundException, SQLException, IOException{
		DataBase.init();
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("final_courses_sorted.txt")));
		String temp;
		double ram=Math.random();
		while((temp=br.readLine())!=null){
			String[] list=temp.split("~");
			String id=list[0];
			String name=list[1];
			String type=list[2];
			String marks=list[4];
			String faculty_id=list[3];
			String teacher_id=list[7];
			String classes=list[8];
			String description="实际存在的课程";
			String student_ids="";
			String term;
			String maxStu="N0";
			if((ram>0.2)&&(ram<0.4)){
				term="2012-2013第二学期";
			}else if((ram>0.4)&&(ram<0.6)){
				term="2013-2014第一学期";
			}else if((ram>0.6)&&(ram<0.8)){
				term="2012-2013第一学期";
			}else{
				term="2013-2014第二学期";
			}
			
			ram=Math.random();
			DataBase.execute("insert into Course values('"+id+"','"+name+"','"+type+"','"+marks+"','"+faculty_id+"','"+teacher_id+"','"+classes+"','"+description+"','"+student_ids+"','"+term+"','"+maxStu+"')");
		}
		br.close();		
		DataBase.exit();
	}
	public static void addTeacher() throws ClassNotFoundException, SQLException, IOException{
		DataBase.init();
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("teacher2.txt")));
		String temp;
		double ram=Math.random();
		while((temp=br.readLine())!=null){
			String[] deli1=temp.split(": ");
			String id=deli1[0];
			String name=deli1[0];
			
			String sex;
			if(ram>0.5){sex="男";}
			else{sex="女";}
			
			String[] deli2=deli1[1].split("#");
			String faculty_id=deli2[0];
			
			
			String mailbox="0000@163.com";
			String password="12345678";
			String course_ids=deli2[1];
			DataBase.execute("insert into Teacher values('"+id+"','"+name+"','"+sex+"','"+faculty_id+"','"+mailbox+"','"+password+"','"+course_ids+"')");
			
			ram=Math.random();
			
		}
		br.close();		
		DataBase.exit();
	}
	public static void main(String[] args) throws Exception, SQLException {
		addCourse();
		addTeacher();
	}

}
