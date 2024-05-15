package Proyecto1_Recursividad.test.fibonacci;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto1_Recursividad.Algoritmos;

class RecursiveFibonacci {

	@Test
	void recursiveFibonacciCaso1() {
		int n = 0;
		assertEquals( Algoritmos.recursiveFibonacci(n), 0);
	}
	
	@Test
	void recursiveFibonacciCaso2() {
		int n = 1;
		assertEquals( Algoritmos.recursiveFibonacci(n), 1);
	}
	
	@Test
	void recursiveFibonacciCaso3() {
		int n = 2;
		assertEquals( Algoritmos.recursiveFibonacci(n), 1);
	}
	
	@Test
	void recursiveFibonacciCaso4() {
		int n = 3;
		assertEquals( Algoritmos.recursiveFibonacci(n), 2);
	}
	
	@Test
	void recursiveFibonacciCaso5() {
		int n = 4;
		assertEquals( Algoritmos.recursiveFibonacci(n), 3);
	}
	
	@Test
	void recursiveFibonacciCaso6() {
		int n = 5;
		assertEquals( Algoritmos.recursiveFibonacci(n), 5);
	}
	
	@Test
	void recursiveFibonacciCaso7() {
		int n = 1000000;
		assertEquals( Algoritmos.recursiveFibonacci(n), 1884755131);
	}

}
