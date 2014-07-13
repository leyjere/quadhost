package Peripherals;

import net.java.games.input.Component;
import Information.Analog;
import Information.Data;

/* Interface provides callback function declarations */
public interface JoystickEventListener {

	public void pitch(int percent);
	
	public void roll(int percent);
	
	public void yaw(int percent);
	
	public void throttle(int percent);
	
	public void trigger(int state);
	
	public void thumb(int state);
	
	public void thumb2(int state);
	
	public void top(int state);
	
	public void top2(int state);
	
	public void pinkie(int state);
	
	public void base(int state);
	
	public void base2(int state);
	
	public void base3(int state);
	
	public void base4(int state);
	
	public void base5(int state);
	
	public void base6(int state);
	
}
