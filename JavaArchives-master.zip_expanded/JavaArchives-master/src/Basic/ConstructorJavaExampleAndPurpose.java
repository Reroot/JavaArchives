package Basic;

public class ConstructorJavaExampleAndPurpose {
	private String name;

	//	Constructor is a block of code that initializes the newly created object. A constructor 
	//	resembles an instance method in java but it’s not a method as it doesn’t 
	//	have a return type. In short constructor and method 
	ConstructorJavaExampleAndPurpose() {
	      this.name = "Gettting Whatever is here";
	}
	
//	ConstructorJavaExampleAndPurpose obj = new ConstructorJavaExampleAndPurpose()
//			The new keyword here creates the object of class MyClass 
//			and invokes the constructor to initialize this newly created object.
		public static void main(String[] args) {
			ConstructorJavaExampleAndPurpose obj = new ConstructorJavaExampleAndPurpose();
			System.out.println(obj.name);
		}
		
		//if you do not implement any constructor in your class, Java compiler inserts a default constructor into your code on your behalf.
		//If you implement any constructor then you no longer receive a default constructor from Java compiler.
		

}
//
//With Parameters being initialized with passed in obj
//and constructed with method objects
//public class Employee {
//
//	   int empId;  
//	   String empName;  
//		    
//	   //parameterized constructor with two parameters
//	   Employee(int id, String name){  
//	       this.empId = id;  
//	       this.empName = name;  
//	   }  
//	   void info(){
//	        System.out.println("Id: "+empId+" Name: "+empName);
//	   }  
//		   
//	   public static void main(String args[]){  
//		Employee obj1 = new Employee(10245,"Chaitanya");  
//		Employee obj2 = new Employee(92232,"Negan");  
//		obj1.info();  
//		obj2.info();  
//Output:
//
//Id: 10245 Name: Chaitanya
//Id: 92232 Name: Negan
//	   }  
//	}

//We can invoke both a default and a parameter constructor
//to work intercahangby 
//class Example2
//{
//      private int var;
//      //default constructor
//      public Example2()
//      {
//             this.var = 10;
//      }
//      //parameterized constructor
//      public Example2(int num)
//      {
//             this.var = num;
//      }
//      public int getValue()
//      {
//              return var;
//      }
//      public static void main(String args[])
//      {
//              Example2 obj = new Example2();
//              Example2 obj2 = new Example2(100);
//              System.out.println("var is: "+obj.getValue());
//              System.out.println("var is: "+obj2.getValue());
//      }
//}
//Output:
//
//var is: 10
//var is: 100

//if invoked without a defualt and a not using the pass it
//will thorw a compile error
//class Example3
//{
//      private int var;
//      public Example3(int num)
//      {
//             var=num;
//      }
//      public int getValue()
//      {
//              return var;
//      }
//      public static void main(String args[])
//      {
//              Example3 myobj = new Example3();
//              System.out.println("value of var is: "+myobj.getValue());
//      }
//}
//Output: It will throw a compilation error. The reason is, the statement Example3 myobj = new Example3() 
//is invoking a default constructor which we don’t 
//have in our program. when you don’t implement any 
//constructor in your class, compiler inserts the default constructor into your code
//
//********Super()*******
//Whenever a child class constructor gets invoked it implicitly invokes the constructor of parent class. You can also say that the compiler inserts a super(); statement at the beginning of child class constructor.
//
//class MyParentClass {
//   MyParentClass(){
//	System.out.println("MyParentClass Constructor");
//   }
//}
//class MyChildClass extends MyParentClass{
//   MyChildClass() {
//	System.out.println("MyChildClass Constructor");
//   }
//   public static void main(String args[]) {
//	new MyChildClass();
//   }
//}


//***
//Constructor Overloading
//Constructor overloading is a concept of having more than one constructor with different parameters list, in such a way so that each constructor performs a different task.




