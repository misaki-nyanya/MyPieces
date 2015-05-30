package server;

import java.io.IOException;

import zjs.smartevents.SmartEvents;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, IOException, InterruptedException {
		SmartEvents.bind(events.PlayerOp.class, Sender.class, "getPlayerOp");
		SmartEvents.bind(events.MeLoseEvent.class, Sender.class, "onePlayerLose");
		SmartEvents.acceptShareApply(10000);
		Thread.sleep(10000000);

	}

}
