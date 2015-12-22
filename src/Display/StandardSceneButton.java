package Display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Bodies.Sphere;
import Math.MyVector;
import Physics.PlanetManager;

public class StandardSceneButton extends JButton {

	ArrayList<Sphere> objects;
	PlanetManager physics;
	
	public StandardSceneButton(String message, ArrayList<Sphere> objects, PlanetManager phyics) {
		super(message);
		this.physics = phyics;
		this.objects = objects;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Type in 1-5 for a scene, and 6 if you want a random one");
				if(name.equals("1")) {
					addSceneOne();
				} else if(name.equals("2")) {
					addSceneTwo();
				} else if(name.equals("3")) {
					addSceneThree();
				} else if(name.equals("4")) {
					addSceneFour();
				} else if(name.equals("5")) {
					addSceneFive();
				} else if(name.equals("6")) {
					addSceneRandom();
				}
				//phyics.reCalcScale();
			}
		});

	}
	
	public void addSceneOne() { //Standard Orbit
		objects.add(new Sphere(3.1e22, new MyVector(0, 3.84e8, 0), new MyVector(1014.9, 0, 0), Color.BLUE, 20, "moon"));
	}
	
	public void addSceneTwo() { //Standard with Crash
		objects.add(new Sphere(3.1e22, new MyVector(0, 3.84e8, 0), new MyVector(1014.9, 0, 0), Color.BLUE, 20, "moon"));
		objects.add(new Sphere(3.1e22, new MyVector(0, 4.84e8, 0), new MyVector(1014.9, 0, 0), Color.BLUE, 20, "moon1"));
	}
	
	public void addSceneThree() {
		objects.add(new Sphere(3.1e22, new MyVector(0, 3.84e8, 0), new MyVector(1014.9, 0, 0), Color.BLUE, 20, "moon"));

	}
	
	public void addSceneFour() {
		
	}
	
	public void addSceneFive() {
		
	}
	
	public void addSceneRandom() {
		
	}
	
}
