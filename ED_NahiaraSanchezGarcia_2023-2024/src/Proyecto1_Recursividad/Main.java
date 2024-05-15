package Proyecto1_Recursividad;

import java.io.IOException;

public class Main {
	
	public static void main (String[] args) throws IOException {
		//System.out.print(TestBench.testAlgorithm ("Codigo.Algoritmos", "iterativeFactorial", 6));
		
		TestBench.test("linealB.csv", 5, 1, 10, "Codigo.Algoritmos", "lineal");
		TestBench.test("quadraticB.csv", 5, 1, 10, "Codigo.Algoritmos", "quadratic");
		TestBench.test("cubicB.csv", 5, 1, 10, "Codigo.Algoritmos", "cubic");
		TestBench.test("logarithmicB.csv", 5, 1, 10, "Codigo.Algoritmos", "logarithmic");

	}
	
}
