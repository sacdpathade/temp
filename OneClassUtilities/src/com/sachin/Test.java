package com.sachin;

public class Test {
    public static void main(String[] args) {
        
        int a = 10;

        int b = 100;

        // System.out.println("Addition = " + (a+b));

        //  System.out.println("Subtraction = " + (a-b));

        // System.out.println("Multiplication = " + (a*b));

        // System.out.println("Division = " + (a/b));

        int result = b;

        for(int index=1; index<=b; index++) {
            for(int subindex = 0 ;subindex <index; subindex++) {
                 System.out.print("*");
            }
            System.out.println();
           
        }






    }
    
}