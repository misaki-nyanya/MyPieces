package events;

import zjs.smartevents.events.Event;

public class MeLoseEvent extends Event {
	private static final long serialVersionUID = 1L;
	public int playID;
	public MeLoseEvent(int playid){
		playID=playid;
	}
}
