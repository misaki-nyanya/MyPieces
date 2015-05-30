package theBigHomework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class test {
//	private JFrame f = new JFrame();
//	private JScrollPane sp = new JScrollPane();
//	private JTable table = new JTable();
//	private DefaultTableModel dtm;
//	private JButton a = new JButton("Click me");
//	public void init(){
//		f.setLayout(new BorderLayout());
//		Vector<Vector<String>> vdata = new Vector<Vector<String>>();
//		Vector<String> item = new Vector<String>();
//		item.add(" ");
//		item.add("item");
//		item.add("aaa");
//		Vector<String> item2 = new Vector<String>();
//		item2.add("sdfaf ");
//		item2.add(null);
//		item2.add("agwrfaa");
//		Vector<String> item3 = new Vector<String>();
//		item3.add(" ");
//		item3.add("item");
//		item3.add("aaa");
//		vdata.add(item);
//		vdata.add(item2);
//		vdata.add(item3);
//		
//		Vector<String> vcolumn = new Vector<String>();
//		vcolumn.add("a");
//		vcolumn.add("b");
//		vcolumn.add("c");
//		dtm = new DefaultTableModel(vdata,vcolumn);
//		table.setModel(dtm);
//		a.addActionListener(new ActionListener(){
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				for(int row = 0;row<table.getRowCount();row++){
//					for(int column = 0;column<table.getColumnCount();column++){
//						System.out.println(dtm.getValueAt(row, column));
//						System.out.println("------");
//						System.out.println(Integer.valueOf((String) dtm.getValueAt(row, column)));
//						System.out.println("------");
//					}
//				}
//				
//			}
//			
//		});
//		sp.setViewportView(table);
//		f.add(sp,BorderLayout.CENTER);
//		
//		f.add(a,BorderLayout.SOUTH);
//		f.pack();
//		f.setPreferredSize(new Dimension(500,500));
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setVisible(true);
		
		
//	}
	private static String a= "aaaa";
	static{
		test.a = "bbbb";
		System.out.println("a is "+test.a);
	}
	public int b;
	public test(int i){
		System.out.println("a is "+test.a);
		b = i;
	}
	
	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable 
	{
		
//		new test().init();
		System.out.println(test.a);
//		test.a[0] = "3333";
		System.out.println();
		new test(1);
	}

}
