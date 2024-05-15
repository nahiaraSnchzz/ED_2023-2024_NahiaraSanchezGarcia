package Proyecto2_Grafos.tests.GrafosTest.Edges;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.ElementNotPresentException;
import Proyecto2_Grafos.Excepciones.FullStructureException;



class ExistsEdgeTest {

	/**
	 * CASOS: 1- Existe una arista entre los nodos --> true. 2- No existe nodo
	 * origen --> false 3- No existe nodo destino --> false 4- No existen ambos
	 * nodos --> false. 5- No existe arista --> false.
	 * 
	 * @throws FullStructureException
	 */

	@Test
	void existsEdgeTestCaso1() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		g.addNode(node0);
		g.addNode(node1);
		g.addEdge(node0, node1, 2);
		assertTrue(g.existsEdge(node0, node1));
	}

	@Test
	void existsEdgeTestCaso2() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addNode(node1);
			g.addEdge(node0, node1, 2);
		} catch (ElementNotPresentException msg) {
			assertFalse(g.existsEdge(node0, node1));
		}
	}

	@Test
	void existsEdgeTestCaso3() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addNode(node0);
			g.addEdge(node0, node1, 2);
		} catch (ElementNotPresentException msg) {
			assertFalse(g.existsEdge(node0, node1));
		}
	}

	@Test
	void existsEdgeTestCaso4() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addEdge(node0, node1, 2);
		} catch (ElementNotPresentException msg) {
			assertFalse(g.existsEdge(node0, node1));
		}
	}

	@Test
	void existsEdgeTestCaso5() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;

		g.addNode(node0);
		g.addNode(node1);

		assertFalse(g.existsEdge(node0, node1));

	}

}
