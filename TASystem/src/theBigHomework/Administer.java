package theBigHomework;

import java.io.*;
import java.util.Vector;

public class Administer {

	/**
	 * @param args
	 */
	private String accountName="Admin";
	private String key="";
//	public static final int map_key = 10 ;
	public Administer(String adminKey) throws IOException
	{
		
		this.key = adminKey;
		System.out.println("Admin created,name   "+this.accountName+"  key  "+this.key);
	}
	public String getKey()
	{
		return this.key;
	}
	
	public synchronized boolean changeAdminKey(String newKey) throws IOException
	{
		
		String line;
		Vector<String> v = new Vector<String>();
		//save change
		File file=new File("D:\\CourseSystemDB\\Admin.txt");
		if(!file.exists())
		{
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			file.createNewFile();
			writer.write("Admin  "+newKey);
			writer.close();
		}
			//read file
		FileInputStream fis = new FileInputStream(file);		
		InputStreamReader read = new InputStreamReader (fis); 
		BufferedReader reader=new BufferedReader(read);
		
		while ((line = reader.readLine()) != null) 
		{   
			
			String[] temp = line.split("  ");
			
			v.addElement(temp[0]);
			v.addElement(temp[1]);
		}
		v.trimToSize();
		
		read.close();
		
		
			//change
		
		v.set(v.indexOf(this.accountName)+1, newKey);
		
		this.key = newKey;
			//write back
		FileOutputStream fos = new FileOutputStream(file);		
		OutputStreamWriter write = new OutputStreamWriter (fos); 
		BufferedWriter writer=new BufferedWriter(write);
		for(int i = 0;i<v.size();i=i+2)
		{
			writer.write(v.get(i)+"  "+v.get(i+1)+"\r\n");
		}
		
		writer.close();
		write.close();
		fos.close();
		
		System.out.println("Key successfully changed");
		return true;
	}
	public void lookList(String listName) throws Throwable 
	{
		//disputed
		// TODO Auto-generated method stub
		File file = new File("D:\\CourseSystemDB\\"+listName+".txt");
		if(!file.exists())
		{
			System.out.println("File lost!");
		}
		else
		{
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			String line;
			while ((line = reader.readLine()) != null) 
			{   
				
				System.out.println(line);
			}
			reader.close();
		}
		
	}
	public synchronized void deleteItem(String listName, int itemIndex) throws Throwable {
		//disputed
		// TODO Auto-generated method stub
		File file = new File(listName+".txt");
		if(!file.exists())
		{
			System.out.println("File lost!");
		}
		else
		{
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			String line;
			Vector<String> v = new Vector<String>();
			
			//read file
			while ((line = reader.readLine()) != null) 
			{			
				v.addElement(line);
			}
			reader.close();
			
			//remove
			v.remove(itemIndex);
			v.trimToSize();
			System.gc();
			//write back
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			for(int i = 0;i<v.size();i++)
			{
				writer.write(v.get(i));
				writer.write("\r\n");
				
			}
			
			writer.close();
			write.close();
			fos.close();
			}
		}
		
	public synchronized void deleteItem(String listName, int itemIndex, int itemNum) throws Throwable {
		//disputed
		// TODO Auto-generated method stub
		File file = new File(listName+".txt");
		if(!file.exists())
		{
			System.out.println("File lost!");
		}
		else
		{
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			String line;
			Vector<String> v = new Vector<String>();
			
			//read file
			while ((line = reader.readLine()) != null) 
			{			
				v.addElement(line);
			}
			reader.close();
			
			//remove
			String targetLine = v.get(itemIndex);
			String[] temp = targetLine.split("  ");
			temp[itemNum] = "";
			targetLine = "";
			for(int j=0;j<temp.length;j++)
			{
				targetLine = targetLine + temp[j] + "  ";
			}
			v.setElementAt(targetLine, itemIndex);
			v.trimToSize();
			System.gc();
			//write back
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			for(int i = 0;i<v.size();i++)
			{
				writer.write(v.get(i));
				
				writer.write("\r\n");
			}
			
			writer.close();
			write.close();
			fos.close();
		}
	}
	public synchronized Vector<String> deleteTea(String teaNo) throws IOException{
		Vector<String> couBeDele = new Vector<String>();
		File teaProfile = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+teaNo+".txt");
		
