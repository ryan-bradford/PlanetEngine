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

public class CreateObjectButton extends JButton {

	ArrayList<Sphere> objects;
	PlanetManager phyics;
	
	public CreateObjectButton(String message, ArrayList<Sphere> objects, PlanetManager phyics) {
		super(message);
		this.phyics = phyics;
		this.objects = objects;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("What Is The Name Of This Object?");
				double mass = Double.parseDouble(JOptionPane.showInputDialog("What Is The Mass Of This Object?"));
				double x = Double.parseDouble(JOptionPane.showInputDialog("What Is The X Location Of This Object? (Earth is 0)"));
				double y = Double.parseDouble(JOptionPane.showInputDialog("What Is The Y Location Of This Object? (Earth is 0)"));
				double z = Double.parseDouble(JOptionPane.showInputDialog("What Is The Z Location Of This Object? (Earth is 0)"));
				double xv = Double.parseDouble(JOptionPane.showInputDialog("What Is The X Velocity Of This Object?"));
				double yv = Double.parseDouble(JOptionPane.showInputDialog("What Is The Y Velocity Of This Object?"));
				double zv = Double.parseDouble(JOptionPane.showInputDialog("What Is The Z Velocity Of This Object?"));
				double radius = Double.parseDouble(JOptionPane.showInputDialog("What Is The Radius Of This Object on the Screen (In Pixels)?"));
				objects.add(new Sphere(mass, new MyVector(x, y, z), new MyVector(xv, yv, zv),
						new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random()*255)), radius, name));
				phyics.reCalcScale();
			}
		});

	}

}
