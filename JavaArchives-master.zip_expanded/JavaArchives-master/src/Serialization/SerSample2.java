package Serialization;

//Java code for serialization and deserialization 
//of a Java object 

//Remember the purpose of serialization is 
//Advantages of Serialization
//1. To save/persist state of an object.
//2. To travel an object across a network.
import java.io.*; 

class SerSample2 implements java.io.Serializable 
{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 9138981737353839888L;
	public int a; 
	public String b; 

	// Default constructor 
	public SerSample2(int a, String b) 
	{ 
		this.a = a; 
		this.b = b; 
	} 
	public static void main(String[] args) 
	{ 
		SerSample2 object = new SerSample2(1, "geeksforgeeks"); 
		String filename = "file.ser"; 
		
		// Serialization 
		try
		{ 
			//Saving of object in a file 
			FileOutputStream file = new FileOutputStream(filename); 
			ObjectOutputStream out = new ObjectOutputStream(file); 
			
			// Method for serialization of object 
			out.writeObject(object); 
			
			out.close(); 
			file.close(); 
			
			System.out.println("Object has been serialized"); 

		} 
		
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 


		SerSample2 object1 = null; 

		// Deserialization 
		try
		{ 
			// Reading the object from a file 
			FileInputStream file = new FileInputStream(filename); 
			ObjectInputStream in = new ObjectInputStream(file); 
			
			// Method for deserialization of object 
			object1 = (SerSample2)in.readObject(); 
			
			in.close(); 
			file.close(); 
			
			System.out.println("Object has been deserialized "); 
			System.out.println("a = " + object1.a); 
			System.out.println("b = " + object1.b); 
		} 
		
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 
		
		catch(ClassNotFoundException ex) 
		{ 
			System.out.println("ClassNotFoundException is caught"); 
		} 

	} 

} 

