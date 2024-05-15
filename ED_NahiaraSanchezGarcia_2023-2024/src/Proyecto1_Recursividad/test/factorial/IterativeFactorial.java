package Proyecto1_Recursividad.test.factorial;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Proyecto1_Recursividad.Algoritmos;

public class IterativeFactorial {
	
	@Test
	void iterativeFactorialCaso1() {
		int n = 0;
		assertEquals (Algoritmos.iterativeFactorial(n), 1);
	}
	
	@Test
	void iterativeFactorialCaso2() {
		int n = 1;
		assertEquals (Algoritmos.iterativeFactorial(n), 1);
	}
	
	@Test
	void iterativeFactorialCaso3() {
		int n = 2;
		assertEquals (Algoritmos.iterativeFactorial(n), 2);
	}
	
	@Test
	void iterativeFactorialCaso4() {
		int n = 3;
		assertEquals (Algoritmos.iterativeFactorial(n), 6);
	}
	
	@Test
	void iterativeFactorialCaso5() {
		int n = 4;
		assertEquals (Algoritmos.iterativeFactorial(n), 24);
	}
	
	@Test
	void iterativeFactorialCaso6() {
		int n = 5;
		assertEquals (Algoritmos.iterativeFactorial(n), 120);
	}
	

}
