package com.ryanb3.planetengine.clickControls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.ryanb3.planetengine.body.Sphere;
import com.ryanb3.planetengine.display.Panel;
import com.ryanb3.planetengine.math.MyVector;
import com.ryanb3.planetengine.physics.PlanetManager;

public class MouseControls implements MouseListener {

	PlanetManager planetManager;
	double massPerPixel = 2.965e+23;
	Point startPixel = new Point(0, 0);
	Point endPixel = new Point(0, 0);
	private Dimension screenDimensions;
	Panel panel;

	public MouseControls(Dimension screenDimensions, PlanetManager planetManager, Panel panel) {
		this.screenDimensions = screenDimensions;
		this.planetManager = planetManager;
		this.panel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		startPixel = arg0.getPoint();		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		endPixel = arg0.getPoint();	
		double dy = endPixel.getY() - startPixel.getY();
		double dx = endPixel.getX() - startPixel.getX();
		
		MyVector center = planetManager.getCenterOfMass();
		
		double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		System.out.println(distance);
		double angle = Math.atan(dy / dx);
		
		double distancePerPixel = planetManager.getScale();
		
		double xDistance = (startPixel.getX() + distance / 2 - screenDimensions.width / 2) * distancePerPixel + center.x;
		double yDistance = (startPixel.getY() + distance / 2 - screenDimensions.height / 2) * distancePerPixel + center.y;

		MyVector possition = new MyVector(xDistance, yDistance, 0);
				
		MyVector velocity = new MyVector(0, 0, 0);

		Sphere toAdd = new Sphere(distance * massPerPixel, possition, velocity, randomColor(), distance / 2, "bleh");
		
		panel.addNewObject(toAdd);
	}
	
	public Color randomColor() {
		return new Color((int)1*255, (int)1*255, (int)1*255);
	}

}
