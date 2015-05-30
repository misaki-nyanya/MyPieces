package getURL;

/**
 *HttpUtil.java
 *下午05:26:36
 */

 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
 
/**
 * @author yoUng
 * @description 发送http请求
 * @filename HttpUtil.java
 * @time 2011-6-15 下午05:26:36
 * @version 1.0
 */
public class HttpUtil {
 
	public static String http(String url, Map<String, String> params) {
		URL u = null;
		HttpURLConnection con = null;
		//构建请求参数
		StringBuffer sb = new StringBuffer();
		if(params!=null){
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		System.out.println("send_url:"+url);
		System.out.println("send_data:"+sb.toString());
		//尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(sb.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
 
		//读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con
					.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer.toString();
	}
	public static void httpGET() {
		String url="http://jwas3.nju.edu.cn:8080/jiaowu/student/teachinginfo/allCourseList.do?method=getCourseList&curTerm=20132&curSpeciality=250&curGrade=2012";
		URL u = null;
		HttpURLConnection con = null;
		//构建请求参数
		StringBuffer sb = new StringBuffer();
		sb.append("");
		System.out.println("send_data:"+sb.toString());
		//尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Referer", "http://自主打马/jiaowu/student/teachinginfo/allCourseList.do?method=getTermAcademy");
			con.setRequestProperty("Cookie", "JSESSIONID=自主打马; LOGIN=自主打马; SCREEN_NAME=自主打马");
			con.setRequestProperty("Connection", "keep-alive");
			con.setAllowUserInteraction(true);
			con.setUseCaches(true);
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(sb.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
 
		//读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con
					.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.print(buffer.toString());
	}
	public static void saveCourses(int grade,BufferedWriter bw) throws IOException
	{
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("records.txt")));
		int courseID=101;
		
		while(courseID<900)
		{
			String url="http://自主打马/jiaowu/student/teachinginfo/allCourseList.do?method=getCourseList&curTerm=20132&curSpeciality="+courseID+"&curGrade="+grade;
			URL u = null;
			HttpURLConnection con = null;
			//构建请求参数
			StringBuffer sb = new StringBuffer();
			sb.append("");
			System.out.println("send_data:"+sb.toString());
			//尝试发送请求
			try {
				u = new URL(url);
				con = (HttpURLConnection) u.openConnection();
				con.setRequestMethod("GET");
				con.setDoOutput(true);
				con.setDoInput(true);
				con.setUseCaches(false);
				con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				con.setRequestProperty("Referer", "http://自主打马/jiaowu/student/teachinginfo/allCourseList.do?method=getTermAcademy");
				con.setRequestProperty("Cookie", "JSESSIONID=自主打马; LOGIN=自主打马; SCREEN_NAME=自主打马");
				con.setRequestProperty("Connection", "keep-alive");
				con.setAllowUserInteraction(true);
				con.setUseCaches(true);
				OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
				osw.write(sb.toString());
				osw.flush();
				osw.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					con.disconnect();
				}
			}
	 
			//读取返回内容
			String temp;
			StringBuffer buffer = new StringBuffer();
			try {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(con
						.getInputStream(), "UTF-8"));
				
				while ((temp = br.readLine()) != null) {
					buffer.append(temp);
					buffer.append("\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//获取空文件进行比对
			StringBuffer noReply2=new StringBuffer();
			try {
				BufferedReader read_empty=new BufferedReader(new InputStreamReader(new FileInputStream("100.html")));
				while((temp = read_empty.readLine()) != null){
					noReply2.append(temp);
					noReply2.append("\n");
				}
				read_empty.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//当不是空列表时进行记录
			if(!buffer.toString().equals(noReply2.toString()))
			{
				int p=0;
				int last=0;
				int p_clear=0;
				int last_clear=0;
				
				while(p<buffer.length())
				{
					p=buffer.indexOf("TABLE_TR",p);
					last=buffer.indexOf("tr",p);
					if((p==-1)||(last==-1))
					{
						break;
					}					
					//提取每一行
					StringBuilder clear=new StringBuilder(buffer.substring(p,last));
					//course id
					p_clear=clear.indexOf("<td align=\"center\" valign=\"middle\">",p_clear);
					last_clear=clear.indexOf("</td>",p_clear);
					p_clear=p_clear+"<td align=\"center\" valign=\"middle\">".length();
					bw.write(clear.substring(p_clear, last_clear)+"~");
					p_clear=p_clear+1;
					//course name
					p_clear=clear.indexOf("<td valign=\"middle\">",p_clear);
					last_clear=clear.indexOf("</td>",p_clear);
					p_clear=p_clear+"<td valign=\"middle\">".length();
					bw.write(clear.substring(p_clear, last_clear)+"~");
					p_clear=p_clear+1;
					//others
					while((p_clear<clear.lastIndexOf("<td align=\"center\" valign=\"middle\">")))
					{
						p_clear=clear.indexOf("<td align=\"center\" valign=\"middle\">",p_clear);
						last_clear=clear.indexOf("</td>",p_clear);
						p_clear=p_clear+"<td align=\"center\" valign=\"middle\">".length();
						bw.write(clear.substring(p_clear, last_clear)+"~");
						p_clear=p_clear+1;
					}
					//last 2
					p_clear=clear.indexOf("<td valign=\"middle\">",p_clear);
					last_clear=clear.indexOf("</td>",p_clear);
					p_clear=p_clear+"<td valign=\"middle\">".length();
					bw.write(clear.substring(p_clear, last_clear)+"~");
					p_clear=p_clear+1;
					p_clear=clear.indexOf("<td valign=\"middle\">",p_clear);
					last_clear=clear.indexOf("</td>",p_clear);
					p_clear=p_clear+"<td valign=\"middle\">".length();
					bw.write(clear.substring(p_clear, last_clear)+"~");
					p_clear=p_clear+1;
					bw.write("\n");
					//prepare for next
					p_clear=0;
					last_clear=0;
					p=last+1;
				}
			}
			courseID++;
		}
		//bw.close();
		
	}
	public static void main(String[] args) throws IOException{
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("records.txt")));
//		HttpUtil.saveCourses(2013,bw);
//		HttpUtil.saveCourses(2012,bw);
//		HttpUtil.saveCourses(2011,bw);
//		HttpUtil.saveCourses(2010,bw);
//		bw.close();
		HttpUtil.httpGET();
		//System.out.println("\u0350\u0257\u0276");
	}
 
}
