package Structure;


public class MaxNumInArray {

	/**
	 * Array to find the maximum number in.  Edit this to test different arrays.
	 */
	Integer[][] findMaxNum = {{2, 3},{8, 11}};
	
	public MaxNumInArray() {}
	
	/**
	 * This method loops through the entire 2D array, finds the maximum number, and reports its position.
	 * In the case of two numbers being equal at the maximum, the first position will be reported.
	 * 
	 * @param arr
	 * 		2-D array to be searched through
	 * @return answer array consisting of [The maximum number in array, row position, column position]
	 */
	public Integer[] getMaxNumInArray(Integer[][] arr) {
		Integer maxNum = arr[0][0];
		Integer row = 0;
		Integer col = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int o = 0; o < arr[i].length; o++){
				Integer testMax = Math.max(maxNum, arr[i][o]);
				if (testMax > maxNum){
					maxNum = testMax;
					row = i;
					col = o;
				}
			}
		}
		Integer[] returnArr = {maxNum, row, col};
		return returnArr;
	}
	
	/**
	 * Method to run tests on getMaxNumInArray()
	 * 
	 * @param args
	 * 		N/A
	 */
	public static void main(String[] args){
		MaxNumInArray obj = new MaxNumInArray();
		Integer[] answer = obj.getMaxNumInArray(obj.findMaxNum);
		System.out.println("The maximum number is: " + answer[0]);
		System.out.println("The position is: (" + answer[1] + "," + answer[2] + ")");
	}
}