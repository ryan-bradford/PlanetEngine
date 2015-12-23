package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

import Bodies.Sphere;
import Display.Frame;
import Math.MyVector;

public class Main {

	public static double G = 6.67e-11;
	ArrayList<Sphere> objects;
	Frame display;
	int counter = 0;
	
	public Main() {
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		objects = new ArrayList<Sphere>();
		addNewObject(new Sphere(5.93e24, new MyVector(0, 0, 0), new MyVector(0, 30000, 0), Color.GREEN, 10, "earth"));
		addNewObject(new Sphere(3.1e22, new MyVector(0, 1.84e10, 0), new MyVector(1014.9, 30000, 0), Color.BLUE, 5, "moon"));
		addNewObject(new Sphere(1.989e30, new MyVector(1.49604618e11, 0, 0), new MyVector(0, 0, 0), Color.YELLOW, 50, "sun"));
		//addNewObject(new Sphere(1.989e33, new MyVector(0, 9.49604618e11, 0), new MyVector(0, 0, 0), Color.GRAY, 50, "earth"));
		//addNewObject(new Sphere(0, new MyVector(0, 3.84e8, 0), new MyVector(1014.9, 0, 0), Color.BLUE, 0, "moon"));
		display = new Frame(screenDimensions, objects);
		display.pack();
		display.setBounds(0, 0, (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth()),
				(int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight()));
		display.setVisible(true);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setLayout(null);
		display.display.manager.start();
		new Timer(50, taskPerformer).start();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void addNewObject(Sphere toAdd) {
		objects.add(toAdd);
	}

	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			if(counter != 0) {
			display.display.repaint();
			} else {
				counter++;
			}
		}
	};
	
}