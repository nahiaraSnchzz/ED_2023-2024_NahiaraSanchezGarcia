package Proyecto2_Grafos.tests.GrafosTest.Nodos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.FullStructureException;



class GetSizeTest {

	@Test
	void getSizeCaso1() throws FullStructureException {
		int dimension = 1;
		Grafos<Integer> g = new Grafos<Integer> (dimension);
		g.addNode(dimension);
		int size = 1;
		assertEquals(g.getSize(), size);
	}
	
	@Test
	void getSizeCaso2() throws FullStructureException {
		int dimension = 2;
		Grafos<Integer> g = new Grafos<Integer> (dimension);
		g.addNode(dimension);
		int size = 1;
		assertEquals(g.getSize(), size);
	}
	
	@Test
	void getSizeCaso3() throws FullStructureException {
		int dimension = 3;
		Grafos<Integer> g = new Grafos<Integer> (dimension);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		int size = 3;
		assertEquals(g.getSize(), size);
	}
	
	@Test
	void getSizeCaso4() throws FullStructureException {
		int dimension = 20;
		Grafos<Integer> g = new Grafos<Integer> (dimension);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		int size = 4;
		assertEquals(g.getSize(), size);
	}

}
