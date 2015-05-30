package theBigHomework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class Student {
	private String studentNo;
	private String studentKey;
	private String studentName = "AAA";
	public Student(String studentNo,String studentKey) throws IOException
	{
		this.studentNo = studentNo;
		this.studentKey = studentKey;
		setStudentName();
		System.out.println("Student created,studentNo   "+this.studentNo+"  key  "+this.studentKey);
	}
	private void setStudentName() throws IOException
	{
		String line;
		Vector<String> v = new Vector<String>();
		
		File file=new File("D:\\CourseSystemDB\\Student\\Student"+this.studentNo+".txt");
		if(!file.exists())
		{
			System.out.println("File"+file.getName()+" not found!");
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			file.createNewFile();
			writer.write(this.studentNo+"  AAA  Key  ");
			writer.close();
		}else{
			//read file
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			
			while ((line = reader.readLine()) != null) 
			{   
				
				String[] temp = line.split("  ");
				for(int i = 0;i<temp.length;i++){
					v.addElement(temp[i]);
				}
			}
			v.trimToSize();
			
			read.close();
			this.studentName = v.get(1);
		}
			
	}
	public String getKey()
	{
		return this.studentKey;
	}
	public synchronized boolean changeStudentKey(String newKey) throws IOException
	{
		
		String line;
		Vector<String> v = new Vector<String>();
		//save change
		File file=new File("D:\\CourseSystemDB\\Student\\Student"+this.studentNo+".txt");
		
		if(!file.exists())
		{
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			file.createNewFile();
			writer.write(this.studentNo+"  "+this.studentName+"  "+newKey+"  ");
			writer.close();
		}
			//read file
		FileInputStream fis = new FileInputStream(file);		
		InputStreamReader read = new InputStreamReader (fis); 
		BufferedReader reader=new BufferedReader(read);
		
		while ((line = reader.readLine()) != null) 
		{   
			v.addElement(line);
		}
		v.trimToSize();
		
		read.close();
		
		
			//change
		String[] firstLine = v.get(0).split("  ");
		firstLine[2] = newKey;
		String newfirstLine = "";
		for(int i = 0;i<firstLine.length;i++){
			newfirstLine += firstLine[i] + "  ";
		}
		v.set(0, newfirstLine);
		
		this.studentKey = newKey;
			//write back
		FileOutputStream fos = new FileOutputStream(file);		
		OutputStreamWriter write = new OutputStreamWriter (fos); 
		BufferedWriter writer=new BufferedWriter(write);
		for(int i = 0;i<v.size();i++)
		{
			writer.write(v.get(i)+"\r\n");
		}
		
		writer.close();
		write.close();
		fos.close();
		
		System.out.println("Key successfully changed");
		return true;
	}

	public void showMycourse() throws IOException{
		String line;
		String courseList = "";
		Vector<String> v = new Vector<String>();
		
		File file=new File("D:\\CourseSystemDB\\Student\\Student"+this.studentNo+".txt");
		if(!file.exists())
		{
			System.out.println("File"+file.getName()+" not found!");
		}
			//read file
		FileInputStream fis = new FileInputStream(file);		
		InputStreamReader read = new InputStreamReader (fis); 
		BufferedReader reader=new BufferedReader(read);
		line = reader.readLine();
		while ((line = reader.readLine()) != null) 
		{   
			String[] temp = line.split("  ");
			v.addElement(temp[0]);			
		}
		v.trimToSize();
		read.close();
		for(int i =0;i<v.size();i++){
			courseList+=v.get(i);
			courseList+="\r\n";
		}
		System.out.println(courseList);
	}

	public synchronized boolean selectCourse(String courseNo) throws IOException{
		File f = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		File file=new File("D:\\CourseSystemDB\\Student\\Student"+this.studentNo+".txt");
		if(f.exists())
		{//select course			
			if(!file.exists())
			{
				System.out.println("File"+file.getName()+" not found!");
				FileOutputStream fos = new FileOutputStream(file);		
				OutputStreamWriter write = new OutputStreamWriter (fos); 
				BufferedWriter writer=new BufferedWriter(write);
				file.createNewFile();
				writer.write(this.studentNo+"  "+this.studentName+"  "+this.studentKey+"  ");
				writer.close();
			}
			String line;
			Vector<String> v = new Vector<String>();
			Vector<String> v2 = new Vector<String>();
			Vector<String> courseLi = new Vector<String>();
			Vector<String> studentLi = new Vector<String>();
			//read student profile
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);		
			while ((line = reader.readLine()) != null) {			
				v.addElement(line);			
			}
			v.trimToSize();		
			read.close();
			for(int i = 1;i<v.size();i++){
				String[] temp = v.get(i).split("  ");
				courseLi.addElement(temp[0]);
			}
			//read course profile
			FileInputStream fis2 = new FileInputStream(f);		
			InputStreamReader read2 = new InputStreamReader (fis2); 
			BufferedReader reader2=new BufferedReader(read2);		
			while ((line = reader2.readLine()) != null) {			
				v2.addElement(line);			
			}
			v2.trimToSize();		
			read2.close();
			for(int i = 4;i<v2.size();i++){
				String[] temp = v2.get(i).split("  ");
				studentLi.addElement(temp[0]);
			}
			
			if(courseLi.contains(courseNo)){
				System.out.println("course already selected");
				return false;
			}else{
				//change student profile			
				v.addElement(courseNo+"  "+"NA"+"  ");			
					//write back
				FileOutputStream fos = new FileOutputStream(file);		
				OutputStreamWriter write = new OutputStreamWriter (fos); 
				BufferedWriter writer=new BufferedWriter(write);
				for(int i = 0;i<v.size();i++)
				{
					writer.write(v.get(i)+"  "+"\r\n");
				}			
				writer.close();
				write.close();
				fos.close();
					//change course profile
				v2.addElement(this.studentNo+"  NA");
					//write back
				FileOutputStream fos2 = new FileOutputStream(f);		
				OutputStreamWriter write2 = new OutputStreamWriter (fos2); 
				BufferedWriter writer2=new BufferedWriter(write2);
				for(int i = 0;i<v2.size();i++)
				{
					writer2.write(v2.get(i)+"  "+"\r\n");
				}			
				writer2.close();
				write2.close();
				fos2.close();
				
				System.out.println("course successfully selected");
				return true;
			}			
		}else{
			System.out.println("course not exist!");
			return false;
		}
	}
	public synchronized String quitCourse(String courseNo) throws IOException{
		File f = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		File file=new File("D:\\CourseSystemDB\\Student\\Student"+this.studentNo+".txt");
		if(f.exists())
		{//select course			
			if(!file.exists())
			{
				System.out.println("File"+file.getName()+" not found!");
				FileOutputStream fos = new FileOutputStream(file);		
				OutputStreamWriter write = new OutputStreamWriter (fos); 
				BufferedWriter writer=new BufferedWriter(write);
				file.createNewFile();
				writer.write(this.studentNo+"  "+this.studentName+"  "+this.studentKey+"  ");
				writer.close();
			}
			String line;
			Vector<String> v = new Vector<String>();
			Vector<String> v2 = new Vector<String>();
			Vector<String> courseLi = new Vector<String>();
			Vector<String> studentLi = new Vector<String>();
			//read student profile
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);		
			while ((line = reader.readLine()) != null) {			
				v.addElement(line);			
			}
			v.trimToSize();		
			read.close();
			for(int i = 1;i<v.size();i++){
				String[] temp = v.get(i).split("  ");
				courseLi.addElement(temp[0]);
			}
			//read course profile
			FileInputStream fis2 = new FileInputStream(f);		
			InputStreamReader read2 = new InputStreamReader (fis2); 
			BufferedReader reader2=new BufferedReader(read2);		
			while ((line = reader2.readLine()) != null) {			
				v2.addElement(line);			
			}
			v2.trimToSize();		
			read2.close();
			for(int i = 4;i<v2.size();i++){
				String[] temp = v2.get(i).split("  ");
				studentLi.addElement(temp[0]);
			}
			
			if(!courseLi.contains(courseNo)){
				System.out.println("course never selected!");
				return "False";
			}else{
				//change student profile			
				v.removeElementAt(courseLi.indexOf(courseNo)+1);
				v.trimToSize();
					//write back
				FileOutputStream fos = new FileOutputStream(file);		
				OutputStreamWriter write = new OutputStreamWriter (fos); 
				BufferedWriter writer=new BufferedWriter(write);
				for(int i = 0;i<v.size();i++)
				{
					writer.write(v.get(i)+"  \r\n");
				}			
				writer.close();
				write.close();
				fos.close();
					//change course profile
				v2.removeElementAt(studentLi.indexOf(this.studentNo)+4);
				v2.trimToSize();
					//write back
				FileOutputStream fos2 = new FileOutputStream(f);		
				OutputStreamWriter write2 = new OutputStreamWriter (fos2); 
				BufferedWriter writer2=new BufferedWriter(write2);
				for(int i = 0;i<v2.size();i++)
				{
					writer2.write(v2.get(i)+"  "+"\r\n");
				}			
				writer2.close();
				write2.close();
				fos2.close();			
				System.out.println("course successfully quited");
				return "True";
			}			
		}else{
			System.out.println("course not exist!");
			return "null";
		}
	}
	public void showScore(String courseNo) throws IOException{
		File file=new File("D:\\CourseSystemDB\\Student\\Student"+this.studentNo+".txt");
		if(!file.exists())
		{
			System.out.println("File"+file.getName()+" not found!");
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			file.createNewFile();
			writer.write(this.studentNo+"  "+this.studentName+"  "+this.studentKey+"  ");
			writer.close();
		}
		String line;
		Vector<String> v = new Vector<String>();
		Vector<String> courseLi = new Vector<String>();
		Vector<String> scoreLi = new Vector<String>();
		FileInputStream fis = new FileInputStream(file);		
		InputStreamReader read = new InputStreamReader (fis); 
		BufferedReader reader=new BufferedReader(read);		
		while ((line = reader.readLine()) != null) {			
			v.addElement(line);			
		}
		v.trimToSize();		
		read.close();
		for(int i = 1;i<v.size();i++){
			String[] temp = v.get(i).split("  ");
			courseLi.addElement(temp[0]);
			scoreLi.addElement(temp[1]);
		}
		System.out.println(scoreLi.get(courseLi.indexOf(courseNo)));
	}
	public Vector<String> getMyProfile() throws IOException{
		return FileHelper.readFileByLine("D:\\CourseSystemDB\\Student\\Student"+this.studentNo+".txt");
	}
	public Vector<String> getMyCourseNo() throws IOException{
		return FileHelper.readStuCourse(this.studentNo);
	}
	public Vector<String> getMyCourseName() throws IOException{
		return FileHelper.readStuCourseName( FileHelper.readStuCourse(this.studentNo) );
	}
	
	public Vector<String> getMyScore() throws IOException{
		return FileHelper.readStuScore(this.studentNo);
	}
	public Vector<String> getAllCourse() throws IOException{
		return FileHelper.readAllCourseNum();
	}
	public Vector<String> getAllCourseName() throws IOException{
		return FileHelper.readAllCourseName();
	}
	public Vector<String> getAllCourseTime()throws IOException{
		return FileHelper.readAllCourseTime();
	}
	public Vector<String> getAllCoursePor()throws IOException{
		return FileHelper.readAllCoursePro();
	}
	public static void main(String[] args) throws Throwable {
		Student s = new Student("121250111","CASS");
//		System.out.println(s.selectCourse("c0783"));
//		s.showScore("c0783");
//		s.showMycourse();
//		System.out.println(s.quitCourse("c0783"));
//		s.showMycourse();
		
		s.changeStudentKey("!!!!!");
	}

}
