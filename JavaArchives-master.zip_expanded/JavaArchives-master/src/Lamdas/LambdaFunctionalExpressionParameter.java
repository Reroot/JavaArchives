package Lamdas;

@FunctionalInterface
interface MyFunctionalInterface {

	//A method with single parameter
    public int incrementByFive(int a);
}
public class LambdaFunctionalExpressionParameter {

   public static void main(String args[]) {
        // lambda expression with single parameter num
    	MyFunctionalInterface f = (num) -> num+5;
        System.out.println(f.incrementByFive(22));
    }
}



//package Lamdas;
//
//@FunctionalInterface
//interface MyFunctionalInterface {
//
//	//A method with no parameter
//  public String sayHello();
//}
//public class LambdaNoExpression {
//
// public static void main(String args[]) {
//      // lambda expression
//  	MyFunctionalInterface msg = () -> {
//  		return "Hello";
//  	};
//      System.out.println(msg.sayHello());
//  }
//}