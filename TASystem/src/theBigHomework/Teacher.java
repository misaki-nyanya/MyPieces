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

public class Teacher {
	private String teacherNo;
	private String teacherKey;
	private String teacherName;
	private Vector<String> myCourse;
	public Teacher(String teacherNo,String teacherKey) throws IOException
	{
		this.teacherNo = teacherNo;
		this.teacherKey = teacherKey;
		setTeacherName();
		System.out.println("teacher created,teacherNo   "+this.teacherNo+"  key  "+this.teacherKey);
	}
	private void setTeacherName() throws IOException
	{
		String line;
		Vector<String> v = new Vector<String>();		
		File file=new File("D:\\CourseSystemDB\\Teacher\\Teacher"+this.teacherNo+".txt");
		if(!file.exists())
		{
			System.out.println("File"+file.getName()+" not found!");
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			file.createNewFile();
			writer.write(this.teacherNo+"  AAA  Key  ");
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
			this.teacherName = v.get(1);
		}			
	}
	public String getTeacherName()
	{
		return this.teacherName;
	}
	public synchronized boolean changeteacherKey(String newKey) throws IOException
	{		
		String line;
		Vector<String> v = new Vector<String>();
		//save change
		File file=new File("D:\\CourseSystemDB\\Teacher\\teacher"+this.teacherNo+".txt");
		if(!file.exists())
		{
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			file.createNewFile();
			writer.write(this.teacherNo+"  "+this.teacherName+"  "+newKey+"  ");
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
			newfirstLine += firstLine[i]+"  ";
		}
		v.set(0, newfirstLine);		
		this.teacherKey = newKey;
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
	public synchronized boolean publish() throws IOException{
		File courseLiFile = new File("D:\\CourseSystemDB\\courseList.txt");
		Vector<String> item = new Vector<String>();
		Vector<String> courseNoLi = new Vector<String>();
		Vector<String> courseNameLi = new Vector<String>();
		//read overall course data base
		if(!courseLiFile.exists()){
			courseLiFile.createNewFile();
		}
		FileInputStream fis = new FileInputStream(courseLiFile);		
		InputStreamReader read = new InputStreamReader (fis); 
		BufferedReader reader=new BufferedReader(read);
		String line;
		while((line=reader.readLine())!=null){
			item.addElement(line);
			String[] temp = line.split("  ");
			courseNoLi.addElement(temp[0]);
			courseNameLi.addElement(temp[1]);
		}
		reader.close();
		//get course profile
		System.out.println("Please input course number...");
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		String courseNo = buff.readLine();		
		File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		while(file.exists()){
			System.out.println("course number already exist!");
			courseNo = buff.readLine();
			file = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		}
		
		System.out.println("Please input course name...");
		String courseName = buff.readLine();
		while(courseNameLi.contains(courseName)){
			System.out.println("course name already exist!");
			courseName = buff.readLine();
		}
		
		System.out.println("Please input how many weeks the course last&what time it is...[length/time]");
		
		String courseLength = buff.readLine();
		
		
		
		System.out.println("Please input how many points the course values...[number only]");
		String coursePoint ;
		Integer temp ;
		while(true){
			try{
				temp = Integer.parseInt(buff.readLine());
				coursePoint = temp.toString();
				break;
			}catch(NumberFormatException e){
				System.out.println("numbers only!");
			}
			
		}
		System.out.println("Please input where the course is going to be taught...");
		String coursePlace = buff.readLine();
		
		System.out.println("Please input coTeacher's teacher number...[splited with English';']");
		String tempcoTea = buff.readLine();
		String[] coTeacher = tempcoTea.split(";");
		if(!tempcoTea.equals("")){
			//judge if the teacher exists!
			boolean judget = true;
			while(true){
				judget = true;
				for(int i =0;i<coTeacher.length;i++){
					File coTea = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+coTeacher[i]+".txt");
					if(!coTea.exists()){
						judget = false;
						break;
					}
				}
				if(judget){
					break;
				}else if((coTeacher.length == 1)&&coTeacher[0].equals("")){
					break;
				}else{
					System.out.println("some teachers not exist!");
					tempcoTea = buff.readLine();
					coTeacher = tempcoTea.split(";");
				}				
			}
		}		
		System.out.println("Please input teacher assistant's student number...[splited with English';']");
		String tempTA = buff.readLine();
		String[] ta = tempTA.split(";");
		if(!tempTA.equals("")){
			//judge if the student exists!
			boolean judgeta = true;
			while(true){
				judgeta = true;
				for(int i =0;i<ta.length;i++){
					File coTA = new File("D:\\CourseSystemDB\\Student\\Student"+ta[i]+".txt");
					if(!coTA.exists()){
						judgeta = false;
						break;
					}
				}
				if(judgeta){
					break;
				}else if((ta.length == 1)&&ta[0].equals("")){
					break;
				}else{
					System.out.println("some students not exist!");
					tempTA = buff.readLine();
					ta = tempTA.split(";");
				}				
			}
		}	
		System.out.println("Please input course property...[1 for compulsory/0 for elective]");
		String courseProperty = buff.readLine();		
		while(!(courseProperty.equals("1")||courseProperty.equals("0"))){
			System.out.println("Please in put 0 or 1!");
			courseProperty = buff.readLine();
		}
		//confirm change
		String courseProfile = courseNo+"  "+courseName+"  courseLength:"+courseLength+" weeks  coursePoint:"+coursePoint+"  Place:"
				+coursePlace+"  courseProperty:"+courseProperty+"  \r\nTeacher:"+this.teacherNo+"  ";
		for(int i = 0;i<coTeacher.length;i++){
			courseProfile=courseProfile+coTeacher[i]+"  ";
		}
		courseProfile+="\r\nTA:  ";
		for(int i = 0;i<ta.length;i++){
			courseProfile=courseProfile+ta[i]+"  ";
		}
		System.out.println(courseProfile);
		System.out.println("confirm?[Y/N]");
		//create course
		String confirm = buff.readLine();
		if(confirm.equals("Y")){
			//create course profile			
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			writer.write(courseNo+"  "+courseName+"  "+courseLength+"  "+coursePoint+"  "+coursePlace+"  "+"  \r\n");
			writer.write("T  "+this.teacherNo+"  ");
			for(int i = 0;i<coTeacher.length;i++){
				writer.write(coTeacher[i]+"  ");
			}
			writer.write("\r\n");
			writer.write("Property  "+courseProperty+"  \r\n");	
			writer.write("TA  ");
			for(int i = 0;i<ta.length;i++){
				writer.write(ta[i]+"  ");
			}
			writer.write("\r\n");
			writer.close();
			// add to overall course list
			FileOutputStream fos2 = new FileOutputStream(courseLiFile);		
			OutputStreamWriter write2 = new OutputStreamWriter (fos2); 
			BufferedWriter writer2=new BufferedWriter(write2);
			item.addElement(courseNo+"  "+courseName+"  ");
			item.trimToSize();
			for(int i =0;i<item.size();i++){
				writer2.write(item.get(i)+"\r\n");
			}
			writer2.close();
			System.gc();
			//add to teacher profile
			File teacher = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+this.teacherNo+".txt");
			FileInputStream tfis = new FileInputStream(teacher);		
			InputStreamReader tread = new InputStreamReader (tfis); 
			BufferedReader treader=new BufferedReader(tread);
			String tline;
			Vector<String> t = new Vector<String>();
			while((tline=treader.readLine())!=null){
				t.addElement(tline);
			}
			t.addElement(courseNo+"  "+courseName+"  ");
			t.trimToSize();
			treader.close();
			
			FileOutputStream tfos = new FileOutputStream(teacher);		
			OutputStreamWriter twrite = new OutputStreamWriter (tfos); 
			BufferedWriter twriter=new BufferedWriter(twrite);
			for(int i =0;i<t.size();i++){
				twriter.write(t.get(i)+"  \r\n");
			}
			twriter.close();
			if(coTeacher.length>1){
				for(int i =0;i<coTeacher.length;i++){
					File coteacher = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+coTeacher[i]+".txt");
					FileInputStream cotfis = new FileInputStream(coteacher);		
					InputStreamReader cotread = new InputStreamReader (cotfis); 
					BufferedReader cotreader=new BufferedReader(cotread);
					String cotline;
					Vector<String> cot = new Vector<String>();
					while((cotline=cotreader.readLine())!=null){
						cot.addElement(cotline);
					}
					cot.addElement(courseNo+"  "+courseName+"  ");
					cot.trimToSize();
					cotreader.close();
					
					FileOutputStream cotfos = new FileOutputStream(coteacher);		
					OutputStreamWriter cotwrite = new OutputStreamWriter (cotfos); 
					BufferedWriter cotwriter=new BufferedWriter(cotwrite);
					for(int j =0;j<cot.size();j++){
						cotwriter.write(cot.get(j)+"  \r\n");
					}
					cotwriter.close();
				}
			}
			
