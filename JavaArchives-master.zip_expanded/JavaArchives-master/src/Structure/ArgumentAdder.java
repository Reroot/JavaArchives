package Structure;

/**
 * This assignment involves taking arguments from the command line (An assumption
 * was made that all arguments were integers) and adding them together.
 * 
 * @author ryanb
 *
 */
public class ArgumentAdder {

	/**
	 * Sum of all arguments
	 */
	Integer sum = 0;
	
	public ArgumentAdder() {}
	
	/**
	 * If arguments are given, this method will add up all arguments and report a sum
	 * 
	 * @param args
	 * 		Integers to be added
	 */
	public static void main(String[] args){
		ArgumentAdder adder = new ArgumentAdder();

		if (args.length > 0) {
			Integer[] intArr = adder.convertToInteger(args);
			for (Integer currInt: intArr){
				adder.sum += currInt;
			}
			System.out.println("The sum of all the arguments is: " + adder.sum.toString());
		} else {
			System.out.println("No arguments given");
		}
	}

	/**
	 * Converts the arguments from Strings to Integers
	 * @param args
	 * 		The Strings being inputed
	 * @return an array of Integers
	 */
	private Integer[] convertToInteger(String[] args) {

		Integer[] convertedInts = new Integer[args.length];
		for (int i = 0; i < args.length; i++){
			convertedInts[i] = new Integer(args[i]);
		}
		return convertedInts;
	}
}