package server;

import events.LoseEvent;
import events.ServerMassage;
import zjs.smartevents.SmartEvents;

public class Sender {
	public static void getPlayerOp(events.PlayerOp playerOp){
		ServerMassage s=new ServerMassage();
		s.op=playerOp.op;
		s.op_motion=playerOp.op_motion;
		s.playerid=playerOp.playerid;
		s.playerLeftShip=playerOp.playerLeftShip;
		SmartEvents.happen(s,false,playerOp.getFriend());
		System.out.println("server sent op");
	}
	public static void onePlayerLose(events.MeLoseEvent lose){
		LoseEvent s=new LoseEvent(lose.playID);
		SmartEvents.happen(s,false,lose.getFriend());
		System.out.println("server sent lose message");
	}
}
