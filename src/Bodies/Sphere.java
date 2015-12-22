package Bodies;

import java.awt.Color;
import java.util.ArrayList;

import Main.Main;
import Math.MyVector;
import Physics.PlanetManager;

public class Sphere {

	public int radius;
	public double mass;
	public MyVector possition;
	MyVector velocity;
	MyVector acceleration;
	public Color color;
	double id = Math.random();
	String name;
	public Sphere(double mass, MyVector possition, MyVector velocity, Color color, int radius, String name) { //Radius in pixels
		this.name = name;
		this.radius = radius;
		this.mass = mass;
		this.possition = possition;
		this.velocity = velocity;
		this.color = color;
	}
	
	public void calcAcceleration(ArrayList<Sphere> otherBodies) {
		MyVector totalAcc = new MyVector(0, 0, 0);
		for(int i = 0; i < otherBodies.size(); i++) {
			//if(id != otherBodies.get(i).id && !name.equals("earth")) {
			if(id != otherBodies.get(i).id) {
				MyVector displacement = otherBodies.get(i).possition.subtract(possition);
				totalAcc = totalAcc.addTo(displacement.norm().scale(Main.G * otherBodies.get(i).mass/Math.pow(displacement.getMag(), 2)));
			}
		}
		acceleration = totalAcc;
	}
	
	public void move(double time) {
	    velocity = velocity.addTo(acceleration.scale(time));
	    possition = possition.addTo(velocity.scale(time).addTo(acceleration.scale(.5 * Math.pow(time, 2))));
	}
	
}
