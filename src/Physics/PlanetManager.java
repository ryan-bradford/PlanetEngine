package Physics;

import java.awt.Dimension;
import java.util.ArrayList;

import Bodies.Sphere;
import Math.MyVector;

public class PlanetManager extends Thread {

	ArrayList<Sphere> objects;
	MyVector center;
	double screenCross;
	public double scale;
	int counter = 0;

	public PlanetManager(ArrayList<Sphere> objects, Dimension screenDimensions) {
		this.objects = objects;
		center = new MyVector(0,0,0);
		screenCross = Math.sqrt(Math.pow(screenDimensions.width, 2) + Math.pow(screenDimensions.height, 2));
	}

	public void run() {
		while (true) {
			try {
				sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).calcAcceleration(objects);
			}
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).move(500);
			}
		}
	}
	
	public void reCalcScale() {
		double max = 0;
		for (int i = 0; i < objects.size(); i++) {
			double distance = objects.get(i).possition.getDistance(center);
			if (distance > max) {
				max = distance;
			}
		}
		scale = max * 10 / (screenCross);
	}

	public double getScale() {
		if (counter == 0) {
			double max = 0;
			for (int i = 0; i < objects.size(); i++) {
				double distance = objects.get(i).possition.getDistance(center);
				if (distance > max) {
					max = distance;
				}
			}
			scale = max * 10 / (screenCross);
			counter++;
		}
		return scale;
	}
	
	public MyVector getCenterOfMass() {
		MyVector center = new MyVector(0, 0, 0);
		double totalMass = 0;
		for(int i = 0; i < objects.size(); i++) {
			center = center.addTo(objects.get(i).possition.scale(objects.get(i).mass));
			totalMass += objects.get(i).mass;
		}
		return center.scale(1 / totalMass);
	}

}
