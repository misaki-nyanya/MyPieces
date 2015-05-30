package theBigHomework;

import java.io.*;
import java.util.*;

public class Logger 
{
	public static Boolean adminLogin(String adminName,String adminKey) throws Throwable
	{
		File file=new File("D:\\CourseSystemDB\\Admin.txt");
		if(!file.exists())
		{
			System.out.println("File"+file.getName()+" lost!");
		}
		else
		{
			//read file
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			String line;
			Vector<String> v = new Vector<String>();
			while ((line = reader.readLine()) != null) 
			{   
				String[] temp = line.split("  ");
				v.addElement(temp[0]);
				v.addElement(temp[1]);
			}
			v.trimToSize();
			
			reader.close();
			read.close();
			fis.close();
			
			//write back
//			FileOutputStream fos = new FileOutputStream(file);		
//			OutputStreamWriter write = new OutputStreamWriter (fos); 
//			BufferedWriter writer=new BufferedWriter(write);
//			for(int i = 0;i<v.size();i=i+2)
//			{
//				writer.write(v.get(i)+"  "+v.get(i+1)+"\r\n");
//			}
//			
//			writer.close();
//			write.close();
//			fos.close();
			
			if(
				(v.contains(adminName))
				&&(v.get(v.indexOf(adminName)+1)).equals(adminKey)
			  )
			{
				
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	public static Boolean stuLogin(String stuNo,String stuKey) throws Throwable
	{
		File file=new File("D:\\CourseSystemDB\\Student\\Student"+stuNo+".txt");
		if(!file.exists())
		{
			System.out.println("File "+file.getName()+" lost!");
//			FileOutputStream fos = new FileOutputStream(file);		
//			OutputStreamWriter write = new OutputStreamWriter (fos); 
//			BufferedWriter writer=new BufferedWriter(write);
//			file.createNewFile();
//			writer.write(stuNo+"  "+"AAA"+"  "+"Key"+0);
//			writer.close();
		}
		else
		{
			//read file
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			String line;
			Vector<String> v = new Vector<String>();
			while ((line = reader.readLine()) != null) 
			{   
				String[] temp = line.split("  ");
				for(int i = 0;i< temp.length;i++){
					v.addElement(temp[i]);
				}
			}
			v.trimToSize();
			
			reader.close();
			read.close();
			fis.close();
			
			//write back
//			FileOutputStream fos = new FileOutputStream(file);		
//			OutputStreamWriter write = new OutputStreamWriter (fos); 
//			BufferedWriter writer=new BufferedWriter(write);
//			for(int i = 0;i<v.size();i++)
//			{
//				writer.write(v.get(i)+"  ");
//			}
//			
//			writer.close();
//			write.close();
//			fos.close();
			
			if(
				(v.contains(stuNo))
				&&(v.get(v.indexOf(stuNo)+2)).equals(stuKey)
			  )
			{
				
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	public static Boolean teaLogin(String teaNo,String teaKey) throws Throwable
	{
		File file=new File("D:\\CourseSystemDB\\Teacher\\Teacher"+teaNo+".txt");
		if(!file.exists())
		{
			System.out.println("File "+file.getName()+" lost!");
//			FileOutputStream fos = new FileOutputStream(file);		
//			OutputStreamWriter write = new OutputStreamWriter (fos); 
//			BufferedWriter writer=new BufferedWriter(write);
//			file.createNewFile();
//			writer.write(teaNo+"  "+"AAA"+"  "+"Key"+0);
//			writer.close();
		}
		else
		{
			//read file
			FileInputStream fis = new FileInputStream(file);		
			InputStreamReader read = new InputStreamReader (fis); 
			BufferedReader reader=new BufferedReader(read);
			String line;
			Vector<String> v = new Vector<String>();
			while ((line = reader.readLine()) != null) 
			{   
				String[] temp = line.split("  ");
				for(int i = 0;i< temp.length;i++){
					v.addElement(temp[i]);
				}
			}
			v.trimToSize();
			
			reader.close();
			read.close();
			fis.close();
			
			//write back
//			FileOutputStream fos = new FileOutputStream(file);		
//			OutputStreamWriter write = new OutputStreamWriter (fos); 
//			BufferedWriter writer=new BufferedWriter(write);
//			for(int i = 0;i<v.size();i++)
//			{
//				writer.write(v.get(i)+"  ");
//			}
//			
//			writer.close();
//			write.close();
//			fos.close();
			
			if(
				(v.contains(teaNo))
				&&(v.get(v.indexOf(teaNo)+2)).equals(teaKey)
			  )
			{
				
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	public static void main(String[] args) throws Throwable {
//		System.out.println(adminLogin("Admin","Admin"));
		System.out.println(stuLogin("121250323","aaa"));
//		System.out.println(teaLogin("1250111","CASS"));
	}
}
