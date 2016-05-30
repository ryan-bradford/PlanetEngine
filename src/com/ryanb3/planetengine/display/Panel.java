package com.ryanb3.planetengine.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ryanb3.planetengine.body.Sphere;
import com.ryanb3.planetengine.clickControls.MouseControls;
import com.ryanb3.planetengine.math.MyVector;
import com.ryanb3.planetengine.physics.PlanetManager;

public class Panel extends JPanel {

	private PlanetManager planetManager;
	private ArrayList<Sphere> objects;
	private Dimension screenDimensions;

	public Panel(Dimension screenDimensions) {
		this.screenDimensions = screenDimensions;
		setLayout(null);
		setBackground(Color.BLACK);
		setBounds(0, 0, screenDimensions.width, screenDimensions.height);

		objects = new ArrayList<Sphere>();
		planetManager = new PlanetManager(objects, screenDimensions, this);
	
		newPlanetButton();		
		removePlanetButton();
		sceneButton();
		
		initMouseControls();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			MyVector center = planetManager.getCenterOfMass();

			g.setColor(Color.black);
			g.drawRect(0, 0, screenDimensions.width, screenDimensions.height);

			double scale = planetManager.getScale();
			for (int i = 0; i < objects.size(); i++) {
				Sphere object = objects.get(i);

				g.setColor(object.getColor());

				MyVector position = object.getPosition();
				double realX = position.x;
				double realY = position.y;
				double radius = (object.getRadius());

				double x = (realX) / scale - radius + screenDimensions.width / 2;
				double y = (realY) / scale - radius + screenDimensions.height / 2;
				objects.get(i).setScreenPossition(new MyVector(x, y, 0));
				g.fillOval((int) (x - (center.x) / scale), (int) (y - (center.y) / scale), (int) radius, (int) radius);
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	public void addNewObject(Sphere object) {
		objects.add(object);
	}

	public PlanetManager getPlanetManager() {
		return planetManager;
	}

	public ArrayList<Sphere> getObjects() {
		return objects;
	}
 
	public void initMouseControls() {
		MouseControls mouseControls = new MouseControls(screenDimensions, planetManager, this);
		this.addMouseListener(mouseControls);
	}
	
	public void newPlanetButton() {
		JButton objectButton = new JButton("New Body!");
		objectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = JOptionPane.showInputDialog("What Is The Name Of This Object?");
					double mass = Double.parseDouble(JOptionPane.showInputDialog("What Is The Mass Of This Object?"));
					double x = Double.parseDouble(
							JOptionPane.showInputDialog("What Is The X Location Of This Object? (Earth is 0)"));
					double y = Double.parseDouble(
							JOptionPane.showInputDialog("What Is The Y Location Of This Object? (Earth is 0)"));
					double z = Double.parseDouble(
							JOptionPane.showInputDialog("What Is The Z Location Of This Object? (Earth is 0)"));
					double xv = Double
							.parseDouble(JOptionPane.showInputDialog("What Is The X Velocity Of This Object?"));
					double yv = Double
							.parseDouble(JOptionPane.showInputDialog("What Is The Y Velocity Of This Object?"));
					double zv = Double
							.parseDouble(JOptionPane.showInputDialog("What Is The Z Velocity Of This Object?"));
					double radius = Double.parseDouble(
							JOptionPane.showInputDialog("What Is The Radius Of This Object on the Screen?"));
					objects.add(new Sphere(mass, new MyVector(x, y, z), new MyVector(xv, yv, zv),
							new Color((int) (Math.random() * 255), (int) (Math.random() * 255),
									(int) (Math.random() * 255)),
							radius, name));
				} catch (NullPointerException ex) {

				} catch(NumberFormatException ex) {
					
				}
			}
		});	
		
		objectButton.setBounds(100, 100, 150, 100);
		add(objectButton, 0);
	}
	
	public void removePlanetButton() {
		JButton removeButton = new JButton("Remove All Bodies");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int size = objects.size();
				for(int i = 0; i < size; i++) {
					objects.remove(0);
				}
			}
		});
		removeButton.setBounds(100, 500, 150, 100);
		add(removeButton, 0);
	}
	
	public void sceneButton() {
		StandardSceneButton sceneButton = new StandardSceneButton("New Scene!", objects, planetManager);
		sceneButton.setBounds(100, 300, 150, 100);
		add(sceneButton, 0);
	}
}
