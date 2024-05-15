package Proyecto1_Recursividad.test.Pow1Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto1_Recursividad.Algoritmos;

class RecursivePow1Test {

	@Test
	void recursivePow1Caso1() {
		int n = 0;
		assertEquals(Algoritmos.recursivePow1(n), 1);
	}
	
	@Test
	void recursivePow1Caso2() {
		int n = 1;
		assertEquals(Algoritmos.recursivePow1(n), 2);
	}
	@Test
	void recursivePow1Caso3() {
		int n = 2;
		assertEquals(Algoritmos.recursivePow1(n), 4);
	}
	@Test
	void recursivePow1Caso4() {
		int n = 3;
		assertEquals(Algoritmos.recursivePow1(n), 8);
	}
	@Test
	void recursivePow1Caso5() {
		int n = 4;
		assertEquals(Algoritmos.recursivePow1(n), 16);
	}

}
