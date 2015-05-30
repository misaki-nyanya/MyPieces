package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import zjs.smartevents.SmartEvents;

public class UI {
	public JFrame main=new JFrame("Player "+Judger.myID);
	public JPanel mainP=new JPanel();
	public JPanel battlefield=new JPanel();
	public JPanel op=new JPanel();
	public JLabel player1state=new JLabel();
	public JLabel player2state=new JLabel();
	public JButton move=new JButton("移动");
	public JButton attack=new JButton("攻击");
	public JButton drawback=new JButton("撤销");
	public JButton submit=new JButton("提交");
	public JButton[][] buttons=new JButton[5][5];
	public void init(){
		battlefield.setLayout(new GridLayout(5,5));
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				buttons[i][j]=new JButton();
				buttons[i][j].setPreferredSize(new Dimension(45,45));
				buttons[i][j].setEnabled(false);
				buttons[i][j].addActionListener(new ActionListener()
				{
					private int i,j;
					public ActionListener set(int i,int j){this.i=i;this.j=j;return this;}
					public void actionPerformed(ActionEvent arg0) 
					{
						
						Judger.getOp(i,j);						
					}
				}.set(i, j));
				battlefield.add(buttons[i][j]);
			}
		}
		for(int i=0;i<5;i++){
			buttons[Judger.ships[i].position.x][Judger.ships[i].position.y].setText("●");//my ships
		}
		battlefield.setPreferredSize(new Dimension(45*5,45*5));
		
		move.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Judger.setState(Judger.MOVE);
				int i=Judger.opAt;
					if((Judger.ships[i].position.x+1<5)&&(Judger.ships[i].position.y+1<5))
						buttons[Judger.ships[i].position.x+1][Judger.ships[i].position.y+1].setEnabled(true);
					if((Judger.ships[i].position.x+1<5))
						buttons[Judger.ships[i].position.x+1][Judger.ships[i].position.y].setEnabled(true);
					if((Judger.ships[i].position.y+1<5))
						buttons[Judger.ships[i].position.x][Judger.ships[i].position.y+1].setEnabled(true);
					if((Judger.ships[i].position.x-1>=0)&&(Judger.ships[i].position.y+1<5))
						buttons[Judger.ships[i].position.x-1][Judger.ships[i].position.y+1].setEnabled(true);
					if((Judger.ships[i].position.x+1<5)&&(Judger.ships[i].position.y-1>=0))
						buttons[Judger.ships[i].position.x+1][Judger.ships[i].position.y-1].setEnabled(true);
					if((Judger.ships[i].position.x-1>=0))
						buttons[Judger.ships[i].position.x-1][Judger.ships[i].position.y].setEnabled(true);
					if((Judger.ships[i].position.y-1>=0))
						buttons[Judger.ships[i].position.x][Judger.ships[i].position.y-1].setEnabled(true);
					if((Judger.ships[i].position.x-1>=0)&&(Judger.ships[i].position.y-1>=0))
						buttons[Judger.ships[i].position.x-1][Judger.ships[i].position.y-1].setEnabled(true);
				
			}
			
		});
		
		attack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Judger.setState(Judger.ATTACK);
				int i=Judger.opAt;
					if((Judger.ships[i].position.x+1<5)&&(Judger.ships[i].position.y+1<5))
						buttons[Judger.ships[i].position.x+1][Judger.ships[i].position.y+1].setEnabled(true);
					if((Judger.ships[i].position.x+1<5))
						buttons[Judger.ships[i].position.x+1][Judger.ships[i].position.y].setEnabled(true);
					if((Judger.ships[i].position.y+1<5))
						buttons[Judger.ships[i].position.x][Judger.ships[i].position.y+1].setEnabled(true);
					if((Judger.ships[i].position.x-1>=0)&&(Judger.ships[i].position.y+1<5))
						buttons[Judger.ships[i].position.x-1][Judger.ships[i].position.y+1].setEnabled(true);
					if((Judger.ships[i].position.x+1<5)&&(Judger.ships[i].position.y-1>=0))
						buttons[Judger.ships[i].position.x+1][Judger.ships[i].position.y-1].setEnabled(true);
					if((Judger.ships[i].position.x-1>=0))
						buttons[Judger.ships[i].position.x-1][Judger.ships[i].position.y].setEnabled(true);
					if((Judger.ships[i].position.y-1>=0))
						buttons[Judger.ships[i].position.x][Judger.ships[i].position.y-1].setEnabled(true);
					if((Judger.ships[i].position.x-1>=0)&&(Judger.ships[i].position.y-1>=0))
						buttons[Judger.ships[i].position.x-1][Judger.ships[i].position.y-1].setEnabled(true);
				
			}
		});
		
		drawback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Judger.drawbackop();
			}			
		});
		
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Judger.submit();
			}			
		});
		
		player1state.setText("player1 残舰:");
		player2state.setText("player2 残舰:");
		
		op.setLayout(new GridLayout(6,1));
		op.add(move);
		op.add(attack);
		op.add(drawback);
		op.add(submit);
		op.add(player1state);
		op.add(player2state);
		
		mainP.setLayout(new BorderLayout());
		mainP.add(battlefield,BorderLayout.CENTER);
		mainP.add(op,BorderLayout.WEST);
		mainP.setPreferredSize(new Dimension(60*6,60*5));
		
		main.add(mainP);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		main.pack();
		
		if(Judger.nowPlaying==Judger.myID){
			this.setAbleToOp(true, false, false);
		}else{
			this.setAbleToOp(false, false, false);
		}
	}
	public void opSet(){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				buttons[i][j].setEnabled(false);
			}
		}
	}
	public void setAbleToOp(boolean moveAndAttack,boolean draw,boolean send){
		this.move.setEnabled(moveAndAttack);
		this.attack.setEnabled(moveAndAttack);
		this.drawback.setEnabled(draw);
		this.submit.setEnabled(send);
	}
	public static void main(String[] args) throws IOException, NoSuchMethodException, InterruptedException {
		Judger.init();
		UI u=new UI();
		u.init();
		Judger.getUI(u);
		SmartEvents.shareWith("localhost",10000);
		SmartEvents.bind(events.ServerMassage.class, Judger.class, "oneOped");
		SmartEvents.bind(events.LoseEvent.class, Judger.class, "oneLose");
		
	}
}
