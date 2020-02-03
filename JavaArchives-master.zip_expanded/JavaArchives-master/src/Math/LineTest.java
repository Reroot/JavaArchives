//package Math;
//
//import org.junit.jupiter.api.Test;
//
//public class LineTest {
//
//	Line line1;
//	Line line2;
//	Line lineParallel;
//	
//	@Before
//	public void startUp() {
//		line1 = new Line(0,4,1,5);
//		line2 = new Line(0,0,1,5);
//		lineParallel = new Line(3,7,6,10);
//	}
//	
//	@Test
//	public void testGetSlope() {
//		assertEquals(line1.getSlope(), 1, 0.0001);
//	}
//	
//	@Test (expected = ArithmeticException.class)
//	public void testGetSlopeUndefined() {
//		line2.getSlope();
//		fail();
//	}
//
//	@Test
//	public void testGetDistance() {
//		assertEquals(line1.getDistance(), Math.sqrt(32), 0.0001);
//	}
//
//	@Test
//	public void testParalletlTo() {
//		assertTrue(line1.paralletlTo(lineParallel));
//	}
//
//}