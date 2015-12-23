package com.ryanb3.planetengine.body;

import java.awt.Color;
import java.util.ArrayList;
import java.util.UUID;

import com.ryanb3.planetengine.math.MyVector;

public class Sphere {

	private final static double G = 6.67e-11;
	
	private double radius;
	private double mass;
	private MyVector position;
	private MyVector velocity;
	private MyVector acceleration;
	private Color color;
	private UUID id;
	private String name;
	private boolean markedForRemoval;
	private boolean newborn;
	
	public Sphere(double mass, MyVector position, MyVector velocity, Color color, double radius, String name) { //Radius in pixels
		this.name = name;
		this.radius = radius;
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		this.color = color;
		id = UUID.randomUUID();
		markedForRemoval = false;
	}
	
	public void calcAcceleration(ArrayList<Sphere> otherBodies) {
		MyVector totalAcc = new MyVector(0, 0, 0);
		for(int i = 0; i < otherBodies.size(); i++) {
			Sphere other = otherBodies.get(i);
			//if(id != otherBodies.get(i).id && !name.equals("earth")) {
			if(!id.equals(other.id)) {
				MyVector displacement = other.getPosition().subtract(position);
				totalAcc = totalAcc.addTo(displacement.norm().scale(G * other.getMass()/Math.pow(displacement.getMag(), 2)));
			}
		}
		acceleration = totalAcc;
		
		if(newborn) newborn = false;
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
	
	public void markForRemoval() {
		markedForRemoval = true;
	}
	
	public void markAsNewborn() {
		newborn = true;
	}
	
	public boolean isNewborn() {
		return newborn;
	}
	
	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}
}