			//add to TA profile
			if(!ta[0].equals("")){
				for(int i =0;i<ta.length;i++){
					File taf = new File("D:\\CourseSystemDB\\Student\\Student"+ta[i]+".txt");
					FileInputStream tafis = new FileInputStream(taf);		
					InputStreamReader taread = new InputStreamReader (tafis); 
					BufferedReader tareader=new BufferedReader(taread);
					String taline;
					Vector<String> tav = new Vector<String>();
					while((taline=tareader.readLine())!=null){
						tav.addElement(taline);
					}
					tareader.close();
					String[] tatemp = tav.get(0).split("  ");
					String tatemp2 = "";
					for(int k = 0;k<tatemp.length;k++){
						tatemp2+=tatemp[k]+"  ";
					}
					tatemp2+=courseNo+"  ";
					tav.set(0, tatemp2);
					
					FileOutputStream tafos = new FileOutputStream(taf);		
					OutputStreamWriter tawrite = new OutputStreamWriter (tafos); 
					BufferedWriter tawriter=new BufferedWriter(tawrite);
					for(int j =0;j<tav.size();j++){
						tawriter.write(tav.get(j)+"  \r\n");
					}
					tawriter.close();
				}
			}
			
			System.out.println("Publish succeeded!");
			return true;
		}else if(confirm.equals("N")){
			System.out.println("Publish concealed");
			return false;
		}else{
			return false;
		}
	
	}
	public boolean showCourse(String courseNo) throws IOException{
		File aimCourse = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		if((!courseNo.equals(""))&&(aimCourse.exists())){
			FileInputStream fis = new FileInputStream(aimCourse);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			String line;
			Vector<String> courseProfile = new Vector<String>();
			while((line=reader.readLine())!=null){
				courseProfile.addElement(line);
			}
			reader.close();
			for(int i =0;i<4;i++){
				System.out.println(courseProfile.get(i));
			}
			return true;
		}else if(courseNo.equals("*")){
			File courseList = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+this.teacherNo+".txt");
			FileInputStream fis = new FileInputStream(courseList);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			String line;
			Vector<String> courseNoLi = new Vector<String>();
			reader.readLine();
			while((line=reader.readLine())!=null){
				String[] temp = line.split("  "); 
				courseNoLi.addElement(temp[0]);
			}
			courseNoLi.trimToSize();
			reader.close();
			for(int i = 0;i< courseNoLi.size();i++){
				aimCourse = new File("D:\\CourseSystemDB\\Course\\"+courseNoLi.get(i)+".txt");
				FileInputStream fis2 = new FileInputStream(aimCourse);		
				InputStreamReader read2 = new InputStreamReader (fis2); 
				BufferedReader reader2=new BufferedReader(read2);
				for(int j = 0;j<4;j++){
					System.out.println(reader2.readLine());
				}
				reader2.close();
			}
			return true;
		}else{
			System.out.println("bad input!");
			return false;
		}		
	}
	public synchronized boolean updateCourse(String courseNo) throws IOException{
		File aimCourse = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		
		if((!aimCourse.exists())&&(!courseNo.equals(""))){
			System.out.println("Course not exist!");
			return false;
		}else if(aimCourse.exists()){
			//read overall course property
			File courseLi = new File("D:\\CourseSystemDB\\courseList.txt");
			Vector<String> courseNameLi = new Vector<String>();
			FileInputStream fisc = new FileInputStream(courseLi);		
			InputStreamReader readc = new InputStreamReader (fisc); 
			BufferedReader readerc=new BufferedReader(readc);
			String line;
			while((line=readerc.readLine())!=null){
				String[] temp = line.split("  ");
				courseNameLi.addElement(temp[1]);
			}
			readerc.close();
			//show now property
			FileInputStream fis = new FileInputStream(aimCourse);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			Vector<String> courseP = new Vector<String>();
			for(int i = 0;i<4;i++){
				courseP.addElement(reader.readLine());
			}
			reader.close();
			System.out.println("the course now is:");
			for(int i = 0;i<courseP.size();i++){
				System.out.println(courseP.get(i));
			}
			//get change
			System.out.println("Please input new course number...");
			BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
			String newCourseNo = buff.readLine();
			File testRepeat = new File("D:\\CourseSystemDB\\Course\\"+newCourseNo+".txt");
			while(testRepeat.exists()){
				System.out.println("course number exists!");
				newCourseNo = buff.readLine();
				testRepeat = new File("D:\\CourseSystemDB\\Course\\"+newCourseNo+".txt");
			}
			System.out.println("Please input new course name...");
			String newCourseName = buff.readLine();
			while(courseNameLi.contains(newCourseName)){
				System.out.println("course name already exist!");
				newCourseName = buff.readLine();
			}
			
			System.out.println("Please input how many weeks the course last...[number only]");
			
			String newCourseLength = buff.readLine();
			
			
			System.out.println("Please input how many points the course values...[number only]");
			String newCoursePoint ;
			Integer temp ;
			while(true){
				try{
					temp = Integer.parseInt(buff.readLine());
					newCoursePoint = temp.toString();
					break;
				}catch(NumberFormatException e){
					System.out.println("numbers only!");
				}
				
			}
			
			System.out.println("Please input where the course is going to be taught...");
			String newCoursePlace = buff.readLine();
			
			System.out.println("Please input coTeacher's teacher number...[splited with English';']");
			String tempcoTea = buff.readLine();
			String[] newCoTeacher = tempcoTea.split(";");
			if(!tempcoTea.equals("")){
				//judge if the teacher exists!
				boolean judget = true;
				while(true){
					judget = true;
					for(int i =0;i<newCoTeacher.length;i++){
						File coTea = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+newCoTeacher[i]+".txt");
						if(!coTea.exists()){
							judget = false;
							break;
						}
					}
					if(judget){
						break;
					}else if((newCoTeacher.length == 1)&&newCoTeacher[0].equals("")){
						break;
					}else{
						System.out.println("some teachers not exist!");
						tempcoTea = buff.readLine();
						newCoTeacher = tempcoTea.split(";");
					}				
				}
			}		
			System.out.println("Please input teacher assistant's student number...[splited with English';']");
			String tempTA = buff.readLine();
			String[] newTA = tempTA.split(";");
			if(!tempTA.equals("")){
				//judge if the student exists!
				boolean judgeta = true;
				while(true){
					judgeta = true;
					for(int i =0;i<newTA.length;i++){
						File coTA = new File("D:\\CourseSystemDB\\Student\\Student"+newTA[i]+".txt");
						if(!coTA.exists()){
							judgeta = false;
							break;
						}
					}
					if(judgeta){
						break;
					}else if((newTA.length == 1)&&newTA[0].equals("")){
						break;
					}else{
						System.out.println("some students not exist!");
						tempTA = buff.readLine();
						newTA = tempTA.split(";");
					}				
				}
			}	
			System.out.println("Please input course property...[1 for compulsory/0 for elective]");
			String newCourseProperty = buff.readLine();		
			while(!(newCourseProperty.equals("1")||newCourseProperty.equals("0"))){
				System.out.println("Please in put 0 or 1!");
				newCourseProperty = buff.readLine();
			}
			//confirm change?
			String courseProfile = newCourseNo+"  "+newCourseName+"  courseLength:"+newCourseLength+" weeks  coursePoint:"+newCoursePoint+"  Place:"
					+newCoursePlace+"  courseProperty:"+newCourseProperty+"  \r\nTeacher:"+this.teacherNo+"  ";
			for(int i = 0;i<newCoTeacher.length;i++){
				courseProfile=courseProfile+newCoTeacher[i]+"  ";
			}
			courseProfile+="\r\nTA:  ";
			for(int i = 0;i<newTA.length;i++){
				courseProfile=courseProfile+newTA[i]+"  ";
			}
			System.out.println(courseProfile);
			System.out.println("confirm?[Y/N]");
			System.gc();
			String confirm = buff.readLine();
			//create course
			if(confirm.equals("Y")){
				//create course profile	
				aimCourse.delete();
				aimCourse = new File("D:\\CourseSystemDB\\Course\\"+newCourseNo+".txt");
				FileOutputStream fos = new FileOutputStream(aimCourse);		
				OutputStreamWriter write = new OutputStreamWriter (fos); 
				BufferedWriter writer=new BufferedWriter(write);
				writer.write(newCourseNo+"  "+newCourseName+"  "+newCourseLength+"  "+newCoursePoint+"  "+newCoursePlace+"  "+"  \r\n");
				writer.write("Property  "+newCourseProperty+"  \r\n");
				writer.write("T  "+this.teacherNo+"  ");
				for(int i = 0;i<newCoTeacher.length;i++){
					writer.write(newCoTeacher[i]+"  ");
				}
				writer.write("\r\n");
				writer.write("TA  ");
				for(int i = 0;i<newTA.length;i++){
					writer.write(newTA[i]+"  ");
				}
				writer.write("\r\n");
				writer.close();
				//delete old teacher profile
				String[] oldTeacherList = courseP.get(2).split("  ");
				String[] oldTAList = courseP.get(3).split("  ");
				String[] oldCourseProperty = courseP.get(0).split("  ");
				String oldCourseNo = oldCourseProperty[0];
				for(int i=1;i<oldTeacherList.length;i++){
					File oldTeacher = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+oldTeacherList[i]+".txt");
					Vector<String> oldTeacherProperty = new Vector<String>();
					String oldtline;
					FileInputStream oldtfis = new FileInputStream(oldTeacher);		
					InputStreamReader oldtread = new InputStreamReader (oldtfis); 
					BufferedReader oldtreader=new BufferedReader(oldtread);
					while((oldtline=oldtreader.readLine())!=null){
						oldTeacherProperty.addElement(oldtline);
					}
					oldtreader.close();
					for(int j = 1;j<oldTeacherProperty.size();j++){
						String[] oldttemp = oldTeacherProperty.get(j).split("  ");
						if(oldttemp[0].equals(oldCourseNo)){
							oldTeacherProperty.remove(j);
							break;
						}
					}
					oldTeacherProperty.trimToSize();
					FileOutputStream oldtfos = new FileOutputStream(oldTeacher);		
					OutputStreamWriter oldtwrite = new OutputStreamWriter (oldtfos); 
					BufferedWriter oldtwriter=new BufferedWriter(oldtwrite);
					for(int k =0;k<oldTeacherProperty.size();k++){
						oldtwriter.write(oldTeacherProperty.get(k)+"  \r\n");
					}
					oldtwriter.close();
				}
				//delete old TA file
				for(int i=1;i<oldTAList.length;i++){
					File oldTA = new File("D:\\CourseSystemDB\\Student\\Student"+oldTAList[i]+".txt");
					Vector<String> oldTAProperty = new Vector<String>();
					String oldtline;
					FileInputStream oldtfis = new FileInputStream(oldTA);		
					InputStreamReader oldtread = new InputStreamReader (oldtfis); 
					BufferedReader oldtreader=new BufferedReader(oldtread);
					while((oldtline=oldtreader.readLine())!=null){
						oldTAProperty.addElement(oldtline);
					}
					oldtreader.close();
					oldTAProperty.trimToSize();
					String[] oldTAPtemp = oldTAProperty.get(0).split("  ");
					String deletedTAP = "";
					for(int j = 4;j<oldTAPtemp.length;j++){
						if(!oldTAPtemp[j].equals(oldCourseNo)){
							deletedTAP+=oldTAPtemp[j]+"  ";
						}
					}
					oldTAProperty.set(0, deletedTAP);
					FileOutputStream oldtfos = new FileOutputStream(oldTA);		
					OutputStreamWriter oldtwrite = new OutputStreamWriter (oldtfos); 
					BufferedWriter oldtwriter=new BufferedWriter(oldtwrite);
					for(int k =0;k<oldTAProperty.size();k++){
						oldtwriter.write(oldTAProperty.get(k)+"  \r\n");
					}
					oldtwriter.close();
				}
				//add to teacher profile
				File teacher = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+this.teacherNo+".txt");
				FileInputStream tfis = new FileInputStream(teacher);		
				InputStreamReader tread = new InputStreamReader (tfis); 
				BufferedReader treader=new BufferedReader(tread);
				String tline;
				Vector<String> t = new Vector<String>();
				while((tline=treader.readLine())!=null){
					t.addElement(tline);
				}
				t.addElement(courseNo+"  "+newCourseName+"  ");
				t.trimToSize();
				treader.close();
				
				FileOutputStream tfos = new FileOutputStream(teacher);		
				OutputStreamWriter twrite = new OutputStreamWriter (tfos); 
				BufferedWriter twriter=new BufferedWriter(twrite);
				for(int i =0;i<t.size();i++){
					twriter.write(t.get(i)+"  \r\n");
				}
				twriter.close();
				//add ta profile
				if(!tempTA.equals("")){
					for(int i =0;i<newTA.length;i++){
						File taf = new File("D:\\CourseSystemDB\\Student\\Student"+newTA[i]+".txt");
						FileInputStream tafis = new FileInputStream(taf);		
						InputStreamReader taread = new InputStreamReader (tafis); 
						BufferedReader tareader=new BufferedReader(taread);
						String taline;
						Vector<String> tav = new Vector<String>();
						while((taline=tareader.readLine())!=null){
							tav.addElement(taline);
						}
						tareader.close();
						String[] tatemp = tav.get(0).split("  ");
						String tatemp2 = "";
						for(int k = 0;k<tatemp.length;k++){
							tatemp2+=tatemp[k];
						}
						tatemp2+=courseNo;
						tav.set(0, tatemp2);
						
						FileOutputStream tafos = new FileOutputStream(taf);		
						OutputStreamWriter tawrite = new OutputStreamWriter (tafos); 
						BufferedWriter tawriter=new BufferedWriter(tawrite);
						for(int j =0;j<tav.size();j++){
							tawriter.write(tav.get(j)+"  \r\n");
						}
						tawriter.close();
					}
				}
				//change courseList.txt
				File courseList = new File("D:\\CourseSystemDB\\courseList.txt");
				BufferedReader courseListReader = new BufferedReader(new InputStreamReader(new FileInputStream(courseList)));
				Vector<String> courseListv = new Vector<String>();
				String courseListLine;
				while((courseListLine=courseListReader.readLine())!=null){
					courseListv.addElement(courseListLine);
				}
				courseListReader.close();
				for(int k =0;k<courseListv.size();k++){
					String[] tempcourseList = courseListv.get(k).split("  ");
					if(tempcourseList[0].equals(courseNo)){
						courseListv.set(k, newCourseNo+"  "+newCourseName+"  ");
						break;
					}
				}
				BufferedWriter courseListWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(courseList)));
				for(int k = 0;k<courseListv.size();k++){
					courseListWriter.write(courseListv.get(k));
				}
				courseListWriter.close();
				System.out.println("change succeeded!");
				return true;
			}else if(confirm.equals("N")){
				System.out.println("change concealed");
				return false;
			}else{
				return false;
			}
		}else{
			System.out.println("Bad input");
			return false;			
		}		
	}
	public boolean showStudent(String courseNo) throws IOException{
		File aimCourse = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		if((!courseNo.equals(""))&&(!aimCourse.exists())){
			System.out.println("course not exist!");
			return false;
		}else if(aimCourse.exists()){
			FileInputStream fis = new FileInputStream(aimCourse);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			Vector<String> stu = new Vector<String>();
			String line;
			//get all student number
			
			for(int i = 0;i<4;i++){
				reader.readLine();
			}
			while((line = reader.readLine())!=null){
				String[] temp = line.split("  ");
				stu.addElement(temp[0]);
				System.gc();
			}
			reader.close();
		
			//output
			for(int i = 0;i<stu.size();i++){
				File stuFile = new File("D:\\CourseSystemDB\\Student\\Student"+stu.get(i)+".txt");
				FileInputStream sfis = new FileInputStream(stuFile);		
				InputStreamReader sread = new InputStreamReader (sfis); 
				BufferedReader sreader=new BufferedReader(sread);
				line = sreader.readLine();
				sreader.close();
				String[] temp = line.split("  ");
				System.out.println(stu.get(i)+"  "+temp[1]);
			
			}
			return true;
		}else{
			System.out.println("Bad input!");
			return false;
		}
	}
	public synchronized boolean recordScore(String courseNo) throws IOException{
		File aimCourse = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		if(!aimCourse.exists()){
			System.out.println("Course not exists!");
			return false;
		}else if(aimCourse.exists()){
			FileInputStream fis = new FileInputStream(aimCourse);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			Vector<String> stu = new Vector<String>();
			Vector<String> stuNo = new Vector<String>();
			String line;
			for(int i = 0;i<4;i++){
				stu.addElement(reader.readLine());
			}
			while((line = reader.readLine())!=null){
				String[] temp = line.split("  ");
				stu.addElement(line);
				stuNo.addElement(temp[0]);
				System.gc();
			}
			reader.close();
			//get score
			for(int i = 0;i<stuNo.size();i++){
				//change courseXXX.txt
				System.out.println(stuNo.get(i)+"  Score:...");
				String score = buff.readLine();
				stu.set(i+4, stuNo.get(i)+"  "+score+"  ");
				
				
				
				//change student file
				File stuFile = new File("D:\\CourseSystemDB\\Student\\Student"+stuNo.get(i)+".txt");
				FileInputStream sfis = new FileInputStream(stuFile);		
				InputStreamReader sread = new InputStreamReader (sfis); 
				BufferedReader sreader=new BufferedReader(sread);
				Vector<String> stuP = new Vector<String>();
				String sline;
				while((sline=sreader.readLine())!=null){
					stuP.addElement(sline);
				}
				sreader.close();
				for(int j=1;j<stuP.size();j++){
					String[] temp = stuP.get(j).split("  ");
					if(temp[0].equals(courseNo)){
						stuP.set(j, courseNo+"  "+score+"  ");
						break;
					}
				}
				System.out.println(stuP);/////////
				FileOutputStream sfos = new FileOutputStream(stuFile);		
				OutputStreamWriter swrite = new OutputStreamWriter (sfos); 
				BufferedWriter swriter=new BufferedWriter(swrite);
				for(int j = 0;j<stuP.size();j++){
					swriter.write(stuP.get(j)+"  \r\n");
				}
				swriter.close();
				System.gc();
			}
			FileOutputStream fos = new FileOutputStream(aimCourse);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			for(int j = 0;j<stu.size();j++){
				writer.write(stu.get(j)+"  \r\n");
			}
			writer.close();			
			return true;
		}
		return false;
	}
	public Vector<String> getMyCourseList() throws IOException{
		this.myCourse = FileHelper.readTeaCourse(this.teacherNo);
		return this.myCourse;
	}
	public Vector<String> getMyCourseName() throws IOException{
		return FileHelper.readTeaCourseName(this.myCourse);
	}
	public Vector<String> getMyCourseTime() throws IOException{
		return FileHelper.readTeaCourseTime(this.myCourse);
	}
	public Vector<String> getMyCoursePlace() throws IOException{
		return FileHelper.readTeaCoursePlace(this.myCourse);
	}
	public Vector<String> getCourseStu(String courseNo) throws IOException{
		return FileHelper.readCourseStu(courseNo);
	}
	public Vector<String> guiShowCourse()	{
		return theBigHomework.FileHelper.readAllCourse();
	}
	public Vector<String> guiShowCourseName() throws IOException	{
		return theBigHomework.FileHelper.readAllCourseName();
	}
	public Vector<String> guiShowCourseTime() throws IOException	{
		return theBigHomework.FileHelper.readAllCourseTime();
	}
	public Vector<String> guiShowCoursePlace() throws IOException	{
		return theBigHomework.FileHelper.readAllCoursePlace();
	}
	public Vector<String> guiShowAllCoursePor()throws IOException{
		return FileHelper.readAllCoursePro();
	}
	public synchronized boolean guiPublish(String courseNo,String courseName,String courseLength,String coursePoint,String coursePlace,String[] coTeacher,String[] ta,String courseProperty) throws IOException{
		/*courseLenth is actually courseTime
		 * coTeacher doesn't contains Teacher himself
		 */
		File courseLiFile = new File("D:\\CourseSystemDB\\courseList.txt");
		Vector<String> item = new Vector<String>();
		Vector<String> courseNoLi = new Vector<String>();
		Vector<String> courseNameLi = new Vector<String>();
		//read overall course data base
		if(!courseLiFile.exists()){
			courseLiFile.createNewFile();
		}
		FileInputStream fis = new FileInputStream(courseLiFile);		
		InputStreamReader read = new InputStreamReader (fis); 
		BufferedReader reader=new BufferedReader(read);
		String line;
		while((line=reader.readLine())!=null){
			item.addElement(line);
			String[] temp = line.split("  ");
			courseNoLi.addElement(temp[0]);
			courseNameLi.addElement(temp[1]);
		}
		reader.close();
		//get course profile
		
//		System.out.println("Please input course number...");
//		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
//		String courseNo = buff.readLine();	
		
		File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		if(file.exists()){
			System.out.println("course number already exist!");
			return false;
		}
		
//		System.out.println("Please input course name...");
//		String courseName = buff.readLine();
		if(courseNameLi.contains(courseName)){
			System.out.println("course name already exist!");
			return false;
		}
		
//		System.out.println("Please input how many weeks the course last&what time it is...[length/time]");
		
//		String courseLength = buff.readLine();		
		
//		System.out.println("Please input how many points the course values...[number only]");
//		String coursePoint ;
		Integer temp ;
		try{
			temp = Integer.parseInt(coursePoint);
			coursePoint = temp.toString();
		}catch(NumberFormatException e){
			System.out.println("numbers only!");
			return false;
		}
			
		
//		System.out.println("Please input where the course is going to be taught...");
//		String coursePlace = buff.readLine();
		
//		System.out.println("Please input coTeacher's teacher number...[splited with English';']");
//		String tempcoTea = buff.readLine();
//		String[] coTeacher = tempcoTea.split(";");
		
			//judge if the teacher exists!
		if(!((coTeacher.length == 1)&&coTeacher[0].equals("null"))){
			for(int i =0;i<coTeacher.length;i++){
					File coTea = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+coTeacher[i]+".txt");
					if(!coTea.exists()){
						return false;
					}
			}
		}else if((coTeacher.length == 1)&&coTeacher[0].equals("null")){
			coTeacher = new String[0];
		}else{
			return false;
		}

		
