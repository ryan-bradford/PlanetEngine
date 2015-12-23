package Bodies;

import java.awt.Color;
import java.util.ArrayList;

import Main.Main;
import Math.MyVector;
import Physics.PlanetManager;

public class Sphere {

	private double radius;
	private double mass;
	private MyVector position;
	private MyVector velocity;
	private MyVector acceleration;
	private Color color;
	private double id = Math.random();
	private String name;
	
	public Sphere(double mass, MyVector possition, MyVector velocity, Color color, double radius, String name) { //Radius in pixels
		this.name = name;
		this.radius = radius;
		this.mass = mass;
		this.position = possition;
		this.velocity = velocity;
		this.color = color;
	}
	
	public void calcAcceleration(ArrayList<Sphere> otherBodies) {
		MyVector totalAcc = new MyVector(0, 0, 0);
		for(int i = 0; i < otherBodies.size(); i++) {
			//if(id != otherBodies.get(i).id && !name.equals("earth")) {
			if(id != otherBodies.get(i).id) {
				MyVector displacement = otherBodies.get(i).position.subtract(position);
				totalAcc = totalAcc.addTo(displacement.norm().scale(Main.G * otherBodies.get(i).mass/Math.pow(displacement.getMag(), 2)));
			}
		}
		acceleration = totalAcc;
	}
	
	public void move(double time) {
	    velocity = velocity.addTo(acceleration.scale(time));
	    position = position.addTo(velocity.scale(time).addTo(acceleration.scale(.5 * Math.pow(time, 2))));
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getMass() {
		return mass;
	}
	
	public MyVector getPosition() {
		return position;
	}
	
	public MyVector getVelocity() {
		return velocity;
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
}
