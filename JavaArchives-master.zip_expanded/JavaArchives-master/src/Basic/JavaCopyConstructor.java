package Basic;

//A copy constructor in a Java class is a constructor that creates an object using another object of the same Java class.
//
//That's helpful when we want to copy a complex object that has several fields, 
//or when we want to make a deep copy of an existing object.


class JavaCopyConstructor{  
	   String web; 
	   JavaCopyConstructor(String w){  
		web = w;
	   }  

	   /* This is the Copy Constructor, it 
	    * copies the values of one object
	    * to the another object (the object
	    * that invokes this constructor)
	    */
	   //THIS EXTRACTS THE WEB STRING FROM THE OBJ
	   JavaCopyConstructor(JavaCopyConstructor je){  
		web = je.web; 
	   }  
	   void disp(){
		System.out.println("Website: "+web);
	   }  

	   public static void main(String args[]){  
		   JavaCopyConstructor obj1 = new JavaCopyConstructor("BeginnersBook");  
			
		/* Passing the object as an argument to the constructor
		 * This will invoke the copy constructor
		 */
		JavaCopyConstructor obj2 = new JavaCopyConstructor(obj1);  
		obj1.disp();  
		obj2.disp();  
	   }  
	}
//	Output:
//
//	Website: BeginnersBook
//	Website: BeginnersBook