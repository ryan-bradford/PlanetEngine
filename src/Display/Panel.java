package Display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Bodies.Sphere;
import Math.MyVector;
import Physics.PlanetManager;

public class Panel extends JPanel {

	public PlanetManager manager;
	ArrayList<Sphere> objects;
	Dimension screenDimensions;
	int counter = 0;

	public Panel(ArrayList<Sphere> objects, Dimension screenDimensions) {
		this.screenDimensions = screenDimensions;
		this.objects = objects;
		this.setLayout(null);
		this.setBackground(Color.black);
		manager = new PlanetManager(objects, screenDimensions);
		CreateObjectButton objectButton = new CreateObjectButton("New Body!", objects, manager);
		objectButton.setBounds(100, 100, 150, 100);
		add(objectButton, 0);
		StandardSceneButton sceneButton = new StandardSceneButton("New Scene!", objects, manager);
		sceneButton.setBounds(100, 300, 150, 100);
		add(sceneButton, 0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			MyVector center = manager.getCenterOfMass();
			g.setColor(Color.black);
			g.drawRect(0, 0, screenDimensions.width, screenDimensions.height);
			double scale = manager.getScale();
			for (int i = 0; i < objects.size(); i++) {
				g.setColor(objects.get(i).getColor());
				double thisX = objects.get(i).getPosition().x;
				double thisY = objects.get(i).getPosition().y;
				double radius = (objects.get(i).getRadius() / (scale / 1000));
				if(radius > 100) {
					radius = 100;
				}
				double x = (thisX) / scale - radius / 2 + screenDimensions.width/2;
				double y = (thisY) / scale - radius / 2 + screenDimensions.height/2;
				System.out.println(objects.get(i).getName() + " " + radius);
				g.fillOval((int) (x - (center.x)/scale), (int) (y - (center.y)/scale), (int)radius, (int)radius);
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}
}
