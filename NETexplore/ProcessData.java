package getURL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ProcessData {

	public ArrayList<String> data=new ArrayList<String>();
	public HashMap<String,ArrayList<String>> teacherList=new HashMap<String,ArrayList<String>>();
	
	public void readData() throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream("final_courses_sorted.txt")));
		String temp;
		while((temp=br.readLine())!=null){
			if(!data.contains(temp)){
				data.add(temp);
			}			
		}
		br.close();		
	}
	public void process(){
		for(String temp : data){
			
			String[] list=temp.split("~");
			try{
				if(!list[7].equals("")){
					String teacher=list[7];
					String faculty=list[3];
					String courseID=list[0];
					
						if(teacherList.containsKey(teacher)){
							
							ArrayList<String> info = teacherList.get(teacher);
							if(!info.contains(courseID)){
								info.add(courseID);
							}else{
								System.out.println(courseID);
							}
							teacherList.put(teacher, info);
						}
						else{
							ArrayList<String> info = new ArrayList<String>();
							info.add(faculty);
							info.add(courseID);
							teacherList.put(teacher, info);
						}
						
					
				}
			}catch(java.lang.ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
			}			
		}
	}
	public void arrangeCourseData(){
		Collections.sort(data);
	}
	public void writeCourseData() throws IOException{
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("courses_sorted.txt")));
		for(String temp : data){
			bw.write(temp+"\n");
		}
		bw.close();
	}
	public void writeData() throws IOException{
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("teacher2.txt")));
		for(String temp : teacherList.keySet()){
			bw.write(temp+": ");
			ArrayList<String> info=teacherList.get(temp);
			bw.write(info.get(0)+"#");
			for(int i=1;i<info.size();i++){
				bw.write(info.get(i)+";");
			}
			bw.write("\n");
		}
		bw.close();
	}
	public static void main(String[] args) throws IOException {
		ProcessData p=new ProcessData();
		p.readData();
		p.process();
		p.writeData();	
//		String[] a="12000030~热学~核心~物理学院~3.0~3~~".split("~");
//		System.out.println(a.length);
	}

}
