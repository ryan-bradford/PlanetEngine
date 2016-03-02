package com.ryanb3.planetengine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.ryanb3.planetengine.body.Sphere;
import com.ryanb3.planetengine.display.Panel;
import com.ryanb3.planetengine.math.MyVector;

public class PlanetEngine {

	private Panel display;
	
	public PlanetEngine() {
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		
		display = new Panel(screenDimensions);
		
		display.addNewObject(new Sphere(5.93e24, new MyVector(0, 0, 0), new MyVector(0, 30000, 0), Color.GREEN, 20, "earth"));
		display.addNewObject(new Sphere(3.1e22, new MyVector(0, 1.84e10, 0), new MyVector(3014.9, 30000, 0), Color.BLUE, 10, "moon"));
		display.addNewObject(new Sphere(1.989e30, new MyVector(1.49604618e11, 0, 0), new MyVector(0, 0, 0), Color.YELLOW, 50, "sun"));
		//display.addNewObject(new Sphere(1.989e33, new MyVector(0, 9.49604618e11, 0), new MyVector(0, 0, 0), Color.GRAY, 50, "earth"));
		//display.addNewObject(new Sphere(0, new MyVector(0, 3.84e8, 0), new MyVector(1014.9, 0, 0), Color.BLUE, 0, "moon"));
		
		JFrame frame = new JFrame();
		frame.pack();
		
		frame.setBounds(0, 0, screenDimensions.width, screenDimensions.height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.add(display);
		
		display.getPlanetManager().start();
	}
	
	public static void main(String[] args) {
		new PlanetEngine();
	}
	
}