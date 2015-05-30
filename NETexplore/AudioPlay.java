package getURL;
import java.io.File; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.FileInputStream; 

import sun.audio.AudioPlayer; 

/** 
* <li>Title: Audio.java</li> 
* <li>Project: WorkFrame</li> 
* <li>Package: com.chengyi.common.util</li> 
* <li>Description: 声音播放</li> 
* <li>Copyright: Copyright (c) 2010</li> 
* <li>Company: studio crescent </li> 
* <li>Created on 2010-7-23 下午05:14:39</li> 
* 
* @author chun_chang 
* @version 1.0 
* 
*/ 
public class AudioPlay extends Thread{ 

	private static InputStream inputStream = null; 
	private static String file = null;
	private static String file1 = "F:/music/miwa/3rd-chAngE/01 chAngE.wav"; 
	private static String file2 = ""; 
	private static String file3 = ""; 
	private static String file4 = ""; 
	private static String file5 = ""; 
	private static String file6 = ""; 

//	public AudioPlay(String file){ 
//		this.file = file; 
//		this.setName("Message Sound Thread"); 
//	} 
	public void chooseAudio(int num){
		switch(num){
			case 1: file=file1;break;
			case 2: file=file2;break;
			case 3: file=file3;break;
			case 4: file=file4;break;
			case 5: file=file5;break;
			case 6: file=file6;break;
		}
	}
	public void play() throws IOException { 
		inputStream = new FileInputStream(new File(file)); 
		AudioPlayer.player.start(inputStream); 
	} 

	@Override 
	public void run() { 
		try { 
			this.play(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 
	public static void playAudio(int num){
		AudioPlay a=new AudioPlay();
		a.chooseAudio(num);
		a.start();
	}
	public static void main(String[] args){
		AudioPlay.playAudio(1);
	}

} 
