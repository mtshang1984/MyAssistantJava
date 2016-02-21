package MouseKeyboard;

import java.awt.AWTException;
import java.awt.Robot;

public class MouseKeyboard {

	public MouseKeyboard() {
		// TODO Auto-generated constructor stub
	}
	public void moveMouse(int xCoordinate, int yCoordinate){

		try {
			Robot robot =new Robot();
			robot.mouseMove(xCoordinate, yCoordinate);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
