package com.ryanb3.planetengine.physics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.ryanb3.planetengine.body.Sphere;
import com.ryanb3.planetengine.math.MyVector;

public class PlanetManager implements Runnable {

	private ArrayList<Sphere> objects;
	private double screenCross;
	private double scale;

	public PlanetManager(ArrayList<Sphere> objects, Dimension screenDimensions) {
		this.objects = objects;
		screenCross = Math.sqrt(Math.pow(screenDimensions.width, 2) + Math.pow(screenDimensions.height, 2));
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).calcAcceleration(objects);
			}
			for (int i = 0; i < objects.size(); i++) {
				Sphere sphere1 = objects.get(i);
				if (sphere1.isMarkedForRemoval() || sphere1.isNewborn()) continue;
				sphere1.move(500);
				
				MyVector position1 = sphere1.getPosition();
				MyVector velocity1 = sphere1.getVelocity();
				double mass1 = sphere1.getMass();
				double radius1 = sphere1.getRadius();
				for (int j = 0; j < objects.size(); j++) {
					Sphere sphere2 = objects.get(j);
					if(sphere1 == sphere2) continue;
					
					MyVector position2 = sphere2.getPosition();
					MyVector velocity2 = sphere2.getVelocity();
					double mass2 = sphere2.getMass();
					double radius2 = sphere2.getRadius();
					
					double dx = position2.x - position1.x;
					double dy = position2.y - position1.y;
					double distanceSquared = dx*dx + dy*dy;
					double radii = radius1+radius2;
					if(distanceSquared < radii*radii) {
						sphere1.markForRemoval();
						sphere2.markForRemoval();
						
						double resultVelocityX = (mass1*velocity1.x + mass2*velocity2.x)/(mass1+mass2);
						double resultVelocityY = (mass1*velocity1.y + mass2*velocity2.y)/(mass1+mass2);
						MyVector resultVelocity = new MyVector(resultVelocityX, resultVelocityY, 0);
						
						double resultPositionX = (position1.x*mass1 + position2.x*mass2)/(mass1+mass2);
						double resultPositionY = (position1.y*mass1 + position2.y*mass2)/(mass1+mass2);
						MyVector resultPosition = new MyVector(resultPositionX, resultPositionY, 0);
						resultPosition.addTo(resultVelocity.scale(500));
						
						double resultRadius = Math.sqrt(radius1*radius1+radius2*radius2);
						
						double resultMass = mass1 + mass2;
						
						String resultName = sphere1.getName() + "-" + sphere2.getName();
						
						Random random = new Random();
						Color resultColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
						
						Sphere resultSphere = new Sphere(resultMass, resultPosition, resultVelocity, resultColor, (int) (resultRadius), resultName);
						resultSphere.markAsNewborn();
						objects.add(resultSphere);
						
						System.out.println("Collision");
						System.out.println(resultMass);
						
						break;
					}
				}
			}
			for (Iterator<Sphere> itr = objects.iterator(); itr.hasNext();) {
				Sphere next = itr.next();
				if(next.isMarkedForRemoval()) itr.remove();
			}
		}
	}

	public double getScale() {
		if (scale == 0) {
			MyVector center = getCenterOfMass();
		
			double maxDistance = 0;
			for (int i = 0; i < objects.size(); i++) {
				double distance = objects.get(i).getPosition().getDistance(center);
				
				if(distance > maxDistance) {
					maxDistance = distance;
				}
			}
			
			scale = maxDistance * 10 / screenCross;
		}
		
		return scale;
	}
	
	public MyVector getCenterOfMass() {
		MyVector center = new MyVector(0, 0, 0);
		double totalMass = 0;
		
		for(int i = 0; i < objects.size(); i++) {
			Sphere object = objects.get(i);
			double mass = object.getMass();
			
			center = center.addTo(object.getPosition().scale(mass));
			totalMass += mass;
		}
		
		return center.scale(1 / totalMass);
	}

}
