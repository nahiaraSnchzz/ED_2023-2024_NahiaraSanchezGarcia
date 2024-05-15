package Proyecto2_Grafos.tests.GrafosTest.Nodos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Proyecto2_Grafos.Grafos;
import Proyecto2_Grafos.Excepciones.FullStructureException;



class ExistsNodeTest {

	/**
	 * CASOS:
	 * 1- Devuelve false si no existe un nodo.
	 * 2- Devuelve true si existe el nodo.
	 */
	
	@Test
	void existsNodeCaso1() {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node = 1;
		assertFalse (g.existsNode(node));
	}
	
	@Test
	void existsNodeCaso2() throws FullStructureException {
		Grafos<Integer> g = new Grafos<Integer>(4);
		int node = 1;
		g.addNode(node);
		assertTrue (g.existsNode(node));
	}

}
