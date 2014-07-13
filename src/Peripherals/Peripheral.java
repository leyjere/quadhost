package Peripherals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;

/* Defines the generic behaviour and handling of any peripheral device */
public abstract class Peripheral {
	
    protected List<Controller> foundControllers = new ArrayList<Controller>();

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

}
