package Collections;

//Java Program to demonstrate the working 
//of the Collectors.joining() method 

import java.util.stream.Collectors; 
import java.util.stream.Stream; 

public class StreamOne { 
	public static void main(String[] args) 
	{ 
		// Create a character array 
		char[] ch = { 'G', 'e', 'e', 'k', 's', 
					'f', 'o', 'r', 
					'G', 'e', 'e', 'k', 's' }; 

		// Convert the character array into String 
		// using Collectors.joining() method 
		String chString = Stream.of(ch) 
							.map(arr -> new String(arr)) 
							.collect(Collectors.joining()); 

		// Print the concatenated String 
		System.out.println(chString); 
	} 
} 
