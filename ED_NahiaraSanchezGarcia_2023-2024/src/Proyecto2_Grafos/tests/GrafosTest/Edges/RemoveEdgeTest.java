package Proyecto2_Grafos.tests.GrafosTest.Edges;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.ElementNotPresentException;
import Proyecto2_Grafos.Excepciones.FullStructureException;



class RemoveEdgeTest {
	
	/**
	 * CASOS:
	 * 1- La arista existe --> la borra y devuelve true.
	 * 2- Nodo origen no existe --> excepcion.
	 * 3- Nodo destino no existe --> excepcion.
	 * 4- Ambos nodos no existen --> excepcion.
	 * 5- La arista a eliminar no existe --> false
	 * @throws FullStructureException 
	 */

	@Test
	void removeEdgeTestCaso1() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		g.addNode(node0);
		g.addNode(node1);
		g.addEdge(node0, node1, 2);
		assertTrue(g.removeEdge(node0, node1));
	}
	
	@Test
	void removeEdgeTestCaso2() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addNode(node1);
			g.addEdge(node0, node1, 2);
			g.removeEdge(node0, node1);
			fail ("no fallo");
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(), "Al menos uno de los nodos no existe");
		}
		
	}
	
	@Test
	void removeEdgeTestCaso3() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addNode(node0);
			g.addEdge(node0, node1, 2);
			g.removeEdge(node0, node1);
			fail ("no fallo");
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(), "Al menos uno de los nodos no existe");
		}
	}
	
	@Test
	void removeEdgeTestCaso4() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addEdge(node0, node1, 2);
			g.removeEdge(node0, node1);
			fail ("no fallo");
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(), "Al menos uno de los nodos no existe");
		}
	}
	
	@Test
	void removeEdgeTestCaso5() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		g.addNode(node0);
		g.addNode(node1);
		assertFalse(g.removeEdge(node0, node1));
	}

}
