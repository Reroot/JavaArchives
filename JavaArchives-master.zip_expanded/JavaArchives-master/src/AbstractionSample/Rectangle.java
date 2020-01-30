package AbstractionSample;

public class Rectangle implements Shape{

	Float length;
	Float width;
	
	public Rectangle(Float length, Float width) {
		this.length = length;
		this.width = width;
	}

	@Override
	public Float calculateArea() {
		return length * width;
	}

	@Override
	public void display() {
		System.out.println("[]");
	}
	
	
}