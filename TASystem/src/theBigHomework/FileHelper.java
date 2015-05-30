package theBigHomework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class FileHelper {
	public synchronized static String[] readAdmin() throws IOException{
		File admin = new File("D:\\CourseSystemDB\\Admin.txt");
		BufferedReader adminreader = new BufferedReader(new InputStreamReader(new FileInputStream(admin)));
		String temp = adminreader.readLine();
		adminreader.close();
		String[] adminAccount = temp.split("  ");
		return adminAccount;
	}
	public synchronized static Vector<String> readFileByLine(String fileDic) throws IOException{
		File file = new File(fileDic);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		Vector<String> v = new Vector<String>();
		while((line = reader.readLine())!= null){
			v.addElement(line);
		}
		reader.close();
		return v;
	}
	public synchronized static Vector<String> readStuTA(String stuNo) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Student\\Student"+stuNo+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String[] firstLine = reader.readLine().split("  ");
		reader.close();
		Vector<String> v = new Vector<String>();
		for(int i = 3;i<firstLine.length;i++){
			v.addElement(firstLine[i]);
		}		
		return v;
	}
	public synchronized static Vector<String> readStuCourse(String stuNo) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Student\\Student"+stuNo+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		Vector<String> v = new Vector<String>();
		reader.readLine();
		while((line = reader.readLine())!=null){
			String[] temp = line.split("  ");
			v.addElement(temp[0]);
		}
		reader.close();
		return v;
	}	
	public synchronized static Vector<String> readStuScore(String stuNo) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Student\\Student"+stuNo+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		Vector<String> v = new Vector<String>();
		reader.readLine();
		while((line = reader.readLine())!=null){
			String[] temp = line.split("  ");
			v.addElement(temp[1]);
		}
		reader.close();
		return v;
	}
	public synchronized static Vector<String> readStuCourseName(Vector<String> courseNo) throws IOException{
		Vector<String> courseName = new Vector<String>();
		for(int i = 0;i<courseNo.size();i++){
			File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo.get(i)+".txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = reader.readLine();			
			String[] temp = line.split("  ");
			courseName.addElement(temp[1]);
			reader.close();
		}
		return courseName;
	}
	public synchronized static Vector<String> readTeaCourse(String teaNo) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+teaNo+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		Vector<String> v = new Vector<String>();
		reader.readLine();
		while((line = reader.readLine())!=null){
			String[] temp = line.split("  ");
			v.addElement(temp[0]);
		}
		reader.close();
		return v;
	}
	public synchronized static Vector<String> readTeaCourseName(Vector<String> courseNo) throws IOException{
		Vector<String> courseName = new Vector<String>();
		for(int i = 0;i<courseNo.size();i++){
			File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo.get(i)+".txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = reader.readLine();			
			String[] temp = line.split("  ");
			courseName.addElement(temp[1]);
			reader.close();
		}
		return courseName;
	}
	public synchronized static Vector<String> readTeaCourseTime(Vector<String> courseNo) throws IOException{
		Vector<String> courseTime = new Vector<String>();
		for(int i = 0;i<courseNo.size();i++){
			File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo.get(i)+".txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = reader.readLine();			
			String[] temp = line.split("  ");
			courseTime.addElement(temp[2]);
			reader.close();
		}
		return courseTime;
	}
	public synchronized static Vector<String> readTeaCoursePlace(Vector<String> courseNo) throws IOException{
		Vector<String> coursePlace = new Vector<String>();
		for(int i = 0;i<courseNo.size();i++){
			File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo.get(i)+".txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = reader.readLine();			
			String[] temp = line.split("  ");
			coursePlace.addElement(temp[4]);
			reader.close();
		}
		return coursePlace;
	}
	
	public synchronized static Vector<String> readCourseTea(String courseNo) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		reader.readLine();
		String line = reader.readLine();		
		Vector<String> v = new Vector<String>();
		String[] temp = line.split("  ");
		for(int i = 1;i<temp.length;i++){
			v.addElement(temp[i]);
		}
		reader.close();
		return v;
	}
	public synchronized static Vector<String> readCourseTA(String courseNo) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		reader.readLine();
		reader.readLine();
		reader.readLine();
		String line = reader.readLine();		
		Vector<String> v = new Vector<String>();
		String[] temp = line.split("  ");
		for(int i = 1;i<temp.length;i++){
			v.addElement(temp[i]);
		}
		reader.close();
		return v;
	}
	public synchronized static Vector<String> readCourseStu(String courseNo) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		reader.readLine();
		reader.readLine();
		reader.readLine();
		reader.readLine();
		String line;		
		Vector<String> v = new Vector<String>();
		while((line = reader.readLine())!=null){
			String[] temp = line.split("  ");
			v.addElement(temp[0]);
		}
		reader.close();
		return v;
	}
	public synchronized static Vector<String> readCourseScore(String courseNo) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		reader.readLine();
		reader.readLine();
		reader.readLine();
		reader.readLine();
		String line;		
		Vector<String> v = new Vector<String>();
		while((line = reader.readLine())!=null){
			String[] temp = line.split("  ");
			v.addElement(temp[1]);
		}
		reader.close();
		return v;
	}
	public synchronized static Vector<String> readAllTeacher(){
		File file = new File("D:\\CourseSystemDB\\Teacher");
		String[] tea = file.list();
		Vector<String> teaList = new Vector<String>();
		for(int i = 0;i<tea.length;i++){
			teaList.addElement(tea[i].substring(7, tea[i].lastIndexOf(".txt")));
		}
		return teaList;
	}
	public synchronized static Vector<String> readAllStudent(){
		File file = new File("D:\\CourseSystemDB\\Student");
		String[] stu = file.list();
		Vector<String> stuList = new Vector<String>();
		for(int i = 0;i<stu.length;i++){
			stuList.addElement(stu[i].substring(7, stu[i].lastIndexOf(".txt")));
		}
		return stuList;
	}
	public synchronized static Vector<String> readAllCourse(){
		File file = new File("D:\\CourseSystemDB\\Course");
		String[] cla = file.list();
		Vector<String> claList = new Vector<String>();
		for(int i = 0;i<cla.length;i++){
			claList.addElement(cla[i].substring(0, cla[i].lastIndexOf(".txt")));
		}
		return claList;
	}
	public synchronized static Vector<String> readAllCoursePro() throws IOException{
		File file = new File("D:\\CourseSystemDB\\Course");		
		String[] line;
		File[] cla = file.listFiles();
		Vector<String> allCourse = new Vector<String>();		
		for(int i = 0;i<cla.length;i++){
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cla[i])));
			String property;
			line = reader.readLine().split("  ");
			String item = line[0]+","+line[1]+","+line[2]+","+line[3]+","+line[4]+",";
			line = reader.readLine().split("  ");			
			for(int j = 1;j<line.length-1;j++){
				item = item+line[j]+";";
			}
			item = item + line[line.length-1]+",";			
						
			property = reader.readLine();//temporary store course property
			
			line = reader.readLine().split("  ");
			for(int j = 1;j<line.length-1;j++){
				item = item+line[j]+";";
			}
			item = item + line[line.length-1]+",";			
						
			line = property.split("  ");
			item = item+line[1]+" ";
			reader.close();
			allCourse.add(item);
		}
		
		return allCourse;
	}
	public synchronized static Vector<String> readAllCourseNum() throws IOException{
		File file = new File("D:\\CourseSystemDB\\courseList.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		Vector<String> v = new Vector<String>();
		while((line = reader.readLine())!= null){
			String[] temp = line.split("  ");
			v.addElement(temp[0]);
		}
		reader.close();
		return v;
	}
	public synchronized static Vector<String> readAllTeacherName() throws IOException{
		File file = new File("D:\\CourseSystemDB\\Teacher");
		String[] tea = file.list();
		Vector<String> teaNameList = new Vector<String>();
		String[] firstLine;
		for(int i = 0;i<tea.length;i++){
			File teaFile = new File("D:\\CourseSystemDB\\Teacher\\"+tea[i]);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(teaFile)));
			firstLine = reader.readLine().split("  ");
			teaNameList.addElement(firstLine[1]);
			reader.close();
			System.gc();
		}
		return teaNameList;
	}
	public synchronized static Vector<String> readAllStudentName() throws IOException{
		File file = new File("D:\\CourseSystemDB\\Student");
		String[] stu = file.list();
		Vector<String> stuNameList = new Vector<String>();
		String[] firstLine;
		for(int i = 0;i<stu.length;i++){
			File stuFile = new File("D:\\CourseSystemDB\\Student\\"+stu[i]);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(stuFile)));
			firstLine = reader.readLine().split("  ");
			stuNameList.addElement(firstLine[1]);
			reader.close();
			System.gc();
		}
		return stuNameList;
	}
	public synchronized static Vector<String> readAllCourseName() throws IOException{
		File file = new File("D:\\CourseSystemDB\\Course");
		String[] cla = file.list();
		Vector<String> claNameList = new Vector<String>();
		String[] firstLine;
		for(int i = 0;i<cla.length;i++){
			File claFile = new File("D:\\CourseSystemDB\\Course\\"+cla[i]);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(claFile)));
			firstLine = reader.readLine().split("  ");
			claNameList.addElement(firstLine[1]);
			reader.close();
			System.gc();
		}
		return claNameList;
	}
	public synchronized static Vector<String> readAllCourseTime() throws IOException{
		File file = new File("D:\\CourseSystemDB\\Course");
		String[] cla = file.list();
		Vector<String> claTimeList = new Vector<String>();
		String[] firstLine;
		for(int i = 0;i<cla.length;i++){
			File claFile = new File("D:\\CourseSystemDB\\Course\\"+cla[i]);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(claFile)));
			firstLine = reader.readLine().split("  ");
			claTimeList.addElement(firstLine[2]);
			reader.close();
			System.gc();
		}
		return claTimeList;
	}
	public synchronized static Vector<String> readAllCoursePlace() throws IOException{
		File file = new File("D:\\CourseSystemDB\\Course");
		String[] cla = file.list();
		Vector<String> claPlaceList = new Vector<String>();
		String[] firstLine;
		for(int i = 0;i<cla.length;i++){
			File claFile = new File("D:\\CourseSystemDB\\Course\\"+cla[i]);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(claFile)));
			firstLine = reader.readLine().split("  ");
			claPlaceList.addElement(firstLine[4]);
			reader.close();
			System.gc();
		}
		return claPlaceList;
	}
	
	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
