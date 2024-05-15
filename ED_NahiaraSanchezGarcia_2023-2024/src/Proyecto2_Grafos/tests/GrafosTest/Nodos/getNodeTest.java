package Proyecto2_Grafos.tests.GrafosTest.Nodos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.FullStructureException;


class getNodeTest {
	
	/**
	 * CASOS:
	 * 1- Si el nodo no existe devuelve -1.
	 * 2- Si el nodo existe devuelve su posición
	 */
	
	@Test
	void getNodeCaso1() {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node = 1;
		assertEquals(g.getNode(node), -1);
	}
	
	@Test
	void getNodeCaso2() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node = 1;
		g.addNode(node);
		assertEquals(g.getNode(node), 0);
	}
	
	@Test
	void getNodeCaso3() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node = 1;
		int node1 = 2;
		g.addNode(node);
		g.addNode(node1);
		assertEquals(g.getNode(node1), 1);
	}

}
