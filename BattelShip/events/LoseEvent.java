package events;

import zjs.smartevents.events.Event;

public class LoseEvent extends Event{
	private static final long serialVersionUID = 1L;
	public int playID;
	public LoseEvent(int playid){
		playID=playid;
	}
}
