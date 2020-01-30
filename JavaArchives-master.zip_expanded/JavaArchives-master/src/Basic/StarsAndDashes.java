package Basic;


// * The first assignment for smoothstack.  The assignment was to replicate
// * a pattern of stars and dashes in the command line using for loops and
// * methods to break up the logic.
// 1)
// *
// **
// ***
// ****
// ---------

// 2)
// ----------
// ****
// ***
// **
// *

// 3)
//      *
//     ***
//    *****
//   *******
// -----------

// 4)
// ------------
//   *******
//    *****
//     ***
//      *

public class StarsAndDashes {
	
	/**
	 * Current number the program is working on
	 */
	int currNum;
	
	/**
	 * The string of the current amount of dashes
	 */
	StringBuilder currDashes;
	
	/**
	 * Object to hold the current num and current dashes
	 */
	public StarsAndDashes(){
		this.currNum = 1;
		this.currDashes = new StringBuilder("---------");
	}
	
	/**
	 * Prints the first star pattern
	 */
	public void printOne(){
		for (int i = 1; i < 5; i++){ //amount of /n
			for (int o = 1; o <= i; o++){
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Prints the second star pattern
	 */
	public void printTwo(){
		for (int i = 5; i > 0; i--){ //amount on /n
			for (int o = 1; o < i; o++){ //fist iter is 4 lines
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Prints the third star pattern
	 */
	public void printThree(){
		for (int i = 0; i < 4; i++){
			for (int o = 0; o < 5 - i; o++){
				System.out.print(" ");
			}
			for (int o = 0; o < 1 + (i*2); o++){
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Prints the fourth star pattern
	 */
	public void printFour(){
		for (int i = 3; i >= 0; i--){
			for (int o = 0; o < 5 - i; o++){
				System.out.print(" ");
			}
			for (int o = 0; o < 1 + (i*2); o++){
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Prints the current amount of dashes then adds another
	 */
	public void printThenAddDash(){
		System.out.println(currDashes);
		currDashes.append("-");
	}
	
	/**
	 * Prints the current number then increments by one
	 */
	public void printThenAddNumber(){
		System.out.println("" + currNum + ")");
		currNum++;
	}

	/**
	 * Main method to run the assignment
	 */
	public static void main(final String[] args){
		final StarsAndDashes assign = new StarsAndDashes();
		assign.printThenAddNumber();
		assign.printOne();
		assign.printThenAddDash();
		System.out.println();
		assign.printThenAddNumber();
		assign.printThenAddDash();
		assign.printTwo();
		System.out.println();
		assign.printThenAddNumber();
		assign.printThree();
		assign.printThenAddDash();
		System.out.println();
		assign.printThenAddNumber();
		assign.printThenAddDash();
		assign.printFour();
	}
}