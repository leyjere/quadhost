package Peripherals;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import Information.Analog;
import Information.Data;

/* Class which polls the state of the controller and calls the 
 * appropriate callback upon a change occurring. 
 */
public class EventHandler {
	
	/* Collections of past observed states */
	private EnumMap<Data.Type, Integer> prevState;
	private JoystickEventListener js;
	
	public EventHandler(Controller controller, JoystickEventListener event){
		prevState = new EnumMap<Data.Type,Integer>(Data.Type.class);
		setupMap();
		js = event;
		pollController(controller);
	}
	
	/* Method to acquire data from Joystick */
	public void pollController(Controller controller){

		while(true){
			
			controller.poll();

			List<Component> components = Arrays.asList(controller.getComponents());

			/* Must iterate through components returned from poll */
			for(Component component:components){

				Identifier identifier = component.getIdentifier();
				int value = getAxisValueInPercentage(component.getPollData());
				
				if(component.isAnalog()){

					if(identifier == Component.Identifier.Axis.X && prevState.get(Data.Type.Roll) != value){
						js.roll(value);
						prevState.put(Data.Type.Roll, value);
					}else if(identifier == Component.Identifier.Axis.Y && prevState.get(Data.Type.Pitch) != value){
						js.pitch(value);
						prevState.put(Data.Type.Pitch, value);
					}else if(identifier == Component.Identifier.Axis.RZ && prevState.get(Data.Type.Yaw) != value){
						js.yaw(value);
						prevState.put(Data.Type.Yaw, value);
					}else if(identifier == Component.Identifier.Axis.Z && prevState.get(Data.Type.Throttle) != value){
						js.throttle(Math.abs(value-100));
						prevState.put(Data.Type.Throttle, Math.abs(value-100));
					}else if(identifier == Component.Identifier.Axis.POV && prevState.get(Data.Type.POV) != value){
						
					}

				}else{
					if(identifier == Component.Identifier.Button.TRIGGER && prevState.get(Data.Type.Trigger) != value){
						js.trigger(value);
						prevState.put(Data.Type.Trigger, value);
					}else if(identifier == Component.Identifier.Button.THUMB && prevState.get(Data.Type.Thumb) != value){
						js.thumb(value);
						prevState.put(Data.Type.Thumb, value);
					}else if(identifier == Component.Identifier.Button.THUMB2 && prevState.get(Data.Type.Thumb2) != value){
						js.thumb2(value);
						prevState.put(Data.Type.Thumb2, value);
					}else if(identifier == Component.Identifier.Button.TOP && prevState.get(Data.Type.Top) != value){
						js.top(value);
						prevState.put(Data.Type.Top, value);
					}else if(identifier == Component.Identifier.Button.TOP2 && prevState.get(Data.Type.Top2) != value){
						js.top2(value);
						prevState.put(Data.Type.Top2, value);
					}else if(identifier == Component.Identifier.Button.PINKIE && prevState.get(Data.Type.Pinkie) != value){
						js.pinkie(value);
						prevState.put(Data.Type.Pinkie, value);
					}else if(identifier == Component.Identifier.Button.BASE && prevState.get(Data.Type.Base) != value){
						js.base(value);
						prevState.put(Data.Type.Base, value);
					}else if(identifier == Component.Identifier.Button.BASE2 && prevState.get(Data.Type.Base2) != value){
						js.base2(value);
						prevState.put(Data.Type.Base2, value);
					}else if(identifier == Component.Identifier.Button.BASE3 && prevState.get(Data.Type.Base3) != value){
						js.base3(value);
						prevState.put(Data.Type.Base3, value);
					}else if(identifier == Component.Identifier.Button.BASE4 && prevState.get(Data.Type.Base4) != value){
						js.base4(value);
						prevState.put(Data.Type.Base4, value);
					}else if(identifier == Component.Identifier.Button.BASE5 && prevState.get(Data.Type.Base5) != value){
						js.base5(value);
						prevState.put(Data.Type.Base5, value);
					}else if(identifier == Component.Identifier.Button.BASE6 && prevState.get(Data.Type.Base6) != value){
						js.base6(value);
						prevState.put(Data.Type.Base6, value);
					}
				}
			}
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Helper method */
	public int getAxisValueInPercentage(float axisValue){
		return (int)(((2 - (1 - axisValue)) * 100) / 2);
	}
	
	/* Helper method */
	public void setupMap(){
		for (Data.Type type:Data.Type.values()){
			prevState.put(type, 0);
		}
	}

}
