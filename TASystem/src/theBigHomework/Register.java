package theBigHomework;

//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Register {

	public static Boolean stuRegister(String stuNo,String stuName,String stuKey) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Student\\Student"+stuNo+".txt");
		if(file.exists()){
			System.out.println("Student exists!");
			return false;
		}else{
			while(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			buff.write(stuNo+"  "+stuName+"  "+stuKey+"  ");
			buff.close();
			return true;
		}		
	}
	public static Boolean teaRegister(String teaNo,String teaName,String teaKey) throws IOException{
		File file = new File("D:\\CourseSystemDB\\Teacher\\Teacher"+teaNo+".txt");
		if(file.exists()){
			System.out.println("Teacher exists!");
			return false;
		}else{
			while(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			buff.write(teaNo+"  "+teaName+"  "+teaKey+"  ");
			buff.close();
			return true;
		}		
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
//	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		System.out.println(StuRegister(121250111,"CASS","dadada"));
//		System.out.println(TeaRegister(1250111,"CASS","dadada"));
//		
//		File file=new File("Student121250111.txt");
//		FileInputStream fis = new FileInputStream(file);		
//		InputStreamReader read = new InputStreamReader (fis); 
//		BufferedReader reader=new BufferedReader(read);
//		String line;
//		
//		while ((line = reader.readLine()) != null) 
//		{   
//			System.out.println(line);
//			
//		}
//		
//		
//		reader.close();
//		read.close();
//		fis.close();
//	}

}