		BufferedReader treader = new BufferedReader(new InputStreamReader(new FileInputStream(teaProfile)));
		Vector<String> course = new Vector<String>();
		String tline;
		treader.readLine();
		while((tline = treader.readLine())!=null){
			String[] ttemp = tline.split("  ");
			course.addElement(ttemp[0]);
			System.gc();
		}
		course.trimToSize();
		treader.close();
		//process course
		for(int i =0;i<course.size();i++){
			//for an course:  No. course.get(i)
			File cou = new File("D:\\CourseSystemDB\\Course\\"+course.get(i)+".txt");
			//course profile
			Vector<String> couP = new Vector<String>();
			//student list
			Vector<String> relateStu = new Vector<String>();			
			BufferedReader creader = new BufferedReader(new InputStreamReader(new FileInputStream(cou)));
			String cline;
			couP.addElement(creader.readLine());
			cline = creader.readLine();
			couP.addElement(cline);
			//Teacher list
			String[] relateTea = cline.split("  ");
			couP.addElement(creader.readLine());
			cline = creader.readLine();
			couP.addElement(cline);
			//TA list
			String[] relateTA = cline.split("  ");
			while((cline = creader.readLine())!=null){
				couP.addElement(cline);
				String[] ctemp = cline.split("  ");
				relateStu.addElement(ctemp[0]);
			}
			creader.close();
			
			if(relateTea.length<=2){
				//delete course
				//delete related TAs
				for(int j = 1;j<relateTA.length;j++){
					//for an TA No. relateTA[j]
					File ta = new File("D:\\CourseSystemDB\\Student\\Student"+relateTA[j]+".txt");
					BufferedReader taReader = new BufferedReader(new InputStreamReader(new FileInputStream(ta)));
					Vector<String> taP = new Vector<String>();
					String taLine;
					while((taLine=taReader.readLine())!=null){
						taP.addElement(taLine);
					}
					taReader.close();
					String[] firstLine = taP.get(0).split("  ");
					String firstLine2 = "";
					for(int k = 0;k<3;k++){
						firstLine2+=firstLine[k]+"  ";
					}
					for(int k = 3;k<firstLine.length;k++){
						if(!firstLine[k].equals(course.get(i))){
							firstLine2+=firstLine[k]+"  ";
						}
					}
					taP.set(0, firstLine2);
					BufferedWriter taWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ta)));
					for(int k = 0;k<taP.size();k++){
						taWriter.write(taP.get(k)+"  \r\n");
					}
					taWriter.close();
					System.gc();
				}
				//delete related-teacher course
				
				for(int j = 1;j<relateTea.length;j++){
					File tea = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+relateTea[j]+".txt");
					BufferedReader teaReader = new BufferedReader(new InputStreamReader(new FileInputStream(tea)));
					Vector<String> teaP = new Vector<String>();
					String teaLine;
					while((teaLine=teaReader.readLine())!=null){
						teaP.addElement(teaLine);
					}
					teaReader.close();
					for(int k = 0;k<teaP.size();k++){
						if(teaP.get(k).startsWith(course.get(i))){
							teaP.remove(k);
						}
					}
					teaP.trimToSize();
					BufferedWriter teaWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tea)));
					for(int k = 0;k<teaP.size();k++){
						teaWriter.write(teaP.get(k)+"  \r\n");
					}
					teaWriter.close();
					System.gc();
				}
				//delete course-related student
				for(int j=0;j<relateStu.size();j++){
					File stu = new File("D:\\CourseSystemDB\\Student\\Student"+relateStu.get(j)+".txt");
					BufferedReader stuReader = new BufferedReader(new InputStreamReader(new FileInputStream(stu)));
					Vector<String> stuP = new Vector<String>();
					String stuLine;
					while((stuLine = stuReader.readLine())!=null){
						stuP.addElement(stuLine);
					}
					stuReader.close();
					///////////
					System.out.println(stuP);
					/////////////
					for(int k = 0;k<stuP.size();k++){
						if(stuP.get(k).startsWith(course.get(i))){
							stuP.removeElementAt(k);
							
						}
					}
					//////////////
					System.out.println(stuP);
					//////////////
					
					stuP.trimToSize();
					BufferedWriter stuWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(stu)));
					for(int k = 0;k<stuP.size();k++){
						stuWriter.write(stuP.get(k)+"  \r\n");
					}
					stuWriter.close();
					System.gc();
				}
				couBeDele.addElement(course.get(i));
				cou.delete();
				//delete from courseList.txt
				File courseListFile = new File("D:\\CourseSystemDB\\courseList.txt");
				BufferedReader courseListReader = new BufferedReader(new InputStreamReader(new FileInputStream(courseListFile)));
				Vector<String> courseList = new Vector<String>();
				String courseListLine;
				while((courseListLine = courseListReader.readLine())!=null){
					courseList.addElement(courseListLine);
				}
				courseListReader.close();
				for(int j = 0;j<courseList.size();j++){
					if(courseList.get(j).startsWith(course.get(i))){
						courseList.removeElementAt(j);
					}
				}
				BufferedWriter courseListWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(courseListFile)));
				for(int j = 0;j<courseList.size();j++){
					courseListWriter.write(courseList.get(j)+"  \r\n");
				}
				courseListWriter.close();
				System.gc();
			}else{
				//delete teacher from course
				String courseT = "T  ";
				for(int k=1;k<relateTea.length;k++){
					if(!relateTea[k].equals(teaNo)){
						courseT+=relateTea[k];
					}
				}
				couP.set(1, courseT);
				BufferedWriter cWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cou)));
				for(int k =0;k<couP.size();k++){
					cWriter.write(couP.get(k)+"  \r\n");
				}
				cWriter.close();
			}
			System.gc();
		}
		//delete teacher profile
		teaProfile.delete();
		System.out.println("Teacher "+teaNo+" is deleted!");
		return couBeDele;
	}
	public synchronized boolean deleteCourse(String courseNo) throws IOException{
		File cou = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		//student list
		Vector<String> relateStu = new Vector<String>();			
		BufferedReader creader = new BufferedReader(new InputStreamReader(new FileInputStream(cou)));
		String cline;
		creader.readLine();//first Line
		//Teacher list
		String[] relateTea = creader.readLine().split("  ");//second Line
		creader.readLine();//third line
		
		//TA list
		String[] relateTA = creader.readLine().split("  ");//forth line
		while((cline = creader.readLine())!=null){
			String[] ctemp = cline.split("  ");
			relateStu.addElement(ctemp[0]);
		}
		creader.close();
		//delete related TAs
		for(int j = 1;j<relateTA.length;j++){
			//for an TA No. relateTA[j]
			File ta = new File("D:\\CourseSystemDB\\Student\\Student"+relateTA[j]+".txt");
			BufferedReader taReader = new BufferedReader(new InputStreamReader(new FileInputStream(ta)));
			Vector<String> taP = new Vector<String>();
			String taLine;
			while((taLine=taReader.readLine())!=null){
				taP.addElement(taLine);
			}
			taReader.close();
			String[] firstLine = taP.get(0).split("  ");
			String firstLine2 = "";
			for(int k = 0;k<3;k++){
				firstLine2+=firstLine[k]+"  ";
			}
			for(int k = 3;k<firstLine.length;k++){
				if(!firstLine[k].equals(courseNo)){
					firstLine2+=firstLine[k]+"  ";
				}
			}
			taP.set(0, firstLine2);
			BufferedWriter taWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ta)));
			for(int k = 0;k<taP.size();k++){
				taWriter.write(taP.get(k)+"  \r\n");
			}
			taWriter.close();
			System.gc();
		}
		//delete teacher course
		for(int j = 1;j<relateTea.length;j++){
			File tea = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+relateTea[j]+".txt");
			BufferedReader teaReader = new BufferedReader(new InputStreamReader(new FileInputStream(tea)));
			Vector<String> teaP = new Vector<String>();
			String teaLine;
			while((teaLine=teaReader.readLine())!=null){
				teaP.addElement(teaLine);
			}
			teaReader.close();
			for(int k = 0;k<teaP.size();k++){
				if(teaP.get(k).startsWith(courseNo)){
					teaP.removeElementAt(k);
				}
			}
			teaP.trimToSize();
			BufferedWriter teaWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tea)));
			for(int k = 0;k<teaP.size();k++){
				teaWriter.write(teaP.get(k)+"  \r\n");
			}
			teaWriter.close();
			System.gc();
		}
		//delete course-related student
		for(int j=0;j<relateStu.size();j++){
			File stu = new File("D:\\CourseSystemDB\\Student\\Student"+relateStu.get(j)+".txt");
			BufferedReader stuReader = new BufferedReader(new InputStreamReader(new FileInputStream(stu)));
			Vector<String> stuP = new Vector<String>();
			String stuLine;
			while((stuLine = stuReader.readLine())!=null){
				stuP.addElement(stuLine);
			}
			stuReader.close();
			for(int k = 0;k<stuP.size();k++){
				if(stuP.get(k).startsWith(courseNo)){
					stuP.removeElementAt(k);
				}
			}
			stuP.trimToSize();
			BufferedWriter stuWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(stu)));
			for(int k = 0;k<stuP.size();k++){
				stuWriter.write(stuP.get(k)+"  \r\n");
			}
			stuWriter.close();
			System.gc();
		}
		cou.delete();
		//delete from courseList.txt
		File courseListFile = new File("D:\\CourseSystemDB\\courseList.txt");
		BufferedReader courseListReader = new BufferedReader(new InputStreamReader(new FileInputStream(courseListFile)));
		Vector<String> courseList = new Vector<String>();
		String courseListLine;
		while((courseListLine = courseListReader.readLine())!=null){
			courseList.addElement(courseListLine);
		}
		courseListReader.close();
		for(int j = 0;j<courseList.size();j++){
			if(courseList.get(j).startsWith(courseNo)){
				courseList.removeElementAt(j);
			}
		}
		BufferedWriter courseListWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(courseListFile)));
		for(int j = 0;j<courseList.size();j++){
			courseListWriter.write(courseList.get(j)+"  \r\n");
		}
		courseListWriter.close();
		System.gc();
		System.out.println("course "+courseNo+" is deleted!");
		return true;
		
	}
	public synchronized boolean deleteStu(String stuNo)throws IOException{
		File stuProfile = new File("D:\\CourseSystemDB\\Student\\Student"+stuNo+".txt");
		BufferedReader stuReader = new BufferedReader(new InputStreamReader(new FileInputStream(stuProfile)));
		Vector<String> stuP = new Vector<String>();
		String[] stuTA;//the line include TA info
		String sLine;
		while((sLine = stuReader.readLine())!=null){
			stuP.addElement(sLine);
		}
		stuReader.close();
		stuTA = stuP.get(0).split("  ");//from 3
		//delete TA in course file
		for(int i = 3;i<stuTA.length;i++){
			File cou = new File("D:\\CourseSystemDB\\Course\\"+stuTA[i]+".txt");
			BufferedReader creader = new BufferedReader(new InputStreamReader(new FileInputStream(cou)));
			Vector<String> courseP = new Vector<String>();
			String cLine;
			while((cLine=creader.readLine())!=null){
				courseP.addElement(cLine);
			}
			creader.close();
			String[] changeTAtemp = courseP.get(3).split("  ");
			String changeTA = "TA  ";
			for(int j=1;j<changeTAtemp.length;j++){
				if(!changeTAtemp[j].equals(stuNo)){
					changeTA+=changeTAtemp[j];
				}
			}
			courseP.set(3, changeTA);
			courseP.trimToSize();
			BufferedWriter cWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cou)));
			for(int j=0;j<courseP.size();j++){
				cWriter.write(courseP.get(j)+"  \r\n");
			}
			cWriter.close();
			System.gc();
		}
		//delete student in course file
		for(int i = 1;i<stuP.size();i++){
			String[] course = stuP.get(i).split("  ");
			File cou = new File("D:\\CourseSystemDB\\Course\\"+course[0]+".txt");
			BufferedReader creader = new BufferedReader(new InputStreamReader(new FileInputStream(cou)));
			Vector<String> courseP = new Vector<String>();
			String cLine;
			while((cLine=creader.readLine())!=null){
				courseP.addElement(cLine);
			}
			creader.close();
			for(int j = 4;j<courseP.size();j++){
				if(courseP.get(j).startsWith(stuNo)){
					courseP.remove(j);
				}
			}
			courseP.trimToSize();
			BufferedWriter cWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cou)));
			for(int j=0;j<courseP.size();j++){
				cWriter.write(courseP.get(j)+"  \r\n");
			}
			cWriter.close();
			System.gc();
		}
		stuProfile.delete();
		System.out.println("Student "+stuNo+" is deleted!");
		return true;
		
	}
	
	public Vector<String> guiShowTeacher()	{
		return theBigHomework.FileHelper.readAllTeacher();
	}
	public Vector<String> guiShowStudent()	{
		return theBigHomework.FileHelper.readAllStudent();
	}
	public Vector<String> guiShowCourse()	{
		return theBigHomework.FileHelper.readAllCourse();
	}
	public Vector<String> guiShowCourseName() throws IOException	{
		return theBigHomework.FileHelper.readAllCourseName();
	}
	public Vector<String> guiShowTeacherName() throws IOException	{
		return theBigHomework.FileHelper.readAllTeacherName();
	}
	public Vector<String> guiShowStudentName() throws IOException	{
		return theBigHomework.FileHelper.readAllStudentName();
	}
	public Vector<String> guiShowCourseTime() throws IOException	{
		return theBigHomework.FileHelper.readAllCourseTime();
	}
	public Vector<String> guiShowCoursePlace() throws IOException	{
		return theBigHomework.FileHelper.readAllCoursePlace();
	}
	public static void main(String[] args) throws IOException{
//		new Administer("Admin").deleteCourse("c0783");
//		Vector<String> v = Administer.testShowCoursePlace();
//		System.out.println(v);
	}
}
