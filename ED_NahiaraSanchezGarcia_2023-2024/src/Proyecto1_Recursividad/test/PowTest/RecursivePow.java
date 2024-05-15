package Proyecto1_Recursividad.test.PowTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto1_Recursividad.Algoritmos;


class RecursivePow {

	@Test
	void recursivePowCaso1() {
		int n = 2;
		int p = 0;
		assertEquals(Algoritmos.recursivePow(n, p), 1);
	}
	
	@Test
	void recursivePowCaso2() {
		int n = 2;
		int p = 1;
		assertEquals(Algoritmos.recursivePow(n, p), 2);
	}
	@Test
	void recursivePowCaso3() {
		int n = 2;
		int p = 2;
		assertEquals(Algoritmos.recursivePow(n, p), 4);
	}
	@Test
	void recursivePowCaso4() {
		int n = 2;
		int p = 3;
		assertEquals(Algoritmos.recursivePow(n, p), 8);
	}
	@Test
	void recursivePowCaso5() {
		int n = 2;
		int p = 4;
		assertEquals(Algoritmos.recursivePow(n, p), 16);
	}

}
