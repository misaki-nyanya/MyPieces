package events;

import client.Coordinate;
import zjs.smartevents.events.Event;

public class ServerMassage extends Event{

	private static final long serialVersionUID = 1L;
	public Coordinate[] op;
	public int[] op_motion;
	public int playerid;
	public int playerLeftShip;

}