//		System.out.println("Please input teacher assistant's student number...[splited with English';']");
//		String tempTA = buff.readLine();
//		String[] ta = tempTA.split(";");
		
			//judge if the student exists!
		if(!((ta.length == 1)&&ta[0].equals("null"))){
				for(int i =0;i<ta.length;i++){
					File coTA = new File("D:\\CourseSystemDB\\Student\\Student"+ta[i]+".txt");
					if(!coTA.exists()){
						return false;
					}
				}
		}else if((ta.length == 1)&&ta[0].equals("null")){
			ta = new String[0];
		}else{
			return false;
		}
		if(!(courseProperty.equals("1")||courseProperty.equals("0"))){
			return false;
		}
			
//		System.out.println("Please input course property...[1 for compulsory/0 for elective]");
//		String courseProperty = buff.readLine();		
//		while(!(courseProperty.equals("1")||courseProperty.equals("0"))){
//			System.out.println("Please in put 0 or 1!");
//			courseProperty = buff.readLine();
//		}
		//confirm change
//		String courseProfile = courseNo+"  "+courseName+"  courseLength:"+courseLength+" weeks  coursePoint:"+coursePoint+"  Place:"
//				+coursePlace+"  courseProperty:"+courseProperty+"  \r\nTeacher:"+this.teacherNo+"  ";
//		for(int i = 0;i<coTeacher.length;i++){
//			courseProfile=courseProfile+coTeacher[i]+"  ";
//		}
//		courseProfile+="\r\nTA:  ";
//		for(int i = 0;i<ta.length;i++){
//			courseProfile=courseProfile+ta[i]+"  ";
//		}
//		System.out.println(courseProfile);
//		System.out.println("confirm?[Y/N]");
		//create course
