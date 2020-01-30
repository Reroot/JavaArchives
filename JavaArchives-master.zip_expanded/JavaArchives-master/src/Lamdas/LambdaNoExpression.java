package Lamdas;

@FunctionalInterface
interface MyFunctionalInterface2 {

	//A method with no parameter
    public String sayHello();
}
public class LambdaNoExpression {

   public static void main(String args[]) {
        // lambda expression
    	MyFunctionalInterface2 msg = () -> {
    		return "Hello";
    	};
    	
    	
        System.out.println(msg.sayHello());
    }
}

