package Information;

public class Analog extends Data implements Comparable{
	
	private int val;
	private Type type;
	
	/* Create an instance of analog data; 
	 * type indicates yaw, pitch, roll, throttle */
	public  Analog(Type type, int val){
		this.type = type;
		this.val = val;
	}
	
	/* Add this element to the main pipeline */
	public void add(){
		try {
			DataPipe.queue.put(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return String.format("Type %s Value %s", type, val);
	}

	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

}