//		String[] tests;
//		Vector<String> testv = new Vector<String>();
//		tests = FileHelper.readAdmin();
//		for(int i = 0;i<tests.length;i++){
//			System.out.println("readAdmin "+tests[i]);
//		}
//		System.out.println("---------------");
//		testv = FileHelper.readAllCourseNum();
//		System.out.println("readAllCourseNum "+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readCourseScore("c0783");
//		System.out.println("readCourseScore "+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readCourseStu("c0783");
//		System.out.println("readCourseStu "+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readCourseTA("c0783");
//		System.out.println("readCourseTA "+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readCourseTea("c0783");
//		System.out.println("readCourseTea "+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readFileByLine("D:\\CourseSystemDB\\Course\\c0783.txt");
//		System.out.println("readFileByLine"+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readStuCourse("121250323");
//		System.out.println("readStuCourse"+ testv);
//		System.out.println("---------------");
//		testv = FileHelper.readStuScore("121250323");
//		System.out.println("readStuScore"+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readStuTA("121250333");
//		System.out.println("readStuTA"+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readTeaCourse("12453");
//		System.out.println("readTeaCourse"+testv);
//		System.out.println("---------------");
//		testv = FileHelper.readAllStudent();
//		System.out.println("readAllStudent"+testv);		
//		testv = FileHelper.readAllTeacherName();
//		System.out.println("readAllTeacherName"+testv);
//		testv = FileHelper.readAllStudentName();
//		System.out.println("readAllStudentName"+testv);
//		testv = FileHelper.readAllCourseName();
//		System.out.println("readAllCourseName"+testv);
//		testv = FileHelper.readAllCourseTime();
//		System.out.println("readAllCourseTime"+testv);
//		Vector<String> v = new Vector<String>();
//		v.addElement("c0783");
		
//		testv = FileHelper.readTeaCourseName(v);
//		System.out.println("readteaCourseName"+testv);
//		testv = FileHelper.readTeaCoursePlace(v);
//		System.out.println("readteaCoursePlace"+testv);
//		testv = FileHelper.readTeaCourseTime(v);
//		System.out.println("readteaCourseTime"+testv);
		
		
//		testv = FileHelper.readStuCourseName(v);
//		System.out.println("readStuCourseName"+testv);
		
		Vector<String> a = FileHelper.readAllCoursePro();
		System.out.println(a);
	}

}
