package Math;

public class MyVector {

	public double x = 0;
	public double y = 0;
	double z = 0;
	
	public MyVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public MyVector addTo(MyVector toAdd) {
		return new MyVector(x + toAdd.x, y + toAdd.y, z + toAdd.z);
	}
	
	public MyVector subtract(MyVector toSubtract) {
		return new MyVector(x - toSubtract.x, y - toSubtract.y, z - toSubtract.z);
	}
	
	public MyVector scale(double num) {
		return new MyVector(x * num, y * num, z * num);
	}
	
	public double getDistance(MyVector from) {
		double total = 0;
		total += Math.pow((x - from.x), 2);
		total += Math.pow((y - from.y), 2);
		//total += Math.pow((z - from.z), 2);
		return Math.sqrt(total);
	}
	
	public double getMag() {
		double total = 0;
		total += Math.pow((x), 2);
		total += Math.pow((y), 2);
		total += Math.pow((z), 2);
		return Math.sqrt(total);
	}
	
	public MyVector norm() {
		double mag = getMag();
		return new MyVector(x / mag, y / mag, z / mag);
	}
}
