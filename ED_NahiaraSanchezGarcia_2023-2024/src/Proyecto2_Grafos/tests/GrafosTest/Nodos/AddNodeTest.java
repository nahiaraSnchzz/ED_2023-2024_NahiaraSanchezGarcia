package Proyecto2_Grafos.tests.GrafosTest.Nodos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.FullStructureException;



class AddNodeTest {
	
	/**
	 * CASOS:
	 * 1- Ya existe el nodo a añadir --> Devuelve false.
	 * 2- El nodo a añadir es null --> Lanza excepcion.
	 * 3- Size es mayor que length --> Lanza excepcion.
	 * 4- Se añade un nodo correctamente en size.
	 * @throws FullStructureException 
	 */
	@Test
	void addNodeCaso1() throws FullStructureException {
		int dimension = 4;
		Grafos<Integer> g = new Grafos<Integer>(dimension);
		try {
			int node = 1;
			g.addNode(node);
			assertEquals(false, g.addNode(node));
		}
		catch (FullStructureException msg) {
			assertEquals(msg, "El grafo ya está completo" );
		}
	}
	
	@Test
	void addNodeCaso2() throws FullStructureException {
		int dimension = 4;
		Grafos<Integer> g = new Grafos<Integer>(dimension);
		try {
			int node = 1;
			g.addNode(node);
			g.addNode(null);
			fail ("No ha saltado la excepción");
		}
		catch (NullPointerException msg1) {
			assertEquals(msg1.getMessage(), "El nodo a insertar no puede ser null");
		}
		catch (FullStructureException msg) {
			assertEquals(msg.getMessage(), "El grafo ya está completo" );
		}
	}
	
	@Test
	void addNodeCaso3() throws FullStructureException {
		int dimension = 1;
		Grafos<Integer> g = new Grafos<Integer>(dimension);
		try {
			int node = 1;
			int node2 = 2;
			g.addNode(node);
			g.addNode(node2);
			fail ("No ha saltado la excepción");
		}
		catch (NullPointerException msg1) {
			assertEquals(msg1.getMessage(), "El nodo a insertar no puede ser null");
		}
		catch (FullStructureException msg) {
			assertEquals(msg.getMessage(), "El grafo ya está completo" );
		}
	}
	
	@Test
	void addNodeCaso4() throws FullStructureException {
		int dimension = 4;
		Grafos<Integer> g = new Grafos<Integer>(dimension);
		try {
			int node = 1;
			assertTrue(g.addNode(node));
		}
		catch (NullPointerException msg1) {
			assertEquals(msg1.getMessage(), "El nodo a insertar no puede ser null");
		}
		catch (FullStructureException msg) {
			assertEquals(msg.getMessage(), "El grafo ya está completo" );
		}
	}

}
