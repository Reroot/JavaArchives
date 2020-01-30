package AbstractionSample;


public class Circle implements Shape{

	Float radius;
	
	public Circle(Float radius) {
		this.radius = radius;
	}
	
	@Override
	public Float calculateArea() {
		return new Float(Math.PI * Math.pow(radius, 2));
	}

	@Override
	public void display() {
		System.out.println("O");
	}

}