package Display;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JFrame;

import Bodies.Sphere;

public class Frame extends JFrame {

	public Panel display;
	
	public Frame(Dimension screenDimensions, ArrayList<Sphere> objects) {
		this.setLayout(null);
		display = new Panel(objects, screenDimensions);
		display.setLayout(null);
		display.setBounds(0, 0, (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth()),
				(int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()));
		add(display);
	}
	
}
