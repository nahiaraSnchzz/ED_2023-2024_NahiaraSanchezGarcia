package Proyecto1_Recursividad.test.Pow3Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto1_Recursividad.Algoritmos;


class RecursivePow3Test {

	@Test
	void recursivePow3Caso1() {
		int n = 0;
		assertEquals(Algoritmos.recursivePow3(n), 1);
	}
	
	@Test
	void recursivePow3Caso2() {
		int n = 1;
		assertEquals(Algoritmos.recursivePow3(n), 2);
	}
	@Test
	void recursivePow3Caso3() {
		int n = 2;
		assertEquals(Algoritmos.recursivePow3(n), 4);
	}
	@Test
	void recursivePow3Caso4() {
		int n = 3;
		assertEquals(Algoritmos.recursivePow3(n), 8);
	}
	@Test
	void recursivePow3Caso5() {
		int n = 4;
		assertEquals(Algoritmos.recursivePow3(n), 16);
	}

}
