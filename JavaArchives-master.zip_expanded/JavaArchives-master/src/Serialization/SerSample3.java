package Serialization;


//Java code for serialization and deserialization 
//of a Java object 
import java.io.*; 
//Description for Output:
//You have seen while deserializing the object the values of a and b 
//has changed. The reason being a was marked as transient and b was 
//static.
//In case of transient variables:- A variable defined with transient 
//keyword is not serialized during serialization process.This variable
//will be initialized with default value during deserialization. (e.g: for objects it is null, for int it is 0).
//In case of static Variables:- A variable defined with static keyword 
//is not serialized during serialization process.This variable will be loaded with current value defined in the class during deserialization.
class Emp implements Serializable { 
private static final long serialversionUID = 
								129348938L; 
//transient keyword, it ignores original value of the variable and save default value of that variable data type.
	transient int a; 
	//static allows data manipulation, All references to the static refer to the same memory location. Static items have the static lifetime,
	static int b; 
	String name; 
	int age; 

	// Default constructor 
public Emp(String name, int age, int a, int b) 
	{ 
		this.name = name; 
		this.age = age; 
		this.a = a; 
		this.b = b; 
	} 

} 

public class SerSample3 { 
	
	public static void printdata(Emp object1) { 
			System.out.println("name = " + object1.name); 
			System.out.println("age = " + object1.age); 
			System.out.println("a = " + object1.a); 
			System.out.println("b = " + object1.b); 
		} 

	public static void main(String[] args) { 
		Emp object = new Emp("ab", 20, 2, 1000); 
		String filename = "shubham.txt"; 
		

		// Serialization 
		try { 

			// Saving of object in a file 
			FileOutputStream file = new FileOutputStream 
										(filename); 
			ObjectOutputStream out = new ObjectOutputStream 
										(file); 

			// Method for serialization of object 
			out.writeObject(object); 

			out.close(); 
			file.close(); 

			System.out.println("Object has been serialized\n"
							+ "Data before Deserialization."); 
			printdata(object); 

			// value of static variable changed 
			object.b = 2000; 
		} catch (IOException ex) { 
			System.out.println("IOException is caught"); 
		} 

		object = null; 

		// Deserialization 
		try { 

			// Reading the object from a file 
			FileInputStream file = new FileInputStream 
										(filename); 
			ObjectInputStream in = new ObjectInputStream 
										(file); 

			// Method for deserialization of object 
			object = (Emp)in.readObject(); 

			in.close(); 
			file.close(); 
			System.out.println("Object has been deserialized\n"
								+ "Data after Deserialization."); 
			printdata(object); 

			// System.out.println("z = " + object1.z); 
		} 

		catch (IOException ex) { 
			System.out.println("IOException is caught"); 
		} 

		catch (ClassNotFoundException ex) { 
			System.out.println("ClassNotFoundException" + 
								" is caught"); 
		} 
	} 
} 
