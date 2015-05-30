package clientUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LoginUI {
	private JFrame loginFrame = new JFrame("Class Manager System");
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
	private JPanel adminPanel= new  JPanel();
	private JPanel stuPanel= new  JPanel();
	private JPanel teaPanel= new  JPanel();
	private JLabel adminName = new JLabel("Admin");
	private JLabel stuNoLabel = new JLabel("Student Number:");
	private JLabel teaNoLabel = new JLabel("Teacher Number:");
	private JLabel stuPassLabel = new JLabel("Student Password:");
	private JLabel teaPassLabel = new JLabel("Teacher Number:");
	private JLabel adminPassLabel = new JLabel("Adminster Password:");
	private JLabel adminLabel = new JLabel("Adminster:\r\n");
	private JTextField stuNo = new JTextField(); 
	private JTextField teaNo = new JTextField(); 
	private JPasswordField adminPassword = new JPasswordField();
	private JPasswordField stuPassword = new JPasswordField();
	private JPasswordField teaPassword = new JPasswordField();
	private JButton stuLogin = new JButton("Login");
	private JButton adminLogin = new JButton("Login");
	private JButton teaLogin = new JButton("Login");
	
	private String accountNo = "";
	private String accountPass = "";
	private boolean LoginFrameVisibility = true;
	private boolean accountReady = false;
	
	public void init(){
		stuLogin.addMouseListener(new LoginStu());
		teaLogin.addMouseListener(new LoginTea());
		adminLogin.addMouseListener(new LoginAdmin());
		tabbedPane.addTab("Administer Login", null, null);
		tabbedPane.addTab("Student Login", null, null);
		tabbedPane.addTab("Teacher Login", null, null);
		loginFrame.add(tabbedPane,BorderLayout.CENTER);
		tabbedPane.addChangeListener(
				new ChangeListener(){
					@Override
					public void stateChanged(ChangeEvent e) {
						tabbedPane.getSelectedComponent();
					}
				}	
		
		);

		adminPanel.setLayout(new BoxLayout(adminPanel,BoxLayout.Y_AXIS));
		adminPanel.add(adminLabel);
		adminPanel.add(adminName);
		adminPanel.add(Box.createVerticalStrut(50));
		adminPanel.add(adminPassLabel);
		adminPanel.add(Box.createVerticalStrut(50));
		adminPanel.add(adminPassword);
		adminPanel.add(adminLogin);
		
		stuPanel.setLayout(new BoxLayout(stuPanel,BoxLayout.Y_AXIS));
		stuPanel.add(stuNoLabel);
		stuPanel.add(Box.createVerticalStrut(50));
		stuPanel.add(stuNo);
		stuPanel.add(stuPassLabel);
		stuPanel.add(Box.createVerticalStrut(50));
		stuPanel.add(stuPassword);
		stuPanel.add(stuLogin);
		
		teaPanel.setLayout(new BoxLayout(teaPanel,BoxLayout.Y_AXIS));
		teaPanel.add(teaNoLabel);
		teaPanel.add(Box.createVerticalStrut(50));
		teaPanel.add(teaNo);
		teaPanel.add(teaPassLabel);
		teaPanel.add(Box.createVerticalStrut(50));
		teaPanel.add(teaPassword);
		teaPanel.add(teaLogin);
		
		tabbedPane.setComponentAt(0, adminPanel);
		tabbedPane.setComponentAt(1, stuPanel);
		tabbedPane.setComponentAt(2, teaPanel);
		
		
		loginFrame.setPreferredSize(new Dimension(500,300));
		loginFrame.pack();
		loginFrame.addWindowListener(new WindowListener(){
			@Override
			public void windowActivated(WindowEvent arg0) {
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				Client.close();
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
			}
			@Override
			public void windowOpened(WindowEvent e) {
			}			
		});
//		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setVisible(true);
	}
	
	public String getAccountNo(){
		if(!this.accountNo.equals("")){
			return accountNo;
		}else{
			System.out.println("empty account number!");
			return null;
		}
	}
	public String getAccountPass(){
		if(!this.accountPass.equals("")){
			return accountPass;
		}else{
			System.out.println("empty account Password!");
			return null;
		}
	}
	public void setLoginFrameVisibility(boolean visible){
		this.LoginFrameVisibility = visible;
		this.loginFrame.setVisible(visible);		
	}
	public boolean getVisibility(){
		return this.LoginFrameVisibility;
	}
	public boolean getAccountStatus(){
		return accountReady;
	}
	
	
	class LoginAdmin implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// get account
			char[] temp = adminPassword.getPassword();
			StringBuilder password = new StringBuilder();			
			password.append(temp);
			accountNo = "Admin";
			accountPass = password.toString();
			//judge login
			boolean login = false;
			try {
				login = Client.checkAdminLogin(accountPass);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			if(login){
				setLoginFrameVisibility(false);
			}else{
				JOptionPane.showMessageDialog(null, "Wrong Password or Wrong Account Name!", "Login Failed", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
	class LoginStu implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			//get account
			accountNo = stuNo.getText();
			char[] temp = stuPassword.getPassword();
			StringBuilder password = new StringBuilder();			
			password.append(temp);
			accountPass = password.toString();
			accountReady = true;
			//judge login
			boolean login = false;
			try {
				login = Client.checkStuLogin(accountNo,accountPass);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			if(login){
				setLoginFrameVisibility(false);
			}else{
				JOptionPane.showMessageDialog(null, "Wrong Password or Wrong Account Name!", "Login Failed", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
	class LoginTea implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// get account
			accountNo = teaNo.getText();
			char[] temp = teaPassword.getPassword();
			StringBuilder password = new StringBuilder();			
			password.append(temp);
			accountPass = password.toString();
			accountReady = true;
			//judge login
			boolean login = false;
			try {
				login = Client.checkTeaLogin(accountNo,accountPass);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Cannot connect to server!", "Initialization Failed", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			if(login){
				setLoginFrameVisibility(false);
			}else{
				JOptionPane.showMessageDialog(null, "Wrong Password or Wrong Account Name!", "Login Failed", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LoginUI().init();
	}

}
