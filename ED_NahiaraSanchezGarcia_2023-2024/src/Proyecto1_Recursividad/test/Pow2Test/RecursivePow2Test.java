package Proyecto1_Recursividad.test.Pow2Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto1_Recursividad.Algoritmos;


class RecursivePow2Test {

	@Test
	void recursivePow2Caso1() {
		int n = 0;
		assertEquals(Algoritmos.recursivePow2(n), 1);
	}
	
	@Test
	void recursivePow2Caso2() {
		int n = 1;
		assertEquals(Algoritmos.recursivePow2(n), 2);
	}
	@Test
	void recursivePow2Caso3() {
		int n = 2;
		assertEquals(Algoritmos.recursivePow2(n), 4);
	}
	@Test
	void recursivePow2Caso4() {
		int n = 3;
		assertEquals(Algoritmos.recursivePow2(n), 8);
	}
	@Test
	void recursivePow2Caso5() {
		int n = 4;
		assertEquals(Algoritmos.recursivePow2(n), 16);
	}

}