//		String confirm = buff.readLine();
//		if(confirm.equals("Y")){
			//create course profile			
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			writer.write(courseNo+"  "+courseName+"  "+courseLength+"  "+coursePoint+"  "+coursePlace+"  "+"  \r\n");
			writer.write("T  "+this.teacherNo+"  ");
			for(int i = 0;i<coTeacher.length;i++){
				writer.write(coTeacher[i]+"  ");
			}
			writer.write("\r\n");
			writer.write("Property  "+courseProperty+"  \r\n");	
			writer.write("TA  ");
			for(int i = 0;i<ta.length;i++){
				writer.write(ta[i]+"  ");
			}
			writer.write("\r\n");
			writer.close();
			// add to overall course list
			FileOutputStream fos2 = new FileOutputStream(courseLiFile);		
			OutputStreamWriter write2 = new OutputStreamWriter (fos2); 
			BufferedWriter writer2=new BufferedWriter(write2);
			item.addElement(courseNo+"  "+courseName+"  ");
			item.trimToSize();
			for(int i =0;i<item.size();i++){
				writer2.write(item.get(i)+"\r\n");
			}
			writer2.close();
			System.gc();
			//add to teacher profile
			File teacher = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+this.teacherNo+".txt");
			FileInputStream tfis = new FileInputStream(teacher);		
			InputStreamReader tread = new InputStreamReader (tfis); 
			BufferedReader treader=new BufferedReader(tread);
			String tline;
			Vector<String> t = new Vector<String>();
			while((tline=treader.readLine())!=null){
				t.addElement(tline);
			}
			t.addElement(courseNo+"  "+courseName+"  ");
			t.trimToSize();
			treader.close();
			
			FileOutputStream tfos = new FileOutputStream(teacher);		
			OutputStreamWriter twrite = new OutputStreamWriter (tfos); 
			BufferedWriter twriter=new BufferedWriter(twrite);
			for(int i =0;i<t.size();i++){
				twriter.write(t.get(i)+"  \r\n");
			}
			twriter.close();
			if(coTeacher.length>1){
				for(int i =0;i<coTeacher.length;i++){
					File coteacher = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+coTeacher[i]+".txt");
					FileInputStream cotfis = new FileInputStream(coteacher);		
					InputStreamReader cotread = new InputStreamReader (cotfis); 
					BufferedReader cotreader=new BufferedReader(cotread);
					String cotline;
					Vector<String> cot = new Vector<String>();
					while((cotline=cotreader.readLine())!=null){
						cot.addElement(cotline);
					}
					cot.addElement(courseNo+"  "+courseName+"  ");
					cot.trimToSize();
					cotreader.close();
					
					FileOutputStream cotfos = new FileOutputStream(coteacher);		
					OutputStreamWriter cotwrite = new OutputStreamWriter (cotfos); 
					BufferedWriter cotwriter=new BufferedWriter(cotwrite);
					for(int j =0;j<cot.size();j++){
						cotwriter.write(cot.get(j)+"  \r\n");
					}
					cotwriter.close();
				}
			}
			
			//add to TA profile
			if(!ta[0].equals("")){
				for(int i =0;i<ta.length;i++){
					File taf = new File("D:\\CourseSystemDB\\Student\\Student"+ta[i]+".txt");
					FileInputStream tafis = new FileInputStream(taf);		
					InputStreamReader taread = new InputStreamReader (tafis); 
					BufferedReader tareader=new BufferedReader(taread);
					String taline;
					Vector<String> tav = new Vector<String>();
					while((taline=tareader.readLine())!=null){
						tav.addElement(taline);
					}
					tareader.close();
					String[] tatemp = tav.get(0).split("  ");
					String tatemp2 = "";
					for(int k = 0;k<tatemp.length;k++){
						tatemp2+=tatemp[k]+"  ";
					}
					tatemp2+=courseNo+"  ";
					tav.set(0, tatemp2);
					
					FileOutputStream tafos = new FileOutputStream(taf);		
					OutputStreamWriter tawrite = new OutputStreamWriter (tafos); 
					BufferedWriter tawriter=new BufferedWriter(tawrite);
					for(int j =0;j<tav.size();j++){
						tawriter.write(tav.get(j)+"  \r\n");
					}
					tawriter.close();
				}
			}
			
			System.out.println("Publish succeeded!");
			return true;
