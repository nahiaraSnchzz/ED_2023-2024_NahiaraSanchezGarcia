package Proyecto1_Recursividad.test.factorial;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Proyecto1_Recursividad.Algoritmos;

public class RecursiveFactorial {
	
	@Test
	void recursiveFactorialCaso1() {
		int n = 0;
		assertEquals (Algoritmos.recursiveFactorial(n), 1);
	}
	
	@Test
	void recursiveFactorialCaso2() {
		int n = 1;
		assertEquals (Algoritmos.recursiveFactorial(n), 1);
	}
	
	@Test
	void recursiveFactorialCaso3() {
		int n = 2;
		assertEquals (Algoritmos.recursiveFactorial(n), 2);
	}
	
	@Test
	void recursiveFactorialCaso4() {
		int n = 3;
		assertEquals (Algoritmos.recursiveFactorial(n), 6);
	}
	
	@Test
	void recursiveFactorialCaso5() {
		int n = 4;
		assertEquals (Algoritmos.recursiveFactorial(n), 24);
	}
	
	@Test
	void recursiveFactorialCaso6() {
		int n = 5;
		assertEquals (Algoritmos.recursiveFactorial(n), 120);
	}


}
