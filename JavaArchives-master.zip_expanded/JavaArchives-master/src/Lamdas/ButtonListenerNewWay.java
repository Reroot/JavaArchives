package Lamdas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  

public class ButtonListenerNewWay {  
   public static void main(String[] args) {  
      Frame frame = new Frame("ActionListener java8");  
     
      Button b=new Button("Click Here");  
      b.setBounds(50,100,80,50); 
//      b.addActionListener(new ActionListener(){  
//          public void actionPerformed(ActionEvent e){  
//    	     System.out.println("Hello World!"); 
//          }  
//       });  
      b.addActionListener(e -> System.out.println(e)); 
      frame.add(b);
  
      frame.setSize(200,200);  
      frame.setLayout(null);  
      frame.setVisible(true);   
   }  
}


//Where to use the Lambdas in Java
//To use lambda expression, you need to either create your own functional
//interface or use the pre defined functional interface provided by Java. 
//An interface with only single abstract method is called functional interface(or
//Single Abstract method interface), for example: Runnable, callable, 
//ActionListener etc.
//
//To use function interface:
//Pre Java 8: We create anonymous inner classes.
//Post Java 8: You can use lambda expression instead of anonymous 
//inner classes.