//		}else if(confirm.equals("N")){
//			System.out.println("Publish concealed");
//			return false;
//		}else{
//			return false;
//		}
	
	}
	public synchronized boolean guiUpdate(String oldCourseNo,String courseNo,String courseName,String courseLength,String coursePoint,String coursePlace,String[] coTeacher,String[] ta,String courseProperty) throws IOException{
		/*courseLenth is actually courseTime
		 * coTeacher doesn't contains Teacher himself
		 */
		File courseLiFile = new File("D:\\CourseSystemDB\\courseList.txt");
		Vector<String> item = new Vector<String>();
		Vector<String> courseNoLi = new Vector<String>();
		Vector<String> courseNameLi = new Vector<String>();
		//read overall course data base
		if(!courseLiFile.exists()){
			courseLiFile.createNewFile();
		}
		FileInputStream fis = new FileInputStream(courseLiFile);		
		InputStreamReader read = new InputStreamReader (fis); 
		BufferedReader reader=new BufferedReader(read);
		String line;
		while((line=reader.readLine())!=null){
			item.addElement(line);
			String[] temp = line.split("  ");
			courseNoLi.addElement(temp[0]);
			courseNameLi.addElement(temp[1]);
		}
		reader.close();
		//get course profile
		
//		System.out.println("Please input course number...");
//		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
//		String courseNo = buff.readLine();	
		File oldFile = new File("D:\\CourseSystemDB\\Course\\"+oldCourseNo+".txt");
		oldFile.delete();
		File file = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		
		if(file.exists()){
			System.out.println("course number already exist!");
			return false;
		}
		
//		System.out.println("Please input course name...");
//		String courseName = buff.readLine();
		if(courseNameLi.contains(courseName)){
			System.out.println("course name already exist!");
			return false;
		}
		
//		System.out.println("Please input how many weeks the course last&what time it is...[length/time]");
		
//		String courseLength = buff.readLine();		
		
//		System.out.println("Please input how many points the course values...[number only]");
//		String coursePoint ;
		Integer temp ;
		try{
			temp = Integer.parseInt(coursePoint);
			coursePoint = temp.toString();
		}catch(NumberFormatException e){
			System.out.println("numbers only!");
			return false;
		}
			
		
//		System.out.println("Please input where the course is going to be taught...");
//		String coursePlace = buff.readLine();
		
//		System.out.println("Please input coTeacher's teacher number...[splited with English';']");
//		String tempcoTea = buff.readLine();
//		String[] coTeacher = tempcoTea.split(";");
		
			//judge if the teacher exists!
		if(!((coTeacher.length == 1)&&coTeacher[0].equals("null"))){
			for(int i =0;i<coTeacher.length;i++){
					File coTea = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+coTeacher[i]+".txt");
					if(!coTea.exists()){
						return false;
					}
			}
		}else if((coTeacher.length == 1)&&coTeacher[0].equals("null")){
			coTeacher = new String[0];
		}else{
			return false;
		}

		
