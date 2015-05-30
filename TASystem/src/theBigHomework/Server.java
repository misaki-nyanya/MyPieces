package theBigHomework;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Server {
	private static JFrame f = new JFrame();
	private static JButton b = new JButton("Close the server");

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int a = JOptionPane.showConfirmDialog(null, "Confirm to quit server?", "Quiting...", JOptionPane.YES_NO_OPTION);
				if(a == JOptionPane.YES_OPTION){
					System.exit(0);
				}else if(a == JOptionPane.NO_OPTION){					
				}else{					
				}
			}
			
		});
		f.setTitle("Server is Runing...");
		f.add(b);
		f.setPreferredSize(new Dimension(500,300));
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(30000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
			Socket s = null;
			try {
				s = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("socket accepted!");
			BufferedReader buffr = null;
			BufferedWriter buffw = null;
			try {
				buffr = new BufferedReader(new InputStreamReader( s.getInputStream()));
				buffw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Start thread");
			new ServiceThread(buffr,buffw,s).start();
		}
		
		
	}

}
