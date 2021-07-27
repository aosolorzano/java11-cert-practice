package com.hiperium.java.cert.prep.unit.one;

public class ClassInitializr {
    
    public ClassInitializr() {
	    System.out.println("Class Constructor. Actual Class number: " + number);
   	    number = 5;
    }

    public static void main(String[] args) {
	    ClassInitializr egg = new ClassInitializr();
	    System.out.println("New object Class number: " + egg.number);
    }

    private int number = 3;

    { 
	    System.out.println("Initial Class number: " + number);
	    number = 4;
    }
}