//		System.out.println("Please input teacher assistant's student number...[splited with English';']");
//		String tempTA = buff.readLine();
//		String[] ta = tempTA.split(";");
		
			//judge if the student exists!
		if(!((ta.length == 1)&&ta[0].equals("null"))){
				for(int i =0;i<ta.length;i++){
					File coTA = new File("D:\\CourseSystemDB\\Student\\Student"+ta[i]+".txt");
					if(!coTA.exists()){
						return false;
					}
				}
		}else if((ta.length == 1)&&ta[0].equals("null")){
			ta = new String[0];
		}else{
			return false;
		}
		if(!(courseProperty.equals("1")||courseProperty.equals("0"))){
			return false;
		}
			
//		System.out.println("Please input course property...[1 for compulsory/0 for elective]");
//		String courseProperty = buff.readLine();		
//		while(!(courseProperty.equals("1")||courseProperty.equals("0"))){
//			System.out.println("Please in put 0 or 1!");
//			courseProperty = buff.readLine();
//		}
		//confirm change
//		String courseProfile = courseNo+"  "+courseName+"  courseLength:"+courseLength+" weeks  coursePoint:"+coursePoint+"  Place:"
//				+coursePlace+"  courseProperty:"+courseProperty+"  \r\nTeacher:"+this.teacherNo+"  ";
//		for(int i = 0;i<coTeacher.length;i++){
//			courseProfile=courseProfile+coTeacher[i]+"  ";
//		}
//		courseProfile+="\r\nTA:  ";
//		for(int i = 0;i<ta.length;i++){
//			courseProfile=courseProfile+ta[i]+"  ";
//		}
//		System.out.println(courseProfile);
//		System.out.println("confirm?[Y/N]");
		//create course
