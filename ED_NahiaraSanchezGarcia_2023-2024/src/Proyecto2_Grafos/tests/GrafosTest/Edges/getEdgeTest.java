package Proyecto2_Grafos.tests.GrafosTest.Edges;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.ElementNotPresentException;
import Proyecto2_Grafos.Excepciones.FullStructureException;



class GetEdgeTest {
	
	/**
	 * CASOS:
	 * 1- Devuelve el peso de la arista que conecta los nodos correctamente
	 * 2- Nodo origen no existe --> excepcion
	 * 3- Nodo destino no existe --> excepcion
	 * 4- Nodos no existen --> excepcion
	 * 5- La arista a eliminar no existe --> devuelve -1
	 */
	
	@Test
	void getTestCaso1() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1; 
		g.addNode(node0);
		g.addNode(node1);
		g.addEdge(node0, node1, 2);
		assertEquals(g.getEdge(node0, node1), 2);
	}
	
	@Test
	void getTestCaso2() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1; 
		try {
			g.addNode(node1);
			g.addEdge(node0, node1, 2);
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(),"Al menos uno de los nodos no existe" );
		}
	}
	
	@Test
	void getTestCaso3() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1; 
		try {
			g.addNode(node0);
			g.addEdge(node0, node1, 2);
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(),"Al menos uno de los nodos no existe" );
		}
	}
	
	@Test
	void getTestCaso4() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1; 
		try {
			g.addEdge(node0, node1, 2);
		}
		catch (ElementNotPresentException msg) {
			assertEquals(msg.getMessage(),"Al menos uno de los nodos no existe" );
		}
	}
	
	@Test
	void getTestCaso5() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node0 = 0;
		int node1 = 1; 
		g.addNode(node0);
		g.addNode(node1);
		assertEquals(g.getEdge(node0, node1), -1);
	}
}
