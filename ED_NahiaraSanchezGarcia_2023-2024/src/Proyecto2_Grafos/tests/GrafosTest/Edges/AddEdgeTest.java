package Proyecto2_Grafos.tests.GrafosTest.Edges;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.ElementNotPresentException;
import Proyecto2_Grafos.Excepciones.FullStructureException;


class AddEdgeTest {

	/**
	 * CASOS:
	 * 1- Se inserta correctamente la arista.
	 * 2- Ya existe la arista --> devuelve false.
	 * 3- No existe el nodo origen --> excepcion
	 * 4- No existe el nodo destino --> excepcion
	 * 5- No existe nodo origen ni destino --> excepcion.
	 * 6- El peso es inválido --> excepcion.
	 * @throws FullStructureException 
	 */
	
	@Test
	void addEdgeTestCaso1() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		g.addNode(node0);
		g.addNode(node1);
		
		assertTrue(g.addEdge(node0, node1, 2));
	}
	
	@Test
	void addEdgeTestCaso2() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		g.addNode(node0);
		g.addNode(node1);
		g.addEdge(node0, node1, 2);
		assertFalse(g.addEdge(node0, node1, 2));
	}
	
	@Test
	void addEdgeTestCaso3() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addNode(node1);
			g.addEdge(node0, node1, 2);
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(), "Al menos uno de los nodos no existe");
		}
	}
	@Test
	void addEdgeTestCaso4() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addNode(node0);
			g.addEdge(node0, node1, 2);
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(), "Al menos uno de los nodos no existe");
		}
	}
	
	@Test
	void addEdgeTestCaso5() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addEdge(node0, node1, 2);
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(), "Al menos uno de los nodos no existe");
		}
	}
	
	@Test
	void addEdgeTestCaso6() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1;
		try {
			g.addNode(node1);
			g.addEdge(node0, node1, 0);
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(), "Al menos uno de los nodos no existe");
		}
		catch (IllegalArgumentException msg2) {
			assertEquals(msg2.getMessage(), "El peso debe ser mayor de 0");
		}
	}

}