//		String confirm = buff.readLine();
//		if(confirm.equals("Y")){
			//create course profile			
			FileOutputStream fos = new FileOutputStream(file);		
			OutputStreamWriter write = new OutputStreamWriter (fos); 
			BufferedWriter writer=new BufferedWriter(write);
			writer.write(courseNo+"  "+courseName+"  "+courseLength+"  "+coursePoint+"  "+coursePlace+"  "+"  \r\n");
			writer.write("T  "+this.teacherNo+"  ");
			for(int i = 0;i<coTeacher.length;i++){
				writer.write(coTeacher[i]+"  ");
			}
			writer.write("\r\n");
			writer.write("Property  "+courseProperty+"  \r\n");	
			writer.write("TA  ");
			for(int i = 0;i<ta.length;i++){
				writer.write(ta[i]+"  ");
			}
			writer.write("\r\n");
			writer.close();
			// add to overall course list
			FileOutputStream fos2 = new FileOutputStream(courseLiFile);		
			OutputStreamWriter write2 = new OutputStreamWriter (fos2); 
			BufferedWriter writer2=new BufferedWriter(write2);
			item.addElement(courseNo+"  "+courseName+"  ");
			item.trimToSize();
			for(int i =0;i<item.size();i++){
				writer2.write(item.get(i)+"\r\n");
			}
			writer2.close();
			System.gc();
			//add to teacher profile
			File teacher = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+this.teacherNo+".txt");
			FileInputStream tfis = new FileInputStream(teacher);		
			InputStreamReader tread = new InputStreamReader (tfis); 
			BufferedReader treader=new BufferedReader(tread);
			String tline;
			Vector<String> t = new Vector<String>();
			while((tline=treader.readLine())!=null){
				t.addElement(tline);
			}
			t.addElement(courseNo+"  "+courseName+"  ");
			t.trimToSize();
			treader.close();
			
			FileOutputStream tfos = new FileOutputStream(teacher);		
			OutputStreamWriter twrite = new OutputStreamWriter (tfos); 
			BufferedWriter twriter=new BufferedWriter(twrite);
			for(int i =0;i<t.size();i++){
				twriter.write(t.get(i)+"  \r\n");
			}
			twriter.close();
			if(coTeacher.length>1){
				for(int i =0;i<coTeacher.length;i++){
					File coteacher = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+coTeacher[i]+".txt");
					FileInputStream cotfis = new FileInputStream(coteacher);		
					InputStreamReader cotread = new InputStreamReader (cotfis); 
					BufferedReader cotreader=new BufferedReader(cotread);
					String cotline;
					Vector<String> cot = new Vector<String>();
					while((cotline=cotreader.readLine())!=null){
						cot.addElement(cotline);
					}
					cot.addElement(courseNo+"  "+courseName+"  ");
					cot.trimToSize();
					cotreader.close();
					
					FileOutputStream cotfos = new FileOutputStream(coteacher);		
					OutputStreamWriter cotwrite = new OutputStreamWriter (cotfos); 
					BufferedWriter cotwriter=new BufferedWriter(cotwrite);
					for(int j =0;j<cot.size();j++){
						cotwriter.write(cot.get(j)+"  \r\n");
					}
					cotwriter.close();
				}
			}
			
			//add to TA profile
			if(!ta[0].equals("")){
				for(int i =0;i<ta.length;i++){
					File taf = new File("D:\\CourseSystemDB\\Student\\Student"+ta[i]+".txt");
					FileInputStream tafis = new FileInputStream(taf);		
					InputStreamReader taread = new InputStreamReader (tafis); 
					BufferedReader tareader=new BufferedReader(taread);
					String taline;
					Vector<String> tav = new Vector<String>();
					while((taline=tareader.readLine())!=null){
						tav.addElement(taline);
					}
					tareader.close();
					String[] tatemp = tav.get(0).split("  ");
					String tatemp2 = "";
					for(int k = 0;k<tatemp.length;k++){
						tatemp2+=tatemp[k]+"  ";
					}
					tatemp2+=courseNo+"  ";
					tav.set(0, tatemp2);
					
					FileOutputStream tafos = new FileOutputStream(taf);		
					OutputStreamWriter tawrite = new OutputStreamWriter (tafos); 
					BufferedWriter tawriter=new BufferedWriter(tawrite);
					for(int j =0;j<tav.size();j++){
						tawriter.write(tav.get(j)+"  \r\n");
					}
					tawriter.close();
				}
			}
			
			System.out.println("Publish succeeded!");
			return true;
	}
	
	
	
	public synchronized boolean guiRecordScore(String courseNo,String[] scoreList) throws IOException{
		File aimCourse = new File("D:\\CourseSystemDB\\Course\\"+courseNo+".txt");
		if(!aimCourse.exists()){
			System.out.println("Course not exists!");
			return false;
		}else if(aimCourse.exists()){
			BufferedReader reader=new BufferedReader(new InputStreamReader (new FileInputStream(aimCourse)));
			Vector<String> claPro = new Vector<String>(4);
			Vector<String> stuNo = new Vector<String>();
			String line;
			for(int i = 0;i<4;i++){
				claPro.addElement(reader.readLine());
			}
			while((line = reader.readLine())!=null){
				String[] temp = line.split("  ");
				claPro.addElement(line);
				stuNo.addElement(temp[0]);
				System.gc();
			}
			reader.close();
			if(claPro.size() == 4){
				System.out.println("No Student under this course!");
				return false;
			}
			//get score
			for(int i = 0;i<stuNo.size();i++){
				//change courseXXX.txt				
				claPro.setElementAt(stuNo.get(i)+"  "+scoreList[i], 4+i);				
				//change student file
				File stuFile = new File("D:\\CourseSystemDB\\Student\\Student"+stuNo.get(i)+".txt");
				BufferedReader sreader=new BufferedReader(new InputStreamReader (new FileInputStream(stuFile)));
				Vector<String> stuP = new Vector<String>();
				String sline;
				while((sline=sreader.readLine())!=null){
					stuP.addElement(sline);
				}
				sreader.close();
				for(int j=1;j<stuP.size();j++){
					String[] temp = stuP.get(j).split("  ");
					if(temp[0].equals(courseNo)){
						stuP.setElementAt(courseNo+"  "+scoreList[i]+"  ",j);
						break;
					}
				}
				
				
				
				
				
				BufferedWriter swriter=new BufferedWriter(new OutputStreamWriter (new FileOutputStream(stuFile)));
				for(int j = 0;j<stuP.size();j++){
					swriter.write(stuP.get(j)+"\r\n");
				}
				swriter.close();
				System.gc();
			}
			//course write back
			BufferedWriter writer=new BufferedWriter(new OutputStreamWriter (new FileOutputStream(aimCourse)));
			for(int j = 0;j<claPro.size();j++){
				writer.write(claPro.get(j)+"  \r\n");
			}
			writer.close();			
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		String[] coTeacher = new String[]{"null"};
		String[] ta = new String[]{"121250323"};
		Teacher a = new Teacher("12453","aaa");
		System.out.println(a.guiPublish("c0782", "aaaaaaaa", "19/19:00-21:00", "2", "uuuuuuuuuuuu", coTeacher, ta, "1"));
		System.out.println(a.guiRecordScore("c0782",new String[]{"60"}));
		System.out.println(a.guiRecordScore("c0783",new String[]{"70"}));
	}
}
