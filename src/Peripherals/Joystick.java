package Peripherals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

import Information.Analog;
import Information.Data;
import Information.DataPipe;
import Information.Digital;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Controller;
import net.java.games.input.Component.Identifier;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.ControllerEvent;
import net.java.games.input.ControllerListener;

public class Joystick implements JoystickEventListener, Runnable{
	
    protected List<Controller> foundControllers = new ArrayList<Controller>();
    protected EventHandler ev;

	/* Method to be invoked by subclasses, type defined
	 * by inheriting class. Search through connected peripherals 
	 * and return the first matching type. */
	public void searchForControllers(Controller.Type type){

		List<Controller> controllers = Arrays.asList(ControllerEnvironment.getDefaultEnvironment().getControllers());

		for(Controller controller:controllers){

			if (controller.getType() == type){
				foundControllers.add(controller);
			}
		}

	}

	@Override
	public void pitch(int percent) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Pitch %s", percent));
		new Analog(Data.Type.Pitch, percent).add();
	}

	@Override
	public void roll(int percent) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Roll %s", percent));
		new Analog(Data.Type.Roll, percent).add();
	}

	@Override
	public void yaw(int percent) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Yaw %s", percent));
		new Analog(Data.Type.Yaw, percent).add();
	}

	@Override
	public void throttle(int percent) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Throttle %s", percent));
		new Analog(Data.Type.Throttle, percent).add();
	}

	@Override
	public void trigger(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Trigger %s", state));
		new Digital(Data.Type.Trigger, (state==100)).add();
		
	}

	@Override
	public void thumb(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Thumb %s", state));
		new Digital(Data.Type.Thumb, (state==100)).add();
	}

	@Override
	public void thumb2(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Thumb2 %s", state));
		new Digital(Data.Type.Trigger, (state==100)).add();
	}

	@Override
	public void top(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Top %s", state));
		new Digital(Data.Type.Top, (state==100)).add();
	}

	@Override
	public void top2(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Top2 %s", state));
		new Digital(Data.Type.Top2, (state==100)).add();
	}

	@Override
	public void pinkie(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Pinkie %s", state));
		new Digital(Data.Type.Pinkie, (state==100)).add();
	}

	@Override
	public void base(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Base %s", state));
		new Digital(Data.Type.Base, (state==100)).add();
	}

	@Override
	public void base2(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Base2 %s", state));
		new Digital(Data.Type.Base2, (state==100)).add();
	}

	@Override
	public void base3(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Base3 %s", state));
		new Digital(Data.Type.Base3, (state==100)).add();
	}

	@Override
	public void base4(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Base4 %s", state));
		new Digital(Data.Type.Base4, (state==100)).add();
	}

	@Override
	public void base5(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Base5 %s", state));
		new Digital(Data.Type.Base5, (state==100)).add();
	}

	@Override
	public void base6(int state) {
		// TODO Auto-generated method stub
		System.out.println(String.format("Base6 %s", state));
		new Digital(Data.Type.Base6, (state==100)).add();
	}

	@Override
	public void run() {
		searchForControllers(Controller.Type.STICK);
		if(foundControllers.isEmpty()){ System.out.println("NO JOYSTICK FOUND!!!"); System.exit(0);}
		ev = new EventHandler(this);
		ev.pollController(foundControllers.get(0));
	}

	

}
