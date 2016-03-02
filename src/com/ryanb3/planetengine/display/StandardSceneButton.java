package com.ryanb3.planetengine.display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.ryanb3.planetengine.body.Sphere;
import com.ryanb3.planetengine.math.MyVector;
import com.ryanb3.planetengine.physics.PlanetManager;

public class StandardSceneButton extends JButton {

	private ArrayList<Sphere> objects;

	public StandardSceneButton(String message, ArrayList<Sphere> objects, PlanetManager phyics) {
		super(message);
		this.objects = objects;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int scene = Integer.parseInt(
							JOptionPane.showInputDialog("Type in 1-5 for a scene, and 6 if you want a random one"));
					switch (scene) {
					case 1:
						addSceneOne();
						break;
					case 2:
						addSceneTwo();
						break;
					case 3:
						addSceneThree();
						break;
					case 4:
						addSceneFour();
						break;
					case 5:
						addSceneFive();
						break;
					default:
						addSceneRandom();
						break;
					}
				} catch (NumberFormatException ex) {

				}
			}
		});

	}

	public void addSceneOne() { // Standard Orbit
		objects.add(new Sphere(3.1e22, new MyVector(0, 3.84e8, 0), new MyVector(1014.9, 0, 0), Color.BLUE, 20, "moon"));
	}

	public void addSceneTwo() { // Standard with Crash
		objects.add(new Sphere(5.93e24, new MyVector(0, 0, 0), new MyVector(0, 30000, 0), Color.GREEN, 20, "earth"));
		objects.add(new Sphere(3.1e22, new MyVector(0, 1.84e10, 0), new MyVector(3014.9, 30000, 0), Color.BLUE, 10, "moon"));
		objects.add(new Sphere(1.989e30, new MyVector(1.49604618e11, 0, 0), new MyVector(0, 0, 0), Color.YELLOW, 50, "sun"));
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
