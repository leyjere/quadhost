package Information;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class DataPipe {
	
	public static BlockingQueue<Data> queue = new PriorityBlockingQueue<Data>();

}
