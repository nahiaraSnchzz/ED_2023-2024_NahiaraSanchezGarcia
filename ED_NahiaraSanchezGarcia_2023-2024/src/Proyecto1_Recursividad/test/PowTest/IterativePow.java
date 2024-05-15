package Proyecto1_Recursividad.test.PowTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto1_Recursividad.Algoritmos;


class IterativePow {

	@Test
	void iterativePowCaso1() {
		int n = 2;
		int p = 0;
		assertEquals(Algoritmos.iterativePow(n, p), 1);
	}
	
	@Test
	void iterativePowCaso2() {
		int n = 2;
		int p = 1;
		assertEquals(Algoritmos.iterativePow(n, p), 2);
	}
	@Test
	void iterativePowCaso3() {
		int n = 2;
		int p = 2;
		assertEquals(Algoritmos.iterativePow(n, p), 4);
	}
	@Test
	void iterativePowCaso4() {
		int n = 2;
		int p = 3;
		assertEquals(Algoritmos.iterativePow(n, p), 8);
	}
	@Test
	void iterativePowCaso5() {
		int n = 2;
		int p = 4;
		assertEquals(Algoritmos.iterativePow(n, p), 16);
	}
}
