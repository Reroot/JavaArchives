package AbstractionSample;

public class ShapeTest {
	public static void main(String[] args) {
		Shape s = new Triangle((float)3, (float)3);
		s.display();
		System.out.println(s.calculateArea());
	}

}
