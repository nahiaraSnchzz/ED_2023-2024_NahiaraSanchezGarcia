package Proyecto1_Recursividad.test.fibonacci;

import static org.junit.jupiter.api.Assertions.*;
import Proyecto1_Recursividad.Algoritmos;
import org.junit.jupiter.api.Test;


class IterativeFibonacci {

	@Test
	void iterativeFibonacciCaso1() {
		int n = 0;
		assertEquals( Algoritmos.iterativeFibonacci(n), 0);
	}
	
	@Test
	void iterativeFibonacciCaso2() {
		int n = 1;
		assertEquals( Algoritmos.iterativeFibonacci(n), 1);
	}
	
	@Test
	void iterativeFibonacciCaso3() {
		int n = 2;
		assertEquals( Algoritmos.iterativeFibonacci(n), 1);
	}
	
	@Test
	void iterativeFibonacciCaso4() {
		int n = 3;
		assertEquals( Algoritmos.iterativeFibonacci(n), 2);
	}
	
	@Test
	void iterativeFibonacciCaso5() {
		int n = 4;
		assertEquals( Algoritmos.iterativeFibonacci(n), 3);
	}
	
	@Test
	void iterativeFibonacciCaso6() {
		int n = 5;
		assertEquals( Algoritmos.iterativeFibonacci(n), 5);
	}
	
	@Test
	void iterativeFibonacciCaso7() {
		int n = 1000000;
		assertEquals( Algoritmos.iterativeFibonacci(n), 1884755131);
	}

}
