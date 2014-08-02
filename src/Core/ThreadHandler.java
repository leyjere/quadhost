package Core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import Information.DataPipe;
import Networking.Server;
import Peripherals.Joystick;
/*
 * Class which orchestrates the program. Runnables are 
 * delegated by the threadpool.
 */
public class ThreadHandler {
	
	int  core = 5;
	int  max = 10;
	long keepAliveTime = 5000;

	private ExecutorService threadPoolExecutor;
	private Server ss;
	private DataPipe dp;
	private Joystick js;
	
	/* Entry point of execution path */
	public static void main(String args[]){
		new ThreadHandler();
	}
	
	/* Main execution */
	public ThreadHandler(){
		
		js = new Joystick();
		dp = new DataPipe();
		
		try {
			ss = new Server(5900);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("SERVER FAILURE");
		}
		threadPoolExecutor = new ThreadPoolExecutor(core, max, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		/* Initiate subcomponents of program */
		threadPoolExecutor.execute(js);
		threadPoolExecutor.execute(dp);
		threadPoolExecutor.execute(ss);
	}

}
