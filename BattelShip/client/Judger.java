package client;

import javax.swing.JOptionPane;

import zjs.smartevents.SmartEvents;
import events.MeLoseEvent;
import events.PlayerOp;
/**
 * 
 * @author Rillke
 * @使用方法 设置players总人数 设置myID 设置每个Battleship位置
 */
public class Judger {
	public static final int ATTACK=0;
	public static final int MOVE=1;
	public static final int NULL = 2;	
	//state data
	public static int myID=1;
	public static int nowPlaying=0;
	public static int players=2;//3 players
	public static int op_state=-1;
	//game data
	public static Battleship[] ships=new Battleship[5];
	public static Coordinate[] op=new Coordinate[5];
	public static int[] op_motion=new int[5];
	public static Coordinate op_temp;
	public static int op_motion_temp;
	public static int opAt=0;
	public static int shipLeft=5;
	
	public static UI u;
	public static boolean isLose=false;
	
	public static void init(){
		
//		players=num_players;
		ships[0]=new Battleship(0,0);
		ships[1]=new Battleship(1,1);
		ships[2]=new Battleship(2,3);
		ships[3]=new Battleship(4,4);
		ships[4]=new Battleship(3,4);		
	}
	public static void getUI(UI ui){
		u=ui;
	}
	

	public static void getOp(int i, int j) {
		//get player operation from button
		if(nowPlaying==myID){
			if((op_state==Judger.ATTACK)&&(ships[opAt].state!=Battleship.DEAD)){
				for(int m=0;m<5;m++){
					if((ships[m].position.x==i)&&(ships[m].position.y==j)){
						JOptionPane.showMessageDialog(null, u,"请勿进攻自己的舰队",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				for(int m=0;m<opAt;m++){
					if((op[m].x==i)&&(op[m].y==j)){
						JOptionPane.showMessageDialog(null, u,"请勿在自己舰队的行进路线上移动或攻击",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				u.buttons[i][j].setText("X");
				u.opSet();
				op[opAt]=new Coordinate(i,j);
				op_motion[opAt]=Judger.ATTACK;
				
				//backup
				op_temp=op[opAt];
				u.setAbleToOp(true,true,false);
				opAt++;
				if(opAt==5){
					u.setAbleToOp(false,true,true);
				}
			}else if((op_state==Judger.MOVE)&&(ships[opAt].state!=Battleship.DEAD)){
				for(int m=0;m<5;m++){
					if((ships[m].position.x==i)&&(ships[m].position.y==j)){
						JOptionPane.showMessageDialog(null, u,"请勿和自己的舰队相撞",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				for(int m=0;m<opAt;m++){
					if((op[m].x==i)&&(op[m].y==j)){
						JOptionPane.showMessageDialog(null, u,"请勿在自己舰队的行进路线上移动或攻击",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				u.buttons[i][j].setText("O");
				u.opSet();
				op[opAt]=new Coordinate(i,j);
				op_motion[opAt]=Judger.MOVE;
				
				//backup
				op_temp=op[opAt];
				
				opAt++;
				u.setAbleToOp(true,true,false);
				if(opAt==5){
					u.setAbleToOp(false,true,true);
				}
			}else if(ships[opAt].state==Battleship.DEAD){
				System.out.println("!!operation dead ship");
				op_motion[opAt]=Judger.NULL;
				u.opSet();
				opAt++;
				if(opAt==5){
					u.setAbleToOp(false,true,true);
				}
			}
		}
	}
	public static void drawbackop(){
		if(opAt==5){
			u.setAbleToOp(true, true,false);
		}
		if(opAt!=0){
			opAt--;
			if(ships[opAt].state!=Battleship.DEAD)
				u.buttons[op[opAt].x][op[opAt].y].setText("");
		}		
	}
	public static void submit(){
		//send
		PlayerOp myOp=new PlayerOp();
		myOp.op=op;
		myOp.op_motion=op_motion;
		myOp.playerid=myID;
		myOp.playerLeftShip=shipLeft;
		SmartEvents.happen(myOp, SmartEvents.NETWORK_ONLY);
		System.out.println("send my op");
		for(int i=0;i<5;i++){
			if(op_motion[i]==Judger.MOVE){
				u.buttons[ships[i].position.x][ships[i].position.y].setText("");
				u.buttons[op[i].x][op[i].y].setText("●");
				ships[i].position=op[i];
			}else if(op_motion[i]==Judger.ATTACK){
				u.buttons[op[i].x][op[i].y].setText("");
			}
		}
		opAt=0;
		u.setAbleToOp(false, false,false);//until get your turn
		Judger.nowPlaying++;
		if(Judger.nowPlaying==Judger.players){
			Judger.nowPlaying=0;
		}
		System.out.println("Player "+myID+" sent ");
		System.out.println("Now player "+nowPlaying+" is to play");
	}
	public static void setState(int stat){
		//set in ATTCK or MOVE
		op_state=stat;
	}
	public static void oneOped(events.ServerMassage op){
		//get others op from network
		System.out.println("get op");
		for(int i=0;i<5;i++){
			if(op.op_motion[i]==Judger.ATTACK){
				for(int j=0;j<5;j++){
					if((ships[j].position.x==op.op[i].x)&&(ships[j].position.y==op.op[i].y)){
						ships[j].hitpoints--;
					}
					
				}
			}
		}
		for(int j=0;j<5;j++){
			if(ships[j].hitpoints==0){
				u.buttons[ships[j].position.x][ships[j].position.y].setText("D");
				ships[j].state=Battleship.DEAD;
				shipLeft--;
				System.out.println("!!ship"+j+" is set to dead");
			}
		}
		u.player1state.setText("Player "+op.playerid+" 残舰 "+op.playerLeftShip);
		Judger.nowPlaying++;
		if(Judger.nowPlaying==Judger.players){
			Judger.nowPlaying=0;
		}
		if(nowPlaying==myID){
			u.setAbleToOp(true, false, false);
		}
		boolean lose=true;
		for(int m=0;m<5;m++){
			if(ships[m].state!=Battleship.DEAD){
				lose=false;
			}
		}
		if(lose){
			isLose=true;
			JOptionPane.showMessageDialog(null, u,"您输了",JOptionPane.INFORMATION_MESSAGE);
			u.setAbleToOp(false, false, false);
			SmartEvents.happen(new MeLoseEvent(myID), SmartEvents.NETWORK_ONLY);
		}
		
		
		System.out.println("Player "+myID+" get ");
		System.out.println("Now player "+nowPlaying+" is to play");
		for(int i=0;i<5;i++){
			System.out.println("ship"+i+" hitpoints="+ships[i].hitpoints+" at ("+ships[i].position.x+","+ships[i].position.y+")");
		}
	}
	public static void oneLose(events.LoseEvent lose){
		players--;
		if(players==1&&(isLose==false)){
			JOptionPane.showMessageDialog(null, u,"您赢了",JOptionPane.INFORMATION_MESSAGE);
		}
		if((myID-nowPlaying)<0){
			myID=myID-nowPlaying+players;
		}else{
			myID=myID-nowPlaying;
		}
	}
	public static void main(String[] args) {

	}

}
