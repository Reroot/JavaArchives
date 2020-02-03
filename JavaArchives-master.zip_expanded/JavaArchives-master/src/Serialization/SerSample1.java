/**
 * 
 */
package Serialization;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
/**
 * @author guts
 *
 */
public class SerSample1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "./BinaryWords.ser";
		String[] words = {"six","teen","doors"};
		System.out.println(Arrays.deepToString(words));
		
//		try(ObjectOutputStream serializer = 
//				(new FileOutputStream(fileName))) {
//			
//		} catch (FileNotFoundException) {
//			
//		}
	}
}


