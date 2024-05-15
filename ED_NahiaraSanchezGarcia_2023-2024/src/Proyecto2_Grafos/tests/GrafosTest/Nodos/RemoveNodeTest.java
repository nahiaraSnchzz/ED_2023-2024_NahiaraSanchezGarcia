package Proyecto2_Grafos.tests.GrafosTest.Nodos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.FullStructureException;



class RemoveNodeTest {

	
	/**
	 * CASOS:
	 * 1- Si el nodo existe, lo borra del vector de nodos y sus aristas, devuelve true.
	 * 2- Si el nodo no existe, devuelve false.
	 * 3- Si el nodo a borrar es null, lanza excepcion.
	 */
	
	@Test
	void removeNodeTestCaso1() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		int node2 = 2;
		g.addNode(node0);
		g.addNode(node1);
		g.addNode(node2);
		assertTrue(g.removeNode(node2));
		assertEquals(g.getSize(), 2);
	}
	
	@Test
	void removeNodeTestCaso2() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		int node2 = 2;
		g.addNode(node0);
		g.addNode(node1);
		assertFalse(g.removeNode(node2));
	}
	
	@Test
	void removeNodeTestCaso3() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addNode(node0);
			g.addNode(node1);
			g.removeNode(null);
		}
		catch (NullPointerException msg) {
			assertEquals(msg.getMessage(), "El nodo a borrar no puede ser null");
		}
		
	}

}
